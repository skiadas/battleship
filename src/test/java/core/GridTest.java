package core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GridTest {
    Grid testGrid = new Grid(3, 3);

    @Test
    public void aNewGridHasProvidedDimensions() {
        assertEquals(testGrid.numRows(), 3);
        assertEquals(testGrid.numCols(), 3);
    }

    @Test
    public void aNewGridHasNoneNullCells() {
        for (int i = 0; i < testGrid.numCols(); i++){
            for (int j = 0; j < testGrid.numRows(); j++){
                assertNotEquals(null, testGrid.get(i,j));
            }
        }
    }
}