package control.model;

import control.view.AlertFabrik;
import control.view.ChatViewController;
import control.view.MainViewController;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Spielwelt;
import model.netzwerk.NetzwerkFlagContainer;
import org.controlsfx.control.Notifications;
import resources.interfaces.*;
import utilities.CheatMode;
import utilities.MusikUndSoundController;
import view.Buehne;
import view.StartView;

/**
 * Diese Klasse beinhaltet Methoden, die fuer das Starten des GUI-Threads und des MusikUndSoundControllers von Noeten sind.
 */
public class Main extends Application {

    /**
     * Diese Methode startet den GUI-Thread sowie den MusikUndSoundController.
     *
     * @param args
     */
    public static void main(String[] args) {
        MusikUndSoundController.initMediaPlayer();
//        MusikUndSoundController.spieleHintergrundMusik();
        launch(args);
    }

    /**
     * Diese Methode liest den StartView ein und ruft initGUI zur Initialisierung der GUI auf.
     *
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {
        initGUI(primaryStage);
    }

    /**
     * Diese Methode dient der Initialisierung der fuer die GUI relevanten Inhalte.
     *
     * @param primaryStage
     */
    private void initGUI(Stage primaryStage) {

        initPrimaryStage(primaryStage);
        initBuehne(primaryStage);
        initCheatMode();
    }

    /**
     * Diese Methode initialisiert die PrimaryStage.
     *
     * @param primaryStage
     */
    private void initPrimaryStage(Stage primaryStage) {
        primaryStage.setTitle(StringsammlungGUI.GUI_TITEL);
        primaryStage.getIcons().add(getIconFuerGUI());
        primaryStage.setFullScreenExitHint(StringsammlungGUI.VOLLBILD_EXIT_NACHRICHT);
        primaryStage.setOnCloseRequest(MainViewController.oeffneBestaetigungsDialog());
        initShortcuts(primaryStage);
    }

    /**
     * Diese Methode initialisiert die Buehne.
     *
     * @param primaryStage
     */
    private void initBuehne(Stage primaryStage) {
        Buehne.getInstance().setStage(primaryStage);
        Buehne.getInstance().getStage().setScene(initScene());
        Buehne.getInstance().setViewable(new StartView());
        Buehne.getInstance().getStage().show();
    }

    /**
     * Diese Methode initialisiert die Scene.
     *
     * @return scene
     */
    private Scene initScene() {
        Scene scene = new Scene(new StartView().getRoot(), ZahlensammlungGUI.SZENEN_BREITE, ZahlensammlungGUI.SZENEN_HOEHE);
        scene.getStylesheets().add(StyleSpeicher.CSS_FILE);
        return scene;
    }

    /**
     * Diese Methode initialisiert den CheatMode.
     */
    private void initCheatMode() {
        CheatMode.getInstance().istDeaktiviert(false);
    }

    /**
     * Diese Methode initialisiert alle Shortcuts.
     * Zum Beispiel kann der Spieler mittels der Taste "F11" den Vollbildmodus ein- oder ausschalten.
     *
     * @param primaryStage
     */
    private void initShortcuts(Stage primaryStage) {

        KeyCombination keyCombination = new KeyCodeCombination(KeyCode.F4, KeyCombination.ALT_DOWN);

        primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, keyEvent -> {
            if (KeyCode.C.equals(keyEvent.getCode())) {
                if (NetzwerkFlagContainer.wirdOnlineGespielt()) {
                    ChatViewController.nachrichtAlsGelesenMarkierenUndChatOeffnen();
                } else {
                    AlertFabrik.generiereAlert(Alert.AlertType.ERROR, StringsammlungAlert.CHAT_OHNE_GEGENSPIELER_TITEL, StringsammlungAlert.CHAT_OHNE_GEGENSPIELER_NACHRICHT).show();
                }
            }
        });

        primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, keyEvent -> {
            if (keyCombination.match(keyEvent)) {
                AlertFabrik.generiereSchliessenAlert();
            }
        });

        primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, keyEvent -> {
            if (KeyCode.ESCAPE.equals(keyEvent.getCode())) {
                if (NetzwerkFlagContainer.istChatGeoeffnet()) {
                    ChatViewController.schliesseChat();
                } else {
                    AlertFabrik.generiereSchliessenAlert();
                }
            }
        });

        primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, keyEvent -> {
            if (KeyCode.F11.equals(keyEvent.getCode())) {
                primaryStage.setFullScreen(!primaryStage.isFullScreen());
            }
        });

        primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, keyEvent -> {
            if (KeyCode.M.equals(keyEvent.getCode())) {
                MusikUndSoundController.setVolumeMusik(ZahlensammlungGUI.LAUTSTAERKE_NULL);
                MusikUndSoundController.setVolumeSounds(ZahlensammlungGUI.LAUTSTAERKE_NULL);
            }
        });

        primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, keyEvent -> {
            if (KeyCode.N.equals(keyEvent.getCode())) {
                MusikUndSoundController.setVolumeMusik(ZahlensammlungGUI.LAUTSTAERKE_VOLL);
                MusikUndSoundController.setVolumeSounds(ZahlensammlungGUI.LAUTSTAERKE_VOLL);
            }
        });

        primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, keyEvent -> {
            if (KeyCode.T.equals(keyEvent.getCode()) && !CheatMode.getInstance().getZuvorGedrueckt() && !CheatMode.getInstance().getIstGedrueckt() && !NetzwerkFlagContainer.wirdOnlineGespielt()) {
                CheatMode.getInstance().istDeaktiviert(true);
                CheatMode.getInstance().setIstGedrueckt(true);
                Notifications.create().title(StringsammlungGUI.CHEATMODE_AN).position(Pos.TOP_RIGHT).hideAfter(Duration.seconds(3)).owner(primaryStage).show();
            }
            if (KeyCode.T.equals(keyEvent.getCode()) && CheatMode.getInstance().getZuvorGedrueckt() && CheatMode.getInstance().getIstGedrueckt() && !NetzwerkFlagContainer.wirdOnlineGespielt()) {
                CheatMode.getInstance().istDeaktiviert(false);
                CheatMode.getInstance().setIstGedrueckt(false);
                Notifications.create().title(StringsammlungGUI.CHEATMODE_AUS).position(Pos.TOP_RIGHT).hideAfter(Duration.seconds(3)).owner(primaryStage).show();
            }
        });

        primaryStage.addEventHandler(KeyEvent.KEY_RELEASED, keyEvent -> {
            if (KeyCode.T.equals(keyEvent.getCode()) && CheatMode.getInstance().getIstGedrueckt()) {
                CheatMode.getInstance().setZuvorGedrueckt(true);
            }
            if (KeyCode.T.equals(keyEvent.getCode()) && !CheatMode.getInstance().getIstGedrueckt()) {
                CheatMode.getInstance().setZuvorGedrueckt(false);
            }
        });

        primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, keyEvent -> {
            if (KeyCode.G.equals(keyEvent.getCode()) && CheatMode.getInstance().istAktiv()) {
                Spielwelt.getInstance().getProtagonist().setGold(Spielwelt.getInstance().getProtagonist().getGold() + ZahlensammlungGUI.CHEATMODE_GOLD);
            }
        });
    }

    /**
     * Diese Methode liest das Icon fuer die GUI aus der Datei ein und gibt dieses zurueck.
     *
     * @return icon
     */
    private Image getIconFuerGUI() {
        return new Image(PfadSammlung.PFAD_ICON);
    }
}