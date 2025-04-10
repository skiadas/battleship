package core;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import ui.CoordToStringAttributeConverter;

/**
 * Represents a ship object for the {@link Grid} class, Describes the different directions it can
 * face, Describes the length of the ship Has the starting coordinate of the ship, Helps identify
 * the name of different ships,
 */
@Entity
public class Ship {

    public Long getId() {
        return id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public enum Direction {
        HORIZONTAL,
        VERTICAL
    }

    public Coord getStartcoordinate() {
        return startcoordinate;
    }

    /** The starting coordinate of the ship */
    // @OneToOne
    // @JoinColumn(name = "startcoordinate_id")
    @Convert(converter = CoordToStringAttributeConverter.class)
    private Coord startcoordinate;

    /** Length of the Ship object */
    @Column private int size;

    /** the directions its facing like horizontal and vertical */
    @Column private Direction direction;

    /** Name of the ship e.g. Battleship */
    @Column(nullable = false)
    private String name;

    /** The coordinates of the whole ship */
    // @Convert(converter = CoordToStringAttributeConverter.class)
    @Transient private List<Coord> coordList;

    public Ship(Coord coordinate, int size, Direction direction, String name) {
        this.startcoordinate = coordinate;
        this.size = size;
        this.direction = direction;
        this.name = name;
        this.coordList = genCoordList();
    }

    public Ship() {}

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
        return any(c -> coord.isEqual(c));
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
        return other.any(this::containsCoord);
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

    public boolean any(Predicate<Coord> predicate) {
        for (Coord coord : getCoordList()) {
            if (predicate.test(coord)) {
                return true;
            }
        }
        return false;
    }
}
