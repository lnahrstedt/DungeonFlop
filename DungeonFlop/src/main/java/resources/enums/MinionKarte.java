package resources.enums;

import model.effekt.*;
import model.karte.Minion;
import resources.interfaces.StringsammlungSpielfigur;
import resources.interfaces.Zahlensammlung;
import resources.interfaces.ZahlensammlungSpielfigur;
import utilities.MeinIO;

import java.util.ArrayList;

/**
 * Beinhaltet die vordefinierten Minions.
 */
public enum MinionKarte {
    SANDSKORPION_ST1(StringsammlungSpielfigur.SANDSKORPION_NAME, StringsammlungSpielfigur.SANDSKORPION_BESCHREIBUNG, ZahlensammlungSpielfigur.SANDSKOPRION_LEBENSPUNKTE_ST1, ZahlensammlungSpielfigur.SANDSKORPION_ANGRIFFSPUNKTE_ST1, ZahlensammlungSpielfigur.SANDSKORPION_MANAKOSTEN, new ArrayList<>() {{
        add(new ReichweiteEffekt(ZahlensammlungSpielfigur.SANDSKORPION_REICHWEITEEFFEKT_WERT_ST1));
    }}, Zahlensammlung.KARTE_STUFE_1),

    SANDSKORPION_ST2(StringsammlungSpielfigur.SANDSKORPION_NAME, StringsammlungSpielfigur.SANDSKORPION_BESCHREIBUNG, ZahlensammlungSpielfigur.SANDSKOPRION_LEBENSPUNKTE_ST2, ZahlensammlungSpielfigur.SANDSKORPION_ANGRIFFSPUNKTE_ST2, ZahlensammlungSpielfigur.SANDSKORPION_MANAKOSTEN, new ArrayList<>() {{
        add(new ReichweiteEffekt(ZahlensammlungSpielfigur.SANDSKORPION_REICHWEITEEFFEKT_WERT_ST2));
    }}, Zahlensammlung.KARTE_STUFE_2),

    SANDSKORPION_ST3(StringsammlungSpielfigur.SANDSKORPION_NAME, StringsammlungSpielfigur.SANDSKORPION_BESCHREIBUNG, ZahlensammlungSpielfigur.SANDSKOPRION_LEBENSPUNKTE_ST3, ZahlensammlungSpielfigur.SANDSKORPION_ANGRIFFSPUNKTE_ST3, ZahlensammlungSpielfigur.SANDSKORPION_MANAKOSTEN, new ArrayList<>() {{
        add(new ReichweiteEffekt(ZahlensammlungSpielfigur.SANDSKORPION_REICHWEITEEFFEKT_WERT_ST3));
    }}, Zahlensammlung.KARTE_STUFE_3),


    SCHLANGE_ST1(StringsammlungSpielfigur.SCHLANGE_NAME, StringsammlungSpielfigur.SCHLANGE_BESCHREIBUNG, ZahlensammlungSpielfigur.SCHLANGE_LEBENSPUNKTE_ST1, ZahlensammlungSpielfigur.SCHLANGE_ANGRIFFSPUNKTE_ST1, ZahlensammlungSpielfigur.SCHLANGE_MANAKOSTEN, new ArrayList<>() {{
        add(new WeiterGehenEffekt(ZahlensammlungSpielfigur.SCHLANGE_EFFEKT_WERT_ST1));
    }}, Zahlensammlung.KARTE_STUFE_1),

    SCHLANGE_ST2(StringsammlungSpielfigur.SCHLANGE_NAME, StringsammlungSpielfigur.SCHLANGE_BESCHREIBUNG, ZahlensammlungSpielfigur.SCHLANGE_LEBENSPUNKTE_ST2, ZahlensammlungSpielfigur.SCHLANGE_ANGRIFFSPUNKTE_ST2, ZahlensammlungSpielfigur.SCHLANGE_MANAKOSTEN, new ArrayList<>() {{
        add(new WeiterGehenEffekt(ZahlensammlungSpielfigur.SCHLANGE_EFFEKT_WERT_ST2));
    }}, Zahlensammlung.KARTE_STUFE_2),

    SCHLANGE_ST3(StringsammlungSpielfigur.SCHLANGE_NAME, StringsammlungSpielfigur.SCHLANGE_BESCHREIBUNG, ZahlensammlungSpielfigur.SCHLANGE_LEBENSPUNKTE_ST3, ZahlensammlungSpielfigur.SCHLANGE_ANGRIFFSPUNKTE_ST3, ZahlensammlungSpielfigur.SCHLANGE_MANAKOSTEN, new ArrayList<>() {{
        add(new WeiterGehenEffekt(ZahlensammlungSpielfigur.SCHLANGE_EFFEKT_WERT_ST3));
    }}, Zahlensammlung.KARTE_STUFE_3),


    ECHSE_ST1(StringsammlungSpielfigur.ECHSE_NAME, StringsammlungSpielfigur.ECHSE_BESCHREIBUNG, ZahlensammlungSpielfigur.ECHSE_LEBENSPUNKTE_ST1, ZahlensammlungSpielfigur.ECHSE_ANGRIFFSPUNKTE_ST1, ZahlensammlungSpielfigur.ECHSE_MANAKOSTEN, new ArrayList<>() {{
        add(new WeiterGehenEffekt(ZahlensammlungSpielfigur.ECHSE_EFFEKT_WERT_GEHEN_ST1));
        add(new MehrfachSchlagenEffekt(ZahlensammlungSpielfigur.ECHSE_EFFEKT_WERT_ANGREIFEN_ST1));
    }}, Zahlensammlung.KARTE_STUFE_1),

    ECHSE_ST2(StringsammlungSpielfigur.ECHSE_NAME, StringsammlungSpielfigur.ECHSE_BESCHREIBUNG, ZahlensammlungSpielfigur.ECHSE_LEBENSPUNKTE_ST2, ZahlensammlungSpielfigur.ECHSE_ANGRIFFSPUNKTE_ST2, ZahlensammlungSpielfigur.ECHSE_MANAKOSTEN, new ArrayList<>() {{
        add(new WeiterGehenEffekt(ZahlensammlungSpielfigur.ECHSE_EFFEKT_WERT_GEHEN_ST2));
        add(new MehrfachSchlagenEffekt(ZahlensammlungSpielfigur.ECHSE_EFFEKT_WERT_ANGREIFEN_ST2));
    }}, Zahlensammlung.KARTE_STUFE_2),

    ECHSE_ST3(StringsammlungSpielfigur.ECHSE_NAME, StringsammlungSpielfigur.ECHSE_BESCHREIBUNG, ZahlensammlungSpielfigur.ECHSE_LEBENSPUNKTE_ST3, ZahlensammlungSpielfigur.ECHSE_ANGRIFFSPUNKTE_ST3, ZahlensammlungSpielfigur.ECHSE_MANAKOSTEN, new ArrayList<>() {{
        add(new WeiterGehenEffekt(ZahlensammlungSpielfigur.ECHSE_EFFEKT_WERT_GEHEN_ST3));
        add(new MehrfachSchlagenEffekt(ZahlensammlungSpielfigur.ECHSE_EFFEKT_WERT_ANGREIFEN_ST3));
    }}, Zahlensammlung.KARTE_STUFE_3),


    WUESTENKATZE_ST1(StringsammlungSpielfigur.WUESTENKATZE_NAME, StringsammlungSpielfigur.WUESTENKATZE_BESCHREIBUNG, ZahlensammlungSpielfigur.WUESTENKATZE_LEBENSPUNKTE_ST1, ZahlensammlungSpielfigur.WUESTENKATZE_ANGRIFFSPUNKTE_ST1, ZahlensammlungSpielfigur.WUESTENKATZE_MANAKOSTEN, new ArrayList<>() {{
        add(new MehrfachSchlagenEffekt(ZahlensammlungSpielfigur.WUESTENKATZE_EFFEKT_WERT_ST1));
    }}, Zahlensammlung.KARTE_STUFE_1),

    WUESTENKATZE_ST2(StringsammlungSpielfigur.WUESTENKATZE_NAME, StringsammlungSpielfigur.WUESTENKATZE_BESCHREIBUNG, ZahlensammlungSpielfigur.WUESTENKATZE_LEBENSPUNKTE_ST2, ZahlensammlungSpielfigur.WUESTENKATZE_ANGRIFFSPUNKTE_ST2, ZahlensammlungSpielfigur.WUESTENKATZE_MANAKOSTEN, new ArrayList<>() {{
        add(new MehrfachSchlagenEffekt(ZahlensammlungSpielfigur.WUESTENKATZE_EFFEKT_WERT_ST2));
        add(new SofortLoslaufenEffekt());
    }}, Zahlensammlung.KARTE_STUFE_2),

    WUESTENKATZE_ST3(StringsammlungSpielfigur.WUESTENKATZE_NAME, StringsammlungSpielfigur.WUESTENKATZE_BESCHREIBUNG, ZahlensammlungSpielfigur.WUESTENKATZE_LEBENSPUNKTE_ST3, ZahlensammlungSpielfigur.WUESTENKATZE_ANGRIFFSPUNKTE_ST3, ZahlensammlungSpielfigur.WUESTENKATZE_MANAKOSTEN, new ArrayList<>() {{
        add(new MehrfachSchlagenEffekt(ZahlensammlungSpielfigur.WUESTENKATZE_EFFEKT_WERT_ST3));
        add(new SofortLoslaufenEffekt());
    }}, Zahlensammlung.KARTE_STUFE_3),

    TOEDLICHEVIPER_ST1(StringsammlungSpielfigur.TOEDLICHEVIPER_NAME, StringsammlungSpielfigur.TOEDLICHEVIPER_BESCHREIBUNG, ZahlensammlungSpielfigur.TOEDLICHEVIPER_LEBENSPUNKTE_ST1, ZahlensammlungSpielfigur.TOEDLICHEVIPER_ANGRIFFSPUNKTE_ST1, ZahlensammlungSpielfigur.TOEDLICHEVIPER_MANAKOSTEN, new ArrayList<>() {{
        add(new WeiterGehenEffekt(ZahlensammlungSpielfigur.TOEDLICHEVIPER_WEITERGEHENEFFEKT_ST1));
    }}, Zahlensammlung.KARTE_STUFE_1),

    TOEDLICHEVIPER_ST2(StringsammlungSpielfigur.TOEDLICHEVIPER_NAME, StringsammlungSpielfigur.TOEDLICHEVIPER_BESCHREIBUNG, ZahlensammlungSpielfigur.TOEDLICHEVIPER_LEBENSPUNKTE_ST2, ZahlensammlungSpielfigur.TOEDLICHEVIPER_ANGRIFFSPUNKTE_ST2, ZahlensammlungSpielfigur.TOEDLICHEVIPER_MANAKOSTEN, new ArrayList<>() {{
        add(new WeiterGehenEffekt(ZahlensammlungSpielfigur.TOEDLICHEVIPER_WEITERGEHENEFFEKT_ST2));
    }}, Zahlensammlung.KARTE_STUFE_2),

    TOEDLICHEVIPER_ST3(StringsammlungSpielfigur.TOEDLICHEVIPER_NAME, StringsammlungSpielfigur.TOEDLICHEVIPER_BESCHREIBUNG, ZahlensammlungSpielfigur.TOEDLICHEVIPER_LEBENSPUNKTE_ST3, ZahlensammlungSpielfigur.TOEDLICHEVIPER_ANGRIFFSPUNKTE_ST3, ZahlensammlungSpielfigur.TOEDLICHEVIPER_MANAKOSTEN, new ArrayList<>() {{
        add(new WeiterGehenEffekt(ZahlensammlungSpielfigur.TOEDLICHEVIPER_WEITERGEHENEFFEKT_ST3));
    }}, Zahlensammlung.KARTE_STUFE_3),

    KILLERKOJOTE_ST1(StringsammlungSpielfigur.KILLERKOJOTE_NAME, StringsammlungSpielfigur.KILLERKOJOTE_BESCHREIBUNG, ZahlensammlungSpielfigur.KILLERKOJOTE_LEBENSPUNKTE_ST1, ZahlensammlungSpielfigur.KILLERKOJOTE_ANGRIFFSPUNKTE_ST1, ZahlensammlungSpielfigur.KILLERKOJOTE_MANAKOSTEN, new ArrayList<>(),
            Zahlensammlung.KARTE_STUFE_1),

    KILLERKOJOTE_ST2(StringsammlungSpielfigur.KILLERKOJOTE_NAME, StringsammlungSpielfigur.KILLERKOJOTE_BESCHREIBUNG, ZahlensammlungSpielfigur.KILLERKOJOTE_LEBENSPUNKTE_ST2, ZahlensammlungSpielfigur.KILLERKOJOTE_ANGRIFFSPUNKTE_ST2, ZahlensammlungSpielfigur.KILLERKOJOTE_MANAKOSTEN, new ArrayList<>() {{
        add(new SofortLoslaufenEffekt());
    }}, Zahlensammlung.KARTE_STUFE_2),

    KILLERKOJOTE_ST3(StringsammlungSpielfigur.KILLERKOJOTE_NAME, StringsammlungSpielfigur.KILLERKOJOTE_BESCHREIBUNG, ZahlensammlungSpielfigur.KILLERKOJOTE_LEBENSPUNKTE_ST3, ZahlensammlungSpielfigur.KILLERKOJOTE_ANGRIFFSPUNKTE_ST3, ZahlensammlungSpielfigur.KILLERKOJOTE_MANAKOSTEN, new ArrayList<>() {{
        add(new MehrfachSchlagenEffekt(ZahlensammlungSpielfigur.KILLERKOJOTE_MEHRFACHSCHLAGENEFFEKT_ST3));
        add(new SofortLoslaufenEffekt());
    }}, Zahlensammlung.KARTE_STUFE_3),

    HUNGRIGEHORNISSE_ST1(StringsammlungSpielfigur.HUNGRIGEHORNISSE_NAME, StringsammlungSpielfigur.HUNGRIGEHORNISSE_BESCHREIBUNG, ZahlensammlungSpielfigur.HUNGRIGEHORNISSE_LEBENSPUNKTE_ST1, ZahlensammlungSpielfigur.HUNGRIGEHORNISSE_ANGRIFFSPUNKTE_ST1, ZahlensammlungSpielfigur.HUNGRIGEHORNISSE_MANAKOSTEN, new ArrayList<>() {{
        add(new SofortLoslaufenEffekt());
    }}, Zahlensammlung.KARTE_STUFE_1),

    HUNGRIGEHORNISSE_ST2(StringsammlungSpielfigur.HUNGRIGEHORNISSE_NAME, StringsammlungSpielfigur.HUNGRIGEHORNISSE_BESCHREIBUNG, ZahlensammlungSpielfigur.HUNGRIGEHORNISSE_LEBENSPUNKTE_ST2, ZahlensammlungSpielfigur.HUNGRIGEHORNISSE_ANGRIFFSPUNKTE_ST2, ZahlensammlungSpielfigur.HUNGRIGEHORNISSE_MANAKOSTEN, new ArrayList<>() {{
        add(new SofortLoslaufenEffekt());
    }}, Zahlensammlung.KARTE_STUFE_2),

    HUNGRIGEHORNISSE_ST3(StringsammlungSpielfigur.HUNGRIGEHORNISSE_NAME, StringsammlungSpielfigur.HUNGRIGEHORNISSE_BESCHREIBUNG, ZahlensammlungSpielfigur.HUNGRIGEHORNISSE_LEBENSPUNKTE_ST3, ZahlensammlungSpielfigur.HUNGRIGEHORNISSE_ANGRIFFSPUNKTE_ST3, ZahlensammlungSpielfigur.HUNGRIGEHORNISSE_MANAKOSTEN, new ArrayList<>() {{
        add(new SofortLoslaufenEffekt());
    }}, Zahlensammlung.KARTE_STUFE_3),

    MAECHTIGERMOSQUITO_ST1(StringsammlungSpielfigur.MAECHTIGERMOSQUITO_NAME, StringsammlungSpielfigur.MAECHTIGERMOSQUITO_BESCHREIBUNG, ZahlensammlungSpielfigur.MAECHTIGERMOSQUITO_LEBENSPUNKTE_ST1, ZahlensammlungSpielfigur.MAECHTIGERMOSQUITO_ANGRIFFSPUNKTE_ST1, ZahlensammlungSpielfigur.MAECHTIGERMOSQUITO_MANAKOSTEN, new ArrayList<>() {{
        add(new SofortLoslaufenEffekt());
    }}, Zahlensammlung.KARTE_STUFE_1),

    MAECHTIGERMOSQUITO_ST2(StringsammlungSpielfigur.MAECHTIGERMOSQUITO_NAME, StringsammlungSpielfigur.MAECHTIGERMOSQUITO_BESCHREIBUNG, ZahlensammlungSpielfigur.MAECHTIGERMOSQUITO_LEBENSPUNKTE_ST2, ZahlensammlungSpielfigur.MAECHTIGERMOSQUITO_ANGRIFFSPUNKTE_ST2, ZahlensammlungSpielfigur.MAECHTIGERMOSQUITO_MANAKOSTEN, new ArrayList<>() {{
        add(new SofortLoslaufenEffekt());
    }}, Zahlensammlung.KARTE_STUFE_2),

    MAECHTIGERMOSQUITO_ST3(StringsammlungSpielfigur.MAECHTIGERMOSQUITO_NAME, StringsammlungSpielfigur.MAECHTIGERMOSQUITO_BESCHREIBUNG, ZahlensammlungSpielfigur.MAECHTIGERMOSQUITO_LEBENSPUNKTE_ST3, ZahlensammlungSpielfigur.MAECHTIGERMOSQUITO_ANGRIFFSPUNKTE_ST3, ZahlensammlungSpielfigur.MAECHTIGERMOSQUITO_MANAKOSTEN, new ArrayList<>() {{
        add(new WeiterGehenEffekt(ZahlensammlungSpielfigur.MAECHTIGERMOSQUITO_WEITERGEHENEFFEKT_ST3));
        add(new SofortLoslaufenEffekt());
    }}, Zahlensammlung.KARTE_STUFE_3),

    NAECHTLICHERNEBELTRINKER_ST1(StringsammlungSpielfigur.NAECHTLICHERNEBELTRINKER_NAME, StringsammlungSpielfigur.NAECHTLICHERNEBELTRINKER_BESCHREIBUNG, ZahlensammlungSpielfigur.NAECHTLICHERNEBELTRINKER_LEBENSPUNKTE_ST1, ZahlensammlungSpielfigur.NAECHTLICHERNEBELTRINKER_ANGRIFFSPUNKTE_ST1, ZahlensammlungSpielfigur.NAECHTLICHERNEBELTRINKER_MANAKOSTEN, new ArrayList<>(),
            Zahlensammlung.KARTE_STUFE_1),

    NAECHTLICHERNEBELTRINKER_ST2(StringsammlungSpielfigur.NAECHTLICHERNEBELTRINKER_NAME, StringsammlungSpielfigur.NAECHTLICHERNEBELTRINKER_BESCHREIBUNG, ZahlensammlungSpielfigur.NAECHTLICHERNEBELTRINKER_LEBENSPUNKTE_ST2, ZahlensammlungSpielfigur.NAECHTLICHERNEBELTRINKER_ANGRIFFSPUNKTE_ST2, ZahlensammlungSpielfigur.NAECHTLICHERNEBELTRINKER_MANAKOSTEN, new ArrayList<>(),
            Zahlensammlung.KARTE_STUFE_2),

    NAECHTLICHERNEBELTRINKER_ST3(StringsammlungSpielfigur.NAECHTLICHERNEBELTRINKER_NAME, StringsammlungSpielfigur.NAECHTLICHERNEBELTRINKER_BESCHREIBUNG, ZahlensammlungSpielfigur.NAECHTLICHERNEBELTRINKER_LEBENSPUNKTE_ST3, ZahlensammlungSpielfigur.NAECHTLICHERNEBELTRINKER_ANGRIFFSPUNKTE_ST3, ZahlensammlungSpielfigur.NAECHTLICHERNEBELTRINKER_MANAKOSTEN, new ArrayList<>(),
            Zahlensammlung.KARTE_STUFE_3),

    DUESTERERDARKLING_ST1(StringsammlungSpielfigur.DUESTERERDARKLING_NAME, StringsammlungSpielfigur.DUESTERERDARKLING_BESCHREIBUNG, ZahlensammlungSpielfigur.DUESTERERDARKLING_LEBENSPUNKTE_ST1, ZahlensammlungSpielfigur.DUESTERERDARKLING_ANGRIFFSPUNKTE_ST1, ZahlensammlungSpielfigur.DUESTERERDARKLING_MANAKOSTEN, new ArrayList<>() {{
        add(new WeiterGehenEffekt(ZahlensammlungSpielfigur.DUESTERERDARKLING_WEITERGEHENEFFEKT_ST1));
    }}, Zahlensammlung.KARTE_STUFE_1),

    DUESTERERDARKLING_ST2(StringsammlungSpielfigur.DUESTERERDARKLING_NAME, StringsammlungSpielfigur.DUESTERERDARKLING_BESCHREIBUNG, ZahlensammlungSpielfigur.DUESTERERDARKLING_LEBENSPUNKTE_ST2, ZahlensammlungSpielfigur.DUESTERERDARKLING_ANGRIFFSPUNKTE_ST2, ZahlensammlungSpielfigur.DUESTERERDARKLING_MANAKOSTEN, new ArrayList<>() {{
        add(new WeiterGehenEffekt(ZahlensammlungSpielfigur.DUESTERERDARKLING_WEITERGEHENEFFEKT_ST2));
    }}, Zahlensammlung.KARTE_STUFE_2),

    DUESTERERDARKLING_ST3(StringsammlungSpielfigur.DUESTERERDARKLING_NAME, StringsammlungSpielfigur.DUESTERERDARKLING_BESCHREIBUNG, ZahlensammlungSpielfigur.DUESTERERDARKLING_LEBENSPUNKTE_ST3, ZahlensammlungSpielfigur.DUESTERERDARKLING_ANGRIFFSPUNKTE_ST3, ZahlensammlungSpielfigur.DUESTERERDARKLING_MANAKOSTEN, new ArrayList<>() {{
        add(new WeiterGehenEffekt(ZahlensammlungSpielfigur.DUESTERERDARKLING_WEITERGEHENEFFEKT_ST3));
    }}, Zahlensammlung.KARTE_STUFE_3),

    GRUSELIGEGOTTESANBETERIN_ST1(StringsammlungSpielfigur.GRUSELIGEGOTTESANBETERIN_NAME, StringsammlungSpielfigur.GRUSELIGEGOTTESANBETERIN_BESCHREIBUNG, ZahlensammlungSpielfigur.GRUSELIGEGOTTESANBETERIN_LEBENSPUNKTE_ST1, ZahlensammlungSpielfigur.GRUSELIGEGOTTESANBETERIN_ANGRIFFSPUNKTE_ST1, ZahlensammlungSpielfigur.GRUSELIGEGOTTESANBETERIN_MANAKOSTEN, new ArrayList<>() {{
        add(new SofortLoslaufenEffekt());
    }}, Zahlensammlung.KARTE_STUFE_1),

    GRUSELIGEGOTTESANBETERIN_ST2(StringsammlungSpielfigur.GRUSELIGEGOTTESANBETERIN_NAME, StringsammlungSpielfigur.GRUSELIGEGOTTESANBETERIN_BESCHREIBUNG, ZahlensammlungSpielfigur.GRUSELIGEGOTTESANBETERIN_LEBENSPUNKTE_ST2, ZahlensammlungSpielfigur.GRUSELIGEGOTTESANBETERIN_ANGRIFFSPUNKTE_ST2, ZahlensammlungSpielfigur.GRUSELIGEGOTTESANBETERIN_MANAKOSTEN, new ArrayList<>() {{
        add(new SofortLoslaufenEffekt());
    }}, Zahlensammlung.KARTE_STUFE_2),

    GRUSELIGEGOTTESANBETERIN_ST3(StringsammlungSpielfigur.GRUSELIGEGOTTESANBETERIN_NAME, StringsammlungSpielfigur.GRUSELIGEGOTTESANBETERIN_BESCHREIBUNG, ZahlensammlungSpielfigur.GRUSELIGEGOTTESANBETERIN_LEBENSPUNKTE_ST3, ZahlensammlungSpielfigur.GRUSELIGEGOTTESANBETERIN_ANGRIFFSPUNKTE_ST3, ZahlensammlungSpielfigur.GRUSELIGEGOTTESANBETERIN_MANAKOSTEN, new ArrayList<>() {{
        add(new SofortLoslaufenEffekt());
    }}, Zahlensammlung.KARTE_STUFE_3),

    DURSTIGESDROMEDAR_ST1(StringsammlungSpielfigur.DURSTIGESDROMEDAR_NAME, StringsammlungSpielfigur.DURSTIGESDROMEDAR_BESCHREIBUNG, ZahlensammlungSpielfigur.DURSTIGESDROMEDAR_LEBENSPUNKTE_ST1, ZahlensammlungSpielfigur.DURSTIGESDROMEDAR_ANGRIFFSPUNKTE_ST1, ZahlensammlungSpielfigur.DURSTIGESDROMEDAR_MANAKOSTEN, new ArrayList<>() {{
        add(new WeiterGehenEffekt(ZahlensammlungSpielfigur.DURSTIGESDROMEDAR_WEITERGEHENEFFEKT_ST1));
        add(new MehrfachSchlagenEffekt(ZahlensammlungSpielfigur.DURSTIGESDROMEDAR_MEHRFACHSCHLAGENEFFEKT_ST1));
        add(new SofortLoslaufenEffekt());
    }}, Zahlensammlung.KARTE_STUFE_1),

    DURSTIGESDROMEDAR_ST2(StringsammlungSpielfigur.DURSTIGESDROMEDAR_NAME, StringsammlungSpielfigur.DURSTIGESDROMEDAR_BESCHREIBUNG, ZahlensammlungSpielfigur.DURSTIGESDROMEDAR_LEBENSPUNKTE_ST2, ZahlensammlungSpielfigur.DURSTIGESDROMEDAR_ANGRIFFSPUNKTE_ST2, ZahlensammlungSpielfigur.DURSTIGESDROMEDAR_MANAKOSTEN, new ArrayList<>() {{
        add(new WeiterGehenEffekt(ZahlensammlungSpielfigur.DURSTIGESDROMEDAR_WEITERGEHENEFFEKT_ST2));
        add(new MehrfachSchlagenEffekt(ZahlensammlungSpielfigur.DURSTIGESDROMEDAR_MEHRFACHSCHLAGENEFFEKT_ST2));
        add(new SofortLoslaufenEffekt());
    }}, Zahlensammlung.KARTE_STUFE_2),

    DURSTIGESDROMEDA_ST3(StringsammlungSpielfigur.DURSTIGESDROMEDAR_NAME, StringsammlungSpielfigur.DURSTIGESDROMEDAR_BESCHREIBUNG, ZahlensammlungSpielfigur.DURSTIGESDROMEDAR_LEBENSPUNKTE_ST3, ZahlensammlungSpielfigur.DURSTIGESDROMEDAR_ANGRIFFSPUNKTE_ST3, ZahlensammlungSpielfigur.DURSTIGESDROMEDAR_MANAKOSTEN, new ArrayList<>() {{
        add(new WeiterGehenEffekt(ZahlensammlungSpielfigur.DURSTIGESDROMEDAR_WEITERGEHENEFFEKT_ST3));
        add(new MehrfachSchlagenEffekt(ZahlensammlungSpielfigur.DURSTIGESDROMEDAR_MEHRFACHSCHLAGENEFFEKT_ST3));
        add(new SofortLoslaufenEffekt());
    }}, Zahlensammlung.KARTE_STUFE_3),

    LAESSIGESLAMA_ST1(StringsammlungSpielfigur.LAESSIGESLAMA_NAME, StringsammlungSpielfigur.LAESSIGESLAMA_BESCHREIBUNG, ZahlensammlungSpielfigur.LAESSIGESLAMA_LEBENSPUNKTE_ST1, ZahlensammlungSpielfigur.LAESSIGESLAMA_ANGRIFFSPUNKTE_ST1, ZahlensammlungSpielfigur.LAESSIGESLAMA_MANAKOSTEN, new ArrayList<>() {{
        add(new ReichweiteEffekt(ZahlensammlungSpielfigur.LAESSIGESLAMA_REICHWEITEEFFEKT_ST1));
    }},
            Zahlensammlung.KARTE_STUFE_1),

    LAESSIGESLAMA_ST2(StringsammlungSpielfigur.LAESSIGESLAMA_NAME, StringsammlungSpielfigur.LAESSIGESLAMA_BESCHREIBUNG, ZahlensammlungSpielfigur.LAESSIGESLAMA_LEBENSPUNKTE_ST2, ZahlensammlungSpielfigur.LAESSIGESLAMA_ANGRIFFSPUNKTE_ST2, ZahlensammlungSpielfigur.LAESSIGESLAMA_MANAKOSTEN, new ArrayList<>() {{
        add(new ReichweiteEffekt(ZahlensammlungSpielfigur.LAESSIGESLAMA_REICHWEITEEFFEKT_ST2));
    }},
            Zahlensammlung.KARTE_STUFE_2),

    LAESSIGESLAMA_ST3(StringsammlungSpielfigur.LAESSIGESLAMA_NAME, StringsammlungSpielfigur.LAESSIGESLAMA_BESCHREIBUNG, ZahlensammlungSpielfigur.LAESSIGESLAMA_LEBENSPUNKTE_ST3, ZahlensammlungSpielfigur.LAESSIGESLAMA_ANGRIFFSPUNKTE_ST3, ZahlensammlungSpielfigur.LAESSIGESLAMA_MANAKOSTEN, new ArrayList<>() {{
        add(new ReichweiteEffekt(ZahlensammlungSpielfigur.LAESSIGESLAMA_REICHWEITEEFFEKT_ST3));
    }},
            Zahlensammlung.KARTE_STUFE_3),

    HUNGRIGESCHWARZEWITWE_ST1(StringsammlungSpielfigur.HUNGRIGESCHWARZEWITWE_NAME, StringsammlungSpielfigur.HUNGRIGESCHWARZEWITWE_BESCHREIBUNG, ZahlensammlungSpielfigur.HUNGRIGESCHWARZEWITWE_LEBENSPUNKTE_ST1, ZahlensammlungSpielfigur.HUNGRIGESCHWARZEWITWE_ANGRIFFSPUNKTE_ST1, ZahlensammlungSpielfigur.HUNGRIGESCHWARZEWITWE_MANAKOSTEN, new ArrayList<>() {{
        add(new WeiterGehenEffekt(ZahlensammlungSpielfigur.HUNGRIGESCHWARZEWITWE_WEITERGEHENEFFEKT_ST1));
    }}, Zahlensammlung.KARTE_STUFE_1),

    HUNGRIGESCHWARZEWITWE_ST2(StringsammlungSpielfigur.HUNGRIGESCHWARZEWITWE_NAME, StringsammlungSpielfigur.HUNGRIGESCHWARZEWITWE_BESCHREIBUNG, ZahlensammlungSpielfigur.HUNGRIGESCHWARZEWITWE_LEBENSPUNKTE_ST2, ZahlensammlungSpielfigur.HUNGRIGESCHWARZEWITWE_ANGRIFFSPUNKTE_ST2, ZahlensammlungSpielfigur.HUNGRIGESCHWARZEWITWE_MANAKOSTEN, new ArrayList<>() {{
        add(new WeiterGehenEffekt(ZahlensammlungSpielfigur.HUNGRIGESCHWARZEWITWE_WEITERGEHENEFFEKT_ST2));
    }}, Zahlensammlung.KARTE_STUFE_2),

    HUNGRIGESCHWARZEWITWE_ST3(StringsammlungSpielfigur.HUNGRIGESCHWARZEWITWE_NAME, StringsammlungSpielfigur.HUNGRIGESCHWARZEWITWE_BESCHREIBUNG, ZahlensammlungSpielfigur.HUNGRIGESCHWARZEWITWE_LEBENSPUNKTE_ST3, ZahlensammlungSpielfigur.HUNGRIGESCHWARZEWITWE_ANGRIFFSPUNKTE_ST3, ZahlensammlungSpielfigur.HUNGRIGESCHWARZEWITWE_MANAKOSTEN, new ArrayList<>() {{
        add(new WeiterGehenEffekt(ZahlensammlungSpielfigur.HUNGRIGESCHWARZEWITWE_WEITERGEHENEFFEKT_ST3));
    }}, Zahlensammlung.KARTE_STUFE_3),

    IGNORANTEIGELZECKE_ST1(StringsammlungSpielfigur.IGNORANTEIGELZECKE_NAME, StringsammlungSpielfigur.IGNORANTEIGELZECKE_BESCHREIBUNG, ZahlensammlungSpielfigur.IGNORANTEIGELZECKE_LEBENSPUNKTE_ST1, ZahlensammlungSpielfigur.IGNORANTEIGELZECKE_ANGRIFFSPUNKTE_ST1, ZahlensammlungSpielfigur.IGNORANTEIGELZECKE_MANAKOSTEN, new ArrayList<>() {{
        add(new MehrfachSchlagenEffekt(ZahlensammlungSpielfigur.IGNORANTEIGELZECKE_MEHRFACHSCHLAGENEFFEKT));
    }}, Zahlensammlung.KARTE_STUFE_1),

    IGNORANTEIGELZECKE_ST2(StringsammlungSpielfigur.IGNORANTEIGELZECKE_NAME, StringsammlungSpielfigur.IGNORANTEIGELZECKE_BESCHREIBUNG, ZahlensammlungSpielfigur.IGNORANTEIGELZECKE_LEBENSPUNKTE_ST2, ZahlensammlungSpielfigur.IGNORANTEIGELZECKE_ANGRIFFSPUNKTE_ST2, ZahlensammlungSpielfigur.IGNORANTEIGELZECKE_MANAKOSTEN, new ArrayList<>() {{
        add(new MehrfachSchlagenEffekt(ZahlensammlungSpielfigur.IGNORANTEIGELZECKE_MEHRFACHSCHLAGENEFFEKT));
    }}, Zahlensammlung.KARTE_STUFE_2),

    IGNORANTEIGELZECKE_ST3(StringsammlungSpielfigur.IGNORANTEIGELZECKE_NAME, StringsammlungSpielfigur.IGNORANTEIGELZECKE_BESCHREIBUNG, ZahlensammlungSpielfigur.IGNORANTEIGELZECKE_LEBENSPUNKTE_ST3, ZahlensammlungSpielfigur.IGNORANTEIGELZECKE_ANGRIFFSPUNKTE_ST3, ZahlensammlungSpielfigur.IGNORANTEIGELZECKE_MANAKOSTEN, new ArrayList<>() {{
        add(new MehrfachSchlagenEffekt(ZahlensammlungSpielfigur.IGNORANTEIGELZECKE_MEHRFACHSCHLAGENEFFEKT));
    }}, Zahlensammlung.KARTE_STUFE_3);

    private final String name;
    private final String beschreibung;
    private final int lebenspunkte;
    private final int angriffspunkte;
    private final int manakosten;
    private final ArrayList<Effekt> effekte;
    private final int stufe;


    MinionKarte(String name, String beschreibung, int lebenspunkte, int angriffspunkte, int manakosten, ArrayList<Effekt> effekte, int stufe) {
        this.name = name;
        this.beschreibung = beschreibung;
        this.lebenspunkte = lebenspunkte;
        this.angriffspunkte = angriffspunkte;
        this.manakosten = manakosten;
        this.effekte = effekte;
        this.stufe = stufe;
    }

    /**
     * Gibt eine zufaellige Minionkarte zurueck.
     *
     * @return zufaellige Minionkarte
     */
    public static MinionKarte getRandomMinionKarte() {
        return MinionKarte.values()[MeinIO.getRandomInt(MinionKarte.values().length - 1)];
    }

    /**
     * Verbessert einen Minion.
     * Ist die Verbesserung nicht moeglich, wird der selbe Minion zurueckgegeben
     *
     * @param zuVerbessern
     * @return verbesserter Minion
     */
    public static Minion getUpgradedMinionKarte(Minion zuVerbessern) {
        MinionKarte[] alleMinionKarten = MinionKarte.values();
        for (int i = 0; i < alleMinionKarten.length; i++) {
            if (alleMinionKarten[i].getName().equals(zuVerbessern.getName()) && alleMinionKarten[i].getStufe() == zuVerbessern.getStufe() && alleMinionKarten[i].getStufe() < Zahlensammlung.HOECHSTE_KARTEN_STUFE) {
                return new Minion(alleMinionKarten[i + 1]);
            }
        }
        return zuVerbessern;
    }

    public int getLebenspunkte() {
        return lebenspunkte;
    }

    public int getAngriffspunkte() {
        return angriffspunkte;
    }

    public ArrayList<Effekt> getEffekte() {
        return effekte;
    }

    public String getName() {
        return name;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public int getManakosten() {
        return manakosten;
    }

    public int getStufe() {
        return stufe;
    }
}
