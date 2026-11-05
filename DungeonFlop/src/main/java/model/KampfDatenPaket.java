package model;

import java.io.Serializable;

/**
 * Die Instanzen dieser Klasse beinhalten relevante Daten fuer die GegnerraumView.
 * Sie werden als Datenpakete zum Versenden der Information genutzt.
 * Das Kampfdatenpaket beinhaltet den Kampf, den Spieler und die Runde.
 */
public class KampfDatenPaket implements Serializable {
    private Kampf kampf;
    private Runde runde;
    private Spieler spieler;

    /**
     * Erzeugt ein Kampfdatenpaket.
     *
     * @param kampf
     * @param runde
     * @param spieler
     */
    public KampfDatenPaket(Kampf kampf, Runde runde, Spieler spieler) {
        this.kampf = kampf;
        this.runde = runde;
        this.spieler = spieler;
    }

    public Kampf getKampf() {
        return kampf;
    }

    public void setKampf(Kampf kampf) {
        this.kampf = kampf;
    }

    public Runde getRunde() {
        return runde;
    }

    public void setRunde(Runde runde) {
        this.runde = runde;
    }

    public Spieler getSpieler() {
        return spieler;
    }

    public void setSpieler(Spieler spieler) {
        this.spieler = spieler;
    }

}
