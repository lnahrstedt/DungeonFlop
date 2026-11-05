package model.netzwerk;

import control.netzwerk.ClientThreadController;
import resources.interfaces.StringsammlungNetzwerk;
import utilities.MeinIO;

import java.io.EOFException;
import java.io.IOException;
import java.io.Serializable;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * Diese Klasse repraesentiert den Client, der fuer die Netzwerkfaehigkeit des Spiels benoetigt wird.
 * Sie ist eine Subklasse der Klasse NetzwerkVerbindung und muss daher ihre abstrakten Methoden ausformulieren.
 * Durch die Implementation des Interfaces Runnable ist diese Klasse ein Thread.
 */
public non-sealed class ClientThread extends NetzwerkVerbindung implements Runnable {

    private final String serverAdresse;

    public ClientThread(String serverAdresse) {
        this.serverAdresse = serverAdresse;
        new Thread(this).start();
    }

    /**
     * Diese Methode legt fest, was beim Starten des ClientThreads ausgefuehrt werden soll.
     * Zunaechst wird eine Initialisierungsmethode aufgerufen.
     * Sobald eine Verbindung zum Server aufgebaut wurde, wird die Empfangsbereitschaft von Daten gestartet.
     * Ausserdem behandelt diese Methode die moeglicherweise auftretenden Exceptions.
     */
    @Override
    public void run() {
        MeinIO.stringAusgeben(StringsammlungNetzwerk.CLIENT_RERUN);
        try {
            init();
            starteEmpfangsbereitschaft();
        } catch (ConnectException | UnknownHostException e) {
            informiereSpielerVerbindungFehlgeschlagen(StringsammlungNetzwerk.CLIENT_ZU_SERVER_VERBINDUNG_NICHT_HERGESTELLT);
            e.printStackTrace();
        } catch (SocketException e) {
            MeinIO.stringAusgeben(StringsammlungNetzwerk.SOCKET_EXCEPTION_TEXT + e);
            e.printStackTrace();
        } catch (EOFException e) {
            MeinIO.stringAusgeben(StringsammlungNetzwerk.EOFException_TEXT + e);
            e.printStackTrace();
        } catch (Exception e) {
            MeinIO.stringAusgeben(StringsammlungNetzwerk.FEHLER_IM_CLIENT_THREAD + e);
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
        NetzwerkFlagContainer.setBinIchClient(true);
        this.verbindungAktiv = true;
    }

    /**
     * Diese Methode initialisiert die benoetigten Sockets und stellt eine Verbindung zum Server her.
     * Sobald die Verbindung erfolgreich aufgebaut wurde, wird der Spieler darueber informiert.
     *
     * @throws IOException
     */
    @Override
    protected void initSocket() throws IOException {
        this.socket = new Socket(getServerAdresse(), getPort());
        this.socket.setTcpNoDelay(true);
        NetzwerkFlagContainer.setWirdOnlineGespielt(true);

        informiereSpielerVerbindungHergestellt(StringsammlungNetzwerk.CLIENT_ZU_SERVER_VERBINDUNG_HERGESTELLT);
        MeinIO.stringAusgeben(StringsammlungNetzwerk.CLIENT_IST_VERBUNDEN);
    }


    /**
     * Diese Methode uebergibt die empfangenen Daten vom Server zur Auswertung dieser an einen Controller.
     *
     * @param daten des Servers, die es auszuwerten gilt
     */
    @Override
    protected void werteDatenAus(Object daten) {
        ClientThreadController.getInstance().werteDatenAus(daten);
    }

    /**
     * Diese Methode prueft, ob der Client mit dem Server verbunden ist.
     * Es wird geprueft, ob der Socket null ist oder nicht.
     *
     * @return true, wenn der Socket nicht null ist; sonst false
     */
    @Override
    public boolean istVerbunden() {
        if (this.socket == null) {
            return false;
        } else {
            return this.socket.isConnected();
        }
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

    public String getServerAdresse() {
        return serverAdresse;
    }


}

