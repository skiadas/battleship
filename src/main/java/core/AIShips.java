package core;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class AIShips {
    private final Grid grid;
    private final List<Ship> ships;
    private final ShipSpec[] shipSpecs;

    public AIShips(final Grid grid, final ShipSpec... shipSpecs) {
        this.grid = grid;
        this.shipSpecs = shipSpecs;
        this.ships = new ArrayList<>();
        this.checkShipSizes();
    }

    public void setShips() {
        final int MAX_NUMBER_OF_TRIES = 10;
        int currentShip = 0;
        int tries = 0;
        while (currentShip != shipSpecs.length) {
            final Ship newShip = getShip(shipSpecs[currentShip]);
            if (grid.isShipOnGrid(newShip)) {
                if (!conflicts(newShip)) {
                    ships.add(newShip);
                    currentShip += 1;
                    tries = 0;
                }
            }
            tries++;
            if (tries == MAX_NUMBER_OF_TRIES) {
                currentShip = 0;
                tries = 0;
                ships.clear();
            }
        }
    }

    @SuppressWarnings("PMD.AvoidThrowingRawExceptionTypes")
    private void checkShipSizes() {
        for (ShipSpec shipSpec : shipSpecs) {
            if (shipSpec.size > grid.numRows() || shipSpec.size > grid.numCols()) {
                throw new RuntimeException("INVALID SHIP SIZES GIVEN");
            }
        }
    }

    private Ship getShip(final ShipSpec shipSpec) {
        Random random = new Random();
        int row = getRow(random);
        int col = getCol(random);
        Ship.Direction direction = getDirection(random);
        return new Ship(new Coord(row, col), shipSpec.size, direction, shipSpec.name);
    }

    private int getRow(final Random random) {
        return random.nextInt(0, grid.numRows());
    }

    private int getCol(final Random random) {
        return random.nextInt(0, grid.numCols());
    }

    private static Ship.Direction getDirection(final Random random) {
        return random.nextBoolean() ? Ship.Direction.VERTICAL : Ship.Direction.HORIZONTAL;
    }

    private boolean conflicts(Ship newShip) {
        for (final Ship ship : ships) {
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
