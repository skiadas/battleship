package core;

import static core.Ship.Direction.VERTICAL;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
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

    @Test
    public void whenShootIsMiss() {
        testGrid.shoot(new Coord(2, 2));
        Cell shootCell = testGrid.get(new Coord(2, 2));
        assertTrue(shootCell.cellIsMiss());
    }

    @Test
    public void whenShootIsHit() {
        Cell shootCell = testGrid.get(new Coord(3, 2));
        shootCell.setAsShip();
        testGrid.shoot(new Coord(3, 2));
        assertTrue(shootCell.cellIsHit());
    }

    @Test
    public void allShipsHaveNotBeenShot() {
        boolean result = testGrid.allShipsAreSunk();
        assertEquals(false, result);
    }

    @Test
    public void allShipsAreParticallyShot() {
        Cell cell1 = testGrid.get(new Coord(5, 1));
        cell1.setAsHit();
        Cell cell2 = testGrid.get(new Coord(1, 5));
        cell2.setAsHit();
        boolean result = testGrid.allShipsAreSunk();
        assertEquals(false, result);
    }

    @Test
    public void allShipsAreShot() {
        List<Ship> shipList = testGrid.getShipList();
        for (Ship ship : shipList) {
            List<Coord> coords = ship.getCoordList();
            for (Coord coord : coords) {
                testGrid.get(coord).setAsShot();
                testGrid.get(coord).setAsHit();
            }
        }
        boolean result = testGrid.allShipsAreSunk();
        assertEquals(true, result);
    }

    @Test
    public void isShipOnGridReturnsTrueWhenShipIsShipOnGrid() {
        List<Ship> ships = testGrid.getShipList();
        assertTrue(testGrid.isShipOnGrid(ships.get(0)));
    }

    @Test
    public void isShipOnGridReturnsFalseWhenShipIsShipOnGrid() {
        Coord c1 = new Coord(1, 2);
        Ship otherShip = new Ship(c1, 6, VERTICAL, "BattleShip");
        assertFalse(testGrid.isShipOnGrid(otherShip));
    }

    @Test
    public void isShipSunkReturnsTrueIfAllCellsMarkedAsHit() {
        Ship ship = new Ship(new Coord(1, 2), 3, VERTICAL, "BattleShip");
        Coord c1 = new Coord(1, 2);
        Coord c2 = new Coord(2, 2);
        Coord c3 = new Coord(3, 2);
        testGrid.get(c1).setAsShip();
        testGrid.get(c2).setAsShip();
        testGrid.get(c3).setAsShip();
        testGrid.get(c1).setAsShot();
        testGrid.get(c2).setAsShot();
        assertFalse(testGrid.isShipSunk(ship));
        testGrid.get(c3).setAsShot();
        assertTrue(testGrid.isShipSunk(ship));
    }
}
