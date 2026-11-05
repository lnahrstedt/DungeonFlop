package utilities;

public class CheatMode {
    private static final CheatMode cheatMode = new CheatMode();
    private boolean cheat = false;
    private boolean zuvorGedrueckt = false;
    private boolean istGedrueckt = false;

    public static CheatMode getInstance() {
        return cheatMode;
    }

    public void istDeaktiviert(boolean cheat) {
        this.cheat = cheat;
    }

    public boolean istAktiv() {
        return cheat;
    }

    public void setZuvorGedrueckt(boolean zuvorGedrueckt) {
        this.zuvorGedrueckt = zuvorGedrueckt;
    }

    public boolean getZuvorGedrueckt() {
        return zuvorGedrueckt;
    }

    public void setIstGedrueckt(boolean istGedrueckt) {
        this.istGedrueckt = istGedrueckt;
    }

    public boolean getIstGedrueckt() {
        return istGedrueckt;
    }

}
