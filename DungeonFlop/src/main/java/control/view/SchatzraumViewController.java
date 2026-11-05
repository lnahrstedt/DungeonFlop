package control.view;

import control.model.raum.SchatzraumController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import model.Spielwelt;
import model.raum.Schatzraum;

/**
 * Diese Klasse enthaelt die Aktion fuer das Nehmen einer Karte.
 */
public class SchatzraumViewController {

    /**
     * Erstellt die Schatzaktion anhand einer spezifischen Karte des Schatzes.
     *
     * @param schatzraum
     * @param knopf
     * @param position
     * @return
     */
    public static EventHandler<ActionEvent> generiereSchatzAktion(Schatzraum schatzraum, Button knopf, int position) {
        return event -> {
            SchatzraumController.karteNehmen(position, schatzraum, Spielwelt.getInstance().getProtagonist());
            //Knopf wird deaktiviert
            knopf.setDisable(true);
        };
    }
}
