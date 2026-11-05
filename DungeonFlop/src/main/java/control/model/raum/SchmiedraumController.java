package control.model.raum;

import control.model.SpielController;
import model.HauptSpieler;
import model.Spielwelt;
import model.karte.Karte;
import model.karte.Minion;
import model.karte.Zauber;
import model.raum.Schmiedraum;
import resources.enums.MinionKarte;
import resources.enums.ZauberKarte;
import resources.exceptions.NichtGenugGoldException;
import resources.interfaces.Zahlensammlung;

/**
 * Diese Klasse stellt die Methode aufwerten fuer den Schmiedraum zur Verfuegung.
 */
public class SchmiedraumController {

    /**
     * Weist anhand des Kartentyps die richtige Aufwertmethode zu.
     *
     * @param schmiedRaum Der Schmiedraum
     * @param karte       Die upzugradene Karte
     * @throws NichtGenugGoldException
     */
    public static Karte aufwerten(Schmiedraum schmiedRaum, Karte karte) throws NichtGenugGoldException {
        HauptSpieler protagonist = Spielwelt.getInstance().getProtagonist();

        //TODO ungenutzte Variable?!
        int schmiedKosten = schmiedRaum.getKosten() + Zahlensammlung.SCHMIEDRAUM_PREIS_SKALIERUNG * SpielController.getSkalierung();

        if (schmiedRaum.getKosten() <= protagonist.getGold()) {
            protagonist.setGold(protagonist.getGold() - schmiedRaum.getKosten());
            return aufwertenOhneKosten(karte);
        }
        throw new NichtGenugGoldException(schmiedRaum.getKosten());
    }

    /**
     * Diese Methode gibt eine kopierte Instanz der uebergebenen Karte mit den verbesserten Werten zur Vorschau zurueck.
     *
     * @param karte
     * @return Karte
     */
    public static Karte aufwertenOhneKosten(Karte karte) {

        if (karte instanceof Minion minion) {
            return MinionKarte.getUpgradedMinionKarte(minion);
        } else {
            return ZauberKarte.getUpgradedZauberKarte((Zauber) karte);
        }
    }
}
