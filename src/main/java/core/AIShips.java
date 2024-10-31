package core;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class AIShips {
    private final int gridRows;
    private final int gridCols;
    private List<Ship> ships;

    public AIShips(Grid grid) {
        this.gridRows = grid.numRows();
        this.gridCols = grid.numCols();
        this.ships = new ArrayList<>();
    }

    private void setShips(List<Integer> shipsSizes) {
        List<Ship> ships = new ArrayList<>();
        while (!shipsSizes.isEmpty()) {
            Ship newShip = getShip(shipsSizes);
            if (newShip.isOnGrid()) {
                if (!conflicts(ships, newShip)) {
                    ships.add(newShip);
                    shipsSizes.removeFirst();
                }
            }
        }
    }

    private Ship getShip(List<Integer> shipsSizes) {
        Random random = new Random();
        int row = getRow(random);
        int col = getCol(random);
        Ship.Direction direction = getDirection(random);
        Ship newShip = new Ship(row, col, shipsSizes.getFirst(), direction, "Hello");
        return newShip;
    }

    private int getRow(Random random) {
        return random.nextInt(0, gridRows);
    }

    private int getCol(Random random) {
        return random.nextInt(0, gridCols);
    }

    private static Ship.Direction getDirection(Random random) {
        return random.nextBoolean() ? Ship.Direction.VERTICAL : Ship.Direction.HORIZONTAL;
    }

    private boolean conflicts(List<Ship> ships, Ship newShip) {
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
