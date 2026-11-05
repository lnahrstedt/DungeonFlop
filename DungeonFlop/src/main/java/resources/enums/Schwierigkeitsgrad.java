package resources.enums;

import resources.interfaces.StringsammlungSchwierigkeitsgrad;
import resources.interfaces.Zahlensammlung;

/**
 * Legt die Werte fuer den zu Beginn einstellbaren Schwierigkeitsgrad fest.
 */
public enum Schwierigkeitsgrad {

    LEICHT(Zahlensammlung.SCHWIERIKGEITSFAKTOR_LEICHT, StringsammlungSchwierigkeitsgrad.LEICHT),
    MITTEL(Zahlensammlung.SCHWIERIKGEITSFAKTOR_MITTEL, StringsammlungSchwierigkeitsgrad.MITTEL),
    SCHWER(Zahlensammlung.SCHWIERIKGEITSFAKTOR_SCHWER, StringsammlungSchwierigkeitsgrad.SCHWER),
    HARDCORE(Zahlensammlung.SCHWIERIKGEITSFAKTOR_HARDCORE, StringsammlungSchwierigkeitsgrad.HARDCORE);

    private final int schwierigkeitsgrad;
    private final String bezeichnung;

    Schwierigkeitsgrad(int schwierigkeitsgrad, String bezeichnung) {
        this.schwierigkeitsgrad = schwierigkeitsgrad;
        this.bezeichnung = bezeichnung;
    }

    public int getSchwierigkeitsgrad() {
        return schwierigkeitsgrad;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }
}
