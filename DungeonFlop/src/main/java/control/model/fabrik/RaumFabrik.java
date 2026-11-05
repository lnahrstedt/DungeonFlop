package control.model.fabrik;

import model.raum.*;
import resources.interfaces.StringsammlungRaum;

/**
 * In dieser Fabrik werden auf Basis der vordefinierten Werte unterschiedliche Raeume generiert.
 */
public class RaumFabrik {

    private static final int STARTRAUMNUMMER = 0;
    private static int raumnummer = 0;

    /**
     * Generiert einen einfachen Raum
     *
     * @param name         Der Name des Raums
     * @param beschreibung Die Beschreibung des Raums
     * @return Raum
     */
    public static Raum generiereRaum(String name, String beschreibung) {
        return new Raum(name, beschreibung, raumnummer++);
    }

    /**
     * Generiert einen Haendlerraum
     *
     * @return Haendlerraum
     */
    public static Haendlerraum generiereHaendlerraum() {
        return new Haendlerraum(StringsammlungRaum.HAENDLERRAUM_NAME, StringsammlungRaum.HAENDLERRAUM_BESCHREIBUNG, raumnummer++);
    }

    /**
     * Generiert einen Gegnerraum
     *
     * @param schwierigkeitsgrad Der Schwierigkeitsgrad des Gegners
     * @return Gegnerraum
     */
    public static Gegnerraum generiereGegnerraum(float schwierigkeitsgrad) {
        return new Gegnerraum(StringsammlungRaum.GEGNERRAUM_NAME, StringsammlungRaum.GEGNERRAUM_BESCHREIBUNG, raumnummer++, schwierigkeitsgrad);
    }

    /**
     * Generiert einen Bossraum
     *
     * @param schwierigkeitsgrad Der Schwierigkeitsgrad des Bosses
     * @return Gegnerraum als Bossraum
     */
    public static Gegnerraum generiereBossraum(float schwierigkeitsgrad) {
        return new Gegnerraum(StringsammlungRaum.BOSSRAUM_NAME, StringsammlungRaum.BOSSRAUM_BESCHREIBUNG, raumnummer++, schwierigkeitsgrad);
    }

    /**
     * Generiert einen Schatzraum
     *
     * @return Schatzraum
     */
    public static Schatzraum generiereSchatzraum() {
        return new Schatzraum(StringsammlungRaum.SCHATZRAUM_NAME, StringsammlungRaum.SCHATZRAUM_BESCHREIBUNG, raumnummer++);
    }

    /**
     * Generiert einen Schmiedraum
     *
     * @param preis Der Preis fuer die Aufwertung eines Items.
     * @return SchmiedRaum
     */
    public static Schmiedraum generiereSchmiedRaum(int preis) {
        return new Schmiedraum(StringsammlungRaum.SCHMIED_NAME, StringsammlungRaum.SCHMIED_BESCHREIBUNG, raumnummer++, preis);
    }

    /**
     * Setzt die Raumnummer zurueck.
     */
    public static void resetRaumnummer() {
        RaumFabrik.raumnummer = STARTRAUMNUMMER;
    }


}
