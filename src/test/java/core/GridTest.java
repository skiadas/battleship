package core;

import static core.Ship.Direction.HORIZONTAL;
import static core.Ship.Direction.VERTICAL;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;

class GridTest {

    Grid testGrid = new Grid(5, 5, DefaultGridBuilder.defaultShipsFor5x5());

    @Test
    public void aNewGridHasProvidedDimensions() {
        assertEquals(testGrid.numRows(), 5);
        assertEquals(testGrid.numCols(), 5);
    }

    @Test
    public void aNewGridHasNoneNullCells() {
        for (int i = 1; i <= testGrid.numRows(); i++) {
            for (int j = 1; j <= testGrid.numCols(); j++) {
                assertNotEquals(null, testGrid.getStatus(new Coord(i, j)));
            }
        }
    }

    @Test
    public void aNewGridCorrectlyMarksShips() {
        assertEquals(CellStatus.SHIP_UNREVEALED, testGrid.getStatus(new Coord(1, 2)));
        assertEquals(CellStatus.SHIP_UNREVEALED, testGrid.getStatus(new Coord(2, 2)));
        assertEquals(CellStatus.SHIP_UNREVEALED, testGrid.getStatus(new Coord(3, 2)));
        assertEquals(CellStatus.SHIP_UNREVEALED, testGrid.getStatus(new Coord(5, 2)));
        assertEquals(CellStatus.UNKNOWN, testGrid.getStatus(new Coord(4, 2)));
        assertEquals(CellStatus.UNKNOWN, testGrid.getStatus(new Coord(1, 1)));
    }

    @Test
    public void addShipUpdatesShipListCorrectly() {
        Ship ship1 = new Ship(new Coord(1, 2), 3, VERTICAL, "Submarine");
        Ship ship2 = new Ship(new Coord(5, 1), 5, HORIZONTAL, "Carrier");
        Ship ship3 = new Ship(new Coord(1, 5), 3, VERTICAL, "Destroyer");
        Ship addedShip = new Ship(new Coord(4, 5), 1, VERTICAL, "Ship");
        testGrid.addShip(addedShip);
        assertTrue(assertEqualShips(ship1, testGrid.getShipList().get(0)));
        assertTrue(assertEqualShips(ship2, testGrid.getShipList().get(1)));
        assertTrue(assertEqualShips(ship3, testGrid.getShipList().get(2)));
        assertTrue(assertEqualShips(addedShip, testGrid.getShipList().get(3)));
    }

    public static boolean assertEqualShips(Ship expected, Ship actual) {
        if (expected.getSize() != actual.getSize()) {
            return false;
        }
        for (int i = 0; i < expected.getSize(); i++) {
            if (!expected.getCoordList().get(i).isEqual(actual.getCoordList().get(i))) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void whenShootingAndNotAlreadyShotChangeCellToShot() {
        testGrid.shoot(new Coord(2, 2));
        CellStatus shootCellStatus = testGrid.getStatus(new Coord(2, 2));
        assertEquals(shootCellStatus, CellStatus.SHIP_HIT);
    }

    @Test
    public void allShipsHaveNotBeenShot() {
        boolean result = testGrid.allShipsAreSunk();
        assertFalse(result);
    }

    @Test
    public void allShipsArePartiallyShot() {
        testGrid.shoot(new Coord(5, 1));
        testGrid.shoot(new Coord(1, 5));
        boolean result = testGrid.allShipsAreSunk();
        assertFalse(result);
    }

    @Test
    public void allShipsAreShot() {
        List<Ship> shipList = testGrid.getShipList();
        for (Ship ship : shipList) {
            List<Coord> coords = ship.getCoordList();
            for (Coord coord : coords) {
                testGrid.shoot(coord);
                testGrid.shoot(coord);
            }
        }
        boolean result = testGrid.allShipsAreSunk();
        assertTrue(result);
    }

    @Test
    public void isShipOnGridReturnsTrueWhenShipIsShipOnGrid() {
        List<Ship> ships = testGrid.getShipList();
        assertTrue(ships.get(0).isWithinBounds(testGrid));
    }

    @Test
    public void isShipOnGridReturnsFalseWhenShipIsShipOnGrid() {
        Coord c1 = new Coord(1, 2);
        Ship otherShip = new Ship(c1, 6, VERTICAL, "BattleShip");
        assertFalse(otherShip.isWithinBounds(testGrid));
    }

    @Test
    public void isShipSunkReturnsTrueIfAllCellsMarkedAsHit() {
        Ship ship = new Ship(new Coord(1, 2), 3, VERTICAL, "BattleShip");
        Coord c1 = new Coord(1, 2);
        Coord c2 = new Coord(2, 2);
        Coord c3 = new Coord(3, 2);
        testGrid.shoot(c1);
        testGrid.shoot(c2);
        assertFalse(testGrid.getSunkShipAt(ship));
        testGrid.shoot(c3);
        assertTrue(testGrid.getSunkShipAt(ship));
    }

    @Test
    public void getSunkShipAtReturnsEmptyWhenNoShipAtCoordinate() {
        Optional<Ship> result = testGrid.getSunkShipAt(new Coord(1, 1)); // Empty cell
        assertTrue(result.isEmpty(), "Should return empty when no ship is at the coordinate");
    }

    @Test
    public void getSunkShipAtReturnsEmptyWhenShipNotFullyHit() {
        Ship ship = new Ship(new Coord(1, 2), 3, VERTICAL, "BattleShip");
        Coord c1 = new Coord(1, 2);
        Coord c2 = new Coord(2, 2);
        Coord c3 = new Coord(3, 2);
        testGrid.shoot(c1);
        testGrid.shoot(c2);
        Optional<Ship> result = testGrid.getSunkShipAt(c1);
        assertTrue(result.isEmpty(), "Should return empty when ship is not fully hit");
    }

    @Test
    public void getSunkShipAtReturnsShipWhenFullyHit() {
        Ship ship = new Ship(new Coord(4, 4), 2, HORIZONTAL, "BattleShip");
        testGrid.addShip(ship);
        for (Coord coord : ship.getCoordList()) {
            testGrid.shoot(coord);
        }
        Optional<Ship> result = testGrid.getSunkShipAt(ship.getCoordList().get(0));
        assertTrue(result.isPresent());
        assertEquals("BattleShip", result.get().getName());
    }
}
