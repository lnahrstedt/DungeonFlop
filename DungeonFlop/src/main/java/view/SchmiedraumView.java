package view;

import control.model.SpielController;
import control.view.HilfeAlertFabrik;
import control.view.ItemViewController;
import control.view.SchmiedraumViewController;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.Spielwelt;
import model.karte.Karte;
import model.raum.Schmiedraum;
import resources.interfaces.PfadSammlung;
import resources.interfaces.StyleSpeicher;
import resources.interfaces.Zahlensammlung;
import resources.interfaces.ZahlensammlungGUI;

/**
 * Diese Klasse enthaelt die Layout-Daten fuer den Schmiedraum-Bildschirm und erstellt daraus eine Scene.
 */
public class SchmiedraumView implements Viewable {

    private Schmiedraum schmiedraum;
    private Pane root;

    /**
     * Erstellt anhand des uebergebenen Schmiedraumes die entsprechende Schmiedszene.
     *
     * @param schmiedraum
     */
    public SchmiedraumView(Schmiedraum schmiedraum) {
        this.schmiedraum = schmiedraum;
        this.root = initRoot(schmiedraum);
    }

    /**
     * Initialisiert die Schmiedszene.
     *
     * @param schmiedraum
     * @return
     */
    private Pane initRoot(Schmiedraum schmiedraum) {

        BorderPane root = new BorderPane();
        //Fuegt das Menu ein
        root.setTop(ErstelleMenueView.generiereMenu());
        Text spielerGold = new Text(String.valueOf(Spielwelt.getInstance().getProtagonist().getGold()));

        //Verhindert, dass das Bild nach dem erstem Hovern ueber einer Karte springt.
        Pane platzhalter = new Pane();
        platzhalter.setPadding(ZahlensammlungGUI.SCHMIEDRAUM_PLATZHALTER_INSET);

        BorderPane right = new BorderPane();
        right.setRight(platzhalter);

        HBox knopfSammlung = new HBox();
        VBox knopfSammlungSammlung = new VBox();
        for (int i = 0; i < Spielwelt.getInstance().getProtagonist().getDeck().size(); i++) {
            Button schmiedKnopf = new Button();

            //Teilt die Karten in mehreren Zeilen auf
            if (i % ZahlensammlungGUI.KARTEN_PRO_ZEILE == 0) {
                knopfSammlungSammlung.getChildren().add(knopfSammlung);
                knopfSammlung = new HBox();
            }

            Karte karte = Spielwelt.getInstance().getProtagonist().getDeck().get(i);
            schmiedKnopf.setGraphic(ItemView.erstelleItemKlein(karte));
            schmiedKnopf.setOnAction(SchmiedraumViewController.getSchmiedKnopfAktion(schmiedKnopf, schmiedraum, karte, spielerGold, right));
            schmiedKnopf.setOnMouseEntered(ItemViewController.schmiedHoverEnter(karte, right));
            schmiedKnopf.setOnMouseExited(ItemViewController.itemHoverExitRight(right));

            if (karte.getStufe() == Zahlensammlung.HOECHSTE_KARTEN_STUFE) {
                schmiedKnopf.setDisable(true);
                schmiedKnopf.setStyle(StyleSpeicher.FX_BORDER_COLOR_GRAY + StyleSpeicher.FX_BORDER_RADIUS_15 + StyleSpeicher.FX_BORDER_WIDTH_2 + StyleSpeicher.FX_BACKGROUND_COLOR_WHITE);
            } else {
                schmiedKnopf.setStyle(StyleSpeicher.FX_BORDER_COLOR_ORANGE + StyleSpeicher.FX_BORDER_RADIUS_15 + StyleSpeicher.FX_BORDER_WIDTH_2 + StyleSpeicher.FX_BACKGROUND_COLOR_WHITE);
            }

            knopfSammlung.getChildren().add(schmiedKnopf);
        }
        knopfSammlungSammlung.getChildren().add(knopfSammlung);


        Text schmiedKosten = new Text(String.valueOf(schmiedraum.getKosten() + Zahlensammlung.SCHMIEDRAUM_PREIS_SKALIERUNG * SpielController.getSkalierung()));
        schmiedKosten.setUnderline(true);
        schmiedKosten.setFont(Font.font(ZahlensammlungGUI.SCHRIFTGROESSE_32));
        BorderPane zentrierer = new BorderPane();
        zentrierer.setCenter(schmiedKosten);
        VBox test = new VBox(right, zentrierer);

        root.setRight(test);

        ImageView goldBild = new ImageView(PfadSammlung.PFAD_GOLD);
        goldBild.setPreserveRatio(true);
        goldBild.setFitHeight(ZahlensammlungGUI.GOLD_BILD_HOEHE);

        spielerGold.setFont(Font.font(ZahlensammlungGUI.SCHRIFTGROESSE_32));
        HBox goldKombination = new HBox(goldBild, spielerGold);
        goldKombination.setSpacing(ZahlensammlungGUI.GOLDKOMBINATION_SPACING);
        BorderPane middle = new BorderPane();
        middle.setLeft(goldKombination);

        HBox knopfKollektion = new HBox(DeckView.erstelleDeckKnopf(), InventarView.erstelleInventarButton(), Zurueckknopf.erstelleZurueckKnopf());
        middle.setRight(knopfKollektion);
        middle.setMaxWidth(ZahlensammlungGUI.SCHMIEDRAUM_SCROLLPANE_BREITE);

        //Scrollpane ermoeglicht es auch bei vielen Karten alle auszuwaehlen zu koennen
        ScrollPane scrollPane = new ScrollPane(knopfSammlungSammlung);
        scrollPane.setMaxHeight(ZahlensammlungGUI.SCHMIEDRAUM_SCROLLPANE_HOEHE);
        scrollPane.setMinHeight(ZahlensammlungGUI.SCHMIEDRAUM_SCROLLPANE_HOEHE);
        scrollPane.setStyle(StyleSpeicher.SCROLL_PANE_BACKGROUND_WHITE);
        VBox kartenGoldKnopfKombination = new VBox(scrollPane, middle);
        root.setCenter(kartenGoldKnopfKombination);

        root.setBackground(Background.fill(Color.WHITE));
        return root;
    }

    @Override
    public Pane getRoot() {
        return root;
    }

    @Override
    public Viewable aktualisiere() {
        return new SchmiedraumView(this.schmiedraum);
    }

    @Override
    public void gibHilfeAus() {
        HilfeAlertFabrik.zeigeHilfeAlertSchmiedraumView();
    }
}
