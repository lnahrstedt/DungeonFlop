package control.view;

import control.netzwerk.ServerThreadController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.HauptSpieler;
import model.Spielwelt;
import resources.enums.ServerClientKommunikation;
import view.*;

/**
 * Diese Klasse steuert bzw. verwaltet alle Vorgaenge im Zusammenhang des Zurueckknopfs.
 * Zum Beispiel verarbeitet sie die Eingaben des Spielers.
 */
public class ZurueckknopfController {

    /**
     * Diese Methode verarbeitet den Klick auf das Zuruecksymbol.
     * Beim Klick wird die auf die zuvor aufgerufene Scene gewechselt.
     *
     * @return actionEvent
     */
    public static EventHandler<ActionEvent> geheZurueck() {
        return actionEvent -> {
            Viewable letztesElementAufStack = Zurueckknopf.getInstance().getElementZurueckknopf();
            if (letztesElementAufStack instanceof EbenenLayoutView) {
                ServerThreadController.getInstance().sendeDaten(new HauptSpieler(Spielwelt.getInstance().getProtagonist()));
                ViewWechsler.wechselZuEbenenLayoutView();
            } else if (letztesElementAufStack instanceof HaendlerraumView || letztesElementAufStack instanceof SchmiedraumView || letztesElementAufStack instanceof SchatzraumView) {
                ServerThreadController.getInstance().sendeDaten(ServerClientKommunikation.SERVER_INTERAGIERT_MIT_RAUM);
                Buehne.getInstance().setRootOfScene(letztesElementAufStack);
            } else {
                Buehne.getInstance().setRootOfScene(letztesElementAufStack);
            }
        };
    }
}
