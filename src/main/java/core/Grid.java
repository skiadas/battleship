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

        for (int i = 0 ; i < cols ; i++) {
            for (int j = 0 ; j < rows ; j++) {
                grid[i][j] = new Cell();
            }
        }

    }

    public Cell get(int row, int col) {
        return grid[col][row];
    }

    public int numRows() {
        return rows;
    }

    public int numCols() {
        return cols;
    }
}
