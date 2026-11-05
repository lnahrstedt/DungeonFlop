package layout.test;

import graph.layout.LayoutEbene6;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.HauptSpieler;
import model.Spielwelt;
import model.raum.Raum;
import resources.enums.Spielerklasse;
import resources.interfaces.ZahlensammlungGUI;
import view.Buehne;
import view.DeckView;
import view.ErstelleMenueView;
import view.InventarView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LayoutHilfeMain2 extends Application {
    GraphTest graph = new GraphTest();

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) {
        HauptSpieler hauptSpieler = new HauptSpieler("Ole", Spielerklasse.AMOS);
        Spielwelt.getInstance().setProtagonist(hauptSpieler);
        Buehne.getInstance().setStage(primaryStage);

        BorderPane root = new BorderPane();
        graph = new GraphTest();
        Button button = new Button("Koordinaten! :D");
        button.setOnAction(actionEvent -> {
            PrintLayout specificLayout = new PrintLayout(graph);
            specificLayout.execute();
        });

        VBox vBox = new VBox(graph.getPane());
        vBox.setAlignment(Pos.CENTER);

        HBox hBox = new HBox(vBox);
        hBox.setAlignment(Pos.CENTER);

        root.setCenter(hBox);
        root.setTop(ErstelleMenueView.generiereMenu());
        root.setBottom(erstelleBottom());

        Scene scene = new Scene(root, ZahlensammlungGUI.SZENEN_BREITE, ZahlensammlungGUI.SZENEN_HOEHE);

        primaryStage.setScene(scene);
        primaryStage.show();

        addGraphComponents();

        LayoutEbene6 layout = new LayoutEbene6(graph);
        layout.richteGraphAus();

    }


    private void addGraphComponents() {

        HashMap<Raum, ArrayList<Raum>> raeumeUndNachbarraeume = Spielwelt.getInstance().getEbenen().get(6).getLayout();
        HashMap<Integer, ArrayList<Raum>> raumnummerUndNachbarraeume = new HashMap<>();

        for (Map.Entry<Raum, ArrayList<Raum>> entry : raeumeUndNachbarraeume.entrySet()) {
            raumnummerUndNachbarraeume.put(entry.getKey().getRaumnummer(), entry.getValue());
        }

        graph.beginInit();

        for (Map.Entry<Raum, ArrayList<Raum>> entry : raeumeUndNachbarraeume.entrySet()) {
            graph.addKnoten(entry.getKey().getName(), entry.getKey().getRaumnummer(), entry.getKey(), KnotenTypTest.BUTTON);
        }

        for (int i = 0; i < raumnummerUndNachbarraeume.size(); i++) {
            for (int j = 0; j < raumnummerUndNachbarraeume.get(i).size(); j++) {
                graph.addKante(i, raumnummerUndNachbarraeume.get(i).get(j).getRaumnummer());
            }
        }
        graph.endInit();

    }

    private HBox erstelleBottom() {
        return new HBox(InventarView.erstelleInventarButton(), DeckView.erstelleDeckKnopf());
    }

}