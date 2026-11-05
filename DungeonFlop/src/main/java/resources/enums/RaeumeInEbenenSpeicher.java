package resources.enums;

import control.model.fabrik.RaumFabrik;
import model.raum.Raum;
import resources.interfaces.StringsammlungRaum;
import resources.interfaces.Zahlensammlung;

import java.util.ArrayList;

/**
 * Diese Enumeration definiert die Raeume innerhalb der Ebenen vor.
 */
public enum RaeumeInEbenenSpeicher {

    RAEUME_IN_EBENE_0(new ArrayList<>() {{
        add(RaumFabrik.generiereRaum(StringsammlungRaum.RAUM_0_EBENE_0_NAME, StringsammlungRaum.RAUM_0_EBENE_0_BESCHREIBUNG));
        add(RaumFabrik.generiereGegnerraum(Zahlensammlung.SCHWIERIGKEITSGRAD_EBENE_0));
        add(RaumFabrik.generiereGegnerraum(Zahlensammlung.SCHWIERIGKEITSGRAD_EBENE_0));
        add(RaumFabrik.generiereHaendlerraum());
        add(RaumFabrik.generiereGegnerraum(Zahlensammlung.SCHWIERIGKEITSGRAD_EBENE_0));
        add(RaumFabrik.generiereSchmiedRaum(Zahlensammlung.SCHMIEDRAUM_EBENE_0_PREIS));
        add(RaumFabrik.generiereGegnerraum(Zahlensammlung.SCHWIERIGKEITSGRAD_EBENE_0));
        add(RaumFabrik.generiereGegnerraum(Zahlensammlung.SCHWIERIGKEITSGRAD_EBENE_0));
        add(RaumFabrik.generiereBossraum(Zahlensammlung.SCHWIERIGKEITSGRAD_EBENE_0));
        add(RaumFabrik.generiereRaum(StringsammlungRaum.LEITERRAUM_NAME, StringsammlungRaum.LEITERRAUM_BESCHREIBUNG));

    }}),

    RAEUME_IN_EBENE_1(new ArrayList<>() {{
        add(RaumFabrik.generiereRaum(StringsammlungRaum.RAUM_0_EBENE_1_NAME, StringsammlungRaum.RAUM_0_EBENE_1_BESCHREIBUNG));
        add(RaumFabrik.generiereGegnerraum(Zahlensammlung.SCHWIERIGKEITSGRAD_EBENE_1));
        add(RaumFabrik.generiereGegnerraum(Zahlensammlung.SCHWIERIGKEITSGRAD_EBENE_1));
        add(RaumFabrik.generiereSchmiedRaum(Zahlensammlung.SCHMIEDRAUM_EBENE_1_PREIS));
        add(RaumFabrik.generiereHaendlerraum());
        add(RaumFabrik.generiereGegnerraum(Zahlensammlung.SCHWIERIGKEITSGRAD_EBENE_1));
        add(RaumFabrik.generiereGegnerraum(Zahlensammlung.SCHWIERIGKEITSGRAD_EBENE_1));
        add(RaumFabrik.generiereBossraum(Zahlensammlung.SCHWIERIGKEITSGRAD_EBENE_1));
        add(RaumFabrik.generiereSchatzraum());
        add(RaumFabrik.generiereRaum(StringsammlungRaum.LEITERRAUM_NAME, StringsammlungRaum.LEITERRAUM_BESCHREIBUNG));
    }}),

    RAEUME_IN_EBENE_2(new ArrayList<>() {{
        add(RaumFabrik.generiereRaum(StringsammlungRaum.RAUM_0_EBENE_2_NAME, StringsammlungRaum.RAUM_0_EBENE_2_BESCHREIBUNG));
        add(RaumFabrik.generiereGegnerraum(Zahlensammlung.SCHWIERIGKEITSGRAD_EBENE_2));
        add(RaumFabrik.generiereHaendlerraum());
        add(RaumFabrik.generiereSchmiedRaum(Zahlensammlung.SCHMIEDRAUM_EBENE_2_PREIS));
        add(RaumFabrik.generiereGegnerraum(Zahlensammlung.SCHWIERIGKEITSGRAD_EBENE_2));
        add(RaumFabrik.generiereGegnerraum(Zahlensammlung.SCHWIERIGKEITSGRAD_EBENE_2));
        add(RaumFabrik.generiereGegnerraum(Zahlensammlung.SCHWIERIGKEITSGRAD_EBENE_2));
        add(RaumFabrik.generiereSchatzraum());
        add(RaumFabrik.generiereBossraum(Zahlensammlung.SCHWIERIGKEITSGRAD_EBENE_2));
        add(RaumFabrik.generiereRaum(StringsammlungRaum.LEITERRAUM_NAME, StringsammlungRaum.LEITERRAUM_BESCHREIBUNG));
    }}),

    RAEUME_IN_EBENE_3(new ArrayList<>() {{
        add(RaumFabrik.generiereRaum(StringsammlungRaum.RAUM_0_EBENE_3_NAME, StringsammlungRaum.RAUM_0_EBENE_3_BESCHREIBUNG));
        add(RaumFabrik.generiereGegnerraum(Zahlensammlung.SCHWIERIGKEITSGRAD_EBENE_3));
        add(RaumFabrik.generiereSchmiedRaum(Zahlensammlung.SCHMIEDRAUM_EBENE_3_PREIS));
        add(RaumFabrik.generiereGegnerraum(Zahlensammlung.SCHWIERIGKEITSGRAD_EBENE_3));
        add(RaumFabrik.generiereSchatzraum());
        add(RaumFabrik.generiereGegnerraum(Zahlensammlung.SCHWIERIGKEITSGRAD_EBENE_3));
        add(RaumFabrik.generiereHaendlerraum());
        add(RaumFabrik.generiereGegnerraum(Zahlensammlung.SCHWIERIGKEITSGRAD_EBENE_3));
        add(RaumFabrik.generiereBossraum(Zahlensammlung.SCHWIERIGKEITSGRAD_EBENE_3));
        add(RaumFabrik.generiereRaum(StringsammlungRaum.LEITERRAUM_NAME, StringsammlungRaum.LEITERRAUM_BESCHREIBUNG));
    }}),

    RAEUME_IN_EBENE_4(new ArrayList<>() {{
        add(RaumFabrik.generiereRaum(StringsammlungRaum.RAUM_0_EBENE_4_NAME, StringsammlungRaum.RAUM_0_EBENE_4_BESCHREIBUNG));
        add(RaumFabrik.generiereGegnerraum(Zahlensammlung.SCHWIERIGKEITSGRAD_EBENE_4));
        add(RaumFabrik.generiereGegnerraum(Zahlensammlung.SCHWIERIGKEITSGRAD_EBENE_4));
        add(RaumFabrik.generiereSchmiedRaum(Zahlensammlung.SCHMIEDRAUM_EBENE_4_PREIS));
        add(RaumFabrik.generiereGegnerraum(Zahlensammlung.SCHWIERIGKEITSGRAD_EBENE_4));
        add(RaumFabrik.generiereHaendlerraum());
        add(RaumFabrik.generiereGegnerraum(Zahlensammlung.SCHWIERIGKEITSGRAD_EBENE_4));
        add(RaumFabrik.generiereSchatzraum());
        add(RaumFabrik.generiereBossraum(Zahlensammlung.SCHWIERIGKEITSGRAD_EBENE_4));
        add(RaumFabrik.generiereRaum(StringsammlungRaum.LEITERRAUM_NAME, StringsammlungRaum.LEITERRAUM_BESCHREIBUNG));
    }}),

    RAEUME_IN_EBENE_5(new ArrayList<>() {{
        add(RaumFabrik.generiereRaum(StringsammlungRaum.RAUM_0_EBENE_5_NAME, StringsammlungRaum.RAUM_0_EBENE_5_BESCHREIBUNG));
        add(RaumFabrik.generiereHaendlerraum());
        add(RaumFabrik.generiereGegnerraum(Zahlensammlung.SCHWIERIGKEITSGRAD_EBENE_5));
        add(RaumFabrik.generiereGegnerraum(Zahlensammlung.SCHWIERIGKEITSGRAD_EBENE_5));
        add(RaumFabrik.generiereSchatzraum());
        add(RaumFabrik.generiereGegnerraum(Zahlensammlung.SCHWIERIGKEITSGRAD_EBENE_5));
        add(RaumFabrik.generiereGegnerraum(Zahlensammlung.SCHWIERIGKEITSGRAD_EBENE_5));
        add(RaumFabrik.generiereSchmiedRaum(Zahlensammlung.SCHMIEDRAUM_EBENE_5_PREIS));
        add(RaumFabrik.generiereBossraum(Zahlensammlung.SCHWIERIGKEITSGRAD_EBENE_5));
        add(RaumFabrik.generiereRaum(StringsammlungRaum.LEITERRAUM_NAME, StringsammlungRaum.LEITERRAUM_BESCHREIBUNG));
    }}),

    RAEUME_IN_EBENE_6(new ArrayList<>() {{
        add(RaumFabrik.generiereRaum(StringsammlungRaum.RAUM_0_EBENE_6_NAME, StringsammlungRaum.RAUM_0_EBENE_6_BESCHREIBUNG));
        add(RaumFabrik.generiereGegnerraum(Zahlensammlung.SCHWIERIGKEITSGRAD_EBENE_6));
        add(RaumFabrik.generiereGegnerraum(Zahlensammlung.SCHWIERIGKEITSGRAD_EBENE_6));
        add(RaumFabrik.generiereSchatzraum());
        add(RaumFabrik.generiereGegnerraum(Zahlensammlung.SCHWIERIGKEITSGRAD_EBENE_6));
        add(RaumFabrik.generiereHaendlerraum());
        add(RaumFabrik.generiereGegnerraum(Zahlensammlung.SCHWIERIGKEITSGRAD_EBENE_6));
        add(RaumFabrik.generiereSchatzraum());
        add(RaumFabrik.generiereGegnerraum(Zahlensammlung.SCHWIERIGKEITSGRAD_EBENE_6));
        add(RaumFabrik.generiereSchmiedRaum(Zahlensammlung.SCHMIEDRAUM_EBENE_6_PREIS));
        add(RaumFabrik.generiereBossraum(Zahlensammlung.SCHWIERIGKEITSGRAD_EBENE_6));
        add(RaumFabrik.generiereRaum(StringsammlungRaum.LEITERRAUM_NAME, StringsammlungRaum.LEITERRAUM_BESCHREIBUNG));
    }});


    private final ArrayList<Raum> raeumeInEbene;

    RaeumeInEbenenSpeicher(ArrayList<Raum> raeumeInEbene) {
        this.raeumeInEbene = raeumeInEbene;
        RaumFabrik.resetRaumnummer();
    }

    public ArrayList<Raum> getRaeumeInEbene() {
        return raeumeInEbene;
    }

}