package graph.layout;

import control.view.EbenenLayoutViewController;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.Spielwelt;
import model.raum.Raum;
import resources.interfaces.StyleSpeicher;
import resources.interfaces.ZahlensammlungGUI;

/**
 * Diese Klasse erweitert die Klasse Knoten.
 * Sie ermoeglicht das Erstellen von Knoten, die von einem Button repraesentiert werden.
 */
public class ButtonKnoten extends Knoten {

    public ButtonKnoten(String text, int id, Raum raum) {
        super(id);

        Button button = new Button(text);
        button.setFont(Font.font(null, FontWeight.BOLD, ZahlensammlungGUI.SCHRIFTGROESSE_15));
        button.setStyle(StyleSpeicher.FX_TEXT_ALIGNMENT_CENTER +
                StyleSpeicher.FX_BORDER_COLOR_ORANGE +
                StyleSpeicher.FX_BORDER_WIDTH_2);

        button.setCursor(Cursor.HAND);
        button.setBackground(Background.fill(Color.WHITE));
        fuegeActionHandlerZuButton(button, raum);
        checkPosition(button, id);

        setView(button);

    }

    /**
     * Diese Methode bestueckt den ButtonKnoten mit einem ActionHandler.
     *
     * @param button
     * @param raum
     */
    private static void fuegeActionHandlerZuButton(Button button, Raum raum) {
        button.setOnAction(EbenenLayoutViewController.wechselZuRaum(raum));
    }

    /**
     * Diese Methode ueberprueft, wo sich der Spieler aktuell befindet.
     * Auf Basis dieser Daten wird der entsprechende Button mit einer Farbe versehen.
     *
     * @param button
     * @param id
     */
    private static void checkPosition(Button button, int id) {
        if (id == Spielwelt.getInstance().getSpielerEbenePosition().getSpielerRaumPosition().getRaumnummer()) {
            button.setDefaultButton(true);
            button.setBackground(Background.fill(Color.ORANGE));
        }
    }
}
