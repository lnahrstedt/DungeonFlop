package view;

import control.view.DeckViewController;
import control.view.HilfeAlertFabrik;
import control.view.ItemViewController;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import model.Spielwelt;
import model.karte.Karte;
import resources.interfaces.PfadSammlung;
import resources.interfaces.StyleSpeicher;
import resources.interfaces.ZahlensammlungGUI;

import java.util.ArrayList;

/**
 * Diese Klasse enthaelt die Layout-Daten fuer den Kartendeck-Bildschirm und erstellt daraus eine Scene.
 */
public class DeckView implements Viewable {
    private Pane root;

    public DeckView() {
        this.root = initRoot();
    }

    /**
     * Diese Methode erstellt den Button um das Deck zu oeffnen.
     *
     * @return Button - Der Button, welcher das Deck oeffnet
     */
    public static Button erstelleDeckKnopf() {

        Button deckButton = new Button();
        deckButton.setGraphic(new ImageView(new Image(PfadSammlung.PFAD_DECK_ICON, ZahlensammlungGUI.DECK_KNOPF_BREITE, ZahlensammlungGUI.DECK_KNOPF_HOEHE, true, true)));
        deckButton.setPrefSize(ZahlensammlungGUI.DECK_KNOPF_BREITE, ZahlensammlungGUI.DECK_KNOPF_HOEHE);
        deckButton.setStyle(StyleSpeicher.BUTTON + StyleSpeicher.FX_BACKGROUND_COLOR_TRANSPARENT);
        deckButton.setCursor(Cursor.HAND);
        deckButton.setOnAction(DeckViewController.onDeckButtonClick());

        return deckButton;
    }

    /**
     * Diese Methode erstellt die Scene, welche das aktuelle Deck des Spielers zeigt.
     */
    private Pane initRoot() {

        BorderPane root = new BorderPane();
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setStyle(StyleSpeicher.SCROLL_PANE_BACKGROUND_WHITE);

        TilePane deck = erstelleDeck(root);
        deck.setHgap(ZahlensammlungGUI.DECK_HVGAP);
        deck.setVgap(ZahlensammlungGUI.DECK_HVGAP);
        deck.setBackground(Background.fill(Color.WHITE));

        scrollPane.setContent(deck);
        root.setTop(ErstelleMenueView.generiereMenu());
        root.setCenter(scrollPane);
        root.setBottom(Zurueckknopf.erstelleZurueckKnopf());
        root.setBackground(Background.fill(Color.WHITE));
        return root;
    }

    /**
     * Diese Methode erstellt das Deck, dargestellt durch ein TilePane.
     *
     * @param root - Die urspruengliche BorderPane
     * @return TilePane - Das Deck dargestellt mithilfe eines TilePanes
     */
    private TilePane erstelleDeck(BorderPane root) {
        TilePane deckView = new TilePane();
        ArrayList<Karte> deck = Spielwelt.getInstance().getProtagonist().getDeck();
        for (Karte value : deck) {
            BorderPane karte = ItemView.erstelleItemKlein(value);
            BorderPane karteGross = ItemView.erstelleItemGross(value);
            karte.setOnMouseEntered(ItemViewController.itemHoverEnterRight(karteGross, root));
            karte.setOnMouseExited(ItemViewController.itemHoverExitRight(root));
            karte.setStyle(StyleSpeicher.KARTE);
            deckView.getChildren().add(karte);
        }

        return deckView;
    }

    @Override
    public Pane getRoot() {
        return root;
    }

    @Override
    public Viewable aktualisiere() {
        return new DeckView();
    }

    @Override
    public void gibHilfeAus() {
        HilfeAlertFabrik.zeigeHilfeAlertDeckView();
    }
}
