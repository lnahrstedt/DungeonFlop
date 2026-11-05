package view;

import control.view.HilfeAlertFabrik;
import control.view.NeuesSpielViewController;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import resources.enums.Schwierigkeitsgrad;
import resources.enums.Spielerklasse;
import resources.interfaces.*;

import java.util.ArrayList;

/**
 * Diese Klasse enthaelt die Layout-Daten fuer den Neues-Spiel-Bildschirm und erstellt daraus eine Scene.
 */
public class NeuesSpielView implements Viewable {

    private Pane root;
    private static final ArrayList<BorderPane> spielerklassen = new ArrayList<>();
    private static Spielerklasse ausgewaehlteSpielerKlasse = Spielerklasse.values()[0];
    private static HBox spielerklassenHBox;
    private static BorderPane ausgewaehlteSpielerklasseBorderPane;

    public NeuesSpielView() {
        this.root = initRoot();
    }

    /**
     * Diese Methode erstellt und gibt die Szene des Neues-Spiel-Bildschirms zurueck.
     *
     * @return Scene NeuesSpiel
     */
    private Pane initRoot() {

        BorderPane root = new BorderPane();
        BorderPane borderPane = new BorderPane();
        generiereFormular(borderPane);

        root.setTop(ErstelleTopView.generiereTopView());
        root.setCenter(borderPane);
        root.setBottom(Zurueckknopf.erstelleZurueckKnopf());
        root.setBackground(Background.fill(Color.WHITE));
        return root;
    }


    /**
     * Setzt aus den verschiedenen Komponenten das Formular zusammen.
     *
     * @param borderPane
     */
    private void generiereFormular(BorderPane borderPane) {
        erstelleAlleSpielerKlassenViews();
        ausgewaehlteSpielerklasseBorderPane = spielerklassen.get(0);
        Label kopfLabel = new Label(StringsammlungGUI.SCENE_NEUES_SPIEL_KOPFZEILE_TEXT);
        kopfLabel.setFont(Font.font(null, FontWeight.BOLD, ZahlensammlungGUI.SCHRIFTGROESSE_35));

        Label spielerNameLabel = new Label(StringsammlungGUI.SCENE_NEUES_SPIEL_SPIELERNAME);
        TextField spielerNamenEingabeFeld = erstelleNamensTextfeld();

        VBox name = erstelleEingabe(spielerNameLabel, spielerNamenEingabeFeld);

        Label schwierigkeitsgradLabel = new Label(StringsammlungGUI.SCENE_NEUES_SPIEL_SCHWIERIGKEIT);
        ComboBox<Schwierigkeitsgrad> schwierigkeitsgradComboBox = erstelleSchwierigkeitsgradComboBox();

        VBox schwierigkeit = erstelleEingabe(schwierigkeitsgradLabel, schwierigkeitsgradComboBox);

        Button neuesSpielErstellenButton = erstelleSpielErstellenButton();

        VBox eingaben = new VBox(name, schwierigkeit, neuesSpielErstellenButton);
        eingaben.setSpacing(ZahlensammlungGUI.NEUESSPIEL_EINGABEN_SPACING);
        eingaben.setAlignment(Pos.TOP_LEFT);

        VBox alles = new VBox(eingaben, neuesSpielErstellenButton);
        alles.setSpacing(ZahlensammlungGUI.NEUESSPIEL_ALLES_SPACING);

        HBox auswahlSpielerklasse = erstelleKlassenAuswahl();
        spielerklassenHBox = auswahlSpielerklasse;

        HBox center = new HBox(alles, auswahlSpielerklasse);
        center.setSpacing(ZahlensammlungGUI.NEUESSPIEL_CENTER_SPACING);
        center.setAlignment(Pos.TOP_CENTER);

        VBox vbox = new VBox(center);
        vbox.setAlignment(Pos.CENTER);

        borderPane.setCenter(vbox);
        borderPane.setPadding(ZahlensammlungGUI.NEUESSPIEL_BORDERPANE_INSETS);

        neuesSpielErstellenButton.setOnAction(actionEvent -> {
            NeuesSpielViewController.pruefeEingaben(
                    spielerNamenEingabeFeld.getText(),
                    ausgewaehlteSpielerKlasse,
                    schwierigkeitsgradComboBox.getValue()
            );
            ausgewaehlteSpielerKlasse = Spielerklasse.values()[0];
        });
    }

    /**
     * Diese Methode erzeugt den "Spiel erstellen" - Button.
     *
     * @return Button
     */
    private static Button erstelleSpielErstellenButton() {
        Button neuesSpielErstellenButton = new Button(StringsammlungGUI.SCENE_NEUES_SPIEL_BUTTON_TEXT);
        neuesSpielErstellenButton.setCursor(Cursor.HAND);
        neuesSpielErstellenButton.setDefaultButton(true);
        neuesSpielErstellenButton.setStyle(StyleSpeicher.BUTTON);
        neuesSpielErstellenButton.setBackground(Background.EMPTY);
        neuesSpielErstellenButton.setFont(Font.font(null, FontWeight.BOLD, ZahlensammlungGUI.SCHRIFTGROESSE_20));
        return neuesSpielErstellenButton;
    }

    /**
     * Diese Methode erzeugt die SchwierigkeitsComboBox.
     *
     * @return ComboBox
     */
    private static ComboBox<Schwierigkeitsgrad> erstelleSchwierigkeitsgradComboBox() {
        ComboBox<Schwierigkeitsgrad> schwierigkeitsgradComboBox = new ComboBox<>();
        schwierigkeitsgradComboBox.setPromptText(StringsammlungGUI.SCENE_NEUES_SPIEL_PLATZHALTER_TEXT_COMBO_BOX);
        schwierigkeitsgradComboBox.getItems().setAll(Schwierigkeitsgrad.values());
        schwierigkeitsgradComboBox.setStyle(StyleSpeicher.FX_BACKGROUND_COLOR_CONTROL_INNER_BACKGROUND +
                StyleSpeicher.FX_ACCENT_ORANGE);
        schwierigkeitsgradComboBox.setBorder(new Border(new BorderStroke(Color.ORANGE, BorderStrokeStyle.SOLID, new CornerRadii(ZahlensammlungGUI.BORDER_RADII), BorderStroke.MEDIUM)));
        return schwierigkeitsgradComboBox;
    }

    /**
     * Diese Methode erzeugt das Textfeld fuer den Namen des Spielers.
     *
     * @return TextField
     */
    private static TextField erstelleNamensTextfeld() {
        TextField spielerNamenEingabeFeld = new TextField();
        spielerNamenEingabeFeld.setPrefHeight(ZahlensammlungGUI.NEUES_SPIEL_SPIELERNAME_HEIGHT);
        spielerNamenEingabeFeld.setBorder(new Border(new BorderStroke(Color.ORANGE, BorderStrokeStyle.SOLID, new CornerRadii(ZahlensammlungGUI.BORDER_RADII), BorderStroke.MEDIUM)));
        spielerNamenEingabeFeld.setStyle(StyleSpeicher.FX_BACKGROUND_COLOR_CONTROL_INNER_BACKGROUND);
        spielerNamenEingabeFeld.setPromptText(StringsammlungGUI.NEUESSPIEL_EINGABEFELD_NAME);
        return spielerNamenEingabeFeld;
    }

    /**
     * Diese Methode erstellt aus zwei Nodes ein Formular.
     *
     * @param node
     * @param node1
     * @return VBox
     */
    private static VBox erstelleEingabe(Node node, Node node1) {
        VBox vBox = new VBox(node, node1);
        vBox.setAlignment(Pos.BASELINE_LEFT);
        vBox.setSpacing(ZahlensammlungGUI.NEUESSPIEL_ERSTELLE_EINGABE_SPACING);
        return vBox;
    }

    /**
     * Durch diese Methode werden alle SpielerklassenViews erzeugt und einer ArrayList hinzugefuegt.
     */
    private static void erstelleAlleSpielerKlassenViews() {
        for (Spielerklasse spielerklasse : Spielerklasse.values()) {
            spielerklassen.add((BorderPane) new SpielerklassenView(spielerklasse).getRoot());
        }
    }

    /**
     * Diese Methode erzeugt eine Ansicht, in der der Spieler seine Spielerklasse auswaehlen kann.
     *
     * @return HBox
     */
    private static HBox erstelleKlassenAuswahl() {
        ImageView linkerPfeil = erstelleBild(PfadSammlung.PFAD_PFEIL_LINKS);
        linkerPfeil.setCursor(Cursor.HAND);
        fuehreKlickAufLinkenPfeilAus(linkerPfeil);
        ImageView rechterPfeil = erstelleBild(PfadSammlung.PFAD_PFEIL_RECHTS);
        rechterPfeil.setCursor(Cursor.HAND);
        fuehreKlickAufRechtenPfeilAus(rechterPfeil);
        HBox hBox = new HBox(linkerPfeil, ausgewaehlteSpielerklasseBorderPane, rechterPfeil);
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(ZahlensammlungGUI.NEUESSPIEL_KLASSENAUSWAHL_SPACING);
        return hBox;
    }

    /**
     * Durch diese Methode wechselt die Ansicht der Spielerklasse.
     * Der Spieler wechselt zu der in der ArrayList vorherigen Figur.
     *
     * @param rechterPfeil
     */
    private static void fuehreKlickAufRechtenPfeilAus(ImageView rechterPfeil) {
        rechterPfeil.setOnMouseClicked(mouseEvent -> {
            int i = gebeIndexVonSpielerklasse();
            spielerklassenHBox.getChildren().remove(1);
            if (i != Zahlensammlung.ANZAHL_SPIELERKLASSEN - 1) {
                spielerklassenHBox.getChildren().add(1, spielerklassen.get(i + 1));
                ausgewaehlteSpielerklasseBorderPane = spielerklassen.get(i + 1);
                ausgewaehlteSpielerKlasse = Spielerklasse.values()[i + 1];
            } else {
                spielerklassenHBox.getChildren().add(1, spielerklassen.get(0));
                ausgewaehlteSpielerklasseBorderPane = spielerklassen.get(0);
                ausgewaehlteSpielerKlasse = Spielerklasse.values()[0];
            }
        });
    }

    /**
     * Durch diese Methode wechselt die Ansicht der Spielerklasse.
     * Der Spieler wechselt zu der in der ArrayList nachfolgenden Figur.
     *
     * @param linkerPfeil
     */
    private static void fuehreKlickAufLinkenPfeilAus(ImageView linkerPfeil) {
        linkerPfeil.setOnMouseClicked(mouseEvent -> {
            int i = gebeIndexVonSpielerklasse();
            spielerklassenHBox.getChildren().remove(1);
            if (i != 0) {
                spielerklassenHBox.getChildren().add(1, spielerklassen.get(i - 1));
                ausgewaehlteSpielerklasseBorderPane = spielerklassen.get(i - 1);
                ausgewaehlteSpielerKlasse = Spielerklasse.values()[i - 1];
            } else {
                spielerklassenHBox.getChildren().add(1, spielerklassen.get(2));
                ausgewaehlteSpielerklasseBorderPane = spielerklassen.get(2);
                ausgewaehlteSpielerKlasse = Spielerklasse.values()[2];
            }
        });
    }

    /**
     * Diese Methode erzeugt das Bild fuer den Pfeil.
     *
     * @param pfad
     * @return ImageView
     */
    private static ImageView erstelleBild(String pfad) {
        Image image = new Image(pfad);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(ZahlensammlungGUI.SCHRIFTGROESSE_50);
        imageView.setFitHeight(ZahlensammlungGUI.SCHRIFTGROESSE_50);
        return imageView;
    }

    /**
     * Diese Methode sucht den Index der aktuellen Spielerklasse aus der ArrayList raus.
     *
     * @return int
     */
    private static int gebeIndexVonSpielerklasse() {
        int j = 0;
        for (int i = 0; i < spielerklassen.size(); i++) {
            if (ausgewaehlteSpielerklasseBorderPane == spielerklassen.get(i)) {
                j = i;
            }
        }
        return j;
    }

    @Override
    public Pane getRoot() {
        return root;
    }

    @Override
    public Viewable aktualisiere() {
        return new NeuesSpielView();
    }

    @Override
    public void gibHilfeAus() {
        HilfeAlertFabrik.zeigeHilfeAlertNeuesSpielView();
    }
}
