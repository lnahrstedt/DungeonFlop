package resources.interfaces;

/**
 * Dieses Interface beinhaltet alle wichtigen Werte fuer die Zauber.
 */
public interface ZahlensammlungZauber {
    //Feuerspeer
    int FEUERSPEER_MANAKOSTEN = 1;
    int FEUERSPEER_SCHADEN_ST1 = 2;
    int FEUERSPEER_SCHADEN_ST2 = 3;
    int FEUERSPEER_SCHADEN_ST3 = 4;

    //Feuerball
    int FEUERBALL_MANAKOSTEN = 2;
    int FEUERBALL_SCHADEN_ST1 = 4;
    int FEUERBALL_SCHADEN_ST2 = 6;
    int FEUERBALL_SCHADEN_ST3 = 8;

    //Sand
    int SAND_MANAKOSTEN = 1;
    int SAND_SCHADEN_ST1 = 1;
    int SAND_SCHADEN_ST2 = 2;
    int SAND_SCHADEN_ST3 = 3;

    //Verbaler Angriff
    int VERBALER_ANGRIFF_MANAKOSTEN = 0;
    int VERBALER_ANGRIFF_SCHADEN_ST1 = 0;
    int VERBALER_ANGRIFF_SCHADEN_ST2 = 1;
    int VERBALER_ANGRIFF_SCHADEN_ST3 = 2;
}
