package core;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class AIShips {
    private Grid grid;
    private List<Ship> ships;
    private List<Integer> shipSizes;

    public AIShips(Grid grid, List<Integer> shipSizes) {
        this.grid = grid;
        this.shipSizes = shipSizes;
        this.ships = new ArrayList<>();
    }

    public void setShips() {
        this.checkShipSizes();
        while (!shipSizes.isEmpty()) {
            Ship newShip = getShip(shipSizes.getFirst());
            if (newShip.isOnGrid(grid)) {
                if (!conflicts(newShip)) {
                    ships.add(newShip);
                    shipSizes.remove(0);
                }
            }
        }
    }

    private void checkShipSizes(){
        for (int i = 0; i < shipSizes.size(); i++){
            if (shipSizes.get(i) > grid.numRows() || shipSizes.get(i) > grid.numCols()){
                throw new RuntimeException("INVALID SHIP SIZES GIVEN");
            }
        }
    }

    private Ship getShip(int shipsSize) {
        Random random = new Random();
        int row = getRow(random);
        int col = getCol(random);
        Ship.Direction direction = getDirection(random);
        return new Ship(row, col, shipsSize, direction, "");
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
