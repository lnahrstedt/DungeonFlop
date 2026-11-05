package model;

import model.raum.Raum;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Stellt ein Level innerhalb des Dungeons dar.
 */
public class Ebene implements Serializable {

    private final String name;
    private final String story;
    private HashMap<Raum, ArrayList<Raum>> layout;
    private Raum spielerRaumPosition;

    /**
     * Erstellt eine neue Ebene
     *
     * @param name                Der Name der Ebene
     * @param story               Die Story der Ebene
     * @param layout              Die Anordnung der Raeume in der Ebene
     * @param spielerRaumPosition Die Position des Spielers in der Ebene
     */
    public Ebene(String name, String story, HashMap<Raum, ArrayList<Raum>> layout, Raum spielerRaumPosition) {
        this.name = name;
        this.story = story;
        this.layout = layout;
        this.spielerRaumPosition = spielerRaumPosition;
    }

    /**
     * Gibt den Namen und Story als String zurueck.
     *
     * @return Name: Story
     */
    public String toString() {
        return String.format("%s: %s", this.getName(), this.getStory());
    }

    public String getName() {
        return name;
    }

    public String getStory() {
        return story;
    }

    public HashMap<Raum, ArrayList<Raum>> getLayout() {
        return layout;
    }

    public void setLayout(HashMap<Raum, ArrayList<Raum>> layout) {
        this.layout = layout;
    }

    public Raum getSpielerRaumPosition() {
        return spielerRaumPosition;
    }

    public void setSpielerRaumPosition(Raum spielerRaumPosition) {
        this.spielerRaumPosition = spielerRaumPosition;
    }

    /**
     * Diese methode ueberlagert die equals-Methode und vergleicht diese Ebene mit dem uebergebenen Objekt.
     *
     * @param obj
     * @return boolean
     */
    @Override
    public boolean equals(Object obj) {

        if (obj == null) {
            return false;
        }

        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Ebene ebene)) {
            return false;
        }
        return this.name.equals(ebene.getName()) && this.story.equals(ebene.getStory()) && this.layout.equals(ebene.getLayout());
    }
}
