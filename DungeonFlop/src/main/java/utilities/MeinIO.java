package utilities;

import java.io.*;
import java.util.*;

/**
 * Diese Klasse stellt Methoden fuer In- und Output zur Verfuegung.
 */
public abstract class MeinIO {

    /**
     * Gibt eine zufaellige Zahl von 0 bis obereGrenze zurueck
     *
     * @param obereGrenze ist exklusiv
     * @return Zufallszahl
     */
    public static int getRandomInt(int obereGrenze) {
        return new Random().nextInt(obereGrenze);
    }

    /**
     * Gibt einen zufaelligen boolean zurueck
     *
     * @return boolean
     */
    public static boolean getRandomBoolean() {
        return new Random().nextBoolean();
    }

    /**
     * Diese Methode druckt den als Parameter uebergebenen String in der Konsole aus
     *
     * @param ausgabetext
     */
    public static void stringAusgeben(String ausgabetext) {
        System.out.println(ausgabetext);
    }

    /**
     * Diese Methode liest basierend auf einer Nutzereingabe ein Integer ein.
     *
     * @return eingelesener int
     */
    public static int intEingabeEinlesen() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    /**
     * Diese Methode liest basierend auf einer Nutzereingabe einen String ein.
     *
     * @return eingelesene Zeichenkette
     */
    public static String stringEingabeEinlesen() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /**
     * Diese Methode liest ein Objekt aus der Datei mit dem uebergebenen Dateipfad.
     *
     * @param filename
     */
    public static Object ladeSpielAusDatei(String filename) {
        return ladeSpielAusDatei(new File(filename));
    }

    /**
     * Diese Methode liest ein Objekt aus der Datei mit dem uebergebenen Dateiobjekt.
     *
     * @param file
     */
    public static Object ladeSpielAusDatei(File file) {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            return objectInputStream.readObject();
        } catch (ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Diese Methode speichert das uebergebene Objekt in eine Datei mit dem uebergebenen Dateipfad.
     *
     * @param filename
     * @param object
     */
    public static void speichereSpielInDatei(String filename, Object object) {
        speichereSpielInDatei(new File(filename), object);
    }

    /**
     * Diese Methode speichert das uebergebene Objekt in eine Datei mit dem uebergebenen Dateiobjekt.
     *
     * @param file
     * @param object
     */
    public static void speichereSpielInDatei(File file, Object object) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);

            ObjectOutputStream objectOut = new ObjectOutputStream(fileOutputStream);

            objectOut.writeObject(object);

            objectOut.close();
            fileOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Diese Methode gibt eine Liste aller Dateien aus dem uebergebenen Ordner zurueck.
     *
     * @param directory
     * @return Liste der Dateien
     */
    public static List<File> listFiles(String directory) {
        File file = new File(directory);
        File[] fileList = file.listFiles();
        if (fileList != null) {
            return Arrays.asList(fileList);
        } else {
            return new ArrayList<>();
        }
    }
}
