package resources.exceptions;

import model.Spielwelt;
import model.raum.Raum;

/**
 * Diese Exception wird ausgeloest, wenn ein Raum nicht benachbart, also nicht betretbar ist
 */
public class RaumIstKeinNachbarException extends Exception {
    public RaumIstKeinNachbarException(Raum raum, String message) {

        super("\"" + raum.getName() + "\"" + message + "\"" + Spielwelt.getInstance().getSpielerEbenePosition().getSpielerRaumPosition().getName() + "\"!");
    }
}
