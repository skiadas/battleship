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
        Grid grid = new Grid(5, 5); // Temporary grid
        presenter.displayGrid(grid);

        /*
        presenter.displayMessage("Insert a coordinate to shoot!");

        while(true) { // infinite loop
            Coord playerInputCoord = presenter.askForCoordinate(grid);
            if(grid.isValid(playerInputCoord)) {
                grid.get(playerInputCoord);
            }
        }

        // Place ships (user and AI)
        // User Shoots then AI shoots

        // loop as long as there are still ships that are above water ask user what coordinate,
        // hit that coordinate then you have to know if that's a hit or miss

        */

    }

    private void stopGame() {
        presenter.displayMessage("Game is stopping...");
    }
}
