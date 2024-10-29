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

    public boolean containsCell(int row, int col) {
        if (this.direction == Direction.HORIZONTAL) {
            if (row == this.startRow) {
                for (int i = 0; i < this.size; i++) {
                    if (col == this.startCol + i) return true;
                }
            }
        } else {
            if (col == this.startCol) {
                for (int i = 0; i < this.size; i++) {
                    if (row == this.startRow + i) return true;
                }
            }
        }
        return false;
    }
}
