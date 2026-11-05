package resources.exceptions;

import resources.interfaces.StringsammlungException;

/**
 * Diese Exception wird ausgeloest, wenn sich eine Spielfigur bewegen soll, sie jedoch keine freien Bewegungen mehr hat.
 */
public class BewegungsException extends Exception {
    public BewegungsException() {
        super(StringsammlungException.BEWEGUNG);
    }
}
