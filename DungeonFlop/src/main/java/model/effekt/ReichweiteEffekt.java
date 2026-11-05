package model.effekt;

import model.AktionsSpeicher;
import resources.interfaces.StringsammlungEffekt;

import java.io.Serializable;

/**
 * Ermoeglicht Spielfiguren ueber mehr als ein Feld hinweg anzugreifen.
 */
public non-sealed class ReichweiteEffekt extends Effekt implements Serializable {
    private int wert;

    public ReichweiteEffekt(int wert) {
        super(StringsammlungEffekt.REICHWEITE_NAME, String.format(StringsammlungEffekt.REICHWEITE_BESCHREIBUNG, wert));
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

    }

}
