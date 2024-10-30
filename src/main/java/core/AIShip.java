package core;

import java.util.*;

class AIShips {

    private int gridWidth;
    private int gridHeight;
    private List<Ship> ships;
    private int[][] grid;

    public AIShips(int gridWidth, int gridHeight) {
        this.gridWidth = gridWidth;
        this.gridHeight = gridHeight;
        this.ships = new ArrayList<>();
        this.grid = new int[gridWidth][gridHeight];
    }

    public boolean addShip(Ship ship) {
        if (isWithinGrid(ship) && !isOverlapping(ship)) {
            placeShipOnGrid(ship);
            ships.add(ship);
            return true;
        }
        return false;
    }

    private boolean isWithinGrid(Ship ship) {
        int x = ship.getStartCol();
        int y = ship.getStartRow();
        int size = ship.getSize();
        Ship.Direction direction = ship.getDirection();

        if (direction == Ship.Direction.HORIZONTAL) {
            return x + size <= gridWidth;
        } else if (direction == Ship.Direction.VERTICAL) {
            return y + size <= gridHeight;
        }
        return false;
    }

    private boolean isOverlapping(Ship ship) {
        int x = ship.getStartCol();
        int y = ship.getStartRow();
        int size = ship.getSize();
        Ship.Direction direction = ship.getDirection();

        for (int i = 0; i < size; i++) {
            if (direction == Ship.Direction.HORIZONTAL) {
                if (grid[x + i][y] == 1) {
                    return true;
                }
            } else if (direction == Ship.Direction.VERTICAL) {
                if (grid[x][y + i] == 1) {
                    return true;
                }
            }
        }
        return false;
    }


    private void placeShipOnGrid(Ship ship) {
        int x = ship.getStartCol();
        int y = ship.getStartRow();
        int size = ship.getSize();
        Ship.Direction direction = ship.getDirection();

        for (int i = 0; i < size; i++) {
            if (direction == Ship.Direction.HORIZONTAL) {
                grid[x + i][y] = 1;
            } else if (direction == Ship.Direction.VERTICAL) {
                grid[x][y + i] = 1;
            }
        }
    }

    public int getShipCount() {
        return ships.size();
    }

    public List<Ship> getShips() {
        return ships;
    }

    public void setShips(List<Integer> sizes) {
        Collections.sort(sizes, Comparator.reverseOrder());
        List<Ship> ships = List.of();
        Random random = new Random();
        while (!sizes.isEmpty()) {
            int length = sizes.get(0);
            int row = random.nextInt(gridWidth);
            int col = random.nextInt(gridHeight);
            Ship.Direction direction = random.nextBoolean() ? Ship.Direction.VERTICAL : Ship.Direction.HORIZONTAL;
            Ship newShip = new Ship(row, col, length, direction, "Hello");
            if (isInGrid(newShip)) {
                if (!conflicts(ships, newShip)) {
                    ships.add(newShip);
                    sizes.remove(0);
                }
            }
        }
    }

    private static boolean isInGrid(Ship ship) { //Needs Implementation
        return true;
    }

    private boolean conflicts(List<Ship> ships, Ship newShip) { //Replace ship.equals with checking its Grid Spots
        for (Ship ship : ships) {
            if (ship.equals(newShip)) {
                return true;
            }
        }
        return false;
    }
}