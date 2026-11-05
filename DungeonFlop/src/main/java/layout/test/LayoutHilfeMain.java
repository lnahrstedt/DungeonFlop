package layout.test;

import graph.layout.LayoutEbene6;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.HauptSpieler;
import model.Spielwelt;
import model.raum.Raum;
import resources.enums.Spielerklasse;
import view.Buehne;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LayoutHilfeMain extends Application {
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

        BorderPane.setAlignment(graph.getScrollPane(), Pos.CENTER);

        root.setCenter(graph.getScrollPane());
        root.setBottom(button);

        Scene scene = new Scene(root, 1024, 768);

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

}