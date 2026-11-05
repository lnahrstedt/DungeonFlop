package control.netzwerk;

import control.view.ViewWechsler;
import javafx.application.Platform;
import model.netzwerk.ClientThread;
import model.netzwerk.NetzwerkFlagContainer;
import model.netzwerk.NetzwerkVerbindung;
import resources.interfaces.StringsammlungNetzwerk;
import utilities.MeinIO;

import java.io.IOException;
import java.io.Serializable;

/**
 * Diese Klasse steuert bzw. verwaltet alle Vorgaenge im Zusammenhang des ClientThreads.
 * Sie ist eine Subklasse des NetzwerkControllers und muss daher die abstrakten Methoden ausformulieren.
 * <p>
 * Da von dieser Klasse nur eine Instanz existieren soll, wird auf das Singleton-Pattern zurueckgegriffen.
 */
public non-sealed class ClientThreadController extends NetzwerkController {


    private static final ClientThreadController clientThreadController = new ClientThreadController();
    private ClientThread client = null;

    /**
     * Diese Methode startet den ClientThread.
     *
     * @param serverAdresse des zu verbindenden Servers
     */
    public void starteClientThread(String serverAdresse) {
        this.client = new ClientThread(serverAdresse);
        MeinIO.stringAusgeben(StringsammlungNetzwerk.NEUER_CLIENTTHREAD);
    }

    /**
     * Diese Methode schliesst die Verbindung des ClientThreads.
     */
    @Override
    public void schliesseVerbindung() {
        this.client.setVerbindungAktiv(false);
        try {
            this.client.getSocket().close();
            this.client.setSocket(null);
        } catch (IOException e) {
            MeinIO.stringAusgeben(StringsammlungNetzwerk.FEHLER_BEIM_SCHLIESSEN_DES_CLIENT + e);
            NetzwerkVerbindung.informiereSpielerNetzwerkfehler();
        }
        NetzwerkFlagContainer.setBinIchClient(false);
        MeinIO.stringAusgeben(StringsammlungNetzwerk.CLIENT_GESCHLOSSEN);
    }

    /**
     * Diese Methode setzt den Client zurueck.
     */
    @Override
    public void reset() {
        NetzwerkFlagContainer.setWirdOnlineGespielt(false);
        NetzwerkFlagContainer.setBinIchAnDerReihe(false);
        ClientThreadController.getInstance().schliesseVerbindung();
        Platform.runLater(ViewWechsler::wechselZuStartView);
    }

    /**
     * Diese Methode sendet Daten an den Server.
     *
     * @param daten, die an den Server gesendet werden sollen
     */
    @Override
    public void sendeDaten(Serializable daten) {
        if (this.client.istVerbunden()) {
            try {
                this.client.sende(daten);
            } catch (IOException e) {
                MeinIO.stringAusgeben(StringsammlungNetzwerk.SENDEN_DATEN_CLIENT_SERVER_ERROR);
                e.printStackTrace();
            }
        }
    }

    /**
     * Diese Methode reicht die auszuwertenden Daten an den ClientThreadDatenAuswerter weiter.
     *
     * @param daten, die ausgewertet werden sollen
     */
    @Override
    public void werteDatenAus(Object daten) {
        ClientThreadDatenAuswerter.werteDatenAus(daten);
    }

    public ClientThread getClient() {
        return client;
    }

    public static ClientThreadController getInstance() {
        return clientThreadController;
    }
}
