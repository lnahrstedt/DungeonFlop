package model.karte;

/**
 * Garantiert, dass Minion und Zauber das Verhalten von Karte implementieren.
 */
public interface Karte {

    String getName();

    String getBeschreibung();

    int getStufe();

    int getManakosten();

    void setManakosten(int manakosten);
}