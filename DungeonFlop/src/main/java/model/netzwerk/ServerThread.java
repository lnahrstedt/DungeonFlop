package model.netzwerk;

import control.netzwerk.ServerThreadController;
import resources.interfaces.StringsammlungNetzwerk;
import utilities.CheatMode;
import utilities.MeinIO;
import view.Buehne;

import java.io.EOFException;
import java.io.IOException;
import java.io.Serializable;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.SocketException;

/**
 * Diese Klasse repraesentiert den Server, der fuer die Netzwerkfaehigkeit des Spiels benoetigt wird.
 * Sie ist eine Subklasse der Klasse NetzwerkVerbindung und muss daher ihre abstrakten Methoden ausformulieren.
 * Durch die Implementation des Interfaces Runnable ist diese Klasse ein Thread.
 */
public non-sealed class ServerThread extends NetzwerkVerbindung implements Runnable {

    private ServerSocket serverSocket = null;

    public ServerThread() {
        new Thread(this).start();
    }

    /**
     * Diese Methode legt fest, was beim Starten des ServerThreads ausgefuehrt werden soll.
     * Zunaechst wird eine Initialisierungsmethode aufgerufen.
     * Sobald ein Client verbunden ist, wird die Spielwelt des Servers an den Client uebertragen und es wird die Empfangsbereitschaft von Daten gestartet.
     * Ausserdem behandelt diese Methode die moeglicherweise auftretenden Exceptions.
     */
    @Override
    public void run() {
        MeinIO.stringAusgeben(StringsammlungNetzwerk.SERVER_RERUN);
        try {
            init();
            ServerThreadController.getInstance().uebertrageSpielweltAnClient();
            starteEmpfangsbereitschaft();
        } catch (BindException e) {
            informiereSpielerServerBereitsVerwendet();
            e.printStackTrace();
        } catch (SocketException e) {
            MeinIO.stringAusgeben(StringsammlungNetzwerk.SOCKET_EXCEPTION_TEXT + e);
            e.printStackTrace();
        } catch (EOFException e) {
            MeinIO.stringAusgeben(StringsammlungNetzwerk.EOFException_TEXT + e);
            e.printStackTrace();
        } catch (Exception e) {
            MeinIO.stringAusgeben(StringsammlungNetzwerk.FEHLER_IM_SERVER_THREAD + e);
            informiereSpielerNetzwerkfehler();
            e.printStackTrace();
        }
    }

    /**
     * Diese Methode fungiert als allgemeine Initialisierungsmethode.
     * Sie ruft weitere Methoden zur Initialisierung der benoetigten Sockets und Streams auf.
     *
     * @throws IOException
     */
    @Override
    protected void init() throws IOException {
        initSocket();
        initStreams();
        this.verbindungAktiv = true;
    }

    /**
     * Diese Methode initialisiert die benoetigten Sockets und wartet auf eine Verbindungsanfrage eines Clients.
     * Sobald ein Client mit dem Server verbunden ist, wird der Spieler ueber die erfolgreiche Verbindung informiert.
     *
     * @throws IOException
     */
    @Override
    protected void initSocket() throws IOException {
        this.serverSocket = new ServerSocket(getPort());
        MeinIO.stringAusgeben(StringsammlungNetzwerk.SERVER_WARTET_AUF_VERBINDUNG);
        this.socket = serverSocket.accept();
        MeinIO.stringAusgeben(StringsammlungNetzwerk.CLIENT_DEM_SERVER_BEIGETRETEN);
        NetzwerkFlagContainer.setWirdOnlineGespielt(true);
        if (CheatMode.getInstance().istAktiv()) {
            CheatMode.getInstance().istDeaktiviert(false);
        }
        this.serverSocket.close();
        this.socket.setTcpNoDelay(true);
        Buehne.getInstance().aktualisiereAktuelleView();
        informiereSpielerVerbindungHergestellt(StringsammlungNetzwerk.CLIENT_ZU_SERVER_BEIGETRETEN + socket.getInetAddress());
    }

    /**
     * Diese Methode uebergibt die empfangenen Daten vom Client zur Auswertung dieser an einen Controller.
     *
     * @param daten des Clients, die es auszuwerten gilt
     */
    @Override
    protected void werteDatenAus(Object daten) {
        ServerThreadController.getInstance().werteDatenAus(daten);
    }

    /**
     * Diese Methode prueft, ob der Server mit einem Client verbunden ist.
     * Es wird geprueft, ob der Socket null ist oder nicht.
     *
     * @return true, wenn der Socket nicht null ist; sonst false
     */
    @Override
    public boolean istVerbunden() {
        return this.socket != null;
    }

    /**
     * Diese Methode ermoeglicht das Senden von Daten an den Client.
     *
     * @param daten, die an den Client gesendet werden sollen.
     * @throws IOException
     */
    @Override
    public void sende(Serializable daten) throws IOException {
        this.objectOutputStream.writeObject(daten);
        this.objectOutputStream.reset();
    }


    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }
}
