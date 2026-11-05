package control.model;

import control.model.fabrik.SpielfeldFabrik;
import model.*;
import model.karte.Karte;
import model.karte.Kartenverwaltung;
import model.netzwerk.NetzwerkFlagContainer;
import resources.interfaces.Zahlensammlung;
import utilities.MeinIO;

import java.util.*;

/**
 * Dieser Controller steuert den Kampf innerhalb eines Raumes.
 */
public class KampfController {

    private static Runde aktuelleRunde = null;
    private static Kampf kampf = null;
    private static Spieler gegenSpieler = null;

    private static boolean spielIchGegner = false;

    /**
     * Diese Methode kontrolliert den Kampf.
     *
     * @param hauptSpieler Dies ist der Hauptspieler des Kampfs.
     * @param gegner       Dies ist der Gegner des Kampfs.
     */
    public static void starteKampf(HauptSpieler hauptSpieler, Spieler gegner) {
        NetzwerkFlagContainer.setBinIchAnDerReihe(true);
        setKampf(erstelleKampf(hauptSpieler, gegner));
        setAktuelleRunde(RundenController.starteErsteRunde(getKampf(), hauptSpieler, gegner, getKampf().getSpielfeld()));
    }

    /**
     * @param hauptSpieler Dies ist der Spieler des Kampfs.
     * @param gegner       Dies ist ebenfalls ein Spieler, der als Gegner antritt.
     * @return Der erstellte Kampf wird zurueckgegeben
     */
    private static Kampf erstelleKampf(HauptSpieler hauptSpieler, Spieler gegner) {
        Spielfeld spielfeld;
        Random r = new Random();
        spielfeld = switch (r.nextInt(Zahlensammlung.ANZAHL_SPIELFELDGROESSEN)) {
            case 0 -> SpielfeldFabrik.erstelleKleinesSpielfeld(hauptSpieler, gegner);
            case 1 -> SpielfeldFabrik.erstelleGrossesSpielfeld(hauptSpieler, gegner);
            default -> SpielfeldFabrik.erstelleMittleresSpielfeld(hauptSpieler, gegner);
        };
        spielfeld.getSpielfigurenAufDemFeld().get(hauptSpieler).add(hauptSpieler.getSpielfigur());
        spielfeld.getSpielfigurenAufDemFeld().get(gegner).add(gegner.getSpielfigur());
        HashMap<Spieler, Kartenverwaltung> spielerKartenverwaltung = new HashMap<>();
        spielerKartenverwaltung.put(hauptSpieler, erstelleKartenverwaltung(hauptSpieler));
        spielerKartenverwaltung.put(gegner, erstelleKartenverwaltung(gegner));
        return new Kampf(hauptSpieler, gegner, spielerKartenverwaltung, spielfeld);
    }

    /**
     * Diese Methode erzeugt die Kartenverwaltung fuer den entsprechenden Spieler.
     *
     * @param spieler Dies ist der Spieler, fuer den die Kartenverwaltung erstellt werden soll.
     * @return Kartenverwaltung
     */
    public static Kartenverwaltung erstelleKartenverwaltung(Spieler spieler) {
        ArrayList<Karte> handkarten = new ArrayList<>();
        ArrayList<Karte> ablagestapel = new ArrayList<>();
        Stack<Karte> aufnahmestapel = new Stack<>();
        aufnahmestapel.addAll(kartenDurchmischen(spieler.getDeck()));
        return new Kartenverwaltung(aufnahmestapel, handkarten, ablagestapel);
    }

    private static ArrayList<Karte> kartenDurchmischen(ArrayList<Karte> spielerDeck) {
        for (int i = spielerDeck.size() - 1; i >= 1; i--) {
            Collections.swap(spielerDeck, i, MeinIO.getRandomInt(i + 1));
        }
        return spielerDeck;
    }

    public static Runde getAktuelleRunde() {
        return aktuelleRunde;
    }

    public static void setAktuelleRunde(Runde aktuelleRunde) {
        KampfController.aktuelleRunde = aktuelleRunde;
    }

    public static Kampf getKampf() {
        return kampf;
    }

    public static void setKampf(Kampf kampf) {
        KampfController.kampf = kampf;
    }

    public static Spieler getGegenSpieler() {
        return gegenSpieler;
    }

    public static void setGegenSpieler(Spieler gegenSpieler) {
        KampfController.gegenSpieler = gegenSpieler;
    }

    public static boolean spielIchGegner() {
        return spielIchGegner;
    }

    public static void setSpielIchGegner(boolean spielIchGegner) {
        KampfController.spielIchGegner = spielIchGegner;
    }
}
