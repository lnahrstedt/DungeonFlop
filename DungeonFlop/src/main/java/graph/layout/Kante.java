package graph.layout;

import graph.layout.Knoten;
import javafx.scene.Group;
import javafx.scene.shape.Line;

/**
 * Diese Klasse dient der Visualisierung der Verbundenheit einzelner Knoten.
 * Bezogen auf das Spiel bedeutet das, dass Kanten Raeume mit ihren Nachbarraeumen verbinden.
 */
public class Kante extends Group {

    private Line line = null;

    public Kante(Knoten ursprungsKnoten, Knoten zielKnoten) {

        this.line = new Line();

        initKante(ursprungsKnoten, zielKnoten, this.line);
        getChildren().add(this.line);

    }

    /**
     * Diese Methode ist fuer die Erstellung einer Kante verantwortlich.
     *
     * @param ursprungsKnoten
     * @param zielKnoten
     * @param line
     */
    private static void initKante(Knoten ursprungsKnoten, Knoten zielKnoten, Line line) {
        ursprungsKnoten.addKindknoten(zielKnoten);
        zielKnoten.addElternknoten(ursprungsKnoten);

        line.startXProperty().bind(ursprungsKnoten.layoutXProperty().add(ursprungsKnoten.getBoundsInParent().getWidth()));
        line.startYProperty().bind(ursprungsKnoten.layoutYProperty().add(ursprungsKnoten.getBoundsInParent().getHeight()));

        line.endXProperty().bind(zielKnoten.layoutXProperty().add(zielKnoten.getBoundsInParent().getWidth()));
        line.endYProperty().bind(zielKnoten.layoutYProperty().add(zielKnoten.getBoundsInParent().getHeight()));
    }

    public Line getLine() {
        return line;
    }
}