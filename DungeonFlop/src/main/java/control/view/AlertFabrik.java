package control.view;

import control.netzwerk.NetzwerkController;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import resources.interfaces.StringsammlungAlert;
import view.Buehne;

import java.util.Optional;

/**
 * Diese Fabrik dient der vereinfachten Erstellung von Dialogen.
 */
public class AlertFabrik {
    /**
     * Diese Methode dient der Erstellung verschiedenster Dialoge.
     *
     * @param alertType
     * @param titel
     * @param nachricht
     * @return alert
     */
    public static Alert generiereAlert(Alert.AlertType alertType, String titel, String nachricht) {
        Alert alert = new Alert(alertType);
        alert.setTitle(titel);
        alert.setHeaderText(null);
        alert.setContentText(nachricht);
        alert.initOwner(Buehne.getInstance().getStage());
        return alert;
    }

    /**
     * Diese Methode erstellt den Dialog, der den Nutzer befragt, ob er das Spiel wirklich verlassen moechte.
     */
    public static void generiereSchliessenAlert() {
        Alert alert = AlertFabrik.generiereAlert(
                Alert.AlertType.CONFIRMATION,
                StringsammlungAlert.SPIEL_VERLASSEN_TITEL,
                StringsammlungAlert.SPIEL_VERLASSEN_TEXT
        );
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent()) {
            if (result.get() == ButtonType.OK) {
                NetzwerkController.gegenseiteUeberSchliessungInformieren();
                System.exit(0);
            } else {
                alert.close();
            }
        }
    }

}
