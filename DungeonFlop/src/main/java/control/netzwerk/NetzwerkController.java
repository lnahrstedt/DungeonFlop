package control.netzwerk;

import control.model.KampfController;
import model.KampfDatenPaket;
import model.netzwerk.NetzwerkFlagContainer;
import resources.enums.ServerClientKommunikation;

import java.io.Serializable;

/**
 * Diese abstrakte Superklasse stellt die benoetigten Methoden im Zusammenhang mit dem Netzwerk zur Verfuegung.
 * Durch ihre abstrakten Methoden wird sichergestellt, dass ihre Subklassen (ServerThreadController und ClientThreadController) die fuer das Netzwerk relevante Methoden ausformulieren.
 */
public abstract sealed class NetzwerkController permits ClientThreadController, ServerThreadController {

    /**
     * Eine Methode zum schliessen der Verbindung.
     */
    public abstract void schliesseVerbindung();

    /**
     * Eine Methode zum Ruecksetzen
     */
    public abstract void reset();

    /**
     * Eine Methode zum Senden von Daten.
     *
     * @param daten, die uebermittelt werden
     */
    public abstract void sendeDaten(Serializable daten);

    /**
     * Eine Methode zum Auswerten von Daten.
     *
     * @param daten, die ausgewertet werden
     */
    public abstract void werteDatenAus(Object daten);

    /**
     * Diese Methode wird aufgerufen, wenn der Spieler eingewilligt hat, das Spiel zu verlassen.
     * Sie ist dafuer verantwortlich, dass sie jeweils dem anderen (Server dem Client bzw. CLient dem Server) eine
     * Information bezueglich der Schliessung des Spiels uebersendet.
     */
    public static void gegenseiteUeberSchliessungInformieren() {

        if (!NetzwerkFlagContainer.binIchClient() && ServerThreadController.getInstance().getServer() != null) {
            ServerThreadController.getInstance().sendeDaten(ServerClientKommunikation.SERVER_HAT_SPIEL_VERLASSEN);
            ServerThreadController.getInstance().schliesseServerSocket();
        }

        if (NetzwerkFlagContainer.binIchClient() && ClientThreadController.getInstance().getClient() != null) {
            ClientThreadController.getInstance().sendeDaten(ServerClientKommunikation.CLIENT_HAT_SPIEL_VERLASSEN);
            ClientThreadController.getInstance().reset();
        }
    }

    /**
     * Diese Methode versendet ein KampfDatenPaket, welches alle fuer die Gegenseite relevanten Informationen in Bezug auf den aktuellen Kampf enthaelt.
     */
    public static void sendeKampfDatenPaket() {
        if (ServerThreadController.getInstance().getServer() != null) {
            ServerThreadController.getInstance().sendeDaten(new KampfDatenPaket(KampfController.getKampf(), KampfController.getAktuelleRunde(), KampfController.getGegenSpieler()));
        } else {
            ClientThreadController.getInstance().sendeDaten(new KampfDatenPaket(KampfController.getKampf(), KampfController.getAktuelleRunde(), KampfController.getGegenSpieler()));
        }
    }

}
