package view;

import control.view.HilfeAlertFabrik;
import control.view.StoryViewController;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import model.Spielwelt;
import resources.interfaces.StringsammlungGUI;
import resources.interfaces.StyleSpeicher;
import resources.interfaces.ZahlensammlungGUI;

/**
 * Diese Klasse enthaelt die Layout-Daten fuer den Story-Bildschirm und erstellt daraus eine Scene.
 */
public class StoryView implements Viewable {

    private Pane root;

    public StoryView() {
        this.root = initRoot();
    }

    /**
     * Diese Methode erstellt und gibt die Szene mit dem Story-Bildschirm zurueck.
     *
     * @return Scene storyView
     */
    private Pane initRoot() {

        BorderPane root = new BorderPane();
        root.setTop(ErstelleTopView.generiereTopView());
        GridPane gridPane = erstelleGridPaneFuerStoryText();
        fuelleGridPaneMitStoryText(gridPane);
        fuelleGridPaneMitLayoutEbene0Button(gridPane);
        root.setCenter(gridPane);
        root.setBackground(Background.fill(Color.WHITE));

        return root;
    }

    /**
     * Erstellt und gibt ein GridPane zurueck.
     *
     * @return GridPane
     */
    private GridPane erstelleGridPaneFuerStoryText() {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.TOP_CENTER);

        return gridPane;
    }

    /**
     * Fuegt den StoryText in das uebergebene GridPane ein.
     *
     * @param gridPane
     */
    private void fuelleGridPaneMitStoryText(GridPane gridPane) {
        Label kopfLabel = new Label(StringsammlungGUI.STORY_TEXT_LABEL);
        kopfLabel.setFont(Font.font(null, FontWeight.BOLD, ZahlensammlungGUI.SCHRIFTGROESSE_35));
        gridPane.add(kopfLabel, ZahlensammlungGUI.STORYVIEW_KOPFLABEL_INDEX, ZahlensammlungGUI.STORYVIEW_KOPFLABEL_INDEX, ZahlensammlungGUI.STORYVIEW_KOPFLABEL_COLSPAN, ZahlensammlungGUI.STORYVIEW_KOPFLABEL_ROWSPAN);
        GridPane.setHalignment(kopfLabel, HPos.CENTER);
        GridPane.setMargin(kopfLabel, ZahlensammlungGUI.STORYVIEW_KOPFLABEL_KNOPF_MARGIN);

        Text storyText = new Text(Spielwelt.getInstance().getStory());
        storyText.setFont(Font.font(null, FontWeight.SEMI_BOLD, ZahlensammlungGUI.SCHRIFTGROESSE_20));
        storyText.setTextAlignment(TextAlignment.CENTER);
        gridPane.add(storyText, ZahlensammlungGUI.STORYVIEW_STORYTEXT_COLINDEX, ZahlensammlungGUI.STORYVIEW_STORYTEXT_ROWINDEX);
    }

    /**
     * Fuegt den Layout-Ebene0-Button in das uebergebene GridPane ein.
     *
     * @param gridPane
     */
    private void fuelleGridPaneMitLayoutEbene0Button(GridPane gridPane) {
        Button button = erstelleLayout0Button();
        button.setCursor(Cursor.HAND);
        GridPane.setHalignment(button, HPos.CENTER);
        GridPane.setMargin(button, ZahlensammlungGUI.STORYVIEW_KOPFLABEL_KNOPF_MARGIN);
        gridPane.add(button, ZahlensammlungGUI.STORYVIEW_KNOPF_COLINDEX, ZahlensammlungGUI.STORYVIEW_KNOPF_ROWINDEX);
    }

    /**
     * Erstellt und gibt den Layout-Ebene0-Button zurueck.
     *
     * @return Button Ebene0Layout
     */
    private Button erstelleLayout0Button() {
        Button button = new Button(StringsammlungGUI.ZU_EBENE_WECHSELN_BUTTON);
        button.setFont(Font.font(null, FontWeight.BOLD, ZahlensammlungGUI.SCHRIFTGROESSE_20));
        button.setStyle(StyleSpeicher.BUTTON);
        button.setCursor(Cursor.HAND);
        button.setBackground(Background.EMPTY);
        button.setOnAction(StoryViewController.wechselZuLayoutEbene0Scene());
        return button;
    }

    @Override
    public Pane getRoot() {
        return this.root;
    }

    @Override
    public Viewable aktualisiere() {
        return new StoryView();
    }

    @Override
    public void gibHilfeAus() {
        HilfeAlertFabrik.zeigeHilfeAlertStoryView();
    }
}
