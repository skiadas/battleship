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
        if (action instanceof Start) return new RunningState(presenter);
        if (action instanceof Stop) return new Terminated(presenter);
        throw new RuntimeException("Should not select coords on main menu");
    }
}
