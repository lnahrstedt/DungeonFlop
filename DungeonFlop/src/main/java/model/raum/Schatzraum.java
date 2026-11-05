package model.raum;

import model.karte.Karte;
import model.karte.Minion;
import model.karte.Zauber;
import resources.enums.MinionKarte;
import resources.enums.ZauberKarte;
import resources.interfaces.Zahlensammlung;
import utilities.MeinIO;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Die Klasse Schatzraum stellt einen Raum, der einen Schatz enthaelt, dar.
 */
public non-sealed class Schatzraum extends Raum implements Serializable {

    private ArrayList<Karte> schatz = new ArrayList<>();

    public Schatzraum(String name, String beschreibung, int raumnummer) {
        super(name, beschreibung, raumnummer);
        for (int i = 0; i < Zahlensammlung.SCHATZRAUM_KARTEN_ANZAHL; i++) {
            if (MeinIO.getRandomBoolean()) {
                schatz.add(new Minion(MinionKarte.getRandomMinionKarte()));
            } else {
                schatz.add(new Zauber(ZauberKarte.getRandomZauberKarte()));
            }
        }
    }


    public ArrayList<Karte> getSchatz() {
        return schatz;
    }

    /**
     * Setzt den Inhalt des Schatzraums zurueck.
     */
    public void reset() {
        schatz.removeAll(schatz);
        for (int i = 0; i < Zahlensammlung.SCHATZRAUM_KARTEN_ANZAHL; i++) {
            if (MeinIO.getRandomBoolean()) {
                schatz.add(new Minion(MinionKarte.getRandomMinionKarte()));
            } else {
                schatz.add(new Zauber(ZauberKarte.getRandomZauberKarte()));
            }
        }
    }

    public void setSchatz(ArrayList<Karte> schatz) {
        this.schatz = schatz;
    }

}
