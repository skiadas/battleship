package core;

import java.util.ArrayList;
import java.util.List;

/**
 * Creates a ship object for the Grid class to use to place within its grid. ඞ
 *
 * @author Lily-Kate
 */
public class Ship {

    public enum Direction {
        HORIZONTAL,
        VERTICAL
    }

    /**
     * The starting coordinate within the grid that helps create the starting row, col and coord
     * list
     *
     * @author Lily Kate
     */
    private Coord startcoordinate;

    /**
     * This describes the length of the Ship object
     *
     * @author Lily Kate
     */
    private int size;

    /**
     * Used to create ships facing different directions Like horizontal and vertical
     *
     * @author Lily Kate
     */
    private Direction direction;

    /**
     * Used to explain which ship the player or opponent has sunk For example: Battleship
     *
     * @author Lily Kate
     */
    private String name;

    /**
     * The coordinates that the ship resides in as a list
     *
     * @author Lily Kate
     */
    private List<Coord> coordList;

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
}
