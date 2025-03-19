package core;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CellTest {
    @Test
    void defaultCellHoldsProperlyMade() {
        Cell cell = new Cell();
        assertFalse(cell.hasBeenShot());
        assertFalse(cell.cellIsHit());
    }

    @Test
    void returnsIsHitCorrectlyWithShipAndShot() {
        Cell cell = new Cell();
        cell.setAsShot();
        assertTrue(cell.cellIsHit());
    }

    @Test
    void resetSetsShotToFalse() {
        Cell cell = new Cell();
        cell.setAsShot();
        cell.reset();
        assertFalse(cell.cellIsHit());
    }

    @Test
    void settingAsShotMultipleTimesDoesNotChangeState() {
        Cell cell = new Cell();
        cell.setAsShot();
        cell.setAsShot();
        assertTrue(cell.hasBeenShot());
    }
}
