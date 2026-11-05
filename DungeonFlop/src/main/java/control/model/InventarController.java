package control.model;

import model.Boni;
import model.Inventar;
import model.Waffe;

/**
 * Diese Klasse steuert bzw. verwaltet alle Vorgaenge im Zusammenhang eines Inventars.
 */
public class InventarController {

    /**
     * Fuegt eine Waffe dem Inventar hinzu.
     *
     * @param inventar
     * @param waffe
     */
    public static void fuegeItemHinzu(Inventar inventar, Waffe waffe) {
        inventar.getWaffeninventar().add(waffe);
    }

    /**
     * Fuegt eine Boni dem Inventar hinzu
     *
     * @param inventar
     * @param boni
     */
    public static void fuegeItemHinzu(Inventar inventar, Boni boni) {
        inventar.getArtefaktinventar().add(boni);
    }

    /**
     * Entfernt eine Waffe aus dem Inventar.
     *
     * @param inventar
     * @param waffe
     */
    public static void entferneItem(Inventar inventar, Waffe waffe) {
        int zuEntfernendeWaffe = sucheWaffeHeraus(inventar, waffe);
        inventar.getWaffeninventar().remove(zuEntfernendeWaffe);
    }

    /**
     * Gibt den Index innerhalb des Inventars der uebergebenen Waffe zurueck.
     *
     * @param inventar
     * @param waffe
     * @return index der zu entfernenden Waffe
     */
    private static int sucheWaffeHeraus(Inventar inventar, Waffe waffe) {
        return inventar.getWaffeninventar().indexOf(waffe);
    }

    /**
     * Entfernt einen Boni aus dem Inventar
     *
     * @param inventar
     * @param boni
     */
    public static void entferneItem(Inventar inventar, Boni boni) {
        int zuEntfernendeBoni = sucheBoniHeraus(inventar, boni);
        inventar.getArtefaktinventar().remove(zuEntfernendeBoni);
    }

    /**
     * Gibt den Index innerhalb des Inventars der uebergebenen Boni zurueck.
     *
     * @param inventar
     * @param boni
     * @return index des zu entfernenden Bonis
     */
    private static int sucheBoniHeraus(Inventar inventar, Boni boni) {
        return inventar.getArtefaktinventar().indexOf(boni);
    }
}
