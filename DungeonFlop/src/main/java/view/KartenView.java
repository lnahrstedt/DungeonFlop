package view;

import control.model.KampfController;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import model.Runde;
import model.Spieler;
import model.Spielfigur;
import model.karte.Karte;
import resources.interfaces.PfadSammlung;
import resources.interfaces.StringsammlungGUI;
import resources.interfaces.StyleSpeicher;
import resources.interfaces.ZahlensammlungGUI;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Diese Klasse dient als Bauplan fuer die Ansicht einer Spielfigur in der grafischen Benutzeroberflaeche.
 */
public class KartenView {

    private Color borderColor = null;
    private BorderPane borderPane = null;

    public KartenView(Spielfigur spielfigur, Color borderColor) {
        this.borderColor = borderColor;
        this.borderPane = initKarte(spielfigur);
    }

    public KartenView(Karte karte, Color borderColor) {
        this.borderColor = borderColor;
        this.borderPane = initKarte(karte);
    }

    public KartenView(Color borderColor) {
        this.borderColor = borderColor;
        this.borderPane = initKarte();
    }

    /**
     * Diese Methode initialisiert eine leere Karte.
     *
     * @return BorderPane
     */
    private BorderPane initKarte() {
        this.borderPane = new BorderPane();
        this.borderPane.setBackground(Background.EMPTY);
        this.borderPane.setPrefHeight(ZahlensammlungGUI.FELDGROESSE_HOEHE);
        this.borderPane.setPrefWidth(ZahlensammlungGUI.FELDGROESSE_BREITE);
        this.borderPane.setStyle(StyleSpeicher.KARTENVIEW_SPIELFIGUR_BORDERPANE);
        return borderPane;
    }

    /**
     * Diese Methode initialisiert die Spielfigur in Form einer BorderPane.
     *
     * @param spielfigur
     * @return BorderPane
     */
    private BorderPane initKarte(Spielfigur spielfigur) {
        this.borderPane = new BorderPane();
        Text lebensPunkte = new Text(String.valueOf(spielfigur.getLebenspunkte()));
        lebensPunkte.setTextAlignment(TextAlignment.LEFT);
        lebensPunkte.setFill(Color.GREEN);
        Text schadensPunkte = new Text(String.valueOf(spielfigur.getAngriffspunkte()));
        schadensPunkte.setTextAlignment(TextAlignment.RIGHT);
        schadensPunkte.setFill(Color.RED);
        HBox topHBox = new HBox(lebensPunkte, schadensPunkte);
        topHBox.setSpacing(ZahlensammlungGUI.KARTENVIEW_KARTE_BORDERPANE_SPACING);
        topHBox.setAlignment(Pos.CENTER);
        this.borderPane.setTop(topHBox);
        ImageView spielfigurenBild = erstelleBildDerSpielfigur(spielfigur);
        this.borderPane.setCenter(spielfigurenBild);
        this.borderPane.setBackground(Background.EMPTY);
        this.borderPane.setPrefHeight(ZahlensammlungGUI.FELDGROESSE_HOEHE);
        this.borderPane.setPrefWidth(ZahlensammlungGUI.FELDGROESSE_BREITE);
        this.borderPane.setCursor(Cursor.HAND);
        return borderPane;
    }

    /**
     * Diese Methode initialisiert die Karte in Form einer BorderPane.
     *
     * @param karte
     * @return BorderPane
     */
    private BorderPane initKarte(Karte karte) {
        this.borderPane = new BorderPane();
        Text manakosten = new Text(String.valueOf(karte.getManakosten()));
        manakosten.setTextAlignment(TextAlignment.RIGHT);
        manakosten.setFill(Color.RED);
        HBox topHBox = new HBox(manakosten);
        topHBox.setAlignment(Pos.CENTER);
        this.borderPane.setTop(topHBox);
        ImageView kartenBild = erstelleBildDerKarte(karte);
        this.borderPane.setCenter(kartenBild);
        Text kartenName = new Text(karte.getName());
        kartenName.setTextAlignment(TextAlignment.CENTER);
        HBox bottomHBox = new HBox(kartenName);
        bottomHBox.setAlignment(Pos.CENTER);
        this.borderPane.setBottom(bottomHBox);
        this.borderPane.setBackground(Background.EMPTY);
        this.borderPane.setStyle(StyleSpeicher.KARTENVIEW_SPIELFIGUR_BORDERPANE);
        this.borderPane.setPrefHeight(ZahlensammlungGUI.FELDGROESSE_HOEHE);
        this.borderPane.setPrefWidth(ZahlensammlungGUI.FELDGROESSE_BREITE);
        this.borderPane.setCursor(Cursor.HAND);
        return borderPane;
    }

    public BorderPane getBorderPane() {
        return borderPane;
    }

    /**
     * Diese Methode erstellt die Bilder fuer die Spielfigurenansicht.
     *
     * @param spielfigur
     * @return ImageView
     */
    private ImageView erstelleBildDerSpielfigur(Spielfigur spielfigur) {
        Image bildDerSpielfigur = new Image(String.format(PfadSammlung.BILDER_PFAD_GENERELL, spielfigur.getName().toLowerCase()));
        ImageView spielfigurenBild = new ImageView(bildDerSpielfigur);
        spielfigurenBild.setFitWidth(ZahlensammlungGUI.FELDGROESSE_BREITE * ZahlensammlungGUI.HAELFTE);
        spielfigurenBild.setFitHeight(ZahlensammlungGUI.FELDGROESSE_HOEHE * ZahlensammlungGUI.HAELFTE);
        return spielfigurenBild;
    }

    /**
     * Diese Methode erstellt die Bilder fuer die Kartenansicht.
     *
     * @param karte
     * @return ImageView
     */
    private ImageView erstelleBildDerKarte(Karte karte) {
        Image bildDerSpielfigur = new Image(String.format(PfadSammlung.BILDER_PFAD_GENERELL, karte.getName().toLowerCase()));
        ImageView spielfigurenBild = new ImageView(bildDerSpielfigur);
        spielfigurenBild.setFitWidth(ZahlensammlungGUI.FELDGROESSE_BREITE * ZahlensammlungGUI.HAELFTE);
        spielfigurenBild.setFitHeight(ZahlensammlungGUI.FELDGROESSE_HOEHE * ZahlensammlungGUI.HAELFTE);
        return spielfigurenBild;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderPane.setBorder(new Border(new BorderStroke(borderColor, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
    }

    /**
     * Erstellt eine detaillierte Ansicht der uebergebenen Spielfigur.
     *
     * @param spielfigur - Die Spielfigur, aus der eine vergroesserte Ansicht erstellt wird
     * @param runde      - Die Runde, welche den aktuellen Aktionsspeicher enthaelt
     * @return spielfigurView - Die Ansicht der Spielfigur
     */
    public static BorderPane spielfigurViewGross(Spielfigur spielfigur, Runde runde) {
        BorderPane spielfigurView = new BorderPane();
        spielfigurView.setMinSize(ZahlensammlungGUI.SPIELFIGURGROSS_BREITE, ZahlensammlungGUI.SPIELFIGURGROSS_HOEHE);
        spielfigurView.setMaxSize(ZahlensammlungGUI.SPIELFIGURGROSS_BREITE, ZahlensammlungGUI.SPIELFIGURGROSS_HOEHE);
        spielfigurView.setStyle(StyleSpeicher.KARTENVIEW_SPIELFIGURGROSS_BORDERPANE);
        ImageView spielfigurenBild = new ImageView(new Image(String.format(PfadSammlung.BILDER_PFAD_GENERELL, spielfigur.getName().toLowerCase()),
                ZahlensammlungGUI.KARTENVIEW_IMAGE_HOEHE_BREITE, ZahlensammlungGUI.KARTENVIEW_IMAGE_HOEHE_BREITE, true, true));
        VBox bildStufe = new VBox(spielfigurenBild);

        Text angriffe = new Text();

        Text bewegungen = new Text();

        if (runde.getAktionsSpeicher(spielfigur) != null) {
            angriffe.setText(StringsammlungGUI.KARTE_ANGRIFFE + runde.getAktionsSpeicher(spielfigur).getAngriffe());
            angriffe.setFont(new Font(ZahlensammlungGUI.SCHRIFTGROESSE_24));
            angriffe.setTextAlignment(TextAlignment.CENTER);

            bewegungen.setText(StringsammlungGUI.KARTE_BEWEGUNGEN + runde.getAktionsSpeicher(spielfigur).getBewegungen());
            bewegungen.setFont(new Font(ZahlensammlungGUI.SCHRIFTGROESSE_24));
            bewegungen.setTextAlignment(TextAlignment.CENTER);
        }

        Text name = new Text();
        if (spielfigur instanceof Karte karte) {
            name.setText(karte.getName());
            Text stufe = new Text(StringsammlungGUI.STUFE + karte.getStufe());
            bildStufe.getChildren().add(stufe);
        } else {

            HashMap<Spieler, ArrayList<Spielfigur>> spielfigurenZugehoerigkeit = KampfController.getKampf().getSpielfeld().getSpielfigurenAufDemFeld();
            Spieler[] alleSpieler = spielfigurenZugehoerigkeit.keySet().toArray(new Spieler[0]);

            for (Spieler spieler : alleSpieler) {
                ArrayList<Spielfigur> spielfigurenEinesSpielers = spielfigurenZugehoerigkeit.get(spieler);
                for (Spielfigur spielfigurA : spielfigurenEinesSpielers) {
                    if (spielfigurA == spielfigur) {
                        name.setText(spieler.getName());
                    }
                }
            }
        }

        name.setFont(new Font(ZahlensammlungGUI.SCHRIFTGROESSE_24));
        name.setTextAlignment(TextAlignment.CENTER);


        Text leben = new Text(StringsammlungGUI.LEER + spielfigur.getLebenspunkte());
        leben.setFont(new Font(ZahlensammlungGUI.SCHRIFTGROESSE_24));
        leben.setTextAlignment(TextAlignment.LEFT);
        leben.setFill(Color.GREEN);

        Text schaden = new Text(StringsammlungGUI.LEER + spielfigur.getAngriffspunkte());
        schaden.setFont(new Font(ZahlensammlungGUI.SCHRIFTGROESSE_24));
        schaden.setTextAlignment(TextAlignment.RIGHT);
        schaden.setFill(Color.RED);

        HBox top = new HBox(leben, schaden);
        top.setSpacing(ZahlensammlungGUI.KARTE_SPACING);
        top.setPadding(ZahlensammlungGUI.KARTENVIEW_SPIELFIGURVIEWGROSS_TOP_INSETS);
        top.setAlignment(Pos.CENTER);

        VBox bottom = new VBox(name, angriffe, bewegungen);
        bottom.setPadding(ZahlensammlungGUI.KARTENVIEW_SPIELFIGURVIEWGROSS_BOTTOM_INSETS);
        bottom.setAlignment(Pos.CENTER);

        bildStufe.setAlignment(Pos.CENTER);

        spielfigurView.setTop(top);
        spielfigurView.setCenter(bildStufe);
        spielfigurView.setBottom(bottom);

        return spielfigurView;
    }
}
