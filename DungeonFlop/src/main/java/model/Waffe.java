package model;

import model.effekt.Effekt;
import resources.enums.WaffenSammlung;

import java.io.Serializable;

/**
 * Die Klasse Waffe augmentiert die Spielfigur des Spielers.
 */
public class Waffe implements Serializable {

    private final int schaden;
    private final int seltenheitsgrad;
    private final Effekt effekt;
    private final String name;

    /**
     * Erstellt eine Waffe auf Grundlage der Enumeration Waffensammlung.
     *
     * @param waffeVorlage
     */
    public Waffe(WaffenSammlung waffeVorlage) {
        this.schaden = waffeVorlage.getSchaden();
        this.seltenheitsgrad = waffeVorlage.getSeltenheitsgrad();
        this.effekt = waffeVorlage.getEffekt();
        this.name = waffeVorlage.getName();
    }

    public int getSchaden() {
        return schaden;
    }

    public int getSeltenheitsgrad() {
        return seltenheitsgrad;
    }

    public Effekt getEffekt() {
        return effekt;
    }

    public String getName() {
        return name;
    }

}
