package control.view;

import control.netzwerk.ClientThreadController;
import control.netzwerk.NetzwerkController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import model.Spielwelt;
import resources.interfaces.PfadSammlung;
import resources.interfaces.StringsammlungAlert;
import resources.interfaces.StringsammlungGUI;
import resources.interfaces.Zahlensammlung;
import utilities.CheatMode;
import utilities.MeinIO;
import utilities.ThreadHelferKlasse;
import view.Buehne;
import view.Zurueckknopf;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

/**
 * Diese Klasse steuert bzw. verwaltet alle Vorgaenge im Zusammenhang des ErstelleMenueViews.
 * Zum Beispiel verarbeitet sie die Eingaben des Spielers.
 */
public class ErstelleMenueViewController {

    /**
     * Diese Methode verarbeitet den Klick des MenueItems "Neues Spiel".
     * Beim Klick wird die Scene auf die Scene des NeuesSpielView gesetzt.
     *
     * @return eventHandler
     */
    public static EventHandler<ActionEvent> wechselZuNeuesSpielScene() {

        return actionEvent -> ViewWechsler.wechselZuNeuesSpielViewMitZurueckOption();
    }

    /**
     * Diese Methode verarbeitet den Klick des MenueItems "Spiel laden".
     * Beim Klick wird die Scene auf die Scene des SpielLadenView gesetzt.
     *
     * @return eventHandler
     */
    public static EventHandler<ActionEvent> wechselZuSpielLadenScene() {
        return actionEvent -> ViewWechsler.wechselZuSpielLadenViewMitZurueckOption();
    }

    /**
     * Diese Methode verarbeitet den Klick auf das MenieItem "Zum Hauptmenue".
     * Beim Klick wird die Scene auf die StartView Scene gesetzt.
     *
     * @return EventHandler
     */
    public static EventHandler<ActionEvent> wechsleZumHauptmenue() {
        return actionEvent -> {
            Alert a = AlertFabrik.generiereAlert(Alert.AlertType.CONFIRMATION, StringsammlungGUI.ALERT_ZUM_HAUPTMENUE_TITEL, StringsammlungGUI.ALERT_ZUM_HAUPTMENUE_NACHRICHT);
            Optional<ButtonType> result = a.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                NetzwerkController.gegenseiteUeberSchliessungInformieren();
                Zurueckknopf.getInstance().zurueckStackLeeren();
                ViewWechsler.wechselZuStartView();
            }
        };
    }

    /**
     * Diese Methode verarbeitet den Klick des MenueItems "Spieleinstellungen".
     * Beim Klick wird die Scene auf die Scene des SpieleinstellungenView gesetzt.
     *
     * @return eventHandler
     */
    public static EventHandler<ActionEvent> wechselZuOptionScene() {
        return actionEvent -> ViewWechsler.wechselZuSpieleinstellungenViewMitZurueckOption();
    }

    /**
     * Diese Methode verarbeitet den Klick des MenueItems "Spiel beenden".
     * Beim Klick wird der Spieler durch einen Dialog aufgefordert, der Beendigung des Spiels zuzustimmen.
     *
     * @return eventHandler
     */
    public static EventHandler<ActionEvent> oeffneBestaetigungsDialog() {
        return actionEvent -> AlertFabrik.generiereSchliessenAlert();
    }

    /**
     * Diese Methode verarbeitet den Klick des MenueItems "Spiel speichern".
     * Beim Klick wird eine .dat-Datei mit dem aktuellen Datum im Dateinamen und der Instanz der Spielwelt gespeichert.
     *
     * @return eventHandler
     */
    public static EventHandler<ActionEvent> speichereSpiel() {
        return actionEvent -> {
            SimpleDateFormat formatter = new SimpleDateFormat(PfadSammlung.DATUMSFORMAT);
            Date date = new Date();
            MeinIO.speichereSpielInDatei(PfadSammlung.PFAD_GESPEICHERTE_SPIELE + PfadSammlung.DATEINAME + formatter.format(date) + PfadSammlung.DATEIENDUNG, Spielwelt.getInstance());
            AlertFabrik.generiereAlert(Alert.AlertType.INFORMATION,
                            StringsammlungAlert.SPIEL_SPEICHERN_ALERT_TITEL,
                            StringsammlungAlert.SPIEL_SPEICHERN_ALERT_NACHRICHT)
                    .showAndWait();
        };
    }

    /**
     * Diese Methode wird aufgerufen, wenn der Spieler auf den Button "Spiel beitreten" geklickt hat.
     * Es wird ein Dialog erzeugt, der den Spieler auffordert, eine Server-Adresse einzugeben.
     * Auf Basis dieser Eingabe wird der ClientThread gestartet und es wird versucht, eine Verbindung zum Server aufzubauen.
     * Der Nutzer wird ueber einen Dialog ueber den Status der Verbindung benachrichtigt.
     * Gelingt die Verbindung, wird die Scene des Clients veraendert.
     *
     * @return eventHandler
     */
    public static EventHandler<ActionEvent> treteServerBei() {
        return actionEvent -> {
            TextInputDialog textInputDialog = initTextInputDialog();
            Optional<String> eingabe = textInputDialog.showAndWait();
            if (eingabe.isPresent()) {
                if (CheatMode.getInstance().istAktiv()) {
                    CheatMode.getInstance().istDeaktiviert(false);
                }
                ClientThreadController.getInstance().starteClientThread(textInputDialog.getEditor().getText());
                ThreadHelferKlasse.schlafe(Zahlensammlung.HALBE_SEKUNDE_IN_MILLISEKUNDEN);
            } else {
                textInputDialog.close();
            }
        };
    }

    /**
     * Diese Methode initialisiert den TextInputDialog, der den Nutzer auffordert, eine Server-Adresse einzugeben.
     *
     * @return textInputDialog
     */
    private static TextInputDialog initTextInputDialog() {
        TextInputDialog textInputDialog = new TextInputDialog();
        textInputDialog.setTitle(StringsammlungAlert.TEXT_INPUT_DIALOG_SERVER_ADRESSE_TITEL);
        textInputDialog.setHeaderText(null);
        textInputDialog.setContentText(StringsammlungAlert.TEXT_INPUT_DIALOG_SERVER_ADRESSE_NACHRICHT);
        textInputDialog.initOwner(Buehne.getInstance().getStage());
        return textInputDialog;
    }

    /**
     * Diese Methode wird aufgerufen, wenn der Nutzer auf den Hilfe-Knopf klickt und zeigt den passenden Hilfe-Dialog an.
     *
     * @return EventHandler
     */
    public static EventHandler<ActionEvent> zeigeHilfeAn() {
        return actionEvent -> Buehne.getInstance().getViewable().gibHilfeAus();

    }

    /**
     * Diese Methode wird aufgerufen, wenn der Nutzer den Chat oeffnen moechte.
     *
     * @return EventHandler
     */
    public static EventHandler<ActionEvent> oeffneChat() {
        return actionEvent -> ChatViewController.nachrichtAlsGelesenMarkierenUndChatOeffnen();
    }

    /**
     * Diese Methode wird aufgerufen, wenn der Nutzer den Chat schliessen moechte.
     *
     * @return EventHandler
     */
    public static EventHandler<ActionEvent> schliesseChat() {
        return actionEvent -> ChatViewController.schliesseChat();
    }
}
