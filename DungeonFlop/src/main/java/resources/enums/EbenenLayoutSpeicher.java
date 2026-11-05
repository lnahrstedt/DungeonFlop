package resources.enums;

import model.RaeumeInEbene;
import model.raum.Raum;
import resources.interfaces.KonstantenEbenenLayoutSpeicher;
import resources.interfaces.StringsammlungEbenennamen;
import resources.interfaces.StringsammlungStoryText;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Definiert das Layout der verschiedenen Ebenen vor.
 * Die einzelnen Raeume werden jeweils als Key angelegt.
 * Der Wert des Keys beschreibt die verbundenen Raeume.
 */
public enum EbenenLayoutSpeicher {

    //Layout Ebene 0
    EBENE_0(StringsammlungEbenennamen.EBENE_0, StringsammlungStoryText.EBENE_0_STORY_TEXT, new HashMap<>() {{
        put(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_0, KonstantenEbenenLayoutSpeicher.RAUM_0), new ArrayList<>() {{
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_0, KonstantenEbenenLayoutSpeicher.RAUM_1));
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_0, KonstantenEbenenLayoutSpeicher.RAUM_2));
        }});
        put(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_0, KonstantenEbenenLayoutSpeicher.RAUM_1), new ArrayList<>() {{
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_0, KonstantenEbenenLayoutSpeicher.RAUM_0));
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_0, KonstantenEbenenLayoutSpeicher.RAUM_3));
        }});
        put(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_0, KonstantenEbenenLayoutSpeicher.RAUM_2), new ArrayList<>() {{
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_0, KonstantenEbenenLayoutSpeicher.RAUM_0));
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_0, KonstantenEbenenLayoutSpeicher.RAUM_5));
        }});
        put(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_0, KonstantenEbenenLayoutSpeicher.RAUM_3), new ArrayList<>() {{
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_0, KonstantenEbenenLayoutSpeicher.RAUM_1));
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_0, KonstantenEbenenLayoutSpeicher.RAUM_4));
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_0, KonstantenEbenenLayoutSpeicher.RAUM_8));
        }});
        put(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_0, KonstantenEbenenLayoutSpeicher.RAUM_4), new ArrayList<>() {{
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_0, KonstantenEbenenLayoutSpeicher.RAUM_3));
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_0, KonstantenEbenenLayoutSpeicher.RAUM_7));
        }});
        put(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_0, KonstantenEbenenLayoutSpeicher.RAUM_5), new ArrayList<>() {{
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_0, KonstantenEbenenLayoutSpeicher.RAUM_2));
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_0, KonstantenEbenenLayoutSpeicher.RAUM_6));
        }});
        put(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_0, KonstantenEbenenLayoutSpeicher.RAUM_6), new ArrayList<>() {{
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_0, KonstantenEbenenLayoutSpeicher.RAUM_5));
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_0, KonstantenEbenenLayoutSpeicher.RAUM_7));
        }});
        put(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_0, KonstantenEbenenLayoutSpeicher.RAUM_7), new ArrayList<>() {{
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_0, KonstantenEbenenLayoutSpeicher.RAUM_4));
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_0, KonstantenEbenenLayoutSpeicher.RAUM_6));
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_0, KonstantenEbenenLayoutSpeicher.RAUM_8));
        }});
        put(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_0, KonstantenEbenenLayoutSpeicher.RAUM_8), new ArrayList<>() {{
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_0, KonstantenEbenenLayoutSpeicher.RAUM_3));
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_0, KonstantenEbenenLayoutSpeicher.RAUM_7));
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_0, KonstantenEbenenLayoutSpeicher.RAUM_9));
        }});
        put(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_0, KonstantenEbenenLayoutSpeicher.RAUM_9), new ArrayList<>() {{
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_0, KonstantenEbenenLayoutSpeicher.RAUM_8));
        }});
    }}),

    //Layout Ebene 1
    EBENE_1(StringsammlungEbenennamen.EBENE_1, StringsammlungStoryText.EBENE_1_STORY_TEXT, new HashMap<>() {{
        put(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_1, KonstantenEbenenLayoutSpeicher.RAUM_0), new ArrayList<>() {{
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_1, KonstantenEbenenLayoutSpeicher.RAUM_1));
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_1, KonstantenEbenenLayoutSpeicher.RAUM_2));
        }});
        put(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_1, KonstantenEbenenLayoutSpeicher.RAUM_1), new ArrayList<>() {{
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_1, KonstantenEbenenLayoutSpeicher.RAUM_0));
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_1, KonstantenEbenenLayoutSpeicher.RAUM_3));
        }});
        put(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_1, KonstantenEbenenLayoutSpeicher.RAUM_2), new ArrayList<>() {{
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_1, KonstantenEbenenLayoutSpeicher.RAUM_0));
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_1, KonstantenEbenenLayoutSpeicher.RAUM_3));
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_1, KonstantenEbenenLayoutSpeicher.RAUM_4));
        }});
        put(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_1, KonstantenEbenenLayoutSpeicher.RAUM_3), new ArrayList<>() {{
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_1, KonstantenEbenenLayoutSpeicher.RAUM_1));
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_1, KonstantenEbenenLayoutSpeicher.RAUM_2));
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_1, KonstantenEbenenLayoutSpeicher.RAUM_6));
        }});
        put(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_1, KonstantenEbenenLayoutSpeicher.RAUM_4), new ArrayList<>() {{
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_1, KonstantenEbenenLayoutSpeicher.RAUM_2));
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_1, KonstantenEbenenLayoutSpeicher.RAUM_5));
        }});
        put(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_1, KonstantenEbenenLayoutSpeicher.RAUM_5), new ArrayList<>() {{
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_1, KonstantenEbenenLayoutSpeicher.RAUM_4));
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_1, KonstantenEbenenLayoutSpeicher.RAUM_7));
        }});
        put(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_1, KonstantenEbenenLayoutSpeicher.RAUM_6), new ArrayList<>() {{
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_1, KonstantenEbenenLayoutSpeicher.RAUM_3));
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_1, KonstantenEbenenLayoutSpeicher.RAUM_7));
        }});
        put(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_1, KonstantenEbenenLayoutSpeicher.RAUM_7), new ArrayList<>() {{
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_1, KonstantenEbenenLayoutSpeicher.RAUM_5));
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_1, KonstantenEbenenLayoutSpeicher.RAUM_6));
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_1, KonstantenEbenenLayoutSpeicher.RAUM_8));
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_1, KonstantenEbenenLayoutSpeicher.RAUM_9));
        }});
        put(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_1, KonstantenEbenenLayoutSpeicher.RAUM_8), new ArrayList<>() {{
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_1, KonstantenEbenenLayoutSpeicher.RAUM_7));
        }});
        put(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_1, KonstantenEbenenLayoutSpeicher.RAUM_9), new ArrayList<>() {{
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_1, KonstantenEbenenLayoutSpeicher.RAUM_7));
        }});
    }}),

    //Layout Ebene 2
    EBENE_2(StringsammlungEbenennamen.EBENE_2, StringsammlungStoryText.EBENE_2_STORY_TEXT, new HashMap<>() {{
        put(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_2, KonstantenEbenenLayoutSpeicher.RAUM_0), new ArrayList<>() {{
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_2, KonstantenEbenenLayoutSpeicher.RAUM_1));
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_2, KonstantenEbenenLayoutSpeicher.RAUM_4));
        }});
        put(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_2, KonstantenEbenenLayoutSpeicher.RAUM_1), new ArrayList<>() {{
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_2, KonstantenEbenenLayoutSpeicher.RAUM_0));
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_2, KonstantenEbenenLayoutSpeicher.RAUM_2));
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_2, KonstantenEbenenLayoutSpeicher.RAUM_3));
        }});
        put(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_2, KonstantenEbenenLayoutSpeicher.RAUM_2), new ArrayList<>() {{
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_2, KonstantenEbenenLayoutSpeicher.RAUM_1));
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_2, KonstantenEbenenLayoutSpeicher.RAUM_3));
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_2, KonstantenEbenenLayoutSpeicher.RAUM_4));
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_2, KonstantenEbenenLayoutSpeicher.RAUM_8));
        }});
        put(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_2, KonstantenEbenenLayoutSpeicher.RAUM_3), new ArrayList<>() {{
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_2, KonstantenEbenenLayoutSpeicher.RAUM_1));
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_2, KonstantenEbenenLayoutSpeicher.RAUM_2));
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_2, KonstantenEbenenLayoutSpeicher.RAUM_4));
        }});
        put(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_2, KonstantenEbenenLayoutSpeicher.RAUM_4), new ArrayList<>() {{
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_2, KonstantenEbenenLayoutSpeicher.RAUM_0));
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_2, KonstantenEbenenLayoutSpeicher.RAUM_2));
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_2, KonstantenEbenenLayoutSpeicher.RAUM_3));
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_2, KonstantenEbenenLayoutSpeicher.RAUM_5));
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_2, KonstantenEbenenLayoutSpeicher.RAUM_6));
        }});
        put(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_2, KonstantenEbenenLayoutSpeicher.RAUM_5), new ArrayList<>() {{
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_2, KonstantenEbenenLayoutSpeicher.RAUM_4));
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_2, KonstantenEbenenLayoutSpeicher.RAUM_6));
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_2, KonstantenEbenenLayoutSpeicher.RAUM_7));
        }});
        put(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_2, KonstantenEbenenLayoutSpeicher.RAUM_6), new ArrayList<>() {{
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_2, KonstantenEbenenLayoutSpeicher.RAUM_4));
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_2, KonstantenEbenenLayoutSpeicher.RAUM_5));
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_2, KonstantenEbenenLayoutSpeicher.RAUM_8));
        }});
        put(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_2, KonstantenEbenenLayoutSpeicher.RAUM_7), new ArrayList<>() {{
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_2, KonstantenEbenenLayoutSpeicher.RAUM_5));
        }});
        put(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_2, KonstantenEbenenLayoutSpeicher.RAUM_8), new ArrayList<>() {{
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_2, KonstantenEbenenLayoutSpeicher.RAUM_2));
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_2, KonstantenEbenenLayoutSpeicher.RAUM_6));
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_2, KonstantenEbenenLayoutSpeicher.RAUM_9));
        }});
        put(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_2, KonstantenEbenenLayoutSpeicher.RAUM_9), new ArrayList<>() {{
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_2, KonstantenEbenenLayoutSpeicher.RAUM_8));
        }});
    }}),

    //Layout Ebene 3
    EBENE_3(StringsammlungEbenennamen.EBENE_3, StringsammlungStoryText.EBENE_3_STORY_TEXT, new HashMap<>() {{
        put(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_3, KonstantenEbenenLayoutSpeicher.RAUM_0), new ArrayList<>() {{
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_3, KonstantenEbenenLayoutSpeicher.RAUM_1));
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_3, KonstantenEbenenLayoutSpeicher.RAUM_2));
        }});
        put(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_3, KonstantenEbenenLayoutSpeicher.RAUM_1), new ArrayList<>() {{
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_3, KonstantenEbenenLayoutSpeicher.RAUM_0));
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_3, KonstantenEbenenLayoutSpeicher.RAUM_2));
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_3, KonstantenEbenenLayoutSpeicher.RAUM_5));
        }});
        put(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_3, KonstantenEbenenLayoutSpeicher.RAUM_2), new ArrayList<>() {{
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_3, KonstantenEbenenLayoutSpeicher.RAUM_0));
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_3, KonstantenEbenenLayoutSpeicher.RAUM_1));
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_3, KonstantenEbenenLayoutSpeicher.RAUM_3));
        }});
        put(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_3, KonstantenEbenenLayoutSpeicher.RAUM_3), new ArrayList<>() {{
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_3, KonstantenEbenenLayoutSpeicher.RAUM_2));
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_3, KonstantenEbenenLayoutSpeicher.RAUM_4));
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_3, KonstantenEbenenLayoutSpeicher.RAUM_6));
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_3, KonstantenEbenenLayoutSpeicher.RAUM_8));
        }});
        put(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_3, KonstantenEbenenLayoutSpeicher.RAUM_4), new ArrayList<>() {{
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_3, KonstantenEbenenLayoutSpeicher.RAUM_3));
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_3, KonstantenEbenenLayoutSpeicher.RAUM_5));
        }});
        put(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_3, KonstantenEbenenLayoutSpeicher.RAUM_5), new ArrayList<>() {{
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_3, KonstantenEbenenLayoutSpeicher.RAUM_1));
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_3, KonstantenEbenenLayoutSpeicher.RAUM_4));
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_3, KonstantenEbenenLayoutSpeicher.RAUM_7));
        }});
        put(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_3, KonstantenEbenenLayoutSpeicher.RAUM_6), new ArrayList<>() {{
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_3, KonstantenEbenenLayoutSpeicher.RAUM_3));
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_3, KonstantenEbenenLayoutSpeicher.RAUM_7));
        }});
        put(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_3, KonstantenEbenenLayoutSpeicher.RAUM_7), new ArrayList<>() {{
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_3, KonstantenEbenenLayoutSpeicher.RAUM_5));
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_3, KonstantenEbenenLayoutSpeicher.RAUM_6));
        }});
        put(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_3, KonstantenEbenenLayoutSpeicher.RAUM_8), new ArrayList<>() {{
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_3, KonstantenEbenenLayoutSpeicher.RAUM_3));
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_3, KonstantenEbenenLayoutSpeicher.RAUM_9));
        }});
        put(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_3, KonstantenEbenenLayoutSpeicher.RAUM_9), new ArrayList<>() {{
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_3, KonstantenEbenenLayoutSpeicher.RAUM_8));
        }});
    }}),

    //Layout Ebene 4
    EBENE_4(StringsammlungEbenennamen.EBENE_4, StringsammlungStoryText.EBENE_4_STORY_TEXT, new HashMap<>() {{
        put(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_4, KonstantenEbenenLayoutSpeicher.RAUM_0), new ArrayList<>() {{
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_4, KonstantenEbenenLayoutSpeicher.RAUM_1));
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_4, KonstantenEbenenLayoutSpeicher.RAUM_2));
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_4, KonstantenEbenenLayoutSpeicher.RAUM_3));
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_4, KonstantenEbenenLayoutSpeicher.RAUM_4));
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_4, KonstantenEbenenLayoutSpeicher.RAUM_5));
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_4, KonstantenEbenenLayoutSpeicher.RAUM_6));
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_4, KonstantenEbenenLayoutSpeicher.RAUM_7));
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_4, KonstantenEbenenLayoutSpeicher.RAUM_8));

        }});
        put(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_4, KonstantenEbenenLayoutSpeicher.RAUM_1), new ArrayList<>() {{
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_4, KonstantenEbenenLayoutSpeicher.RAUM_0));
        }});
        put(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_4, KonstantenEbenenLayoutSpeicher.RAUM_2), new ArrayList<>() {{
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_4, KonstantenEbenenLayoutSpeicher.RAUM_0));
        }});
        put(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_4, KonstantenEbenenLayoutSpeicher.RAUM_3), new ArrayList<>() {{
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_4, KonstantenEbenenLayoutSpeicher.RAUM_0));
        }});
        put(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_4, KonstantenEbenenLayoutSpeicher.RAUM_4), new ArrayList<>() {{
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_4, KonstantenEbenenLayoutSpeicher.RAUM_0));
        }});
        put(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_4, KonstantenEbenenLayoutSpeicher.RAUM_5), new ArrayList<>() {{
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_4, KonstantenEbenenLayoutSpeicher.RAUM_0));
        }});
        put(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_4, KonstantenEbenenLayoutSpeicher.RAUM_6), new ArrayList<>() {{
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_4, KonstantenEbenenLayoutSpeicher.RAUM_0));
        }});
        put(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_4, KonstantenEbenenLayoutSpeicher.RAUM_7), new ArrayList<>() {{
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_4, KonstantenEbenenLayoutSpeicher.RAUM_0));
        }});
        put(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_4, KonstantenEbenenLayoutSpeicher.RAUM_8), new ArrayList<>() {{
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_4, KonstantenEbenenLayoutSpeicher.RAUM_0));
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_4, KonstantenEbenenLayoutSpeicher.RAUM_9));
        }});
        put(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_4, KonstantenEbenenLayoutSpeicher.RAUM_9), new ArrayList<>() {{
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_4, KonstantenEbenenLayoutSpeicher.RAUM_8));
        }});
    }}),

    //Layout Ebene 5
    EBENE_5(StringsammlungEbenennamen.EBENE_5, StringsammlungStoryText.EBENE_5_STORY_TEXT, new HashMap<>() {{
        put(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_5, KonstantenEbenenLayoutSpeicher.RAUM_0), new ArrayList<>() {{
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_5, KonstantenEbenenLayoutSpeicher.RAUM_1));
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_5, KonstantenEbenenLayoutSpeicher.RAUM_6));
        }});
        put(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_5, KonstantenEbenenLayoutSpeicher.RAUM_1), new ArrayList<>() {{
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_5, KonstantenEbenenLayoutSpeicher.RAUM_0));
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_5, KonstantenEbenenLayoutSpeicher.RAUM_2));
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_5, KonstantenEbenenLayoutSpeicher.RAUM_5));
        }});
        put(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_5, KonstantenEbenenLayoutSpeicher.RAUM_2), new ArrayList<>() {{
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_5, KonstantenEbenenLayoutSpeicher.RAUM_1));
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_5, KonstantenEbenenLayoutSpeicher.RAUM_3));
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_5, KonstantenEbenenLayoutSpeicher.RAUM_4));
        }});
        put(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_5, KonstantenEbenenLayoutSpeicher.RAUM_3), new ArrayList<>() {{
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_5, KonstantenEbenenLayoutSpeicher.RAUM_2));
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_5, KonstantenEbenenLayoutSpeicher.RAUM_8));
        }});
        put(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_5, KonstantenEbenenLayoutSpeicher.RAUM_4), new ArrayList<>() {{
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_5, KonstantenEbenenLayoutSpeicher.RAUM_2));
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_5, KonstantenEbenenLayoutSpeicher.RAUM_5));
        }});
        put(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_5, KonstantenEbenenLayoutSpeicher.RAUM_5), new ArrayList<>() {{
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_5, KonstantenEbenenLayoutSpeicher.RAUM_1));
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_5, KonstantenEbenenLayoutSpeicher.RAUM_4));
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_5, KonstantenEbenenLayoutSpeicher.RAUM_7));
        }});
        put(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_5, KonstantenEbenenLayoutSpeicher.RAUM_6), new ArrayList<>() {{
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_5, KonstantenEbenenLayoutSpeicher.RAUM_0));
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_5, KonstantenEbenenLayoutSpeicher.RAUM_8));
        }});
        put(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_5, KonstantenEbenenLayoutSpeicher.RAUM_7), new ArrayList<>() {{
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_5, KonstantenEbenenLayoutSpeicher.RAUM_5));
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_5, KonstantenEbenenLayoutSpeicher.RAUM_8));
        }});
        put(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_5, KonstantenEbenenLayoutSpeicher.RAUM_8), new ArrayList<>() {{
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_5, KonstantenEbenenLayoutSpeicher.RAUM_3));
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_5, KonstantenEbenenLayoutSpeicher.RAUM_6));
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_5, KonstantenEbenenLayoutSpeicher.RAUM_7));
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_5, KonstantenEbenenLayoutSpeicher.RAUM_9));
        }});
        put(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_5, KonstantenEbenenLayoutSpeicher.RAUM_9), new ArrayList<>() {{
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_5, KonstantenEbenenLayoutSpeicher.RAUM_8));
        }});
    }}),

    //Layout Ebene 6
    EBENE_6(StringsammlungEbenennamen.EBENE_6, StringsammlungStoryText.EBENE_6_STORY_TEXT, new HashMap<>() {{
        put(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_6, KonstantenEbenenLayoutSpeicher.RAUM_0), new ArrayList<>() {{
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_6, KonstantenEbenenLayoutSpeicher.RAUM_1));
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_6, KonstantenEbenenLayoutSpeicher.RAUM_5));
        }});
        put(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_6, KonstantenEbenenLayoutSpeicher.RAUM_1), new ArrayList<>() {{
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_6, KonstantenEbenenLayoutSpeicher.RAUM_0));
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_6, KonstantenEbenenLayoutSpeicher.RAUM_3));
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_6, KonstantenEbenenLayoutSpeicher.RAUM_4));
        }});
        put(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_6, KonstantenEbenenLayoutSpeicher.RAUM_2), new ArrayList<>() {{
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_6, KonstantenEbenenLayoutSpeicher.RAUM_6));
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_6, KonstantenEbenenLayoutSpeicher.RAUM_8));
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_6, KonstantenEbenenLayoutSpeicher.RAUM_9));
        }});
        put(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_6, KonstantenEbenenLayoutSpeicher.RAUM_3), new ArrayList<>() {{
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_6, KonstantenEbenenLayoutSpeicher.RAUM_1));
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_6, KonstantenEbenenLayoutSpeicher.RAUM_5));
        }});
        put(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_6, KonstantenEbenenLayoutSpeicher.RAUM_4), new ArrayList<>() {{
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_6, KonstantenEbenenLayoutSpeicher.RAUM_1));
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_6, KonstantenEbenenLayoutSpeicher.RAUM_7));
        }});
        put(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_6, KonstantenEbenenLayoutSpeicher.RAUM_5), new ArrayList<>() {{
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_6, KonstantenEbenenLayoutSpeicher.RAUM_0));
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_6, KonstantenEbenenLayoutSpeicher.RAUM_3));
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_6, KonstantenEbenenLayoutSpeicher.RAUM_6));
        }});
        put(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_6, KonstantenEbenenLayoutSpeicher.RAUM_6), new ArrayList<>() {{
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_6, KonstantenEbenenLayoutSpeicher.RAUM_2));
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_6, KonstantenEbenenLayoutSpeicher.RAUM_5));
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_6, KonstantenEbenenLayoutSpeicher.RAUM_10));
        }});
        put(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_6, KonstantenEbenenLayoutSpeicher.RAUM_7), new ArrayList<>() {{
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_6, KonstantenEbenenLayoutSpeicher.RAUM_4));
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_6, KonstantenEbenenLayoutSpeicher.RAUM_8));
        }});
        put(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_6, KonstantenEbenenLayoutSpeicher.RAUM_8), new ArrayList<>() {{
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_6, KonstantenEbenenLayoutSpeicher.RAUM_2));
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_6, KonstantenEbenenLayoutSpeicher.RAUM_7));
        }});
        put(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_6, KonstantenEbenenLayoutSpeicher.RAUM_9), new ArrayList<>() {{
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_6, KonstantenEbenenLayoutSpeicher.RAUM_2));
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_6, KonstantenEbenenLayoutSpeicher.RAUM_10));
        }});
        put(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_6, KonstantenEbenenLayoutSpeicher.RAUM_10), new ArrayList<>() {{
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_6, KonstantenEbenenLayoutSpeicher.RAUM_6));
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_6, KonstantenEbenenLayoutSpeicher.RAUM_9));
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_6, KonstantenEbenenLayoutSpeicher.RAUM_11));
        }});
        put(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_6, KonstantenEbenenLayoutSpeicher.RAUM_11), new ArrayList<>() {{
            add(RaeumeInEbene.getRaum(KonstantenEbenenLayoutSpeicher.EBENE_6, KonstantenEbenenLayoutSpeicher.RAUM_10));
        }});
    }});


    private final String name;
    private final String story;
    private final HashMap<Raum, ArrayList<Raum>> layout;

    EbenenLayoutSpeicher(String name, String story, HashMap<Raum, ArrayList<Raum>> layout) {
        this.name = name;
        this.story = story;
        this.layout = layout;

    }

    public String getName() {
        return name;
    }

    public String getStory() {
        return story;
    }

    public HashMap<Raum, ArrayList<Raum>> getLayout() {
        HashMap<Raum, ArrayList<Raum>> ausgabe = new HashMap<>();
        for (Raum key : this.layout.keySet()) {
            ausgabe.put(key, new ArrayList<>(this.layout.get(key)));
        }
        return ausgabe;
    }
}
