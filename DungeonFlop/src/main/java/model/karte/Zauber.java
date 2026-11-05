package model.karte;

import model.Spielfigur;
import resources.enums.ZauberKarte;

import java.io.Serializable;

/**
 * Die Klasse Zauber implementiert Karte und kann gespielt werden.
 */
public class Zauber implements Karte, Serializable {

    private final String name;
    private final String beschreibung;
    private final int stufe;
    private int manakosten;
    private int schaden;

    public Zauber(String name, String beschreibung, int manakosten, int schaden, int stufe) {
        this.name = name;
        this.beschreibung = beschreibung;
        this.manakosten = manakosten;
        this.schaden = schaden;
        this.stufe = stufe;
    }

    /**
     * Erstellt einen Zauber aus der Vorlage im Enum.
     *
     * @param zauberVorlage Die Daten fuer die Zauber aus dem Enum.
     */
    public Zauber(ZauberKarte zauberVorlage) {
        this.name = zauberVorlage.getName();
        this.beschreibung = zauberVorlage.getBeschreibung();
        this.manakosten = zauberVorlage.getManakosten();
        this.schaden = zauberVorlage.getSchaden();
        this.stufe = zauberVorlage.getStufe();
    }

    /**
     * Erstellt einen Zauber mit identischen Werten.
     *
     * @param zuKopieren
     */
    public Zauber(Zauber zuKopieren) {
        this.name = zuKopieren.getName();
        this.beschreibung = zuKopieren.getBeschreibung();
        this.manakosten = zuKopieren.getManakosten();
        this.schaden = zuKopieren.getSchaden();
        this.stufe = zuKopieren.getStufe();
    }

    /**
     * Zieht der uebergebenden Figur die Lebenspunkte
     *
     * @param anzugreifendeFigur Die Figur, welche angegriffen werden soll
     */
    public void angreifen(Spielfigur anzugreifendeFigur) {
        anzugreifendeFigur.setLebenspunkte(anzugreifendeFigur.getLebenspunkte() - this.getSchaden());
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getBeschreibung() {
        return beschreibung;
    }

    @Override
    public int getManakosten() {
        return manakosten;
    }

    @Override
    public void setManakosten(int manakosten) {
        this.manakosten = manakosten;
    }

    public int getSchaden() {
        return schaden;
    }

    public void setSchaden(int schaden) {
        this.schaden = schaden;
    }

    @Override
    public int getStufe() {
        return stufe;
    }

    /**
     * Gibt den Namen, die Manakosten und den Schaden des Zaubers als String zurueck.
     *
     * @return Name Manakosten Schaden
     */
    @Override
    public String toString() {
        return this.getName() + " " +
                this.getManakosten() + " " +
                this.getSchaden();
    }

}
