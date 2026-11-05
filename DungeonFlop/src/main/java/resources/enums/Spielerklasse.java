package resources.enums;

import model.Boni;
import model.Inventar;
import model.Spielfigur;
import model.Waffe;
import model.karte.Karte;
import model.karte.Minion;
import model.karte.Zauber;
import resources.interfaces.StringsammlungKlasse;
import resources.interfaces.ZahlensammlungSpieler;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Dieses Enum definiert die Spielerklassen vor.
 */
public enum Spielerklasse implements Serializable {

    AMOS(StringsammlungKlasse.AMOS_NAME, StringsammlungKlasse.AMOS_BEZEICHNUNG, new ArrayList<>() {{
        add(new Minion(MinionKarte.SANDSKORPION_ST1));
        add(new Minion(MinionKarte.ECHSE_ST1));
        add(new Minion(MinionKarte.SCHLANGE_ST1));
        add(new Minion(MinionKarte.WUESTENKATZE_ST1));
        add(new Minion(MinionKarte.SCHLANGE_ST1));
        add(new Zauber(ZauberKarte.SAND_ST1));
        add(new Zauber(ZauberKarte.VERBALER_ANGRIFF_ST1));
        add(new Zauber(ZauberKarte.SAND_ST1));
        add(new Minion(MinionKarte.WUESTENKATZE_ST1));
        add(new Minion(MinionKarte.SANDSKORPION_ST1));
        add(new Minion(MinionKarte.ECHSE_ST1));
    }}, new Inventar(new ArrayList<>(), new ArrayList<>()), new Spielfigur(SpielfigurSammlung.SANDKRIEGER), new ArrayList<>(), ZahlensammlungSpieler.AMOS_MANA, ZahlensammlungSpieler.AMOS_KPR,
            new Waffe(WaffenSammlung.SCHWERT)),

    ILEOS(StringsammlungKlasse.ILEOS_NAME, StringsammlungKlasse.ILEOS_BEZEICHNUNG, new ArrayList<>() {{
        add(new Minion(MinionKarte.SANDSKORPION_ST1));
        add(new Minion(MinionKarte.SCHLANGE_ST1));
        add(new Minion(MinionKarte.ECHSE_ST1));
        add(new Zauber(ZauberKarte.FEUERBALL_ST1));
        add(new Zauber(ZauberKarte.VERBALER_ANGRIFF_ST1));
        add(new Zauber(ZauberKarte.SAND_ST1));
        add(new Minion(MinionKarte.WUESTENKATZE_ST1));
        add(new Minion(MinionKarte.ECHSE_ST1));
        add(new Minion(MinionKarte.SCHLANGE_ST1));
        add(new Minion(MinionKarte.SANDSKORPION_ST1));
    }}, new Inventar(new ArrayList<>(), new ArrayList<>()), new Spielfigur(SpielfigurSammlung.SONNENPRIESTER), new ArrayList<>(), ZahlensammlungSpieler.ILEOS_MANA, ZahlensammlungSpieler.ILEOS_KPR,
            new Waffe(WaffenSammlung.AXT)),
    NERO(StringsammlungKlasse.NERO_NAME, StringsammlungKlasse.NERO_BEZEICHNUNG, new ArrayList<>() {{
        add(new Minion(MinionKarte.SANDSKORPION_ST1));
        add(new Minion(MinionKarte.ECHSE_ST1));
        add(new Minion(MinionKarte.SCHLANGE_ST1));
        add(new Zauber(ZauberKarte.FEUERBALL_ST1));
        add(new Zauber(ZauberKarte.SAND_ST1));
        add(new Zauber(ZauberKarte.FEUERSPEER_ST1));
        add(new Minion(MinionKarte.WUESTENKATZE_ST1));
        add(new Minion(MinionKarte.SANDSKORPION_ST1));
        add(new Minion(MinionKarte.WUESTENKATZE_ST1));
        add(new Minion(MinionKarte.SCHLANGE_ST1));
    }}, new Inventar(new ArrayList<>(), new ArrayList<>()), new Spielfigur(SpielfigurSammlung.WASSERTAENZER), new ArrayList<>(), ZahlensammlungSpieler.NERO_MANA, ZahlensammlungSpieler.NERO_KPR,
            new Waffe(WaffenSammlung.BOGEN));

    private final String klassenbezeichnung;
    private final String klassenbeschreibung;
    private final ArrayList<Karte> kartenDeck;
    private final Inventar inventar;
    private final Spielfigur spielfigur;
    private final ArrayList<Boni> talente;

    private final Waffe startWaffe;
    private final int mana;
    private final int kartenProRunde;

    Spielerklasse(String klassenbezeichnung, String klassenbeschreibung, ArrayList<Karte> kartenDeck, Inventar inventar,
                  Spielfigur spielfigur, ArrayList<Boni> talente, int mana, int kartenProRunde, Waffe startWaffe) {
        this.klassenbezeichnung = klassenbezeichnung;
        this.klassenbeschreibung = klassenbeschreibung;
        this.kartenDeck = kartenDeck;
        this.startWaffe = startWaffe;
        inventar.setWaffeninventar(new ArrayList<>() {{
            add(startWaffe);
        }});
        this.inventar = inventar;
        this.spielfigur = spielfigur;
        this.talente = talente;
        this.mana = mana;
        this.kartenProRunde = kartenProRunde;
    }

    public String getKlassenbezeichnung() {
        return klassenbezeichnung;
    }

    public String getKlassenbeschreibung() {
        return klassenbeschreibung;
    }

    public ArrayList<Karte> getKartenDeck() {
        return new ArrayList<>(kartenDeck);
    }

    public Inventar getInventar() {
        return inventar;
    }

    public Spielfigur getSpielfigur() {
        return new Spielfigur(spielfigur);
    }

    public ArrayList<Boni> getTalente() {
        return talente;
    }

    public int getMana() {
        return mana;
    }

    public int getKartenProRunde() {
        return kartenProRunde;
    }

    public Waffe getStartwaffe() {
        return startWaffe;
    }

}
