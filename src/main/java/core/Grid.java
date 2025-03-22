package core;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/** Grid is the cells marked as rows and cols. Adds ship functionality. */
public class Grid implements Bounding {

    /** The cells in a given horizontal line */
    private final int rows;

    /** The cells in a given vertical line */
    private final int cols;

    /** The individual cell in a group of cells */
    private Cell[][] cells;

    /** Holds the list of different ships */
    private final List<Ship> shipList = new ArrayList<>();

    /**
     * creates the grid given the 3 parameters
     *
     * @param rows are cells on a vertical line
     * @param cols are cells on a horizontal line
     * @param shipList is a list of different ships
     */
    public Grid(final int rows, final int cols, final List<Ship> shipList) {

        this.rows = rows;
        this.cols = cols;

        cells = new Cell[rows][cols];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                cells[row][col] = new Cell();
            }
        }

        this.shipList.addAll(shipList);
    }

    /** sets the row and col */
    public Grid(final int rows, final int cols) {
        this(rows, cols, new ArrayList<>());
    }

    private Cell getCell(Coord coordinate) {
        int row = coordinate.row - 1;
        int col = coordinate.col - 1;
        return cells[row][col];
    }

    /**
     * gets the status of the cell
     *
     * @return whether the cell has been hit
     */
    public CellStatus getStatus(final Coord coordinate) {
        final Cell cell = getCell(coordinate);
        for (final Ship ship : shipList) {
            if (ship.containsCoord(coordinate)) {
                return cell.hasBeenShot() ? CellStatus.ShipHit : CellStatus.ShipUnrevealed;
            }
        }
        return cell.hasBeenShot() ? CellStatus.Empty : CellStatus.Unknown;
    }

    /**
     * the number of rows in the grid
     *
     * @return the number of rows
     */
    public int numRows() {
        return rows;
    }

    /**
     * the number of cols in the grid
     *
     * @return the number of cols
     */
    public int numCols() {
        return cols;
    }

    /** adds ship to list */
    public void addShip(final Ship ship) {
        shipList.add(ship);
    }

    /**
     * checks if all the ships are sunk
     *
     * @return boolean answer for check
     */
    public boolean allShipsAreSunk() {
        for (final Ship ship : shipList) {
            final List<Coord> coords = ship.getCoordList();
            for (final Coord coord : coords) {
                if (!this.getStatus(coord).equals(CellStatus.ShipHit)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * gets list of ships
     *
     * @return the list of ships
     */
    public List<Ship> getShipList() {
        return shipList;
    }

    /**
     * checks if ship is sunk
     *
     * @param ship is a ship in list of ships
     * @return boolean value for ship status
     */
    public boolean isShipSunk(final Ship ship) {
        for (final Coord coord : ship.getCoordList()) {
            if (!this.getStatus(coord).equals(CellStatus.ShipHit)) {
                return false;
            }
        }
        return true;
    }

    /**
     * checks if ship is on grid
     *
     * @return true if ship is on grid, false if not
     */
    public boolean isShipOnGrid(final Ship ship) {
        for (final Coord coord : ship.getCoordList()) {
            if (coord.row < 1 || coord.row > numRows()) return false;
            if (coord.col < 1 || coord.col > numCols()) return false;
        }
        return true;
    }

    public Optional<Ship> isShipSunk(Coord coordinate, Boolean onlyReturnSunk) {
        Optional<Ship> optionalShip = Optional.empty();
        for (Ship ship : this.shipList) {
            if (ship.containsCoord(coordinate)) {
                optionalShip = Optional.of(ship);
                break;
            }
        }
        if (optionalShip.isEmpty()) return optionalShip;
        for (final Coord coord : optionalShip.get().getCoordList()) {
            if (!this.getStatus(coord).equals(CellStatus.ShipHit)) {
                if (!onlyReturnSunk) {
                    return optionalShip;
                } else {
                    return Optional.empty();
                }
            }
        }
        return optionalShip;
    }

    /** changes status of given cell to shoot */
    public void shoot(final Coord coordinate) {
        final Cell shipCell = getCell(coordinate);
        final CellStatus targetStatus = getStatus(coordinate);
        if (!targetStatus.equals(CellStatus.ShipHit)) {
            shipCell.setAsShot();
        }
    }
}
