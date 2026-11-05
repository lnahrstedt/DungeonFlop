package view;

import control.view.HilfeAlertFabrik;
import graph.layout.Graph;
import graph.layout.GraphController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

/**
 * Diese Klasse enthaelt die Layout-Daten fuer den EbenenLayout-Bildschirm und erstellt daraus eine Scene.
 */
public class EbenenLayoutView implements Viewable {

    private Pane root;

    public EbenenLayoutView() {
        this.root = initRoot();
    }

    /**
     * Diese Methode erstellt und gibt die Scene, welche das Layout der aktuellen Ebene zeigt, zurueck.
     *
     * @return Scene des EbenenLayouts
     */
    private Pane initRoot() {

        BorderPane root = new BorderPane();
        Graph graph = new Graph();

        GraphController.initGraph(graph);
        GraphController.richteGraphAus(graph);

        VBox vBox = new VBox(graph.getPane());
        vBox.setAlignment(Pos.CENTER);

        HBox hBox = new HBox(vBox);
        hBox.setAlignment(Pos.CENTER);

        HBox spielerInfo = new SpielerInfoView().getInfoUeberSpieler();
        spielerInfo.setAlignment(Pos.TOP_LEFT);
        spielerInfo.setPadding(new Insets(40, 0, 0, 40));

        VBox vBoxSpielerinfo = new VBox(spielerInfo);
        vBoxSpielerinfo.setAlignment(Pos.TOP_LEFT);

        HBox spielfigurenInfo = new SpielfigurenInfoView().getInfoUeberSpielfigur();
        spielfigurenInfo.setAlignment(Pos.TOP_RIGHT);
        spielfigurenInfo.setPadding(new Insets(40, 30, 0, 0));

        VBox vBoxSpielfigurenInfo = new VBox(spielfigurenInfo);
        vBoxSpielfigurenInfo.setAlignment(Pos.TOP_RIGHT);

        root.setLeft(vBoxSpielerinfo);
        root.setTop(ErstelleMenueView.generiereMenu());
        root.setCenter(hBox);
        root.setRight(vBoxSpielfigurenInfo);
        root.setBottom(erstelleBottom());
        root.setBackground(Background.fill(Color.WHITE));

        return root;
    }

    /**
     * Diese Methode gibt die Fussleiste mit dem Inventar- und dem Deck-Knopf zurueck.
     *
     * @return HBox der Fussleiste
     */
    private HBox erstelleBottom() {
        return new HBox(InventarView.erstelleInventarButton(), DeckView.erstelleDeckKnopf());
    }

    @Override
    public Pane getRoot() {
        return root;
    }

    @Override
    public Viewable aktualisiere() {
        return new EbenenLayoutView();
    }

    @Override
    public void gibHilfeAus() {
        HilfeAlertFabrik.zeigeHilfeAlertEbenenLayoutView();
    }
}
