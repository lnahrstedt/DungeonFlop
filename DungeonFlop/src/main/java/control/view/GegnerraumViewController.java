package control.view;

import control.model.KampfController;
import control.model.RundenController;
import control.model.SpielfeldController;
import control.model.raum.RaumController;
import control.netzwerk.ClientThreadController;
import control.netzwerk.ServerThreadController;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import model.*;
import model.effekt.ReichweiteEffekt;
import model.karte.Karte;
import model.netzwerk.NetzwerkFlagContainer;
import model.raum.Gegnerraum;
import org.controlsfx.control.Notifications;
import resources.enums.ServerClientKommunikation;
import resources.exceptions.*;
import resources.interfaces.*;
import utilities.CheatMode;
import view.Buehne;
import view.GegnerraumView;
import view.ItemView;
import view.KartenView;

import java.util.ArrayList;


/**
 * Diese Klasse stellt dem GegnerraumView Methoden zur Verfuegung.
 */
public class GegnerraumViewController {

    private static int positionBreite = Zahlensammlung.MINIMALE_BREITE;
    private static int positionHoehe = Zahlensammlung.MINIMALE_HOEHE;
    private static final ArrayList<Rectangle> gefaerbteRectangles = new ArrayList<>();
    private static BorderPane ausgewaehlteFigur = null;
    private static Spielfeld spielfeld;
    private static Runde runde;
    private static int karte = Zahlensammlung.KEINE_KARTE_AUSGEWAEHLT;

    /**
     * Diese Methode beendet ueber einen Button die aktuelle Runde.
     *
     * @param button
     */
    public static void beendeRunde(Button button) {
        button.setOnAction(actionEvent -> {
            if (CheatMode.getInstance().istAktiv()) {
                RaumController.erstelleLeerenErsatzraum((Gegnerraum) Spielwelt.getInstance().getSpielerEbenePosition().getSpielerRaumPosition());
            } else {
                RundenController.beendeRunde(KampfController.getKampf(), KampfController.getAktuelleRunde().getSpieler(), KampfController.getGegenSpieler(), KampfController.getKampf().getSpielfeld());
                setAusgewaehlterCircle(null);
                positionBreite = Zahlensammlung.MINIMALE_BREITE;
                positionHoehe = Zahlensammlung.MINIMALE_HOEHE;
                if (NetzwerkFlagContainer.wirdOnlineGespielt()) {
                    if (KampfController.spielIchGegner()) {
                        RundenController.beendeRunde(KampfController.getKampf(), KampfController.getAktuelleRunde().getSpieler(), KampfController.getGegenSpieler(), KampfController.getKampf().getSpielfeld());
                        ServerThreadController.getInstance().sendeDaten(ServerClientKommunikation.CLIENT_ZUG_BEENDEN);
                        KampfController.setSpielIchGegner(false);
                    } else {
                        if (NetzwerkFlagContainer.binIchClient()) {
                            ClientThreadController.getInstance().sendeDaten(ServerClientKommunikation.ZUG_WURDE_BEENDET);
                        } else {
                            ServerThreadController.getInstance().sendeDaten(ServerClientKommunikation.ZUG_WURDE_BEENDET);
                        }
                    }
                } else {
                    KampfController.setSpielIchGegner(!KampfController.spielIchGegner());
                }
                ViewWechsler.wechselZuGegnerraumView();
            }
        });
    }

    /**
     * Diese Methode waehlt ein Figur aus und greift bei bereits ausgewaehlter Figur die gegnerische Figur an.
     *
     * @param spielfigur Dies ist die Figur, die in der Benutzeroberflaeche angezeigt wird.
     * @param spielfeld  Dies ist das Spielfeld, auf dem gespielt wird.
     * @param runde      Dies ist die aktuelle Runde.
     */
    public static void waehleFigur(BorderPane spielfigur, Spielfeld spielfeld, Runde runde) {
        if (NetzwerkFlagContainer.binIchAnDerReihe() || !NetzwerkFlagContainer.wirdOnlineGespielt()) {
            spielfigur.setOnMouseClicked(mouseEvent -> {
                if (GegnerraumView.getAusgewaehlteKarte() != null) {
                    GegnerraumView.getAusgewaehlteKarte().setStyle(StyleSpeicher.FX_BORDER_COLOR_BLACK + StyleSpeicher.FX_BORDER_RADIUS_15);
                }
                // durch alle Felder des Spielfelds iterieren
                for (int i = 0; i < spielfeld.getBreite(); i++) {
                    for (int j = 0; j < spielfeld.getHoehe(); j++) {
                        // Bedingung: aktuelles Feld enthaelt uebergebene Spielfigur-BorderPane
                        if (GegnerraumView.getSpielfiguren()[j][i] == spielfigur) {
                            BorderPane spielfigur1 = GegnerraumView.getSpielfiguren()[j][i];
                            int breite = i;
                            int hoehe = j;
                            // Bedingung: Figur ist auswaehlbar
                            if (istFigurAuswaehlbar(spielfeld, hoehe, breite, runde)) {
                                SpielfeldController.setAktuelleSpielfigurBreite(breite);
                                SpielfeldController.setAktuelleSpielfigurHoehe(hoehe);
                                ausgewaehlteFigurAusfuehren(spielfeld, spielfigur1, hoehe, breite, runde);
                            }
                            // Bedingung: Figur ist angreifbar
                            else if (istFigurAngreifbar(spielfeld, hoehe, breite, runde)) {
                                figurGreiftAn(spielfeld, hoehe, breite);
                            }
                            // Bedingung: Figur ist nicht angreifbar und nicht auswaehlbar und Karte ist ausgewaehlt
                            else if (!istFigurAngreifbar(spielfeld, hoehe, breite, runde) && !istFigurAuswaehlbar(spielfeld, hoehe, breite, runde) && istKarteAusgewaehlt()) {
                                try {
                                    Karte ausgewaehlteKarte = KampfController.getKampf().getSpielerKartenverwaltung().get(runde.getSpieler()).getHandkarten().get(karte);
                                    RundenController.figurPlatzieren(runde.getSpieler(), spielfeld, runde, ausgewaehlteKarte, hoehe, breite, KampfController.getKampf());
                                    karte = Zahlensammlung.KEINE_KARTE_AUSGEWAEHLT;
                                } catch (FeldNichtVerfuegbarException e) {
                                    Notifications n = Notifications.create().title(StringsammlungGUI.NOTIFICATION_FELD_NICHT_AUSFUEHRBAR).hideAfter(Duration.seconds(Zahlensammlung.ANZEIGEDAUER_NOTIFICATIONS)).position(Pos.TOP_RIGHT).owner(Buehne.getInstance().getStage());
                                    n.show();
                                } catch (IndexOutOfBoundsException e) {
                                    Notifications n = Notifications.create().title(StringsammlungGUI.NOTIFICATION_KARTE_NICHT_VERFUEGBAR).hideAfter(Duration.seconds(Zahlensammlung.ANZEIGEDAUER_NOTIFICATIONS)).position(Pos.TOP_RIGHT).owner(Buehne.getInstance().getStage());
                                    n.show();
                                    e.printStackTrace();
                                } catch (SolException | FeldIstKeinNachbarException e) {
                                    Notifications n = Notifications.create().title(e.getMessage()).hideAfter(Duration.seconds(Zahlensammlung.ANZEIGEDAUER_NOTIFICATIONS)).position(Pos.TOP_RIGHT).owner(Buehne.getInstance().getStage());
                                    n.show();
                                }
                            }
                        }
                    }
                }
            });
        }
    }

    /**
     * Diese Methode sorgt dafuer, dass die gegnerische Figur angegriffen wird.
     *
     * @param spielfeld
     * @param hoehe
     * @param breite
     */
    private static void figurGreiftAn(Spielfeld spielfeld, int hoehe, int breite) {
        try {
            Spieler spieler = KampfController.getAktuelleRunde().getSpieler();
            RundenController.figurBewegen(spieler, spielfeld, KampfController.getAktuelleRunde(), spielfeld.getSpielfigur(positionHoehe, positionBreite), hoehe, breite);
        } catch (FeldNichtVerfuegbarException e) {
            Notifications n = Notifications.create().title(StringsammlungGUI.NOTIFICATION_FELD_NICHT_AUSFUEHRBAR).hideAfter(Duration.seconds(Zahlensammlung.ANZEIGEDAUER_NOTIFICATIONS)).position(Pos.TOP_RIGHT).owner(Buehne.getInstance().getStage());
            n.show();
        } catch (BewegungsException e) {
            Notifications n = Notifications.create().title(StringsammlungGUI.NOTIFICATION_KEINE_BEWEGUNGEN_MEHR).hideAfter(Duration.seconds(Zahlensammlung.ANZEIGEDAUER_NOTIFICATIONS)).position(Pos.TOP_RIGHT).owner(Buehne.getInstance().getStage());
            n.show();
        } catch (KeineAngriffeMehrException e) {
            Notifications n = Notifications.create().title(StringsammlungGUI.NOTIFICATION_KEINE_ANGRIFFE_MEHR).hideAfter(Duration.seconds(Zahlensammlung.ANZEIGEDAUER_NOTIFICATIONS)).position(Pos.TOP_RIGHT).owner(Buehne.getInstance().getStage());
            n.show();
        } catch (NichtImAngriffsradiusException | NichtImAktionsradiusException e) {
            Notifications n = Notifications.create().title(e.getMessage()).hideAfter(Duration.seconds(Zahlensammlung.ANZEIGEDAUER_NOTIFICATIONS)).position(Pos.TOP_RIGHT).owner(Buehne.getInstance().getStage());
            n.show();
        } finally {
            ausgewaehlteFigur = null;
        }
    }


    /**
     * Diese Methode ueberprueft, ob eine Figur auf dem Spielfeld auswaehlbar ist oder nicht.
     *
     * @param spielfeld
     * @param hoehe
     * @param breite
     * @param runde
     * @return boolean
     */
    private static boolean istFigurAuswaehlbar(Spielfeld spielfeld, int hoehe, int breite, Runde runde) {
        return ausgewaehlteFigur == null && !SpielfeldController.istGegnerfeld(spielfeld, hoehe, breite, runde.getSpieler());
    }

    /**
     * Diese Methode ueberprueft, ob eine gegnerische Figur angreifbar ist.
     *
     * @param spielfeld
     * @param hoehe
     * @param breite
     * @param runde
     * @return boolean
     */
    private static boolean istFigurAngreifbar(Spielfeld spielfeld, int hoehe, int breite, Runde runde) {
        return ausgewaehlteFigur != null && SpielfeldController.istGegnerfeld(spielfeld, hoehe, breite, runde.getSpieler());
    }

    /**
     * Diese Methode sorgt dafuer, dass sich eine ausgewaehlte Figur uber das Spielfeld bewegt.
     *
     * @param spielfeld
     * @param runde
     */
    public static void bewegeFigur(Spielfeld spielfeld, Runde runde) {
        for (int i = 0; i < spielfeld.getBreite(); i++) {
            for (int j = 0; j < spielfeld.getHoehe(); j++) {
                if (GegnerraumView.getRectangles()[j][i] != null) {
                    Rectangle rectangle = GegnerraumView.getRectangles()[j][i];
                    int breite = i;
                    int hoehe = j;
                    rectangle.setOnMouseClicked(mouseEvent -> {
                        if (istFigurAusgewaehltUndFeldFrei(spielfeld, hoehe, breite)) {
                            figurBewegen(spielfeld, runde, hoehe, breite);
                        }
                        if (istKarteAusgewaehlt()) {
                            platziereFigur(spielfeld, runde, hoehe, breite);
                        }
                    });
                }
            }
        }
    }

    /**
     * Diese Methode ueberprueft, ob eine Figur ausgewaehlt ist und das angegebene Feld frei ist.
     *
     * @param spielfeld
     * @param hoehe
     * @param breite
     * @return boolean
     */
    private static boolean istFigurAusgewaehltUndFeldFrei(Spielfeld spielfeld, int hoehe, int breite) {
        return ausgewaehlteFigur != null && SpielfeldController.istFeldFrei(spielfeld, hoehe, breite);
    }

    /**
     * Diese Methode uebrprueft, ob eine Karte ausgewahlt ist oder nicht.
     *
     * @return boolean
     */
    private static boolean istKarteAusgewaehlt() {
        return karte != Zahlensammlung.KEINE_KARTE_AUSGEWAEHLT;
    }

    /**
     * Diese Methode sorgt dafuer, dass sich ein ausgewaehlte Figur ueber das Spielfeld bewegt.
     *
     * @param spielfeld
     * @param runde
     * @param hoehe
     * @param breite
     */
    private static void figurBewegen(Spielfeld spielfeld, Runde runde, int hoehe, int breite) {
        try {
            RundenController.figurBewegen(runde.getSpieler(), spielfeld, runde, spielfeld.getSpielfigur(positionHoehe, positionBreite), hoehe, breite);
            positionHoehe = Zahlensammlung.MINIMALE_HOEHE;
            positionBreite = Zahlensammlung.MINIMALE_BREITE;
        } catch (FeldNichtVerfuegbarException e) {
            Notifications n = Notifications.create().title(StringsammlungGUI.NOTIFICATION_FELD_NICHT_AUSFUEHRBAR).hideAfter(Duration.seconds(Zahlensammlung.ANZEIGEDAUER_NOTIFICATIONS)).position(Pos.TOP_RIGHT).owner(Buehne.getInstance().getStage());
            n.show();
        } catch (BewegungsException e) {
            Notifications n = Notifications.create().title(StringsammlungGUI.NOTIFICATION_KEINE_BEWEGUNGEN_MEHR).hideAfter(Duration.seconds(Zahlensammlung.ANZEIGEDAUER_NOTIFICATIONS)).position(Pos.TOP_RIGHT).owner(Buehne.getInstance().getStage());
            n.show();
        } catch (KeineAngriffeMehrException e) {
            Notifications n = Notifications.create().title(StringsammlungGUI.NOTIFICATION_KEINE_ANGRIFFE_MEHR).hideAfter(Duration.seconds(Zahlensammlung.ANZEIGEDAUER_NOTIFICATIONS)).position(Pos.TOP_RIGHT).owner(Buehne.getInstance().getStage());
            n.show();
        } catch (NichtImAngriffsradiusException | NichtImAktionsradiusException e) {
            Notifications n = Notifications.create().title(e.getMessage()).hideAfter(Duration.seconds(Zahlensammlung.ANZEIGEDAUER_NOTIFICATIONS)).position(Pos.TOP_RIGHT).owner(Buehne.getInstance().getStage());
            n.show();
        } finally {
            ausgewaehlteFigur = null;
            Platform.runLater(ViewWechsler::wechselZuGegnerraumView);
        }
    }

    /**
     * Diese Methode sorgt dafuer, dass die ausgewaehlte Karte auf dem Spielfeld platziert wird.
     *
     * @param spielfeld
     * @param runde
     * @param hoehe
     * @param breite
     */
    private static void platziereFigur(Spielfeld spielfeld, Runde runde, int hoehe, int breite) {
        try {
            Karte ausgewaehlteKarte = KampfController.getKampf().getSpielerKartenverwaltung().get(runde.getSpieler()).getHandkarten().get(karte);
            RundenController.figurPlatzieren(runde.getSpieler(), spielfeld, runde, ausgewaehlteKarte, hoehe, breite, KampfController.getKampf());
            karte = Zahlensammlung.KEINE_KARTE_AUSGEWAEHLT;
        } catch (IndexOutOfBoundsException e) {
            Notifications n = Notifications.create().title(StringsammlungGUI.NOTIFICATION_KARTE_NICHT_VERFUEGBAR).hideAfter(Duration.seconds(Zahlensammlung.ANZEIGEDAUER_NOTIFICATIONS)).position(Pos.TOP_RIGHT).owner(Buehne.getInstance().getStage());
            n.show();
        } catch (FeldNichtVerfuegbarException e) {
            Notifications n = Notifications.create().title(StringsammlungGUI.NOTIFICATION_FELD_NICHT_AUSFUEHRBAR).hideAfter(Duration.seconds(Zahlensammlung.ANZEIGEDAUER_NOTIFICATIONS)).position(Pos.TOP_RIGHT).owner(Buehne.getInstance().getStage());
            n.show();
        } catch (SolException e) {
            Notifications n = Notifications.create().title(e.getMessage()).hideAfter(Duration.seconds(Zahlensammlung.ANZEIGEDAUER_NOTIFICATIONS)).position(Pos.TOP_RIGHT).owner(Buehne.getInstance().getStage());
            n.show();
        } catch (FeldIstKeinNachbarException e) {
            Notifications n = Notifications.create().title(e.getMessage()).hideAfter(Duration.seconds(Zahlensammlung.ANZEIGEDAUER_NOTIFICATIONS)).position(Pos.TOP_RIGHT).owner(Buehne.getInstance().getStage());
            n.show();
            karte = Zahlensammlung.KEINE_KARTE_AUSGEWAEHLT;
        }
        Platform.runLater(ViewWechsler::wechselZuGegnerraumView);
    }

    /**
     * Mithilfe dieser Methode lass sich die Karten in der Kartenverwaltung auswaehlen.
     *
     * @param karteBorderPane
     */
    public static void karteAuswaehlen(BorderPane karteBorderPane) {

        karteBorderPane.setOnMouseClicked(mouseEvent -> {
            for (int i = 0; i < GegnerraumView.getKarten().size(); i++) {
                if (GegnerraumView.getKarten().get(i) == karteBorderPane) {
                    if (karte != Zahlensammlung.KEINE_KARTE_AUSGEWAEHLT && karte < GegnerraumView.getKarten().size()) {
                        GegnerraumView.getKarten().get(karte).setStyle(StyleSpeicher.FX_BORDER_COLOR_BLACK + StyleSpeicher.FX_BORDER_RADIUS_15);
                    }
                    karteBorderPane.setStyle(StyleSpeicher.FX_BORDER_COLOR_ORANGE + StyleSpeicher.FX_BORDER_RADIUS_15);
                    karte = i;
                    GegnerraumView.setAusgewaehlteKarte(karteBorderPane);
                    ausgewaehlteFigur = null;
                    if (GegnerraumView.getMarkierteSpielfelder().size() > 0) {
                        felderAbwaehlen(GegnerraumView.getMarkierteSpielfelder());
                    }
                }
            }
        });
    }

    public static void felderAbwaehlen(ArrayList<Rectangle> markierteFelder) {
        for (Rectangle feld : markierteFelder) {
            feld.setFill(FarbenSammlung.FELDERFARBE);
        }
        markierteFelder.removeAll(markierteFelder);
    }


    /**
     * Erstellt eine Detailansicht der Spielfigur, ueber der sich der Cursor befidet.
     *
     * @param spielfigur Die Spielfigur, dessen Werte detailliert angezeigt werden sollen
     * @param root       Die Wurzel der Szene, in der die Detailansicht angezeigt werden soll.
     * @param runde      Die Runde, welche die Angriffe und Bewegungen enthaelt.
     * @return EventHandler
     */
    public static EventHandler<MouseEvent> hovernAufSpielfigurEnter(Spielfigur spielfigur, BorderPane root, Runde runde) {
        return mouseEvent -> {
            BorderPane item = KartenView.spielfigurViewGross(spielfigur, runde);
            ((VBox) ((HBox) root.getLeft()).getChildren().get(0)).getChildren().set(1, item);
        };
    }

    /**
     * Erzeugt die Ansicht einer Karte im Handstapel, sobald sich die Maus ueber der Karte befindet.
     *
     * @param karte
     * @param root
     * @return EventHandler
     */
    public static EventHandler<MouseEvent> hovernAufKarteEnter(Karte karte, BorderPane root) {
        return mouseEvent -> {
            BorderPane item = ItemView.erstelleItemGross(karte);
            ((VBox) ((HBox) root.getLeft()).getChildren().get(0)).getChildren().set(1, item);
        };
    }

    /**
     * Entfernt die Ansicht der Spielfigur, sobald sich die Maus nicht mehr ueber der Spielfigur befindet.
     *
     * @param root Die Wurzel der Szene, in der die Detailansicht angezeigt werden soll.
     * @return EventHandler
     */
    public static EventHandler<MouseEvent> hovernAufSpielfigurExit(BorderPane root) {
        return mouseEvent -> {
            BorderPane item = new BorderPane();
            item.setMaxSize(ZahlensammlungGUI.SPIELFIGURGROSS_BREITE, ZahlensammlungGUI.SPIELFIGURGROSS_HOEHE);
            item.setMinSize(ZahlensammlungGUI.SPIELFIGURGROSS_BREITE, ZahlensammlungGUI.SPIELFIGURGROSS_HOEHE);
            ((VBox) ((HBox) root.getLeft()).getChildren().get(0)).getChildren().set(1, item);
        };
    }

    /**
     * Diese Methode sorgt dafuer, dass ein Figur ausgewaehlt wird und die umliegenden Felder markiert werden.
     *
     * @param spielfeld
     * @param spielfigur
     * @param hoehe
     * @param breite
     * @param runde
     */
    private static void ausgewaehlteFigurAusfuehren(Spielfeld spielfeld, BorderPane spielfigur, int hoehe, int breite, Runde runde) {
        positionBreite = breite;
        positionHoehe = hoehe;

        ausgewaehlteFigur = spielfigur;
        karte = Zahlensammlung.KEINE_KARTE_AUSGEWAEHLT;

        if (runde.getAktionen().containsKey(spielfeld.getFelder()[hoehe][breite])) {
            if (runde.getAktionen().get(spielfeld.getFelder()[hoehe][breite]).getBewegungen() > 0) {
                markiereFelder(spielfeld, hoehe, breite);
            }
            if (runde.getAktionen().get(spielfeld.getFelder()[hoehe][breite]).getAngriffe() > 0) {
                markiereAngriffsfelder(spielfeld, hoehe, breite, spielfeld.getFelder()[hoehe][breite], runde);
            }
        } else {
            Notifications n = Notifications.create().title(StringsammlungGUI.NOTIFICATION_KEIN_AKTIONSSPEICHER_VORHANDEN).hideAfter(Duration.seconds(Zahlensammlung.ANZEIGEDAUER_NOTIFICATIONS)).position(Pos.TOP_RIGHT).owner(Buehne.getInstance().getStage());
            n.show();
        }
    }

    /**
     * Diese Methode markiert die umliegenden Felder der ausgewaehlten Figur.
     *
     * @param spielfeld
     * @param hoehe
     * @param breite
     */
    private static void markiereFelder(Spielfeld spielfeld, int hoehe, int breite) {
        markiereFeldUeberFigur(spielfeld, hoehe, breite);
        markiereFeldUnterFigur(spielfeld, hoehe, breite);
        markiereFeldLinksVonFigur(spielfeld, hoehe, breite);
        markiereFeldRechtsVonFigur(spielfeld, hoehe, breite);
    }

    /**
     * Diese Methode markiert die umliegenden Gegner der ausgewaehlten Figur.
     *
     * @param spielfeld
     * @param hoehe
     * @param breite
     * @param spielfigur
     * @param runde
     */
    private static void markiereAngriffsfelder(Spielfeld spielfeld, int hoehe, int breite, Spielfigur spielfigur, Runde runde) {
        int radius = Zahlensammlung.STANDARD_RADIUS;
        for (int i = 0; i < spielfigur.getEffekte().size(); i++) {
            if (spielfigur.getEffekte().get(i) instanceof ReichweiteEffekt) {
                radius = 1 + ((ReichweiteEffekt) spielfigur.getEffekte().get(i)).getWert();
            }
        }
        markiereAngriffsfeldUeberFigur(spielfeld, hoehe, breite, radius, runde);
        markiereAngriffsfeldUnterFigur(spielfeld, hoehe, breite, radius, runde);
        markiereAngriffsfeldLinksVonFigur(spielfeld, hoehe, breite, radius, runde);
        markiereAngriffsfeldRechtsVonFigur(spielfeld, hoehe, breite, radius, runde);
    }

    /**
     * Diese Methode markiert die gegnerischen Felder, die ueber der Spielfigur liegen.
     *
     * @param spielfeld
     * @param hoehe
     * @param breite
     * @param radius
     * @param runde
     */
    private static void markiereAngriffsfeldUeberFigur(Spielfeld spielfeld, int hoehe, int breite, int radius, Runde runde) {
        for (int i = 1; i < radius + 1; i++) {
            if (SpielfeldController.istGegnerfeld(spielfeld, hoehe - i, breite, runde.getSpieler())) {
                GegnerraumView.getRectangles()[hoehe - i][breite].setStroke(Color.RED);
            }
        }
    }

    /**
     * Diese Methode markiert die Felder ueber der Spielfigur.
     *
     * @param spielfeld
     * @param hoehe
     * @param breite
     */
    private static void markiereFeldUeberFigur(Spielfeld spielfeld, int hoehe, int breite) {
        if (SpielfeldController.istFeldUeberSpielfigurFrei(spielfeld, hoehe, breite, 1)) {
            GegnerraumView.getRectangles()[hoehe - 1][breite].setFill(FarbenSammlung.MARKIERTE_FELDERFARBE);
            GegnerraumView.getRectangles()[hoehe - 1][breite].setCursor(Cursor.HAND);
            GegnerraumView.getMarkierteSpielfelder().add(GegnerraumView.getRectangles()[hoehe - 1][breite]);
            gefaerbteRectangles.add(GegnerraumView.getRectangles()[hoehe - 1][breite]);
        }
    }

    /**
     * Diese Methode markiert die gegnerischen Felder, die unter der Spielfigur liegen.
     *
     * @param spielfeld
     * @param hoehe
     * @param breite
     * @param radius
     * @param runde
     */
    private static void markiereAngriffsfeldUnterFigur(Spielfeld spielfeld, int hoehe, int breite, int radius, Runde runde) {
        for (int i = 1; i < radius + 1; i++) {
            if (SpielfeldController.istGegnerfeld(spielfeld, hoehe + i, breite, runde.getSpieler())) {
                GegnerraumView.getRectangles()[hoehe + i][breite].setStroke(Color.RED);
            }
        }
    }

    /**
     * Diese Methode markiert die Felder unter der Spielfigur.
     *
     * @param spielfeld
     * @param hoehe
     * @param breite
     */
    private static void markiereFeldUnterFigur(Spielfeld spielfeld, int hoehe, int breite) {
        if (SpielfeldController.istFeldUnterSpielfigurFrei(spielfeld, hoehe, breite, 1)) {
            GegnerraumView.getRectangles()[hoehe + 1][breite].setFill(FarbenSammlung.MARKIERTE_FELDERFARBE);
            GegnerraumView.getRectangles()[hoehe + 1][breite].setCursor(Cursor.HAND);
            GegnerraumView.getMarkierteSpielfelder().add(GegnerraumView.getRectangles()[hoehe + 1][breite]);
            gefaerbteRectangles.add(GegnerraumView.getRectangles()[hoehe + 1][breite]);
        }
    }

    /**
     * Diese Methode markiert die Felder unter der Spielfigur.
     *
     * @param spielfeld
     * @param hoehe
     * @param breite
     * @param radius
     * @param runde
     */
    private static void markiereAngriffsfeldLinksVonFigur(Spielfeld spielfeld, int hoehe, int breite, int radius, Runde runde) {
        for (int i = 1; i < radius + 1; i++) {
            if (SpielfeldController.istGegnerfeld(spielfeld, hoehe, breite - i, runde.getSpieler())) {
                GegnerraumView.getRectangles()[hoehe][breite - i].setStroke(Color.RED);
            }
        }
    }

    /**
     * Diese Methode markiert die Felder links von der Spielfigur.
     *
     * @param spielfeld
     * @param hoehe
     * @param breite
     */
    private static void markiereFeldLinksVonFigur(Spielfeld spielfeld, int hoehe, int breite) {
        if (SpielfeldController.istFeldLinksVonSpielfigurFrei(spielfeld, hoehe, breite, 1)) {
            GegnerraumView.getRectangles()[hoehe][breite - 1].setFill(FarbenSammlung.MARKIERTE_FELDERFARBE);
            GegnerraumView.getRectangles()[hoehe][breite - 1].setCursor(Cursor.HAND);
            GegnerraumView.getMarkierteSpielfelder().add(GegnerraumView.getRectangles()[hoehe][breite - 1]);
            gefaerbteRectangles.add(GegnerraumView.getRectangles()[hoehe][breite - 1]);
        }
    }

    /**
     * Diese Methode markiert die gegnerischen Felder rechts von der Spielfigur.
     *
     * @param spielfeld
     * @param hoehe
     * @param breite
     * @param radius
     * @param runde
     */
    private static void markiereAngriffsfeldRechtsVonFigur(Spielfeld spielfeld, int hoehe, int breite, int radius, Runde runde) {
        for (int i = 1; i < radius + 1; i++) {
            if (SpielfeldController.istGegnerfeld(spielfeld, hoehe, breite + i, runde.getSpieler())) {
                GegnerraumView.getRectangles()[hoehe][breite + i].setStroke(Color.RED);
            }
        }
    }

    /**
     * Diese Methode markiert die Felder rechts von der Spielfigur.
     *
     * @param spielfeld
     * @param hoehe
     * @param breite
     */
    private static void markiereFeldRechtsVonFigur(Spielfeld spielfeld, int hoehe, int breite) {
        if (SpielfeldController.istFeldRechtsVonSpielfigurFrei(spielfeld, hoehe, breite, 1)) {
            GegnerraumView.getRectangles()[hoehe][breite + 1].setFill(FarbenSammlung.MARKIERTE_FELDERFARBE);
            GegnerraumView.getRectangles()[hoehe][breite + 1].setCursor(Cursor.HAND);
            GegnerraumView.getMarkierteSpielfelder().add(GegnerraumView.getRectangles()[hoehe][breite + 1]);
            gefaerbteRectangles.add(GegnerraumView.getRectangles()[hoehe][breite + 1]);
        }
    }

    public static Spielfeld getSpielfeld() {
        return spielfeld;
    }

    public static void setSpielfeld(Spielfeld spielfeld) {
        GegnerraumViewController.spielfeld = spielfeld;
    }

    public static Runde getRunde() {
        return runde;
    }

    public static void setRunde(Runde runde) {
        GegnerraumViewController.runde = runde;
    }

    public static void setAusgewaehlterCircle(BorderPane ausgewaehlteSpielfigur) {
        GegnerraumViewController.ausgewaehlteFigur = ausgewaehlteSpielfigur;
    }
}