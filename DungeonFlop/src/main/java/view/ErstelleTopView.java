package view;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import resources.interfaces.PfadSammlung;
import resources.interfaces.ZahlensammlungGUI;

/**
 * Diese Klasse generiert auf Basis des ErstelleMenueView das Menue und liest das Logo ein.
 * Beide Komponenten werden in einer VBOX gebuendelt.
 */
public class ErstelleTopView {

    /**
     * Diese Methode erstellt und gibt eine VBox mit der Menueleiste am oberen Fensterrand zurueck.
     *
     * @return VBox der oberen Menueleiste
     */
    public static VBox generiereTopView() {
        VBox vertikaleTopBox = new VBox();
        HBox menue = ErstelleMenueView.generiereMenu();
        Image logo = new Image(PfadSammlung.PFAD_LOGO, ZahlensammlungGUI.LOGO_BREITE, ZahlensammlungGUI.DECK_KNOPF_HOEHE, true, true, true);
        ImageView imageView = new ImageView(logo);
        vertikaleTopBox.setAlignment(Pos.TOP_CENTER);
        vertikaleTopBox.getChildren().addAll(menue, imageView);
        return vertikaleTopBox;
    }
}
