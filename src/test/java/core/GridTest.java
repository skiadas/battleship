package core;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GridTest {

    Grid testGrid = new Grid(5, 5, Grid.defaultShipsFor5x5());

    @Test
    public void aNewGridHasProvidedDimensions() {
        assertEquals(testGrid.numRows(), 5);
        assertEquals(testGrid.numCols(), 5);
    }

    @Test
    public void aNewGridHasNoneNullCells() {
        for (int i = 1; i <= testGrid.numRows(); i++) {
            for (int j = 1; j <= testGrid.numCols(); j++) {
                assertNotEquals(null, testGrid.get(new Coord(i, j)));
            }
        }
    }

    @Test
    public void aNewGridCorrectlyMarksShips() {
        assertTrue(testGrid.get(new Coord(1, 2)).hasShip());
        assertTrue(testGrid.get(new Coord(2, 2)).hasShip());
        assertTrue(testGrid.get(new Coord(3, 2)).hasShip());
        assertTrue(testGrid.get(new Coord(5, 2)).hasShip());
        assertFalse(testGrid.get(new Coord(4, 2)).hasShip());
        assertFalse(testGrid.get(new Coord(1, 1)).hasShip());
    }

    @Test
    public void isTheCoordinateWithinGrid() {
        Boolean result = testGrid.isValid(new Coord(3, 2));
        assertEquals(true, result);
    }

    @Test
    public void isTheCoordinateNotWithinGrid() {
        Boolean result = testGrid.isValid(new Coord(5, 6));
        assertEquals(false, result);
    }
}
