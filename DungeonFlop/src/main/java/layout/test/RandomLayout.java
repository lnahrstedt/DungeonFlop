package layout.test;

import graph.layout.Graph;
import graph.layout.Knoten;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RandomLayout {

    final Graph graph;

    final Random rnd = new Random();

    public RandomLayout(Graph graph) {

        this.graph = graph;

    }

    public void execute() {

        List<Knoten> knotens = graph.getKnoten();
        Collections.sort(knotens);

        for (Knoten knoten : knotens) {

            double x = rnd.nextDouble() * 500;
            double y = rnd.nextDouble() * 500;

            knoten.relocate(x, y);

        }

    }

}
