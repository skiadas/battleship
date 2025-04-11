package core;

import core.state.Action;
import java.util.Map;

public abstract class Presenter {
    protected Driver driver;

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public abstract void displayMessage(String s);

    public abstract void displayGrid(Grid g);

    public abstract Coord askForCoordinate(Grid g);

    public abstract void displayOptions(String prompt, Map<String, Runnable> choices);

    /**
     * Display a set of {@link core.state.Action} options
     *
     * @param prompt the prompt to show
     * @param choices the possible action choices indexed by the trigger word
     */
    public abstract void displayOptionsActions(String prompt, Map<String, Action> choices);

    public abstract void displayGame(Game game);

    /**
     * Prompts user for a game action (start, stop, or coordinate)
     *
     * @return an Action representing user intent
     */
    public abstract Action askForGameAction();
}
