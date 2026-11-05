package model.netzwerk;

import control.view.AlertFabrik;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import resources.interfaces.StringsammlungAlert;
import resources.interfaces.ZahlensammlungNetzwerk;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

/**
 * Diese abstrakte Superklasse beinhaltet alles Notwendige fuer eine Netzwerkverbindung.
 * Durch ihre abstrakten Methoden wird sichergestellt, dass ihre Subklassen (ServerThread und ClientThread) die fuer eine Netzwerkverbindung relevante Methoden ausformulieren.
 */
public abstract sealed class NetzwerkVerbindung permits ClientThread, ServerThread {

    protected Socket socket;
    protected ObjectOutputStream objectOutputStream;
    protected ObjectInputStream objectInputStream;
    protected boolean verbindungAktiv;

    /**
     * Solange die jeweilige Verbindung aktiv ist, ist diese Methode fuer den Empfang von Daten verantwortlich.
     * Bei Paketempfang reicht sie das Datenpaket zur Auswertung an eine Methode weiter.
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    protected void starteEmpfangsbereitschaft() throws IOException, ClassNotFoundException {
        while (verbindungAktiv) {
            Object daten = objectInputStream.readObject();
            werteDatenAus(daten);
        }
    }

    /**
     * Diese Methode initialisiert den Input- und Outputstream.
     *
     * @throws IOException
     */
    protected void initStreams() throws IOException {
        this.objectOutputStream = new ObjectOutputStream(this.socket.getOutputStream());
        this.objectInputStream = new ObjectInputStream(this.socket.getInputStream());

    }

    /**
     * Diese Methode generiert einen Alert, der den Spieler darueber informiert, dass eine Verbindung hergestellt wurde.
     *
     * @param nachricht, die im Alert angezeigt wird
     */
    public static void informiereSpielerVerbindungHergestellt(String nachricht) {
        Platform.runLater(() -> AlertFabrik.generiereAlert(Alert.AlertType.INFORMATION, StringsammlungAlert.VERBINDUNG_ERFOLGREICH_TITEL, nachricht).show());
    }

    /**
     * Diese Methode generiert einen Alert, der den Spieler darueber informiert, dass die Verbindung nicht hergestellt werden konnte.
     *
     * @param nachricht, die im Alert angezeigt wird
     */
    public static void informiereSpielerVerbindungFehlgeschlagen(String nachricht) {
        Platform.runLater(() -> AlertFabrik.generiereAlert(Alert.AlertType.ERROR, StringsammlungAlert.VERBINDUNG_FEHLER_TITEL, nachricht).show());
    }

    /**
     * Diese Methode erstellt einen Alert, der den Spieler uber einen unerwarteten Netzwerkfehler informiert.
     */
    public static void informiereSpielerNetzwerkfehler() {
        Platform.runLater(() -> AlertFabrik.generiereAlert(Alert.AlertType.ERROR, StringsammlungAlert.NETZWERKFEHLER_TITEL, StringsammlungAlert.NETZWERKFEHLER_NACHRICHT).show());
    }

    /**
     * Diese Methode erstellt einen Alert, der den Spieler darueber informiert, dass der Server aufgrund eines anderen Servers, der denselben Port nutzt, nicht erstellt werden konnte.
     */
    protected void informiereSpielerServerBereitsVerwendet() {
        Platform.runLater(() -> AlertFabrik.generiereAlert(Alert.AlertType.ERROR, StringsammlungAlert.ERSTELLEN_SERVER_FEHLGESCHLAGEN_TITEL, StringsammlungAlert.ERSTELLEN_SERVER_FEHLGESCHLAGEN_NACHRICHT).show());
    }

    /**
     * Eine allgemeine Initialisierungsmethode.
     *
     * @throws IOException
     */
    protected abstract void init() throws IOException;

    /**
     * Eine Initialisierungsmethode speziell fuer Sockets.
     *
     * @throws IOException
     */
    protected abstract void initSocket() throws IOException;

    /**
     * Eine Methode, die Auskunft ueber den Verbundenheitsstatus gibt.
     *
     * @return true, wenn verbunden; false, wenn nicht verbunden
     */
    public abstract boolean istVerbunden();

    /**
     * Eine Methode zum Senden von Daten.
     *
     * @param daten
     * @throws IOException
     */
    public abstract void sende(Serializable daten) throws IOException;

    /**
     * Eine Methode zum Auswerten von Daten.
     *
     * @param daten
     */
    protected abstract void werteDatenAus(Object daten);

    public int getPort() {
        return ZahlensammlungNetzwerk.PORT;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void setVerbindungAktiv(boolean verbindungAktiv) {
        this.verbindungAktiv = verbindungAktiv;
    }

    public ObjectInputStream getObjectInputStream() {
        return objectInputStream;
    }

    public ObjectOutputStream getObjectOutputStream() {
        return objectOutputStream;
    }
}
