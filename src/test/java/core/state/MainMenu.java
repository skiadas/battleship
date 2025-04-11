package core.state;

import core.Presenter;
import java.util.Map;

public class MainMenu extends GameState {
    public MainMenu(Presenter presenter) {
        super(presenter);
        presenter.displayOptionsActions(
                "Choose an action: start or stop",
                Map.of("start", new Start(), "stop", new Stop()));
    }

    @Override
    GameState actOn(Action action) {
        return null;
    }
}
