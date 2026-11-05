package utilities;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import resources.interfaces.PfadSammlung;
import resources.interfaces.Zahlensammlung;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Diese Klasse steuert die Wiedergabe und Lautstaerke der Hintergrundmusik und der verschiedenen Soundeffekte.
 */
public class MusikUndSoundController {

    private static MediaPlayer musikPlayer;
    private static MediaPlayer soundPlayer;
    private static double volumeMusik = Zahlensammlung.VOLUME_MUSIK_START;
    private static double volumeSounds = Zahlensammlung.VOLUME_SOUNDS_START;

    private MusikUndSoundController() {
    }

    /**
     * Diese Methode ermoeglicht das Abspielen eines Soundeffekts.
     *
     * @param string Dateipfad der Sounddatei
     */
    public static void spieleSoundeffekt(String string) {
        Media sound = new Media(Paths.get(string).toUri().toString());
        soundPlayer = new MediaPlayer(sound);
        soundPlayer.setVolume(getVolumeSounds());
        soundPlayer.play();
    }

    /**
     * Diese Methode initiiert die zwei MediaPlayer fuer Musik und Sounds.
     */
    public static void initMediaPlayer() {

        Path path = Paths.get(PfadSammlung.PFAD_NOTIFICATION_SOUNDEFFEKT);
        musikPlayer = new MediaPlayer(new Media(path.toUri().toString()));
        musikPlayer.setVolume(Zahlensammlung.NULL);
        musikPlayer.play();
        soundPlayer = new MediaPlayer(new Media(path.toUri().toString()));
        soundPlayer.setVolume(Zahlensammlung.NULL);
        soundPlayer.play();
    }

    /**
     * Diese Methode startet die Hintergrundmusik in Schleife.
     */
    public static void spieleHintergrundMusik() {
        Media musik = new Media(Paths.get(PfadSammlung.PFAD_HINTERGRUNDMUSIK).toUri().toString());
        musikPlayer = new MediaPlayer(musik);
        musikPlayer.setVolume(getVolumeMusik());
        musikPlayer.play();
        musikPlayer.setCycleCount(MediaPlayer.INDEFINITE);
    }

    /**
     * Diese Methode aendert die Lautstaerke der Musik auf den uebergebenen Wert
     *
     * @param volume zwischen 0.0 und 1.0
     */
    private static void aendereVolumeDerMusik(double volume) {
        musikPlayer.setVolume(volume);
    }

    /**
     * Diese Methode aendert die Lautstaerke der Soundeffekte auf den uebergebenen Wert
     *
     * @param volume zwischen 0.0 und 1.0
     */
    private static void aendereVolumeDerSounds(double volume) {
        soundPlayer.setVolume(volume);
    }

    /**
     * Diese Methode ist der Getter fuer die Klassenvariable volumeMusik.
     *
     * @return volumeMusik zwischen 0.0 und 1.0
     */
    public static double getVolumeMusik() {
        return volumeMusik;
    }

    /**
     * Diese Methode ist der Setter fuer die Klassenvariable volumeMusik.
     *
     * @param volumeMusik zwischen 0.0 und 1.0
     */
    public static void setVolumeMusik(double volumeMusik) {
        MusikUndSoundController.volumeMusik = volumeMusik;
        aendereVolumeDerMusik(volumeMusik);
    }

    /**
     * Diese Methode ist der Getter fuer die Klassenvariable volumeSounds.
     *
     * @return volumeSounds zwischen 0.0 und 1.0
     */
    public static double getVolumeSounds() {
        return volumeSounds;
    }

    /**
     * Diese Methode ist der Setter fuer die Klassenvariable volumeSounds.
     *
     * @param volumeSounds zwischen 0.0 und 1.0
     */
    public static void setVolumeSounds(double volumeSounds) {
        MusikUndSoundController.volumeSounds = volumeSounds;
        aendereVolumeDerSounds(volumeSounds);
    }
}
