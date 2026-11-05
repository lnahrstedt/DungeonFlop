package model.effekt;

import model.AktionsSpeicher;
import resources.interfaces.StringsammlungEffekt;

import java.io.Serializable;

/**
 * Die Klasse MehrfachSchlagenEffekt.
 * Ein Effekt, der ein mehrfaches Schlagen ermoeglicht.
 */
public non-sealed class MehrfachSchlagenEffekt extends Effekt implements Serializable {

    private static final String name = StringsammlungEffekt.WIRBELWIND_NAME;
    private int wert;

    public MehrfachSchlagenEffekt(int wert) {
        super(name, String.format(StringsammlungEffekt.WIRBELWIND_BESCHREIBUNG, wert));
        this.wert = wert;
    }

    public int getWert() {
        return wert;
    }

    public void setWert(int wert) {
        this.wert = wert;
    }

    /**
     * Fuehrt den Effekt aus, indem der AktionsSpeicher veraendert wird.
     *
     * @param aktionsSpeicher Der AktionsSpeicher der Spielfigur
     */
    @Override
    public void ausfuehren(AktionsSpeicher aktionsSpeicher) {
        aktionsSpeicher.setAngriffe(aktionsSpeicher.getAngriffe() + this.getWert());
    }

}
