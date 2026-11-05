package view;

import control.model.raum.HaendlerraumController;
import control.view.HaendlerraumViewController;
import control.view.HilfeAlertFabrik;
import control.view.ItemViewController;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.Spielwelt;
import model.raum.Haendlerraum;
import resources.interfaces.*;

/**
 * Diese Klasse enthaelt die Layout-Daten fuer den Haendlerraum-Bildschirm und erstellt daraus eine Scene.
 */
public class HaendlerraumView implements Viewable {
    private Haendlerraum haendlerraum;
    private Pane root;

    /**
     * Erstellt anhand des uebergebenen Haendlerraumes die entsprechende Shopszene.
     *
     * @param haendlerraum
     */
    public HaendlerraumView(Haendlerraum haendlerraum) {
        this.haendlerraum = haendlerraum;
        this.root = initRoot(haendlerraum);
    }

    private static BorderPane gekaufteKarteDarstellen() {
        BorderPane genommeneKarte = new BorderPane();
        genommeneKarte.setMaxSize(ZahlensammlungGUI.ITEM_KLEIN_BREITE, ZahlensammlungGUI.ITEM_KLEIN_HOEHE);
        genommeneKarte.setMinSize(ZahlensammlungGUI.ITEM_KLEIN_BREITE, ZahlensammlungGUI.ITEM_KLEIN_HOEHE);
        genommeneKarte.setStyle(StyleSpeicher.FX_FILL_LIGHTGRAY + StyleSpeicher.FX_BORDER_COLOR_GRAY);
        return genommeneKarte;
    }

    /**
     * Initialisiert die Shopszene.
     *
     * @param haendlerraum
     * @return
     */
    private Pane initRoot(Haendlerraum haendlerraum) {

        BorderPane root = new BorderPane();
        //Fuegt das Menu ein
        root.setTop(ErstelleMenueView.generiereMenu());

        VBox knopfSammlung = new VBox();
        Text spielerGold = new Text(String.valueOf(Spielwelt.getInstance().getProtagonist().getGold()));
        spielerGold.setFont(Font.font(ZahlensammlungGUI.SCHRIFTGROESSE_32));

        ImageView goldBild = new ImageView(PfadSammlung.PFAD_GOLD);
        goldBild.setPreserveRatio(true);
        goldBild.setFitHeight(ZahlensammlungGUI.GOLD_BILD_HOEHE);

        //Kombiniert das Goldbild mit dem Spielergold
        HBox goldKombination = new HBox(goldBild, spielerGold);
        goldKombination.setSpacing(ZahlensammlungGUI.GOLDKOMBINATION_SPACING);
        BorderPane bottom = new BorderPane();
        bottom.setLeft(goldKombination);

        //Enthaelt die Kaufknoepfe fuer die Karten
        ImageView kartenbild = new ImageView(PfadSammlung.PFAD_DECK_ICON);
        kartenbild.setPreserveRatio(true);
        kartenbild.setFitWidth(ZahlensammlungGUI.HAENDLERRAUM_KATEGORIE_GROESSE);

        HBox kartenHBox = new HBox(kartenbild);
        kartenHBox.setAlignment(Pos.CENTER_LEFT);
        for (int i = 0; i < haendlerraum.getKartenangebot().size(); i++) {
            //Karte wurde nicht bereits gekauft
            if (haendlerraum.getKartenangebot().get(i) != null) {
                kartenHBox.getChildren().add(generiereKaufKnopf(Zahlensammlung.HAENDLERRAUM_KATEGORIE_KARTE, i, haendlerraum, root, spielerGold));
            }
        }

        //Enthaelt die Kaufknoepfe fuer die Artefakte
        ImageView artefaktbild = new ImageView(PfadSammlung.ARTEFAKT);
        artefaktbild.setPreserveRatio(true);
        artefaktbild.setFitWidth(ZahlensammlungGUI.HAENDLERRAUM_KATEGORIE_GROESSE);

        HBox artefaktHBox = new HBox(artefaktbild);
        artefaktHBox.setAlignment(Pos.CENTER_LEFT);
        for (int i = 0; i < haendlerraum.getBoniangebot().size(); i++) {
            //Artefakt wurde nicht bereits gekauft
            if (haendlerraum.getBoniangebot().get(i) != null) {
                artefaktHBox.getChildren().add(generiereKaufKnopf(Zahlensammlung.HAENDLERRAUM_KATEGORIE_BONI, i, haendlerraum, root, spielerGold));
            }
        }

        //Enthaelt die Kaufknoepfe fuer die Waffen
        ImageView waffenbild = new ImageView(PfadSammlung.WAFFE);
        waffenbild.setPreserveRatio(true);
        waffenbild.setFitWidth(ZahlensammlungGUI.HAENDLERRAUM_KATEGORIE_GROESSE);

        HBox waffenHBox = new HBox(waffenbild);
        waffenHBox.setAlignment(Pos.CENTER_LEFT);
        for (int i = 0; i < haendlerraum.getWaffenangebot().size(); i++) {
            //Waffe wurde nicht bereits genommen
            if (haendlerraum.getWaffenangebot().get(i) != null) {
                waffenHBox.getChildren().add(generiereKaufKnopf(Zahlensammlung.HAENDLERRAUM_KATEGORIE_WAFFE, i, haendlerraum, root, spielerGold));
            }
        }

        //Die drei Knopfkategorien werden untereinander positioniert
        knopfSammlung.getChildren().addAll(kartenHBox, artefaktHBox, waffenHBox);
        root.setCenter(knopfSammlung);

        HBox knopfKollektion = new HBox(DeckView.erstelleDeckKnopf(), InventarView.erstelleInventarButton(), Zurueckknopf.erstelleZurueckKnopf());
        bottom.setRight(knopfKollektion);

        root.setBottom(bottom);
        root.setBackground(Background.fill(Color.WHITE));
        return root;
    }

    /**
     * Erstellt die einzelnen Kaufknoepfe und verknuepft diese mit deren Funktionn
     *
     * @param kategorie    des Items
     * @param position     des Items
     * @param haendlerraum
     * @param root
     * @param spielerGold
     * @return
     */
    private Button generiereKaufKnopf(int kategorie, int position, Haendlerraum haendlerraum, BorderPane root, Text spielerGold) {

        Button kaufKnopf = new Button();
        kaufKnopf.setStyle(StyleSpeicher.FX_BACKGROUND_COLOR_WHITE + StyleSpeicher.FX_BORDER_COLOR_BLACK);

        //Grafik fuer die einzelnen Knoepfe
        BorderPane ansicht = new BorderPane();
        //Die grosse Ansicht erscheint wenn die Maus ueber dem Item hovert
        BorderPane ansichtGross = new BorderPane();

        switch (kategorie) {
            case Zahlensammlung.HAENDLERRAUM_KATEGORIE_KARTE -> {
                ansicht.setCenter(ItemView.erstelleItemKlein(haendlerraum.getKartenangebot().get(position)));
                ansichtGross = ItemView.erstelleItemGross(haendlerraum.getKartenangebot().get(position));
            }
            case Zahlensammlung.HAENDLERRAUM_KATEGORIE_BONI -> {
                ansicht.setCenter(ItemView.erstelleItemKlein(haendlerraum.getBoniangebot().get(position)));
                ansichtGross = ItemView.erstelleItemGross(haendlerraum.getBoniangebot().get(position));
            }
            case Zahlensammlung.HAENDLERRAUM_KATEGORIE_WAFFE -> {
                ansicht.setCenter(ItemView.erstelleItemKlein(haendlerraum.getWaffenangebot().get(position)));
                ansichtGross = ItemView.erstelleItemGross(haendlerraum.getWaffenangebot().get(position));
            }
        }

        kaufKnopf.setOnAction(HaendlerraumViewController.generiereKaufAktion(kaufKnopf, spielerGold, kategorie, position, haendlerraum));
        kaufKnopf.setOnMouseEntered(ItemViewController.itemHoverEnterRight(ansichtGross, root));
        kaufKnopf.setOnMouseExited(ItemViewController.itemHoverExitRight(root));

        Text itemPreis = new Text(HaendlerraumController.berechneKaufpreis(kategorie) + StringsammlungGUI.GOLD);
        itemPreis.setFont(Font.font(ZahlensammlungGUI.SCHRIFTGROESSE_16));
        ansicht.setBottom(itemPreis);

        kaufKnopf.setGraphic(ansicht);
        kaufKnopf.setStyle(StyleSpeicher.FX_BORDER_COLOR_ORANGE + StyleSpeicher.FX_BORDER_RADIUS_15 + StyleSpeicher.FX_BORDER_WIDTH_2 + StyleSpeicher.FX_BACKGROUND_COLOR_WHITE);
        return kaufKnopf;
    }

    @Override
    public Pane getRoot() {
        return this.root;
    }

    @Override
    public Viewable aktualisiere() {
        return new HaendlerraumView(this.haendlerraum);
    }

    @Override
    public void gibHilfeAus() {
        HilfeAlertFabrik.zeigeHilfeAlertHaendlerraumView();
    }
}
