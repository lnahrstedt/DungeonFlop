package view;

import control.view.ChatViewController;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.netzwerk.Chat;
import model.netzwerk.NetzwerkFlagContainer;
import resources.interfaces.*;

/**
 * Diese Klasse enthaelt die Layout-Daten fuer den Chat-Bildschirm und erstellt daraus eine Scene.
 */
public class ChatView implements Viewable {

    private Pane root;
    private static double startpositionX;
    private static double startpositionY;
    private static double endpositionX;
    private static double endpositionY;

    public ChatView() {
        NetzwerkFlagContainer.setIstChatGeoeffnet(true);
        this.root = initRoot();
    }

    /**
     * Initialisiert und gibt die Szene des Chat-Bildschirms zurueck.
     *
     * @return Scene der CreditsView
     */
    private Pane initRoot() {

        StackPane root = new StackPane();
        root.getChildren().addAll(Buehne.getInstance().getViewable().getRoot(), initBorderPane());
        return root;
    }

    /**
     * Diese Methode initialisiert die fuer den ChatView benoetigte BorderPane.
     *
     * @return borderPane
     */
    private static BorderPane initBorderPane() {
        BorderPane borderPane = new BorderPane();
        borderPane.setTranslateX(endpositionX);
        borderPane.setTranslateY(endpositionY);
        borderPane.setBackground(Background.fill(Color.WHITE));
        borderPane.setStyle(StyleSpeicher.FX_BORDER_WIDTH_2 + StyleSpeicher.FX_BORDER_COLOR_ORANGE);
        borderPane.setMaxWidth(ZahlensammlungChat.CHAT_BREITE);
        borderPane.setMaxHeight(ZahlensammlungChat.CHAT_HOEHE);

        ImageView imageView = initImageView();
        Label label = initLabel(imageView.getFitWidth());
        BorderPane topBorderPane = initTopBorderPane(label, imageView);

        borderPane.setTop(topBorderPane);
        borderPane.setCenter(initScrollPane());
        borderPane.setBottom(initTextArea());
        ChatViewController.machChatZiehbar(borderPane);

        return borderPane;
    }

    /**
     * Diese Methode initialisiert den "Kopf" des ChatViews. Er enthaelt ein Label mit der Aufschrift "Chat" und eine Grafik, die die Moeglichkeit des Schliesses des Chats einraeumt.
     *
     * @param label
     * @param imageView
     * @return topBorderPane
     */
    private static BorderPane initTopBorderPane(Label label, ImageView imageView) {
        BorderPane topBorderPane = new BorderPane();
        topBorderPane.setCenter(label);
        topBorderPane.setRight(imageView);
        topBorderPane.setCursor(Cursor.MOVE);
        return topBorderPane;
    }

    /**
     * Diese Methode initialisiert ein ImageView, welcher fuer den Kopf des ChatViews benoetigt wird.
     *
     * @return imageView
     */
    private static ImageView initImageView() {
        ImageView imageView = new ImageView(new Image(PfadSammlung.KREUZ_ICON));
        imageView.setFitWidth(ZahlensammlungChat.IMAGE_VIEW_BREITE);
        imageView.setFitHeight(ZahlensammlungChat.IMAGE_VIEW_HOEHE);
        imageView.setCursor(Cursor.HAND);
        imageView.setOnMouseClicked(mouseEvent -> ChatViewController.schliesseChat());
        return imageView;
    }

    /**
     * Diese Methode initialisiert ein Label mit der Aufschrift "Chat" fuer den Kopf des ChatViews.
     *
     * @param korrektur
     * @return label
     */
    private static Label initLabel(double korrektur) {
        Label label = new Label(StringsammlungChat.LABEL_TEXT);
        label.setFont(Font.font(null, FontWeight.BOLD, null, ZahlensammlungGUI.SCHRIFTGROESSE_35));
        label.setPadding(new Insets(0, 0, 0, korrektur));
        return label;
    }

    /**
     * Diese Methode initialisiert die TextArea, die das Eingeben von Nachrichten ermoeglicht.
     *
     * @return textArea
     */
    private static TextArea initTextArea() {
        TextArea textArea = new TextArea();
        textArea.setPromptText(StringsammlungChat.PLATZHALTER_TEXT_AREA);
        textArea.setFocusTraversable(true);
        textArea.setPrefHeight(ZahlensammlungChat.HOEHE_TEXT_AREA);
        textArea.setFont(Font.font(null, FontWeight.NORMAL, ZahlensammlungGUI.SCHRIFTGROESSE_15));
        textArea.setOnKeyReleased(ChatViewController.handleTastertureingaben(textArea));
        textArea.setText(Chat.getInstance().getZwischenspeicher());
        Platform.runLater(() -> {
            textArea.requestFocus();
            textArea.end();
        });
        return textArea;
    }

    /**
     * Diese Methode initialisiert die ScrollPane zum Anzeigen der versendeten Nachrichten.
     *
     * @return scrollPane
     */
    private static ScrollPane initScrollPane() {
        ScrollPane scrollPane = new ScrollPane(initVBox());
        scrollPane.setFitToWidth(true);
        scrollPane.setVvalue(ZahlensammlungChat.V_VALUE_SCROLLPANE);
        scrollPane.setStyle(StyleSpeicher.SCROLL_PANE_BACKGROUND_TRANSPARENT);
        return scrollPane;
    }

    /**
     * Diese Methode initialisiert die VBoxen, die zum Anzeigen der Nachrichten benoetigt werden.
     *
     * @return vBox
     */
    private static VBox initVBox() {
        VBox vBox = new VBox();
        vBox.setSpacing(ZahlensammlungChat.ABSTAND_VBOX);
        vBox.setAlignment(Pos.BOTTOM_CENTER);
        vBox.setPadding(new Insets(ZahlensammlungChat.VBOX_INSETS_V, ZahlensammlungChat.VBOX_INSETS_V1, ZahlensammlungChat.VBOX_INSETS_V2, ZahlensammlungChat.VBOX_INSETS_V3));

        vBox.getChildren().setAll(ChatViewController.leseNachrichtenEinUndStyleSie());
        return vBox;
    }

    public static double getStartpositionX() {
        return startpositionX;
    }

    public static void setStartpositionX(double startpositionX) {
        ChatView.startpositionX = startpositionX;
    }

    public static double getStartpositionY() {
        return startpositionY;
    }

    public static void setStartpositionY(double startpositionY) {
        ChatView.startpositionY = startpositionY;
    }

    public static void setEndpositionX(double endpositionX) {
        ChatView.endpositionX = endpositionX;
    }

    public static void setEndpositionY(double endpositionY) {
        ChatView.endpositionY = endpositionY;
    }

    @Override
    public Pane getRoot() {
        return root;
    }

    @Override
    public Viewable aktualisiere() {
        return new ChatView();
    }

    @Override
    public void gibHilfeAus() {
        Buehne.getInstance().getViewable().gibHilfeAus();
    }
}
