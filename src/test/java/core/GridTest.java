package core;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GridTest {
    Grid testGrid = new Grid(3, 2);

    @Test
    public void aNewGridHasProvidedDimensions() {
        assertEquals(testGrid.numRows(), 3);
        assertEquals(testGrid.numCols(), 2);
    }

    @Test
    public void aNewGridHasNoneNullCells() {
        for (int i = 0; i < testGrid.numRows(); i++) {
            for (int j = 0; j < testGrid.numCols(); j++) {
                assertNotEquals(null, testGrid.get(i, j));
            }
        }
    }
}
