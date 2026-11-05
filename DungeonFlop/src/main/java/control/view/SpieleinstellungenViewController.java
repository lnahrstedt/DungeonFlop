package control.view;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import utilities.MusikUndSoundController;
import view.Buehne;

/**
 * Diese Klasse steuert bzw. verwaltet alle Vorgaenge im Zusammenhang des SpieleinstellungenViews.
 * Zum Beispiel verarbeitet sie die Eingaben des Spielers.
 */
public class SpieleinstellungenViewController {

    /**
     * Diese Methode ist fuer die Regulierung der Lautstaerke der Hintergrundmusik ueber den Slider verantwortlich.
     *
     * @param slider
     */
    public static void setMusikLautstaerke(Slider slider) {
        slider.valueProperty().addListener((observableValue, number, t1) -> MusikUndSoundController.setVolumeMusik(t1.doubleValue()));
    }

    /**
     * Diese Methode ist fuer die Regulierung der Lautstaerke der Soundeffekte ueber den Slider verantwortlich.
     *
     * @param slider
     */
    public static void setSoundLautstaerke(Slider slider) {
        slider.valueProperty().addListener((observableValue, number, t1) -> MusikUndSoundController.setVolumeSounds(t1.doubleValue()));
    }

    /**
     * Beim Aktivieren der CheckBox "Vollbild" wird durch diese Methode in den Vollbildmodus gewechselt.
     *
     * @param checkBox
     */
    public static void setFullScreen(CheckBox checkBox) {
        checkBox.selectedProperty().addListener((observableValue, aBoolean, t1) -> Buehne.getInstance().getStage().setFullScreen(t1));
    }
}
