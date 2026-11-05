package view;

import control.view.HilfeAlertFabrik;
import control.view.SpieleinstellungenViewController;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import resources.interfaces.StringsammlungGUI;
import resources.interfaces.Zahlensammlung;
import resources.interfaces.ZahlensammlungGUI;

/**
 * Diese Klasse enthaelt die Layout-Daten fuer den Spieleinstellung-Bildschirm und erstellt daraus eine Scene.
 */
public class SpieleinstellungenView implements Viewable {

    private Pane root;

    public SpieleinstellungenView() {

        this.root = initRoot();
    }

    /**
     * Erstellt einen Slider zur Regulierung der Lautstaerke von Musik bzw. Soundeffekten.
     *
     * @return Slider
     */
    private static Slider erstelleSlider() {
        Slider gesamtSlider = new Slider();
        gesamtSlider.setPrefWidth(Zahlensammlung.SLIDERLAENGE);
        gesamtSlider.setPrefHeight(Zahlensammlung.SLIDERHOEHE);
        gesamtSlider.setMin(Zahlensammlung.SLIDERMIN);
        gesamtSlider.setMax(Zahlensammlung.SLIDERMAX);
        gesamtSlider.setValue(ZahlensammlungGUI.EINSTELLUNGEN_SLIDER_VALUE);
        return gesamtSlider;
    }

    /**
     * Erstellt und gibt eine VBox mit drei Elementen (Texte bzw. Steuerelemente) zurueck.
     *
     * @param node
     * @param node1
     * @param node2
     * @return VBox
     */
    private static VBox erstelleVBox(Node node, Node node1, Node node2) {
        VBox optionVBox = new VBox(node, node1, node2);
        optionVBox.setAlignment(Pos.CENTER_LEFT);
        optionVBox.setSpacing(Zahlensammlung.VBOXSPACING);
        return optionVBox;
    }

    /**
     * Erstellt und gibt eine VBox mit zwei Elementen zurueck.
     *
     * @param node
     * @param node1
     * @return HBox
     */
    private static HBox erstelleHBox(Node node, Node node1) {
        HBox optionHBox = new HBox(node, node1);
        optionHBox.setAlignment(Pos.TOP_CENTER);
        optionHBox.setSpacing(Zahlensammlung.HBOXSPACING);
        return optionHBox;
    }

    /**
     * Erstellt und gibt einen Text zurueck.
     *
     * @param text
     * @param font
     * @return Text
     */
    private static Text erstelleText(String text, Font font) {
        Text info = new Text(text);
        info.setFont(font);
        return info;
    }

    /**
     * Erstellt und gibt die Szene mit den Spieleinstellungen zurueck.
     *
     * @return Scene Spieleinstellungen
     */
    private Pane initRoot() {

        BorderPane root = new BorderPane();
        root.setTop(ErstelleTopView.generiereTopView());

        Text gesamtText = erstelleText(StringsammlungGUI.OPTION_MUSIKLAUTSTAERKE, Font.font(ZahlensammlungGUI.SCHRIFTGROESSE_25));
        Slider gesamtSlider = erstelleSlider();
        SpieleinstellungenViewController.setMusikLautstaerke(gesamtSlider);

        Text effektText = erstelleText(StringsammlungGUI.OPTION_EFFEKTLAUTSTAERKE, Font.font(ZahlensammlungGUI.SCHRIFTGROESSE_25));
        Slider effektSlider = erstelleSlider();
        SpieleinstellungenViewController.setSoundLautstaerke(effektSlider);

        Text vollBildText = erstelleText(StringsammlungGUI.OPTION_VOLLBILD, Font.font(ZahlensammlungGUI.SCHRIFTGROESSE_25));
        CheckBox vollbildCheckBox = new CheckBox();
        vollbildCheckBox.setSelected(Buehne.getInstance().getStage().isFullScreen());
        SpieleinstellungenViewController.setFullScreen(vollbildCheckBox);

        VBox optionTextVBox = erstelleVBox(gesamtText, effektText, vollBildText);

        VBox optionSliderVBox = erstelleVBox(gesamtSlider, effektSlider, vollbildCheckBox);

        HBox optionHBox = erstelleHBox(optionTextVBox, optionSliderVBox);
        root.setCenter(optionHBox);
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
        return new SpieleinstellungenView();
    }

    @Override
    public void gibHilfeAus() {
        HilfeAlertFabrik.zeigeHilfeAlertSpieleinstellungenView();
    }
}
