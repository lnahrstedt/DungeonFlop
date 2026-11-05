package control.view;

import control.netzwerk.ClientThreadController;
import control.netzwerk.ServerThreadController;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.netzwerk.Chat;
import model.netzwerk.NetzwerkFlagContainer;
import resources.interfaces.StyleSpeicher;
import resources.interfaces.ZahlensammlungChat;
import resources.interfaces.ZahlensammlungGUI;
import utilities.Security;
import view.Buehne;
import view.ChatView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Diese Klasse steuert bzw. verwaltet alle Vorgaenge im Zusammenhang des ChatViews.
 */
public class ChatViewController {

    /**
     * Diese Methode macht die Ziehbarkeit des Chats moeglich.
     *
     * @param node
     */
    public static void machChatZiehbar(Node node) {
        node.setOnMousePressed(e -> {
            ChatView.setStartpositionX(e.getSceneX() - node.getTranslateX());
            ChatView.setStartpositionY(e.getSceneY() - node.getTranslateY());
        });

        node.setOnMouseDragged(e -> {

            node.setTranslateX(e.getSceneX() - ChatView.getStartpositionX());
            node.setTranslateY(e.getSceneY() - ChatView.getStartpositionY());
        });

        node.setOnMouseReleased(e -> {
            Bounds bounds = node.getParent().getLayoutBounds();
            if ((e.getSceneX() >= bounds.getMaxX() || e.getSceneX() <= bounds.getMinX()) || (e.getSceneY() >= bounds.getMaxY() || e.getSceneY() <= bounds.getMinY())) {
                node.setTranslateX(ZahlensammlungChat.STANDART_X);
                node.setTranslateY(ZahlensammlungChat.STANDART_Y);
            }
            ChatView.setEndpositionX(e.getSceneX() - ChatView.getStartpositionX());
            ChatView.setEndpositionY(e.getSceneY() - ChatView.getStartpositionY());
        });
    }

    /**
     * Diese Methode stattet die TextArea mit einem EventHandler aus, welcher auf den Tastendruck der Taste ENTER reagiert.
     * So wird zum Beipspiel die eingegebene Nachricht an den Server bzw. Client gesendet.
     *
     * @param textArea
     * @return EventHandler<KeyEvent>
     */
    public static EventHandler<KeyEvent> handleTastertureingaben(TextArea textArea) {
        return keyEvent -> {
            KeyCombination keyCombination = new KeyCodeCombination(KeyCode.ENTER, KeyCombination.SHIFT_DOWN);
            String nachricht = textArea.getText();

            if (keyCombination.match(keyEvent)) {
                textArea.appendText("\n");
                return;
            }

            if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                if (!textArea.getText().trim().isEmpty()) {
                    Security security = new Security();
                    Chat.getInstance().fuegeNachrichtHinzu(nachricht, true);
                    Chat.getInstance().setZwischenspeicher(null);
                    textArea.clear();
                    ViewWechsler.wechselZuChatView();
                    if (NetzwerkFlagContainer.binIchClient()) {
                        ClientThreadController.getInstance().sendeDaten(security.verschluessel(nachricht));
                    } else {
                        ServerThreadController.getInstance().sendeDaten(security.verschluessel(nachricht));
                    }
                }
            } else {
                Chat.getInstance().setZwischenspeicher(nachricht);
            }
        };
    }

    /**
     * Diese Methode liest alle Nachrichten ein, die im Chatmodel gespeichert sind und uebergibt sie zum Sytelen an eine weitere Methode.
     *
     * @return listeAllerNachrichtenblaasen
     */
    public static ArrayList<HBox> leseNachrichtenEinUndStyleSie() {
        ArrayList<HBox> listeAllerNachrichtenblaasen = new ArrayList<>();
        for (Map.Entry<Long, HashMap<String, Boolean>> entry : Chat.getInstance().getNachrichten().entrySet()) {
            for (Map.Entry<String, Boolean> entry1 : entry.getValue().entrySet()) {
                listeAllerNachrichtenblaasen.add(erstelleNachrichtenblaase(entry1.getKey(), entry1.getValue()));
            }
        }
        return listeAllerNachrichtenblaasen;
    }

    /**
     * Diese Methode erstellt aus der Nachricht die Nachrichtenblaase.
     * Auf Basis eines booleans wird entschieden, ob die Nachricht links oder rechts plaziert wird und wie sie gestylt wird.
     * Versendete Nachrichten haben einen orangenen Ramen und erscheinen rechts im ChatView.
     * Empfangene Nachrichten haben einen schwarzen Ramen und erscheinen links.
     *
     * @param nachricht
     * @param rechts
     * @return hBox
     */
    public static HBox erstelleNachrichtenblaase(String nachricht, boolean rechts) {
        Label label = new Label(nachricht);
        label.setWrapText(true);
        label.setMaxWidth(ZahlensammlungChat.HBOX_BREITE);
        label.setPadding(new Insets(ZahlensammlungChat.NACHRICHT_PADDING));
        label.setStyle(rechts ? StyleSpeicher.BUTTON : StyleSpeicher.FX_TEXT_ALIGNMENT_CENTER + StyleSpeicher.FX_BORDER_COLOR_BLACK + StyleSpeicher.FX_BORDER_WIDTH_2 + StyleSpeicher.FX_BORDER_RADIUS_25);
        label.setAlignment(rechts ? Pos.CENTER_RIGHT : Pos.CENTER_LEFT);
        label.setFont(Font.font(null, FontWeight.NORMAL, ZahlensammlungGUI.SCHRIFTGROESSE_15));
        HBox hBox = new HBox(label);
        hBox.setAlignment(rechts ? Pos.CENTER_RIGHT : Pos.CENTER_LEFT);
        return hBox;
    }

    /**
     * Diese Methode setzt die Flag "ungeleseneNachricht" auf false und oeffnet im Anschluss den Chat.
     */
    public static void nachrichtAlsGelesenMarkierenUndChatOeffnen() {
        NetzwerkFlagContainer.setUngeleseneNachricht(false);
        Buehne.getInstance().aktualisiereAktuelleView();
        ViewWechsler.wechselZuChatView();
        Buehne.getInstance().aktualisiereAktuelleView();
        ViewWechsler.wechselZuChatView();
    }

    /**
     * Diese Methode Prueft, ob der Chat geoeffnet ist.
     * Ist dies Der Fall, wird die Ansicht aktualisiert und der Chat erneut geoeffnet.
     */
    public static void wennChatOffenLassOffen() {
        if (NetzwerkFlagContainer.istChatGeoeffnet()) {
            Buehne.getInstance().aktualisiereAktuelleView();
            ViewWechsler.wechselZuChatView();
        }
    }

    /**
     * Methode, die den Chat schliesst.
     */
    public static void schliesseChat() {
        NetzwerkFlagContainer.setIstChatGeoeffnet(false);
        Buehne.getInstance().aktualisiereAktuelleView();
    }

}

