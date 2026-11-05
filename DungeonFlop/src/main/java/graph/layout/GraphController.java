package graph.layout;

import model.Spielwelt;
import model.raum.Raum;
import resources.interfaces.KonstantenEbenenLayoutSpeicher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Diese Klasse stellt der Klasse Graph Methoden zur Verfuegung.
 */
public class GraphController {

    /**
     * Diese Methode initialisiert den Graphen auf Basis des vordefinierten Layouts der einzelnen Ebenen.
     * Sie erstellt alle Knoten und deren Kanten.
     * Die Knoten sind dabei die einzelnen Raeume einer Ebene.
     * Sie werden durch Buttons und dem entsprechenden Namen des Raumes visualisiert.
     * Die Kanten geben dem Spieler visuell Aufschluss darueber, welche Raeume anliegend (also Nachbarn) sind.
     *
     * @param graph
     */
    public static void initGraph(Graph graph) {

        //Diese HashMap koppelt die einzelnen Raeume an einer Liste an Nachbarraeumen
        HashMap<Raum, ArrayList<Raum>> raeumeUndNachbarraeume = Spielwelt.getInstance().getSpielerEbenePosition().getLayout();

        //Diese HashMap koppelt die einzelnen Raumnummern an einer Liste an Nachbarraeumen
        HashMap<Integer, ArrayList<Raum>> raumnummerUndNachbarraeume = konvertiereRaumKeyZuRaumnummerKey(raeumeUndNachbarraeume);

        graph.beginInit();

        addKnotenZuGraph(graph, raeumeUndNachbarraeume);
        addKantenzuGraph(graph, raumnummerUndNachbarraeume);

        graph.endInit();
    }

    /**
     * Diese Methode fuegt die einzelnen Knoten dem Graphen hinzu.
     *
     * @param graph
     * @param raeumeUndNachbarraeume
     */
    private static void addKnotenZuGraph(Graph graph, HashMap<Raum, ArrayList<Raum>> raeumeUndNachbarraeume) {
        for (Map.Entry<Raum, ArrayList<Raum>> entry : raeumeUndNachbarraeume.entrySet()) {
            graph.addKnoten(entry.getKey().getName(), entry.getKey().getRaumnummer(), entry.getKey(), KnotenTyp.BUTTON);
        }
    }

    /**
     * Diese Methode fuegt die einzelnen Kanten dem Graphen hinzu.
     *
     * @param graph
     * @param raumnummerUndNachbarraeume
     */
    private static void addKantenzuGraph(Graph graph, HashMap<Integer, ArrayList<Raum>> raumnummerUndNachbarraeume) {
        for (int i = 0; i < raumnummerUndNachbarraeume.size(); i++) {
            for (int j = 0; j < raumnummerUndNachbarraeume.get(i).size(); j++) {
                graph.addKante(i, raumnummerUndNachbarraeume.get(i).get(j).getRaumnummer());
            }
        }
    }

    /**
     * Diese Methode richtet die Knoten des uebergebenen Graph aus.
     * Dies geschieht auf Basis der vordefinierten Koordinaten der einzelnen Knoten in den Ebenen.
     *
     * @param graph
     */
    public static void richteGraphAus(Graph graph) {

        if (Spielwelt.getInstance().getEbenen().get(KonstantenEbenenLayoutSpeicher.EBENE_0) == Spielwelt.getInstance().getSpielerEbenePosition()) {
            LayoutEbene0 layoutEbene0 = new LayoutEbene0(graph);
            layoutEbene0.richteGraphAus();
        }

        if (Spielwelt.getInstance().getEbenen().get(KonstantenEbenenLayoutSpeicher.EBENE_1) == Spielwelt.getInstance().getSpielerEbenePosition()) {
            LayoutEbene1 layoutEbene1 = new LayoutEbene1(graph);
            layoutEbene1.richteGraphAus();
        }

        if (Spielwelt.getInstance().getEbenen().get(KonstantenEbenenLayoutSpeicher.EBENE_2) == Spielwelt.getInstance().getSpielerEbenePosition()) {
            LayoutEbene2 layoutEbene2 = new LayoutEbene2(graph);
            layoutEbene2.richteGraphAus();
        }

        if (Spielwelt.getInstance().getEbenen().get(KonstantenEbenenLayoutSpeicher.EBENE_3) == Spielwelt.getInstance().getSpielerEbenePosition()) {
            LayoutEbene3 layoutEbene3 = new LayoutEbene3(graph);
            layoutEbene3.richteGraphAus();
        }

        if (Spielwelt.getInstance().getEbenen().get(KonstantenEbenenLayoutSpeicher.EBENE_4) == Spielwelt.getInstance().getSpielerEbenePosition()) {
            LayoutEbene4 layoutEbene4 = new LayoutEbene4(graph);
            layoutEbene4.richteGraphAus();
        }

        if (Spielwelt.getInstance().getEbenen().get(KonstantenEbenenLayoutSpeicher.EBENE_5) == Spielwelt.getInstance().getSpielerEbenePosition()) {
            LayoutEbene5 layoutEbene5 = new LayoutEbene5(graph);
            layoutEbene5.richteGraphAus();
        }

        if (Spielwelt.getInstance().getEbenen().get(KonstantenEbenenLayoutSpeicher.EBENE_6) == Spielwelt.getInstance().getSpielerEbenePosition()) {
            LayoutEbene6 layoutEbene6 = new LayoutEbene6(graph);
            layoutEbene6.richteGraphAus();
        }
    }

    /**
     * Diese Methode konvertiert eine HashMap mit einem Raumobjekt als Key zu einer HashMap mit der entsprechenden Raumnummer als Key.
     *
     * @param raeumeUndNachbarraeume
     * @return eine HashMap, die nun nicht mehr das Raumobjekt als Schluessel hat, sondern die entsprechende Raumnummer.
     */
    private static HashMap<Integer, ArrayList<Raum>> konvertiereRaumKeyZuRaumnummerKey(HashMap<Raum, ArrayList<Raum>> raeumeUndNachbarraeume) {
        HashMap<Integer, ArrayList<Raum>> raumnummerUndNachbarraeume = new HashMap<>();

        for (Map.Entry<Raum, ArrayList<Raum>> entry : raeumeUndNachbarraeume.entrySet()) {
            raumnummerUndNachbarraeume.put(entry.getKey().getRaumnummer(), entry.getValue());
        }
        return raumnummerUndNachbarraeume;
    }

}
