package model.talent;

import model.Spielfigur;
import model.Spielwelt;
import resources.interfaces.ZahlensammlungTalent;

import java.io.Serializable;

/**
 * Dieses Talent erhoeht die Lebenspunkte der Heldenfigur.
 */
public non-sealed class HeldLebenTalent extends Talent implements Serializable {

    /**
     * Erstellt ein HeldLebenTalent.
     *
     * @param name
     * @param beschreibung
     */
    public HeldLebenTalent(String name, String beschreibung) {
        super(name, beschreibung);
    }

    /**
     * Erhoeht die Lebenspunkte der Heldenfigur um einen festen Wert.
     */
    @Override
    public void ausfuehren() {
        Spielfigur held = Spielwelt.getInstance().getProtagonist().getSpielfigur();
        held.setLebenspunkte(held.getLebenspunkte() + ZahlensammlungTalent.LEBEN_VERBESSERUNG_TALENT);
    }
}
