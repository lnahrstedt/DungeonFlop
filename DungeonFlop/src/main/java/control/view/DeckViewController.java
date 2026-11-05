package control.view;

import control.netzwerk.ServerThreadController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import model.netzwerk.NetzwerkFlagContainer;
import resources.enums.ServerClientKommunikation;
import resources.interfaces.StringsammlungAlert;

/**
 * Diese Klasse stellt dem DeckView Methoden zur Verfuegung.
 * Zum Beispiel verarbeitet sie die Eingaben des Spielers.
 */
public class DeckViewController {

    /**
     * Diese Methode wechselt bei Knopfdruck die Szene zur DeckView.
     *
     * @return EventHandler
     */
    public static EventHandler<ActionEvent> onDeckButtonClick() {
        return actionEvent -> {
            if (!NetzwerkFlagContainer.binIchClient()) {
                ServerThreadController.getInstance().sendeDaten(ServerClientKommunikation.SERVER_INTERAGIERT_MIT_DECK);
                ViewWechsler.wechselZuDeckViewMitZurueckOption();
            } else {
                AlertFabrik.generiereAlert(Alert.AlertType.WARNING, StringsammlungAlert.CLIENT_BUTTONS_DEAKTIVIERT_TITEL, StringsammlungAlert.CLIENT_BUTTONS_DEAKTIVIERT_MESSAGE_ALLGEMEIN).show();
            }
        };
    }
}
