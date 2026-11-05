package graph.layout;

import graph.layout.ButtonKnoten;
import graph.layout.Kante;
import graph.layout.Knoten;
import graph.layout.KnotenTyp;
import javafx.scene.layout.Pane;
import model.raum.Raum;
import resources.interfaces.StringsammlungException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Diese Klasse ermoeglicht die Darstellung des Layouts einer Ebene in Form eines ungerichteten Graphen.
 * Dieser Graph besteht aus Knoten und Kanten.
 * Die Knoten repraesentieren hierbei die unterschiedlichen Raeume einer Ebene.
 * Wie die Knoten, also die Raeume, miteinander verbunden sind, visualisieren die Kanten.
 */
public class Graph {

    private final List<Knoten> knoten = new ArrayList<>();
    private final List<Kante> kanten = new ArrayList<>();
    private final HashMap<Integer, Knoten> integerKnotenHashMap = new HashMap<>();

    private Pane pane = null;

    public Graph() {

        this.pane = new Pane();

    }

    /**
     * Diese Methode dient als Schnittstelle zur Hinzufuegung von Knoten.
     *
     * @param text
     * @param id
     * @param raum
     * @param type
     */
    public void addKnoten(String text, int id, Raum raum, KnotenTyp type) {

        if (type == KnotenTyp.BUTTON) {
            ButtonKnoten buttonCell = new ButtonKnoten(text, id, raum);
            addKnoten(buttonCell);
        } else {
            throw new UnsupportedOperationException(StringsammlungException.KNOTENTYP_WIRD_NICHT_UNTERSTUETZT + type);
        }
    }

    /**
     * Diese Methode fuegt dem Graphen Knoten hinzu.
     *
     * @param knoten
     */
    private void addKnoten(Knoten knoten) {

        getKnoten().add(knoten);
        integerKnotenHashMap.put(knoten.getKnotennummer(), knoten);

    }

    /**
     * Diese Methode fuegt dem Graphen Kanten hinzu.
     *
     * @param ursprungskanteID
     * @param zielkanteID
     */
    public void addKante(int ursprungskanteID, int zielkanteID) {

        Knoten sourceKnoten = integerKnotenHashMap.get(ursprungskanteID);
        Knoten targetKnoten = integerKnotenHashMap.get(zielkanteID);

        Kante kante = new Kante(sourceKnoten, targetKnoten);

        getKanten().add(kante);
    }

    /**
     * Diese Methode wurde rein der Uebersicht halber implementiert.
     */
    public void beginInit() {
    }

    /**
     * Diese Methode schliesst die Initialisierung des Graphen ab, indem sie alle erstellten Knoten und Kanten dem pane hinzufuegt.
     * Nun ist der Graph visualisierbar.
     */
    public void endInit() {

        getPane().getChildren().addAll(getKanten());
        getPane().getChildren().addAll(getKnoten());

    }

    public Pane getPane() {
        return pane;
    }

    public List<Knoten> getKnoten() {
        return knoten;
    }

    public List<Kante> getKanten() {
        return kanten;
    }

}
