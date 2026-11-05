package model;

import resources.enums.Spielerklasse;
import resources.interfaces.ZahlensammlungSpieler;

import java.io.Serializable;

/**
 * Der Hauptspieler beeinhaltet alle fuer das Spiel benoetigten Eigenschaften.
 * So kommen etwa das Inventar, das Gold und die Erfahrungspunkte hinzu.
 */
public non-sealed class HauptSpieler extends Spieler implements Serializable {

    private String klassenbezeichnung;
    private Inventar inventar;
    private int gold = 0;
    private int erfahrungsPunkte = 0;

    /**
     * Erstellt einen Spieler anhand der SpielerKlasse-Vorlage und des Namens.
     *
     * @param name
     * @param spielerklasseVorlage
     */
    public HauptSpieler(String name, Spielerklasse spielerklasseVorlage) {
        super(name, spielerklasseVorlage.getKartenDeck(), spielerklasseVorlage.getSpielfigur(), ZahlensammlungSpieler.HAUPTSPIELER_START_LEVEL, spielerklasseVorlage.getMana(), spielerklasseVorlage.getKartenProRunde());
        this.klassenbezeichnung = spielerklasseVorlage.getKlassenbezeichnung();
        this.inventar = spielerklasseVorlage.getInventar();

        //Setzt das Inventar zurueck.
        this.inventar.getArtefaktinventar().removeAll(this.inventar.getArtefaktinventar());
        Waffe startWaffe = spielerklasseVorlage.getStartwaffe();
        this.inventar.getWaffeninventar().removeAll(this.inventar.getWaffeninventar());
        this.inventar.getWaffeninventar().add(startWaffe);

        this.getSpielfigur().getEffekte().add(this.getInventar().getWaffeninventar().get(0).getEffekt());
        if (this.getInventar().getArtefaktinventar().size() == 1) {
            this.getSpielfigur().getEffekte().add(this.getInventar().getArtefaktinventar().get(0).getEffekt());
        } else if (this.getInventar().getArtefaktinventar().size() > 1) {
            this.getSpielfigur().getEffekte().add(this.getInventar().getArtefaktinventar().get(0).getEffekt());
            this.getSpielfigur().getEffekte().add(this.getInventar().getArtefaktinventar().get(1).getEffekt());
        }
    }

    /**
     * Erzeugt einen neuen Hauptspieler mit den identischen Werten.
     *
     * @param hauptSpieler
     */
    public HauptSpieler(HauptSpieler hauptSpieler) {
        super(hauptSpieler.getName(), hauptSpieler.getDeck(), hauptSpieler.getSpielfigur(), hauptSpieler.getLevel(), hauptSpieler.getMana(), hauptSpieler.getKartenProRunde());

        this.klassenbezeichnung = hauptSpieler.getKlassenbezeichnung();
        this.inventar = hauptSpieler.getInventar();
        this.gold = hauptSpieler.getGold();
        this.erfahrungsPunkte = hauptSpieler.getErfahrungsPunkte();
    }


    public String getKlassenbezeichnung() {
        return klassenbezeichnung;
    }

    public void setKlassenbezeichnung(String klassenbezeichnung) {
        this.klassenbezeichnung = klassenbezeichnung;
    }

    public Inventar getInventar() {
        return inventar;
    }

    public void setInventar(Inventar inventar) {
        this.inventar = inventar;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getErfahrungsPunkte() {
        return erfahrungsPunkte;
    }

    public void setErfahrungsPunkte(int erfahrungsPunkte) {
        this.erfahrungsPunkte = erfahrungsPunkte;
    }

}
