package view;

import control.model.raum.SchmiedraumController;
import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import model.Boni;
import model.Waffe;
import model.effekt.Effekt;
import model.karte.Karte;
import model.karte.Minion;
import model.karte.Zauber;
import resources.interfaces.PfadSammlung;
import resources.interfaces.StringsammlungGUI;
import resources.interfaces.StyleSpeicher;
import resources.interfaces.ZahlensammlungGUI;

/**
 * Diese Klasse enthaelt die Layout-Daten fuer den Item-Bildschirm und erstellt daraus eine Scene.
 */
public class ItemView {

    /**
     * Erstellt die aus zwei Karten bestehende Ansicht, die in dem Schmiedraum angezeigt wird.
     * Die Grafik zeigt die aktuelle Karte und die verbesserte Karte an.
     *
     * @param karte
     * @return
     */
    public static BorderPane erstelleSchmiedAnsicht(Karte karte) {
        BorderPane ausgabe = new BorderPane();
        ImageView pfeil = new ImageView(PfadSammlung.PFAD_PFEIL_HERUNTER);
        pfeil.setPreserveRatio(true);
        pfeil.setFitHeight(ZahlensammlungGUI.SCHMIED_PFEIL_GROESSE);
        BorderPane zentrierer = new BorderPane();
        zentrierer.setCenter(pfeil);
        try {
            VBox karten = new VBox(ItemView.erstelleItemGross(karte), zentrierer, ItemView.erstelleItemGross(SchmiedraumController.aufwertenOhneKosten(karte)));
            ausgabe.setRight(karten);
            ausgabe.getRight().setVisible(true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ausgabe;
    }

    /**
     * Erstellt die grosse Grafik einer Karte
     *
     * @param karte
     * @return Grafik der Karte als Borderpane
     */
    public static BorderPane erstelleItemGross(Karte karte) {
        BorderPane ausgabe = new BorderPane();

        VBox bottom = new VBox();
        if (karte instanceof Minion minion) {

            Text lebensPunkte = new Text(String.valueOf(minion.getLebenspunkte()));
            lebensPunkte.setTextAlignment(TextAlignment.LEFT);
            lebensPunkte.setFill(Color.RED);
            lebensPunkte.setFont(Font.font(ZahlensammlungGUI.SCHRIFTGROESSE_32));

            Text angriffsPunkte = new Text(String.valueOf(minion.getAngriffspunkte()));
            angriffsPunkte.setTextAlignment(TextAlignment.LEFT);
            angriffsPunkte.setFont(Font.font(ZahlensammlungGUI.SCHRIFTGROESSE_32));

            BorderPane lebenUndAngriff = new BorderPane();
            lebenUndAngriff.setLeft(lebensPunkte);
            lebenUndAngriff.setRight(angriffsPunkte);
            lebenUndAngriff.setMaxWidth(ZahlensammlungGUI.ITEM_LEBEN_ANGRIFF_BREITE);
            bottom.getChildren().add(lebenUndAngriff);

            if (minion.getEffekte() != null) {
                for (Effekt effekt : minion.getEffekte()) {
                    Text effektName = new Text(effekt.getName());
                    effektName.setUnderline(true);
                    effektName.setFont(Font.font(ZahlensammlungGUI.SCHRIFTGROESSE_16));
                    Text effektBeschreibung = new Text(effekt.getBeschreibung());
                    bottom.getChildren().add(effektName);
                    bottom.getChildren().add(effektBeschreibung);
                }
            }
        } else {
            Zauber zauber = (Zauber) karte;
            Text schaden = new Text(zauber.getSchaden() + StringsammlungGUI.SCHADEN);
            bottom.getChildren().add(schaden);
        }

        Text beschreibung = new Text(karte.getBeschreibung());
        beschreibung.setWrappingWidth(ZahlensammlungGUI.ITEM_GROSS_BREITE);
        bottom.getChildren().add(beschreibung);

        Text stufe = new Text(StringsammlungGUI.STUFE + karte.getStufe());
        bottom.getChildren().add(stufe);

        ausgabe.setBottom(bottom);

        ausgabe.setTop(erstelleTopHBox(karte));
        erstelleItemGrossGenerell(ausgabe, karte.getName());
        ausgabe.setStyle(StyleSpeicher.FX_BORDER_COLOR_BLACK + StyleSpeicher.FX_BORDER_RADIUS_15 + StyleSpeicher.FX_BACKGROUND_COLOR_BEIGE + StyleSpeicher.FX_BACKGROUND_RADIUS_15);
        return ausgabe;
    }

    /**
     * Erstellt die grosse Grafik eines Bonis
     *
     * @param boni
     * @return Grafik des Bonis als Borderpane
     */
    public static BorderPane erstelleItemGross(Boni boni) {
        BorderPane ausgabe = new BorderPane();
        ausgabe.setStyle(StyleSpeicher.FX_BACKGROUND_COLOR_ORANGE);

        Text boniName = new Text(boni.getName());
        boniName.setFont(Font.font(ZahlensammlungGUI.SCHRIFTGROESSE_24));
        BorderPane namenZentrierer = new BorderPane();
        namenZentrierer.setCenter(boniName);
        ausgabe.setTop(namenZentrierer);

        VBox bottom = new VBox();
        Text boniLevel = new Text(StringsammlungGUI.LEVEL + boni.getLevel());
        boniLevel.setUnderline(true);
        boniLevel.setFont(Font.font(ZahlensammlungGUI.SCHRIFTGROESSE_16));
        Text boniEffekt = new Text(boni.getEffekt().getBeschreibung());
        boniEffekt.setFont(Font.font(ZahlensammlungGUI.SCHRIFTGROESSE_16));
        bottom.getChildren().addAll(boniLevel, boniEffekt);
        ausgabe.setBottom(bottom);

        erstelleItemGrossGenerell(ausgabe, boni.getName());
        ausgabe.setStyle(StyleSpeicher.FX_BORDER_COLOR_BLACK + StyleSpeicher.FX_BORDER_RADIUS_15 + StyleSpeicher.FX_BACKGROUND_COLOR_AZURE + StyleSpeicher.FX_BACKGROUND_RADIUS_15);
        return ausgabe;
    }

    /**
     * Erstellt die grosse Grafik einer Waffe
     *
     * @param waffe
     * @return Grafik der Waffe als Borderpane
     */
    public static BorderPane erstelleItemGross(Waffe waffe) {
        BorderPane ausgabe = new BorderPane();
        ausgabe.setStyle(StyleSpeicher.FX_BACKGROUND_COLOR_ORANGE);

        Text waffeName = new Text(waffe.getName());
        waffeName.setFont(Font.font(ZahlensammlungGUI.SCHRIFTGROESSE_24));
        BorderPane namenZentrierer = new BorderPane();
        namenZentrierer.setCenter(waffeName);
        ausgabe.setTop(namenZentrierer);

        VBox bottom = new VBox();
        Text waffeSchaden = new Text(waffe.getSchaden() + StringsammlungGUI.SCHADEN);
        waffeSchaden.setFont(Font.font(ZahlensammlungGUI.SCHRIFTGROESSE_16));
        waffeSchaden.setUnderline(true);
        bottom.getChildren().add(waffeSchaden);
        if (waffe.getEffekt() != null) {
            Text waffeEffekt = new Text(waffe.getEffekt().getBeschreibung());
            bottom.getChildren().add(waffeEffekt);
        }
        ausgabe.setBottom(bottom);

        erstelleItemGrossGenerell(ausgabe, waffe.getName());
        ausgabe.setStyle(StyleSpeicher.FX_BORDER_COLOR_BLACK + StyleSpeicher.FX_BORDER_RADIUS_15 + StyleSpeicher.FX_BACKGROUND_COLOR_BEIGE + StyleSpeicher.FX_BACKGROUND_RADIUS_15);
        return ausgabe;
    }

    /**
     * Erstellt die kleine Grafik einer Karte
     *
     * @param karte
     * @return Grafik der Karte als Borderpane
     */
    public static BorderPane erstelleItemKlein(Karte karte) {
        BorderPane ausgabe = new BorderPane();
        ausgabe.setTop(erstelleTopHBox(karte));
        erstelleItemKleinGenerell(ausgabe, karte.getName());
        return ausgabe;
    }

    /**
     * Erstellt die kleine Grafik eines Bonis
     *
     * @param boni
     * @return Grafik des Bonis als Borderpane
     */
    public static BorderPane erstelleItemKlein(Boni boni) {
        BorderPane ausgabe = new BorderPane();
        ausgabe.setTop(erstelleTopHBox(boni));
        erstelleItemKleinGenerell(ausgabe, boni.getName());
        return ausgabe;
    }

    /**
     * Erstellt die kleine Grafik einer Waffe
     *
     * @param waffe
     * @return Grafik der Waffe als Borderpane
     */
    public static BorderPane erstelleItemKlein(Waffe waffe) {
        BorderPane ausgabe = new BorderPane();
        ausgabe.setTop(erstelleTopHBox(waffe));
        erstelleItemKleinGenerell(ausgabe, waffe.getName());
        return ausgabe;
    }

    /**
     * Setzt die Einstellungen der kleinen Itemgrafik
     *
     * @param borderPane
     * @param name
     */
    private static void erstelleItemKleinGenerell(BorderPane borderPane, String name) {
        ImageView itemBild = new ImageView(String.format(PfadSammlung.BILDER_PFAD_GENERELL, name));
        itemBild.setPreserveRatio(true);
        itemBild.setFitWidth(ZahlensammlungGUI.ITEM_BILD_KLEIN_BREITE);
        itemBild.setFitHeight(ZahlensammlungGUI.ITEM_BILD_KLEIN_HOEHE);

        borderPane.setCenter(itemBild);
        borderPane.setMinWidth(ZahlensammlungGUI.ITEM_KLEIN_BREITE);
        borderPane.setMaxWidth(ZahlensammlungGUI.ITEM_KLEIN_BREITE);
        borderPane.setMinHeight(ZahlensammlungGUI.ITEM_KLEIN_HOEHE);
        borderPane.setMaxHeight(ZahlensammlungGUI.ITEM_KLEIN_HOEHE);
    }

    /**
     * Setzt die Einstellungen der grossen Grafik
     *
     * @param borderPane
     * @param name
     */
    private static void erstelleItemGrossGenerell(BorderPane borderPane, String name) {
        ImageView itemBild = new ImageView(String.format(PfadSammlung.BILDER_PFAD_GENERELL, name));
        itemBild.setPreserveRatio(true);
        itemBild.setFitWidth(ZahlensammlungGUI.ITEM_BILD_GROSS_BREITE);
        itemBild.setFitHeight(ZahlensammlungGUI.ITEM_BILD_GROSS_HOEHE);

        borderPane.setCenter(itemBild);
        borderPane.setMinWidth(ZahlensammlungGUI.ITEM_GROSS_BREITE);
        borderPane.setMaxWidth(ZahlensammlungGUI.ITEM_GROSS_BREITE);
        borderPane.setMinHeight(ZahlensammlungGUI.ITEM_GROSS_HOEHE);
        borderPane.setMaxHeight(ZahlensammlungGUI.ITEM_GROSS_HOEHE);
        borderPane.setPadding(new Insets(ZahlensammlungGUI.ITEM_GROSS_PADDING));
    }

    /**
     * Erstellt den oberen Teil der Grafik einer Karte
     *
     * @param karte
     * @return oberen Teil der Grafik einer Karte
     */
    private static BorderPane erstelleTopHBox(Karte karte) {
        BorderPane ausgabe = new BorderPane();

        Text kartenName = new Text(karte.getName());
        kartenName.setTextAlignment(TextAlignment.CENTER);
        kartenName.setFont(Font.font(ZahlensammlungGUI.SCHRIFTGROESSE_24));
        kartenName.setWrappingWidth(ZahlensammlungGUI.ITEM_NAME_WRAPPING);
        ausgabe.setCenter(kartenName);

        Text manaKosten = new Text(String.valueOf(karte.getManakosten()));
        manaKosten.setFont(Font.font(ZahlensammlungGUI.SCHRIFTGROESSE_24));
        manaKosten.setFill(Color.BLUE);
        ausgabe.setLeft(manaKosten);

        HBox.setHgrow(ausgabe, Priority.ALWAYS);
        return ausgabe;
    }

    /**
     * Erstellt den oberen Teil der Grafik eines Bonis
     *
     * @param boni
     * @return oberen Teil der Grafik eines Bonis
     */
    private static BorderPane erstelleTopHBox(Boni boni) {
        Text boniName = new Text(boni.getName());
        boniName.setFont(Font.font(ZahlensammlungGUI.SCHRIFTGROESSE_24));
        boniName.setWrappingWidth(ZahlensammlungGUI.ITEM_NAME_WRAPPING);
        Text boniLevel = new Text(StringsammlungGUI.LEVEL + boni.getLevel());
        boniLevel.setFont(Font.font(ZahlensammlungGUI.SCHRIFTGROESSE_16));
        BorderPane ausgabe = new BorderPane();
        ausgabe.setTop(boniName);
        ausgabe.setBottom(boniLevel);
        return ausgabe;
    }

    /**
     * Erstellt den oberen Teil der Grafik einer Waffe
     *
     * @param waffe
     * @return oberen Teil der Grafik einer Waffe
     */
    private static BorderPane erstelleTopHBox(Waffe waffe) {
        Text waffeName = new Text(waffe.getName());
        waffeName.setFont(Font.font(ZahlensammlungGUI.SCHRIFTGROESSE_24));
        waffeName.setWrappingWidth(ZahlensammlungGUI.ITEM_NAME_WRAPPING);
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(waffeName);
        return borderPane;
    }
}