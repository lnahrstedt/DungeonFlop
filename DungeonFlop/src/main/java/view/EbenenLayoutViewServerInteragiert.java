package view;

import control.view.HilfeAlertFabrik;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import resources.interfaces.StyleSpeicher;
import resources.interfaces.ZahlensammlungGUI;

/**
 * Diese Klasse enthaelt die Layout-Daten des EbenenLayout-Bildschirms fuer den Client im Falle, dass der Server mit einem Raum interagiert.
 * Daraus wird eine Scene erstellt.
 */
public class EbenenLayoutViewServerInteragiert implements Viewable {

    private String text;
    private Pane root;

    public EbenenLayoutViewServerInteragiert(String text) {
        this.text = text;
        this.root = initRoot(text);
    }

    /**
     * Diese Methode erstellt die Scene, die dem Client mitteilt, dass der Server aktuell mit einem Raum interagiert.
     *
     * @return Scene des EbenenLayouts, wenn der Server mit einem Raum interagiert.
     */
    private Pane initRoot(String nachricht) {

        BorderPane root = new BorderPane();

        BorderPane schicht0 = (BorderPane) new EbenenLayoutView().getRoot();
        schicht0.getChildren().remove(schicht0.getTop());

        Text text = new Text(nachricht);
        text.setFont(Font.font(null, FontWeight.BOLD, ZahlensammlungGUI.SCHRIFTGROESSE_35));
        text.setFill(Color.ORANGE);
        text.setTextAlignment(TextAlignment.CENTER);

        StackPane schicht1 = new StackPane();
        schicht1.getChildren().addAll(text);
        StackPane.setAlignment(text, Pos.CENTER);
        schicht1.setStyle(StyleSpeicher.FX_BACKGROUND_COLOR_DARK_TRANSPARENT);

        StackPane schichten = new StackPane(schicht0, schicht1);

        root.setTop(ErstelleMenueView.generiereMenu());
        root.setCenter(schichten);
        root.setBackground(Background.fill(Color.WHITE));

        return root;

    }

    @Override
    public Pane getRoot() {
        return root;
    }

    @Override
    public Viewable aktualisiere() {
        return new EbenenLayoutViewServerInteragiert(this.text);
    }

    @Override
    public void gibHilfeAus() {
        HilfeAlertFabrik.zeigeHilfeAlertEbenenLayoutViewServerInteragiert();
    }
}
