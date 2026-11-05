package resources.exceptions;

import resources.interfaces.StringsammlungException;

/**
 * Diese Exception wird ausgeloest, wenn eine Spielfigur auf ein Feld bewegt werden soll, das ausserhalb der Schrittweite liegt.
 */
public class NichtImAktionsradiusException extends Exception {
    public NichtImAktionsradiusException() {
        super(StringsammlungException.NICHT_IM_AKTIONSRADIUS);
    }
}
