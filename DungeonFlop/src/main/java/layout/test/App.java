package layout.test;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {

        VBox pane = new VBox();

        pane.setPrefWidth(300);
        pane.setSpacing(10);
        pane.setAlignment(Pos.BOTTOM_CENTER);
        pane.setPadding(new Insets(20, 10, 20, 10));

        HBox text1 = createText("Lorem ipsum dolor sit amet, consectetur adipiscing elit.", true);
        HBox text2 = createText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam vel quam erat.", false);

        HBox text3 = createText("Lorem ipsum dolor sit amet, consectetur adipiscing elit.", true);
        HBox text4 = createText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam vel quam erat.", false);

        pane.getChildren().setAll(text1, text2, text3, text4);

        Scene scene = new Scene(new StackPane(pane), 500, 600);

        stage.setScene(scene);
        stage.show();
    }

    private static HBox createText(String text, boolean right) {
        Label label = new Label(text);
        label.setWrapText(true);
        label.setMinWidth(200);
        label.setPrefWidth(200);
        label.setMaxWidth(200);
        label.setPadding(new Insets(5));
        label.setStyle("-fx-background-color: " + (right ? "#DCF8C6;" : "white;") + "-fx-background-radius: 10;");
        HBox pane = new HBox(label);
        pane.setAlignment(right ? Pos.CENTER_RIGHT : Pos.CENTER_LEFT);
        return pane;
    }

    public static void main(String[] args) {
        launch();
    }

}