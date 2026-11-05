package model.karte;

import model.Spielfigur;
import model.effekt.Effekt;
import resources.enums.MinionKarte;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Diese Klasse vereint die Spielfigur mit der Implementation des Karteninterfaces.
 */
public class Minion extends Spielfigur implements Karte, Serializable {
    private final String name;
    private final String beschreibung;
    private final int stufe;
    private int manakosten;

    /**
     * Erstellt einen Minion anhand der einzelnen Parameter.
     *
     * @param lebenspunkte   Die Lebenspunkte des Minions
     * @param angriffspunkte Die Angriffspunkte des Minions
     * @param effekte        Die Effekte des Minions
     * @param name           Der Name des Minions
     * @param beschreibung   Die Beschreibung des Minions
     * @param manakosten     Die Manakosten fuer den Minion
     */
    public Minion(int lebenspunkte, int angriffspunkte, ArrayList<Effekt> effekte, String name, String beschreibung, int manakosten, int stufe) {
        super(lebenspunkte, angriffspunkte, effekte);
        this.name = name;
        this.beschreibung = beschreibung;
        this.manakosten = manakosten;
        this.stufe = stufe;
    }

    /**
     * Pflegt die Werte aus dem Enum ein und erstellt einen Minion.
     *
     * @param minionVorlage Die Daten des Minions aus dem Enum
     */
    public Minion(MinionKarte minionVorlage) {
        super(minionVorlage.getLebenspunkte(), minionVorlage.getAngriffspunkte(), minionVorlage.getEffekte());
        this.name = minionVorlage.getName();
        this.beschreibung = minionVorlage.getBeschreibung();
        this.manakosten = minionVorlage.getManakosten();
        this.stufe = minionVorlage.getStufe();
    }

    /**
     * Erstellt einen neuen Minion mit identischen Werten.
     *
     * @param zuKopieren
     */
    public Minion(Minion zuKopieren) {
        super(zuKopieren.getLebenspunkte(), zuKopieren.getAngriffspunkte(), zuKopieren.getEffekte());
        this.name = zuKopieren.getName();
        this.beschreibung = zuKopieren.getBeschreibung();
        this.manakosten = zuKopieren.getManakosten();
        this.stufe = zuKopieren.getStufe();
    }

    public String getName() {
        return name;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public int getManakosten() {
        return manakosten;
    }

    public void setManakosten(int manakosten) {
        this.manakosten = manakosten;
    }

    @Override
    public int getStufe() {
        return stufe;
    }

    /**
     * Gibt den Namen und die Manakosten des Minion als String zurueck.
     *
     * @return NameManakosten
     */
    @Override
    public String toString() {
        return this.getName() +
                this.getManakosten();
    }
}
