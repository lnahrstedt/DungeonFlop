package model.talent;

import model.Spielfigur;
import model.Spielwelt;
import resources.interfaces.ZahlensammlungTalent;

import java.io.Serializable;

/**
 * Dieses Talent erhoeht den Angriff der Heldenfigur.
 */
public non-sealed class HeldAngriffTalent extends Talent implements Serializable {

    /**
     * Erstellt einer HeldAngriffTalent.
     *
     * @param name
     * @param beschreibung
     */
    public HeldAngriffTalent(String name, String beschreibung) {
        super(name, beschreibung);
    }

    /**
     * Erhoeht den Angriff der Heldenfigur um einen festen Wert.
     */
    @Override
    public void ausfuehren() {
        Spielfigur held = Spielwelt.getInstance().getProtagonist().getSpielfigur();
        held.setAngriffspunkte(held.getAngriffspunkte() + ZahlensammlungTalent.ANGRIFF_VERBESSERUNG_TALENT);
    }
}
