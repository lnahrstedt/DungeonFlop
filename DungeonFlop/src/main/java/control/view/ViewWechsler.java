package control.view;

import model.raum.Haendlerraum;
import model.raum.Schatzraum;
import model.raum.Schmiedraum;
import view.*;

/**
 * Diese Klasse stellt Methoden zum veraendern des Views bereit.
 */
public class ViewWechsler {

    /**
     * Wechsel zu StartView
     */
    public static void wechselZuStartView() {

        Buehne.getInstance().setRootOfScene(new StartView());
    }

    /**
     * Wechsel zu CreditsView
     */
    public static void wechselZuCreditsView() {
        Buehne.getInstance().setRootOfScene(new CreditsView());
        ChatViewController.wennChatOffenLassOffen();
    }

    /**
     * Wechsel zu EbenenLayoutView
     */
    public static void wechselZuEbenenLayoutView() {
        Buehne.getInstance().setRootOfScene(new EbenenLayoutView());
        ChatViewController.wennChatOffenLassOffen();
    }

    /**
     * Wechsel zu EbenenLayoutViewServerInteragiert
     *
     * @param text
     */
    public static void wechselEbenenLayoutViewServerInteragiert(String text) {
        Buehne.getInstance().setRootOfScene(new EbenenLayoutViewServerInteragiert(text));
        ChatViewController.wennChatOffenLassOffen();
    }

    /**
     * Wechsel zu StufenaufstiegView
     */
    public static void wechselZuStufenaufstiegView() {
        Buehne.getInstance().setRootOfScene(new StufenaufstiegView());
        ChatViewController.wennChatOffenLassOffen();
    }

    /**
     * Wechsel zu InventarView
     */
    public static void wechselZuInventarView() {
        Buehne.getInstance().setRootOfScene(new InventarView());
        ChatViewController.wennChatOffenLassOffen();
    }

    /**
     * Wechsel zu GegnerraumView
     */
    public static void wechselZuGegnerraumView() {
        Buehne.getInstance().setRootOfScene(new GegnerraumView());
        ChatViewController.wennChatOffenLassOffen();
    }

    /**
     * Wechsel zu ChatView
     */
    public static void wechselZuChatView() {
        Buehne.getInstance().setRootOfScene(new ChatView());
    }

    /**
     * Wechsel zu GegnerraumView mit Zurueckbuttonunterstuetzung
     */
    public static void wechselZuGegnerraumViewMitZurueckOption() {
        Buehne.getInstance().setRootMitZurueckStack(new GegnerraumView());
        ChatViewController.wennChatOffenLassOffen();
    }

    /**
     * Wechsel zu HaendlerraumView mit Zurueckbuttonunterstuetzung
     *
     * @param haendlerraum
     */
    public static void wechselZuHaendlerraumViewMitZurueckOption(Haendlerraum haendlerraum) {
        Buehne.getInstance().setRootMitZurueckStack(new HaendlerraumView(haendlerraum));
        ChatViewController.wennChatOffenLassOffen();
    }

    /**
     * Wechsel zu SchatzraumView mit Zurueckbuttonunterstuetzung
     *
     * @param schatzraum
     */
    public static void wechselZuSchatzraumViewMitZurueckOption(Schatzraum schatzraum) {
        Buehne.getInstance().setRootMitZurueckStack(new SchatzraumView(schatzraum));
        ChatViewController.wennChatOffenLassOffen();
    }

    /**
     * Wechsel zu SchmiedraumView mit Zurueckbuttonunterstuetzung
     *
     * @param schmiedraum
     */
    public static void wechselZuSchmiedraumViewMitZurueckOption(Schmiedraum schmiedraum) {
        Buehne.getInstance().setRootMitZurueckStack(new SchmiedraumView(schmiedraum));
        ChatViewController.wennChatOffenLassOffen();
    }

    /**
     * Wechsel zu DeckView mit Zurueckbuttonunterstuetzung
     */
    public static void wechselZuDeckViewMitZurueckOption() {
        Buehne.getInstance().setRootMitZurueckStack(new DeckView());
        ChatViewController.wennChatOffenLassOffen();
    }

    /**
     * Wechsel zu NeuesSpielView mit Zurueckbuttonunterstuetzung
     */
    public static void wechselZuNeuesSpielViewMitZurueckOption() {
        Buehne.getInstance().setRootMitZurueckStack(new NeuesSpielView());
        ChatViewController.wennChatOffenLassOffen();
    }

    /**
     * Wechsel zu SpielLadenView mit Zurueckbuttonunterstuetzung
     */
    public static void wechselZuSpielLadenViewMitZurueckOption() {
        Buehne.getInstance().setRootMitZurueckStack(new SpielLadenView());
        ChatViewController.wennChatOffenLassOffen();
    }

    /**
     * Wechsel zu SpieleinstellungenView mit Zurueckbuttonunterstuetzung
     */
    public static void wechselZuSpieleinstellungenViewMitZurueckOption() {
        Buehne.getInstance().setRootMitZurueckStack(new SpieleinstellungenView());
        ChatViewController.wennChatOffenLassOffen();
    }

    /**
     * Wechsel zu InventarView mit Zurueckbuttonunterstuetzung
     */
    public static void wechselZuInventarViewMitZurueckOption() {
        Buehne.getInstance().setRootMitZurueckStack(new InventarView());
        ChatViewController.wennChatOffenLassOffen();
    }

    /**
     * Wechsel zu StoryView mit Zurueckbuttonunterstuetzung
     */
    public static void wechselZuStoryViewMitZurueckOption() {
        Buehne.getInstance().setRootMitZurueckStack(new StoryView());
        ChatViewController.wennChatOffenLassOffen();
    }
}