package core;

import static core.Ship.Direction.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ShipTest {

    Ship ship = new Ship(1, 2, 3, VERTICAL, "BattleShip");
    Grid grid = new Grid(5, 5);

    @Test
    public void getStartRowReturnsStartRowFromField() {
        assertEquals(1, ship.getStartRow());
    }

    @Test
    public void getStartColReturnsStartColFromField() {
        assertEquals(2, ship.getStartCol());
    }

    @Test
    public void getSizeReturnsSizeFromField() {
        assertEquals(3, ship.getSize());
    }

    @Test
    public void getDirectionReturnsDirectionFromField() {
        assertEquals(VERTICAL, ship.getDirection());
    }

    @Test
    public void getNameReturnsNameFromField() {
        assertEquals("BattleShip", ship.getName());
    }

    @Test
    public void hitsShipCorrectlyReturnsTrue() {
        Coord coordInShip = new Coord(1, 2);
        assertTrue(ship.containsCoord(coordInShip));
    }

    @Test
    public void hitsShipCorrectlyReturnsFalse() {
        Coord coordNotInShip = new Coord(3, 3);
        assertFalse(ship.containsCoord(coordNotInShip));
    }

    @Test
    public void isSunkReturnsTrueOnlyIfAllCellsMarkedAsHit() {
        Coord c1 = new Coord(1, 2);
        Coord c2 = new Coord(2, 2);
        Coord c3 = new Coord(3, 2);
        grid.get(c1).setAsHit();
        grid.get(c2).setAsHit();
        assertFalse(ship.isSunk(grid));
        grid.get(c3).setAsHit();
        assertTrue(ship.isSunk(grid));
    }

    @Test
    public void shipOverlapsReturnsTrueWhenShipsOverlap() {
        Ship otherShip = new Ship(1, 2, 3, VERTICAL, "BattleShip");
        assertTrue(ship.isOverlapping(otherShip));
    }

    @Test
    public void shipOverlapsReturnsFalseWhenShipsDoNotOverlap() {
        Ship otherShip = new Ship(4, 4, 2, VERTICAL, "BattleShip");
        assertFalse(ship.isOverlapping(otherShip));
    }

    @Test
    public void isOnGridReturnsTrueWhenShipIsOnGrid() {
        assertTrue(ship.isOnGrid(grid));
    }

    @Test
    public void isOnGridReturnsFalseWhenShipIsOnGrid() {
        Ship otherShip = new Ship(1, 2, 6, VERTICAL, "BattleShip");
        assertFalse(otherShip.isOnGrid(grid));
    }
}
