package resources.enums;

import model.effekt.Effekt;
import model.effekt.MehrfachSchlagenEffekt;
import model.effekt.ReichweiteEffekt;
import model.effekt.WeiterGehenEffekt;
import resources.interfaces.StringsammlungWaffe;
import resources.interfaces.ZahlensammlungWaffe;
import utilities.MeinIO;

/**
 * Diese Enumeration beinhaltet die vordefinierten Waffen.
 */
public enum WaffenSammlung {
    SCHWERT(ZahlensammlungWaffe.SCHWERT_SCHADEN, ZahlensammlungWaffe.SCHWERT_SELTENHEIT, new MehrfachSchlagenEffekt(ZahlensammlungWaffe.SCHWERT_EFFEKT_WERT), StringsammlungWaffe.SCHWERT),
    AXT(ZahlensammlungWaffe.AXT_SCHADEN, ZahlensammlungWaffe.AXT_SELTENHEIT, new WeiterGehenEffekt(ZahlensammlungWaffe.AXT_EFFEKT_WERT), StringsammlungWaffe.AXT),
    BOGEN(ZahlensammlungWaffe.BOGEN_SCHADEN, ZahlensammlungWaffe.BOGEN_SELTENHEIT, new ReichweiteEffekt(ZahlensammlungWaffe.BOGEN_EFFEKT_WERT), StringsammlungWaffe.BOGEN);

    private final int schaden;
    private final int seltenheitsgrad;
    private final Effekt effekt;
    private final String name;

    WaffenSammlung(int schaden, int seltenheitsgrad, Effekt effekt, String name) {
        this.schaden = schaden;
        this.seltenheitsgrad = seltenheitsgrad;
        this.effekt = effekt;
        this.name = name;
    }

    /**
     * Gibt eine zufaellige Waffenvorlage zurueck.
     *
     * @return Waffe
     */
    public static WaffenSammlung getRandomWaffe() {
        return WaffenSammlung.values()[MeinIO.getRandomInt(WaffenSammlung.values().length)];
    }

    public int getSchaden() {
        return schaden;
    }

    public int getSeltenheitsgrad() {
        return seltenheitsgrad;
    }

    public Effekt getEffekt() {
        return effekt;
    }

    public String getName() {
        return name;
    }
}