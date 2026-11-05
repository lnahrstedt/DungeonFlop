package resources.enums;

import model.talent.*;
import resources.interfaces.StringsammlungTalent;
import utilities.MeinIO;

import java.io.Serializable;

public enum TalentSammlung implements Serializable {

    GOLD(new GoldTalent(StringsammlungTalent.GOLD_NAME, StringsammlungTalent.GOLD_BESCHREIBUNG)),
    ERFAHRUNG(new ErfahrungsTalent(StringsammlungTalent.ERFAHRUNG_NAME, StringsammlungTalent.ERFAHRUNG_BESCHREIBUNG)),
    HELD_LEBEN(new HeldLebenTalent(StringsammlungTalent.HELD_LEBEN_NAME, StringsammlungTalent.HELD_LEBEN_BESCHREIBUNG)),
    HELD_ANGRIFF(new HeldAngriffTalent(StringsammlungTalent.HELD_ANGRIFF_NAME, StringsammlungTalent.HELD_ANGRIFF_BESCHREIBUNG)),
    ZUFAELLIGE_AUFWERTUNG(new ZufaelligeVerbesserungTalent(StringsammlungTalent.KARTEN_AUFWERTUNG_NAME, StringsammlungTalent.KARTEN_AUFWERTUNG_BESCHREIBUNG));

    private final Talent talent;

    TalentSammlung(Talent talent) {
        this.talent = talent;
    }

    public static Talent getRandomTalent() {
        return TalentSammlung.values()[MeinIO.getRandomInt(TalentSammlung.values().length)].talent;
    }

    /*
    TALENTE (new HashMap<ArrayList<String>, Runnable>() {{
        put (new ArrayList<>() {{
            add (StringSammlungTalent.GOLD_NAME);
            add (StringSammlungTalent.GOLD_BESCHREIBUNG);
        }},
                () -> TalentController.goldEffektAusfuehren());

        put (new ArrayList<>() {{
            add (StringSammlungTalent.ERFAHRUNG_NAME);
            add (StringSammlungTalent.ERFAHRUNG_BESCHREIBUNG);
        }},
                () -> TalentController.erfahrungsEffektAusfuehren());

        put (new ArrayList<>() {{
            add (StringSammlungTalent.HELD_ANGRIFF_NAME);
            add (StringSammlungTalent.HELD_ANGRIFF_BESCHREIBUNG);
        }},
                () -> TalentController.heldAngriffVerbessern());

        put (new ArrayList<>() {{
            add (StringSammlungTalent.HELD_LEBEN_NAME);
            add (StringSammlungTalent.HELD_LEBEN_BESCHREIBUNG);
        }},
                () -> TalentController.heldLebenVerbessern());

        put (new ArrayList<>() {{
            add (StringSammlungTalent.KARTEN_AUFWERTUNG_NAME);
            add (StringSammlungTalent.KARTEN_AUFWERTUNG_BESCHREIBUNG);
        }},
                () -> TalentController.zufaelligeKartenVerbessern());
    }});

    private HashMap<ArrayList<String>, Runnable> methodenAssoziation;

    TalentSammlung(HashMap <ArrayList<String>, Runnable> methodenAssoziation){
        this.methodenAssoziation = methodenAssoziation;
    }

    public static String getRandomTalent () {

    }

    public static void talentAusfuehren (String name) {
        HashMap<ArrayList<String>, Runnable> methodenAssoziation = TalentSammlung.values()[0].methodenAssoziation;
        ArrayList[] alleKeys = methodenAssoziation.keySet().toArray(new ArrayList[0]);


        for (int i = 0; i < alleKeys.length; i++) {
            if( alleKeys[i].get(0) == name) {
                methodenAssoziation.get(alleKeys[i]).run();
            }
        }
    }

     */
}
