package resources.exceptions;

import resources.interfaces.StringsammlungException;

/**
 * Diese Exception wird ausgeloest, wenn eine Spielfigur angreifen soll, aber keinen freien Angriff mehr hat.
 */
public class KeineAngriffeMehrException extends Exception {
    public KeineAngriffeMehrException() {
        super(StringsammlungException.KEINE_ANGRIFFE_MEHR_VERFUEGBAR);
    }
}
