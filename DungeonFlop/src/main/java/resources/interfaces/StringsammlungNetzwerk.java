package resources.interfaces;

/**
 * Dieses Interface speichert Strings, die fuer die Netzwerkverbindung benoetigt werden.
 */
public interface StringsammlungNetzwerk {

    String CLIENT_ZU_SERVER_VERBINDUNG_HERGESTELLT = "Verbindung erfolgreich hergestellt!";
    String CLIENT_ZU_SERVER_VERBINDUNG_NICHT_HERGESTELLT = "Verbindung zum Server konnte nicht hergestellt werden!\nPruefe die eingegebene IP-Adresse und versuche es erneut.\n\nMoeglicherweise wurde die maximale Anzahl an Spielern erreicht.";
    String CLIENT_ZU_SERVER_BEIGETRETEN = "Es ist ein Client beigetreten!\n\nIP-Adresse des Client:  ";
    String CLIENT_IST_VERBUNDEN = "Client ist mit einem Server verbunden";
    String NEUER_SERVERTHREAD = "Neuer Server-Thread erstellt";
    String NEUER_CLIENTTHREAD = "Neuer Client-Thread erstellt";
    String SERVER_RERUN = "run() im ServerThread aufgerufen";
    String CLIENT_RERUN = "run() im ClientThread aufgerufen";
    String SOCKET_EXCEPTION_TEXT = "Es ist eine Socketexception aufgetreten! ";
    String EOFException_TEXT = "Es ist eine EOFException aufgetreten! ";
    String FEHLER_BEIM_SCHLIESSEN_DES_CLIENT = "Beim schliessen des Client ist ein Fehler aufgetreten! ";
    String FEHLER_BEIM_SCHLIESSEN_DES_SERVERS = "Beim schliessen des Servers ist ein Fehler aufgetreten! ";
    String FEHLER_IM_SERVER_THREAD = "Es ist eine Exception im ServerThread aufgetreten! ";
    String FEHLER_IM_CLIENT_THREAD = "Es ist eine Exception im ClientThread aufgetreten! ";
    String SERVER_SOCKET_GESCHLOSSEN = "Der ServerSocket wurde geschlossen";
    String CLIENT_VON_SERVER_GETRENNT = "Ein Client wurde vom Server getrennt!";
    String CLIENT_GESCHLOSSEN = "Der Client wurde geschlossen";
    String SERVER_WARTET_AUF_VERBINDUNG = "Warten auf Verbindungen...";
    String CLIENT_DEM_SERVER_BEIGETRETEN = "Ein Client ist dem Server beigetreten.";
    String SENDEN_DATEN_CLIENT_SERVER_ERROR = "Das Senden von Daten vom Client zum Server ist fehlgeschlagen!";
    String SENDEN_DATEN_SERVER_CLIENT_ERROR = "Das Senden von Daten vom Server zum Client ist fehlgeschlagen!";

}