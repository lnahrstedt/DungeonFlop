package control.model.fabrik;

import model.HauptSpieler;
import model.Spieler;
import model.Spielfeld;
import model.Spielfigur;
import resources.interfaces.Zahlensammlung;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Diese Fabrik erzeugt Spielfelder in unterschiedlichen Groessen.
 */
public class SpielfeldFabrik {

    /**
     * Diese Methode erzeugt ein Spielfeld der Groesse 4x4.
     *
     * @param hauptSpieler Dies ist der Spieler, der auf dem Spielfeld spielen wird.
     * @param gegner       Dies ist der Gegner, der auf dem Spielfeld spielen wird.
     * @return Spielfeld der Groesse 4x4
     */
    public static Spielfeld erstelleKleinesSpielfeld(HauptSpieler hauptSpieler, Spieler gegner) {
        HashMap<Spieler, ArrayList<Spielfigur>> spielfigurenAufDemFeld = erstelleSpielfigurenAufDemFeldHashMap(hauptSpieler, gegner);
        return new Spielfeld(Zahlensammlung.KLEINES_SPIELFELD_HOEHE, Zahlensammlung.KLEINES_SPIELFELD_BREITE, spielfigurenAufDemFeld);
    }

    /**
     * Diese Methode erzeugt ein Spielfeld der Groesse 5x5.
     *
     * @param hauptSpieler Dies ist der Spieler, der auf dem Spielfeld spielen wird.
     * @param gegner       Dies ist der Gegner, der auf dem Spielfeld spielen wird.
     * @return Spielfeld der Groesse 5x5
     */
    public static Spielfeld erstelleMittleresSpielfeld(HauptSpieler hauptSpieler, Spieler gegner) {
        HashMap<Spieler, ArrayList<Spielfigur>> spielfigurenAufDemFeld = erstelleSpielfigurenAufDemFeldHashMap(hauptSpieler, gegner);
        return new Spielfeld(Zahlensammlung.MITTLERES_SPIELFELD_HOEHE, Zahlensammlung.MITTLERES_SPIELFELD_BREITE, spielfigurenAufDemFeld);
    }

    /**
     * Diese Methode erzeugt ein Spielfeld der Groesse 6x6.
     *
     * @param hauptSpieler Dies ist der Spieler, der auf dem Spielfeld spielen wird.
     * @param gegner       Dies ist der Gegner, der auf dem Spielfeld spielen wird.
     * @return Spielfeld der Groesse 6x6
     */
    public static Spielfeld erstelleGrossesSpielfeld(HauptSpieler hauptSpieler, Spieler gegner) {
        HashMap<Spieler, ArrayList<Spielfigur>> spielfigurenAufDemFeld = erstelleSpielfigurenAufDemFeldHashMap(hauptSpieler, gegner);
        return new Spielfeld(Zahlensammlung.GROSSES_SPIELFELD_HOEHE, Zahlensammlung.GROSSES_SPIELFELD_BREITE, spielfigurenAufDemFeld);
    }

    /**
     * Erstellt die HashMap mit den Spielern und den zugehoerigen Spielfiguren
     *
     * @param hauptSpieler Der Spieler dem die Spielfiguren zugewiesen werden sollen
     * @param gegner       Der Gegner dem die Spielfiguren zugewiesen werden soll
     * @return HashMap aus Spielern und Spielfiguren
     */
    private static HashMap<Spieler, ArrayList<Spielfigur>> erstelleSpielfigurenAufDemFeldHashMap(HauptSpieler hauptSpieler, Spieler gegner) {
        ArrayList<Spielfigur> spielfiguren1 = new ArrayList<>();
        ArrayList<Spielfigur> spielfiguren2 = new ArrayList<>();
        HashMap<Spieler, ArrayList<Spielfigur>> spielfigurenAufDemFeld = new HashMap<>();
        spielfigurenAufDemFeld.put(hauptSpieler, spielfiguren1);
        spielfigurenAufDemFeld.put(gegner, spielfiguren2);
        return spielfigurenAufDemFeld;
    }

}
