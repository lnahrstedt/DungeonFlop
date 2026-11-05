package layout.test;

import graph.layout.Graph;
import graph.layout.Knoten;

import java.util.Collections;
import java.util.List;

public class PrintLayout {
    private final Graph graph;

    public PrintLayout(Graph graph) {

        this.graph = graph;

    }

    public void execute() {

        List<Knoten> knotens = graph.getKnoten();
        Collections.sort(knotens);

        for (Knoten knoten : knotens) {

            System.out.println(knoten.getKnotennummer());
            System.out.println(knoten.getLayoutX());
            System.out.println(knoten.getLayoutY());


        }

    }

}
