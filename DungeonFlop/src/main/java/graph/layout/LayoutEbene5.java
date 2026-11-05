package graph.layout;

import resources.interfaces.KonstantenEbenenLayoutSpeicher;
import resources.interfaces.KoordinatenSpeicher;

import java.util.Collections;
import java.util.List;

/**
 * Diese Klasse richtet den Graph gemaess des vordefinierten Layouts fuer Ebene 5 aus.
 */
public class LayoutEbene5 {

    private final Graph graph;

    public LayoutEbene5(Graph graph) {

        this.graph = graph;

    }

    /**
     * Richtet den Graphen mittels X- und Y-Koordinaten aus.
     */
    public void richteGraphAus() {

        List<Knoten> knoten = graph.getKnoten();

        Collections.sort(knoten);

        knoten.get(KonstantenEbenenLayoutSpeicher.RAUM_0).setLayoutX(KoordinatenSpeicher.EBENE_5_RAUM_0_X);
        knoten.get(KonstantenEbenenLayoutSpeicher.RAUM_0).setLayoutY(KoordinatenSpeicher.EBENE_5_RAUM_0_Y);

        knoten.get(KonstantenEbenenLayoutSpeicher.RAUM_1).setLayoutX(KoordinatenSpeicher.EBENE_5_RAUM_1_X);
        knoten.get(KonstantenEbenenLayoutSpeicher.RAUM_1).setLayoutY(KoordinatenSpeicher.EBENE_5_RAUM_1_Y);

        knoten.get(KonstantenEbenenLayoutSpeicher.RAUM_2).setLayoutX(KoordinatenSpeicher.EBENE_5_RAUM_2_X);
        knoten.get(KonstantenEbenenLayoutSpeicher.RAUM_2).setLayoutY(KoordinatenSpeicher.EBENE_5_RAUM_2_Y);

        knoten.get(KonstantenEbenenLayoutSpeicher.RAUM_3).setLayoutX(KoordinatenSpeicher.EBENE_5_RAUM_3_X);
        knoten.get(KonstantenEbenenLayoutSpeicher.RAUM_3).setLayoutY(KoordinatenSpeicher.EBENE_5_RAUM_3_Y);

        knoten.get(KonstantenEbenenLayoutSpeicher.RAUM_4).setLayoutX(KoordinatenSpeicher.EBENE_5_RAUM_4_X);
        knoten.get(KonstantenEbenenLayoutSpeicher.RAUM_4).setLayoutY(KoordinatenSpeicher.EBENE_5_RAUM_4_Y);

        knoten.get(KonstantenEbenenLayoutSpeicher.RAUM_5).setLayoutX(KoordinatenSpeicher.EBENE_5_RAUM_5_X);
        knoten.get(KonstantenEbenenLayoutSpeicher.RAUM_5).setLayoutY(KoordinatenSpeicher.EBENE_5_RAUM_5_Y);

        knoten.get(KonstantenEbenenLayoutSpeicher.RAUM_6).setLayoutX(KoordinatenSpeicher.EBENE_5_RAUM_6_X);
        knoten.get(KonstantenEbenenLayoutSpeicher.RAUM_6).setLayoutY(KoordinatenSpeicher.EBENE_5_RAUM_6_Y);

        knoten.get(KonstantenEbenenLayoutSpeicher.RAUM_7).setLayoutX(KoordinatenSpeicher.EBENE_5_RAUM_7_X);
        knoten.get(KonstantenEbenenLayoutSpeicher.RAUM_7).setLayoutY(KoordinatenSpeicher.EBENE_5_RAUM_7_Y);

        knoten.get(KonstantenEbenenLayoutSpeicher.RAUM_8).setLayoutX(KoordinatenSpeicher.EBENE_5_RAUM_8_X);
        knoten.get(KonstantenEbenenLayoutSpeicher.RAUM_8).setLayoutY(KoordinatenSpeicher.EBENE_5_RAUM_8_Y);

        knoten.get(KonstantenEbenenLayoutSpeicher.RAUM_9).setLayoutX(KoordinatenSpeicher.EBENE_5_RAUM_9_X);
        knoten.get(KonstantenEbenenLayoutSpeicher.RAUM_9).setLayoutY(KoordinatenSpeicher.EBENE_5_RAUM_9_Y);

    }

}
