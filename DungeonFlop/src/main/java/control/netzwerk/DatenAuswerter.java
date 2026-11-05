package control.netzwerk;

import control.model.KampfController;
import control.view.AlertFabrik;
import control.view.ViewWechsler;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import model.HauptSpieler;
import model.KampfDatenPaket;
import model.Spieler;
import model.Spielwelt;
import model.netzwerk.Chat;
import model.netzwerk.NetzwerkFlagContainer;
import resources.interfaces.PfadSammlung;
import resources.interfaces.StringsammlungAlert;
import utilities.MusikUndSoundController;
import utilities.Security;
import view.Buehne;
import view.Viewable;

/**
 * Diese abstrakte Superklasse beinhaltet universelle Methoden, die von Server und Client zum Auswerten von Daten benoetigt werden.
 */
public abstract sealed class DatenAuswerter permits ClientThreadDatenAuswerter, ServerThreadDatenAuswerter {

    protected static Security security = new Security();

    /**
     * Die Methode wertet empfangene Nachrichten aus und oeffnet das Chatfenster.
     *
     * @param nachricht
     */
    protected static void werteChatnachrichtAus(String nachricht) {
        Chat.getInstance().fuegeNachrichtHinzu(nachricht, false);
        if (NetzwerkFlagContainer.istChatGeoeffnet()) {
            Platform.runLater(ViewWechsler::wechselZuChatView);
        } else {
            NetzwerkFlagContainer.setUngeleseneNachricht(true);
            Platform.runLater(() -> Buehne.getInstance().aktualisiereAktuelleView());
            MusikUndSoundController.spieleSoundeffekt(PfadSammlung.PFAD_NOTIFICATION_SOUNDEFFEKT);
        }
    }

    /**
     * Diese Methode ist die Eingangsmethode fuer die Auswertung eines KampfDatenPakets.
     * Ausgehend von ihr wird das KampfDatenPaket an weitere Methoden zur Auswertung weitergereicht.
     *
     * @param kampfDatenPaket
     */
    protected static void werteKampfDatenPaketAus(KampfDatenPaket kampfDatenPaket) {
        kampfControllerAktualisieren(kampfDatenPaket);
        setzeHauptspielerNeu(kampfDatenPaket);
        Platform.runLater(ViewWechsler::wechselZuGegnerraumView);
    }

    /**
     * Diese Methode wird aufgerufen, wenn entweder der Client oder der Server das Spiel verloren hat.
     * Es wird ein Alert erzeugt, der den jeweiligen Spieler ueber diesen Umstand informiert.
     * Anschliessend wird der Server geschlossen
     *
     * @param viewable
     */
    protected static void handleSpielVerloren(Viewable viewable) {
        Platform.runLater(() -> Buehne.getInstance().setRootOfScene(viewable));
        if (!NetzwerkFlagContainer.binIchClient()) {
            ServerThreadController.getInstance().reset();
            Platform.runLater(() -> AlertFabrik.generiereAlert(Alert.AlertType.INFORMATION, StringsammlungAlert.RUNDE_VERLOREN_TITEL, StringsammlungAlert.RUNDE_VERLOREN_NACHRICHT).show());
        } else {
            Platform.runLater(() -> AlertFabrik.generiereAlert(Alert.AlertType.INFORMATION, StringsammlungAlert.RUNDE_VERLOREN_TITEL, StringsammlungAlert.RUNDE_VERLOREN_NACHRICHT_CLIENT).show());
        }

    }

    /**
     * Diese Methode aktualisiert den KampfController auf Basis des empfangenen KampfDatenPakets.
     *
     * @param kampfDatenPaket
     */
    private static void kampfControllerAktualisieren(KampfDatenPaket kampfDatenPaket) {
        KampfController.setKampf(kampfDatenPaket.getKampf());
        KampfController.setGegenSpieler(kampfDatenPaket.getSpieler());
        KampfController.setAktuelleRunde(kampfDatenPaket.getRunde());
    }

    /**
     * Diese Methode setzte den Hauptspieler auf Grundlage des empfangenen KampfDatenPakets neu.
     *
     * @param kampfDatenPaket
     */
    private static void setzeHauptspielerNeu(KampfDatenPaket kampfDatenPaket) {
        Spieler[] spieler = kampfDatenPaket.getKampf().getSpielfeld().getSpielfigurenAufDemFeld().keySet().toArray(new Spieler[0]);

        for (Spieler value : spieler) {
            if (value instanceof HauptSpieler) {
                Spielwelt.getInstance().setProtagonist((HauptSpieler) value);
                return;
            }
        }
    }
}
