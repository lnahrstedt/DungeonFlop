package view;

import control.view.HilfeAlertFabrik;
import control.view.SpielLadenViewController;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import resources.interfaces.StringsammlungGUI;
import resources.interfaces.StyleSpeicher;
import resources.interfaces.Zahlensammlung;
import resources.interfaces.ZahlensammlungGUI;
import utilities.MeinIO;

import java.io.File;
import java.util.List;

/**
 * Enthaelt die Layout-Daten fuer den Spiel-laden-Bildschirm und erstellt daraus eine Scene.
 */
public class SpielLadenView implements Viewable {

    private Pane root;

    public SpielLadenView() {
        this.root = initPane();
    }

    /**
     * Erstellt und gibt die Szene mit dem Spiel-laden-Bildschirm zurueck.
     *
     * @return Scene spielLaden
     */
    private Pane initPane() {

        BorderPane root = new BorderPane();
        root.setTop(ErstelleTopView.generiereTopView());
        root.setBottom(Zurueckknopf.erstelleZurueckKnopf());

        root.setCenter(erstelleSpielstaende());

        root.setBackground(Background.fill(Color.WHITE));

        return root;
    }

    /**
     * Erstellt eine Ansicht der bereits gespeicherten Spielstaende.
     *
     * @return ScrollPane
     */
    public ScrollPane erstelleSpielstaende() {
        List<File> spielstaende = SpielLadenViewController.zeigeGespeicherteSpiele();
        VBox vBox = new VBox();
        ScrollPane scrollPane = new ScrollPane();
        if (spielstaende.size() != 0) {
            for (int i = 0; i < spielstaende.size(); i++) {
                Text spielstand = new Text(getDatumUhrzeitAusDateiname(spielstaende.get(i).toString()));
                spielstand.setFont(new Font(ZahlensammlungGUI.SCHRIFTGROESSE_25));
                Button spielLadenButton = new Button(StringsammlungGUI.SCENE_SPIEL_LADEN_BUTTON_TEXT);
                spielLadenButton.setPrefHeight(ZahlensammlungGUI.SPIELLADEN_KNOPF_HOEHE);
                spielLadenButton.setPrefWidth(ZahlensammlungGUI.SPIELLADEN_KNOPF_BREITE);
                int finalI = i;
                spielLadenButton.setOnAction(actionEvent -> SpielLadenViewController.setSpielwelt(MeinIO.ladeSpielAusDatei(spielstaende.get(finalI).toString())));
                HBox hBox1 = new HBox(spielstand, spielLadenButton);
                vBox.getChildren().add(hBox1);
                vBox.getChildren().add(new Separator());
            }
        } else {
            Text keineSpielstaendeVorhanden = new Text(StringsammlungGUI.KEINE_SPIELSTAENDE_VORHANDEN);
            vBox.getChildren().add(keineSpielstaendeVorhanden);
        }
        scrollPane.setContent(vBox);
        scrollPane.setMaxHeight(ZahlensammlungGUI.START_BACKGROUND_HOEHE);
        scrollPane.setMaxWidth(ZahlensammlungGUI.START_BACKGROUND_BREITE);
        scrollPane.setBorder(new Border(new BorderStroke(Color.ORANGE, BorderStrokeStyle.SOLID, new CornerRadii(ZahlensammlungGUI.BORDER_RADII), BorderStroke.MEDIUM)));
        scrollPane.setStyle(StyleSpeicher.SCROLL_PANE_BACKGROUND_WHITE);
        return scrollPane;
    }

    /**
     * Diese Methode extrahiert Datum und Uhrzeit aus einem Dateinamen-String
     *
     * @param dateiname
     * @return Datum und Uhrzeit als String
     */
    private String getDatumUhrzeitAusDateiname(String dateiname) {
        String datumUndUhrzeit;
        int stringlaenge = dateiname.length();
        datumUndUhrzeit = dateiname.substring(stringlaenge - Zahlensammlung.DATEINAME_ENDUNG_PLUS_DATUMUHRZEIT_LAENGE, stringlaenge - Zahlensammlung.DATEINAME_ENDUNG_LAENGE);
        return datumUndUhrzeit;
    }

    /**
     * Gibt die Wurzel dieses Views zurueck.
     *
     * @return root des SpielLadenViews
     */
    @Override
    public Pane getRoot() {
        return root;
    }

    @Override
    public Viewable aktualisiere() {
        return new SpielLadenView();
    }

    @Override
    public void gibHilfeAus() {
        HilfeAlertFabrik.zeigeHilfeAlertSpielLadenView();
    }
}
