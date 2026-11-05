package model.netzwerk;

import resources.interfaces.ZahlensammlungChat;

import java.util.HashMap;
import java.util.TreeMap;

/**
 * Diese Klasse repraesentiert den Chat innerhalb des Spiels.
 */
public class Chat {

    private static final Chat chat = new Chat();
    private long nachrichtennummer;

    private TreeMap<Long, HashMap<String, Boolean>> nachrichten = new TreeMap<>();
    private String zwischenspeicher;

    public static Chat getInstance() {
        return chat;
    }

    /**
     * Fuegt der Map die uebergebene Nachricht hinzu.
     *
     * @param nachricht
     * @param rechts
     */
    public void fuegeNachrichtHinzu(String nachricht, boolean rechts) {
        nachrichten.put(nachrichtennummer++, new HashMap<>() {{
            put(nachricht, rechts);
        }});
    }

    /**
     * Setzt sowohl die Map mit den Chatnachrichten als auch die Iterationsvariable zurueck.
     */
    public void zuruecksetzen() {
        nachrichtennummer = ZahlensammlungChat.STARTNUMMER_CHATNACHRICHT;
        nachrichten.clear();
    }

    public TreeMap<Long, HashMap<String, Boolean>> getNachrichten() {
        return nachrichten;
    }

    public String getZwischenspeicher() {
        return zwischenspeicher;
    }

    public void setZwischenspeicher(String zwischenspeicher) {
        this.zwischenspeicher = zwischenspeicher;
    }
}
