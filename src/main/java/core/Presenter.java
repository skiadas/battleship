package core;

import java.util.Map;
import core.state.Action;

public abstract class Presenter {
    protected Driver driver;

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public abstract void displayMessage(String s);

    public abstract void displayGrid(Grid g);

    public abstract Coord askForCoordinate(Grid g);

    public abstract void displayOptions(String prompt, Map<String, Runnable> choices);

    public abstract void displayGame(Game game);

    public abstract Action askForGameAction(Grid g);
}
