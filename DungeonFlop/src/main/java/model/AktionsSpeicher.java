package model;

import java.io.Serializable;

/**
 * Ermoeglicht eine uebersichtliche Abspeicherung der restlichen Bewegungen und Angriffen einer Spielfigur.
 */
public class AktionsSpeicher implements Serializable {

    private int bewegungen;
    private int angriffe;

    /**
     * Erzeugt einen Aktionsspeicher.
     *
     * @param bewegungen
     * @param angriffe
     */
    public AktionsSpeicher(int bewegungen, int angriffe) {
        this.bewegungen = bewegungen;
        this.angriffe = angriffe;
    }

    public int getBewegungen() {
        return bewegungen;
    }

    public void setBewegungen(int bewegungen) {
        this.bewegungen = bewegungen;
    }

    public int getAngriffe() {
        return angriffe;
    }

    public void setAngriffe(int angriffe) {
        this.angriffe = angriffe;
    }

}