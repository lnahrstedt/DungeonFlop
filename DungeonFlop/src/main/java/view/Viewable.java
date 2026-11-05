package view;

import javafx.scene.layout.Pane;

/**
 * Dieses Interface stellt sicher, dass alle Klassen, die dieses Interface implementieren, eine "getRoot"-Methode ausformulieren.
 */
public interface Viewable {
    Pane getRoot();

    Viewable aktualisiere();

    void gibHilfeAus();
}
