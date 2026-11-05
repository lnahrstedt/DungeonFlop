package graph.layout;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Diese Klasse repraesentiert Knoten in einem Graphen.
 * In Bezug auf das Spiel stellen die Knoten Raeume in der Ebene dar.
 */
public class Knoten extends Pane implements Comparable<Knoten> {

    private final int knotennummer;

    private final List<Knoten> kinder = new ArrayList<>();
    private final List<Knoten> eltern = new ArrayList<>();

    private Node view;

    public Knoten(int knotennummer) {
        this.knotennummer = knotennummer;
    }

    public void addKindknoten(Knoten knoten) {
        kinder.add(knoten);
    }

    public void addElternknoten(Knoten knoten) {
        eltern.add(knoten);
    }

    public Node getView() {
        return this.view;
    }

    public void setView(Node view) {

        this.view = view;
        getChildren().add(view);

    }

    public int getKnotennummer() {
        return knotennummer;
    }

    @Override
    public int compareTo(@NotNull Knoten knoten) {
        return Integer.compare(this.knotennummer, knoten.knotennummer);
    }
}