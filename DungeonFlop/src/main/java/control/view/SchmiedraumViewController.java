package control.view;

import control.model.raum.SchmiedraumController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import model.Spielwelt;
import model.karte.Karte;
import model.raum.Schmiedraum;
import resources.exceptions.NichtGenugGoldException;
import resources.interfaces.PfadSammlung;
import resources.interfaces.StringsammlungAlert;
import resources.interfaces.Zahlensammlung;
import utilities.MusikUndSoundController;
import view.ItemView;

/**
 * Diese Klasse beeinhaltet die Aktion fuer das Aufwerten einer Karte.
 */
public class SchmiedraumViewController {

    /**
     * Erstellt die Aufwertaktion anhand der spezifischen Karte.
     * Tauscht die Ansicht direkt mit der der verbesserten Karte aus.
     *
     * @param schmiedraum
     * @param karte
     * @param spielerGold
     * @return
     */
    public static EventHandler<ActionEvent> getSchmiedKnopfAktion(Button schmiedKnopf, Schmiedraum schmiedraum, Karte karte, Text spielerGold, BorderPane root) {
        return event -> {
            try {
                Karte aufgewerteteKarte = SchmiedraumController.aufwerten(schmiedraum, karte);
                Spielwelt.getInstance().getProtagonist().getDeck().remove(karte);
                Spielwelt.getInstance().getProtagonist().getDeck().add(aufgewerteteKarte);
                MusikUndSoundController.spieleSoundeffekt(PfadSammlung.PFAD_SCHMIED_SOUNDEFFEKT);
                spielerGold.setText(String.valueOf(Spielwelt.getInstance().getProtagonist().getGold()));
                if (karte.getStufe() == Zahlensammlung.ZWEIT_HOECHSTE_KARTEN_STUFE) {
                    schmiedKnopf.setDisable(true);
                } else {
                    root.setRight(ItemView.erstelleSchmiedAnsicht(aufgewerteteKarte));
                    schmiedKnopf.setOnAction(getSchmiedKnopfAktion(schmiedKnopf, schmiedraum, aufgewerteteKarte, spielerGold, root));
                    schmiedKnopf.setOnMouseEntered(ItemViewController.schmiedHoverEnter(aufgewerteteKarte, root));
                    schmiedKnopf.setOnMouseExited(ItemViewController.itemHoverExitRight(root));
                }
            } catch (NichtGenugGoldException nichtGenugGoldException) {
                AlertFabrik.generiereAlert(Alert.AlertType.WARNING, StringsammlungAlert.NICHT_GENUG_GOLD, nichtGenugGoldException.getMessage()).showAndWait();
            }
        };
    }
}
