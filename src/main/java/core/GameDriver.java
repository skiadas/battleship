package core;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameDriver {
    private final Presenter presenter;

    public GameDriver(Presenter presenter) {
        this.presenter = presenter;
    }

    public void start() {
        presenter.displayMessage("Welcome to Battleship!");
        presenter.displayMessage("Choose an action: start, instructions, back, setup, or stop");
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            String input = scanner.nextLine().toLowerCase();
            switch (input) {
                case "start":
                    startGame();
                    break;
                case "instructions":
                    displayInstructions();
                    break;
                case "back":
                    goBackToMenu();
                    break;
                case "setup":
                    setupShips();
                    break;
                case "stop":
                    stopGame();
                    running = false;
                    break;
                default:
                    presenter.displayMessage("Invalid input. Please try again.");
            }
        }
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
            reportIfShipSunk(grid, playerInputCoord);
        }
    }

    private void reportIfShipSunk(Grid grid, Coord playerInputCoord) {
        for (Ship ship : grid.getShipList()) {
            for (Coord c : ship.getCoordList()) {
                if (c.isEqual(playerInputCoord) && grid.isShipSunk(ship)) {
                    presenter.displayMessage("You sunk your opponents " + ship.getName() + "!");
                }
            }
        }
    }

    private void displayInstructions() {
        presenter.displayMessage(
                "Instructions: Place your ships carefully to sink all enemy ships.");
    }

    private void goBackToMenu() {
        presenter.displayMessage("Returning to the main menu...");
        start();
    }

    private void stopGame() {
        presenter.displayMessage("Game is stopping...");
    }

    private void setupShips() {
        presenter.displayMessage("Setup your ships before the game begins");
        Scanner scanner = new Scanner(System.in);

        Grid grid = new Grid(10, 10); //
        List<Ship> placedShips = new ArrayList<>();

        boolean setupComplete = false;

        while (!setupComplete) {
            try {
                // it will ask for ship size
                presenter.displayMessage("Enter ship size (2, 3, 4, 5, 6):");
                int shipSize = scanner.nextInt();

                // Ask for starting coordinate
                presenter.displayMessage("Enter starting coordinate (e.g., A1):");
                scanner.nextLine(); // Consume newline
                String startCoordInput = scanner.nextLine();
                Coord startCoord = new Coord(startCoordInput);

                // it will ask for direction
                presenter.displayMessage("Enter direction (horizontal/vertical):");
                String directionInput = scanner.nextLine().toUpperCase();
                Ship.Direction direction = Ship.Direction.valueOf(directionInput);

                // It will create  the ship
                Ship newShip = new Ship(startCoord, shipSize, direction, "CustomShip");

                //  it will validate placement
                if (!newShip.isOnGrid(grid)) {
                    presenter.displayMessage("Error: Ship is out of grid bounds. Try again.");
                    continue;
                }
                boolean overlaps = false;
                for (Ship placedShip : placedShips) {
                    if (newShip.isOverlapping(placedShip)) {
                        overlaps = true;
                        break;
                    }
                }
                if (overlaps) {
                    presenter.displayMessage(
                            "Error: Ship overlaps with an existing ship. Try again.");
                    continue;
                }

                // it will add ship to the grid and list of placed ships
                placedShips.add(newShip);
                grid.getShipList().add(newShip); // Assuming `getShipList()` is mutable
                grid = new Grid(grid.numRows(), grid.numCols(), placedShips); // Refresh the grid

                presenter.displayMessage("Ship placed successfully!");

                // Ask if user wants to place another ship
                presenter.displayMessage("Do you want to place another ship? (yes/no):");
                String response = scanner.next();
                setupComplete = response.equalsIgnoreCase("no");
            } catch (IllegalArgumentException e) {
                presenter.displayMessage("Invalid input. Please try again.");
            }
        }
    }
}
