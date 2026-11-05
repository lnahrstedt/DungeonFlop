package model.raum;

import java.io.Serializable;

/**
 * Diese Klasse stellt einen Raum mit einem Schmied dar.
 * Der Spieler kann in diesem Raum eine Karte verbessern.
 */
public non-sealed class Schmiedraum extends Raum implements Serializable {

    private int kosten = 0;

    public Schmiedraum(String name, String beschreibung, int raumnummer, int preis) {
        super(name, beschreibung, raumnummer);
        this.kosten = preis;
    }

    public int getKosten() {
        return kosten;
    }

    public void setKosten(int kosten) {
        this.kosten = kosten;
    }

}
