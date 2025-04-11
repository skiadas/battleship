package core.state;

import core.Presenter;

public class Terminated extends GameState {
    public Terminated(Presenter presenter) {
        super(presenter);
        presenter.displayMessage("Goodbye!");
    }

    @Override
    GameState actOn(Action action) {
        return null;
    }
}
