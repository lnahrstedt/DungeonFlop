package control.netzwerk;

import control.model.KampfController;
import control.model.RundenController;
import control.view.AlertFabrik;
import control.view.ChatViewController;
import control.view.SpielLadenViewController;
import control.view.ViewWechsler;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import model.HauptSpieler;
import model.KampfDatenPaket;
import model.Spielwelt;
import model.netzwerk.Chat;
import model.netzwerk.NetzwerkFlagContainer;
import model.raum.Raum;
import resources.enums.ServerClientKommunikation;
import resources.interfaces.StringsammlungAlert;
import resources.interfaces.StringsammlungGUI;
import view.EbenenLayoutView;

/**
 * Diese Klasse ist fuer die Auswertung der vom Server gesendeten Daten zustaendig.
 * Sie ist eine Subklasse von DatenAuswerter.
 */
public non-sealed class ClientThreadDatenAuswerter extends DatenAuswerter {


    /**
     * Diese Methode wertet die vom Server gesendeten Daten aus und ruft auf Basis dieser Daten weitere Methoden auf.
     *
     * @param daten, die ausgewertet werden sollen
     */
    public static void werteDatenAus(Object daten) {

        if (daten instanceof byte[] nachricht) {
            werteChatnachrichtAus(security.entschluessel(nachricht));
            return;
        }

        if (daten instanceof Spielwelt spielwelt) {
            handleSpielweltEmpfangen(spielwelt);
            return;
        }

        if (daten instanceof Raum raum) {
            handleRaumEmpfangen(raum);
            return;
        }

        if (daten instanceof HauptSpieler hauptSpieler) {
            handleHauptSpielerEmpfangen(hauptSpieler);
            return;
        }

        if (daten instanceof KampfDatenPaket kampfDatenPaket) {
            werteKampfDatenPaketAus(kampfDatenPaket);
            return;
        }

        if (daten instanceof ServerClientKommunikation serverClientKommunikation) {
            switch (serverClientKommunikation) {
                case SERVER_HAT_SPIEL_VERLASSEN -> handleServerHatSpielVerlassen();
                case SERVER_INTERAGIERT_MIT_RAUM -> handleServerInteragiertMitRaum();
                case SERVER_INTERAGIERT_MIT_INVENTAR -> handleServerInteragiertMitInventar();
                case SERVER_INTERAGIERT_MIT_DECK -> handleServerInteragiertMitDeck();
                case ZUG_WURDE_BEENDET -> handleZugWurdeBeendet();
                case VERLOREN -> handleSpielVerloren(new EbenenLayoutView());
                case CLIENT_ZUG_BEENDEN -> handleClientZugBeenden();
            }
        }

    }

    /**
     * Diese Methode wird aufgerufen, wenn der Server nach erfolgreicher Erstverbindung seine Spielwelt uebersendet hat.
     * Sie ersetzt die Spielwelt des Clients auf die des Servers und wechselt zum EbenenLayoutView.
     *
     * @param spielwelt des Servers
     */
    private static void handleSpielweltEmpfangen(Spielwelt spielwelt) {
        ersetzeSpielwelt(spielwelt);
        Platform.runLater(ViewWechsler::wechselZuEbenenLayoutView);
    }


    /**
     * Diese Methode wird aufgerufen, wenn der Server einen Raum uebersendet hat.
     * Die Raumposition des Clients wird auf die des Servers gesetzt.
     *
     * @param raum des Servers
     */
    private static void handleRaumEmpfangen(Raum raum) {
        Spielwelt.getInstance().getSpielerEbenePosition().setSpielerRaumPosition(raum);
        if (NetzwerkFlagContainer.istChatGeoeffnet()) {
            Platform.runLater(ChatViewController::wennChatOffenLassOffen);

        } else {
            Platform.runLater(ViewWechsler::wechselZuEbenenLayoutView);
        }
    }

    /**
     * Diese Methode wird aufgerufen, wenn der Server den Hauptspieler uebersendet hat.
     * Der Hauptspieler des Clients wird auf den des Servers gesetzt.
     *
     * @param hauptSpieler des Servers
     */
    private static void handleHauptSpielerEmpfangen(HauptSpieler hauptSpieler) {
        Spielwelt.getInstance().setProtagonist(hauptSpieler);
        Platform.runLater(ViewWechsler::wechselZuEbenenLayoutView);
    }

    /**
     * Diese Methode wird aufgerufen, wenn der Server dem Client mitgeteilt hat, dass er das Spiel verlassen hat.
     * Zunaechst wird der Spieler mittels eines Alerts ueber das Verlassen des Servers informiert und im Anschluss wird die Verbindung gekappt.
     * Daraufhin gelangt der Spieler im Client wieder zum Hauptmenue.
     */
    private static void handleServerHatSpielVerlassen() {
        informiereSpielerServerDisconnected();
        Chat.getInstance().zuruecksetzen();
        ClientThreadController.getInstance().reset();
    }

    /**
     * Diese Methode wird aufgerufen, wenn der Server den Client ueber eine Interaktion mit einem Raum benachrichtigt.
     */
    private static void handleServerInteragiertMitRaum() {
        Platform.runLater(() -> ViewWechsler.wechselEbenenLayoutViewServerInteragiert(Spielwelt.getInstance().getProtagonist().getName() + StringsammlungGUI.SERVER_INTERAGIERT_MIT_RAUM));
    }

    /**
     * Diese Methode wird aufgerufen, wenn der Server den Client ueber eine Interaktion mit dem Inventar benachrichtigt.
     */
    private static void handleServerInteragiertMitInventar() {
        Platform.runLater(() -> ViewWechsler.wechselEbenenLayoutViewServerInteragiert(Spielwelt.getInstance().getProtagonist().getName() + StringsammlungGUI.SERVER_INTERAGIERT_MIT_INVENTAR));
    }

    /**
     * Diese Methode wird aufgerufen, wenn der Server den Client ueber eine Interaktion mit dem Deck benachrichtigt.
     */
    private static void handleServerInteragiertMitDeck() {
        Platform.runLater(() -> ViewWechsler.wechselEbenenLayoutViewServerInteragiert(Spielwelt.getInstance().getProtagonist().getName() + StringsammlungGUI.SERVER_INTERAGIERT_MIT_DECK));
    }

    /**
     * Diese Methode erstellt ein Alert, dass den Spieler ueber das Verlassen des Servers informiert.
     */
    private static void informiereSpielerServerDisconnected() {
        Platform.runLater(() -> AlertFabrik.generiereAlert(Alert.AlertType.INFORMATION, StringsammlungAlert.SERVER_HAT_VERLASSEN_TITEL, StringsammlungAlert.SERVER_HAT_VERLASSEN_NACHRICHT).show());
    }

    /**
     * Diese Methode ersetzt die Spielwelt des Clients zu der des Servers.
     *
     * @param spielwelt des Servers
     */
    private static void ersetzeSpielwelt(Spielwelt spielwelt) {
        SpielLadenViewController.setSpielwelt(spielwelt);
    }

    /**
     * Diese Methode wird aufgerufen, wenn der Server den Client darueber informiert hat, dass er seinen Zug beendet hat.
     * Auf Basis dieser Information wird die Flag "BinIchAnDerReihe" auf "true" gesetzt und die View auf den Gegnerraum gesetzt.
     */
    private static void handleZugWurdeBeendet() {
        NetzwerkFlagContainer.setBinIchAnDerReihe(true);
        Platform.runLater(ViewWechsler::wechselZuGegnerraumView);
    }

    /**
     * Diese Methode wird aufgerufen, wenn der Client einem bestehenden Kampf beitritt und sein Zug beendet werden muss.
     */
    private static void handleClientZugBeenden() {
        RundenController.beendeRunde(KampfController.getKampf(), KampfController.getKampf().getSpieler(), KampfController.getGegenSpieler(), KampfController.getKampf().getSpielfeld());
        ClientThreadController.getInstance().sendeDaten(ServerClientKommunikation.ZUG_WURDE_BEENDET);
        NetzwerkFlagContainer.setBinIchAnDerReihe(false);
        Platform.runLater(ViewWechsler::wechselZuGegnerraumView);
    }
}