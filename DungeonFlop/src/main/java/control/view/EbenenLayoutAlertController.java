package control.view;

import control.model.EbenenController;
import control.model.raum.RaumController;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import model.Spielwelt;
import model.raum.Gegnerraum;
import model.raum.Raum;
import resources.interfaces.StringsammlungAlert;
import view.Buehne;


/**
 * Diese Klasse buendelt alle in EbenenLayoutView benoetigten Dialoge.
 */
public class EbenenLayoutAlertController {

    /**
     * Wird diese Methode aufgerufen, hat der Spieler den Raum ausgewaehlt, in dem er sich aktuell befindet.
     * Nun werden weitere Eventualitaeten untersucht.
     * Zum Beispiel wird ueberprueft, ob der gewaehlte Raum ein leerer Raum, ein Leiterraum oder ein Gegnerraum ist.
     * Auf Grundlage dieser Daten wird die entsprechende Methode aufgerufen, die den Dialog erstellt.
     * Sofern der ausgewaehlte Raum leer ist, wird ein Dialog erstellt, der dem Spieler mitteilt, dass mit diesem Raum keine Interaktion moeglich ist.
     *
     * @param raum
     * @return alert, der durch die Abfragen an die aktuelle Situation angepasst ist.
     */
    public static Alert generiereLayoutAlert(Raum raum) {
        ButtonType abbrechenButtonType = new ButtonType(StringsammlungAlert.LAYOUT_ABFRAGE_ABBRECHEN, ButtonType.CANCEL.getButtonData());

        // Raum ist ein Gegnerraum.
        if (raum instanceof Gegnerraum) {
            return generiereAlertAktuellerRaumGegnerraum(raum, abbrechenButtonType);
        }

        // Raum ist ein leerer Raum.
        if (RaumController.istRaumLeer(raum)) {
            return AlertFabrik.generiereAlert(Alert.AlertType.INFORMATION,
                    StringsammlungAlert.LAYOUT_ABFRAGE_RAUM_LEER_TITEL, StringsammlungAlert.LAYOUT_ABFRAGE_RAUM_LEER_NACHRICHT);
        }

        // Raum ist ein Leiterraum, jedoch nicht der letzte und es wurden noch nicht alle Gegner besiegt.
        if (RaumController.istRaumEinLeiterraum(raum) && !RaumController.istRaumLetzterRaum(raum) && !EbenenController.wurdenAlleRaeumeBesiegt()) {
            return generiereAlertNichtAlleGegnerRaeumeWurdenBesiegt();
        }

        // Raum ist ein Leiterraum, jedoch nicht der letzte und es wurden alle Gegner besiegt.
        if (RaumController.istRaumEinLeiterraum(raum) && !RaumController.istRaumLetzterRaum(raum) && EbenenController.wurdenAlleRaeumeBesiegt()) {
            return generiereAlertAktuellerRaumLeiterraum(abbrechenButtonType);
        }

        // Raum ist der letzte Leiterraum, jedoch wurden nicht alle Gegner besiegt.
        if (RaumController.istRaumLetzterRaum(raum) && !EbenenController.wurdenAlleRaeumeBesiegt()) {
            return generiereAlertNichtAlleGegnerRaeumeWurdenBesiegt();
        }

        // Raum ist der letzte Leiterraum und alle Gegner wurden besiegt. Das Spiel ist gewonnen.
        if (RaumController.istRaumLetzterRaum(raum) && EbenenController.wurdenAlleRaeumeBesiegt()) {
            return generiereAlertSpielGewonnen(abbrechenButtonType);
        }
        // Raum ist ein Spezialraum (Haendlerraum, Schmiedraum oder Schatzraum).
        return generiereAlertAktuellerRaumSpezialraum(raum, abbrechenButtonType);
    }


    /**
     * Wird diese Methode aufgerufen, befindet sich der Spieler aktuell in einem Gegnerraum.
     * Es wird ein Dialog erstellt, der dem Spieler die Moeglichkeiten eroeffnet zu kaempfen oder den Vorgang abzubrechen.
     *
     * @param raum
     * @param abbrechenButtonType
     * @return alert, der durch die Abfragen an die aktuelle Situation angepasst ist.
     */
    private static Alert generiereAlertAktuellerRaumGegnerraum(Raum raum, ButtonType abbrechenButtonType) {
        ButtonType kaempfenButtonType = new ButtonType(StringsammlungAlert.LAYOUT_ABFRAGE_KAEMPFEN, ButtonType.YES.getButtonData());

        Alert alert = new Alert(Alert.AlertType.INFORMATION, StringsammlungAlert.LAYOUT_ABFRAGE_TEXT_KAMPF_1
                + raum.getName() + StringsammlungAlert.LAYOUT_ABFRAGE_TEXT_KAMPF_2_IDENTISCHER_RAUM,
                kaempfenButtonType, abbrechenButtonType);

        alert.setTitle(StringsammlungAlert.LAYOUT_ABFRAGE_TITEL);
        alert.setHeaderText(null);
        alert.initOwner(Buehne.getInstance().getStage());

        return alert;
    }

    /**
     * Wird diese Methode aufgerufen, befindet sich der Spieler aktuell in einem Leiterraum.
     * Es wird ein Dialog erstellt, der dem Spieler die Moeglichkeiten eroeffnet die Ebene zu verlassen und in die naechste zu wechseln oder den Vorgang abzubrechen.
     *
     * @param abbrechenButtonType
     * @return alert, der durch die Abfragen an die aktuelle Situation angepasst ist.
     */
    private static Alert generiereAlertAktuellerRaumLeiterraum(ButtonType abbrechenButtonType) {
        ButtonType ebeneVerlassenButtonType = new ButtonType(StringsammlungAlert.LAYOUT_ABFRAGE_EBENE_VERLASSEN_BUTTON_TYPE_TEXT, ButtonType.NEXT.getButtonData());
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, StringsammlungAlert.LAYOUT_ABFRAGE_EBENE_VERLASSEN_NACHBARRAUM_TEXT, ebeneVerlassenButtonType, abbrechenButtonType);
        alert.setTitle(StringsammlungAlert.LAYOUT_ABFRAGE_EBENE_VERLASSEN_TITEL);
        alert.setHeaderText(null);
        alert.initOwner(Buehne.getInstance().getStage());
        return alert;
    }

    /**
     * Wird diese Methode aufgerufen, befindet sich der Spieler aktuell in einem Spezialraum (Schmied-, Schatz- oder Haendlerraum).
     * Es wird ein Dialog erstellt, der dem Spieler die Moeglichkeiten eroeffnet den Raum zu betreten oder den Vorgang abzubrechen.
     *
     * @param raum
     * @param abbrechenButtonType
     * @return alert, der durch die Abfragen an die aktuelle Situation angepasst ist.
     */
    private static Alert generiereAlertAktuellerRaumSpezialraum(Raum raum, ButtonType abbrechenButtonType) {
        ButtonType eintretenButtonType = new ButtonType(StringsammlungAlert.LAYOUT_ABFRAGE_EINTRETEN, ButtonType.YES.getButtonData());

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, StringsammlungAlert.LAYOUT_ABFRAGE_TEXT_EINTRETEN_1
                + raum.getName() + StringsammlungAlert.LAYOUT_ABFRAGE_TEXT_EINTRETEN_2_IDENTISCHER_RAUM,
                eintretenButtonType, abbrechenButtonType);

        alert.setTitle(StringsammlungAlert.LAYOUT_ABFRAGE_TITEL);
        alert.setHeaderText(null);
        alert.initOwner(Buehne.getInstance().getStage());

        return alert;
    }

    /**
     * Wird diese Methode aufgerufen, hat der Spieler den letzten Leiterraum im Spiel ausgewaehlt.
     * Das bedeutet, dass der Spieler das Spiel gewonnen hat.
     * Es wird ein Dialog erstellt, der dem Spieler gratuliert und ihm die Moeglichkeiten eroeffnet das Spiel zu beenden.
     * Ausserdem kann abgebrochen werden.
     *
     * @param abbrechenButtonType
     * @return alert, der durch die Abfragen an die aktuelle Situation angepasst ist.
     */
    private static Alert generiereAlertSpielGewonnen(ButtonType abbrechenButtonType) {
        ButtonType spielBeendenButtonType = new ButtonType(StringsammlungAlert.LAYOUT_BUTTON_TYPE_SPIEL_BEENDEN, ButtonType.FINISH.getButtonData());

        Alert alert = new Alert(Alert.AlertType.INFORMATION, StringsammlungAlert.LAYOUT_SPIEL_GEWONNEN_NACHRICHT_1
                + Spielwelt.getInstance().getProtagonist().getName()
                + StringsammlungAlert.AUSRUFEZEICHEN
                + StringsammlungAlert.LAYOUT_SPIEL_GEWONNEN_NACHRICHT_2, spielBeendenButtonType, abbrechenButtonType);
        alert.setTitle(StringsammlungAlert.LAYOUT_SPIEL_GEWONNEN_TITEL);
        alert.setHeaderText(null);
        alert.initOwner(Buehne.getInstance().getStage());
        return alert;
    }

    /**
     * Diese Methode wird aufgerufen, wenn der Spieler einen Leiterraum erreicht hat, er jedoch noch nicht alle Gegner besiegt hat.
     * Es wird ein Dialog erstellt, der den Spieler darueber informiert, dass er noch Kaempfe auszutragen hat.
     *
     * @return alert, der durch die Abfragen an die aktuelle Situation angepasst ist.
     */
    private static Alert generiereAlertNichtAlleGegnerRaeumeWurdenBesiegt() {
        return AlertFabrik.generiereAlert(Alert.AlertType.INFORMATION, StringsammlungAlert.LAYOUT_NICHT_ALLE_GEGNER_BESIEGT_TITEL,
                StringsammlungAlert.LAYOUT_NICHT_ALLE_GEGNER_BESIEGT_NACHRICHT
                        + Spielwelt.getInstance().getProtagonist().getName()
                        + StringsammlungAlert.AUSRUFEZEICHEN);
    }

}
