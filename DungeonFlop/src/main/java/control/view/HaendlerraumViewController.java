package control.view;

import control.model.raum.HaendlerraumController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import model.Spielwelt;
import model.raum.Haendlerraum;
import resources.exceptions.NichtGenugGoldException;
import resources.interfaces.PfadSammlung;
import resources.interfaces.StringsammlungAlert;
import utilities.MusikUndSoundController;

/**
 * Diese Klasse stellt dem HaendlerraumView Methoden zur Verfuegung.
 * Zum Beispiel verarbeitet sie die Eingaben des Spielers.
 */
public class HaendlerraumViewController {

    /**
     * Erstellt die Kaufaktion anhand des spezifischen Items.
     * Ruft dabei den Haendlerraumcontroller auf.
     *
     * @param kaufKnopf    des Items
     * @param spielerGold
     * @param kategorie
     * @param position
     * @param haendlerraum
     * @return EventHandler
     */
    public static EventHandler<ActionEvent> generiereKaufAktion(Button kaufKnopf, Text spielerGold, int kategorie, int position, Haendlerraum haendlerraum) {
        return event -> {
            try {
                HaendlerraumController.kaufe(kategorie, position, haendlerraum);
                //Knopf wird deaktiviert nach dem Kaufen des Items
                kaufKnopf.setDisable(true);
                //Spielergold wird aktualisiert
                spielerGold.setText(String.valueOf(Spielwelt.getInstance().getProtagonist().getGold()));
                //Ein Soundeffekt wird ausgeloest
                MusikUndSoundController.spieleSoundeffekt(PfadSammlung.PFAD_MUENZEN_SOUNDEFFEKT);
            } catch (NichtGenugGoldException exception) {
                //Hat der Spieler nicht genug Gold, wird dieser Alert ausgeloest
                AlertFabrik.generiereAlert(Alert.AlertType.WARNING, StringsammlungAlert.NICHT_GENUG_GOLD, exception.getMessage()).showAndWait();
            }
        };
    }
}
