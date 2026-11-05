package resources.exceptions;

import resources.interfaces.StringsammlungException;

/**
 * Diese Exception wird ausgeloest, wenn eine Spielfigur nicht auf ein Spielfeld gesetzt werden kann, weil dieses nicht neben einer eigenen Spielfigur liegt.
 */
public class FeldIstKeinNachbarException extends Exception {

    public FeldIstKeinNachbarException() {
        super();
    }

    public FeldIstKeinNachbarException(int hoehe, int breite) {
        super(String.format(StringsammlungException.FELD_NICHT_BENACHBART, hoehe, breite));
    }
}
