package model.talent;

import java.io.Serializable;

/**
 * Die Superklasse aller Talente.
 * Die Klasse setzt voraus, dass alle Talente die Attribute Name, Beschreibung und die Methode ausfuehren hat.
 */
public abstract sealed class Talent implements Serializable permits ErfahrungsTalent, GoldTalent, HeldAngriffTalent, HeldLebenTalent, ZufaelligeVerbesserungTalent {

    private final String name;
    private final String beschreibung;

    public Talent(String name, String beschreibung) {
        this.name = name;
        this.beschreibung = beschreibung;
    }

    /**
     * Diese Methode wird in jeder Subklasse mit dem entsprechenden Effekt ueberschrieben.
     */
    public void ausfuehren() {
    }

    public String getName() {
        return name;
    }

    public String getBeschreibung() {
        return beschreibung;
    }
}
