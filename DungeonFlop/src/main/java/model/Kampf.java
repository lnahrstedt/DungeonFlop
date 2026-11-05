package model;

import model.karte.Kartenverwaltung;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Ein Kampf besteht aus einer HashMap, die ein Spieler und eine Kartenverwaltung vereint sowie einem Spielfeld.
 * Er findet zwischen zwei Spielern statt.
 */
public class Kampf implements Serializable {

    private HauptSpieler hauptSpieler;
    private Spieler gegner;
    private HashMap<Spieler, Kartenverwaltung> spielerKartenverwaltung;
    private Spielfeld spielfeld;

    public Kampf(HauptSpieler hauptSpieler, Spieler gegner, HashMap<Spieler, Kartenverwaltung> spielerKartenverwaltung, Spielfeld spielfeld) {
        this.hauptSpieler = hauptSpieler;
        this.gegner = gegner;
        this.spielerKartenverwaltung = spielerKartenverwaltung;
        this.spielfeld = spielfeld;
    }

    public HauptSpieler getSpieler() {
        return hauptSpieler;
    }

    public void setSpieler(HauptSpieler hauptSpieler) {
        this.hauptSpieler = hauptSpieler;
    }

    public Spieler getGegner() {
        return gegner;
    }

    public void setGegner(Spieler gegner) {
        this.gegner = gegner;
    }

    public HashMap<Spieler, Kartenverwaltung> getSpielerKartenverwaltung() {
        return spielerKartenverwaltung;
    }

    public void setSpielerKartenverwaltung(HashMap<Spieler, Kartenverwaltung> spielerKartenverwaltung) {
        this.spielerKartenverwaltung = spielerKartenverwaltung;
    }

    public Spielfeld getSpielfeld() {
        return spielfeld;
    }

    public void setSpielfeld(Spielfeld spielfeld) {
        this.spielfeld = spielfeld;
    }

}
