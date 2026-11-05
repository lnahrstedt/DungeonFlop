package control.model.fabrik;

import model.Ebene;
import model.RaeumeInEbene;
import model.raum.Haendlerraum;
import model.raum.Raum;
import model.raum.Schatzraum;
import resources.enums.EbenenLayoutSpeicher;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * In dieser Fabrik werden die vordefinierten Ebenen samt ihrem Layout aus dem Enum EbenenLayoutSpeicher eingelesen und als Liste zurueckgegeben.
 */
public class EbenenFabrik {

    /**
     * Liest die Ebenen sowie deren Layout aus dem EbenenLayoutSpeicher ein und speichert sie in einer Liste.
     *
     * @return ArrayList
     */
    public static ArrayList<Ebene> liesEbenenEin() {

        ArrayList<Ebene> ebenen = new ArrayList<>();
        ArrayList<EbenenLayoutSpeicher> ebenenVonSpielwelt = new ArrayList<>(Arrays.asList(EbenenLayoutSpeicher.values()));

        for (int i = 0; i < ebenenVonSpielwelt.size(); i++) {
            Raum startPosition = RaeumeInEbene.getStartPosition(i);
            Ebene ebene = new Ebene(ebenenVonSpielwelt.get(i).getName(), ebenenVonSpielwelt.get(i).getStory(), ebenenVonSpielwelt.get(i).getLayout(), startPosition);
            for (Raum raum : ebene.getLayout().keySet()) {
                if (raum instanceof Haendlerraum haendlerraum) {
                    haendlerraum.reset();
                } else if (raum instanceof Schatzraum schatzraum) {
                    schatzraum.reset();
                }
            }
            ebenen.add(ebene);
        }
        return ebenen;
    }

}
