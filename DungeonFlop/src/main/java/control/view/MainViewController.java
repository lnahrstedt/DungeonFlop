package control.view;

import javafx.event.EventHandler;
import javafx.stage.WindowEvent;

/**
 * Diese Klasse steuert bzw. verwaltet alle Vorgaenge im Zusammenhang des MainViews.
 * Zum Beispiel verarbeitet sie die Eingaben des Spielers.
 */
public class MainViewController {

    /**
     * Versucht der Spieler das Fenster mittels des "X" zu schliessen, wird diese Methode aufgerufen.
     * Der Spieler wird durch einen Dialog aufgefordert, der Beendigung des Spiels zuzustimmen.
     *
     * @return eventHandler
     */
    public static EventHandler<WindowEvent> oeffneBestaetigungsDialog() {
        return windowEvent -> {
            AlertFabrik.generiereSchliessenAlert();
            windowEvent.consume();
        };
    }
}