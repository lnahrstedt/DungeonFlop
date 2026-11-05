package utilities;

import resources.interfaces.StringsammlungException;

/**
 * Diese Klasse dient als Helferklasse fuer alles rund um Threads.
 */
public class ThreadHelferKlasse {

    /**
     * Pausiert die Ausfuehrung des aktuellen Threads.
     *
     * @param dauer
     */
    public static void schlafe(int dauer) {
        try {
            Thread.sleep(dauer);
        } catch (InterruptedException e) {
            MeinIO.stringAusgeben(StringsammlungException.INTERRUPTED);
            e.printStackTrace();
        }
    }
}
