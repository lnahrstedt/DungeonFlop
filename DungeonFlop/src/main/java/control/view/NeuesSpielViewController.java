package control.view;

import control.model.SpielController;
import control.netzwerk.ServerThreadController;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import resources.enums.Schwierigkeitsgrad;
import resources.enums.Spielerklasse;
import resources.interfaces.StringsammlungAlert;
import resources.interfaces.StringsammlungGUI;

import java.util.Optional;

/**
 * Diese Klasse steuert bzw. verwaltet alle Vorgaenge im Zusammenhang des NeuesSpielViews.
 */
public class NeuesSpielViewController {

    /**
     * Diese Methode stellt sicher, dass der Spieler bei der Spielerstellung alle Felder korrekt ausfuellt.
     * Falls er ein Feld nicht ausfuellt, unterrichtet ein Alert ihn ueber das fehlende Feld.
     * Gibt der Spieler alles regelkonform ein, wird er durch einen Dialog begruesst und die von ihm eingegeben Daten werden angezeigt.
     * Ausserdem bietet der Dialog dem Spieler an, zur Story wechseln.
     * Sollte der Spieler dies wuenschen, wird zur Scene von StoryView gewechselt.
     *
     * @param spielername
     * @param spielerklasse
     * @param schwierigkeitsgrad
     */
    public static void pruefeEingaben(String spielername, Spielerklasse spielerklasse, Schwierigkeitsgrad schwierigkeitsgrad) {

        if (spielername.isEmpty()) {
            AlertFabrik.generiereAlert(Alert.AlertType.ERROR, StringsammlungGUI.UNVOLLSTAENDIGE_EINGABE_TITEL, StringsammlungGUI.UNVOLLSTAENDIGE_EINGABE_SPIELERNAME).show();
            return;
        }

        if (spielerklasse == null) {
            AlertFabrik.generiereAlert(Alert.AlertType.ERROR, StringsammlungGUI.UNVOLLSTAENDIGE_EINGABE_TITEL, StringsammlungGUI.UNVOLLSTAENDIGE_EINGABE_SPIELERKLASSE).show();
            return;
        }

        if (schwierigkeitsgrad == null) {
            AlertFabrik.generiereAlert(Alert.AlertType.ERROR, StringsammlungGUI.UNVOLLSTAENDIGE_EINGABE_TITEL, StringsammlungGUI.UNVOLLSTAENDIGE_EINGABE_SCHWIERIGKEITSGRAD).show();
            return;
        }

        Alert alert = AlertFabrik.generiereAlert(Alert.AlertType.CONFIRMATION, StringsammlungAlert.NEUES_SPIEL_ERSTELLT_TITEL, StringsammlungAlert.NEUES_SPIEL_ERSTELLT_NACHRICHT_NAME + spielername + StringsammlungAlert.NEUES_SPIEL_ERSTELLT_NACHRICHT_SPIELERKLASSE + spielerklasse.getKlassenbezeichnung() + StringsammlungAlert.NEUES_SPIEL_ERSTELLT_NACHRICHT_SCHWIERIGKEITSGRAD + schwierigkeitsgrad.getBezeichnung() + StringsammlungAlert.NEUES_SPIEL_ERSTELLT_NACHRICHT_ABSCHLUSS);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent()) {
            if (result.get() == ButtonType.OK) {
                SpielController.erstelleSpielwelt(spielername, spielerklasse, schwierigkeitsgrad);
                ServerThreadController.getInstance().starteServerThread();
                wechselZuStoryScene();
            } else {
                alert.close();
            }
        }
    }


    /**
     * Sofern vom Spieler gewuenscht, wechselt diese Methode die aktuelle Scene zu der des StoryViews.
     */
    private static void wechselZuStoryScene() {
        ViewWechsler.wechselZuStoryViewMitZurueckOption();
    }

}
