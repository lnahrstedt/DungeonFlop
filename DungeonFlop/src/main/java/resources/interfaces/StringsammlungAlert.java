package resources.interfaces;

/**
 * Dieses Interface speichert alle Strings, die fuer die Alerts relevant sind.
 */
public interface StringsammlungAlert {

    String SPIEL_VERLASSEN_TITEL = " Spiel verlassen?";
    String SPIEL_VERLASSEN_TEXT = "Moechtest du das Spiel wirklich verlassen?\n\nAchtung! Alle ungespeicherten Daten gehen verloren und alle Verbindungen werden getrennt!";
    String NEUES_SPIEL_ERSTELLT_TITEL = " Auf in den Kampf!";
    String NEUES_SPIEL_ERSTELLT_NACHRICHT_NAME = "Gott zum Grusse ";
    String NEUES_SPIEL_ERSTELLT_NACHRICHT_SPIELERKLASSE = "!\n\nDeine Spielerklasse: ";
    String NEUES_SPIEL_ERSTELLT_NACHRICHT_SCHWIERIGKEITSGRAD = "\nDein Schwierigkeitsgrad: ";
    String NEUES_SPIEL_ERSTELLT_NACHRICHT_ABSCHLUSS = "\n\nBereit fuer die Schlacht?";
    String RAUM_KEIN_NACHBAR_TITEL = " Nicht moeglich!";
    String LAYOUT_ABFRAGE_TITEL = " Auswahl erforderlich!";
    String LAYOUT_ABFRAGE_KAEMPFEN = "Auf in den Kampf!";
    String LAYOUT_ABFRAGE_EINTRETEN = "Betreten!";
    String LAYOUT_ABFRAGE_ABBRECHEN = "Nichts tun";
    String LAYOUT_ABFRAGE_TEXT_KAMPF_1 = "Moechtest du im \"";
    String LAYOUT_ABFRAGE_TEXT_KAMPF_2_IDENTISCHER_RAUM = "\" kaempfen?";
    String LAYOUT_ABFRAGE_TEXT_EINTRETEN_1 = "Moechtest du den \"";
    String LAYOUT_ABFRAGE_TEXT_EINTRETEN_2_IDENTISCHER_RAUM = "\" betreten?";
    String AUSRUFEZEICHEN = "!";
    String LAYOUT_ABFRAGE_RAUM_LEER_TITEL = " Dieser Raum ist leer!";
    String LAYOUT_ABFRAGE_RAUM_LEER_NACHRICHT = "Hier kannst du nichts unternehmen.";
    String LAYOUT_ABFRAGE_EBENE_VERLASSEN_TITEL = "Dungeon verlassen?";
    String LAYOUT_ABFRAGE_EBENE_VERLASSEN_BUTTON_TYPE_TEXT = "Auf in den naechsten Dungeon!";
    String LAYOUT_ABFRAGE_EBENE_VERLASSEN_NACHBARRAUM_TEXT = """
            Nanu?! ...
            Was befindet sich denn hier? Eine Leiter! Sie fuehrt tief in einen Abgrund...

            Moechtest du diese Leiter nutzen, um zum naechsten Dungeon zu gelangen?

            Achtung! Hast du den Dungeon einmal verlassen, kannst nicht zurueckkehren!
            \s""";
    String LAYOUT_BUTTON_TYPE_SPIEL_BEENDEN = "Spiel beenden!";
    String LAYOUT_SPIEL_GEWONNEN_TITEL = " Gewonnen!";
    String LAYOUT_SPIEL_GEWONNEN_NACHRICHT_1 = "Herzlichen Gluehstrumpf ";
    String LAYOUT_SPIEL_GEWONNEN_NACHRICHT_2 = """
             Du hast Karkos und alle Listis besiegt und somit den Prosopos Zugang zum Wasser verschafft! Hallelujah!

            Ganz Erimos feiert dich als Held!

            Moechtest du das Spiel verlassen?

            Achtung! Du kannst nicht mehr zurueckkommen.
            \s""";
    String LAYOUT_NICHT_ALLE_GEGNER_BESIEGT_TITEL = " Zugang blockiert!";
    String LAYOUT_NICHT_ALLE_GEGNER_BESIEGT_NACHRICHT = """
            Huch?!
            Was ist denn hier los?

            Der Zugang zur Leiter ist durch maechtige Truppen der Listis versperrt!

            Besiege erst alle Gegnerraeume und versuche es dann erneut!

            Auf in den Kampf\s""";
    String NICHT_GENUG_GOLD = "Du hast nicht genug Gold!";
    String UNGUELTIGE_AUFWERTUNG = "Diese Karte kann nicht verbessert werden";
    String SPIEL_SPEICHERN_ALERT_TITEL = "Spiel gespeichert!";
    String SPIEL_SPEICHERN_ALERT_NACHRICHT = "Dein Spiel wurde erfolgreich gespeichert!";
    String RUNDE_VERLOREN_TITEL = " Runde verloren!";
    String RUNDE_VERLOREN_NACHRICHT = "Schade, du hast diese Runde verloren!\nFang nochmal von vorne an.";

    String RUNDE_VERLOREN_NACHRICHT_CLIENT = "Schade, du hast diese Runde verloren!";
    String RUNDE_GEWONNEN_TITEL = "Runde gewonnen!";
    String RUNDE_GEWONNEN_NACHRICHT = "Herzlichen Glueckwunsch!\nDu hast gewonnen!";

    String RUNDE_GEWONNEN_NACHRICHT_CLIENT = "Glueckwunsch, du hast diese Runde gewonnen!\nDas Spiel ist nun zu Ende.";
    String TEXT_INPUT_DIALOG_SERVER_ADRESSE_TITEL = "Eingabe erforderlich";
    String TEXT_INPUT_DIALOG_SERVER_ADRESSE_NACHRICHT = "Bitte gib die Adresse des Servers ein:";
    String VERBINDUNG_FEHLER_TITEL = "Fehler!";
    String VERBINDUNG_ERFOLGREICH_TITEL = " Verbindung erfolgreich";
    String CLIENT_BUTTONS_DEAKTIVIERT_TITEL = " Nicht moeglich!";
    String CLIENT_BUTTONS_DEAKTIVIERT_MESSAGE_BEWEGEN = "Dieser Button ist deaktiviert!\nEs kann sich nur der Hauptspieler in der Ebene bewegen!";
    String CLIENT_BUTTONS_DEAKTIVIERT_MESSAGE_ALLGEMEIN = "Dieser Button ist deaktiviert!\nEr kann nur vom Hauptspieler verwendet werden!";
    String NETZWERKFEHLER_TITEL = " Netzwerkfehler! ";
    String NETZWERKFEHLER_NACHRICHT = "Es ist ein unerwarteter Netzwerkfehler aufgetreten!\nWir arbeiten bereits an einer Loesung!";

    String SICHERHEITSFEHLER_TITEL = " Sicherheitsfehler! ";
    String SICHERHEITSFEHLER_NACHRICHT = "Es ist ein unerwarteter Sicherheitsfehler aufgetreten!\nWir arbeiten bereits an einer Loesung!";
    String CLIENT_HAT_VERLASSEN_TITEL = " Client disconnected!";
    String CLIENT_HAT_VERLASSEN_NACHRICHT = "Dein Mitspieler hat das Spiel verlassen!\nEs wid nach neuen Mitspielern gesucht...";
    String CLIENT_HAT_IM_GEGNERRAUM_VERLASSEN_NACHRICHT = "Dein Mitspieler hat das Spiel verlassen!\nWas fuer ein Feigling!\n\nDu wirst zurueck zum Dungeon-View geleitet...";
    String SERVER_HAT_VERLASSEN_TITEL = " Server disconnected!";
    String SERVER_HAT_VERLASSEN_NACHRICHT = "Der Hauptspieler hat das Spiel verlassen!\nDu wirst ins Hauptmenue weitergeleitet...";
    String ERSTELLEN_SERVER_FEHLGESCHLAGEN_TITEL = " Erstellen des Servers fehlgeschlagen!";
    String ERSTELLEN_SERVER_FEHLGESCHLAGEN_NACHRICHT = "Automatische Servererstellung fehlgeschlagen\nEs befindet sich bereits ein anderer Server auf diesem Port.\n\nWeahle in den Einstellungen einen anderen Port aus und starte den Server manuell ueber das Menue!";
    String CREDITSVIEW_HILFE_TITEL = "Du brauchst Hilfe?";
    String CREDITSVIEW_HILFE_NACHRICHT = "Dies sind die Credits!\nHier siehst Teilnehmer an dem Projekt.";
    String DECKVIEW_HILFE_TITEL = "Du brauchst Hilfe?";
    String DECKVIEW_HILFE_NACHRICHT = "Hier kannst du dein ganzes Deck angucken.\nEs werden alle Karten angezeigt.";
    String EBENENLAYOUTVIEW_HILFE_TITEL = "Du brauchst Hilfe?";
    String EBENENLAYOUTVIEW_HILFE_NACHRICHT = "Du befindest dich in einer Ebene!\nHier kannst du dich von Raum zu Raum bewegen\nund diese betreten.";
    String EBENENLAYOUTVIEWSERVERINTERAGIERT_HILFE_TITEL = "Du brauchst Hilfe?";
    String EBENENLAYOUTVIEWSERVERINTERAGIERT_HILFE_NACHRICHT = "Du befindest dich in einer Ebene!\nHier kannst du dich von Raum zu Raum bewegen\nund diese betreten.";
    String ERSTELLEMENUEVIEW_HILFE_TITEL = "Du brauchst Hilfe?";
    String ERSTELLEMENUEVIEW_HILFE_NACHRICHT = "Du befindest dich im Menue!\nHier kannst du beispielsweise ein neues Spiel starten!";
    String ERSTELLETOPVIEW_HILFE_TITEL = "Du brauchst Hilfe?";
    String ERSTELLETOPVIEW_HILFE_NACHRICHT = "Du befindest dich im Menue!\nHier kannst du beispielsweise ein neues Spiel starten!";
    String GEGNERRAUMVIEW_HILFE_TITEL = "Du brauchst Hilfe?";
    String GEGNERRAUMVIEW_HILFE_NACHRICHT = """
            Du befindest dich im Gegnerraum!
            Hier musst du dein Koennen unter Beweis stellen.\s
            Greife mit deiner Spielfigur den Gegner an und besiege ihn.
            Du kannst deine Karten nutzen, um neue Spielfiguren auf dem Feld zu platzieren oder Effekte anzuwenden.
            Karten duerfen nur an anliegenden Felder platziert werden.
            Viel Erfolg!""";
    String HAENDLERRAUMVIEW_HILFE_TITEL = "Du brauchst Hilfe?";
    String HAENDLERRAUMVIEW_HILFE_NACHRICHT = "Du befindest dich im Haendlerraum!\n"
            + "Hier kannst du Waffen, Artefakte und Karten gegen Gold kaufen!";
    String INVENTARVIEW_HILFE_TITEL = "Du brauchst Hilfe?";
    String INVENTARVIEWVIEW_HILFE_NACHRICHT = """
            Das ist dein Inventar!
            Hier werden deine Waffen und Artefakte angezeigt!
            Ausserdem kannst du sie hier ausruesten.""";
    String ITEMVIEW_HILFE_TITEL = "Du brauchst Hilfe?";
    String ITEMVIEW_HILFE_NACHRICHT = "Hier ist dein Standard-Hilfstext!\nViel Spass damit!";
    String KARTENVIEW_HILFE_TITEL = "Du brauchst Hilfe?";
    String KARTENVIEW_HILFE_NACHRICHT = "Hier ist dein Standard-Hilfstext!\nViel Spass damit!";
    String NEUESSPIELVIEW_HILFE_TITEL = "Du brauchst Hilfe?";
    String NEUESSPIELVIEW_HILFE_NACHRICHT = """
            Hier kannst du ein neues Spiel erstellen!
            Waehle dazu einfach einen Namen, einen Schwierigkeitsgrad und deine Spielerklasse aus.
            Klicke anschliessend auf "Spiel erstellen!".""";
    String SCHATZRAUMVIEW_HILFE_TITEL = "Du brauchst Hilfe?";
    String SCHATZRAUMVIEW_HILFE_NACHRICHT = "Du befindest dich im Schatzraum!\nHier kannst du kostenlos absahnen!"
            + "Waehle eine der 5 Karten aus und erhalte einen Minion oder einen Zauber.";
    String SCHMIEDRAUMVIEW_HILFE_TITEL = "Du brauchst Hilfe?";
    String SCHMIEDRAUMVIEW_HILFE_NACHRICHT = "Du befindest dich im Schmiedraum!\nHier kannst du deine Karten aufwerten lassen!";
    String SPIELEINSTELLUNGENVIEW_HILFE_TITEL = "Du brauchst Hilfe?";
    String SPIELEINSTELLUNGENVIEW_HILFE_NACHRICHT = "Du befindest dich in den Spieleinstellungen!\n"
            + "Hier kannst du die Lautstaerken der Musik und der Soundeffekte aendern"
            + " oder in den Vollbildmodus wechseln!";
    String SPIELERINFOVIEW_HILFE_TITEL = "Du brauchst Hilfe?";
    String SPIELERINFOVIEW_HILFE_NACHRICHT = "Hier ist dein Standard-Hilfstext!\nViel Spass damit!";
    String SPIELERKLASSENVIEW_HILFE_TITEL = "Du brauchst Hilfe?";
    String SPIELERKLASSENVIEW_HILFE_NACHRICHT = "Hier ist dein Standard-Hilfstext!\nViel Spass damit!";
    String SPIELFIGURENINFOVIEW_HILFE_TITEL = "Du brauchst Hilfe?";
    String SPIELFIGURENINFOVIEW_HILFE_NACHRICHT = "Hier ist dein Standard-Hilfstext!\nViel Spass damit!";
    String SPIELLADENVIEW_HILFE_TITEL = "Du brauchst Hilfe?";
    String SPIELLADENVIEW_HILFE_NACHRICHT = "Hier kannst du einen vorhandenen Spielstand laden!\n"
            + "Klicke dazu auf \"Spiel laden\" und waehle die Datei mit dem Spielstand aus!";
    String STARTVIEW_HILFE_TITEL = "Du brauchst Hilfe?";
    String STARTVIEW_HILFE_NACHRICHT = "Du befindest dich im Hauptmenue!\nHier kannst du ein Spiel erstellen, einem bereits bestehenden beitreten oder ein Spiel laden.";
    String STORYVIEW_HILFE_TITEL = "Du brauchst Hilfe?";
    String STORYVIEW_HILFE_NACHRICHT = "Hier erfaehrst du, was dich in diesem Spiel erwartet und was deine Mission ist!\n"
            + "Viel Spass beim Spielen!";
    String STUFENAUFSTIEGVIEW_HILFE_TITEL = "Du brauchst Hilfe?";
    String STUFENAUFSTIEGVIEW_HILFE_NACHRICHT = "Hier wirst du ueber deinen Stufenaufstieg informiert.\nDu kannst dir ein Talent aussuchen!";
    String UNBEKANNTE_VIEW_HILFE_TITEL = "Du brauchst Hilfe?";
    String UNBEKANNTE_VIEW_HILFE_NACHRICHT = """
            Hier kann dir leider niemand weiterhelfen,
            denn dieser Bildschirm sollte eigentlich nicht existieren.
            Sorry!""";
    String CHAT_OHNE_GEGENSPIELER_TITEL = "Chat kann nicht geoeffnet werden!";
    String CHAT_OHNE_GEGENSPIELER_NACHRICHT = "Ohne eine aktive Verbindung zu einem Gegenspieler kannst du den Chat nicht oeffnen...\n\nVersuche es erneut, sobald ein Spieler beigetreten ist.";

}
