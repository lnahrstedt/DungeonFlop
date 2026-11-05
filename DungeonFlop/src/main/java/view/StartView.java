package view;

import control.view.ErstelleMenueViewController;
import control.view.HilfeAlertFabrik;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import resources.interfaces.PfadSammlung;
import resources.interfaces.StringsammlungGUI;
import resources.interfaces.StyleSpeicher;
import resources.interfaces.ZahlensammlungGUI;

/**
 * Diese Klasse enthaelt die Layout-Daten fuer den Start-Bildschirm und erstellt daraus eine Scene.
 */
public class StartView implements Viewable {

    private Pane root;

    public StartView() {
        this.root = initPane();
    }

    /**
     * Erstellt und gibt einen Knopf mit dem uebergebenen Text zurueck.
     *
     * @param text
     * @return Button
     */
    private static Button erstelleButton(String text) {
        Button button = new Button(text);
        button.setCursor(Cursor.HAND);
        button.setDefaultButton(true);
        button.setStyle(StyleSpeicher.BUTTON_SCHWARZER_RAND);
        button.setBackground(Background.EMPTY);
        button.setFont(Font.font(null, FontWeight.BOLD, ZahlensammlungGUI.SCHRIFTGROESSE_35));


        return button;
    }

    /**
     * Diese Methode erstellt und gibt die Szene mit dem Startbildschirm zurueck.
     *
     * @return
     */
    private Pane initPane() {

        BorderPane root = new BorderPane();

        root.setTop(ErstelleMenueView.generiereMenu());

        root.setBackground(new Background(new BackgroundImage(new Image(
                PfadSammlung.PFAD_BACKGROUND, ZahlensammlungGUI.SZENEN_BREITE, ZahlensammlungGUI.SZENEN_HOEHE, true, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                new BackgroundSize(ZahlensammlungGUI.BACKGROUND_SCALE, ZahlensammlungGUI.BACKGROUND_SCALE, true, true, false, false))
        ));

        Button neues_spiel = erstelleButton(StringsammlungGUI.NEUES_SPIEL);
        neues_spiel.setOnAction(ErstelleMenueViewController.wechselZuNeuesSpielScene());

        Button spiel_beitreten = erstelleButton(StringsammlungGUI.SPIEL_BEITRETEN);
        spiel_beitreten.setOnAction(ErstelleMenueViewController.treteServerBei());

        Button spiel_laden = erstelleButton(StringsammlungGUI.SPIEL_LADEN);
        spiel_laden.setOnAction(ErstelleMenueViewController.wechselZuSpielLadenScene());

        HBox hBox = new HBox(neues_spiel, spiel_beitreten, spiel_laden);
        hBox.setSpacing(ZahlensammlungGUI.SPIELLADEN_BUTTONS_SPACING);
        hBox.setPadding(ZahlensammlungGUI.SPIELLADEN_BUTTONS_PADDING);
        hBox.setAlignment(Pos.CENTER);
        root.setBottom(hBox);

        Text text = new Text(StringsammlungGUI.GUI_TITEL);
        text.setTextAlignment(TextAlignment.CENTER);
        text.setFont(Font.font(null, FontWeight.BOLD, ZahlensammlungGUI.SCHRIFTGROESSE_100));

        root.setCenter(text);

        return root;
    }

    @Override
    public Pane getRoot() {
        return root;
    }

    @Override
    public Viewable aktualisiere() {
        return new StartView();
    }

    @Override
    public void gibHilfeAus() {
        HilfeAlertFabrik.zeigeHilfeAlertStartView();
    }
}
