package view;

import control.view.HilfeAlertFabrik;
import control.view.ItemViewController;
import control.view.SchatzraumViewController;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import model.raum.Schatzraum;
import resources.interfaces.StyleSpeicher;

/**
 * Diese Klasse enthaelt die Layout-Daten fuer den Schatzraum-Bildschirm und erstellt daraus eine Scene.
 */
public class SchatzraumView implements Viewable {
    private Schatzraum schatzraum;
    private Pane root;


    /**
     * Erzeugt eine Szene anhand des uebergebenen Schatzraum.
     *
     * @param schatzraum
     */
    public SchatzraumView(Schatzraum schatzraum) {
        this.schatzraum = schatzraum;
        this.root = initRoot(schatzraum);
    }

    /**
     * Initialisiert die Szene anhand des Schatzraumes.
     *
     * @param schatzraum
     * @return
     */
    private Pane initRoot(Schatzraum schatzraum) {
        BorderPane root = new BorderPane();
        root.setTop(ErstelleMenueView.generiereMenu());

        //Enthaelt alle Knoepfe, die die jeweilige Schatzkarte darstellen.
        HBox schatzKnopfSammlung = new HBox();
        for (int i = 0; i < schatzraum.getSchatz().size(); i++) {
            //Schatz wurde nicht bereits genommen
            if (schatzraum.getSchatz().get(i) != null) {
                Button schatzKnopf = new Button();
                schatzKnopf.setOnAction(SchatzraumViewController.generiereSchatzAktion(schatzraum, schatzKnopf, i));
                BorderPane schatzAnsichtGross = ItemView.erstelleItemGross(schatzraum.getSchatz().get(i));
                schatzKnopf.setOnMouseEntered(ItemViewController.itemHoverEnterRight(schatzAnsichtGross, root));
                schatzKnopf.setOnMouseExited(ItemViewController.itemHoverExitRight(root));
                schatzKnopf.setGraphic(ItemView.erstelleItemKlein(schatzraum.getSchatz().get(i)));
                schatzKnopf.setStyle(StyleSpeicher.FX_BORDER_COLOR_ORANGE + StyleSpeicher.FX_BORDER_RADIUS_15 + StyleSpeicher.FX_BORDER_WIDTH_2 + StyleSpeicher.FX_BACKGROUND_COLOR_WHITE);
                schatzKnopfSammlung.getChildren().add(schatzKnopf);
            }
        }
        root.setCenter(schatzKnopfSammlung);

        HBox knopfSammlung = new HBox(DeckView.erstelleDeckKnopf(), InventarView.erstelleInventarButton(), Zurueckknopf.erstelleZurueckKnopf());
        root.setBottom(knopfSammlung);
        root.setBackground(Background.fill(Color.WHITE));
        return root;
    }

    @Override
    public Pane getRoot() {
        return root;
    }

    @Override
    public Viewable aktualisiere() {
        return new SchatzraumView(this.schatzraum);
    }

    @Override
    public void gibHilfeAus() {
        HilfeAlertFabrik.zeigeHilfeAlertSchatzraumView();
    }
}
