package control.model;

import model.karte.Karte;
import model.karte.Kartenverwaltung;

import java.util.Collections;

/**
 * Diese Klasse steuert bzw. verwaltet alle Vorgaenge im Zusammenhang der Kartenverwaltung.
 */
public class KartenverwaltungsController {

    /**
     * Diese Methode bewegt eine einzelne Karte von dem Handstapel auf den Ablagestapel.
     *
     * @param karte
     * @param kartenverwaltung
     */
    public static void bewegeKarte(Karte karte, Kartenverwaltung kartenverwaltung) {
        int zuBewegendeKarte = sucheKarteHeraus(karte, kartenverwaltung);
        kartenverwaltung.getHandkarten().remove(zuBewegendeKarte);
        kartenverwaltung.getAblagestapel().add(karte);
    }

    /**
     * Diese Methode gibt den Index der zu suchenden Karte zurueck.
     *
     * @param karte
     * @param kartenverwaltung
     * @return index
     */
    private static int sucheKarteHeraus(Karte karte, Kartenverwaltung kartenverwaltung) {
        return kartenverwaltung.getHandkarten().indexOf(karte);
    }

    /**
     * Diese Methode bewegt alle Karten von der Hand zum Ablagestapel.
     *
     * @param kartenverwaltung
     */
    public static void bewegeKartenVonHandZuAblage(Kartenverwaltung kartenverwaltung) {
        kartenverwaltung.getAblagestapel().addAll(kartenverwaltung.getHandkarten());
        kartenverwaltung.getHandkarten().clear();
    }

    /**
     * Diese Methode bewegt alle Karten des Ablagestapels zum Aufnahmestapel. Vor der Bewegung werden alle Karten gemischt.
     *
     * @param kartenverwaltung
     */
    public static void bewegeKartenVonAblageZuAufnahme(Kartenverwaltung kartenverwaltung) {
        if (kartenverwaltung.getAufnahmestapel().isEmpty()) {
            Collections.shuffle(kartenverwaltung.getAblagestapel());
            kartenverwaltung.getAufnahmestapel().addAll(kartenverwaltung.getAblagestapel());
            kartenverwaltung.getAblagestapel().clear();
        }
    }

    /**
     * Diese Methode fuellt die Hand auf, indem 5 Karten von dem Aufnahmestapel zur Hand bewegt werden.
     *
     * @param kartenverwaltung
     */
    public static void bewegeKartenVonAufnahmeZuHand(Kartenverwaltung kartenverwaltung, int kartenProRunde) {
        while (kartenverwaltung.getHandkarten().size() != kartenProRunde) {
            if (!kartenverwaltung.getAufnahmestapel().isEmpty()) {
                kartenverwaltung.getHandkarten().add(kartenverwaltung.getAufnahmestapel().pop());
            } else {
                if (kartenverwaltung.getAblagestapel().isEmpty()) {
                    return;
                }
                bewegeKartenVonAblageZuAufnahme(kartenverwaltung);
            }
        }
    }
}