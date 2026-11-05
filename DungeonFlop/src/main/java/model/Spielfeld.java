package model;

import resources.exceptions.FeldNichtVerfuegbarException;
import resources.interfaces.StringsammlungGUI;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Speichert die Positionen der Spielfiguren in einem 2D Array ab.
 * Definiert ausserdem, welche Spielfigur zu welchem Spieler gehoert.
 */
public class Spielfeld implements Serializable {

    private int hoehe;
    private int breite;
    private Spielfigur[][] felder;
    private HashMap<Spieler, ArrayList<Spielfigur>> spielfigurenAufDemFeld;

    public Spielfeld(int hoehe, int breite, HashMap<Spieler, ArrayList<Spielfigur>> spielfigurenAufDemFeld) {
        this.hoehe = hoehe;
        this.breite = breite;
        this.felder = new Spielfigur[hoehe][breite];
        this.spielfigurenAufDemFeld = spielfigurenAufDemFeld;
    }

    /**
     * Setzt eine Spielfigur auf die angegebene Position.
     *
     * @param spielfigur
     * @param hoehe
     * @param breite
     * @throws FeldNichtVerfuegbarException - wenn das Feld nicht verfuegbar ist.
     */
    public void setSpielfigurenfeld(Spielfigur spielfigur, int hoehe, int breite) throws FeldNichtVerfuegbarException {
        if (hoehe > this.hoehe || breite > this.breite) {
            throw new FeldNichtVerfuegbarException(hoehe, breite);
        }
        this.getFelder()[hoehe][breite] = spielfigur;
    }

    /**
     * Gibt die an der angegebenen Position stehende Spielfigur zurueck.
     *
     * @param hoehe
     * @param breite
     * @return
     * @throws FeldNichtVerfuegbarException - wenn das Feld nicht verfuegbar ist.
     */
    public Spielfigur getSpielfigur(int hoehe, int breite) throws FeldNichtVerfuegbarException {
        try {
            return getFelder()[hoehe][breite];
        } catch (IndexOutOfBoundsException e) {
            throw new FeldNichtVerfuegbarException();
        }
    }

    public int getHoehe() {
        return hoehe;
    }

    public void setHoehe(int hoehe) {
        this.hoehe = hoehe;
    }

    public int getBreite() {
        return breite;
    }

    public void setBreite(int breite) {
        this.breite = breite;
    }

    public Spielfigur[][] getFelder() {
        return felder;
    }

    public void setFelder(Spielfigur[][] felder) {
        this.felder = felder;
    }

    public HashMap<Spieler, ArrayList<Spielfigur>> getSpielfigurenAufDemFeld() {
        return spielfigurenAufDemFeld;
    }

    public void setSpielfigurenAufDemFeld(HashMap<Spieler, ArrayList<Spielfigur>> spielfigurenAufDemFeld) {
        this.spielfigurenAufDemFeld = spielfigurenAufDemFeld;
    }

    /**
     * Gibt alle Felder des Spielfelds als formatierten String in Tabellenform zurueck.
     *
     * @return String
     */

    //@Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Spielfigur[] spielfiguren : felder) {
            for (Spielfigur spielfigur : spielfiguren) {
                if (spielfigur == null) {
                    s.append(StringsammlungGUI.CONSOLE_RAUM_BELEGT);
                } else {
                    s.append(StringsammlungGUI.CONSOLE_RAUM_LINKS).append(spielfigur.toString()).append(StringsammlungGUI.CONSOLE_RAUM_RECHTS);
                }
            }
            s.append("\n");
        }
        return s.toString();
    }
}
