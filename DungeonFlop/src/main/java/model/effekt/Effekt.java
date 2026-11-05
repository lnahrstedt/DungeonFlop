package model.effekt;

import model.AktionsSpeicher;
import resources.interfaces.StringsammlungGUI;

import java.io.Serializable;

/**
 * Die abstrakte Klasse Effekt buendelt die Effekte.
 */
public abstract sealed class Effekt implements Serializable permits MehrfachSchlagenEffekt, ReichweiteEffekt, SofortLoslaufenEffekt, WeiterGehenEffekt {
    private String name;
    private String beschreibung;

    public Effekt(String name, String beschreibung) {
        this.name = name;
        this.beschreibung = beschreibung;
    }

    public Effekt() {
    }

    public String getName() {
        return name;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    /**
     * Abstrakte Methode, die den Effekt ausfuehrt.
     *
     * @param aktionsSpeicher
     */
    public abstract void ausfuehren(AktionsSpeicher aktionsSpeicher);

    /**
     * Gibt den Namen und die Beschreibung des Effekts als String zurueck.
     *
     * @return Name, Beschreibung
     */
    @Override
    public String toString() {
        return this.getName() + StringsammlungGUI.CONSOLE_PLATZHALTER_KOMMA +
                this.getBeschreibung();
    }
}
