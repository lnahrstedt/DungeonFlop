package view;

import control.view.HilfeAlertFabrik;
import control.view.InventarViewController;
import control.view.ItemViewController;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import model.Boni;
import model.Spielwelt;
import model.Waffe;
import resources.interfaces.PfadSammlung;
import resources.interfaces.StringsammlungInventar;
import resources.interfaces.StyleSpeicher;
import resources.interfaces.ZahlensammlungGUI;

import java.util.ArrayList;

/**
 * Diese Klasse enthaelt die Layout-Daten fuer den Inventar-Bildschirm und erstellt daraus eine Scene.
 */
public class InventarView implements Viewable {
    private Pane root;

    public InventarView() {
        this.root = initRoot();
    }

    /**
     * Erstellt den Button um das Inventar zu betreten.
     *
     * @return inventar - der Button um das Inventar zu oeffnen
     */
    public static Button erstelleInventarButton() {
        Button inventar = new Button();
        inventar.setGraphic(new ImageView(new Image(PfadSammlung.PFAD_INVENTAR_ICON, ZahlensammlungGUI.INVENTARKNOPF_BREITE, ZahlensammlungGUI.INVENTARKNOPF_HOEHE, true, true)));
        inventar.setPrefSize(ZahlensammlungGUI.INVENTARKNOPF_BREITE, ZahlensammlungGUI.INVENTARKNOPF_HOEHE);
        inventar.setStyle(StyleSpeicher.BUTTON + StyleSpeicher.FX_BACKGROUND_COLOR_TRANSPARENT);
        inventar.setCursor(Cursor.HAND);
        inventar.setOnAction(InventarViewController.onInventarButton());

        return inventar;
    }

    /**
     * Erstellt das Inventar aus der ArrayList des Spielers.
     */
    private Pane initRoot() {
        BorderPane root = new BorderPane();
        HBox menue = ErstelleMenueView.generiereMenu();

        VBox inv = new VBox();
        Text inventarText = new Text(StringsammlungInventar.INVENTAR_TEXT);
        inventarText.setFont(new Font(ZahlensammlungGUI.SCHRIFTGROESSE_24));
        inventarText.setTextAlignment(TextAlignment.CENTER);

        HBox spielerInfo = new SpielerInfoView().getInfoUeberSpieler();

        TilePane inventar = erstelleInventar(root);
        inventar.setVgap(ZahlensammlungGUI.INVENTAR_KARTEN_SPACING);
        inventar.setHgap(ZahlensammlungGUI.INVENTAR_KARTEN_SPACING);
        inv.getChildren().addAll(inventarText, inventar);
        inv.setPadding(new Insets(ZahlensammlungGUI.INVENTAR_INSETS, ZahlensammlungGUI.INVENTAR_INSETS, ZahlensammlungGUI.INVENTAR_INSETS, ZahlensammlungGUI.INVENTAR_INSETS));
        VBox ausruestung = erstelleAusruestung(root);
        ausruestung.setSpacing(ZahlensammlungGUI.INVENTAR_KARTEN_SPACING);
        ausruestung.setPadding(new Insets(ZahlensammlungGUI.INVENTAR_INSETS, ZahlensammlungGUI.INVENTAR_INSETS, ZahlensammlungGUI.INVENTAR_INSETS, ZahlensammlungGUI.INVENTAR_INSETS));
        ausruestung.getChildren().add(spielerInfo);

        root.setLeft(ausruestung);
        root.setRight(inv);
        root.setTop(menue);
        root.setBottom(Zurueckknopf.erstelleZurueckKnopf());
        root.setBackground(Background.fill(Color.WHITE));

        return root;
    }

    @Override
    public Pane getRoot() {
        return root;
    }

    @Override
    public Viewable aktualisiere() {
        return new InventarView();
    }

    @Override
    public void gibHilfeAus() {
        HilfeAlertFabrik.zeigeHilfeAlertInventarView();
    }

    /**
     * Erstellt das Inventar der nicht ausgeruesteten Waffen und Artefakten.
     *
     * @param root
     * @return inventar
     */
    private TilePane erstelleInventar(BorderPane root) {
        TilePane inventar = new TilePane();
        inventar.setPrefColumns(ZahlensammlungGUI.INVENTAR_TILEPANE_COLUMS);
        inventar.setPrefRows(ZahlensammlungGUI.INVENTAR_TILEPANE_ROWS);

        ArrayList<Waffe> waffenInventar = Spielwelt.getInstance().getProtagonist().getInventar().getWaffeninventar();
        ArrayList<Boni> artefaktInventar = Spielwelt.getInstance().getProtagonist().getInventar().getArtefaktinventar();
        for (int i = ZahlensammlungGUI.INVENTAR_ANZAHL_AKTIVER_WAFFEN; i < waffenInventar.size(); i++) {
            BorderPane karte = ItemView.erstelleItemKlein(waffenInventar.get(i));

            karte.setOnMouseEntered(ItemViewController.itemHoverEnterCenter(ItemView.erstelleItemGross(waffenInventar.get(i)), root));
            karte.setOnMouseExited(ItemViewController.itemHoverExitCenter(root));
            karte.setCursor(Cursor.HAND);
            karte.setStyle(StyleSpeicher.KARTE);
            karte.setOnMouseClicked(InventarViewController.waffeClicked(waffenInventar.get(i)));

            inventar.getChildren().add(karte);
        }
        for (int i = ZahlensammlungGUI.INVENTAR_ANZAHL_AKTIVE_ARTEFAKTE; i < artefaktInventar.size(); i++) {
            BorderPane karte = ItemView.erstelleItemKlein(artefaktInventar.get(i));

            karte.setOnMouseEntered(ItemViewController.itemHoverEnterCenter(ItemView.erstelleItemGross(artefaktInventar.get(i)), root));
            karte.setOnMouseExited(ItemViewController.itemHoverExitCenter(root));
            karte.setCursor(Cursor.HAND);
            karte.setStyle(StyleSpeicher.KARTE);
            karte.setOnMouseClicked(InventarViewController.artefaktClicked(artefaktInventar.get(i)));

            inventar.getChildren().add(karte);
        }

        return inventar;
    }

    /**
     * Erstellt die Ausgeruesteten Waffen und Artefakte aus Elementen an der 1. Stelle der ArrayList.
     *
     * @param root
     * @return ausruestung - die ausgeruesteten Waffen und Artefakte.
     */
    private VBox erstelleAusruestung(BorderPane root) {
        VBox ausruestung = new VBox();
        HBox artefakte = new HBox();
        artefakte.setSpacing(ZahlensammlungGUI.INVENTAR_KARTEN_SPACING);

        Text waffenText = new Text(StringsammlungInventar.WAFFE_TEXT);
        waffenText.setFont(new Font(ZahlensammlungGUI.SCHRIFTGROESSE_24));
        ausruestung.getChildren().add(waffenText);

        Text ausruestungText = new Text(StringsammlungInventar.ARTEFAKTE_TEXT);
        ausruestungText.setFont(new Font(ZahlensammlungGUI.SCHRIFTGROESSE_24));

        Rectangle platz1 = erstellePlatzhalter();
        Rectangle platz2 = erstellePlatzhalter();

        artefakte.getChildren().addAll(platz1, platz2);

        if (!Spielwelt.getInstance().getProtagonist().getInventar().getWaffeninventar().isEmpty()) {
            Waffe waffe = Spielwelt.getInstance().getProtagonist().getInventar().getWaffeninventar().get(ZahlensammlungGUI.INVENTAR_AKTIVE_WAFFE_POS);
            BorderPane waffeView = ItemView.erstelleItemKlein(waffe);
            waffeView.setOnMouseEntered(ItemViewController.itemHoverEnterCenter(ItemView.erstelleItemGross(waffe), root));
            waffeView.setOnMouseExited(ItemViewController.itemHoverExitCenter(root));
            waffeView.setStyle(StyleSpeicher.KARTE);
            ausruestung.getChildren().addAll(waffeView, ausruestungText, artefakte);
        }
        if (!Spielwelt.getInstance().getProtagonist().getInventar().getArtefaktinventar().isEmpty()) {
            Boni artefakt0 = Spielwelt.getInstance().getProtagonist().getInventar().getArtefaktinventar().get(ZahlensammlungGUI.INVENTAR_AKTIVES_ARTEFAKT_1_POS);
            BorderPane artefakt0View = ItemView.erstelleItemKlein(artefakt0);
            artefakt0View.setStyle(StyleSpeicher.KARTE);
            artefakt0View.setOnMouseEntered(ItemViewController.itemHoverEnterCenter(ItemView.erstelleItemGross(artefakt0), root));
            artefakt0View.setOnMouseExited(ItemViewController.itemHoverExitCenter(root));
            if (Spielwelt.getInstance().getProtagonist().getInventar().getArtefaktinventar().size() > 1) {
                Boni artefakt1 = Spielwelt.getInstance().getProtagonist().getInventar().getArtefaktinventar().get(ZahlensammlungGUI.INVENTAR_AKTIVES_ARTEFAKT_2_POS);
                BorderPane artefakt1View = ItemView.erstelleItemKlein(artefakt1);
                artefakt1View.setStyle(StyleSpeicher.KARTE);
                artefakt1View.setOnMouseEntered(ItemViewController.itemHoverEnterCenter(ItemView.erstelleItemGross(artefakt1), root));
                artefakt1View.setOnMouseExited(ItemViewController.itemHoverExitCenter(root));
                artefakte.getChildren().set(ZahlensammlungGUI.INVENTAR_AKTIVES_ARTEFAKT_1_POS, artefakt0View);
                artefakte.getChildren().set(ZahlensammlungGUI.INVENTAR_AKTIVES_ARTEFAKT_2_POS, artefakt1View);
                artefakte.setSpacing(ZahlensammlungGUI.INVENTAR_KARTEN_SPACING);
            } else {
                artefakte.getChildren().set(ZahlensammlungGUI.INVENTAR_AKTIVES_ARTEFAKT_1_POS, artefakt0View);
                artefakte.setSpacing(ZahlensammlungGUI.INVENTAR_KARTEN_SPACING);
            }
        }
        return ausruestung;
    }

    /**
     * Erstellt einen Platzhalter fuer nicht besetzte Artefaktplaetze.
     *
     * @return Rectangle
     */
    private Rectangle erstellePlatzhalter() {
        Rectangle platzhalter = new Rectangle(ZahlensammlungGUI.ITEM_KLEIN_BREITE, ZahlensammlungGUI.ITEM_KLEIN_HOEHE);
        platzhalter.setFill(Color.TRANSPARENT);
        platzhalter.setStroke(Color.BLACK);
        platzhalter.setStrokeWidth(ZahlensammlungGUI.INVENTAR_PLATZHALTER_STROKE_WIDTH);
        platzhalter.setArcWidth(ZahlensammlungGUI.INVENTAR_PLATZHALTER_ARCWITHHEIGHT);
        platzhalter.setArcHeight(ZahlensammlungGUI.INVENTAR_PLATZHALTER_ARCWITHHEIGHT);

        return platzhalter;
    }
}