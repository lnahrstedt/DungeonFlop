package control.model;

import control.model.raum.RaumController;
import control.netzwerk.ClientThreadController;
import control.netzwerk.NetzwerkController;
import control.netzwerk.ServerThreadController;
import control.view.AlertFabrik;
import control.view.ViewWechsler;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.util.Duration;
import model.*;
import model.effekt.Effekt;
import model.effekt.MehrfachSchlagenEffekt;
import model.effekt.SofortLoslaufenEffekt;
import model.effekt.WeiterGehenEffekt;
import model.karte.Karte;
import model.karte.Minion;
import model.karte.Zauber;
import model.netzwerk.NetzwerkFlagContainer;
import model.raum.Gegnerraum;
import org.controlsfx.control.Notifications;
import org.jetbrains.annotations.NotNull;
import resources.enums.ServerClientKommunikation;
import resources.exceptions.*;
import resources.interfaces.StringsammlungAlert;
import resources.interfaces.StringsammlungGUI;
import resources.interfaces.Zahlensammlung;
import utilities.MeinIO;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Diese Klasse verwaltet bzw. steuert die Runden innerhalb eines Kampfes.
 */
public class RundenController {

    /**
     * Setzt den Spieler und den Gegner auf die Startpositionen, erstellt den Handkartenstapel fuer den Spieler
     * und den Gegner und erstellt die erste Runde mit dem spieler, den verbleibenden Aktionen und
     * dem verbleibenden Mana.
     *
     * @param kampf        Dies ist der aktuelle Kampf
     * @param hauptSpieler Der Spieler der gerade am Zug ist
     * @param gegner       Der Gegner, welcher nicht am Zug ist
     * @param spielfeld    Das aktuelle Spielfeld
     */
    public static Runde starteErsteRunde(Kampf kampf, HauptSpieler hauptSpieler, Spieler gegner, Spielfeld spielfeld) {

        //Setzt die Handkarten des Hauptspielers
        KartenverwaltungsController.bewegeKartenVonAufnahmeZuHand(kampf.getSpielerKartenverwaltung().get(hauptSpieler), hauptSpieler.getKartenProRunde());

        //Platziert die Spielfiguren der Spieler: Den Gegner auf [0][0], den HauptSpieler in die untere rechte Ecke
        try {
            spielfeld.setSpielfigurenfeld(gegner.getSpielfigur(), Zahlensammlung.GEGNERSTARTHOEHE, Zahlensammlung.GEGNERSTARTBREITE);
            spielfeld.setSpielfigurenfeld(hauptSpieler.getSpielfigur(), spielfeld.getHoehe() - 1, spielfeld.getBreite() - 1);
        } catch (FeldNichtVerfuegbarException e) {
            MeinIO.stringAusgeben(e.getMessage());
        }

        HashMap<Spielfigur, AktionsSpeicher> aktionen = new HashMap<>();

        //packt die verfuegbaren Aktionen fuer die Spieler in eine HashMap
        aktionen.put(hauptSpieler.getSpielfigur(), generiereAktionsSpeicher(hauptSpieler.getSpielfigur()));
        aktionen.put(gegner.getSpielfigur(), generiereAktionsSpeicher(gegner.getSpielfigur()));

        //erstellt die Runde
        Runde runde = new Runde(hauptSpieler, aktionen, hauptSpieler.getMana());
        KampfController.setAktuelleRunde(runde);
        return runde;
    }

    /**
     * Erstellt den Handkartenstapel fuer den Spieler, setzt den Aktionsspeicher,
     * erstellt die neue Runde und startet den RundenLoop.
     *
     * @param kampf     Dies ist der aktuelle Kampf
     * @param spieler   Der Spieler der aktuell am Zug
     * @param spielfeld Das Spielfeld mit den aktuellen Spielfiguren
     */
    public static Runde starteRunde(Kampf kampf, Spieler spieler, Spielfeld spielfeld) {
        KartenverwaltungsController.bewegeKartenVonAufnahmeZuHand(kampf.getSpielerKartenverwaltung().get(spieler), spieler.getKartenProRunde());
        HashMap<Spielfigur, AktionsSpeicher> aktionen = new HashMap<>();
        for (int i = 0; i < spielfeld.getSpielfigurenAufDemFeld().get(spieler).size(); i++) {
            Spielfigur aktuelleSpielfigur = spielfeld.getSpielfigurenAufDemFeld().get(spieler).get(i);
            aktionen.put(aktuelleSpielfigur, generiereAktionsSpeicher(aktuelleSpielfigur));
        }
        return new Runde(spieler, aktionen, spieler.getMana());
    }

    /**
     * Bewegt die Figur auf ein Feld, wenn dieses leer ist. Wenn sich auf dem Zielfeld eine Figur des Gegners befindet,
     * wird diese angegriffen.
     *
     * @param spieler   Der Spieler, der gerade am Zug ist
     * @param spielfeld Das Spielfeld
     * @param runde     Die aktuelle Runde
     */
    public static void figurBewegen(Spieler spieler, Spielfeld spielfeld, Runde runde, Spielfigur spielfigur, int hoehe, int breite) throws BewegungsException, KeineAngriffeMehrException, NichtImAktionsradiusException, NichtImAngriffsradiusException, FeldNichtVerfuegbarException {
        if (SpielfeldController.istFeldFrei(spielfeld, hoehe, breite)) {
            if (!runde.getAktionen().containsKey(spielfigur)) {
                pruefeAufSofortLosLaufenEffektUndFuehreAus((Minion) spielfigur, runde);
            }
            bewegeFigur(runde, spielfigur, spielfeld, hoehe, breite);
            NetzwerkController.sendeKampfDatenPaket();
            Platform.runLater(ViewWechsler::wechselZuGegnerraumView);
        }
        if (SpielfeldController.istGegnerfeld(spielfeld, hoehe, breite, spieler)) {
            if (SpielfeldController.istFeldImAngriffsradius(hoehe, breite, spielfigur, spielfeld)) {
                if (runde.getAktionen().get(spielfigur).getAngriffe() > 0) {
                    try {
                        spielfigur.angreifen(spielfeld.getSpielfigur(hoehe, breite));
                        ViewWechsler.wechselZuGegnerraumView();
                        ueberpruefeAngriffUndFuehreAus(spielfeld, runde, hoehe, breite);
                        veraendereErlaubteRestlicheAngriffe(runde.getAktionen().get(spielfigur).getAngriffe() - 1, spielfigur, runde);
                    } catch (FeldNichtVerfuegbarException e) {
                        throw new FeldNichtVerfuegbarException();
                    }
                } else {
                    throw new KeineAngriffeMehrException();
                }
            } else {
                throw new NichtImAngriffsradiusException();
            }
        }
    }

    /**
     * Diese Methode bewegt unter Beruecksichtigung der Regeln eine Figur auf dem Spielfeld.
     *
     * @param runde
     * @param spielfigur
     * @param spielfeld
     * @param hoehe
     * @param breite
     * @throws BewegungsException
     */
    private static void bewegeFigur(Runde runde, Spielfigur spielfigur, Spielfeld spielfeld, int hoehe, int breite) throws BewegungsException, NichtImAktionsradiusException {
        if (runde.getAktionen().containsKey(spielfigur)) {
            if (runde.getAktionen().get(spielfigur).getBewegungen() > 0) {
                SpielfeldController.bewegeFigur(spielfigur, spielfeld, hoehe, breite, runde);
                veraendereErlaubteRestlicheBewegung(runde.getAktionen().get(spielfigur).getBewegungen() - 1, spielfigur, runde);
            } else {
                throw new BewegungsException();
            }
        } else {
            Notifications notification = Notifications.create().title(StringsammlungGUI.NOTIFICATION_KEIN_AKTIONSSPEICHER_VORHANDEN).text(null).graphic(null).hideAfter(Duration.seconds(Zahlensammlung.ANZEIGEDAUER_NOTIFICATIONS)).position(Pos.TOP_RIGHT);
            notification.show();
        }
    }

    /**
     * Diese Methode fuehrt alle notwendigen Operationen aus, wenn ein Spieler (Server) gewonnen hat.
     */
    private static void spielerHatGewonnen() {
        RaumController.erstelleLeerenErsatzraum((Gegnerraum) Spielwelt.getInstance().getSpielerEbenePosition().getSpielerRaumPosition());
    }

    /**
     * Diese Methode fuehrt alle notwendigen Operationen aus, wenn ein Spieler (Server) verloren hat.
     *
     * @throws FeldNichtVerfuegbarException
     */
    private static void spielerHatVerloren() {
        // Server verloren, Client gewonnen
        if (NetzwerkFlagContainer.wirdOnlineGespielt()) {
            ClientThreadController.getInstance().sendeDaten(ServerClientKommunikation.VERLOREN);
            AlertFabrik.generiereAlert(Alert.AlertType.INFORMATION, StringsammlungAlert.RUNDE_GEWONNEN_TITEL, StringsammlungAlert.RUNDE_GEWONNEN_NACHRICHT_CLIENT).show();
            ViewWechsler.wechselZuStartView();
        }
        // Singleplayer, Hauptspieler verloren
        else {
            AlertFabrik.generiereAlert(Alert.AlertType.INFORMATION, StringsammlungAlert.RUNDE_VERLOREN_TITEL, StringsammlungAlert.RUNDE_VERLOREN_NACHRICHT).show();
            ViewWechsler.wechselZuStartView();
            SpielController.kampfVerloren();
        }
    }

    /**
     * Wenn ein Minion platziert werden soll, wird eine Spielfigur des Minions erstellt. Wird ein Zauber verwendet,
     * wird der Effekt des Zaubers angewendet.
     *
     * @param spieler   Der Spieler, der gerade am Zug ist
     * @param spielfeld Das aktuelle Spielfeld
     * @param runde     Die aktuelle Runde
     * @param karte     Die Karte, die gespielt werden soll
     * @param hoehe     Die Hoehe auf der die Karte gespielt werden soll
     * @param breite    Die breite auf der die Karte gespielt werden soll
     * @param kampf     Der Kampf
     */
    public static void figurPlatzieren(Spieler spieler, Spielfeld spielfeld, Runde runde, Karte karte, int hoehe, int breite, Kampf kampf) throws FeldNichtVerfuegbarException, FeldIstKeinNachbarException, SolException {
        if (!((runde.getAktuelleMana() - karte.getManakosten()) < 0)) {
            if (karte instanceof Minion minion) {
                Minion minion_kopie = new Minion(minion);
                SpielfeldController.setzeFigur(spielfeld, minion_kopie, hoehe, breite, runde);
                zieheManaAb(minion_kopie, runde);
                KartenverwaltungsController.bewegeKarte(minion, kampf.getSpielerKartenverwaltung().get(spieler));
                pruefeAufSofortLosLaufenEffektUndFuehreAus(minion_kopie, runde);
                NetzwerkController.sendeKampfDatenPaket();
            } else {
                try {
                    if (SpielfeldController.istGegnerfeld(spielfeld, hoehe, breite, spieler)) {
                        ((Zauber) karte).angreifen(spielfeld.getSpielfigur(hoehe, breite));
                        zieheManaAb(karte, runde);
                        KartenverwaltungsController.bewegeKarte(karte, kampf.getSpielerKartenverwaltung().get(spieler));
                        ueberpruefeAngriffUndFuehreAus(spielfeld, runde, hoehe, breite);
                    }
                } catch (FeldNichtVerfuegbarException e) {
                    throw new FeldNichtVerfuegbarException();
                }
            }
        } else {
            throw new SolException();
        }
    }

    /**
     * Diese Methode ueberprueft, ob eine Spielfigur den SofortLosLaufenEffekt hat und fuehrt diesen gegebenenfalls aus.
     * Es erstellt ausserdem einen Aktionsspeicher.
     *
     * @param minion
     * @param runde
     */
    private static void pruefeAufSofortLosLaufenEffektUndFuehreAus(Minion minion, Runde runde) {
        AktionsSpeicher aktionsSpeicher = new AktionsSpeicher(0, 0);

        boolean sofortLosLaufEfffekt = false;
        if (minion.getEffekte() != null) {
            for (int i = 0; i < minion.getEffekte().size(); i++) {
                if (minion.getEffekte().get(i) instanceof SofortLoslaufenEffekt) {
                    sofortLosLaufEfffekt = true;
                    break;
                }
            }
        }

        if (sofortLosLaufEfffekt) {
            aktionsSpeicher.setBewegungen(1);
            aktionsSpeicher.setAngriffe(1);
            for (int i = 0; i < minion.getEffekte().size(); i++) {
                if (minion.getEffekte().get(i) instanceof WeiterGehenEffekt) {
                    aktionsSpeicher.setBewegungen(aktionsSpeicher.getBewegungen() + ((WeiterGehenEffekt) minion.getEffekte().get(i)).getWert());
                }
                if (minion.getEffekte().get(i) instanceof MehrfachSchlagenEffekt) {
                    aktionsSpeicher.setAngriffe(aktionsSpeicher.getAngriffe() + ((MehrfachSchlagenEffekt) minion.getEffekte().get(i)).getWert());
                }
            }
        }
        runde.getAktionen().put(minion, aktionsSpeicher);
    }

    /**
     * Diese Methode ueberprueft, ob ein Angriff durchgefuehrt werden darf.
     * Ist dies der Fall wird der Angriff ausgefuehrt werden.
     *
     * @param spielfeld
     * @param runde
     * @param hoehe
     * @param breite
     * @throws FeldNichtVerfuegbarException
     */
    private static void ueberpruefeAngriffUndFuehreAus(Spielfeld spielfeld, Runde runde, int hoehe, int breite) throws FeldNichtVerfuegbarException {
        if (spielfeld.getSpielfigur(hoehe, breite).getLebenspunkte() <= 0) {
            if (spielfeld.getSpielfigur(hoehe, breite) == KampfController.getKampf().getGegner().getSpielfigur()) {
                spielerHatGewonnen();
            } else if (spielfeld.getSpielfigur(hoehe, breite) == KampfController.getKampf().getSpieler().getSpielfigur()) {
                spielerHatVerloren();
            } else {
                SpielfeldController.entferneFigur(spielfeld.getSpielfigur(hoehe, breite), spielfeld, runde);
                ViewWechsler.wechselZuGegnerraumView();
                NetzwerkController.sendeKampfDatenPaket();
            }
        } else {
            ViewWechsler.wechselZuGegnerraumView();
            NetzwerkController.sendeKampfDatenPaket();
        }
    }

    /**
     * Generiert den Aktionsspeicher fuer eine Figur, basierend auf den Effekten.
     *
     * @param spielfigur Dies ist die Spielfigur, welche einen
     * @return AktionsSpeicher mit den Bewegungs- und Angriffswerten der Effekte des Spielers
     */
    public static AktionsSpeicher generiereAktionsSpeicher(Spielfigur spielfigur) {
        ArrayList<Effekt> effekte = spielfigur.getEffekte();
        AktionsSpeicher aktionsSpeicher = new AktionsSpeicher(1, 1);
        for (Effekt effekt : effekte) {
            effekt.ausfuehren(aktionsSpeicher);
        }
        return aktionsSpeicher;
    }

    /**
     * Beendet die runde, legt alle Karten vom Handstapel auf den Ablagestapel
     * Prueft ob der Aufnahmestapel leer ist
     *
     * @param kampf        Der aktuelle Kampf
     * @param hauptSpieler Der Spieler, der am Zug ist
     * @param gegner
     * @param spielfeld
     */
    public static void beendeRunde(@NotNull Kampf kampf, Spieler hauptSpieler, Spieler gegner, Spielfeld spielfeld) {
        KartenverwaltungsController.bewegeKartenVonHandZuAblage(kampf.getSpielerKartenverwaltung().get(hauptSpieler));
        KartenverwaltungsController.bewegeKartenVonAblageZuAufnahme(kampf.getSpielerKartenverwaltung().get(hauptSpieler));
        if (NetzwerkFlagContainer.wirdOnlineGespielt()) {
            if (NetzwerkFlagContainer.binIchClient()) {
                Runde neueRunde = starteRunde(kampf, kampf.getSpieler(), spielfeld);
                KampfController.setGegenSpieler(kampf.getSpieler());
                KampfController.setAktuelleRunde(neueRunde);
                ClientThreadController.getInstance().sendeDaten(new KampfDatenPaket(kampf, neueRunde, kampf.getSpieler()));

            } else {
                Runde neueRunde = starteRunde(kampf, kampf.getGegner(), spielfeld);
                KampfController.setGegenSpieler(kampf.getGegner());
                KampfController.setAktuelleRunde(neueRunde);
                ServerThreadController.getInstance().sendeDaten(new KampfDatenPaket(kampf, neueRunde, kampf.getGegner()));
            }
            NetzwerkFlagContainer.setBinIchAnDerReihe(false);
        } else {
            Runde neueRunde = starteRunde(kampf, gegner, spielfeld);
            KampfController.setGegenSpieler(hauptSpieler);
            KampfController.setAktuelleRunde(neueRunde);
        }

    }

    /**
     * Setzt die restlichen Bewegungen der Spielfigur fuer eine Runde auf den uebergebenen Wert
     *
     * @param restlicheBewegungen Der Wert auf den die restlichen Bewegungen gesetzt werden sollen
     * @param spielfigur          Die Spielfigur, fuer die der Aktionsspeicher aktualisiert werden soll
     * @param runde               Die aktuelle Runde
     */
    public static void veraendereErlaubteRestlicheBewegung(int restlicheBewegungen, Spielfigur spielfigur, Runde runde) {
        runde.getAktionen().get(spielfigur).setBewegungen(restlicheBewegungen);
    }

    /**
     * Setzt die restlichen Angriffe der Spielfigur fuer eine Runde auf den uebergebenen Wert
     *
     * @param restlicheAngriffe Der Wert auf den die restlichen Angriffe gesetzt werden sollen
     * @param spielfigur        Die Spielfigur, fuer die der Aktionsspeicher aktualisiert werden soll
     * @param runde             Die aktuelle Runde
     */
    public static void veraendereErlaubteRestlicheAngriffe(int restlicheAngriffe, Spielfigur spielfigur, Runde runde) {
        runde.getAktionen().get(spielfigur).setAngriffe(restlicheAngriffe);
    }

    /**
     * Zieht den Wert des Mana ab, den die Karte kostet
     *
     * @param karte Die Karte, dessen Wert des Mana geholt wird
     * @param runde Die aktuelle Runde
     */
    public static void zieheManaAb(Karte karte, Runde runde) {
        runde.setAktuelleMana(runde.getAktuelleMana() - karte.getManakosten());
    }
}