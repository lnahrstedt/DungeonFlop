package model;

import model.karte.Karte;
import resources.enums.Gegner;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Der Spieler hat alle fuer den Kampf benoetigten Eigenschaften.
 * Er ist die Schnittstelle fuer viele Klassen.
 * Spieler beinhaltet unter anderem ein Deck, ein Inventar und die Heldenspielfigur.
 */
public sealed class Spieler implements Serializable permits HauptSpieler {

    private final String name;
    private ArrayList<Karte> deck;
    private Spielfigur spielfigur;
    private int level;
    private int mana;
    private int kartenProRunde;

    /**
     * Erstellt einen Spieler
     *
     * @param name
     * @param deck
     * @param spielfigur
     * @param level
     * @param mana
     * @param kartenProRunde
     */
    public Spieler(String name, ArrayList<Karte> deck, Spielfigur spielfigur, int level, int mana, int kartenProRunde) {
        this.name = name;
        this.deck = deck;
        this.spielfigur = spielfigur;
        this.level = level;
        this.mana = mana;
        this.kartenProRunde = kartenProRunde;
    }

    /**
     * Erstellt einen Spieler anhand einer Gegner-Vorlage.
     *
     * @param gegnerVorlage
     */
    public Spieler(Gegner gegnerVorlage) {
        this.name = gegnerVorlage.getName();
        this.deck = new ArrayList<>(gegnerVorlage.getDeck());
        this.spielfigur = gegnerVorlage.getSpielfigur();
        this.level = gegnerVorlage.getLevel();
        this.mana = gegnerVorlage.getMana();
        this.kartenProRunde = gegnerVorlage.getKartenProRunde();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Karte> getDeck() {
        return deck;
    }

    public void setDeck(ArrayList<Karte> deck) {
        this.deck = deck;
    }

    public Spielfigur getSpielfigur() {
        return spielfigur;
    }

    public void setSpielfigur(Spielfigur spielfigur) {
        this.spielfigur = spielfigur;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getKartenProRunde() {
        return kartenProRunde;
    }

    public void setKartenProRunde(int kartenProRunde) {
        this.kartenProRunde = kartenProRunde;
    }


}
