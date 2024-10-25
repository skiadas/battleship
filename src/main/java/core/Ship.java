package core;

public class Ship {

    public enum Direction {
        HORIZONTAL,
        VERTICAL
    }

    private int startRow;
    private int startCol;
    private int size;
    private Direction direction;
    private String name;

    public Ship(int startRow, int startCol, int size, Direction direction, String name) {
        this.startRow = startRow;
        this.startCol = startCol;
        this.size = size;
        this.direction = direction;
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public int getStartRow() {
        return startRow;
    }

    public int getStartCol() {
        return startCol;
    }

    public Direction getDirection() {
        return direction;
    }

    public String getName() {
        return name;
    }
}
