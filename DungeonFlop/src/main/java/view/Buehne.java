package view;

import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Diese Klasse dient der Speicherung der vom Laufzeitsystem uebergebenen primaryStage.
 * Sie stellt sicher, dass alle Klassen auch ohne die Uebergabe der primaryStage als Parameter
 * z. B. Aenderungen an der Scene vornehmen koennen.
 * <p>
 * Da von dieser Klasse nur eine Instanz existieren soll, wird auf das Singleton-Pattern zurueckgegriffen.
 */
public class Buehne {

    private static final Buehne buehne = new Buehne();
    private Stage stage = null;
    private Viewable viewable = null;

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setScene(Scene scene) {
        this.stage.setScene(scene);
    }

    public void setViewable(Viewable viewable) {
        this.viewable = viewable;
    }

    public Viewable getViewable() {
        return this.viewable;
    }

    public void setRootOfScene(Viewable viewable) {
        if (!(viewable instanceof ChatView)) {
            this.viewable = viewable;
        }
        if (viewable != null) {
            getStage().getScene().setRoot(viewable.getRoot());
        }
    }

    /**
     * Gibt die Instanz dieser Klasse zurueck.
     *
     * @return Instanz von Buehne
     */
    public static Buehne getInstance() {
        return buehne;
    }

    public void setRootMitZurueckStack(Viewable viewable) {
        Zurueckknopf.getInstance().addElementZurueckknopf(this.viewable);
        setRootOfScene(viewable);
    }

    public void aktualisiereAktuelleView() {
        setRootOfScene(viewable.aktualisiere());
    }
}