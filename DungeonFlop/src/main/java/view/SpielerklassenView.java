package view;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import resources.enums.Spielerklasse;
import resources.interfaces.PfadSammlung;
import resources.interfaces.StringsammlungGUI;
import resources.interfaces.ZahlensammlungGUI;

/**
 * Diese Klasse enthaelt das Layout fuer die Spielerklassen.
 */
public class SpielerklassenView {

    private Pane root = null;
    private static final Font fontSpielfigur = new Font(ZahlensammlungGUI.SCHRIFTGROESSE_18);
    private final Font fontSpielerklasse = new Font(ZahlensammlungGUI.SCHRIFTGROESSE_25);

    public SpielerklassenView(Spielerklasse spielerklasse) {
        this.root = initRoot(spielerklasse);
    }

    /**
     * Diese Methode erstellt die Karten anhand der Spielerklassen.
     *
     * @param spielerklasse
     * @return BorderPane
     */
    private BorderPane initRoot(Spielerklasse spielerklasse) {
        BorderPane borderPane = new BorderPane();
        borderPane.setBorder(new Border(new BorderStroke(Color.ORANGE, BorderStrokeStyle.SOLID, new CornerRadii(ZahlensammlungGUI.CORNER_RADII), BorderStroke.MEDIUM)));

        Text spielerklasseName = new Text(spielerklasse.getKlassenbezeichnung());
        spielerklasseName.setFont(fontSpielerklasse);

        HBox spielerKlassennameHBox = new HBox(spielerklasseName);
        spielerKlassennameHBox.setAlignment(Pos.CENTER);
        spielerKlassennameHBox.setPadding(ZahlensammlungGUI.SPIELERKLASSENVIEW_SPIELERKLASSENNAME_INSET);

        HBox lebensAnzeige = erstelleLebensanzeige(spielerklasse);
        lebensAnzeige.setAlignment(Pos.CENTER);

        HBox schadensAnzeige = erstelleSchadensanzeige(spielerklasse);
        schadensAnzeige.setAlignment(Pos.CENTER);

        Text name = new Text(spielerklasse.getSpielfigur().getName());
        name.setFont(fontSpielfigur);

        HBox attribute = new HBox(lebensAnzeige, name, schadensAnzeige);
        attribute.setAlignment(Pos.CENTER);
        attribute.setSpacing(ZahlensammlungGUI.SPIELERKLASSENVIEW_ATTRIBUTE_SPACING);

        ImageView bildDerKarte = erstelleBild(String.format(PfadSammlung.BILDER_PFAD_GENERELL, spielerklasse.getSpielfigur().getName().toLowerCase()));

        Separator separator = new Separator(Orientation.HORIZONTAL);
        separator.setPadding(ZahlensammlungGUI.SPIELERKLASSENVIEW_SEPARATOR_INSETS);

        Separator separator2 = new Separator(Orientation.HORIZONTAL);
        separator2.setPadding(ZahlensammlungGUI.SPIELERKLASSENVIEW_SEPARATOR_INSETS);

        Separator separator3 = new Separator(Orientation.HORIZONTAL);
        separator3.setPadding(ZahlensammlungGUI.SPIELERKLASSENVIEW_SEPARATOR_INSETS);


        VBox spielfigurenKarte = new VBox(attribute, bildDerKarte);
        spielfigurenKarte.setSpacing(ZahlensammlungGUI.SPIELERKLASSENVIEW_SPIELFIGURENKARTE_SPACING);
        spielfigurenKarte.setAlignment(Pos.CENTER);
        spielfigurenKarte.setPadding(ZahlensammlungGUI.SPIELERKLASSENVIEW_SPIELFIGURENKARTE_INSETS);

        Text spielerklassenBeschreibung = new Text(spielerklasse.getKlassenbeschreibung());
        spielerklassenBeschreibung.setFont(fontSpielfigur);
        spielerklassenBeschreibung.setWrappingWidth(ZahlensammlungGUI.SPIELERKLASSENVIEW_SPIELERKLASSENBESCHREIBUNG_WRAPPINGWIDTH);

        HBox spielerklassenBeschreibungHBox = new HBox(spielerklassenBeschreibung);
        spielerklassenBeschreibungHBox.setAlignment(Pos.CENTER);
        spielerklassenBeschreibungHBox.setPadding(ZahlensammlungGUI.SPIELERKLASSENVIEW_SPIELERKLASSENBESCHREIBUNG_INSETS);

        HBox eigenschaftenHBox = erstelleEigenschaften(spielerklasse);

        VBox gesamteKarte = new VBox(
                spielerKlassennameHBox,
                separator,
                spielfigurenKarte,
                separator2,
                spielerklassenBeschreibungHBox,
                separator3,
                eigenschaftenHBox
        );

        gesamteKarte.setAlignment(Pos.CENTER);

        borderPane.setCenter(gesamteKarte);

        borderPane.setMaxWidth(ZahlensammlungGUI.SPIELERKLASSENVIEW_BORDERPANE_MAXWIDTH);
        borderPane.setMaxHeight(ZahlensammlungGUI.SPIELERKLASSENVIEW_BORDERPANE_MAXHEIGHT);
        borderPane.setMinWidth(ZahlensammlungGUI.SPIELERKLASSENVIEW_BORDERPANE_MINWIDTH);
        borderPane.setMinHeight(ZahlensammlungGUI.SPIELERKLASSENVIEW_BORDERPANE_MINHEIGHT);

        return borderPane;
    }

    /**
     * Diese Methode erstellt die Icons fuer die Karten in der passenden Groesse.
     *
     * @param pfad
     * @return ImageView
     */
    private static ImageView erstelleIcon(String pfad) {
        Image bild = new Image(pfad);
        ImageView imageView = new ImageView(bild);
        imageView.setFitHeight(ZahlensammlungGUI.SCHRIFTGROESSE_35);
        imageView.setFitWidth(ZahlensammlungGUI.SCHRIFTGROESSE_35);
        return imageView;
    }

    /**
     * Diese Methode erstellt das Bild fuer die Spielfigur, die auf der Karte abgebildet ist.
     *
     * @param pfad
     * @return ImageView
     */
    private static ImageView erstelleBild(String pfad) {
        Image bild = new Image(pfad);
        ImageView imageView = new ImageView(bild);
        imageView.setFitHeight(ZahlensammlungGUI.FELDGROESSE_HOEHE);
        imageView.setFitWidth(ZahlensammlungGUI.FELDGROESSE_BREITE);
        return imageView;
    }

    /**
     * Diese Methode erstellt die Schadensanzeige fuer die Karte.
     *
     * @param spielerklasse
     * @return HBox
     */
    private static HBox erstelleSchadensanzeige(Spielerklasse spielerklasse) {
        Text schaden = new Text(String.valueOf(spielerklasse.getSpielfigur().getAngriffspunkte()));
        schaden.setFont(fontSpielfigur);
        ImageView schadensBild = erstelleIcon(PfadSammlung.PFAD_SCHADENSPUNKTE);
        HBox schadensAnzeige = new HBox(schadensBild, schaden);
        schadensAnzeige.setAlignment(Pos.TOP_LEFT);
        schadensAnzeige.setSpacing(ZahlensammlungGUI.SPIELERKLASSENVIEW_SCHADENSANZEIGE_SPACING);
        return schadensAnzeige;
    }

    /**
     * Diese Methode erstellt die Lebensanzeige fuer die Karte.
     *
     * @param spielerklasse
     * @return HBox
     */
    private static HBox erstelleLebensanzeige(Spielerklasse spielerklasse) {
        Text leben = new Text(String.valueOf(spielerklasse.getSpielfigur().getLebenspunkte()));
        leben.setFont(fontSpielfigur);
        ImageView lebensBild = erstelleIcon(PfadSammlung.PFAD_LEBENSPUNKTE);
        HBox lebensAnzeige = new HBox(leben, lebensBild);
        lebensAnzeige.setAlignment(Pos.TOP_LEFT);
        lebensAnzeige.setSpacing(ZahlensammlungGUI.SPIELERKLASSENVIEW_LEBENSANZEIGE_SPACING);
        return lebensAnzeige;
    }

    public Pane getRoot() {
        return root;
    }

    /**
     * Diese Methode fasst die Eigenschaften in einer HBox zusammen.
     *
     * @param spielerklasse
     * @return HBox
     */
    private static HBox erstelleEigenschaften(Spielerklasse spielerklasse) {
        ImageView mana = erstelleIcon(PfadSammlung.PFAD_SOL);
        ImageView kpr = erstelleIcon(PfadSammlung.PFAD_KARTEN);
        ImageView waffe = erstelleIcon(String.format(PfadSammlung.BILDER_PFAD_GENERELL, spielerklasse.getStartwaffe().getName()));

        Text manaText = new Text(String.valueOf(spielerklasse.getMana()));
        manaText.setFont(fontSpielfigur);
        VBox manaVBox = new VBox(mana, manaText);
        manaVBox.setSpacing(ZahlensammlungGUI.SPIELERKLASSENVIEW_EIGENSCHAFTEN_VBOX_SPACING);
        manaVBox.setAlignment(Pos.CENTER);

        Text kprText = new Text(String.valueOf(spielerklasse.getKartenProRunde()));
        kprText.setFont(fontSpielfigur);
        VBox kprVBox = new VBox(kpr, kprText);
        kprVBox.setSpacing(ZahlensammlungGUI.SPIELERKLASSENVIEW_EIGENSCHAFTEN_VBOX_SPACING);
        kprVBox.setAlignment(Pos.CENTER);

        Text waffenText = new Text(StringsammlungGUI.WAFFE);
        waffenText.setFont(fontSpielfigur);
        VBox waffenVBox = new VBox(waffe, waffenText);
        waffenVBox.setSpacing(ZahlensammlungGUI.SPIELERKLASSENVIEW_EIGENSCHAFTEN_VBOX_SPACING);
        waffenVBox.setAlignment(Pos.CENTER);

        HBox eigenschaftenHBox = new HBox(manaVBox, waffenVBox, kprVBox);
        eigenschaftenHBox.setSpacing(ZahlensammlungGUI.SPIELERKLASSENVIEW_EIGENSCHAFTEN_HBOX_SPACING);
        eigenschaftenHBox.setAlignment(Pos.CENTER);
        eigenschaftenHBox.setPadding(ZahlensammlungGUI.SPIELERKLASSENVIEW_EIGENSCHAFTEN_HBOX_INSETS);

        return eigenschaftenHBox;
    }
}
