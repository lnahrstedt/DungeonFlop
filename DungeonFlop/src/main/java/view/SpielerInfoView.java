package view;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.HauptSpieler;
import model.Spielwelt;
import resources.interfaces.PfadSammlung;
import resources.interfaces.StringsammlungGUI;
import resources.interfaces.Zahlensammlung;
import resources.interfaces.ZahlensammlungGUI;

public class SpielerInfoView {

    private HBox hBox = null;

    public SpielerInfoView() {
        this.hBox = initInfoUeberSpieler();

    }

    /**
     * Initialisiert die Infoansicht
     *
     * @return HBox
     */
    private static HBox initInfoUeberSpieler() {
        HauptSpieler hauptSpieler = Spielwelt.getInstance().getProtagonist();

        ImageView spielfigurenBild = erstelleBild(PfadSammlung.PFAD_SPIELER);
        ImageView goldBild = erstelleBild(PfadSammlung.PFAD_GOLD);
        ImageView epBild = erstelleBild(PfadSammlung.PFAD_ERFAHRUNGSPUNKTE);
        Font font = new Font(ZahlensammlungGUI.SCHRIFTGROESSE_20);

        Text name = new Text(hauptSpieler.getName());
        name.setFont(font);
        Text gold = new Text(String.valueOf(hauptSpieler.getGold()));
        gold.setFont(font);
        Text erfahrungspunkte = new Text(hauptSpieler.getErfahrungsPunkte() + StringsammlungGUI.ERFAHRUNG_TRENNSTRICH + hauptSpieler.getLevel() * Zahlensammlung.ERFAHRUNGSKOSTENFAKTOR);
        erfahrungspunkte.setFont(font);

        HBox hBoxName = new HBox(spielfigurenBild, name);
        hBoxName.setSpacing(ZahlensammlungGUI.SPIELERINFOVIEW_HBOXNAME_SPACING);
        hBoxName.setAlignment(Pos.CENTER_LEFT);

        HBox hBoxGold = new HBox(goldBild, gold);
        hBoxGold.setSpacing(ZahlensammlungGUI.SPIELERINFOVIEW_HBOXGOLD_SPACING);
        hBoxGold.setAlignment(Pos.CENTER_LEFT);

        HBox hBoxEp = new HBox(epBild, erfahrungspunkte);
        hBoxEp.setSpacing(ZahlensammlungGUI.SPIELERINFOVIEW_HBOXEP_SPACING);
        hBoxEp.setAlignment(Pos.CENTER_LEFT);

        VBox vBox = new VBox(hBoxName, hBoxGold, hBoxEp);
        vBox.setSpacing(ZahlensammlungGUI.SPIELERINFOVIEW_VBOX_SPACING);
        vBox.setAlignment(Pos.CENTER_LEFT);

        HBox hBox = new HBox(vBox);
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.setSpacing(ZahlensammlungGUI.SPIELERINFOVIEW_HBOX_SPACING);

        return hBox;
    }

    /**
     * Erstellt ein ImageView aus einem uebergebenen Pfad.
     *
     * @param pfad
     * @return
     */
    private static ImageView erstelleBild(String pfad) {
        Image image = new Image(pfad);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(ZahlensammlungGUI.FELDGROESSE_HOEHE * ZahlensammlungGUI.SPIELERINFOVIEW_BILDSKALIERUNG);
        imageView.setFitWidth(ZahlensammlungGUI.FELDGROESSE_HOEHE * ZahlensammlungGUI.SPIELERINFOVIEW_BILDSKALIERUNG);
        return imageView;
    }

    public HBox getInfoUeberSpieler() {
        return hBox;
    }
}
