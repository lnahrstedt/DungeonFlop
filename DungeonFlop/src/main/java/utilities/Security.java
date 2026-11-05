package utilities;

import com.google.crypto.tink.Aead;
import com.google.crypto.tink.CleartextKeysetHandle;
import com.google.crypto.tink.JsonKeysetReader;
import com.google.crypto.tink.KeysetHandle;
import com.google.crypto.tink.aead.AeadConfig;
import control.view.AlertFabrik;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import resources.interfaces.PfadSammlung;
import resources.interfaces.StringsammlungAlert;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;

/**
 * Diese Klasse stellt Methoden zur Verfuegung, die das ver- und entschluesseln der Chatnachrichten ermoeglichen.
 */
public class Security {

    private KeysetHandle handle;
    private Aead aead;

    public Security() {
        init();
    }

    /**
     * Diese Methode initialisiert alle notwendigen Komponenten.
     */
    private void init() {
        try {
            AeadConfig.register();
            this.handle = CleartextKeysetHandle.read(JsonKeysetReader.withFile(new File(PfadSammlung.PFAD_KEY)));
            this.aead = handle.getPrimitive(Aead.class);
        } catch (GeneralSecurityException | IOException e) {
            zeigeSicherheitsfehlerAlert();
            e.printStackTrace();
        }
    }

    /**
     * Diese Methode verschluesselt die Nachricht.
     *
     * @param string
     * @return geheimnachricht
     */
    public byte[] verschluessel(String string) {
        byte[] geheimnachricht = null;

        try {
            geheimnachricht = aead.encrypt(string.getBytes(StandardCharsets.UTF_8), null);
        } catch (GeneralSecurityException e) {
            zeigeSicherheitsfehlerAlert();
            e.printStackTrace();
        }
        System.out.println(geheimnachricht);
        return geheimnachricht;
    }

    /**
     * Diese Methode entschluesselt die Nachricht.
     *
     * @param bytes
     * @return nachricht
     */
    public String entschluessel(byte[] bytes) {

        String nachricht = null;
        try {
            nachricht = new String(aead.decrypt(bytes, null));
        } catch (GeneralSecurityException e) {
            zeigeSicherheitsfehlerAlert();
            e.printStackTrace();
        }
        return nachricht;
    }

    /**
     * Diese Methode stellt einen Alert bereit, der den Nutzer ueber einen unerwarteten Sicherheitsfehler informiert.
     */
    private void zeigeSicherheitsfehlerAlert() {
        Platform.runLater(() -> AlertFabrik.generiereAlert(Alert.AlertType.ERROR, StringsammlungAlert.SICHERHEITSFEHLER_TITEL, StringsammlungAlert.SICHERHEITSFEHLER_NACHRICHT));
    }
}
