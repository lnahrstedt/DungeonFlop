package model;

import model.effekt.Effekt;
import resources.enums.BoniSammlung;

import java.io.Serializable;

/**
 * Ein Boni beinhaltet einen Effekt mit einem Seltenheitsgrad und Level.
 */
public class Boni implements Serializable {

    private final String name;
    private Effekt effekt;
    private int seltenheit;
    private int level;

    /**
     * Erstellt einen Boni.
     *
     * @param effekt
     * @param seltenheit
     * @param level
     */
    public Boni(Effekt effekt, int seltenheit, int level, String name) {
        this.effekt = effekt;
        this.seltenheit = seltenheit;
        this.level = level;
        this.name = name;
    }

    /**
     * Erstellt einen Boni anhand einer Vorlage.
     *
     * @param boniVorlage
     */
    public Boni(BoniSammlung boniVorlage) {
        this.effekt = boniVorlage.getEffekt();
        this.level = boniVorlage.getLevel();
        this.seltenheit = boniVorlage.getSeltenheit();
        this.name = boniVorlage.getName();
    }

    public Effekt getEffekt() {
        return effekt;
    }

    public void setEffekt(Effekt effekt) {
        this.effekt = effekt;
    }

    public int getSeltenheit() {
        return seltenheit;
    }

    public void setSeltenheit(int seltenheit) {
        this.seltenheit = seltenheit;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

}