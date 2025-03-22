package core;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a ship object for the {@link Grid} class, Describes the different directions it can
 * face, Describes the length of the ship Has the starting coordinate of the ship, Helps identify
 * the name of different ships,
 */
public class Ship {

    public enum Direction {
        HORIZONTAL,
        VERTICAL
    }

    /** The starting coordinate of the ship */
    private final Coord startcoordinate;

    /** Length of the Ship object */
    private final int size;

    /** the directions its facing like horizontal and vertical */
    private final Direction direction;

    /** Name of the ship e.g. Battleship */
    private final String name;

    /** The coordinates of the whole ship */
    private final List<Coord> coordList;

    public Ship(Coord coordinate, int size, Direction direction, String name) {
        this.startcoordinate = coordinate;
        this.size = size;
        this.direction = direction;
        this.name = name;
        this.coordList = genCoordList();
    }

    public int getSize() {
        return size;
    }

    public int getStartRow() {
        return startcoordinate.row;
    }

    public int getStartCol() {
        return startcoordinate.col;
    }

    public Direction getDirection() {
        return direction;
    }

    public String getName() {
        return name;
    }

    public boolean containsCoord(Coord coord) {
        for (Coord c : coordList) {
            if (coord.isEqual(c)) {
                return true;
            }
        }
        return false;
    }

    private List<Coord> genCoordList() {
        List<Coord> coordList = new ArrayList<Coord>();
        if (direction == Direction.HORIZONTAL) {
            for (int i = 0; i < this.size; i++) {
                coordList.add(startcoordinate.shiftBy(0, i));
            }
        } else {
            for (int i = 0; i < this.size; i++) {
                coordList.add(startcoordinate.shiftBy(i, 0));
            }
        }
        return coordList;
    }

    public List<Coord> getCoordList() {
        return coordList;
    }

    public boolean isOverlapping(Ship other) {
        for (Coord coord : other.getCoordList()) {
            if (this.containsCoord(coord)) {
                return true;
            }
        }
        return false;
    }

    /**
     * checks if ship is on grid
     *
     * @return true if ship is on grid, false if not
     */
    public boolean isWithinBounds(Bounding bounding) {
        for (Coord coord : this.coordList) {
            if (coord.row < 1 || coord.row > bounding.numRows()) return false;
            if (coord.col < 1 || coord.col > bounding.numCols()) return false;
        }
        return true;
    }
}
