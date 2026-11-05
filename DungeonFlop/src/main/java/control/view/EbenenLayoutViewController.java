package control.view;

import control.model.EbenenController;
import control.model.KampfController;
import control.model.raum.RaumController;
import control.netzwerk.ServerThreadController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import model.KampfDatenPaket;
import model.Spielwelt;
import model.netzwerk.NetzwerkFlagContainer;
import model.raum.*;
import resources.enums.ServerClientKommunikation;
import resources.exceptions.RaumIstKeinNachbarException;
import resources.interfaces.PfadSammlung;
import resources.interfaces.StringsammlungAlert;
import utilities.MusikUndSoundController;

import java.util.Optional;

/**
 * Diese Klasse steuert bzw. verwaltet alle Vorgaenge im Zusammenhang des ErstelleLayoutViews.
 * Zum Beispiel verarbeitet sie die Eingaben des Spielers.
 */
public class EbenenLayoutViewController {

    /**
     * Diese Methode dient der Steuerung der Buttons im Ebenenlayout.
     * Basierend auf der Auswahl des Spielers wird er durch einen Dialog aufgefordert, eine Aktion wie "kaempfen",
     * "eintreten" oder "nichts tun" auszuwaehlen.
     * Ausserdem behandelt diese Methode die moeglich auftretende Exception "RaumIstKeinNachbarException" und
     * benachrichtigt den Spieler in Form eines Dialoges, dass er einen Raum ausgewaehlt hat,
     * der kein Nachbarraum seines aktuellen Raumes ist.
     *
     * @param raum
     * @return actionEventEventHandler
     */
    public static EventHandler<ActionEvent> wechselZuRaum(Raum raum) {
        return actionEvent -> {
            if (!NetzwerkFlagContainer.binIchClient()) {
                try {
                    EbenenController.pruefeObRaumNachbarOderAktuellerRaum(raum);
                } catch (RaumIstKeinNachbarException raumIstKeinNachbarException) {
                    gibWarnungAlertAus(raumIstKeinNachbarException.getMessage());
                    return;
                }
                verarbeiteKnopfDruck(raum);
            } else {
                AlertFabrik.generiereAlert(Alert.AlertType.WARNING, StringsammlungAlert.CLIENT_BUTTONS_DEAKTIVIERT_TITEL, StringsammlungAlert.CLIENT_BUTTONS_DEAKTIVIERT_MESSAGE_BEWEGEN).show();
            }
        };
    }

    /**
     * Sofern der Spieler eine regelkonforme Eingabe getaetigt hat, wird diese Methode aufgerufen.
     * Sie prueft zunaechst, ob der Spieler einen Nachbarraum ausgewahlt hat oder seinen aktuellen Raum.
     * Auf Basis des Ergebnisses werden weiterfuehrende Methoden aufgerufen.
     *
     * @param raum
     */
    private static void verarbeiteKnopfDruck(Raum raum) {
        boolean wurdeAktuellerRaumAusgewaehlt = Spielwelt.getInstance().getSpielerEbenePosition().getSpielerRaumPosition().equals(raum);

        if (wurdeAktuellerRaumAusgewaehlt) {
            gibAuswahlAlertAus(raum);
        } else {
            bewegeZuRaum(raum);
        }
    }

    /**
     * Diese Methode wird aufgerufen, sofern der Spieler einen Raum ausgewaehlt hat, der weder Nachbar seines aktuellen
     * Raumes noch sein aktueller Raum ist.
     * Es wird ein Dialog erstellt, der den Spieler darueber informiert.
     *
     * @param text
     */
    private static void gibWarnungAlertAus(String text) {
        AlertFabrik.generiereAlert(Alert.AlertType.WARNING, StringsammlungAlert.RAUM_KEIN_NACHBAR_TITEL, text).show();
    }

    /**
     * Diese Methode wird aufgerufen, wenn der Spieler seinen aktuellen Raum ausgewaehlt hat.
     * Auf Basis der Raumart wird ein Dialog erzeugt, der den Spieler auffordert, eine Auswahl zu treffen.
     * Diese Auswahl wird ebenfalls von dieser Methode verarbeitet.
     *
     * @param raum
     */
    private static void gibAuswahlAlertAus(Raum raum) {
        Alert alert = EbenenLayoutAlertController.generiereLayoutAlert(raum);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent()) {
            if (result.get().getButtonData() == ButtonType.YES.getButtonData()) {
                wechselRaumScene(raum);
            }
            if (result.get().getButtonData() == ButtonType.OK.getButtonData()) {
                bewegeZuRaum(raum);
            }
            if (result.get().getButtonData() == ButtonType.NEXT.getButtonData()) {
                wechselEbene(raum);
            }
            if (result.get().getButtonData() == ButtonType.FINISH.getButtonData()) {
                beendeSpiel(raum);
            }
        }
    }

    /**
     * Moechte der Spieler einen Spezialraum betreten oder in einem Gegnerraum kaempfen, steuert diese Methode das
     * Wechseln der einzelnen Szenen.
     *
     * @param raum
     */
    private static void wechselRaumScene(Raum raum) {

        if (raum instanceof Gegnerraum gegnerraum) {
            wechselZuGegnerraum(gegnerraum);
            return;
        }
        if (raum instanceof Haendlerraum haendlerraum) {
            wechselZuHaendlerraum(haendlerraum);
            return;
        }

        if (raum instanceof Schatzraum schatzraum) {
            wechselZuSchatzraum(schatzraum);
            return;
        }

        if (raum instanceof Schmiedraum schmiedraum) {
            wechselZuSchmiedraum(schmiedraum);
        }
    }

    /**
     * Diese Methode wird aufgerufen, wenn der Spieler einen Gegnerraum betritt.
     * Sie aktualisiert sie die Position des Spielers, informiert den Client und spielt einen Soundeffekt ab.
     *
     * @param gegnerraum
     */
    private static void wechselZuGegnerraum(Gegnerraum gegnerraum) {
        Spielwelt.getInstance().getSpielerEbenePosition().setSpielerRaumPosition(gegnerraum);
        MusikUndSoundController.spieleSoundeffekt(PfadSammlung.PFAD_SWORD_SOUNDEFFEKT);
        RaumController.fuehreRaumAus(gegnerraum);

        ServerThreadController.getInstance().sendeDaten(new KampfDatenPaket(KampfController.getKampf(), KampfController.getAktuelleRunde(), KampfController.getGegenSpieler()));

        ViewWechsler.wechselZuGegnerraumViewMitZurueckOption();
    }

    /**
     * Diese Methode wird aufgerufen, wenn der Spieler einen Haendlerraum betritt.
     * Sie aktualisiert sie die Position des Spielers, informiert den Client und spielt einen Soundeffekt ab.
     *
     * @param haendlerraum
     */
    private static void wechselZuHaendlerraum(Haendlerraum haendlerraum) {
        Spielwelt.getInstance().getSpielerEbenePosition().setSpielerRaumPosition(haendlerraum);
        ServerThreadController.getInstance().sendeDaten(ServerClientKommunikation.SERVER_INTERAGIERT_MIT_RAUM);
        MusikUndSoundController.spieleSoundeffekt(PfadSammlung.PFAD_TUER_SOUNDEFFEKT);
        ViewWechsler.wechselZuHaendlerraumViewMitZurueckOption(haendlerraum);
    }

    /**
     * Diese Methode wird aufgerufen, wenn der Spieler einen Schatzraum betritt.
     * Sie aktualisiert sie die Position des Spielers, informiert den Client und spielt einen Soundeffekt ab.
     *
     * @param schatzraum
     */
    private static void wechselZuSchatzraum(Schatzraum schatzraum) {
        Spielwelt.getInstance().getSpielerEbenePosition().setSpielerRaumPosition(schatzraum);
        ServerThreadController.getInstance().sendeDaten(ServerClientKommunikation.SERVER_INTERAGIERT_MIT_RAUM);
        MusikUndSoundController.spieleSoundeffekt(PfadSammlung.PFAD_TUER_SOUNDEFFEKT);
        ViewWechsler.wechselZuSchatzraumViewMitZurueckOption(schatzraum);
    }

    /**
     * Diese Methode wird aufgerufen, wenn der Spieler einen Schmiedraum betritt.
     * Sie aktualisiert sie die Position des Spielers, informiert den Client und spielt einen Soundeffekt ab.
     *
     * @param schmiedraum
     */
    private static void wechselZuSchmiedraum(Schmiedraum schmiedraum) {
        Spielwelt.getInstance().getSpielerEbenePosition().setSpielerRaumPosition(schmiedraum);
        ServerThreadController.getInstance().sendeDaten(ServerClientKommunikation.SERVER_INTERAGIERT_MIT_RAUM);
        MusikUndSoundController.spieleSoundeffekt(PfadSammlung.PFAD_TUER_SOUNDEFFEKT);
        ViewWechsler.wechselZuSchmiedraumViewMitZurueckOption(schmiedraum);
    }

    /**
     * Sofern der Spieler einen seiner Nachbarraeume ausgewaehlt hat, wird diese Methode aufgerufen.
     * Sie aktualisiert die Position des Spielers und laed den EbenenLayoutView neu.
     *
     * @param raum
     */
    private static void bewegeZuRaum(Raum raum) {
        Spielwelt.getInstance().getSpielerEbenePosition().setSpielerRaumPosition(raum);
        ServerThreadController.getInstance().sendeDaten(raum);
        ViewWechsler.wechselZuEbenenLayoutView();
    }

    /**
     * Hat der Spieler einen Leiterraum erreicht und wuenscht in die naechste Ebene zu gelangen, wird diese Methode aufgerufen.
     * Sie ruft eine Methode des Raumcontrollers auf und laed den EbenenLayoutView neu.
     * Ausserdem spielt sie ein Soundeffekt ab.
     *
     * @param raum
     */
    private static void wechselEbene(Raum raum) {
        RaumController.fuehreRaumAus(raum);
        MusikUndSoundController.spieleSoundeffekt(PfadSammlung.PFAD_TRAP_SOUNDEFFEKT);
        ServerThreadController.getInstance().sendeDaten(Spielwelt.getInstance());
        ViewWechsler.wechselZuEbenenLayoutView();
    }

    /**
     * Sofern der Spieler den letzten Leiterraum des Spiels erreicht hat und das Spiel beenden moechte, wird diese Methode aufgerufen.
     * Sie ruft eine Methode des Raumcontrollers auf und setzt die Szene auf den CreditsView.
     * Ausserdem spielt sie einen Soundeffekt ab.
     *
     * @param raum
     */
    private static void beendeSpiel(Raum raum) {
        RaumController.fuehreRaumAus(raum);
        MusikUndSoundController.spieleSoundeffekt(PfadSammlung.PFAD_PARTY_HORN_SOUNDEFFEKT);
        ViewWechsler.wechselZuCreditsView();
    }

}
