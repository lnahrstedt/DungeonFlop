package model;

import model.effekt.Effekt;
import resources.enums.SpielfigurSammlung;
import resources.interfaces.StringsammlungGUI;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Diese Klasse repraesentiert eine Spielfigur.
 */
public class Spielfigur implements Serializable {

    private String name;
    private int lebenspunkte;
    private int angriffspunkte;
    private ArrayList<Effekt> effekte;

    /**
     * Erstellt eine Spielfigur.
     *
     * @param lebenspunkte
     * @param angriffspunkte
     * @param effekte
     */
    public Spielfigur(int lebenspunkte, int angriffspunkte, ArrayList<Effekt> effekte) {
        this.lebenspunkte = lebenspunkte;
        this.angriffspunkte = angriffspunkte;
        this.effekte = effekte;
    }

    /**
     * Erstellt eine Spielfigur anhand der Vorlage SpielfigurSammlung.
     *
     * @param spielfigurVorlage
     */
    public Spielfigur(SpielfigurSammlung spielfigurVorlage) {
        this.name = spielfigurVorlage.getName();
        this.lebenspunkte = spielfigurVorlage.getLebenspunkte();
        this.angriffspunkte = spielfigurVorlage.getAngriffspunkte();
        this.effekte = spielfigurVorlage.getEffekte();
    }

    public Spielfigur(Spielfigur spielfigur) {
        this.name = spielfigur.getName();
        this.lebenspunkte = spielfigur.getLebenspunkte();
        this.angriffspunkte = spielfigur.getAngriffspunkte();
        this.effekte = spielfigur.getEffekte();
    }

    /**
     * Zieht der Zielfigur Lebenspunkte entsprechend der Angriffspunkte ab.
     *
     * @param anzugreifendeFigur
     */
    public void angreifen(Spielfigur anzugreifendeFigur) {
        anzugreifendeFigur.setLebenspunkte(anzugreifendeFigur.getLebenspunkte() - this.getAngriffspunkte());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLebenspunkte() {
        return lebenspunkte;
    }

    public void setLebenspunkte(int lebenspunkte) {
        this.lebenspunkte = lebenspunkte;
    }

    public int getAngriffspunkte() {
        return angriffspunkte;
    }

    public void setAngriffspunkte(int angriffspunkte) {
        this.angriffspunkte = angriffspunkte;
    }

    public ArrayList<Effekt> getEffekte() {
        return effekte;
    }

    public void setEffekte(ArrayList<Effekt> effekte) {
        this.effekte = effekte;
    }

    /**
     * Gibt die Angriffspunkte und die Lebenspunkte der Spielfigur als String zurueck.
     *
     * @return
     */
    @Override
    public String toString() {
        return this.getAngriffspunkte() + StringsammlungGUI.CONSOLE_PLATZHALTER_O + this.getLebenspunkte();
    }

}