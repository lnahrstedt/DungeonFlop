package control.view;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import model.karte.Karte;
import view.ItemView;

/**
 * Diese Klasse stellt der ItemView Methoden zur Verfuegung.
 * Zum Beispiel verarbeitet sie die Eingaben des Spielers.
 */
public class ItemViewController {

    /**
     * Diese Methode verarbeitet das Ziehen einer Karte per Maus durch den Spieler.
     *
     * @param karte
     * @param inventar
     * @param root
     * @return EventHandler
     */
    public static EventHandler<MouseEvent> itemDrag(BorderPane karte, TilePane inventar, BorderPane root) {
        return mouseEvent -> {
            inventar.getChildren().remove(karte);
            root.getChildren().add(karte);
            karte.relocate(mouseEvent.getSceneX() - karte.getWidth() / 2, mouseEvent.getSceneY() - karte.getHeight() / 2);
        };
    }

    /**
     * Diese Methode verarbeitet das Maushovern ueber einem Item in der Center Area der BorderPane durch den Spieler.
     *
     * @param item
     * @param root
     * @return
     */
    public static EventHandler<MouseEvent> itemHoverEnterCenter(BorderPane item, BorderPane root) {
        return mouseEvent -> {
            root.setCenter(item);
            root.getCenter().setVisible(true);
        };
    }

    /**
     * Diese Methode verarbeitet das Maushovern ueber einem Item in der Right Area der BorderPane durch den Spieler.
     *
     * @param item
     * @param root
     * @return EventHandler
     */
    public static EventHandler<MouseEvent> itemHoverEnterRight(BorderPane item, BorderPane root) {
        return mouseEvent -> {
            root.setRight(item);
            root.getRight().setVisible(true);
        };
    }

    /**
     * Diese Methode verarbeitet das Maushovern ueber einer Karte, die im Schmiedraum aufgewertet werden soll, durch den Spieler.
     *
     * @param karte
     * @param root
     * @return EventHandler
     */
    public static EventHandler<MouseEvent> schmiedHoverEnter(Karte karte, BorderPane root) {
        return mouseEvent -> {
            root.setRight(ItemView.erstelleSchmiedAnsicht(karte));
            root.getRight().setVisible(true);
        };
    }

    /**
     * Diese Methode verarbeitet das Entfernen der Maus von einem Item, die Center Area der BorderPane wird unsichtbar gemacht.
     *
     * @param root
     * @return EventHandler
     */
    public static EventHandler<MouseEvent> itemHoverExitCenter(BorderPane root) {
        return mouseEvent -> root.getCenter().setVisible(false);
    }

    /**
     * Diese Methode verarbeitet das Entfernen der Maus von einem Item, die Right Area der BorderPane wird unsichtbar gemacht.
     *
     * @param root
     * @return EventHandler
     */
    public static EventHandler<MouseEvent> itemHoverExitRight(BorderPane root) {
        return mouseEvent -> root.getRight().setVisible(false);
    }
}
