package core;

import core.state.Action;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PresenterDouble extends Presenter {
    List<String> displayedMessages = new ArrayList<>();

    @Override
    public void displayMessage(String s) {
        displayedMessages.add(s);
    }

    @Override
    public void displayGrid(Grid g) {
        // TODO
    }

    @Override
    public void displayGame(Game game) {
        // TODO
    }

    @Override
    public Coord askForCoordinate(Grid g) {
        return null;
    }

    @Override
    public void displayOptions(String prompt, Map<String, Runnable> choices) {
        // simulate choice selection if needed
    }

    @Override
    public Action askForGameAction(Grid g) {
        // Return null for now
        return null;
    }
}
