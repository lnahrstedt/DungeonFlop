package resources.interfaces;

import javafx.scene.paint.Color;

/**
 * Dieses Interface legt die verwendeten Farben im Spiel fest.
 */
public interface FarbenSammlung {
    Color GEGNERFARBE = Color.BLACK;
    Color SPIELERFARBE = Color.ORANGE;
    Color FELDERFARBE = Color.WHITE;
    Color MARKIERTE_FELDERFARBE = Color.ORANGE;
    Color REICHWEITE_FELDFARBE = Color.RED;
    Color AUSGEWAEHLTE_KARTEFARBE = Color.ORANGE;
    Color FELDRANDFARBE = Color.BLACK;
}
