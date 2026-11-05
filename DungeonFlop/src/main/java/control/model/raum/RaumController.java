package control.model.raum;

import control.model.EbenenController;
import control.model.HauptspielerController;
import control.model.KampfController;
import control.model.SpielController;
import control.netzwerk.ServerThreadController;
import control.view.AlertFabrik;
import control.view.ViewWechsler;
import javafx.scene.control.Alert;
import model.*;
import model.netzwerk.NetzwerkFlagContainer;
import model.raum.*;
import resources.enums.Gegner;
import resources.enums.ServerClientKommunikation;
import resources.interfaces.StringsammlungAlert;
import resources.interfaces.StringsammlungRaum;
import resources.interfaces.Zahlensammlung;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Diese Klasse steuert bzw. verwaltet alle Vorgaenge im Zusammenhang eines Raums.
 */
public class RaumController {

    /**
     * Fuehrt den Gegnerraum aus.
     * Startet einen Kampf mit einem anhand des Schwierigkeitsgrads ausgewaehltem Gegner.
     * Ist der Kampf gewonnen, wird der Gegnerraum mit einem leeren Raum ersetzt.
     * Ist der Kampf verloren, ist das Spiel verloren.
     *
     * @param gegnerraum
     */
    public static void fuehreRaumAus(Gegnerraum gegnerraum) {
        Spieler gegenspieler;
        if (RaumController.istRaumEinBossraum(gegnerraum)) {
            gegenspieler = Gegner.getBoss();
        } else {
            gegenspieler = Gegner.getGegner(gegnerraum.getSchwierigkeitsgrad());
        }
        KampfController.setGegenSpieler(gegenspieler);
        KampfController.starteKampf(Spielwelt.getInstance().getProtagonist(), gegenspieler);
    }

    /**
     * Sofern der Spieler die Spielfigur des Gegners in einem Gegnerraum besiegt hat, wird diese Methode aufgerufen.
     * Sie sorgt dafuer, dass der besiegte Raum mit einem leeren Raum ersetzt wird.
     *
     * @param gegnerraum
     */
    public static void erstelleLeerenErsatzraum(Gegnerraum gegnerraum) {
        Raum leererErsatzRaum = new Raum(gegnerraum.getName() + StringsammlungRaum.RAUM_BESIEGT, gegnerraum.getBeschreibung(), gegnerraum.getRaumnummer());
        int aktuelleEbene = 0;
        for (int i = 0; i < Spielwelt.getInstance().getEbenen().size(); i++) {
            if (Spielwelt.getInstance().getEbenen().get(i).getLayout().containsKey(gegnerraum)) {
                aktuelleEbene = i;
                EbenenController.raumAustauschen(Spielwelt.getInstance().getEbenen().get(i), gegnerraum, leererErsatzRaum);
            }
        }
        HauptSpieler protagonist = Spielwelt.getInstance().getProtagonist();
        protagonist.setGold(Math.round(protagonist.getGold() + (aktuelleEbene + 1) * Spielwelt.getInstance().getGoldFaktor()));
        protagonist.setErfahrungsPunkte(Math.round(protagonist.getErfahrungsPunkte() + (aktuelleEbene + 1) * Spielwelt.getInstance().getErfahrungsFaktor()));

        if (protagonist.getErfahrungsPunkte() >= protagonist.getLevel() * Zahlensammlung.ERFAHRUNGSKOSTENFAKTOR) {
            HauptspielerController.aufleveln();
        } else {
            AlertFabrik.generiereAlert(Alert.AlertType.INFORMATION, StringsammlungAlert.RUNDE_GEWONNEN_TITEL, StringsammlungAlert.RUNDE_GEWONNEN_NACHRICHT).show();

            if (NetzwerkFlagContainer.wirdOnlineGespielt()) {
                ServerThreadController.getInstance().sendeDaten(ServerClientKommunikation.VERLOREN);
                ServerThreadController.getInstance().sendeDaten(Spielwelt.getInstance());
            }
            ViewWechsler.wechselZuEbenenLayoutView();
        }
    }

    /**
     * Fuehrt den leeren Raum aus.
     * Ueberprueft, ob es sich bei dem Raum um den Leiterraum handelt,
     * ist dies wahr, ist das Spiel gewonnen, falls es die letzte Ebene ist.
     * Sonst wird die Ebenenposition um eins erhoeht.
     *
     * @param raum
     */
    public static void fuehreRaumAus(Raum raum) {
        for (int i = 0; i < Spielwelt.getInstance().getEbenen().size(); i++) {
            // Ueberprueft, in welcher Ebene sich der Raum befindet.
            if (Spielwelt.getInstance().getEbenen().get(i).getLayout().containsKey(raum)) {
                if (raum.equals(RaeumeInEbene.getLeiterraum(i))) {
                    // Ueberprueft, ob sich der Spieler in der letzten Ebene befindet.
                    if (i == Spielwelt.getInstance().getEbenen().size() - 1) {
                        SpielController.spielGewonnen();
                        return;
                    }
                    // Setzt die SpielerEbenePosition auf die naechste Ebene.
                    Spielwelt.getInstance().setSpielerEbenePosition(Spielwelt.getInstance().getEbenen().get(i + 1));
                    return;
                }
            }
        }
    }

    /**
     * Diese Methode prueft, ob der uebergebene Raum leer ist.
     *
     * @param raum
     * @return true, wenn raum leer; false, wenn nicht
     */
    public static boolean istRaumLeer(Raum raum) {

        return !(raum instanceof Haendlerraum
                || raum instanceof Schmiedraum
                || raum instanceof Schatzraum
                || raum instanceof Gegnerraum
                || istRaumEinLeiterraum(raum));
    }

    /**
     * Diese Methode prueft, ob der uebergebene Raum ein Leiterraum ist
     *
     * @param raum
     * @return true, ja; false, nein
     */
    public static boolean istRaumEinLeiterraum(Raum raum) {

        return Spielwelt.getInstance().getSpielerEbenePosition().getLayout().size() == raum.getRaumnummer() + 1;
    }

    public static boolean istRaumEinBossraum(Gegnerraum gegnerraum) {

        for (int i = 0; i < Spielwelt.getInstance().getEbenen().size(); i++) {
            Ebene ebene = Spielwelt.getInstance().getEbenen().get(i);
            HashMap<Raum, ArrayList<Raum>> layout = ebene.getLayout();
            if (layout.containsKey(gegnerraum)) {
                for (Raum nachbarRaueme : layout.get(gegnerraum)) {
                    if (istRaumEinLeiterraum(nachbarRaueme)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Ueberprueft, ob der uebergebene Raum der Leiterraum der letzten Ebene ist.
     * Ist dies der Fall, hat der Spieler das Spiel gewonnen.
     *
     * @param raum
     * @return true, Spiel gewonnen; false, noch nicht gewonnen
     */
    public static boolean istRaumLetzterRaum(Raum raum) {

        boolean letzterRaum = false;

        if (!istRaumEinLeiterraum(raum) || istRaumLeer(raum)) {
            return false;
        }

        if (RaeumeInEbene.getLeiterraum(Spielwelt.getInstance().getEbenen().size() - 1).equals(raum)) {
            letzterRaum = true;
        }
        return letzterRaum;
    }

}