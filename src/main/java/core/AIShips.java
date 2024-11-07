package core;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class AIShips {
    private Grid grid;
    private List<Ship> ships;
    private int[] shipSizes;

    public AIShips(Grid grid, int[] shipSizes) {
        this.grid = grid;
        this.shipSizes = shipSizes;
        this.ships = new ArrayList<>();
        this.checkShipSizes();
    }

    public void setShips() {
        int current_ship = 0;
        while (current_ship != shipSizes.length) {
            Ship newShip = getShip(shipSizes[current_ship]);
            if (newShip.isOnGrid(grid)) {
                if (!conflicts(newShip)) {
                    ships.add(newShip);
                    current_ship += 1;
                }
            }
        }
    }

    private void checkShipSizes() {
        for (int i = 0; i < shipSizes.length; i++) {
            if (shipSizes[i] > grid.numRows() || shipSizes[i] > grid.numCols()) {
                throw new RuntimeException("INVALID SHIP SIZES GIVEN");
            }
        }
    }

    private Ship getShip(int shipsSize) {
        Random random = new Random();
        int row = getRow(random);
        int col = getCol(random);
        Ship.Direction direction = getDirection(random);
        return new Ship(new Coord(row, col), shipsSize, direction, "");
    }

    private int getRow(Random random) {
        return random.nextInt(0, grid.numRows());
    }

    private int getCol(Random random) {
        return random.nextInt(0, grid.numCols());
    }

    private static Ship.Direction getDirection(Random random) {
        return random.nextBoolean() ? Ship.Direction.VERTICAL : Ship.Direction.HORIZONTAL;
    }

    private boolean conflicts(Ship newShip) {
        for (Ship ship : ships) {
            if (newShip.isOverlapping(ship)) {
                return true;
            }
        }
        return false;
    }

    public int getShipCount() {
        return ships.size();
    }

    public List<Ship> getShips() {
        return ships;
    }
}