package core;

import java.util.List;

public class Grid {
    private final int rows;
    private final int cols;

    private Cell[][] grid;

    private List<Cell> shipList;
    private List<Cell> chosenCells;


    public Grid(int rows, int cols) {

        this.rows = rows;
        this.cols = cols;

        grid = new Cell[rows][cols];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                grid[row][col] = new Cell();
            }
        }
    }

    public Cell get(int row, int col) {
        return grid[row][col];
    }

    public int numRows() {
        return rows;
    }

    public int numCols() {
        return cols;
    }
}
