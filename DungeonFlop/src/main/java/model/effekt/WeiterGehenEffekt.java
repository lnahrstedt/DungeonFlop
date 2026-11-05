package model.effekt;

import model.AktionsSpeicher;
import resources.interfaces.StringsammlungEffekt;

import java.io.Serializable;

/**
 * Die Klasse WeiterGehenEffekt.
 * Ein Effekt, welcher der Spielfigur ermoeglicht, weiter zu gehen.
 */
public non-sealed class WeiterGehenEffekt extends Effekt implements Serializable {

    private static final String name = StringsammlungEffekt.MARATHON_NAME;
    private int wert;

    public WeiterGehenEffekt(int wert) {
        super(name, String.format(StringsammlungEffekt.MARATHON_BESCHREIBUNG, wert));
        this.wert = wert;
    }

    public int getWert() {
        return wert;
    }

    public void setWert(int wert) {
        this.wert = wert;
    }

    /**
     * Fuehrt den Effekt aus, indem der AktionsSpeicher veraendert wird
     *
     * @param aktionsSpeicher Der AktionsSpeicher der Spielfigur
     */
    public void ausfuehren(AktionsSpeicher aktionsSpeicher) {
        aktionsSpeicher.setBewegungen(aktionsSpeicher.getBewegungen() + this.getWert());
    }

}
