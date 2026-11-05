package control.model.raum;

import control.model.SpielController;
import model.HauptSpieler;
import model.Spielwelt;
import model.raum.Haendlerraum;
import resources.exceptions.NichtGenugGoldException;
import resources.interfaces.Zahlensammlung;

/**
 * Diese Klasse stellt Methoden fuer die Klasse Haendlerraum zur Verfuegung.
 */
public class HaendlerraumController {


    /**
     * Schnittstelle zwischen den verschiedenen Kaufkategorien.
     *
     * @param kategorie
     * @param position
     * @param haendlerraum
     * @throws NichtGenugGoldException - Wenn der Spieler nicht genug Gold hat
     */
    public static void kaufe(int kategorie, int position, Haendlerraum haendlerraum) throws NichtGenugGoldException {
        switch (kategorie) {
            case 0 -> kaufeKarte(position, haendlerraum);
            case 1 -> kaufeBoni(position, haendlerraum);
            case 2 -> kaufeWaffe(position, haendlerraum);
        }
    }

    /**
     * Kauft die an der Position angebende Karte im Angebot.
     * Gekaufte Karte wird zu dem Deck des Spielers hinzugefuegt.
     *
     * @param position     der Karte im AngebotsArray
     * @param haendlerraum
     * @throws NichtGenugGoldException, falls der Spieler nicht genug Gold hat
     */
    private static void kaufeKarte(int position, Haendlerraum haendlerraum) throws NichtGenugGoldException {
        int kartenpreis = SpielController.getSkalierung() * Zahlensammlung.HAENDLERRAUM_PREIS_SKALIERUNG + Zahlensammlung.HAENDLERRAUM_PREIS_FAKTOR_KARTE;
        HauptSpieler protagonist = Spielwelt.getInstance().getProtagonist();

        if (protagonist.getGold() >= kartenpreis) {
            protagonist.getDeck().add(haendlerraum.getKartenangebot().get(position));
            haendlerraum.getKartenangebot().set(position, null);
            protagonist.setGold(protagonist.getGold() - kartenpreis);
        } else {
            throw new NichtGenugGoldException(kartenpreis);
        }
    }

    /**
     * Kauft den an der Position angebenden Boni im Angebot.
     * Gekaufter Boni wird zu dem Artefaktinventar hinzugefuegt.
     *
     * @param position     des Bonis im AngebotsArray
     * @param haendlerraum
     * @throws NichtGenugGoldException, falls der Spieler nicht genug Gold hat
     */
    private static void kaufeBoni(int position, Haendlerraum haendlerraum) throws NichtGenugGoldException {
        int bonipreis = SpielController.getSkalierung() * Zahlensammlung.HAENDLERRAUM_PREIS_SKALIERUNG + Zahlensammlung.HAENDLERRAUM_PREIS_FAKTOR_ARTEFAKT;
        HauptSpieler protagonist = Spielwelt.getInstance().getProtagonist();

        if (protagonist.getGold() >= bonipreis) {
            protagonist.getInventar().getArtefaktinventar().add(haendlerraum.getBoniangebot().get(position));
            if (protagonist.getInventar().getArtefaktinventar().size() <= 2) {
                protagonist.getSpielfigur().getEffekte().add(haendlerraum.getBoniangebot().get(position).getEffekt());
            }
            haendlerraum.getBoniangebot().set(position, null);
            protagonist.setGold(protagonist.getGold() - bonipreis);
        } else {
            throw new NichtGenugGoldException(bonipreis);
        }
    }

    /**
     * Kauft die an der Position angebende Waffe im Angebot.
     * Gekaufte Waffe wird zu dem Waffeninventar hinzugefuegt.
     *
     * @param position     der Waffe im AngebotsArray
     * @param haendlerraum
     * @throws NichtGenugGoldException, falls der Spieler nicht genug Gold hat
     */
    private static void kaufeWaffe(int position, Haendlerraum haendlerraum) throws NichtGenugGoldException {
        int waffenpreis = SpielController.getSkalierung() * Zahlensammlung.HAENDLERRAUM_PREIS_SKALIERUNG + Zahlensammlung.HAENDLERRAUM_PREIS_FAKTOR_WAFFE;
        HauptSpieler protagonist = Spielwelt.getInstance().getProtagonist();

        if (protagonist.getGold() >= waffenpreis) {
            protagonist.getInventar().getWaffeninventar().add(haendlerraum.getWaffenangebot().get(position));
            haendlerraum.getWaffenangebot().set(position, null);
            protagonist.setGold(protagonist.getGold() - waffenpreis);
        } else {
            throw new NichtGenugGoldException(waffenpreis);
        }
    }

    /**
     * Diese Methode berechnet und gibt den Kaufpreis eines Spielobjekts (Karte, Boni oder Waffe) basierend auf der Kategorie und
     * dem Schwierigkeitsgrad, dem Level oder dem Seltenheitsgrad zurueck.
     *
     * @param kategorie
     * @return kaufpreis
     */
    public static int berechneKaufpreis(int kategorie) {
        int kaufpreis = 0;
        switch (kategorie) {
            case Zahlensammlung.HAENDLERRAUM_KATEGORIE_KARTE ->
                    kaufpreis = SpielController.getSkalierung() * Zahlensammlung.HAENDLERRAUM_PREIS_SKALIERUNG + Zahlensammlung.HAENDLERRAUM_PREIS_FAKTOR_KARTE;
            case Zahlensammlung.HAENDLERRAUM_KATEGORIE_BONI ->
                    kaufpreis = SpielController.getSkalierung() * Zahlensammlung.HAENDLERRAUM_PREIS_SKALIERUNG + Zahlensammlung.HAENDLERRAUM_PREIS_FAKTOR_ARTEFAKT;
            case Zahlensammlung.HAENDLERRAUM_KATEGORIE_WAFFE ->
                    kaufpreis = SpielController.getSkalierung() * Zahlensammlung.HAENDLERRAUM_PREIS_SKALIERUNG + Zahlensammlung.HAENDLERRAUM_PREIS_FAKTOR_WAFFE;
        }
        return kaufpreis;
    }
}
