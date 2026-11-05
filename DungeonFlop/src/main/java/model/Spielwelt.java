package model;

import control.model.fabrik.EbenenFabrik;
import resources.enums.Schwierigkeitsgrad;
import resources.interfaces.StringsammlungStoryText;
import resources.interfaces.Zahlensammlung;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Die Spielwelt beinhaltet alle Ebenen, den Protagonisten, die aktuelle Ebenenposition des Spielers, den Schwierigkeitsgrad und die Story.
 * Anhand des Schwierigkeitsgrades wird vieles variabel festgelegt.
 * <p>
 * Da von dieser Klasse nur eine Instanz erstellt werden soll, wird auf das Singleton-Pattern zurueckgegriffen.
 */
public class Spielwelt implements Serializable {

    private static final Spielwelt spielwelt = new Spielwelt();

    private int todesGold;
    private String story;
    private ArrayList<Ebene> ebenen;
    private Schwierigkeitsgrad schwierigkeitsgrad;
    private HauptSpieler protagonist;
    private Ebene spielerEbenePosition;
    private float goldFaktor = Zahlensammlung.GOLD_STARTGEWINN;
    private float erfahrungsFaktor = Zahlensammlung.ERFAHRUNGS_STARTGEWINN;

    private Spielwelt() {
        this.story = StringsammlungStoryText.SPIELWELT_STORY_TEXT;
        this.ebenen = EbenenFabrik.liesEbenenEin();
        this.spielerEbenePosition = this.ebenen.get(Zahlensammlung.ERSTE_EBENE);
    }

    public static Spielwelt getInstance() {
        return spielwelt;
    }

    /**
     * Ueberschreibt die equals Methode, um Ebenen untereinander vergleichen zu koennen.
     *
     * @param obj Das Objekt welches verglichen werden soll
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

        if (!(obj instanceof Spielwelt spielwelt)) {
            return false;
        }

        return this.story.equals(spielwelt.getStory())
                && this.ebenen.equals(spielwelt.getEbenen())
                && this.schwierigkeitsgrad == spielwelt.getSchwierigkeitsgrad();

    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public ArrayList<Ebene> getEbenen() {
        return ebenen;
    }

    public void setEbenen(ArrayList<Ebene> ebenen) {
        this.ebenen = ebenen;
    }

    public Schwierigkeitsgrad getSchwierigkeitsgrad() {
        return schwierigkeitsgrad;
    }

    public void setSchwierigkeitsgrad(Schwierigkeitsgrad schwierigkeitsgrad) {
        this.schwierigkeitsgrad = schwierigkeitsgrad;
    }

    public HauptSpieler getProtagonist() {
        return protagonist;
    }

    public void setProtagonist(HauptSpieler protagonist) {
        this.protagonist = protagonist;
    }

    public Ebene getSpielerEbenePosition() {
        return spielerEbenePosition;
    }

    public void setSpielerEbenePosition(Ebene spielerEbenePosition) {
        this.spielerEbenePosition = spielerEbenePosition;
    }

    public float getGoldFaktor() {
        return goldFaktor;
    }

    public void setGoldFaktor(float goldFaktor) {
        this.goldFaktor = goldFaktor;
    }

    public float getErfahrungsFaktor() {
        return erfahrungsFaktor;
    }

    public void setErfahrungsFaktor(float erfahrungsFaktor) {
        this.erfahrungsFaktor = erfahrungsFaktor;
    }

    public int getTodesGold() {
        return todesGold;
    }

    public void setTodesGold(int todesGold) {
        this.todesGold = todesGold;
    }
}
