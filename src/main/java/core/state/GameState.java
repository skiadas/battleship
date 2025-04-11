package core.state;

import core.Presenter;

public abstract class GameState {

    protected final Presenter presenter;

    public GameState(Presenter presenter) {
        this.presenter = presenter;
    }

    abstract GameState actOn(Action action);
}
