package resources.interfaces;

import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

/**
 * In diesem Interface werden die in der GUI benoetigten Style-Elemente abgelegt.
 */
public interface StyleSpeicher {


    String CSS_FILE = "style.css";
    String FX_BORDER_COLOR_ORANGE = "-fx-border-color: orange;";
    String FX_BORDER_COLOR_BLACK = "-fx-border-color: black;";
    String FX_BORDER_COLOR_GRAY = "-fx-border-color: gray;";
    String FX_BORDER_COLOR_WHITE = "-fx-border-color: white";

    String FX_BORDER_COLOR_TRANSPARENT = "-fx-border-color: transparent";
    String FX_BACKGROUND_COLOR_WHITE = "-fx-background-color: white;";
    String FX_BACKGROUND_COLOR_AZURE = "-fx-background-color: azure;";
    String FX_BACKGROUND_COLOR_TRANSPARENT = "-fx-background-color: transparent;";
    String FX_BACKGROUND_COLOR_BEIGE = "-fx-background-color: beige;";
    String FX_BACKGROUND_COLOR_ORANGE = "-fx-background-color: orange;";
    String FX_BACKGROUND_COLOR_DARK_TRANSPARENT = "-fx-background-color: rgba(0, 0, 0, 0.5);";
    String FX_TEXT_ALIGNMENT_CENTER = "-fx-text-alignment: center;";
    String FX_FILL_LIGHTGRAY = "-fx-fill: lightgray;";
    String FX_BACKGROUND_RADIUS_15 = "-fx-background-radius: 15;";
    String FX_BORDER_WIDTH_2 = "-fx-border-width: 2;";

    String FX_BORDER_WIDTH_3 = "-fx-border-width: 3;";
    String FX_BORDER_RADIUS_10 = "-fx-border-radius: 10;";
    String FX_BORDER_RADIUS_15 = "-fx-border-radius: 15;";
    String FX_BORDER_RADIUS_25 = "-fx-border-radius: 25;";
    String FX_BACKGROUND_COLOR_CONTROL_INNER_BACKGROUND = "-fx-background-color: -fx-control-inner-background;";
    String FX_ACCENT_ORANGE = "-fx-accent: orange;";

    String SCROLL_PANE_BACKGROUND_WHITE = "-fx-background:white;-fx-border-color:white;-fx-background-color:white;";
    String SCROLL_PANE_BACKGROUND_TRANSPARENT = "-fx-background:white;-fx-border-color:white;-fx-background-color:transparent;";
    String KARTENVIEW_SPIELFIGUR_BORDERPANE = FX_BORDER_RADIUS_15 +
            FX_BORDER_COLOR_BLACK;
    String KARTENVIEW_SPIELFIGURGROSS_BORDERPANE = FX_BORDER_RADIUS_10 +
            FX_BORDER_COLOR_BLACK +
            FX_BORDER_WIDTH_2 +
            FX_BACKGROUND_COLOR_BEIGE;

    String BUTTON = FX_TEXT_ALIGNMENT_CENTER +
            FX_BORDER_COLOR_ORANGE +
            FX_BORDER_WIDTH_2 +
            FX_BORDER_RADIUS_25;

    String BUTTON_SCHWARZER_RAND = FX_TEXT_ALIGNMENT_CENTER +
            FX_BORDER_COLOR_BLACK +
            FX_BORDER_WIDTH_2 +
            FX_BORDER_RADIUS_25;

    String KARTE = FX_TEXT_ALIGNMENT_CENTER +
            FX_BORDER_COLOR_ORANGE +
            FX_BORDER_WIDTH_2 +
            FX_BORDER_RADIUS_25;
    Border BORDER = new Border(new BorderStroke(Color.ORANGE, BorderStrokeStyle.SOLID, new CornerRadii(ZahlensammlungGUI.BORDER_RADII * ZahlensammlungGUI.SKALIERUNGSFAKTOR), BorderStroke.MEDIUM));
}
