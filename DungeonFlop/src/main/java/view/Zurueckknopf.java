package view;

import control.view.AlertFabrik;
import control.view.ZurueckknopfController;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import resources.interfaces.PfadSammlung;
import resources.interfaces.StringsammlungGUI;
import resources.interfaces.StyleSpeicher;
import resources.interfaces.ZahlensammlungGUI;

import java.util.Stack;

/**
 * Diese Klasse erzeugt einen Zurueckknopf, der Ebenen auf einem Stack und seine Layout-Daten spiechert.
 */
public class Zurueckknopf {
    private static final Zurueckknopf zurueckknopf = new Zurueckknopf();
    private final Stack<Viewable> viewableStack = new Stack<>();

    private Zurueckknopf() {

    }

    /**
     * Gibt die Instanz des Zurueckknopfs zurueck.
     *
     * @return Zurueckknopf-Instanz
     */
    public static Zurueckknopf getInstance() {
        return zurueckknopf;
    }

    /**
     * Erstellt und gibt den Zurueckknopf als GUI-Element zurueck.
     *
     * @return Button zurueckKnopf
     */
    public static Button erstelleZurueckKnopf() {
        Button b = new Button();
        b.setGraphic(new ImageView(new Image(PfadSammlung.ZURUECK_ICON, ZahlensammlungGUI.ZURUECKKNOPF_BREITE, ZahlensammlungGUI.ZURUECKKNOPF_HOEHE, true, true)));
        b.setPrefSize(ZahlensammlungGUI.ZURUECKKNOPF_BREITE, ZahlensammlungGUI.ZURUECKKNOPF_HOEHE);
        b.setStyle(StyleSpeicher.BUTTON + StyleSpeicher.FX_BACKGROUND_COLOR_TRANSPARENT);
        b.setCursor(Cursor.HAND);
        b.setOnAction(ZurueckknopfController.geheZurueck());
        return b;
    }

    /**
     * Fueht die uebergebene Scene dem Zurueckstack hinzu
     *
     * @param viewable - Die Pane, die dem Stack hinzugefuegt wird
     */
    public void addElementZurueckknopf(Viewable viewable) {
        getZurueckScenen().push(viewable);
    }

    /**
     * Leert den ZurueckStack.
     */
    public void zurueckStackLeeren() {
        viewableStack.clear();
    }

    /**
     * Gibt die oberste Scene auf dem Zurueckstack zurueck.
     *
     * @return scene - Die oberste Scene auf dem Stack
     */
    public Viewable getElementZurueckknopf() {
        if (getZurueckScenen().isEmpty()) {
            AlertFabrik.generiereAlert(Alert.AlertType.WARNING, StringsammlungGUI.ZURUECK_MELDUNG_TITEL, StringsammlungGUI.ZURUECK_MELDUNG).show();
            return Buehne.getInstance().getViewable();
        } else {
            return getZurueckScenen().pop();
        }
    }

    public Stack<Viewable> getZurueckScenen() {
        return viewableStack;
    }
}
