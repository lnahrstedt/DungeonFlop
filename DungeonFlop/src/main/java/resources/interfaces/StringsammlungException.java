package resources.interfaces;

/**
 * In diesem Interface werden alle Strings im Zusammenhang mit Exceptions gespeichert.
 */
public interface StringsammlungException {

    String NICHT_GENUG_GOLD = "Du hast nicht %d Gold";
    String FELD_NICHT_VERFUEGBAR = "Feld %d , %d ist nicht verfuegbar oder leer.";
    String FELD_NICHT_VERFUEGBAR_SPIELFIGUR = "Feld %d , %d ist nicht verfuegbar oder leer fuer Spielfigur %s.";
    String FELD_NICHT_BENACHBART = "Feld %d , %d grenzt nicht an einer eigenen Spielfigur.";
    String BEWEGUNG = "Keine Bewegungen mehr verfuegbar.";
    String KEINE_ANGRIFFE_MEHR_VERFUEGBAR = "Keine Angriffe mehr verfuegbar.";
    String NICHT_IM_ANGRIFFSRADIUS = "Gegner befindet sich nicht im Angriffsradius.";
    String RAUM_KEIN_NACHBAR_EXCEPTION_MESSAGE = " ist kein Nachbarraum von ";
    String UNGUELTIGE_AUFWERTUNG = "%s Stufe %d kann nicht aufgewertet werden";
    String KNOTENTYP_WIRD_NICHT_UNTERSTUETZT = "Dieser Knotentyp wird nicht unterstuetzt: ";
    String SOL_EXCEPTION = "Nicht genug Sol";

    String INTERRUPTED = "Es gab eine InterruptedException!";
    String NICHT_IM_AKTIONSRADIUS = "Das ausgewaehlte Feld liegt nicht im Aktionsradius.";
}
