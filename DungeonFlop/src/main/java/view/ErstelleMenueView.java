package view;

import control.view.ErstelleMenueViewController;
import control.view.ZurueckknopfController;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import model.netzwerk.NetzwerkFlagContainer;
import resources.interfaces.PfadSammlung;
import resources.interfaces.StringsammlungGUI;
import resources.interfaces.ZahlensammlungGUI;

/**
 * Diese Klasse erstellt auf Basis seiner Methoden das Menue.
 */
public class ErstelleMenueView {

    /**
     * Diese Methode erstellt und gibt die gesamte Menueleiste mit dem Startmenue, dem Optionmenue und dem Infomenue am oberen Fensterrand zurueck.
     *
     * @return HBox der Menueleiste
     */
    public static HBox generiereMenu() {

        MenuBar links = new MenuBar();
        Menu startMenue = getStartMenu();
        Menu optionMenue = getOptionMenu();
        Menu chatMenue = getChatMenu();
        MenuBar rechts = new MenuBar();
        Menu infoMenue = getInfoMenue();
        Region luecke = new Region();
        luecke.getStyleClass().add(StringsammlungGUI.MENU_BAR);
        HBox.setHgrow(luecke, Priority.SOMETIMES);
        HBox menuBars = new HBox(links, luecke, rechts);

        links.getMenus().addAll(startMenue, optionMenue, chatMenue);
        rechts.getMenus().addAll(infoMenue);

        return menuBars;
    }

    /**
     * Diese Methode erstellt das Menue "Start" samt seiner Untermenues "Neues Spiel" und "Spiel laden".
     * Wird eines der Untermenues per Klick ausgewaehlt, leitet diese Methode die Anfrage an den Controller weiter.
     *
     * @return startMenue
     */
    private static Menu getStartMenu() {
        Menu startMenue = new Menu();
        startMenue.setGraphic(imageViewHelper(PfadSammlung.START_MENUE_ICON));
        MenuItem hauptmenue = new MenuItem(StringsammlungGUI.HAUPTMENUE_TEXT, imageViewHelper(PfadSammlung.START_ICON));
        hauptmenue.setOnAction(ErstelleMenueViewController.wechsleZumHauptmenue());
        MenuItem spielLaden = new MenuItem(StringsammlungGUI.SPIEL_LADEN, imageViewHelper(PfadSammlung.SPIELLADEN_ICON));
        spielLaden.setOnAction(ErstelleMenueViewController.wechselZuSpielLadenScene());
        MenuItem spielSpeichern = new MenuItem(StringsammlungGUI.SPIEL_SPEICHERN, imageViewHelper(PfadSammlung.SPIELSPEICHERN_ICON));
        spielSpeichern.setOnAction(ErstelleMenueViewController.speichereSpiel());
        startMenue.getItems().addAll(spielLaden, spielSpeichern, hauptmenue);

        return startMenue;
    }

    /**
     * Diese Methode erstellt das Menue "Option" samt seiner Untermenues "Spieleinstellungen" und "Spiel verlassen".
     * Wird eines der Untermenues per Klick ausgewaehlt, leitet diese Methode die Anfrage an den Controller weiter.
     *
     * @return optionMenue
     */
    private static Menu getOptionMenu() {
        Menu optionMenue = new Menu();
        optionMenue.setGraphic(imageViewHelper(PfadSammlung.OPTIONEN_ICON));
        MenuItem spieleinstellungen = new MenuItem(StringsammlungGUI.SPIELEINSTELLUNGEN, imageViewHelper(PfadSammlung.OPTIONEN_ICON));
        spieleinstellungen.setOnAction(ErstelleMenueViewController.wechselZuOptionScene());
        MenuItem spielVerlassen = new MenuItem(StringsammlungGUI.SPIEL_VERLASSEN, imageViewHelper(PfadSammlung.SPIEL_VERLASSEN_ICON));
        spielVerlassen.setOnAction(ErstelleMenueViewController.oeffneBestaetigungsDialog());
        optionMenue.getItems().addAll(spieleinstellungen, spielVerlassen);
        return optionMenue;
    }

    private static Menu getChatMenu() {
        Menu chatMenue = new Menu();
        chatMenue.setGraphic(NetzwerkFlagContainer.gibtEsEineUngeleseneNachricht() && !NetzwerkFlagContainer.istChatGeoeffnet() ? imageViewHelper(PfadSammlung.CHAT_ICON_GIF) : imageViewHelper(PfadSammlung.CHAT_ICON_PNG));
        chatMenue.setVisible(NetzwerkFlagContainer.wirdOnlineGespielt());
        MenuItem chatOeffnen = new MenuItem(StringsammlungGUI.CHAT_OEFFNEN);
        chatOeffnen.setOnAction(ErstelleMenueViewController.oeffneChat());
        MenuItem chatSchliessen = new MenuItem(StringsammlungGUI.CHAT_SCHLIESSEN);
        chatSchliessen.setOnAction(ErstelleMenueViewController.schliesseChat());
        chatMenue.getItems().add(NetzwerkFlagContainer.istChatGeoeffnet() ? chatSchliessen : chatOeffnen);
        return chatMenue;
    }

    /**
     * Diese Methode erstellt und gibt ein optionales MenuItem des Zurueckknopfs zurueck.
     *
     * @return MenuItem zurueckKnopf
     */
    private static MenuItem getZurueckMenuItem() {
        MenuItem zurueck = new MenuItem(StringsammlungGUI.ZURUECK_TEXT, imageViewHelper(PfadSammlung.ZURUECK_ICON));
        zurueck.setOnAction(ZurueckknopfController.geheZurueck());
        return zurueck;
    }

    /**
     * Diese Methode erstellt und gibt das InfoMenue mit dem Hilfeknopf zurueck.
     *
     * @return
     */
    public static Menu getInfoMenue() {
        Menu infoMenue = new Menu();
        infoMenue.setGraphic(imageViewHelper(PfadSammlung.INFO_ICON));
        MenuItem hilfe = new MenuItem(StringsammlungGUI.HILFE_TEXT);
        hilfe.setOnAction(ErstelleMenueViewController.zeigeHilfeAn());
        infoMenue.getItems().addAll(hilfe);

        return infoMenue;
    }

    /**
     * Dies ist eine Hilfsmethode, um die Icons im Menue einfacher einzubinden.
     *
     * @param path
     * @return Image des uebergebenen Pfads
     */
    private static ImageView imageViewHelper(String path) {
        return new ImageView(new Image(path, ZahlensammlungGUI.IMAGEVIEW_HELPER_HOEHE_BREITE, ZahlensammlungGUI.IMAGEVIEW_HELPER_HOEHE_BREITE, true, true));
    }

}
