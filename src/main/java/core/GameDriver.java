package core;

import java.util.HashMap;
import java.util.Map;

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
        Grid grid = Grid.defaultGrid(); // Temporary grid
        while (!grid.allShipsAreSunk()) {
            presenter.displayGrid(grid);
            presenter.displayMessage("Insert a coordinate to shoot!");
            Coord playerInputCoord = presenter.askForCoordinate(grid);
            grid.shoot(playerInputCoord);
        }
    }

    private void stopGame() {
        presenter.displayMessage("Game is stopping...");
    }
}
