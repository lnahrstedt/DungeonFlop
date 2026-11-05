package resources.interfaces;

import java.nio.file.Paths;

/**
 * In diesem Interface werden alle benoetigten Dateipfade gesammelt.
 */
public interface PfadSammlung {

    String PFAD_SOUNDS = "\\src\\main\\java\\resources\\sounds\\";
    String PFAD_GESPEICHERTE_SPIELE = Paths.get("").toAbsolutePath() + "\\src\\main\\resources\\";
    String PFAD_KEY = Paths.get("").toAbsolutePath() + "\\src\\main\\resources\\key.json";
    String DATEINAME = "DungeonTopRemake_";
    String DATEIENDUNG = ".dat";
    String DATUMSFORMAT = "dd_MM_yyyy-HH_mm_ss";
    String PFAD_ICON = "/images/icon.png";
    String PFAD_LOGO = "/images/logo.png";
    String PFAD_SPIELER = "/images/player.png";
    String PFAD_PFEIL_HERUNTER = "/images/pfeilHerunter.png";

    String PFAD_PFEIL_LINKS = "/images/linkerpfeil.png";
    String PFAD_PFEIL_RECHTS = "/images/rechterpfeil.png";
    String PFAD_BACKGROUND = "/images/desert.png";

    String PFAD_GOLD = "/images/gold.png";
    String PFAD_SOL = "/images/sol.png";
    String PFAD_KARTEN = "/images/karten.png";
    String PFAD_ERFAHRUNGSPUNKTE = "/images/ep.png";
    String PFAD_HINTERGRUNDBILD = "/images/hintergrundbild.jpg";

    String PFAD_LEBENSPUNKTE = "/images/lebenspunkte.png";
    String PFAD_ANGRIFFSICON = "/images/angreifen.png";
    String PFAD_SCHADENSPUNKTE = "/images/schadenspunkte.png";
    String PFAD_SCHMIED = "/images/schmied.png";
    String BILDER_PFAD_GENERELL = "/images/%s.png";
    String PFAD_HINTERGRUNDMUSIK = Paths.get("").toAbsolutePath() + PFAD_SOUNDS + "cinematic-atmosphere-score.mp3";
    String PFAD_KAMPFMUSIK = Paths.get("").toAbsolutePath() + PFAD_SOUNDS + "honor-and-sword-main.mp3";

    String PFAD_MUENZEN_SOUNDEFFEKT = Paths.get("").toAbsolutePath() + PFAD_SOUNDS + "muenzen.mp3";
    String PFAD_SCHMIED_SOUNDEFFEKT = Paths.get("").toAbsolutePath() + PFAD_SOUNDS + "schmied.mp3";
    String PFAD_TUER_SOUNDEFFEKT = Paths.get("").toAbsolutePath() + PFAD_SOUNDS + "door.mp3";
    String PFAD_SWORD_SOUNDEFFEKT = Paths.get("").toAbsolutePath() + PFAD_SOUNDS + "sword.mp3";
    String PFAD_TRAP_SOUNDEFFEKT = Paths.get("").toAbsolutePath() + PFAD_SOUNDS + "trap.mp3";
    String PFAD_PARTY_HORN_SOUNDEFFEKT = Paths.get("").toAbsolutePath() + PFAD_SOUNDS + "party_horn.wav";
    String PFAD_NOTIFICATION_SOUNDEFFEKT = Paths.get("").toAbsolutePath() + PFAD_SOUNDS + "notification.wav";
    String PFAD_INVENTAR_ICON = "/images/inventar.png";

    String PFAD_DECK_ICON = "/images/cards.png";
    String WAFFE = "/images/waffe.png";
    String ARTEFAKT = "/images/artefakt.png";
    String PFAD_ANFANGSSEQUENZ = "/images/anfangssequenz.gif";


    String OPTIONEN_ICON = "/images/gear.png";
    String START_ICON = "/images/home.png";

    String CHAT_ICON_PNG = "/images/chat.png";
    String CHAT_ICON_GIF = "/images/chat.gif";
    String ZURUECK_ICON = "/images/arrow.png";
    String INFO_ICON = "/images/info.png";
    String NEUESSPIEL_ICON = "/images/plus.png";
    String SPIELLADEN_ICON = "/images/folder.png";
    String SPIELSPEICHERN_ICON = "/images/save.png";
    String START_MENUE_ICON = "/images/menu.png";
    String SPIEL_VERLASSEN_ICON = "/images/exit.png";
    String KREUZ_ICON = "/images/kreuz.jpg";
}
