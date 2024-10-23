package core;

import static core.Ship.Direction.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ShipTest {

    Ship ship = new Ship(1, 2, 3, VERTICAL, "BattleShip");

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
}
