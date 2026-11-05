package control.model;

import control.model.fabrik.EbenenFabrik;
import model.HauptSpieler;
import model.Spielwelt;
import model.netzwerk.NetzwerkFlagContainer;
import resources.enums.Schwierigkeitsgrad;
import resources.enums.Spielerklasse;
import resources.enums.SpielfigurSammlung;
import resources.interfaces.Zahlensammlung;

/**
 * Diese Klasse steuert bzw. verwaltet alle Vorgaenge im Zusammenhang des Spiels.
 */
public class SpielController {

    /**
     * Auf Basis der vom Spieler eingegeben Daten bestueckt diese Methode die Spielwelt und erstellt einen Spieler.
     *
     * @param spielername
     * @param spielerklasse
     * @param schwierigkeitsgrad
     */
    public static void erstelleSpielwelt(String spielername, Spielerklasse spielerklasse, Schwierigkeitsgrad schwierigkeitsgrad) {
        if (Spielwelt.getInstance().getProtagonist() != null && Spielwelt.getInstance().getProtagonist().getSpielfigur().getLebenspunkte() <= 0) {
            HauptSpieler toterHauptSpieler = new HauptSpieler(spielername, spielerklasse);
            toterHauptSpieler.setGold(Spielwelt.getInstance().getTodesGold());
            Spielwelt.getInstance().setProtagonist(toterHauptSpieler);
        } else {
            HauptSpieler hauptSpieler = new HauptSpieler(spielername, spielerklasse);
            Spielwelt.getInstance().setProtagonist(hauptSpieler);
            Spielwelt.getInstance().getProtagonist().setGold(Zahlensammlung.START_GOLD);
        }
        Spielwelt.getInstance().setSchwierigkeitsgrad(schwierigkeitsgrad);
        Spielwelt.getInstance().setEbenen(EbenenFabrik.liesEbenenEin());
        Spielwelt.getInstance().setSpielerEbenePosition(Spielwelt.getInstance().getEbenen().get(0));
        Spielwelt.getInstance().getProtagonist().getSpielfigur().setAngriffspunkte(Spielwelt.getInstance().getProtagonist().getInventar().getWaffeninventar().get(0).getSchaden());
        NetzwerkFlagContainer.setBinIchClient(false);
    }

    /**
     * Diese Methode wird ausgefuehrt, wenn ein Kampf verloren wurde.
     */
    public static void kampfVerloren() {
        Spielwelt.getInstance().setTodesGold(Spielwelt.getInstance().getProtagonist().getGold());
    }

    /**
     * Hat der Spieler den letzten Leiterraum der letzten Ebene erreicht, hat er das Spiel gewonnen.
     * Anschliessend wird diese Methode ausgefuehrt.
     */
    public static void spielGewonnen() {
        heileSpielfigur();
    }

    /**
     * Diese Methode sorgt dafuer, dass die Spielfigur geheilt wird.
     */
    private static void heileSpielfigur() {
        int healLebenspunkte = SpielfigurSammlung.valueOf(Spielwelt.getInstance().getProtagonist().getSpielfigur().getName().toUpperCase()).getLebenspunkte();
        Spielwelt.getInstance().getProtagonist().getSpielfigur().setLebenspunkte(healLebenspunkte);
    }

    /**
     * Gibt einen Wert, mit dem die verschiedensten Aspekte skaliert werden.
     *
     * @return Skalierungswert
     */
    public static int getSkalierung() {

        //Tiefe der Ebene
        int skalierung = Spielwelt.getInstance().getEbenen().indexOf(Spielwelt.getInstance().getSpielerEbenePosition());

        switch (Spielwelt.getInstance().getSchwierigkeitsgrad()) {
            case LEICHT -> skalierung -= 1;
            case SCHWER -> skalierung += 1;
            case HARDCORE -> skalierung += 2;
        }
        return skalierung;
    }


}
