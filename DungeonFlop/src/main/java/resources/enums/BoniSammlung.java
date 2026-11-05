package resources.enums;

import model.effekt.Effekt;
import model.effekt.MehrfachSchlagenEffekt;
import model.effekt.WeiterGehenEffekt;
import resources.interfaces.StringsammlungBoni;
import resources.interfaces.Zahlensammlung;
import utilities.MeinIO;

/**
 * Diese Enumeration dient der Vordefinition der Boni.
 */
public enum BoniSammlung {

    SONNENSTEIN(new MehrfachSchlagenEffekt(Zahlensammlung.SONNENSTEIN_MEHRFACHSCHLAGENEFFEKT_WERT), Zahlensammlung.SONNENSTEIN_SELTENHEIT, Zahlensammlung.SONNENSTEIN_LEVEL, StringsammlungBoni.SONNENSTEIN),
    WASSERAMULETT(new WeiterGehenEffekt(Zahlensammlung.WASSERAMULETT_WEITERGEHENEFFEKT_WERT), Zahlensammlung.WASSERAMULETT_SELTENHEIT, Zahlensammlung.WASSERAMULETT_LEVEL, StringsammlungBoni.WASSERAMULETT);
    private final Effekt effekt;
    private final int seltenheit;
    private final int level;
    private final String name;

    BoniSammlung(Effekt effekt, int seltenheit, int level, String name) {
        this.effekt = effekt;
        this.seltenheit = seltenheit;
        this.level = level;
        this.name = name;
    }

    /**
     * Gibt eine zufaellige Bonivorlage aus.
     *
     * @return BoniSammlung
     */
    public static BoniSammlung getRandomBoni() {
        return BoniSammlung.values()[MeinIO.getRandomInt(BoniSammlung.values().length)];
    }

    public Effekt getEffekt() {
        return effekt;
    }

    public int getSeltenheit() {
        return seltenheit;
    }

    public int getLevel() {
        return level;
    }

    public String getName() {
        return name;
    }
}
