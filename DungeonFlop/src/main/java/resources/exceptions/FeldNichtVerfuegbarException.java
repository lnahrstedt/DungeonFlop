package resources.exceptions;

import model.Spielfigur;
import resources.interfaces.StringsammlungException;

/**
 * Diese Exception wird ausgeloest, wenn eine Spielfigur nicht auf ein Spielfeld gesetzt werden kann, weil dieses nicht verfuegbar ist..
 */
public class FeldNichtVerfuegbarException extends Exception {

    public FeldNichtVerfuegbarException() {
        super();
    }

    public FeldNichtVerfuegbarException(int hoehe, int breite, Spielfigur spielfigur) {
        super(String.format(StringsammlungException.FELD_NICHT_VERFUEGBAR_SPIELFIGUR, hoehe, breite, spielfigur.getName()));
    }

    public FeldNichtVerfuegbarException(int hoehe, int breite) {
        super(String.format(StringsammlungException.FELD_NICHT_VERFUEGBAR, hoehe, breite));
    }
}
