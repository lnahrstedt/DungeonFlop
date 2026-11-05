package resources.interfaces;

/**
 * Innerhalb dieses Interfaces werden die einzelnen Bezeichnungen und Beschreibungen der Talente abgespeichert.
 */
public interface StringsammlungTalent {

    String GOLD_NAME = "Steinreich";
    String GOLD_BESCHREIBUNG = String.format("Du erhaelst %d%% mehr Gold", ZahlensammlungTalent.GOLD_TALENT_WERT);

    String ERFAHRUNG_NAME = "Wissbegierig";
    String ERFAHRUNG_BESCHREIBUNG = String.format("Du erhaelst %d%% mehr Erfahrung", ZahlensammlungTalent.ERFAHRUNGS_TALENT_WERT);

    String HELD_ANGRIFF_NAME = "Eisenfaust";
    String HELD_ANGRIFF_BESCHREIBUNG = String.format("Dein Held erhaelt +%d Angriff", ZahlensammlungTalent.ANGRIFF_VERBESSERUNG_TALENT);

    String HELD_LEBEN_NAME = "Staehlerne Haut";
    String HELD_LEBEN_BESCHREIBUNG = String.format("Dein Held erhaelt +%d Leben", ZahlensammlungTalent.LEBEN_VERBESSERUNG_TALENT);

    String KARTEN_AUFWERTUNG_NAME = "Kartenchaos";
    String KARTEN_AUFWERTUNG_BESCHREIBUNG = String.format("%d zufaellige Karten werden aufgewertet", ZahlensammlungTalent.ZUFAELLIGE_KARTEN_VERBESSERUNG_TALENT_ANZAHL);
}
