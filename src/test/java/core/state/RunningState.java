package core.state;

import core.DefaultGridBuilder;
import core.Game;
import core.Presenter;

public class RunningState extends GameState {
    private Game game;

    public RunningState(Presenter presenter) {
        super(presenter);
        game = new Game(DefaultGridBuilder.defaultGrid(), DefaultGridBuilder.defaultGrid());
        presenter.displayMessage("Game is starting...");
    }

    @Override
    GameState actOn(Action action) {
        return null;
    }
}
