package model;

import model.raum.Raum;
import resources.enums.RaeumeInEbenenSpeicher;
import resources.interfaces.KonstantenEbenenLayoutSpeicher;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Diese Klasse dient als Zwischenspeicher der Raeume, die sich innerhalb einer Ebene befinden.
 */
public class RaeumeInEbene implements Serializable {

    private static final ArrayList<ArrayList<Raum>> rauemeDerEbenen = leseRaeumeDerEbenenEin();

    /**
     * Diese Methode liest die Raeume einer Ebene aus dem RaeumeInEbenenSpeicher ein und gibt diese zurueck.
     *
     * @return ArrayList mit einer ArrayList an Raeumen.
     * <p>
     * Die Nummerierung der Indexe der "Mutter-Liste" gibt Aufschluss ueber die Nummer der Ebene.
     */
    private static ArrayList<ArrayList<Raum>> leseRaeumeDerEbenenEin() {

        return new ArrayList<>() {{
            add(RaeumeInEbenenSpeicher.RAEUME_IN_EBENE_0.getRaeumeInEbene());
            add(RaeumeInEbenenSpeicher.RAEUME_IN_EBENE_1.getRaeumeInEbene());
            add(RaeumeInEbenenSpeicher.RAEUME_IN_EBENE_2.getRaeumeInEbene());
            add(RaeumeInEbenenSpeicher.RAEUME_IN_EBENE_3.getRaeumeInEbene());
            add(RaeumeInEbenenSpeicher.RAEUME_IN_EBENE_4.getRaeumeInEbene());
            add(RaeumeInEbenenSpeicher.RAEUME_IN_EBENE_5.getRaeumeInEbene());
            add(RaeumeInEbenenSpeicher.RAEUME_IN_EBENE_6.getRaeumeInEbene());
        }};
    }

    /**
     * Diese Methode gibt einen spezifischen Raum aus einer spezifischen Ebene zurueck.
     *
     * @param ebene
     * @param raum
     * @return spezifischen Raum aus einer spezifischen Ebene
     */
    public static Raum getRaum(int ebene, int raum) {
        return rauemeDerEbenen.get(ebene).get(raum);
    }

    /**
     * Gibt den Startraum der spezifizierten Ebene zurueck.
     * Der Startraum ist immer der Raum 0.
     *
     * @param ebene
     * @return
     */
    public static Raum getStartPosition(int ebene) {
        return rauemeDerEbenen.get(ebene).get(KonstantenEbenenLayoutSpeicher.RAUM_0);
    }

    /**
     * Gibt den Leiterraum der spezifizierten Ebene zurueck.
     * Der Leiterraum ist immer der letzte Raum.
     *
     * @param ebene
     * @return Leiterraum
     */
    public static Raum getLeiterraum(int ebene) {
        int letzterRaum = leseRaeumeDerEbenenEin().get(ebene).size();
        return rauemeDerEbenen.get(ebene).get(letzterRaum - 1);
    }
}
