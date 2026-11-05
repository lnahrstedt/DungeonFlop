package model;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Eine Runde beinhaltet den aktuellen Spieler.
 * Dessen Spielfiguren werden mit dem AktionsSpeicher verbunden.
 * Zusaetzlich legt eine boolesche Variable fest, ob die Runde aktuell laeuft.
 */
public class Runde implements Serializable {

    private boolean rundeLaeuft = true;
    private Spieler spieler;
    private HashMap<Spielfigur, AktionsSpeicher> aktionen;
    private int aktuelleMana;

    /**
     * Erzeugt eine Runde.
     *
     * @param spieler
     * @param aktionen
     * @param aktuelleMana
     */
    public Runde(Spieler spieler, HashMap<Spielfigur, AktionsSpeicher> aktionen, int aktuelleMana) {
        this.spieler = spieler;
        this.aktionen = aktionen;
        this.aktuelleMana = aktuelleMana;
    }

    public boolean istRundeAmLaufen() {
        return rundeLaeuft;
    }

    public void setRundeLaeuft(boolean rundeLaeuft) {
        this.rundeLaeuft = rundeLaeuft;
    }

    public Spieler getSpieler() {
        return spieler;
    }

    public void setSpieler(HauptSpieler hauptSpieler) {
        this.spieler = hauptSpieler;
    }

    public HashMap<Spielfigur, AktionsSpeicher> getAktionen() {
        return aktionen;
    }

    public void setAktionen(HashMap<Spielfigur, AktionsSpeicher> aktionen) {
        this.aktionen = aktionen;
    }

    public void setAktionsSpeicher(Spielfigur spielfigur, AktionsSpeicher aktionsSpeicher) {
        this.aktionen.get(spielfigur).setBewegungen(aktionsSpeicher.getBewegungen());
        this.aktionen.get(spielfigur).setAngriffe(aktionsSpeicher.getAngriffe());
    }

    public AktionsSpeicher getAktionsSpeicher(Spielfigur spielfigur) {
        return this.getAktionen().get(spielfigur);
    }

    public int getAktuelleMana() {
        return aktuelleMana;
    }

    public void setAktuelleMana(int aktuelleMana) {
        this.aktuelleMana = aktuelleMana;
    }

}
