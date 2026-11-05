package model.raum;

import java.io.Serializable;

/**
 * Diese Klasse stellt einen Raum dar, indem die Kaempfe ausgetragen werden.
 */
public non-sealed class Gegnerraum extends Raum implements Serializable {

    private final float schwierigkeitsgrad;

    public Gegnerraum(String name, String beschreibung, int raumnummer, float schwierigkeitsgrad) {
        super(name, beschreibung, raumnummer);
        this.schwierigkeitsgrad = schwierigkeitsgrad;
    }

    public float getSchwierigkeitsgrad() {
        return schwierigkeitsgrad;
    }

}
