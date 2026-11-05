package model.karte;

import resources.interfaces.StringsammlungGUI;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Stack;

/**
 * Die Klasse Kartenverwaltung stellt die verschiedenen Kartenstapel waehrend des Kampfes zur Verfuegung
 */
public class Kartenverwaltung implements Serializable {

    private Stack<Karte> aufnahmestapel;
    private ArrayList<Karte> handkarten;
    private ArrayList<Karte> ablagestapel;

    public Kartenverwaltung(Stack<Karte> aufnahmestapel, ArrayList<Karte> handkarten, ArrayList<Karte> ablagestapel) {
        this.aufnahmestapel = aufnahmestapel;
        this.handkarten = handkarten;
        this.ablagestapel = ablagestapel;
    }

    public Stack<Karte> getAufnahmestapel() {
        return aufnahmestapel;
    }

    public void setAufnahmestapel(Stack<Karte> aufnahmestapel) {
        this.aufnahmestapel = aufnahmestapel;
    }

    public ArrayList<Karte> getHandkarten() {
        return handkarten;
    }

    public void setHandkarten(ArrayList<Karte> handkarten) {
        this.handkarten = handkarten;
    }

    public ArrayList<Karte> getAblagestapel() {
        return ablagestapel;
    }

    public void setAblagestapel(ArrayList<Karte> ablagestapel) {
        this.ablagestapel = ablagestapel;
    }

    /**
     * Gibt alle Handkarten als formatierten String zurueck.
     *
     * @return String
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < this.getHandkarten().size(); i++) {
            if (this.getHandkarten().get(i) == null) {
                s.append(StringsammlungGUI.CONSOLE_RAUM_BELEGT);
            }
            s.append(StringsammlungGUI.CONSOLE_RAUM_LINKS).append(i).append(" ").append(this.getHandkarten().get(i)).append(StringsammlungGUI.CONSOLE_RAUM_RECHTS);
        }
        return s.toString();
    }

}