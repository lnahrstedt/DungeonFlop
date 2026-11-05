package model.talent;

import model.Spielwelt;
import resources.interfaces.ZahlensammlungTalent;

import java.io.Serializable;

/**
 * Dieses Talent erhoeht den prozentualen Goldgewinn nach einem gewonnenen Kampf.
 */
public non-sealed class GoldTalent extends Talent implements Serializable {

    /**
     * Erstellt ein GoldTalent.
     *
     * @param name
     * @param beschreibung
     */
    public GoldTalent(String name, String beschreibung) {
        super(name, beschreibung);
    }

    /**
     * Erhoeht den Goldgewinn.
     */
    @Override
    public void ausfuehren() {
        Spielwelt.getInstance().setGoldFaktor(Spielwelt.getInstance().getGoldFaktor() + Spielwelt.getInstance().getGoldFaktor() / ZahlensammlungTalent.GOLD_TALENT_WERT);
    }
}
