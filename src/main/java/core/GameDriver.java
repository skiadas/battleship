package core;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class GameDriver {
    private final Presenter presenter;

    public GameDriver(Presenter presenter) {
        this.presenter = presenter;
    }

    public void start() {
        presenter.displayMessage("Welcome to Battleship!");
        Map<String, Runnable> gameOptions = new HashMap<>();
        gameOptions.put("start", this::startGame);
        gameOptions.put("stop", this::stopGame);
        presenter.displayOptions("Choose an action: start or stop", gameOptions);
    }

    private void startGame() {
        presenter.displayMessage("Game is starting...");
        // User input for grid size
        Game game =
                new Game(
                        DefaultGridBuilder.defaultGrid(),
                        DefaultGridBuilder
                                .defaultGrid()); // Temporary default grids for both players
        while (!game.isOver()) {
            presenter.displayGame(game);
            presenter.displayMessage("Insert a coordinate to shoot!");
            Coord playerInputCoord = presenter.askForCoordinate(game.getEnemyGrid());
            game.shoot(playerInputCoord);
            reportIfShipSunk(game, playerInputCoord);
            game.next();
        }
        displayWinner(game);
    }

    private void displayWinner(Game game) {
        if (game.getCurrent() == Game.Player.FIRST)
            presenter.displayMessage("Game is over! Winner is Player");
        else presenter.displayMessage("Game is over! Winner is Enemy");
    }

    private void reportIfShipSunk(Game game, Coord playerInputCoord) {
        Optional<Ship> currShip = game.getEnemyGrid().isShipSunk(playerInputCoord, true);
        if (currShip.isPresent()) {
            presenter.displayMessage("You sunk your opponents " + currShip.get().getName() + "!");
        }
    }

    private void stopGame() {
        presenter.displayMessage("Game is stopping...");
    }
}
