package control.model;

import javafx.geometry.Pos;
import javafx.util.Duration;
import model.Runde;
import model.Spieler;
import model.Spielfeld;
import model.Spielfigur;
import model.effekt.ReichweiteEffekt;
import org.controlsfx.control.Notifications;
import resources.exceptions.FeldIstKeinNachbarException;
import resources.exceptions.FeldNichtVerfuegbarException;
import resources.exceptions.NichtImAktionsradiusException;
import resources.interfaces.Zahlensammlung;
import view.Buehne;


/**
 * Dieser Klasse kontrolliert alle Aktionen auf dem jeweiligen Spielfeld.
 * Zu den Aktionen zaehlen das Bewegen, Setzen, Entfernen von Spielfiguren unter der Beruecksichtigung von bestimmten Regularien.
 */
public class SpielfeldController {

    private static int aktuelleSpielfigurBreite = 0;
    private static int aktuelleSpielfigurHoehe = 0;

    /**
     * Diese Methode prueft, ob das Feld ueber einem bestimmten Feld frei ist.
     *
     * @param spielfeld Das aktuelle Spielfeld ist anzugeben, auf dem die Methode angewandt werden soll.
     * @param hoehe     Dies ist der Wert der Hoehe des gewuenschten Feldes.
     * @param breite    Dies ist der Wert der Breite des gewuenschten Feldes.
     * @return boolean
     */
    public static boolean istFeldUeberSpielfigurFrei(Spielfeld spielfeld, int hoehe, int breite, int radius) {
        return hoehe - radius >= Zahlensammlung.MINIMALE_HOEHE && spielfeld.getFelder()[hoehe - radius][breite] == null;
    }

    /**
     * Diese Methode prueft, ob das Feld unter einem bestimmten Feld frei ist.
     *
     * @param spielfeld Das aktuelle Spielfeld ist anzugeben, auf dem die Methode angewandt werden soll.
     * @param hoehe     Dies ist der Wert der Hoehe des gewuenschten Feldes.
     * @param breite    Dies ist der Wert der Breite des gewuenschten Feldes.
     * @return boolean
     */
    public static boolean istFeldUnterSpielfigurFrei(Spielfeld spielfeld, int hoehe, int breite, int radius) {
        return hoehe + radius < spielfeld.getHoehe() && spielfeld.getFelder()[hoehe + radius][breite] == null;
    }

    /**
     * Diese Methode prueft, ob das Feld links neben einem bestimmten Feld frei ist.
     *
     * @param spielfeld Das aktuelle Spielfeld ist anzugeben, auf dem die Methode angewandt werden soll.
     * @param hoehe     Dies ist der Wert der Hoehe des gewuenschten Feldes.
     * @param breite    Dies ist der Wert der Breite des gewuenschten Feldes.
     * @return boolean
     */
    public static boolean istFeldLinksVonSpielfigurFrei(Spielfeld spielfeld, int hoehe, int breite, int radius) {
        return breite - radius >= Zahlensammlung.MINIMALE_BREITE && spielfeld.getFelder()[hoehe][breite - radius] == null;
    }

    /**
     * Diese Methode prueft, ob das Feld rechts neben einem bestimmten Feld frei ist.
     *
     * @param spielfeld Das aktuelle Spielfeld ist anzugeben, auf dem die Methode angewandt werden soll.
     * @param hoehe     Dies ist der Wert der Hoehe des gewuenschten Feldes.
     * @param breite    Dies ist der Wert der Breite des gewuenschten Feldes.
     * @return boolean
     */
    public static boolean istFeldRechtsVonSpielfigurFrei(Spielfeld spielfeld, int hoehe, int breite, int radius) {
        return breite + radius < spielfeld.getBreite() && spielfeld.getFelder()[hoehe][breite + radius] == null;
    }

    /**
     * Diese Methode prueft generell, ob ein bestimmtes Feld frei ist.
     *
     * @param spielfeld Das aktuelle Spielfeld ist anzugeben, auf dem die Methode angewandt werden soll.
     * @param hoehe     Dies ist der Wert der Hoehe des gewuenschten Feldes.
     * @param breite    Dies ist der Wert der Breite des gewuenschten Feldes.
     * @return boolean
     */
    public static boolean istFeldFrei(Spielfeld spielfeld, int hoehe, int breite) {
        return spielfeld.getFelder()[hoehe][breite] == null;
    }

    /**
     * Diese Methode prueft, ob es sich bei einem bestimmten Feld um ein Feld des Gegners handelt.
     *
     * @param spielfeld Das aktuelle Spielfeld ist anzugeben, auf dem die Methode angewandt werden soll.
     * @param hoehe     Dies ist der Wert der Hoehe des gewuenschten Feldes.
     * @param breite    Dies ist der Wert der Breite des gewuenschten Feldes.
     * @param spieler   Der Spieler der prueft, ob es sich um ein Feld des Gegners handelt.
     * @return boolean
     */
    public static boolean istGegnerfeld(Spielfeld spielfeld, int hoehe, int breite, Spieler spieler) {
        if (hoehe < 0 || breite < 0 || hoehe >= spielfeld.getHoehe() || breite >= spielfeld.getBreite()) {
            return false;
        }
        Spielfigur spielfigur = spielfeld.getFelder()[hoehe][breite];
        if (spielfigur != null) {
            return !spielfeld.getSpielfigurenAufDemFeld().get(spieler).contains(spielfigur);
        } else {
            return false;
        }
    }

    /**
     * Diese Methode prueft, ob es sich bei einem bestimmten Feld um ein Feld des Gegners handelt.
     *
     * @param spielfeld  Das aktuelle Spielfeld ist anzugeben, auf dem die Methode angewandt werden soll.
     * @param spielfigur Die zu ueberpruefende Spielfigur muss angegeben werden.
     * @param runde      Dies ist die derzeit aktuelle Runde.
     * @return boolean
     */
    private static boolean istEsSpielfigurDesSpielers(Spielfigur spielfigur, Runde runde, Spielfeld spielfeld) {
        Spieler spieler = runde.getSpieler();
        return spielfeld.getSpielfigurenAufDemFeld().get(spieler).contains(spielfigur);
    }

    /**
     * Diese Methode prueft, ob sich ein Feld vertikal also in der Hoehe in dem Radius eines anderen Feldes befindet.
     *
     * @param hoehe  Dies ist die Hoehe des zu ueberpreufenden Feldes.
     * @param breite Dies ist die Hoehe des zu ueberpreufenden Feldes.
     * @return boolean
     */
    private static boolean istFeldVertikalImAktionsRadius(int hoehe, int breite, int radius) {
        for (int i = 1; i < radius + 1; i++) {
            if (breite == aktuelleSpielfigurBreite
                    && (hoehe - i == aktuelleSpielfigurHoehe || hoehe + i == aktuelleSpielfigurHoehe)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Diese Methode prueft, ob sich ein Feld horizontal also in der Breite in dem Radius eines anderen Feldes befindet.
     *
     * @param hoehe  Dies ist die Hoehe des zu ueberpreufenden Feldes.
     * @param breite Dies ist die Hoehe des zu ueberpreufenden Feldes.
     * @return boolean
     */
    private static boolean istFeldHorizontalImAktionsRadius(int hoehe, int breite, int radius) {
        for (int i = 1; i < radius + 1; i++) {
            if (hoehe == aktuelleSpielfigurHoehe
                    && (breite + i == aktuelleSpielfigurBreite || breite - i == aktuelleSpielfigurBreite)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Diese Methode ueberprueft, ob sich ein Feld in dem Angriffsradius einer bestimmten Spielfigur befindet.
     *
     * @param hoehe
     * @param breite
     * @param spielfigur
     * @param spielfeld
     * @return
     */
    public static boolean istFeldImAngriffsradius(int hoehe, int breite, Spielfigur spielfigur, Spielfeld spielfeld) {
        gebePositionVonSpielfigur(spielfigur, spielfeld);
        for (int i = 0; i < spielfigur.getEffekte().size(); i++) {
            if (spielfigur.getEffekte().get(i) instanceof ReichweiteEffekt) {
                int reichweite = 1 + ((ReichweiteEffekt) spielfigur.getEffekte().get(i)).getWert();
                return istFeldHorizontalImAktionsRadius(hoehe, breite, reichweite) || istFeldVertikalImAktionsRadius(hoehe, breite, reichweite);
            }
        }
        return istFeldImAktionsRadius(hoehe, breite);
    }

    /**
     * Diese Methode prueft generell, ob sich ein Feld in dem Radius eines anderen Feldes befindet.
     *
     * @param hoehe  Dies ist die Hoehe des zu ueberpreufenden Feldes.
     * @param breite Dies ist die Hoehe des zu ueberpreufenden Feldes.
     * @return boolean
     */
    public static boolean istFeldImAktionsRadius(int hoehe, int breite) {
        return istFeldHorizontalImAktionsRadius(hoehe, breite, 1)
                || istFeldVertikalImAktionsRadius(hoehe, breite, 1);
    }

    // Bewegungsausfuehrung ----------------------------

    /**
     * Diese Methode sorgt dafuer, dass sich eine Spielfigur auf dem Spielfeld unter bestimmten Regularien bewegen kann.
     *
     * @param spielfigur Dies ist die zu bewegende Spielfigur.
     * @param spielfeld  Dies ist das aktuelle Spielfeld.
     * @param hoehe      Dies ist die Hoehe zu der sich die Spielfigur bewegen soll.
     * @param breite     Dies ist die aktuelle Breite, zu der sich die Spielfigur bewegen soll.
     * @throws FeldNichtVerfuegbarException Diese Methode wirft eine Exception, wenn ein Feld nicht belegt werden kann.
     */
    public static void bewegeFigur(Spielfigur spielfigur, Spielfeld spielfeld, int hoehe, int breite, Runde runde) throws NichtImAktionsradiusException {
        gebePositionVonSpielfigur(spielfigur, spielfeld);
        if (istEsSpielfigurDesSpielers(spielfigur, runde, spielfeld)) {
            if (istFeldImAktionsRadius(hoehe, breite)) {
                try {
                    spielfeld.setSpielfigurenfeld(spielfigur, hoehe, breite);
                    spielfeld.setSpielfigurenfeld(null, aktuelleSpielfigurHoehe, aktuelleSpielfigurBreite);
                } catch (FeldNichtVerfuegbarException e) {
                    Notifications n = Notifications.create().title(e.getMessage()).hideAfter(Duration.seconds(Zahlensammlung.ANZEIGEDAUER_NOTIFICATIONS)).position(Pos.TOP_RIGHT).owner(Buehne.getInstance().getStage());
                    n.show();
                }
            } else {
                throw new NichtImAktionsradiusException();
            }
        }
    }

    // Abfragen zu Spielfiguren ------------------------

    /**
     * Diese Methode ueberprueft, ob eine Figur an eine andere grenzt.
     *
     * @param spielfeld Dies ist das aktuelle Spielfeld.
     * @param hoehe     Dies ist die Hoehe des zu ueberpruefenden Feldes.
     * @param breite    Dies ist die Breite des zu ueberpruefenden Feldes.
     * @return boolean
     */
    private static boolean grenztFigurAnAndereFigur(Spielfeld spielfeld, int hoehe, int breite) {
        return !istFeldUeberSpielfigurFrei(spielfeld, hoehe, breite, 1)
                || !istFeldUnterSpielfigurFrei(spielfeld, hoehe, breite, 1)
                || !istFeldLinksVonSpielfigurFrei(spielfeld, hoehe, breite, 1)
                || !istFeldRechtsVonSpielfigurFrei(spielfeld, hoehe, breite, 1);
    }

    /**
     * Diese Methode ueberprueft, ob eine Figur des Gegners vertikal an eine andere grenzt.
     *
     * @param spielfeld Dies ist das aktuelle Spielfeld.
     * @param hoehe     Dies ist die Hoehe des zu ueberpruefenden Feldes.
     * @param breite    Dies ist die Breite des zu ueberpruefenden Feldes.
     * @param runde     Dies ist die aktuelle Runde.
     * @return boolean
     */
    private static boolean istVertikalAngrenzendeFigurSpielerFigur(Spielfeld spielfeld, int hoehe, int breite, Runde runde) {
        return (hoehe - 1 >= Zahlensammlung.MINIMALE_HOEHE
                && istEsSpielfigurDesSpielers(spielfeld.getFelder()[hoehe - 1][breite], runde, spielfeld))
                || (hoehe + 1 <= spielfeld.getHoehe() - 1
                && istEsSpielfigurDesSpielers(spielfeld.getFelder()[hoehe + 1][breite], runde, spielfeld));
    }

    /**
     * Diese Methode ueberprueft, ob eine Figur des Gegners horizontal an eine andere grenzt.
     *
     * @param spielfeld Dies ist das aktuelle Spielfeld.
     * @param hoehe     Dies ist die Hoehe des zu ueberpruefenden Feldes.
     * @param breite    Dies ist die Breite des zu ueberpruefenden Feldes.
     * @return boolean
     */
    private static boolean istHorizontalsAngrenzendeFigurSpielerFigur(Spielfeld spielfeld, int hoehe, int breite, Runde runde) {
        return (breite - 1 >= Zahlensammlung.MINIMALE_BREITE
                && istEsSpielfigurDesSpielers(spielfeld.getFelder()[hoehe][breite - 1], runde, spielfeld))
                || (breite + 1 <= spielfeld.getBreite() - 1
                && istEsSpielfigurDesSpielers(spielfeld.getFelder()[hoehe][breite + 1], runde, spielfeld));
    }

    /**
     * Diese Methode ueberprueft generell, ob eine Figur des Gegners eine andere grenzt.
     *
     * @param spielfeld Dies ist das aktuelle Spielfeld.
     * @param hoehe     Dies ist die Hoehe des zu ueberpruefenden Feldes.
     * @param breite    Dies ist die Breite des zu ueberpruefenden Feldes.
     * @return boolean
     */
    private static boolean istAngrenzendeFigurSpielerFigur(Spielfeld spielfeld, int hoehe, int breite, Runde runde) {
        return istVertikalAngrenzendeFigurSpielerFigur(spielfeld, hoehe, breite, runde) ||
                istHorizontalsAngrenzendeFigurSpielerFigur(spielfeld, hoehe, breite, runde);
    }

    /**
     * Diese Methode ueberprueft, ob eine Figur an eine andere grenzt.
     *
     * @param spielfeld Dies ist das aktuelle Spielfeld.
     * @param hoehe     Dies ist die Hoehe des zu ueberpruefenden Feldes.
     * @param breite    Dies ist die Breite des zu ueberpruefenden Feldes.
     * @param runde     Dies ist die aktuelle Runde
     * @return boolean
     */
    private static boolean grenztFeldAnFeldVonSpielfigurDesSpielers(Spielfeld spielfeld, int hoehe, int breite, Runde runde) {
        return grenztFigurAnAndereFigur(spielfeld, hoehe, breite) &&
                istAngrenzendeFigurSpielerFigur(spielfeld, hoehe, breite, runde);
    }

    // -------------------------------------------------

    /**
     * Diese Methode entfernt eine Spielfigur von dem Spielfeld.
     *
     * @param spielfigur Dies ist die zu entfernende Spielfigur.
     * @param spielfeld  Das Spielfeld, von dem die Spielfigur entfernt werden soll.
     * @param runde      Dies ist die aktuelle Runde.
     * @throws FeldNichtVerfuegbarException Diese Exception wird geworfen, wenn das Feld nicht belegbar ist.
     */
    public static void entferneFigur(Spielfigur spielfigur, Spielfeld spielfeld, Runde runde) throws FeldNichtVerfuegbarException {
        gebePositionVonSpielfigur(spielfigur, spielfeld);
        spielfeld.setSpielfigurenfeld(null, aktuelleSpielfigurHoehe, aktuelleSpielfigurBreite);
        spielfeld.getSpielfigurenAufDemFeld().get(runde.getSpieler()).remove(spielfigur);
    }

    /**
     * Diese Methode setzt Spielfiguren auf das Spielfeld.
     *
     * @param spielfeld  Das Spielfeld auf das die Spielfigur gesetzt wird.
     * @param spielfigur Dies ist die zu setzende Spielfigur.
     * @param hoehe      Dies ist die Hoehe, auf die Spielfigur gesetzt werden soll.
     * @param breite     Dies ist die Breite, auf die Spielfigur gesetzt werden soll.
     * @param runde      Dies ist die aktuelle Runde.
     * @throws FeldNichtVerfuegbarException Diese Exception wird geworfen, wenn ein Feld nicht belegbar ist.
     */
    public static void setzeFigur(Spielfeld spielfeld, Spielfigur spielfigur, int hoehe, int breite, Runde runde) throws FeldNichtVerfuegbarException, FeldIstKeinNachbarException {
        Spieler spieler = runde.getSpieler();
        if (istFeldFrei(spielfeld, hoehe, breite)) {
            if (grenztFeldAnFeldVonSpielfigurDesSpielers(spielfeld, hoehe, breite, runde)) {
                spielfeld.setSpielfigurenfeld(spielfigur, hoehe, breite);
                spielfeld.getSpielfigurenAufDemFeld().get(spieler).add(spielfigur);
            } else {
                throw new FeldIstKeinNachbarException(hoehe, breite);
            }
        } else {
            throw new FeldNichtVerfuegbarException(hoehe, breite, spielfigur);
        }
    }

    /**
     * Diese Methode ermittelt die Position einer bestimmten Spielfigur.
     *
     * @param spielfigur Diese Spielfigur, dessen Position ermittelt werden soll.
     * @param spielfeld  Das Spielfeld auf dem sich die Spielfigur befindet.
     */
    public static void gebePositionVonSpielfigur(Spielfigur spielfigur, Spielfeld spielfeld) {
        for (int i = 0; i < spielfeld.getBreite(); i++) {
            for (int j = 0; j < spielfeld.getHoehe(); j++) {
                if (spielfigur == spielfeld.getFelder()[j][i]) {
                    aktuelleSpielfigurBreite = i;
                    aktuelleSpielfigurHoehe = j;
                }
            }
        }
    }

    public static int getAktuelleSpielfigurHoehe() {
        return aktuelleSpielfigurHoehe;
    }

    public static int getAktuelleSpielfigurBreite() {
        return aktuelleSpielfigurBreite;
    }

    public static void setAktuelleSpielfigurHoehe(int aktuelleSpielfigurHoehe) {
        SpielfeldController.aktuelleSpielfigurHoehe = aktuelleSpielfigurHoehe;
    }

    public static void setAktuelleSpielfigurBreite(int aktuelleSpielfigurBreite) {
        SpielfeldController.aktuelleSpielfigurBreite = aktuelleSpielfigurBreite;
    }
}
