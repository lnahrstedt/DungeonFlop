package model.talent;

import model.Spielwelt;
import resources.interfaces.ZahlensammlungTalent;

import java.io.Serializable;

/**
 * Dieses Talent erhoeht den prozentualen Erfahrungsgewinn nach einem gewonnenen Kampf.
 */
public non-sealed class ErfahrungsTalent extends Talent implements Serializable {

    /**
     * Erzeugt ein ErfahrungsTalent.
     *
     * @param name
     * @param beschreibung
     */
    public ErfahrungsTalent(String name, String beschreibung) {
        super(name, beschreibung);
    }

    /**
     * Erhoeht den Erfahrungsgewinn.
     */
    @Override
    public void ausfuehren() {
        Spielwelt.getInstance().setErfahrungsFaktor(Spielwelt.getInstance().getErfahrungsFaktor() + Spielwelt.getInstance().getErfahrungsFaktor() / ZahlensammlungTalent.ERFAHRUNGS_TALENT_WERT);
    }
}
