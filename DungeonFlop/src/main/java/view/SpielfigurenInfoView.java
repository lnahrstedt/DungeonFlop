package view;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.Spielfigur;
import model.Spielwelt;
import resources.interfaces.PfadSammlung;
import resources.interfaces.ZahlensammlungGUI;

public class SpielfigurenInfoView {

    private HBox hBox = null;

    public SpielfigurenInfoView() {
        this.hBox = initInfoUeberSpielerspielfigur();

    }

    /**
     * Initialisiert die Informationsansicht fuer die Spielfigur.
     *
     * @return HBox
     */
    private static HBox initInfoUeberSpielerspielfigur() {
        Spielfigur spielfigur = Spielwelt.getInstance().getProtagonist().getSpielfigur();

        ImageView spielfigurenBild = erstelleBild(String.format(PfadSammlung.BILDER_PFAD_GENERELL, spielfigur.getName()));
        ImageView schadenspunkteBild = erstelleBild(PfadSammlung.PFAD_SCHADENSPUNKTE);
        ImageView lebenspunkteBild = erstelleBild(PfadSammlung.PFAD_LEBENSPUNKTE);
        Font font = new Font(ZahlensammlungGUI.SCHRIFTGROESSE_20);

        Text name = new Text(spielfigur.getName());
        name.setFont(font);
        Text schadenspunkte = new Text(String.valueOf(spielfigur.getAngriffspunkte()));
        schadenspunkte.setFont(font);
        Text lebenspunkte = new Text(String.valueOf(spielfigur.getLebenspunkte()));
        lebenspunkte.setFont(font);

        HBox hBoxName = new HBox(spielfigurenBild, name);
        hBoxName.setSpacing(ZahlensammlungGUI.SPIELFIGURENINFOVIEW_HBOXNAME_SPACING);
        hBoxName.setAlignment(Pos.CENTER_LEFT);

        HBox hBoxSchadenspunkte = new HBox(schadenspunkteBild, schadenspunkte);
        hBoxSchadenspunkte.setSpacing(ZahlensammlungGUI.SPIELFIGURENINFOVIEW_HBOXSCHADENSPUNKTE_SPACING);
        hBoxSchadenspunkte.setAlignment(Pos.CENTER_LEFT);

        HBox hBoxLebenspunkte = new HBox(lebenspunkteBild, lebenspunkte);
        hBoxLebenspunkte.setSpacing(ZahlensammlungGUI.SPIELFIGURENINFOVIEW_HBOXLEBENSPUNKTE_SPACING);
        hBoxLebenspunkte.setAlignment(Pos.CENTER_LEFT);

        VBox vBox = new VBox(hBoxName, hBoxSchadenspunkte, hBoxLebenspunkte);
        vBox.setSpacing(ZahlensammlungGUI.SPIELFIGURENINFOVIEW_VBOX_SPACING);
        vBox.setAlignment(Pos.CENTER_LEFT);

        HBox hBox = new HBox(vBox);
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.setSpacing(ZahlensammlungGUI.SPIELFIGURENINFOVIEW_HBOX_SPACING);

        return hBox;
    }

    /**
     * Erstellt ein ImageView aus einem uebergebenen Pfad.
     *
     * @param pfad
     * @return ImageView
     */
    private static ImageView erstelleBild(String pfad) {
        Image image = new Image(pfad);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(ZahlensammlungGUI.FELDGROESSE_HOEHE * ZahlensammlungGUI.SPIELFIGURENINFO_BILDSKALIERUNG);
        imageView.setFitWidth(ZahlensammlungGUI.FELDGROESSE_HOEHE * ZahlensammlungGUI.SPIELFIGURENINFO_BILDSKALIERUNG);
        return imageView;
    }

    public HBox getInfoUeberSpielfigur() {
        return hBox;
    }
}
