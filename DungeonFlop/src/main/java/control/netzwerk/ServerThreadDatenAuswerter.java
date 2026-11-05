package control.netzwerk;

import control.view.AlertFabrik;
import control.view.ViewWechsler;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import model.KampfDatenPaket;
import model.netzwerk.Chat;
import model.netzwerk.NetzwerkFlagContainer;
import resources.enums.ServerClientKommunikation;
import resources.interfaces.StringsammlungAlert;
import view.Buehne;
import view.GegnerraumView;
import view.StartView;

/**
 * Diese Klasse ist fuer das Auswerten der vom Client gesendeten Daten zustaendig.
 * Sie ist eine Subklasse von DatenAuswerter.
 */
public non-sealed class ServerThreadDatenAuswerter extends DatenAuswerter {

    /**
     * Diese Methode wertet die vom Client gesendeten Daten aus und ruft auf Basis dieser Daten weitere Methoden auf.
     *
     * @param daten, die ausgewertet werden sollen
     */
    public static void werteDatenAus(Object daten) {

        if (daten instanceof byte[] nachricht) {
            werteChatnachrichtAus(security.entschluessel(nachricht));
            return;
        }

        if (daten instanceof KampfDatenPaket kampfDatenPaket) {
            werteKampfDatenPaketAus(kampfDatenPaket);
            return;
        }

        if (daten instanceof ServerClientKommunikation serverClientKommunikation) {
            switch (serverClientKommunikation) {
                case ZUG_WURDE_BEENDET -> handleZugWurdeBeendet();
                case CLIENT_HAT_SPIEL_VERLASSEN -> handleClientHatDasSpielVerlassen();
                case VERLOREN -> handleSpielVerloren(new StartView());
            }
        }
    }

    /**
     * Diese Methode wird aufgerufen, wenn der Client dem Server mitgeteilt hat, dass er das Spiel verlassen hat.
     * Sie informiert den Spieler mittels eines Alerts ueber das Verlassen des Clients, schliesst die Verbindung des Servers und wartet anschliessend auf einen neuen Client.
     */
    private static void handleClientHatDasSpielVerlassen() {
        ServerThreadController.getInstance().reset();
        if (Buehne.getInstance().getViewable() instanceof GegnerraumView) {
            informiereSpielerClientDisconnected(StringsammlungAlert.CLIENT_HAT_IM_GEGNERRAUM_VERLASSEN_NACHRICHT);
            Platform.runLater(ViewWechsler::wechselZuEbenenLayoutView);
        } else {
            informiereSpielerClientDisconnected(StringsammlungAlert.CLIENT_HAT_VERLASSEN_NACHRICHT);
        }
        Chat.getInstance().zuruecksetzen();
        ServerThreadController.getInstance().getServer().run();
    }

    /**
     * Diese Methode erstellt ein Alert, dass den Spieler ueber das Verlassen des Clients informiert.
     */
    private static void informiereSpielerClientDisconnected(String nachricht) {
        Platform.runLater(() -> AlertFabrik.generiereAlert(Alert.AlertType.INFORMATION, StringsammlungAlert.CLIENT_HAT_VERLASSEN_TITEL, nachricht).show());
    }

    /**
     * Diese Methode setzt eine Flag, sobald der Zug beendet wurde und wechselt zum aktualisierten Gegnerraum.
     */
    private static void handleZugWurdeBeendet() {
        NetzwerkFlagContainer.setBinIchAnDerReihe(true);
        Platform.runLater(ViewWechsler::wechselZuGegnerraumView);
    }

}