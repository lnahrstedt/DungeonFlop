package control.model;

import model.Ebene;
import model.Spielwelt;
import model.raum.Gegnerraum;
import model.raum.Raum;
import resources.exceptions.RaumIstKeinNachbarException;
import resources.interfaces.StringsammlungException;
import utilities.CheatMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;


/**
 * Diese Klasse steuert bzw. verwaltet alle Vorgaenge im Zusammenhang einer Ebene.
 */
public class EbenenController {


    /**
     * Diese Methode prueft, ob der uebergebene Raum ein Nachbar des aktuellen Raums ist.
     * Ausserdem untersucht sie, ob der Spieler den Raum ausgewaehlt hat, in dem er sich bereits befindet.
     *
     * @param raum
     * @throws RaumIstKeinNachbarException Diese wird dann geworfen, wenn der Spieler einen Raum ausgewaehlt hat, der
     *                                     kein Nachbar des aktuellen Raumes ist und er nicht seine aktuelle Position gewaehlt hat.
     */
    public static void pruefeObRaumNachbarOderAktuellerRaum(Raum raum) throws RaumIstKeinNachbarException {
        HashMap<Raum, ArrayList<Raum>> raeumeUndNachbarraeume = Spielwelt.getInstance().getSpielerEbenePosition().getLayout();

        boolean istRaumEinNachbarraum = raeumeUndNachbarraeume.get(Spielwelt.getInstance().getSpielerEbenePosition().getSpielerRaumPosition()).contains(raum);
        boolean wurdeAufAktuellenRaumGeklickt = Spielwelt.getInstance().getSpielerEbenePosition().getSpielerRaumPosition().equals(raum);

        if (!istRaumEinNachbarraum && !wurdeAufAktuellenRaumGeklickt) {
            throw new RaumIstKeinNachbarException(raum, StringsammlungException.RAUM_KEIN_NACHBAR_EXCEPTION_MESSAGE);
        }
    }

    /**
     * Tauscht einen Raum mit einem anderen Raum innerhalb einer Ebene aus.
     * Sollte die Spielerposition auf dem zu wechselenden Raum liegen, wird sie auf den neuen Raum gesetzt.
     *
     * @param ebene
     * @param zuWechselnderRaum
     * @param ersetzenderRaum
     */
    public static void raumAustauschen(Ebene ebene, Raum zuWechselnderRaum, Raum ersetzenderRaum) {
        HashMap<Raum, ArrayList<Raum>> aktuellesLayout = ebene.getLayout();
        HashMap<Raum, ArrayList<Raum>> neuesLayout = new HashMap<>();
        //Setzt Spielerposition zu neuen den Raum, sollte sie auf den alten Raum liegen.
        if (ebene.getSpielerRaumPosition() == zuWechselnderRaum) {
            ebene.setSpielerRaumPosition(ersetzenderRaum);
        }
        //Geht jeden Key und somit jeden Raum in dem Layout durch.
        for (Raum key : aktuellesLayout.keySet()) {
            if (key == zuWechselnderRaum) {
                //Ist der zu wechselnde Raum gefunden, wird in dem neuen Layout der Ersatzraum hinzuguegt und die Nachbarraeume uebernommen.
                neuesLayout.put(ersetzenderRaum, aktuellesLayout.get(key));
            } else {
                //Sonst wird der alte Raum und Nachbarraeume uebernommen.
                neuesLayout.put(key, aktuellesLayout.get(key));
            }
        }
        /*Aendert die Nachbarraeume aller Raeume:
        Der zu wechselnde Raum wird mit dem zu ersetzenden Raum ausgetauscht */
        for (Raum key : aktuellesLayout.keySet()) {
            for (Raum nachbarRaum : aktuellesLayout.get(key)) {
                if (nachbarRaum == zuWechselnderRaum) {
                    neuesLayout.get(key).remove(nachbarRaum);
                    neuesLayout.get(key).add(ersetzenderRaum);
                    break;
                }
            }
        }
        ebene.setLayout(neuesLayout);
    }

    /**
     * Diese Methode ueberprueft, ob innerhalb einer Ebene alle Gegnerraume besiegt wurde.
     *
     * @return true, wenn alle Raeume besigt wurden; sonst false
     */
    public static boolean wurdenAlleRaeumeBesiegt() {
        boolean alleRaeumeBesiegt = true;
        Set<Raum> raeumeInEbene = Spielwelt.getInstance().getSpielerEbenePosition().getLayout().keySet();
        if (CheatMode.getInstance().istAktiv()) {
            return true;
        }
        for (Raum raum : raeumeInEbene) {
            if (raum instanceof Gegnerraum) {
                alleRaeumeBesiegt = false;
                break;
            }
        }
        return alleRaeumeBesiegt;
    }
}
