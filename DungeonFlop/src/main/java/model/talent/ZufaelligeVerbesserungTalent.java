package model.talent;

import control.model.raum.SchmiedraumController;
import model.HauptSpieler;
import model.Spielwelt;
import model.karte.Karte;
import resources.interfaces.Zahlensammlung;
import resources.interfaces.ZahlensammlungTalent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * Dieses Talent verbessert zufaellige Karten.
 */
public non-sealed class ZufaelligeVerbesserungTalent extends Talent implements Serializable {

    /**
     * Erstellt ein ZufaelligeVerbesserungTalent.
     *
     * @param name
     * @param beschreibung
     */
    public ZufaelligeVerbesserungTalent(String name, String beschreibung) {
        super(name, beschreibung);
    }

    /**
     * Verbessert eine feste Anzahl von zufaellig ausgewaehlten Karten.
     */
    @Override
    public void ausfuehren() {
        HauptSpieler protagonist = Spielwelt.getInstance().getProtagonist();
        ArrayList<Karte> spielerDeck = protagonist.getDeck();
        ArrayList<Karte> kartenUnterMaxStufe = new ArrayList<>();

        for (Karte karte : spielerDeck) {
            if (karte.getStufe() != Zahlensammlung.HOECHSTE_KARTEN_STUFE) {
                kartenUnterMaxStufe.add(karte);
            }
        }

        for (int i = 0; i < ZahlensammlungTalent.ZUFAELLIGE_KARTEN_VERBESSERUNG_TALENT_ANZAHL; i++) {
            if (kartenUnterMaxStufe.size() == 0) {
                break;
            }

            Random random = new Random();
            int auserkoreneKarteIndex = random.nextInt(kartenUnterMaxStufe.size());

            protagonist.getDeck().add(SchmiedraumController.aufwertenOhneKosten(kartenUnterMaxStufe.get(auserkoreneKarteIndex)));
            protagonist.getDeck().remove(kartenUnterMaxStufe.get(auserkoreneKarteIndex));
            kartenUnterMaxStufe.remove(auserkoreneKarteIndex);
        }
    }
}
