package layout.test;

import graph.layout.Knoten;
import javafx.scene.control.Label;

public class LabelKnoten extends Knoten {
    public LabelKnoten(int id, String text) {
        super(id);

        Label view = new Label(String.valueOf(id));

        setView(view);

    }
}
