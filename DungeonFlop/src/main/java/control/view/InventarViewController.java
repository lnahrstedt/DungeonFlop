package control.view;

import control.netzwerk.ServerThreadController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import model.Boni;
import model.Spielfigur;
import model.Spielwelt;
import model.Waffe;
import model.effekt.Effekt;
import model.netzwerk.NetzwerkFlagContainer;
import resources.enums.ServerClientKommunikation;
import resources.interfaces.StringsammlungAlert;
import resources.interfaces.ZahlensammlungGUI;

import java.util.ArrayList;

/**
 * Diese Klasse stellt der InventarView Methoden zur Verfuegung.
 * Zum Beispiel verarbeitet sie die Eingaben des Spielers.
 */
public class InventarViewController {

    /**
     * Wechselt in die Inventarscene und fuegt die vorherige Scene dem Zurueckstack hinzu.
     *
     * @return EventHandler
     */
    public static EventHandler<ActionEvent> onInventarButton() {
        return actionEvent -> {
            if (!NetzwerkFlagContainer.binIchClient()) {
                ServerThreadController.getInstance().sendeDaten(ServerClientKommunikation.SERVER_INTERAGIERT_MIT_INVENTAR);
                ViewWechsler.wechselZuInventarViewMitZurueckOption();
            } else {
                AlertFabrik.generiereAlert(Alert.AlertType.WARNING, StringsammlungAlert.CLIENT_BUTTONS_DEAKTIVIERT_TITEL, StringsammlungAlert.CLIENT_BUTTONS_DEAKTIVIERT_MESSAGE_ALLGEMEIN).show();
            }
        };
    }

    /**
     * Setzt die ausgewaehlte Waffe an die erste Stelle der ArrayList, also wird die gewaehlte Waffe ausgeruestet.
     * Falls die ausgewaehlte Waffe einen Effekt besitzt wird dieser der Spielfigur hinzugefuegt.
     *
     * @param waffe Die ausgewaehlte Waffe
     * @return EventHandler
     */
    public static EventHandler<MouseEvent> waffeClicked(Waffe waffe) {
        return mouseEvent -> {
            ArrayList<Waffe> waffenInventar = Spielwelt.getInstance().getProtagonist().getInventar().getWaffeninventar();
            ArrayList<Effekt> effekte = Spielwelt.getInstance().getProtagonist().getSpielfigur().getEffekte();
            Spielfigur heldenFigur = Spielwelt.getInstance().getProtagonist().getSpielfigur();
            int vorherigerSchaden = waffenInventar.get(ZahlensammlungGUI.INVENTAR_AKTIVE_WAFFE_POS).getSchaden();
            effekte.remove(waffenInventar.get(ZahlensammlungGUI.INVENTAR_AKTIVE_WAFFE_POS).getEffekt());
            if (waffe.getEffekt() != null) {
                effekte.add(waffe.getEffekt());
            }
            waffenInventar.remove(waffe);
            waffenInventar.add(waffenInventar.set(ZahlensammlungGUI.INVENTAR_AKTIVE_WAFFE_POS, waffe));

            heldenFigur.setAngriffspunkte(heldenFigur.getAngriffspunkte() - vorherigerSchaden + waffe.getSchaden());

            ViewWechsler.wechselZuInventarView();
        };
    }

    /**
     * Setzt das ausgewaehlte Artefakt an die erste Stelle der ArrayList, also wird das gewaehlte Artefakt ausgeruestet.
     * Fuegt den Effekt eines Artefakts der Spielfigur hinzu.
     *
     * @param boni Das ausgewaehlte Artefakt
     * @return EventHandler
     */
    public static EventHandler<MouseEvent> artefaktClicked(Boni boni) {
        return mouseEvent -> {
            ArrayList<Effekt> effekte = Spielwelt.getInstance().getProtagonist().getSpielfigur().getEffekte();
            ArrayList<Boni> artefaktInventar = Spielwelt.getInstance().getProtagonist().getInventar().getArtefaktinventar();
            artefaktInventar.remove(boni);
            effekte.add(boni.getEffekt());
            if (artefaktInventar.size() == 1) {
                artefaktInventar.add(artefaktInventar.set(0, boni));
            } else if (artefaktInventar.size() > 1) {
                effekte.remove(artefaktInventar.get(1).getEffekt());
                artefaktInventar.add(artefaktInventar.get(1));
                artefaktInventar.set(1, artefaktInventar.set(0, boni));
            }
            ViewWechsler.wechselZuInventarView();
        };
    }
}
