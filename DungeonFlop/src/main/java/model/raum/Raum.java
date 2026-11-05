package model.raum;

import java.io.Serializable;

/**
 * Diese Klasse repraesentiert einen leeren Raum in der Ebene.
 */
public sealed class Raum implements Serializable permits Gegnerraum, Haendlerraum, Schatzraum, Schmiedraum {

    private final String name;
    private final String beschreibung;
    private final int raumnummer;

    public Raum(String name, String beschreibung, int raumnummer) {
        this.name = name;
        this.beschreibung = beschreibung;
        this.raumnummer = raumnummer;
    }

    /**
     * Gibt die Raumnummer, den Namen und die Beschreibung des Raums als String zurueck.
     *
     * @return Nummer. Name; Beschreibung
     */
    @Override
    public String toString() {
        return String.format("%d. %s: %s", this.getRaumnummer(), this.getName(), this.getBeschreibung());
    }

    public String getName() {
        return name;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public int getRaumnummer() {
        return raumnummer;
    }

    /**
     * Ueberschreibt die equals Methode, um Raeume untereinander vergleichen zu koennen.
     *
     * @param obj Das Objekt welches verglichen werden soll
     * @return boolean
     */
    @Override
    public boolean equals(Object obj) {

        if (obj == null) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Raum raum)) {
            return false;
        }

        return this.name.equals(raum.getName())
                && this.beschreibung.equals(raum.getBeschreibung())
                && this.raumnummer == raum.getRaumnummer();

    }

}
