package resources.exceptions;

import resources.interfaces.StringsammlungException;

/**
 * Diese Exception wird ausgeloest, wenn eine Spielfigur angreifen soll, aber keinen freien Angriff mehr hat.
 */
public class NichtImAngriffsradiusException extends Exception {
    public NichtImAngriffsradiusException() {
        super(StringsammlungException.NICHT_IM_ANGRIFFSRADIUS);
    }
}
