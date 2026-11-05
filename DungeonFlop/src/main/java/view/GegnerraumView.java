package view;

import control.model.KampfController;
import control.view.GegnerraumViewController;
import control.view.HilfeAlertFabrik;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import model.Runde;
import model.Spieler;
import model.Spielfeld;
import model.Spielfigur;
import model.karte.Kartenverwaltung;
import model.netzwerk.NetzwerkFlagContainer;
import model.raum.Raum;
import resources.exceptions.FeldNichtVerfuegbarException;
import resources.interfaces.*;

import java.util.ArrayList;

/**
 * Diese Klasse enthaelt die Layout-Daten fuer den Gegnerraum-Bildschirm und erstellt daraus eine Scene.
 */
public class GegnerraumView implements Viewable {

    private static Rectangle[][] rectangles;
    private static BorderPane[][] spielfiguren;
    private static ArrayList<Rectangle> markierteSpielfelder = new ArrayList<>();
    private static StackPane[][] stackPanes;
    private static ArrayList<BorderPane> karten;
    private static BorderPane ausgewaehlteKarte;
    private Pane root;

    public GegnerraumView() {
        this.root = initRoot();
    }

    /**
     * Diese Methode initialisiert die Szene des Gegnerraums.
     *
     * @return Scene
     */
    private Pane initRoot() {
        BorderPane root = new BorderPane();
        root.setTop(ErstelleTopView.generiereTopView());
        root.setTop(ErstelleMenueView.generiereMenu());

        VBox kartenverwaltung = erstelleKartenverwaltung(root);

        Text spielerbezeichnung = new Text();
        if (NetzwerkFlagContainer.binIchClient()) {
            spielerbezeichnung.setText(StringsammlungGUI.CLIENT);
        } else {
            spielerbezeichnung.setText(StringsammlungGUI.SERVER);
        }

        Button rundeBeendenButton = erstelleRundeBeendenButton();
        VBox rundeBeenden = new VBox(kartenverwaltung, rundeBeendenButton);
        rundeBeenden.setAlignment(Pos.BOTTOM_RIGHT);
        rundeBeenden.setSpacing(ZahlensammlungGUI.GEGNERRAUM_RUNDE_BEENDEN_SPACING);
        rundeBeenden.setPadding(ZahlensammlungGUI.GEGNERRAUM_RUNDE_BEENDEN_INSETS);

        GridPane gridPane = erstelleSpielfeld(root);
        VBox vBox = new VBox(gridPane);
        vBox.setSpacing(ZahlensammlungGUI.GEGNERRAUM_MIDDLE_VBOX_SPACING);
        vBox.setAlignment(Pos.CENTER);

        aktualisiereRundenBeendenButton(gridPane, rundeBeenden);

        HBox hBoxSpieler = new SpielerInfoView().getInfoUeberSpieler();
        hBoxSpieler.setAlignment(Pos.TOP_LEFT);
        hBoxSpieler.setPadding(ZahlensammlungGUI.GEGNERRAUM_HBOXSPIELER_INSETS);

        HBox mana = erstelleSol(KampfController.getAktuelleRunde());
        BorderPane spielfigurGross = new BorderPane();
        spielfigurGross.setMinSize(ZahlensammlungGUI.SPIELFIGURGROSS_BREITE, ZahlensammlungGUI.SPIELFIGURGROSS_HOEHE);
        spielfigurGross.setMaxSize(ZahlensammlungGUI.SPIELFIGURGROSS_BREITE, ZahlensammlungGUI.SPIELFIGURGROSS_HOEHE);
        VBox left = new VBox(hBoxSpieler, spielfigurGross, mana);
        left.setAlignment(Pos.CENTER);
        left.setSpacing(ZahlensammlungGUI.GEGNERRAUMVIEW_LEFT_SPACING);
        left.setPadding(ZahlensammlungGUI.GEGNERRAUMVIEW_LEFT_INSETS);

        HBox leftHbox = new HBox(left);
        leftHbox.setAlignment(Pos.CENTER);

        HBox hBox = new HBox(vBox);
        hBox.setAlignment(Pos.CENTER);

        root.setCenter(hBox);
        root.setRight(rundeBeenden);
        root.setLeft(leftHbox);
        root.setBackground(Background.fill(Color.WHITE));

        return root;
    }

    /**
     * Diese Methode erstellt das Spielfeld.
     *
     * @return GridPane
     */
    public static GridPane erstelleSpielfeld(BorderPane root) {
        Spielfeld spielfeld = KampfController.getKampf().getSpielfeld();
        Runde runde = KampfController.getAktuelleRunde();
        initalisiereArrays(spielfeld);
        GridPane gridPane = erstelleGridPane();
        for (int i = 0; i < spielfeld.getBreite(); i++) {
            for (int j = 0; j < spielfeld.getHoehe(); j++) {
                StackPane stackPane = new StackPane();
                Rectangle rectangle = erstelleRechteck();
                fuegeFeldHinzu(rectangle, stackPane, j, i);
                try {
                    if (spielfeld.getSpielfigur(j, i) != null) {
                        erstelleSpielfigur(runde, spielfeld, stackPane, j, i, rectangle, root);
                    }
                } catch (FeldNichtVerfuegbarException e) {
                    e.printStackTrace();
                }
                stackPanes[j][i] = stackPane;
                gridPane.add(stackPane, i, j);
                GegnerraumViewController.bewegeFigur(spielfeld, runde);
            }
        }
        return gridPane;
    }

    /**
     * Diese Methode erstellt die Felder, auf denen sich die Figuren bewegen.
     *
     * @return Rectangle
     */
    private static Rectangle erstelleRechteck() {
        Rectangle rectangle = new Rectangle(ZahlensammlungGUI.FELDGROESSE_BREITE, ZahlensammlungGUI.FELDGROESSE_HOEHE);
        rectangle.setFill(FarbenSammlung.FELDERFARBE);
        rectangle.setStroke(FarbenSammlung.FELDRANDFARBE);
        rectangle.setStrokeWidth(ZahlensammlungGUI.GEGNERRAUM_RECHTECK_STROKE_WIDTH);
        rectangle.setArcWidth(ZahlensammlungGUI.GEGNERRAUM_RECHTECK_ARC_WIDTH_HEIGHT);
        rectangle.setArcHeight(ZahlensammlungGUI.GEGNERRAUM_RECHTECK_ARC_WIDTH_HEIGHT);
        return rectangle;
    }

    /**
     * Diese Methode initialisiert die benoetigten Arrays, um anschliessend die benoetigten Nodes zu individualisieren.
     *
     * @param spielfeld
     */
    private static void initalisiereArrays(Spielfeld spielfeld) {
        rectangles = new Rectangle[spielfeld.getHoehe()][spielfeld.getBreite()];
        spielfiguren = new BorderPane[spielfeld.getHoehe()][spielfeld.getBreite()];
        stackPanes = new StackPane[spielfeld.getHoehe()][spielfeld.getBreite()];
    }

    /**
     * Diese Methode fuegt ein Feld auf dem Spielfeld hinzu.
     *
     * @param rectangle
     * @param stackPane
     * @param hoehe
     * @param breite
     */
    private static void fuegeFeldHinzu(Rectangle rectangle, StackPane stackPane, int hoehe, int breite) {
        rectangles[hoehe][breite] = rectangle;
        stackPane.getChildren().add(rectangle);
    }

    /**
     * Diese Methode positioniert die Figur mittig in ihrem Feld.
     */
    private static void positioniereFigurMittig(BorderPane spielfigur) {
        GridPane.setHalignment(spielfigur, HPos.CENTER);
        GridPane.setValignment(spielfigur, VPos.CENTER);
    }

    /**
     * Diese Methode fuegt einem Feld die Spielfigur hinzu.
     *
     * @param stackPane
     * @param hoehe
     * @param breite
     */
    private static void fuegeFigurHinzu(BorderPane spielfigur, StackPane stackPane, int hoehe, int breite) {
        spielfiguren[hoehe][breite] = spielfigur;
        stackPane.getChildren().add(spielfigur);
    }

    /**
     * Diese Methode erzeugt eine GridPane.
     *
     * @return GridPane
     */
    private static GridPane erstelleGridPane() {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(ZahlensammlungGUI.GEGNERRAUM_GRIDPANE_HVGAP);
        gridPane.setVgap(ZahlensammlungGUI.GEGNERRAUM_GRIDPANE_HVGAP);
        return gridPane;
    }

    /**
     * Diese Methode erzeugt die Spielfigur.
     *
     * @return Circle
     */
    private static BorderPane erstelleSpielfigur(Spielfigur spielfigur, Color color, BorderPane root, Runde runde) {
        KartenView spielfigurenView = new KartenView(spielfigur, color);
        BorderPane ansicht = spielfigurenView.getBorderPane();
        ansicht.setCursor(Cursor.HAND);
        ansicht.setOnMouseEntered(GegnerraumViewController.hovernAufSpielfigurEnter(spielfigur, root, runde));
        ansicht.setOnMouseExited(GegnerraumViewController.hovernAufSpielfigurExit(root));
        return ansicht;
    }

    /**
     * Diese Methode erzeugt die Spielfiguren. Dabei werden die Farben entsprechend gesetzt.
     *
     * @param runde
     * @param spielfeld
     * @param stackPane
     * @param hoehe
     * @param breite
     * @throws FeldNichtVerfuegbarException
     */
    private static void erstelleSpielfigur(Runde runde, Spielfeld spielfeld, StackPane stackPane, int hoehe, int breite, Rectangle feld, BorderPane root) throws FeldNichtVerfuegbarException {
        BorderPane spielfigur = null;

        if (spielfeld.getSpielfigurenAufDemFeld().get(runde.getSpieler()).contains(spielfeld.getSpielfigur(hoehe, breite))) {
            spielfigur = erstelleSpielfigur(spielfeld.getFelder()[hoehe][breite], FarbenSammlung.SPIELERFARBE, root, runde);
            feld.setStroke(FarbenSammlung.SPIELERFARBE);
        } else {
            spielfigur = erstelleSpielfigur(spielfeld.getFelder()[hoehe][breite], FarbenSammlung.GEGNERFARBE, root, runde);
            feld.setStroke(FarbenSammlung.GEGNERFARBE);
        }
        fuegeFigurHinzu(spielfigur, stackPane, hoehe, breite);
        positioniereFigurMittig(spielfigur);
        GegnerraumViewController.waehleFigur(spielfigur, spielfeld, runde);
    }

    /**
     * Diese Methode erstellt den Button, der es ermoeglicht die aktuelle Runde zu beenden,
     *
     * @return Button
     */
    private static Button erstelleRundeBeendenButton() {
        Button button = new Button();
        button.setText(StringsammlungGUI.RUNDE_BEENDEN_BUTTON);
        button.setFont(Font.font(null, FontWeight.BOLD, ZahlensammlungGUI.SCHRIFTGROESSE_25));
        button.setStyle(StyleSpeicher.BUTTON);
        button.setBackground(Background.EMPTY);
        button.setCursor(Cursor.HAND);
        GegnerraumViewController.beendeRunde(button);
        return button;
    }

    /**
     * Diese Methode erzeugt die Kartenverwaltung in einer HBox
     *
     * @return HBox
     */
    private static VBox erstelleKartenverwaltung(BorderPane root) {
        karten = new ArrayList<>();
        Spieler aktuellerSpieler = KampfController.getAktuelleRunde().getSpieler();
        Kartenverwaltung spielerKartenverwaltung = KampfController.getKampf().getSpielerKartenverwaltung().get(aktuellerSpieler);
        VBox handkarten = new VBox();
        for (int i = 0; i < spielerKartenverwaltung.getHandkarten().size(); i++) {
            if (NetzwerkFlagContainer.binIchAnDerReihe()) {
                BorderPane karte = new KartenView(spielerKartenverwaltung.getHandkarten().get(i), FarbenSammlung.SPIELERFARBE).getBorderPane();
                karte.setOnMouseEntered(GegnerraumViewController.hovernAufKarteEnter(spielerKartenverwaltung.getHandkarten().get(i), root));
                karte.setOnMouseExited(GegnerraumViewController.hovernAufSpielfigurExit(root));
                karten.add(karte);
                GegnerraumViewController.karteAuswaehlen(karte);
                karte.setCursor(Cursor.HAND);
                handkarten.getChildren().add(karte);
            } else {
                BorderPane karte = new KartenView(FarbenSammlung.SPIELERFARBE).getBorderPane();
                karten.add(karte);
                GegnerraumViewController.karteAuswaehlen(karte);
                karte.setCursor(Cursor.HAND);
                handkarten.getChildren().add(karte);
            }
        }
        handkarten.setAlignment(Pos.CENTER);
        handkarten.setSpacing(ZahlensammlungGUI.GEGNERRAUM_HANDKARTEN_SPACING);
        return handkarten;
    }

    private static Text erstelleRaumname(Raum raum) {
        Font font = new Font(ZahlensammlungGUI.SCHRIFTGROESSE_20);
        Text text = new Text(raum.getName());
        text.setTextAlignment(TextAlignment.CENTER);
        text.setFont(font);
        return text;
    }

    /**
     * Erstellt die Solanzeige.
     *
     * @param runde
     * @return
     */
    private static HBox erstelleSol(Runde runde) {
        ImageView imageView = erstelleBild(PfadSammlung.PFAD_SOL);
        HBox hBox = new HBox(imageView);
        hBox.setSpacing(ZahlensammlungGUI.GEGNERRAUM_MANA_HBOX_SPACING);
        hBox.setAlignment(Pos.CENTER);
        Font font = new Font(ZahlensammlungGUI.GEGNERRAUMVIEW_MANA_HBOX_SPACING);
        Text text = new Text(StringsammlungGUI.SOL + runde.getAktuelleMana());
        text.setTextAlignment(TextAlignment.CENTER);
        text.setFont(font);
        ProgressBar progressBar = new ProgressBar(1);
        progressBar.setProgress(((1 - (1 - (double) runde.getAktuelleMana() / (double) runde.getSpieler().getMana()))));
        progressBar.setStyle(StyleSpeicher.FX_ACCENT_ORANGE);
        VBox vBox = new VBox(text, progressBar);
        vBox.setSpacing(ZahlensammlungGUI.GEGNERRAUMVIEW_MANA_VBOX_SPACING);
        hBox.getChildren().add(vBox);
        hBox.setPadding(ZahlensammlungGUI.GEGNERRAUM_MANA_HBOX_INSETS);
        return hBox;
    }

    /**
     * Erstellt ein ImageView aus dem gegebenen Pfad.
     *
     * @param pfad
     * @return
     */
    private static ImageView erstelleBild(String pfad) {
        Image image = new Image(pfad);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(ZahlensammlungGUI.FELDGROESSE_HOEHE * ZahlensammlungGUI.GEGNERRAUM_BILDSKALIERUNG);
        imageView.setFitWidth(ZahlensammlungGUI.FELDGROESSE_HOEHE * ZahlensammlungGUI.GEGNERRAUM_BILDSKALIERUNG);
        return imageView;
    }

    /**
     * Deaktiviert den RundeBeenden Button, wenn die Spieler nicht an der Reihe ist.
     *
     * @param gridPane
     * @param rundeBeenden
     */
    private static void aktualisiereRundenBeendenButton(GridPane gridPane, VBox rundeBeenden) {
        if (NetzwerkFlagContainer.wirdOnlineGespielt()) {
            if (!NetzwerkFlagContainer.binIchAnDerReihe()) {
                rundeBeenden.setDisable(true);
            }
        }
    }

    @Override
    public Pane getRoot() {
        return root;
    }

    @Override
    public Viewable aktualisiere() {
        return new GegnerraumView();
    }

    @Override
    public void gibHilfeAus() {
        HilfeAlertFabrik.zeigeHilfeAlertGegnerraumView();
    }

    public static Rectangle[][] getRectangles() {
        return rectangles;
    }

    public static BorderPane[][] getSpielfiguren() {
        return spielfiguren;
    }

    public static ArrayList<Rectangle> getMarkierteSpielfelder() {
        return markierteSpielfelder;
    }

    public static void setMarkierteSpielfelder(ArrayList<Rectangle> markierteSpielfelder) {
        GegnerraumView.markierteSpielfelder = markierteSpielfelder;
    }

    public static ArrayList<BorderPane> getKarten() {
        return karten;
    }

    public static BorderPane getAusgewaehlteKarte() {
        return ausgewaehlteKarte;
    }

    public static void setAusgewaehlteKarte(BorderPane ausgewaehlteKarte) {
        GegnerraumView.ausgewaehlteKarte = ausgewaehlteKarte;
    }

    public static StackPane[][] getStackPanes() {
        return stackPanes;
    }
}