package core;

import core.state.Action;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class GameDriver implements Driver {
    private final Presenter presenter;

    public GameDriver(Presenter presenter) {
        this.presenter = presenter;
    }

    public void start() {
        presenter.setDriver(this);
        presenter.displayMessage("Welcome to Battleship!");
        Map<String, Runnable> gameOptions = new HashMap<>();
        gameOptions.put("start", this::startGame);
        gameOptions.put("stop", this::stopGame);
        presenter.displayOptions("Choose an action: start or stop", gameOptions);
    }

    private void startGame() {
        presenter.displayMessage("Game is starting...");
        Game game = new Game(DefaultGridBuilder.defaultGrid(), DefaultGridBuilder.defaultGrid());

        while (!game.isOver()) {
            presenter.displayGame(game);
            presenter.displayMessage("Insert a coordinate to shoot or type stop.");
            Coord playerInputCoord = presenter.askForCoordinate(game.getEnemyGrid());
            game.shoot(playerInputCoord);
            reportIfShipSunk(game, playerInputCoord);
            game.next();
        }

        displayWinner(game);
    }

    private void displayWinner(Game game) {
        if (game.getCurrent() == Game.Player.FIRST)
            presenter.displayMessage("Game is over! Winner is Player 1");
        else presenter.displayMessage("Game is over! Winner is Player 2");
    }

    private void reportIfShipSunk(Game game, Coord playerInputCoord) {
        game.getEnemyGrid()
                .getSunkShipAt(playerInputCoord)
                .ifPresent(ship -> presenter.displayMessage(
                        "You sunk your opponent's " + ship.getName() + "!"
                ));
    }


    private void stopGame() {
        presenter.displayMessage("Game is stopping...");
    }

    @Override
    public void act(Action action) {}
}
