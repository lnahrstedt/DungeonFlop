package control.view;

import javafx.scene.control.Alert;
import resources.interfaces.StringsammlungAlert;

/**
 * Diese Klasse buendelt alle benoetigten Alerts fuer das Hilfe-Icon im Menue.
 */
public class HilfeAlertFabrik {
    /**
     * Erstellt das HilfeAlert fuer CreditsView.
     */
    public static void zeigeHilfeAlertCreditsView() {
        AlertFabrik.generiereAlert(Alert.AlertType.INFORMATION,
                        StringsammlungAlert.CREDITSVIEW_HILFE_TITEL,
                        StringsammlungAlert.CREDITSVIEW_HILFE_NACHRICHT)
                .show();
    }

    /**
     * Erstellt das HilfeAlert fuer DeckView.
     */
    public static void zeigeHilfeAlertDeckView() {
        AlertFabrik.generiereAlert(Alert.AlertType.INFORMATION,
                        StringsammlungAlert.DECKVIEW_HILFE_TITEL,
                        StringsammlungAlert.DECKVIEW_HILFE_NACHRICHT)
                .show();
    }

    /**
     * Erstellt das HilfeAlert fuer DeckView.
     */
    public static void zeigeHilfeAlertEbenenLayoutView() {
        AlertFabrik.generiereAlert(Alert.AlertType.INFORMATION,
                        StringsammlungAlert.EBENENLAYOUTVIEW_HILFE_TITEL,
                        StringsammlungAlert.EBENENLAYOUTVIEW_HILFE_NACHRICHT)
                .show();
    }

    /**
     * Erstellt das HilfeAlert fuer EbenenLayoutViewServerInteragiert.
     */
    public static void zeigeHilfeAlertEbenenLayoutViewServerInteragiert() {
        AlertFabrik.generiereAlert(Alert.AlertType.INFORMATION,
                        StringsammlungAlert.EBENENLAYOUTVIEWSERVERINTERAGIERT_HILFE_TITEL,
                        StringsammlungAlert.EBENENLAYOUTVIEWSERVERINTERAGIERT_HILFE_NACHRICHT)
                .show();
    }

    /**
     * Erstellt das HilfeAlert fuer GegnerraumView.
     */
    public static void zeigeHilfeAlertGegnerraumView() {
        AlertFabrik.generiereAlert(Alert.AlertType.INFORMATION,
                        StringsammlungAlert.GEGNERRAUMVIEW_HILFE_TITEL,
                        StringsammlungAlert.GEGNERRAUMVIEW_HILFE_NACHRICHT)
                .show();
    }

    /**
     * Erstellt das HilfeAlert fuer HaendlerraumView.
     */
    public static void zeigeHilfeAlertHaendlerraumView() {
        AlertFabrik.generiereAlert(Alert.AlertType.INFORMATION,
                        StringsammlungAlert.HAENDLERRAUMVIEW_HILFE_TITEL,
                        StringsammlungAlert.HAENDLERRAUMVIEW_HILFE_NACHRICHT)
                .show();
    }

    /**
     * Erstellt das HilfeAlert fuer InventarView.
     */
    public static void zeigeHilfeAlertInventarView() {
        AlertFabrik.generiereAlert(Alert.AlertType.INFORMATION,
                        StringsammlungAlert.INVENTARVIEW_HILFE_TITEL,
                        StringsammlungAlert.INVENTARVIEWVIEW_HILFE_NACHRICHT)
                .show();
    }

    /**
     * Erstellt das HilfeAlert fuer NeuesSpielView.
     */
    public static void zeigeHilfeAlertNeuesSpielView() {
        AlertFabrik.generiereAlert(Alert.AlertType.INFORMATION,
                        StringsammlungAlert.NEUESSPIELVIEW_HILFE_TITEL,
                        StringsammlungAlert.NEUESSPIELVIEW_HILFE_NACHRICHT)
                .show();
    }

    /**
     * Erstellt das HilfeAlert fuer SchatzraumView.
     */
    public static void zeigeHilfeAlertSchatzraumView() {
        AlertFabrik.generiereAlert(Alert.AlertType.INFORMATION,
                        StringsammlungAlert.SCHATZRAUMVIEW_HILFE_TITEL,
                        StringsammlungAlert.SCHATZRAUMVIEW_HILFE_NACHRICHT)
                .show();
    }

    /**
     * Erstellt das HilfeAlert fuer SchmiedraumView.
     */
    public static void zeigeHilfeAlertSchmiedraumView() {
        AlertFabrik.generiereAlert(Alert.AlertType.INFORMATION,
                        StringsammlungAlert.SCHMIEDRAUMVIEW_HILFE_TITEL,
                        StringsammlungAlert.SCHMIEDRAUMVIEW_HILFE_NACHRICHT)
                .show();
    }

    /**
     * Erstellt das HilfeAlert fuer SpieleinstellungenView.
     */
    public static void zeigeHilfeAlertSpieleinstellungenView() {
        AlertFabrik.generiereAlert(Alert.AlertType.INFORMATION,
                        StringsammlungAlert.SPIELEINSTELLUNGENVIEW_HILFE_TITEL,
                        StringsammlungAlert.SPIELEINSTELLUNGENVIEW_HILFE_NACHRICHT)
                .show();
    }

    /**
     * Erstellt das HilfeAlert fuer SpielLadenView.
     */
    public static void zeigeHilfeAlertSpielLadenView() {
        AlertFabrik.generiereAlert(Alert.AlertType.INFORMATION,
                        StringsammlungAlert.SPIELLADENVIEW_HILFE_TITEL,
                        StringsammlungAlert.SPIELLADENVIEW_HILFE_NACHRICHT)
                .show();
    }

    /**
     * Erstellt das HilfeAlert fuer StartView.
     */
    public static void zeigeHilfeAlertStartView() {
        AlertFabrik.generiereAlert(Alert.AlertType.INFORMATION,
                        StringsammlungAlert.STARTVIEW_HILFE_TITEL,
                        StringsammlungAlert.STARTVIEW_HILFE_NACHRICHT)
                .show();
    }

    /**
     * Erstellt das HilfeAlert fuer StoryView.
     */
    public static void zeigeHilfeAlertStoryView() {
        AlertFabrik.generiereAlert(Alert.AlertType.INFORMATION,
                        StringsammlungAlert.STORYVIEW_HILFE_TITEL,
                        StringsammlungAlert.STORYVIEW_HILFE_NACHRICHT)
                .show();
    }

    /**
     * Erstellt das HilfeAlert fuer StufenaufstiegsView.
     */
    public static void zeigeHilfeAlertStufenaufstiegsView() {
        AlertFabrik.generiereAlert(Alert.AlertType.INFORMATION,
                        StringsammlungAlert.STUFENAUFSTIEGVIEW_HILFE_TITEL,
                        StringsammlungAlert.STUFENAUFSTIEGVIEW_HILFE_NACHRICHT)
                .show();
    }
}
