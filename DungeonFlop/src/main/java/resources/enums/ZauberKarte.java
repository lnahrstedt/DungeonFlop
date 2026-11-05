package resources.enums;

import model.karte.Zauber;
import resources.interfaces.StringsammlungZauber;
import resources.interfaces.Zahlensammlung;
import resources.interfaces.ZahlensammlungZauber;
import utilities.MeinIO;

/**
 * Dieses Enum beinhaltet die vordefinierten Zauber.
 */
public enum ZauberKarte {
    FEUERSPEER_ST1(StringsammlungZauber.FEUERSPEER_NAME, StringsammlungZauber.FEUERSPEER_BESCHREIBUNG, ZahlensammlungZauber.FEUERSPEER_MANAKOSTEN, ZahlensammlungZauber.FEUERSPEER_SCHADEN_ST1, Zahlensammlung.KARTE_STUFE_1),
    FEUERSPEER_ST2(StringsammlungZauber.FEUERSPEER_NAME, StringsammlungZauber.FEUERSPEER_BESCHREIBUNG, ZahlensammlungZauber.FEUERSPEER_MANAKOSTEN, ZahlensammlungZauber.FEUERSPEER_SCHADEN_ST2, Zahlensammlung.KARTE_STUFE_2),
    FEUERSPEER_ST3(StringsammlungZauber.FEUERSPEER_NAME, StringsammlungZauber.FEUERSPEER_BESCHREIBUNG, ZahlensammlungZauber.FEUERSPEER_MANAKOSTEN, ZahlensammlungZauber.FEUERSPEER_SCHADEN_ST3, Zahlensammlung.KARTE_STUFE_3),

    FEUERBALL_ST1(StringsammlungZauber.FEUERBALL_NAME, StringsammlungZauber.FEUERBALL_BESCHREIBUNG, ZahlensammlungZauber.FEUERBALL_MANAKOSTEN, ZahlensammlungZauber.FEUERBALL_SCHADEN_ST1, Zahlensammlung.KARTE_STUFE_1),
    FEUERBALL_ST2(StringsammlungZauber.FEUERBALL_NAME, StringsammlungZauber.FEUERBALL_BESCHREIBUNG, ZahlensammlungZauber.FEUERBALL_MANAKOSTEN, ZahlensammlungZauber.FEUERBALL_SCHADEN_ST2, Zahlensammlung.KARTE_STUFE_2),
    FEUERBALL_ST3(StringsammlungZauber.FEUERBALL_NAME, StringsammlungZauber.FEUERBALL_BESCHREIBUNG, ZahlensammlungZauber.FEUERBALL_MANAKOSTEN, ZahlensammlungZauber.FEUERBALL_SCHADEN_ST3, Zahlensammlung.KARTE_STUFE_3),

    SAND_ST1(StringsammlungZauber.SAND_NAME, StringsammlungZauber.SAND_BESCHREIBUNG, ZahlensammlungZauber.SAND_MANAKOSTEN, ZahlensammlungZauber.SAND_SCHADEN_ST1, Zahlensammlung.KARTE_STUFE_1),
    SAND_ST2(StringsammlungZauber.SAND_NAME, StringsammlungZauber.SAND_BESCHREIBUNG, ZahlensammlungZauber.SAND_MANAKOSTEN, ZahlensammlungZauber.SAND_SCHADEN_ST2, Zahlensammlung.KARTE_STUFE_2),
    SAND_ST3(StringsammlungZauber.SAND_NAME, StringsammlungZauber.SAND_BESCHREIBUNG, ZahlensammlungZauber.SAND_MANAKOSTEN, ZahlensammlungZauber.SAND_SCHADEN_ST3, Zahlensammlung.KARTE_STUFE_3),

    VERBALER_ANGRIFF_ST1(StringsammlungZauber.VERBALER_ANGRIFF_NAME, StringsammlungZauber.VERBALER_ANGRIFF_BESCHREIBUNG, ZahlensammlungZauber.VERBALER_ANGRIFF_MANAKOSTEN, ZahlensammlungZauber.VERBALER_ANGRIFF_SCHADEN_ST1, Zahlensammlung.KARTE_STUFE_1),
    VERBALER_ANGRIFF_ST2(StringsammlungZauber.VERBALER_ANGRIFF_NAME, StringsammlungZauber.VERBALER_ANGRIFF_BESCHREIBUNG, ZahlensammlungZauber.VERBALER_ANGRIFF_MANAKOSTEN, ZahlensammlungZauber.VERBALER_ANGRIFF_SCHADEN_ST2, Zahlensammlung.KARTE_STUFE_2),
    VERBALER_ANGRIFF_ST3(StringsammlungZauber.VERBALER_ANGRIFF_NAME, StringsammlungZauber.VERBALER_ANGRIFF_BESCHREIBUNG, ZahlensammlungZauber.VERBALER_ANGRIFF_MANAKOSTEN, ZahlensammlungZauber.VERBALER_ANGRIFF_SCHADEN_ST3, Zahlensammlung.KARTE_STUFE_3);

    private final String name;
    private final String beschreibung;
    private final int manakosten;
    private final int schaden;
    private final int stufe;

    ZauberKarte(String name, String beschreibung, int manakosten, int schaden, int stufe) {
        this.name = name;
        this.beschreibung = beschreibung;
        this.manakosten = manakosten;
        this.schaden = schaden;
        this.stufe = stufe;
    }

    /**
     * Gibt zufaellige Zauberkarte zurueck.
     *
     * @return zufaellige Zauberkarte
     */
    public static ZauberKarte getRandomZauberKarte() {
        return ZauberKarte.values()[MeinIO.getRandomInt(ZauberKarte.values().length)];
    }

    /**
     * Verbessert einen Zauber.
     * Ist die Verbesserung nicht moeglich, wird der selbe Zauber zureckgegeben.
     *
     * @param
     * @return
     */
    public static Zauber getUpgradedZauberKarte(Zauber zuVerbessern) {
        ZauberKarte[] alleZauberKarten = ZauberKarte.values();
        for (int i = 0; i < alleZauberKarten.length; i++) {
            if (alleZauberKarten[i].getName().equals(zuVerbessern.getName()) && alleZauberKarten[i].getStufe() == zuVerbessern.getStufe() && alleZauberKarten[i].getStufe() < Zahlensammlung.HOECHSTE_KARTEN_STUFE) {
                return new Zauber(alleZauberKarten[i + 1]);
            }
        }
        return zuVerbessern;
    }

    public String getName() {
        return name;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public int getManakosten() {
        return manakosten;
    }

    public int getSchaden() {
        return schaden;
    }

    public int getStufe() {
        return stufe;
    }
}
