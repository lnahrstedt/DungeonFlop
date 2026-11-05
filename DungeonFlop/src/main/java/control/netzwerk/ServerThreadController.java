package control.netzwerk;

import control.model.KampfController;
import model.KampfDatenPaket;
import model.Spielwelt;
import model.netzwerk.NetzwerkFlagContainer;
import model.netzwerk.NetzwerkVerbindung;
import model.netzwerk.ServerThread;
import resources.enums.ServerClientKommunikation;
import resources.interfaces.StringsammlungNetzwerk;
import utilities.MeinIO;
import view.*;

import java.io.IOException;
import java.io.Serializable;

/**
 * Diese Klasse steuert bzw. verwaltet alle Vorgaenge im Zusammenhang des ServerThreads.
 * Sie ist eine Subklasse des NetzwerkControllers und muss daher die abstrakten Methoden ausformulieren.
 * <p>
 * Da von dieser Klasse nur eine Instanz existieren soll, wird auf das Singleton-Pattern zurueckgegriffen.
 */
public non-sealed class ServerThreadController extends NetzwerkController {

    private static final ServerThreadController serverThreadController = new ServerThreadController();
    private ServerThread server;

    /**
     * Diese Methode startet den ServerThread.
     */
    public void starteServerThread() {
        server = new ServerThread();
        MeinIO.stringAusgeben(StringsammlungNetzwerk.NEUER_SERVERTHREAD);
    }

    /**
     * Diese Methode schliesst die Verbindung des ServerThreads.
     */
    @Override
    public void schliesseVerbindung() {

        try {
            server.getSocket().close();
            server.setSocket(null);
        } catch (IOException e) {
            MeinIO.stringAusgeben(StringsammlungNetzwerk.FEHLER_BEIM_SCHLIESSEN_DES_SERVERS + e);
            NetzwerkVerbindung.informiereSpielerNetzwerkfehler();
        }
        MeinIO.stringAusgeben(StringsammlungNetzwerk.CLIENT_VON_SERVER_GETRENNT);
    }

    /**
     * Diese Methode sendet Daten an den Client.
     *
     * @param daten, die an den Client gesendet werden sollen
     */
    @Override
    public void sendeDaten(Serializable daten) {
        if (server.istVerbunden()) {
            try {
                server.sende(daten);
            } catch (IOException e) {
                MeinIO.stringAusgeben(StringsammlungNetzwerk.SENDEN_DATEN_SERVER_CLIENT_ERROR);
                e.printStackTrace();
            }
        }
    }

    /**
     * Diese Methode reicht die auszuwertenden Daten an den ServerThreadDatenAuswerter weiter.
     *
     * @param daten, die ausgewertet werden sollen
     */
    @Override
    public void werteDatenAus(Object daten) {
        ServerThreadDatenAuswerter.werteDatenAus(daten);
    }

    /**
     * Diese Methode schliesst den ServerSocket.
     */
    public void schliesseServerSocket() {
        server.setVerbindungAktiv(false);
        NetzwerkFlagContainer.setWirdOnlineGespielt(false);
        if (server.getServerSocket() != null) {
            try {
                server.getServerSocket().close();
                server.setServerSocket(null);
            } catch (IOException e) {
                MeinIO.stringAusgeben(StringsammlungNetzwerk.FEHLER_BEIM_SCHLIESSEN_DES_SERVERS + e);
                NetzwerkVerbindung.informiereSpielerNetzwerkfehler();
            }

        }
        MeinIO.stringAusgeben(StringsammlungNetzwerk.SERVER_SOCKET_GESCHLOSSEN);
    }

    /**
     * Diese Methode setzt den Server zurueck und wartet anschliessend auf eine Verbindungsanfrage eines Clients.
     */
    @Override
    public void reset() {
        NetzwerkFlagContainer.setWirdOnlineGespielt(false);
        Buehne.getInstance().aktualisiereAktuelleView();
        ServerThreadController.getInstance().schliesseVerbindung();
    }

    /**
     * Diese Methode uebertraegt die aktuelle Spielwelt des Servers an den Client.
     * Ausserdem ruft sie eine Methode auf, die fuer den Fall, dass sich der Hauptspieler in einem Spezialraum befindet, den Client darueber informiert.
     *
     * @throws IOException
     */
    public void uebertrageSpielweltAnClient() throws IOException {
        server.sende(Spielwelt.getInstance());
        uebertrageAktuellenRaum();
    }

    /**
     * Diese Methode ueberprueft, ob sich der Hauptspieler in einem Spezialraum befindet.
     * Ist dies der Fall, wir eine entsprechende Information an den Client uebermittelt.
     *
     * @throws IOException
     */
    private void uebertrageAktuellenRaum() throws IOException {
        Viewable viewable = Buehne.getInstance().getViewable();

        if (viewable instanceof SchmiedraumView || viewable instanceof HaendlerraumView || viewable instanceof SchatzraumView) {
            this.server.sende(ServerClientKommunikation.SERVER_INTERAGIERT_MIT_RAUM);
            return;
        }

        if (viewable instanceof InventarView) {
            this.server.sende(ServerClientKommunikation.SERVER_INTERAGIERT_MIT_INVENTAR);
            return;
        }

        if (viewable instanceof GegnerraumView) {
            this.server.sende(new KampfDatenPaket(KampfController.getKampf(), KampfController.getAktuelleRunde(), KampfController.getGegenSpieler()));
            return;
        }

        if (viewable instanceof DeckView) {
            this.server.sende(ServerClientKommunikation.SERVER_INTERAGIERT_MIT_DECK);
        }
    }

    public static ServerThreadController getInstance() {
        return serverThreadController;
    }

    public ServerThread getServer() {
        return server;
    }
}
