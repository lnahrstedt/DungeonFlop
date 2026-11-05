package control.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * Diese Klasse steuert bzw. verwaltet alle Vorgaenge im Zusammenhang der StoryView.
 * Zum Beispiel verarbeitet sie die Eingaben des Spielers.
 */

public class StoryViewController {

    /**
     * Diese Methode wechselt zur Scene der EbenenLayoutView.
     *
     * @return EventHandler
     */
    public static EventHandler<ActionEvent> wechselZuLayoutEbene0Scene() {
        return actionEvent -> ViewWechsler.wechselZuEbenenLayoutView();
    }
}
