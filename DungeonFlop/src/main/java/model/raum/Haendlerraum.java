package model.raum;

import model.Boni;
import model.Waffe;
import model.karte.Karte;
import model.karte.Minion;
import model.karte.Zauber;
import resources.enums.BoniSammlung;
import resources.enums.MinionKarte;
import resources.enums.WaffenSammlung;
import resources.enums.ZauberKarte;
import resources.interfaces.Zahlensammlung;
import utilities.MeinIO;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Diese Klasse stellt einen Raum dar, wo der Spieler Sachen kaufen kann.
 * Der Haendlerraum generiert bei Erstellung ein neues Angebot.
 */
public non-sealed class Haendlerraum extends Raum implements Serializable {

    private ArrayList<Karte> kartenangebot = new ArrayList<>();
    private ArrayList<Boni> boniangebot = new ArrayList<>();
    private ArrayList<Waffe> waffenangebot = new ArrayList<>();

    /**
     * Erstellt einen Haendlerraum, dabei wird das Angebot neu generiert.
     *
     * @param name         Der Name es Haendlerraums
     * @param beschreibung Die Beschreibung des Haendlerraums
     * @param raumnummer   Die Nummer des Raums
     */
    public Haendlerraum(String name, String beschreibung, int raumnummer) {
        super(name, beschreibung, raumnummer);
        for (int i = 0; i < Zahlensammlung.HAENDLERRAUM_KARTEN_ANZAHL; i++) {
            if (MeinIO.getRandomBoolean()) { // Wahrscheinlichkeit ist 50/50 ob Zauber oder Minion gewaehlt wird
                this.getKartenangebot().add(new Minion(MinionKarte.getRandomMinionKarte()));
            } else {
                this.getKartenangebot().add(new Zauber(ZauberKarte.getRandomZauberKarte()));
            }
        }

        for (int i = 0; i < Zahlensammlung.HAENDLERRAUM_BONI_ANZAHL; i++) {
            this.getBoniangebot().add(new Boni(BoniSammlung.getRandomBoni()));
        }

        for (int i = 0; i < Zahlensammlung.HAENDLERRAUM_WAFFEN_ANZAHL; i++) {
            this.getWaffenangebot().add(new Waffe(WaffenSammlung.getRandomWaffe()));
        }
    }

    /**
     * Setzt den Inhalt des Haendlerraums zurueck.
     */
    public void reset() {
        kartenangebot.removeAll(kartenangebot);
        boniangebot.removeAll(boniangebot);
        waffenangebot.removeAll(waffenangebot);

        for (int i = 0; i < Zahlensammlung.HAENDLERRAUM_KARTEN_ANZAHL; i++) {
            if (MeinIO.getRandomBoolean()) { // Wahrscheinlichkeit ist 50/50 ob Zauber oder Minion gewaehlt wird
                this.getKartenangebot().add(new Minion(MinionKarte.getRandomMinionKarte()));
            } else {
                this.getKartenangebot().add(new Zauber(ZauberKarte.getRandomZauberKarte()));
            }
        }

        for (int i = 0; i < Zahlensammlung.HAENDLERRAUM_BONI_ANZAHL; i++) {
            this.getBoniangebot().add(new Boni(BoniSammlung.getRandomBoni()));
        }

        for (int i = 0; i < Zahlensammlung.HAENDLERRAUM_WAFFEN_ANZAHL; i++) {
            this.getWaffenangebot().add(new Waffe(WaffenSammlung.getRandomWaffe()));
        }
    }

    public ArrayList<Karte> getKartenangebot() {
        return kartenangebot;
    }

    public void setKartenangebot(ArrayList<Karte> kartenangebot) {
        this.kartenangebot = kartenangebot;
    }

    public ArrayList<Boni> getBoniangebot() {
        return boniangebot;
    }

    public void setBoniangebot(ArrayList<Boni> boniangebot) {
        this.boniangebot = boniangebot;
    }

    public ArrayList<Waffe> getWaffenangebot() {
        return waffenangebot;
    }

    public void setWaffenangebot(ArrayList<Waffe> waffenangebot) {
        this.waffenangebot = waffenangebot;
    }
}
