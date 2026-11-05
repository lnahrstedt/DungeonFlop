package resources.enums;

import model.effekt.Effekt;
import model.effekt.MehrfachSchlagenEffekt;
import model.effekt.ReichweiteEffekt;
import model.effekt.WeiterGehenEffekt;
import resources.interfaces.StringsammlungGegner;
import resources.interfaces.StringsammlungSpielfigur;
import resources.interfaces.ZahlensammlungSpielfigur;

import java.util.ArrayList;

/**
 * Stellt fuer die verschiedenen Spielerklassen und Gegner Spielfiguren zur Verfuegung.
 */
public enum SpielfigurSammlung {

    SANDKRIEGER(StringsammlungSpielfigur.SANDKRIEGER_NAME, ZahlensammlungSpielfigur.SANDKRIEGER_LEBENSPUNKTE,
            ZahlensammlungSpielfigur.SANDKRIEGER_SCHADENSPUNKTE,
            new ArrayList<>()),
    SONNENPRIESTER(StringsammlungSpielfigur.SONNENPRIESTER_NAME, ZahlensammlungSpielfigur.SONNENPRIESTER_LEBENSPUNKTE,
            ZahlensammlungSpielfigur.SONNENPRIESTER_SCHADENSPUNKTE,
            new ArrayList<>()),
    WASSERTAENZER(StringsammlungSpielfigur.WASSERTAENZER_NAME, ZahlensammlungSpielfigur.WASSERTAENZER_LEBENSPUNKTE,
            ZahlensammlungSpielfigur.WASSERTAENZER_SCHADENSPUNKTE,
            new ArrayList<>() {{
                add(new WeiterGehenEffekt(ZahlensammlungSpielfigur.WASSERTAENZER_EFFEKT_WERT));
            }}),
    BOGENSCHUETZE(StringsammlungSpielfigur.BOGENSCHUETZE_NAME, ZahlensammlungSpielfigur.BOGENSCHUETZE_LEBENSPUNKTE,
            ZahlensammlungSpielfigur.BOGENSCHUETZE_SCHADENSPUNKTE,
            new ArrayList<>() {{
                add(new ReichweiteEffekt(ZahlensammlungSpielfigur.BOGENSCHUETZE_EFFEKT_WERT));
            }}),
    NAHKAEMPFER(StringsammlungSpielfigur.NAHKAEMPFER_NAME, ZahlensammlungSpielfigur.NAHKAEMPFER_LEBENSPUNKTE,
            ZahlensammlungSpielfigur.NAHKAEMPFER_SCHADENSPUNKTE,
            new ArrayList<>() {{
                add(new MehrfachSchlagenEffekt(ZahlensammlungSpielfigur.NAHKAEMPFER_EFFEKT_WERT));
            }}),
    MAGIER(StringsammlungSpielfigur.MAGIER_NAME, ZahlensammlungSpielfigur.MAGIER_LEBENSPUNKTE,
            ZahlensammlungSpielfigur.MAGIER_SCHADENSPUNKTE,
            new ArrayList<>() {{
                add(new WeiterGehenEffekt(ZahlensammlungSpielfigur.MAGIER_EFFEKT_WERT));
            }}),
    SISYPHOS(StringsammlungGegner.SISYPHOS_NAME, ZahlensammlungSpielfigur.SISYPHOS_LEBENSPUNKTE,
            ZahlensammlungSpielfigur.SISYPHOS_SCHADENSPUNKTE,
            new ArrayList<>() {{
                add(new MehrfachSchlagenEffekt(ZahlensammlungSpielfigur.SISYPHOS_EFFEKT_WERT));
            }}),

    TANTALOS(StringsammlungGegner.TANTALOS_NAME, ZahlensammlungSpielfigur.TANTALOS_LEBENSPUNKTE,
            ZahlensammlungSpielfigur.TANTALOS_SCHADENSPUNKTE,
            new ArrayList<>() {{
                add(new WeiterGehenEffekt(ZahlensammlungSpielfigur.TANTALOS_WEITERGEHENEFFEKT_WERT));
                add(new ReichweiteEffekt(ZahlensammlungSpielfigur.TANTALOS_REICHWEITEEFFEKT_WERT));
            }}),

    ORPHEUS(StringsammlungGegner.ORPHEUS_NAME, ZahlensammlungSpielfigur.ORPHEUS_LEBENSPUNKTE,
            ZahlensammlungSpielfigur.ORPHEUS_SCHADENSPUNKTE,
            new ArrayList<>() {{
                add(new ReichweiteEffekt(ZahlensammlungSpielfigur.ORPHEUS_EFFEKT_WERT));
            }});


    private final String name;
    private final int lebenspunkte;
    private final int angriffspunkte;
    private final ArrayList<Effekt> effekte;

    SpielfigurSammlung(String name, int lebenspunkte, int angriffspunkte, ArrayList<Effekt> effekte) {
        this.name = name;
        this.lebenspunkte = lebenspunkte;
        this.angriffspunkte = angriffspunkte;
        this.effekte = effekte;
    }

    public int getLebenspunkte() {
        return lebenspunkte;
    }

    public int getAngriffspunkte() {
        return angriffspunkte;
    }

    public ArrayList<Effekt> getEffekte() {
        return new ArrayList<>(effekte);
    }

    public String getName() {
        return name;
    }
}
