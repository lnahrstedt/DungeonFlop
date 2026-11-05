package control.model.raum;

import model.HauptSpieler;
import model.raum.Schatzraum;

/**
 * Stellt die Methode karteNehmen fuer die Klasse Schatzraum zur Verfuegung.
 */
public class SchatzraumController {

    /**
     * Diese Methode legt die ausgewaehlte Karte in das Deck des Spielers und
     * entfernt die ausgewaehlte Karte aus dem Schatz.
     *
     * @param position
     * @param schatzraum
     * @param hauptSpieler
     */
    public static void karteNehmen(int position, Schatzraum schatzraum, HauptSpieler hauptSpieler) {
        hauptSpieler.getDeck().add(schatzraum.getSchatz().get(position));
        schatzraum.getSchatz().set(position, null);
    }
}
