package control.model;

import control.view.ViewWechsler;
import model.HauptSpieler;
import model.Spielfigur;
import model.Spielwelt;
import resources.interfaces.Zahlensammlung;

public class HauptspielerController {

    public static void aufleveln() {
        HauptSpieler protagonist = Spielwelt.getInstance().getProtagonist();
        Spielfigur protagonistSpielfigur = protagonist.getSpielfigur();

        protagonistSpielfigur.setLebenspunkte(protagonistSpielfigur.getLebenspunkte() + Zahlensammlung.LEVELAUFSTIEG_LEBENSPUNKTE);
        protagonistSpielfigur.setAngriffspunkte(protagonistSpielfigur.getAngriffspunkte() + Zahlensammlung.LEVELAUFSTIEG_ANGRIFFSPUNKTE);

        protagonist.setErfahrungsPunkte(protagonist.getErfahrungsPunkte() - protagonist.getLevel() * Zahlensammlung.ERFAHRUNGSKOSTENFAKTOR);
        protagonist.setLevel(protagonist.getLevel() + Zahlensammlung.LEVELAUFSTIEG);

        ViewWechsler.wechselZuStufenaufstiegView();
    }
}
