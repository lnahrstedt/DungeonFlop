package model.netzwerk;

/**
 * Diese Klasse enthaelt alle fuer das Netzwerk notwendigen Flags.
 */
public class NetzwerkFlagContainer {

    /**
     * Speichert, ob aktuelle Instanz des Spiels der Client ist.
     */
    private static boolean binIchClient = false;

    /**
     * Speichert, ob aktuelle Instanz an der Reihe ist.
     */
    private static boolean binIchAnDerReihe = false;

    /**
     * Speichert, ob online gespielt wird.
     */
    private static boolean wirdOnlineGespielt = false;

    /**
     * Speichert, ob Chat geoeffnet ist.
     */
    private static boolean istChatGeoeffnet = false;

    private static boolean ungeleseneNachricht = false;

    public static boolean gibtEsEineUngeleseneNachricht() {
        return ungeleseneNachricht;
    }

    public static void setUngeleseneNachricht(boolean ungeleseneNachricht) {
        NetzwerkFlagContainer.ungeleseneNachricht = ungeleseneNachricht;
    }

    public static boolean istChatGeoeffnet() {
        return istChatGeoeffnet;
    }

    public static void setIstChatGeoeffnet(boolean istChatGeoeffnet) {
        NetzwerkFlagContainer.istChatGeoeffnet = istChatGeoeffnet;
    }

    public static boolean binIchClient() {
        return binIchClient;
    }

    public static void setBinIchClient(boolean binIchClient) {
        NetzwerkFlagContainer.binIchClient = binIchClient;
    }

    public static boolean binIchAnDerReihe() {
        return binIchAnDerReihe;
    }

    public static void setBinIchAnDerReihe(boolean binIchAnDerReihe) {
        NetzwerkFlagContainer.binIchAnDerReihe = binIchAnDerReihe;
    }

    public static boolean wirdOnlineGespielt() {
        return wirdOnlineGespielt;
    }

    public static void setWirdOnlineGespielt(boolean wirdOnlineGespielt) {
        NetzwerkFlagContainer.wirdOnlineGespielt = wirdOnlineGespielt;
    }
}
