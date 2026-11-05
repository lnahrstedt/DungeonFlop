package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Ein Inventar, bestehend aus einer Liste von Waffen und einer Liste Boni.
 */
public class Inventar implements Serializable {
    private ArrayList<Waffe> waffeninventar;
    private ArrayList<Boni> artefaktinventar;

    public Inventar(ArrayList<Waffe> waffeninventar, ArrayList<Boni> artefaktinventar) {
        this.waffeninventar = waffeninventar;
        this.artefaktinventar = artefaktinventar;
    }

    public ArrayList<Waffe> getWaffeninventar() {
        return waffeninventar;
    }

    public void setWaffeninventar(ArrayList<Waffe> waffeninventar) {
        this.waffeninventar = waffeninventar;
    }

    public ArrayList<Boni> getArtefaktinventar() {
        return artefaktinventar;
    }

    public void setArtefaktinventar(ArrayList<Boni> artefaktinventar) {
        this.artefaktinventar = artefaktinventar;
    }

}
