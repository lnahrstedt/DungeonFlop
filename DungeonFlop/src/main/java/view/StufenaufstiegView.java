package view;

import control.view.HilfeAlertFabrik;
import control.view.StufenaufstiegViewController;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import model.Spielwelt;
import model.talent.Talent;
import resources.enums.TalentSammlung;
import resources.interfaces.*;

import java.util.ArrayList;

public class StufenaufstiegView implements Viewable {

    private Pane root;

    public StufenaufstiegView() {
        this.root = initRoot();
    }

    /**
     * initialisiert die Ansicht des Stufenaufstieges.
     *
     * @return Pane
     */
    private Pane initRoot() {
        BorderPane root = new BorderPane();
        root.setTop(ErstelleMenueView.generiereMenu());

        HBox talentKollektion = new HBox();
        ArrayList<Talent> bereitsAusgewaehlteTalente = new ArrayList<>();
        for (int i = 0; i < Zahlensammlung.ANZAHL_TALENTE; i++) {
            Talent randomTalent = TalentSammlung.getRandomTalent();
            if (bereitsAusgewaehlteTalente.contains(randomTalent)) {
                i--;
            } else {
                bereitsAusgewaehlteTalente.add(randomTalent);
                talentKollektion.getChildren().add(talentDarstellen(randomTalent));
            }
        }

        Text ueberschrift = new Text(String.format(StringsammlungGUI.AUFGELEVELT_NACHRICHT, Spielwelt.getInstance().getProtagonist().getLevel()));
        ueberschrift.setFont(Font.font(ZahlensammlungGUI.SCHRIFTGROESSE_32));
        ueberschrift.setTextAlignment(TextAlignment.CENTER);
        ueberschrift.setUnderline(true);

        talentKollektion.setAlignment(Pos.CENTER);
        talentKollektion.setSpacing(ZahlensammlungGUI.TALENT_HBOX_SPACING);

        VBox vbox = new VBox(ueberschrift, talentKollektion);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(ZahlensammlungGUI.TALENT_VBOX_SPACING);

        root.setCenter(vbox);

        HBox knopfKollektion = new HBox(DeckView.erstelleDeckKnopf(), InventarView.erstelleInventarButton());
        root.setBottom(knopfKollektion);
        root.setBackground(Background.fill(Color.WHITE));

        return root;
    }

    /**
     * Erstellt einen Knopf, welcher ein Talent repraesentiert.
     *
     * @param talent
     * @return Button
     */
    private Button talentDarstellen(Talent talent) {
        BorderPane anordnung = new BorderPane();

        Text talentName = new Text(talent.getName());
        talentName.setFont(Font.font(ZahlensammlungGUI.SCHRIFTGROESSE_24));
        anordnung.setTop(talentName);

        ImageView talentBild = new ImageView(String.format(PfadSammlung.BILDER_PFAD_GENERELL, talent.getName()));
        talentBild.setPreserveRatio(true);
        talentBild.setFitHeight(ZahlensammlungGUI.TALENT_BILD_GROESSE);
        anordnung.setCenter(talentBild);

        Text talentBeschreibung = new Text(talent.getBeschreibung());
        talentBeschreibung.setFont(Font.font(ZahlensammlungGUI.SCHRIFTGROESSE_18));
        anordnung.setBottom(talentBeschreibung);

        Button knopf = new Button();
        knopf.setGraphic(anordnung);
        knopf.setOnAction(StufenaufstiegViewController.getStufenaufstiegEventhandler(talent));
        knopf.setPrefSize(ZahlensammlungGUI.TALENT_GROESSE, ZahlensammlungGUI.TALENT_GROESSE);
        knopf.setStyle(StyleSpeicher.FX_BACKGROUND_COLOR_CONTROL_INNER_BACKGROUND);
        knopf.setBorder(StyleSpeicher.BORDER);

        return knopf;
    }

    @Override
    public Pane getRoot() {
        return root;
    }

    @Override
    public Viewable aktualisiere() {
        return new StufenaufstiegView();
    }

    @Override
    public void gibHilfeAus() {
        HilfeAlertFabrik.zeigeHilfeAlertStufenaufstiegsView();
    }
}
