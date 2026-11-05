package view;

import control.view.HilfeAlertFabrik;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import resources.interfaces.PfadSammlung;
import resources.interfaces.StringsammlungGUI;
import resources.interfaces.ZahlensammlungGUI;

/**
 * Diese Klasse enthaelt die Layout-Daten fuer den Credits-Bildschirm und erstellt daraus eine Scene.
 */
public class CreditsView implements Viewable {

    private Pane root;

    public CreditsView() {
        this.root = initRoot();
    }

    /**
     * Initialisiert und gibt die Szene des Credit-Bildschirms zurueck.
     *
     * @return Scene der CreditsView
     */
    private Pane initRoot() {
        BorderPane root = new BorderPane();
        root.setBackground(new Background(new BackgroundImage(new Image(
                PfadSammlung.PFAD_BACKGROUND, ZahlensammlungGUI.SZENEN_BREITE, ZahlensammlungGUI.SZENEN_HOEHE, true, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                new BackgroundSize(ZahlensammlungGUI.BACKGROUND_SCALE, ZahlensammlungGUI.BACKGROUND_SCALE, true, true, false, false))
        ));
        Text titel = new Text(StringsammlungGUI.CREDITS);
        titel.setFont(Font.font(null, FontWeight.EXTRA_BOLD, ZahlensammlungGUI.SCHRIFTGROESSE_100));
        Text text = new Text(StringsammlungGUI.CREDITS_TEXT);
        text.setFont(new Font(ZahlensammlungGUI.SCHRIFTGROESSE_35));
        text.setStyle("-fx-font-weight: bold");

        text.setTextAlignment(TextAlignment.CENTER);
        VBox vBox = new VBox(titel, text);
        vBox.setAlignment(Pos.CENTER);
        vBox.setMaxSize(400, 600);
        vBox.setCenterShape(true);
        root.setCenter(vBox);
        root.setBackground(Background.fill(Color.WHITE));

        VBox vertikaleTopBox = new VBox();
        HBox menue = ErstelleMenueView.generiereMenu();
        vertikaleTopBox.setAlignment(Pos.TOP_CENTER);
        vertikaleTopBox.getChildren().addAll(menue);
        root.setTop(vertikaleTopBox);


        return root;
    }

    @Override
    public Pane getRoot() {
        return root;
    }

    @Override
    public Viewable aktualisiere() {
        return new CreditsView();
    }

    @Override
    public void gibHilfeAus() {
        HilfeAlertFabrik.zeigeHilfeAlertCreditsView();
    }
}
