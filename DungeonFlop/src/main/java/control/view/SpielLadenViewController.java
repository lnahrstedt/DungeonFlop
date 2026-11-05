package control.view;

import control.netzwerk.ServerThreadController;
import javafx.stage.FileChooser;
import model.Spielwelt;
import resources.interfaces.PfadSammlung;
import resources.interfaces.StringsammlungGUI;
import utilities.MeinIO;
import view.Buehne;

import java.io.File;
import java.util.ArrayList;

/**
 * Diese Klasse stellt dem SpielLadenView Methoden zur Verfuegung.
 * Zum Beispiel verarbeitet sie die Eingaben des Spielers.
 */
public class SpielLadenViewController {

    /**
     * Oeffnet einen FileChooser-Dialog und gibt die gewaehlte Datei an setSpielwelt weiter,
     * anschliesssend wird der Netzwerk-Server gestartet.
     */
    public static void oeffneFileChooser() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(StringsammlungGUI.TITEL_FILECHOOSER);
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter(StringsammlungGUI.DATEIART_FILECHOOSER, StringsammlungGUI.DATEIENDUNG_FILECHOOSER));
        File selectedFile = fileChooser.showOpenDialog(Buehne.getInstance().getStage());
        if (selectedFile != null) {
            setSpielwelt(MeinIO.ladeSpielAusDatei(selectedFile));
        }
        ServerThreadController.getInstance().starteServerThread();
    }

    /**
     * Kopiert die Attribute der Spielwelt aus der Spieldatei in die Instanz der Spielwelt und
     * wechselt anschliessend auf die EbenenLayoutScene.
     *
     * @param spielDatei
     */
    public static void setSpielwelt(Object spielDatei) {
        Spielwelt.getInstance().setProtagonist(((Spielwelt) spielDatei).getProtagonist());
        Spielwelt.getInstance().setEbenen(((Spielwelt) spielDatei).getEbenen());
        Spielwelt.getInstance().setSpielerEbenePosition(((Spielwelt) spielDatei).getSpielerEbenePosition());
        Spielwelt.getInstance().setStory(((Spielwelt) spielDatei).getStory());
        Spielwelt.getInstance().setSchwierigkeitsgrad(((Spielwelt) spielDatei).getSchwierigkeitsgrad());
        ViewWechsler.wechselZuEbenenLayoutView();
    }

    public static void setSpielwelt(Spielwelt spielDatei) {
        Spielwelt.getInstance().setProtagonist(spielDatei.getProtagonist());
        Spielwelt.getInstance().setEbenen(spielDatei.getEbenen());
        Spielwelt.getInstance().setSpielerEbenePosition(spielDatei.getSpielerEbenePosition());
        Spielwelt.getInstance().setStory(spielDatei.getStory());
        Spielwelt.getInstance().setSchwierigkeitsgrad(spielDatei.getSchwierigkeitsgrad());
        ViewWechsler.wechselZuEbenenLayoutView();
    }

    /**
     * Diese Methode gibt eine Liste aller Dateien mit gespeicherten Spielstaenden in dem uebergebenen Ordner zurueck.
     *
     * @return Liste der gespeicherten Spiele
     */
    public static ArrayList<File> zeigeGespeicherteSpiele() {
        ArrayList<File> spielDateien = new ArrayList<>(MeinIO.listFiles(PfadSammlung.PFAD_GESPEICHERTE_SPIELE));
        for (int i = 0; i < spielDateien.size(); i++) {
            if (!spielDateien.get(i).toString().endsWith(StringsammlungGUI.DATEIENDUNG_SPIELDATEIEN)) {
                spielDateien.remove(i);
                i--;
            }
        }
        return spielDateien;
    }
}
