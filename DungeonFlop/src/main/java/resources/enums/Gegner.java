package resources.enums;

import control.model.SpielController;
import control.model.raum.SchmiedraumController;
import model.Spieler;
import model.Spielfigur;
import model.karte.Karte;
import model.karte.Minion;
import model.karte.Zauber;
import resources.interfaces.StringsammlungGegner;
import resources.interfaces.Zahlensammlung;
import resources.interfaces.ZahlensammlungSkalierung;
import resources.interfaces.ZahlensammlungSpieler;
import utilities.MeinIO;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Stellt alle moeglichen Gegenspieler (Dungeonmaster) zur Verfuegung.
 */
public enum Gegner {

    //Beispiel
    GEGNER_1(StringsammlungGegner.SANDRAEUBER_NAME, new ArrayList<>() {{
        add(new Minion(MinionKarte.ECHSE_ST1));
        add(new Minion(MinionKarte.ECHSE_ST1));
        add(new Minion(MinionKarte.SANDSKORPION_ST1));
        add(new Minion(MinionKarte.SANDSKORPION_ST1));
        add(new Minion(MinionKarte.SANDSKORPION_ST1));
        add(new Minion(MinionKarte.SCHLANGE_ST1));
        add(new Minion(MinionKarte.SCHLANGE_ST1));
        add(new Minion(MinionKarte.KILLERKOJOTE_ST1));
        add(new Zauber(ZauberKarte.VERBALER_ANGRIFF_ST2));
        add(new Zauber(ZauberKarte.SAND_ST1));
        //Deck aus Karten
    }}
            , new Spielfigur(SpielfigurSammlung.NAHKAEMPFER), ZahlensammlungSpieler.SANDRAEUBER_MANA,
            ZahlensammlungSpieler.SANDRAEUBER_KPR, ZahlensammlungSpieler.SANDRAEUBER_SCHWIERIGKEIT,
            ZahlensammlungSpieler.SANDRAEUBER_LEVEL),

    GEGNER_2(StringsammlungGegner.REPTILIENZUECHTER_NAME, new ArrayList<>() {{
        add(new Minion(MinionKarte.SCHLANGE_ST1));
        add(new Minion(MinionKarte.SCHLANGE_ST1));
        add(new Minion(MinionKarte.SCHLANGE_ST2));
        add(new Minion(MinionKarte.ECHSE_ST1));
        add(new Minion(MinionKarte.ECHSE_ST2));
        add(new Minion(MinionKarte.TOEDLICHEVIPER_ST1));
        add(new Minion(MinionKarte.TOEDLICHEVIPER_ST1));
        add(new Minion(MinionKarte.TOEDLICHEVIPER_ST1));
        add(new Zauber(ZauberKarte.FEUERSPEER_ST1));
        add(new Zauber(ZauberKarte.FEUERSPEER_ST1));
    }}
            , new Spielfigur(SpielfigurSammlung.BOGENSCHUETZE), ZahlensammlungSpieler.REPTILIENZUECHTER_MANA,
            ZahlensammlungSpieler.REPTILIENZUECHTER_KPR, ZahlensammlungSpieler.REPTILIENZUECHTER_SCHWIERIGKEIT,
            ZahlensammlungSpieler.REPTILIENZUECHTER_LEVEL),

    GEGNER_3(StringsammlungGegner.DIEB_NAME, new ArrayList<>() {{
        add(new Minion(MinionKarte.DUESTERERDARKLING_ST1));
        add(new Minion(MinionKarte.DUESTERERDARKLING_ST1));
        add(new Minion(MinionKarte.DUESTERERDARKLING_ST2));
        add(new Minion(MinionKarte.NAECHTLICHERNEBELTRINKER_ST1));
        add(new Minion(MinionKarte.NAECHTLICHERNEBELTRINKER_ST1));
        add(new Minion(MinionKarte.GRUSELIGEGOTTESANBETERIN_ST1));
        add(new Minion(MinionKarte.GRUSELIGEGOTTESANBETERIN_ST1));
        add(new Minion(MinionKarte.ECHSE_ST1));
        add(new Zauber(ZauberKarte.SAND_ST1));
        add(new Zauber(ZauberKarte.SAND_ST2));
    }}
            , new Spielfigur(SpielfigurSammlung.MAGIER), ZahlensammlungSpieler.DIEB_MANA,
            ZahlensammlungSpieler.DIEB_KPR, ZahlensammlungSpieler.DIEB_SCHWIERIGKEIT,
            ZahlensammlungSpieler.DIEB_LEVEL),

    GEGNER_4(StringsammlungGegner.WILDER_NAME, new ArrayList<>() {{
        add(new Minion(MinionKarte.DURSTIGESDROMEDAR_ST1));
        add(new Minion(MinionKarte.DURSTIGESDROMEDAR_ST1));
        add(new Minion(MinionKarte.LAESSIGESLAMA_ST1));
        add(new Minion(MinionKarte.LAESSIGESLAMA_ST1));
        add(new Minion(MinionKarte.LAESSIGESLAMA_ST2));
        add(new Minion(MinionKarte.MAECHTIGERMOSQUITO_ST1));
        add(new Minion(MinionKarte.MAECHTIGERMOSQUITO_ST1));
        add(new Zauber(ZauberKarte.SAND_ST1));
        add(new Zauber(ZauberKarte.SAND_ST2));
    }}
            , new Spielfigur(SpielfigurSammlung.NAHKAEMPFER), ZahlensammlungSpieler.WILDER_MANA,
            ZahlensammlungSpieler.WILDER_KPR, ZahlensammlungSpieler.WILDER_SCHWIERIGKEIT,
            ZahlensammlungSpieler.WILDER_LEVEL),

    GEGNER_5(StringsammlungGegner.SANDBARBAR_NAME, new ArrayList<>() {{
        add(new Minion(MinionKarte.SANDSKORPION_ST1));
        add(new Minion(MinionKarte.SANDSKORPION_ST1));
        add(new Minion(MinionKarte.KILLERKOJOTE_ST1));
        add(new Minion(MinionKarte.KILLERKOJOTE_ST1));
        add(new Minion(MinionKarte.HUNGRIGEHORNISSE_ST1));
        add(new Minion(MinionKarte.HUNGRIGEHORNISSE_ST1));
        add(new Minion(MinionKarte.DURSTIGESDROMEDAR_ST1));
        add(new Minion(MinionKarte.DURSTIGESDROMEDAR_ST2));
        add(new Zauber(ZauberKarte.VERBALER_ANGRIFF_ST2));
        add(new Zauber(ZauberKarte.SAND_ST2));
    }}
            , new Spielfigur(SpielfigurSammlung.NAHKAEMPFER), ZahlensammlungSpieler.SANDBARBAR_MANA,
            ZahlensammlungSpieler.SANDBARBAR_KPR, ZahlensammlungSpieler.SANDBARBAR_SCHWIERIGKEIT,
            ZahlensammlungSpieler.SANDBARBAR_LEVEL),

    GEGNER_6(StringsammlungGegner.SKORPIONZUECHTER_NAME, new ArrayList<>() {{
        add(new Minion(MinionKarte.SANDSKORPION_ST1));
        add(new Minion(MinionKarte.SANDSKORPION_ST1));
        add(new Minion(MinionKarte.SANDSKORPION_ST1));
        add(new Minion(MinionKarte.SANDSKORPION_ST2));
        add(new Minion(MinionKarte.SANDSKORPION_ST2));
        add(new Minion(MinionKarte.SCHLANGE_ST1));
        add(new Minion(MinionKarte.TOEDLICHEVIPER_ST1));
        add(new Zauber(ZauberKarte.FEUERBALL_ST1));
        add(new Zauber(ZauberKarte.FEUERBALL_ST1));
    }}
            , new Spielfigur(SpielfigurSammlung.BOGENSCHUETZE), ZahlensammlungSpieler.SKORPIONZUECHTER_MANA,
            ZahlensammlungSpieler.SKORPIONZUECHTER_KPR, ZahlensammlungSpieler.SKORPIONZUECHTER_SCHWIERIGKEIT,
            ZahlensammlungSpieler.SKORPIONZUECHTER_LEVEL),

    GEGNER_7(StringsammlungGegner.ZAUBERLEHRLING_NAME, new ArrayList<>() {{
        add(new Minion(MinionKarte.NAECHTLICHERNEBELTRINKER_ST1));
        add(new Minion(MinionKarte.NAECHTLICHERNEBELTRINKER_ST1));
        add(new Minion(MinionKarte.MAECHTIGERMOSQUITO_ST1));
        add(new Minion(MinionKarte.MAECHTIGERMOSQUITO_ST2));
        add(new Minion(MinionKarte.HUNGRIGESCHWARZEWITWE_ST1));
        add(new Minion(MinionKarte.HUNGRIGESCHWARZEWITWE_ST1));
        add(new Zauber(ZauberKarte.FEUERBALL_ST1));
        add(new Zauber(ZauberKarte.FEUERBALL_ST1));
        add(new Zauber(ZauberKarte.FEUERSPEER_ST2));
    }}
            , new Spielfigur(SpielfigurSammlung.MAGIER), ZahlensammlungSpieler.ZAUBERLEHRLING_MANA,
            ZahlensammlungSpieler.ZAUBERLEHRLING_KPR, ZahlensammlungSpieler.ZAUBERLEHRLING_SCHWIERIGKEIT,
            ZahlensammlungSpieler.ZAUBERLEHRLING_LEVEL),

    GEGNER_8(StringsammlungGegner.WUESTENWANDERER_NAME, new ArrayList<>() {{
        add(new Minion(MinionKarte.KILLERKOJOTE_ST1));
        add(new Minion(MinionKarte.KILLERKOJOTE_ST1));
        add(new Minion(MinionKarte.LAESSIGESLAMA_ST1));
        add(new Minion(MinionKarte.LAESSIGESLAMA_ST1));
        add(new Minion(MinionKarte.IGNORANTEIGELZECKE_ST1));
        add(new Minion(MinionKarte.IGNORANTEIGELZECKE_ST2));
        add(new Zauber(ZauberKarte.SAND_ST2));
        add(new Zauber(ZauberKarte.SAND_ST3));
    }}
            , new Spielfigur(SpielfigurSammlung.NAHKAEMPFER), ZahlensammlungSpieler.WUESTENWANDERER_MANA,
            ZahlensammlungSpieler.WUESTENWANDERER_KPR, ZahlensammlungSpieler.WUESTENWANDERER_SCHWIERIGKEIT,
            ZahlensammlungSpieler.WUESTENWANDERER_LEVEL),

    GEGNER_9(StringsammlungGegner.INSEKTENHALTER_NAME, new ArrayList<>() {{
        add(new Minion(MinionKarte.SANDSKORPION_ST1));
        add(new Minion(MinionKarte.HUNGRIGEHORNISSE_ST1));
        add(new Minion(MinionKarte.HUNGRIGESCHWARZEWITWE_ST1));
        add(new Minion(MinionKarte.MAECHTIGERMOSQUITO_ST1));
        add(new Minion(MinionKarte.NAECHTLICHERNEBELTRINKER_ST1));
        add(new Minion(MinionKarte.DUESTERERDARKLING_ST2));
        add(new Minion(MinionKarte.GRUSELIGEGOTTESANBETERIN_ST2));
        add(new Minion(MinionKarte.IGNORANTEIGELZECKE_ST2));
        add(new Zauber(ZauberKarte.FEUERSPEER_ST2));
        add(new Zauber(ZauberKarte.FEUERSPEER_ST3));
    }}
            , new Spielfigur(SpielfigurSammlung.BOGENSCHUETZE), ZahlensammlungSpieler.INSEKTENHALTER_MANA,
            ZahlensammlungSpieler.INSEKTENHALTER_KPR, ZahlensammlungSpieler.INSEKTENHALTER_SCHWIERIGKEIT,
            ZahlensammlungSpieler.INSEKTENHALTER_LEVEL),

    GEGNER_10(StringsammlungGegner.BANDIT_NAME, new ArrayList<>() {{
        add(new Minion(MinionKarte.WUESTENKATZE_ST1));
        add(new Minion(MinionKarte.WUESTENKATZE_ST2));
        add(new Minion(MinionKarte.HUNGRIGEHORNISSE_ST1));
        add(new Minion(MinionKarte.HUNGRIGEHORNISSE_ST2));
        add(new Minion(MinionKarte.DURSTIGESDROMEDAR_ST2));
        add(new Minion(MinionKarte.LAESSIGESLAMA_ST1));
        add(new Minion(MinionKarte.KILLERKOJOTE_ST1));
        add(new Minion(MinionKarte.ECHSE_ST2));
        add(new Zauber(ZauberKarte.SAND_ST2));
        add(new Zauber(ZauberKarte.VERBALER_ANGRIFF_ST3));
    }}
            , new Spielfigur(SpielfigurSammlung.NAHKAEMPFER), ZahlensammlungSpieler.BANDIT_MANA,
            ZahlensammlungSpieler.BANDIT_KPR, ZahlensammlungSpieler.BANDIT_SCHWIERIGKEIT,
            ZahlensammlungSpieler.BANDIT_LEVEL),

    //Bosse
    BOSS_1(StringsammlungGegner.SISYPHOS_NAME, new ArrayList<>() {{
        add(new Minion(MinionKarte.SANDSKORPION_ST2));
        add(new Minion(MinionKarte.SANDSKORPION_ST1));
        add(new Minion(MinionKarte.SCHLANGE_ST1));
        add(new Minion(MinionKarte.SCHLANGE_ST2));
        add(new Minion(MinionKarte.SCHLANGE_ST1));
        add(new Minion(MinionKarte.WUESTENKATZE_ST1));
        add(new Minion(MinionKarte.WUESTENKATZE_ST2));
        add(new Minion(MinionKarte.DUESTERERDARKLING_ST1));
        add(new Minion(MinionKarte.GRUSELIGEGOTTESANBETERIN_ST1));
        add(new Zauber(ZauberKarte.FEUERBALL_ST1));
        add(new Zauber(ZauberKarte.FEUERBALL_ST1));
    }}
            , new Spielfigur(SpielfigurSammlung.SISYPHOS), ZahlensammlungSpieler.SISYPHOS_MANA,
            ZahlensammlungSpieler.SISYPHOS_KPR, ZahlensammlungSpieler.SISYPHOS_SCHWIERIGKEIT,
            ZahlensammlungSpieler.SISYPHOS_LEVEL),

    BOSS_2(StringsammlungGegner.TANTALOS_NAME, new ArrayList<>() {{
        add(new Minion(MinionKarte.LAESSIGESLAMA_ST1));
        add(new Minion(MinionKarte.LAESSIGESLAMA_ST2));
        add(new Minion(MinionKarte.IGNORANTEIGELZECKE_ST1));
        add(new Minion(MinionKarte.DURSTIGESDROMEDAR_ST2));
        add(new Minion(MinionKarte.MAECHTIGERMOSQUITO_ST1));
        add(new Minion(MinionKarte.MAECHTIGERMOSQUITO_ST1));
        add(new Minion(MinionKarte.NAECHTLICHERNEBELTRINKER_ST1));
        add(new Minion(MinionKarte.HUNGRIGEHORNISSE_ST2));
        add(new Minion(MinionKarte.KILLERKOJOTE_ST1));
        add(new Zauber(ZauberKarte.FEUERSPEER_ST2));
        add(new Zauber(ZauberKarte.FEUERSPEER_ST2));
    }}
            , new Spielfigur(SpielfigurSammlung.TANTALOS), ZahlensammlungSpieler.TANTALOS_MANA,
            ZahlensammlungSpieler.TANTALOS_KPR, ZahlensammlungSpieler.TANTALOS_SCHWIERIGKEIT,
            ZahlensammlungSpieler.TANTALOS_LEVEL),

    BOSS_3(StringsammlungGegner.ORPHEUS_NAME, new ArrayList<>() {{
        add(new Minion(MinionKarte.TOEDLICHEVIPER_ST2));
        add(new Minion(MinionKarte.TOEDLICHEVIPER_ST1));
        add(new Minion(MinionKarte.KILLERKOJOTE_ST1));
        add(new Minion(MinionKarte.NAECHTLICHERNEBELTRINKER_ST1));
        add(new Minion(MinionKarte.DUESTERERDARKLING_ST2));
        add(new Minion(MinionKarte.DUESTERERDARKLING_ST2));
        add(new Minion(MinionKarte.LAESSIGESLAMA_ST1));
        add(new Minion(MinionKarte.LAESSIGESLAMA_ST2));
        add(new Minion(MinionKarte.HUNGRIGEHORNISSE_ST1));
        add(new Minion(MinionKarte.HUNGRIGEHORNISSE_ST2));
        add(new Zauber(ZauberKarte.VERBALER_ANGRIFF_ST3));
        add(new Zauber(ZauberKarte.SAND_ST2));
    }}
            , new Spielfigur(SpielfigurSammlung.ORPHEUS), ZahlensammlungSpieler.ORPHEUS_MANA,
            ZahlensammlungSpieler.ORPHEUS_KPR, ZahlensammlungSpieler.ORPHEUS_SCHWIERIGKEIT,
            ZahlensammlungSpieler.ORPHEUS_LEVEL);


    private final String name;
    private final ArrayList<Karte> deck;
    private final Spielfigur spielfigur;
    private final int mana;
    private final int kartenProRunde;
    private final float schwierigkeitsgrad;

    private final int level;

    Gegner(String name, ArrayList<Karte> deck, Spielfigur spielfigur, int mana, int kartenProRunde, float schwierigkeitsgrad, int level) {
        this.name = name;
        this.deck = deck;
        this.spielfigur = spielfigur;
        this.mana = mana;
        this.kartenProRunde = kartenProRunde;
        this.schwierigkeitsgrad = schwierigkeitsgrad;
        this.level = level;
    }

    /**
     * Gibt einen zufaelligen, anhand des Schwierigkeitsgrad gewichteten Gegner zurueck.
     * Die Schwierigkeit des Gegners wird anhand des Schwierigkeitsgrads und dem Fortschritt entsprechend skaliert.
     *
     * @param schwierigkeitsgrad Der Schwierigkeitsgrad
     * @return Gegner
     */
    public static Spieler getGegner(float schwierigkeitsgrad) {
        Spieler gegenspieler = new Spieler(variiereGegner(schwierigkeitsgrad));
        gegnerSkalieren(gegenspieler);
        return gegenspieler;
    }

    /**
     * Gibt einen zufaelligen Boss zurueck.
     * Die Schwierigkeit des Bosses wird anhand des Schwierigkeitsgrads und dem Fortschritt entsprechend skaliert.
     * Es besteht eine 50%ige Wahrscheinlichkeit, dass der Gegner zurueckgegeben wird, der am naehesten an dem Schwierigkeitsgrad liegt.
     * Zu 25% wird ein schwererer Gegner und zu 25% ein leichterer Gegner zurueckgegeben.
     *
     * @return Boss
     */
    public static Spieler getBoss() {
        Spieler boss = new Spieler(variiereBoss());
        gegnerSkalieren(boss);
        return new Spieler(variiereBoss());
    }

    /**
     * Passt die Schwierigkeit des Gegners an.
     *
     * @param gegenspieler
     */
    private static void gegnerSkalieren(Spieler gegenspieler) {
        int skalierung = SpielController.getSkalierung();
        gegenspieler.setLevel(skalierung);

        //TODO Leere Abfrage?
        if (skalierung < ZahlensammlungSkalierung.SKALIERUNGS_GRENZE_1) {
        } else if (skalierung < ZahlensammlungSkalierung.SKALIERUNGS_GRENZE_2) {
            verbessereGegner(gegenspieler, ZahlensammlungSkalierung.HELDENVERBESSERUNG_GRENZE_2, ZahlensammlungSkalierung.MINIONVERBESSERUNG_GRENZE_2);
        } else if (skalierung < ZahlensammlungSkalierung.SKALIERUNGS_GRENZE_3) {
            verbessereGegner(gegenspieler, ZahlensammlungSkalierung.HELDENVERBESSERUNG_GRENZE_3, ZahlensammlungSkalierung.MINIONVERBESSERUNG_GRENZE_3);
        } else if (skalierung < ZahlensammlungSkalierung.SKALIERUNGS_GRENZE_4) {
            verbessereGegner(gegenspieler, ZahlensammlungSkalierung.HELDENVERBESSERUNG_GRENZE_4, ZahlensammlungSkalierung.MINIONVERBESSERUNG_GRENZE_4);
        } else {
            verbessereGegner(gegenspieler, ZahlensammlungSkalierung.HELDENVERBESSERUNG_GRENZE_5, ZahlensammlungSkalierung.MINIONVERBESSERUNG_GRENZE_5);
        }
    }

    /**
     * Gibt einen zufaelligen Boss zurueck.
     *
     * @return Boss
     */
    private static Gegner variiereBoss() {

        Gegner[] alleBosse = Arrays.copyOfRange(Gegner.values(), Gegner.values().length - Zahlensammlung.BOSSANZAHL, Gegner.values().length);
        return alleBosse[MeinIO.getRandomInt(alleBosse.length)];
    }

    /**
     * Gibt einen Gegner zurueck.
     * Variiert welcher Gegner anhand des Schwierigkeitsgrades zurueckgegeben wird.
     *
     * @param schwierigkeitsgrad
     * @return
     */
    private static Gegner variiereGegner(float schwierigkeitsgrad) {

        Gegner[] alleGegner = Arrays.copyOfRange(Gegner.values(), 0, Gegner.values().length - Zahlensammlung.BOSSANZAHL);
        float distanz = Math.abs(alleGegner[0].schwierigkeitsgrad - schwierigkeitsgrad);
        int naechsterWertIndex = 0;

        for (int i = 0; i < alleGegner.length; i++) {
            float vergleichsDistanz = Math.abs(alleGegner[i].schwierigkeitsgrad - schwierigkeitsgrad);
            if (vergleichsDistanz < distanz) {
                naechsterWertIndex = i;
                distanz = vergleichsDistanz;
            }
        }

        //Variiert die Gegnerschwierigkeit
        int rand = MeinIO.getRandomInt(Zahlensammlung.VARIATION_RAND_MAX);
        // 25% schwaecherer Gegner
        if (rand == Zahlensammlung.VARIATION_SCHWACH) {
            if (naechsterWertIndex == 0) {
                return alleGegner[naechsterWertIndex];
            } else {
                return alleGegner[naechsterWertIndex - 1];
            }
        }
        //50% normaler Gegner
        else if (rand < Zahlensammlung.VARIATION_NORMAL) {
            return alleGegner[naechsterWertIndex];
        }
        //25% staerkerer Gegner
        else {
            if (naechsterWertIndex == alleGegner.length - 1) {
                return alleGegner[naechsterWertIndex];
            } else {
                return alleGegner[naechsterWertIndex + 1];
            }
        }
    }

    /**
     * Verbessert den Helden und die Minions eines Gegners.
     *
     * @param gegenspieler
     * @param heldenVerbesserung
     * @param minionVerbesserung
     */
    private static void verbessereGegner(Spieler gegenspieler, int heldenVerbesserung, int minionVerbesserung) {
        Spielfigur spielfigur = gegenspieler.getSpielfigur();
        ArrayList<Karte> deck = gegenspieler.getDeck();

        spielfigur.setLebenspunkte(spielfigur.getLebenspunkte() + heldenVerbesserung);
        spielfigur.setAngriffspunkte(spielfigur.getAngriffspunkte() + heldenVerbesserung);

        for (int i = 0; i < minionVerbesserung; i++) {
            ArrayList<Karte> kartenUnterMaxStufe = new ArrayList<>();

            for (Karte karte : deck) {
                if (karte.getStufe() != Zahlensammlung.HOECHSTE_KARTEN_STUFE) {
                    kartenUnterMaxStufe.add(karte);
                }
            }
            //Falls keine Karte mehr verbessert werden kann.
            if (kartenUnterMaxStufe.size() == 0) {
                return;
            }
            SchmiedraumController.aufwertenOhneKosten(kartenUnterMaxStufe.get(MeinIO.getRandomInt(kartenUnterMaxStufe.size())));
        }
    }

    public String getName() {
        return name;
    }

    public ArrayList<Karte> getDeck() {
        return new ArrayList<>(deck);
    }

    public Spielfigur getSpielfigur() {
        return new Spielfigur(spielfigur);
    }

    public int getMana() {
        return mana;
    }

    public int getKartenProRunde() {
        return kartenProRunde;
    }

    public float getSchwierigkeitsgrad() {
        return schwierigkeitsgrad;
    }

    public int getLevel() {
        return level;
    }
}
