package control.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.talent.Talent;

/**
 * Diese Klasse steuert bzw. verwaltet alle Vorgaenge im Zusammenhang der StufenaufstiegView.
 * Zum Beispiel verarbeitet sie die Eingaben des Spielers.
 */
public class StufenaufstiegViewController {

    /**
     * Diese Methode reagiert auf den Klick auf ein Talent in der StufenaufstiegView.
     *
     * @param talent
     * @return
     */
    public static EventHandler<ActionEvent> getStufenaufstiegEventhandler(Talent talent) {
        return event -> {
            talent.ausfuehren();
            ViewWechsler.wechselZuEbenenLayoutView();
        };
    }
}
