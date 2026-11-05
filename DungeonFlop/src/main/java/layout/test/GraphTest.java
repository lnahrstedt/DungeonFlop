package layout.test;

import graph.layout.ButtonKnoten;
import graph.layout.Graph;
import graph.layout.Kante;
import graph.layout.Knoten;
import javafx.scene.Group;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import model.raum.Raum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GraphTest extends Graph {

    final MouseGestures mouseGestures;
    /**
     * the pane wrapper is necessary or else the scrollpane would always align
     * the top-most and left-most child to the top and left eg when you drag the
     * top child down, the entire scrollpane would move down
     */
    final Pane cellLayer;
    private final List<Knoten> knoten = new ArrayList<>();
    private final List<Kante> kanten = new ArrayList<>();
    private final HashMap<Integer, Knoten> integerKnotenHashMap = new HashMap<>();
    private final ZoomableScrollPane scrollPane;

    public GraphTest() {

        Group canvas = new Group();
        cellLayer = new Pane();

        canvas.getChildren().add(cellLayer);

        mouseGestures = new MouseGestures(this);

        scrollPane = new ZoomableScrollPane(canvas);

        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

    }

    public ScrollPane getScrollPane() {
        return this.scrollPane;
    }

    public Pane getPane() {
        return this.cellLayer;
    }

    public void beginInit() {
    }

    public void endInit() {

        // add components to graph pane
        getPane().getChildren().addAll(getKanten());
        getPane().getChildren().addAll(getKnoten());


        // enable dragging of cells
        for (Knoten knoten : getKnoten()) {
            mouseGestures.makeDraggable(knoten);
        }

    }

    public void addKnoten(String text, int id, Raum raum, KnotenTypTest type) {

        switch (type) {

            case BUTTON:
                ButtonKnoten buttonCell = new ButtonKnoten(text, id, raum);
                addKnoten(buttonCell);
                break;

            case LABEL:
                LabelKnoten labelCell = new LabelKnoten(id, text);
                addKnoten(labelCell);
                break;

            default:
                throw new UnsupportedOperationException("Unsupported type: " + type);
        }
    }

    private void addKnoten(Knoten knoten) {

        getKnoten().add(knoten);

        integerKnotenHashMap.put(knoten.getKnotennummer(), knoten);

    }

    public void addKante(int ursprungskanteID, int zielkanteID) {

        Knoten sourceKnoten = integerKnotenHashMap.get(ursprungskanteID);
        Knoten targetKnoten = integerKnotenHashMap.get(zielkanteID);

        Kante kante = new Kante(sourceKnoten, targetKnoten);

        getKanten().add(kante);

    }

    public double getScale() {
        return this.scrollPane.getScaleValue();
    }
}
