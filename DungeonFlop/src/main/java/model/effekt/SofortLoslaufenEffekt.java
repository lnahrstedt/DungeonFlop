package model.effekt;

import model.AktionsSpeicher;
import resources.interfaces.StringsammlungEffekt;

import java.io.Serializable;

public non-sealed class SofortLoslaufenEffekt extends Effekt implements Serializable {

    public SofortLoslaufenEffekt() {
        super(StringsammlungEffekt.HELLWACH_NAME, StringsammlungEffekt.HELLWACH_BESCHREIBUNG);
    }

    @Override
    public void ausfuehren(AktionsSpeicher aktionsSpeicher) {

    }
}
