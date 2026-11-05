package resources.exceptions;

import resources.interfaces.StringsammlungException;

/**
 * Diese Exception wird ausgeloest, wenn der Spieler fuer eine Aktion nicht genug Gold zur Verfuegung hat.
 */
public class NichtGenugGoldException extends Exception {

    public NichtGenugGoldException(int gold) {
        super(String.format(StringsammlungException.NICHT_GENUG_GOLD, gold));
    }
}
