package core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AIShipsTest {

    @Test
    public void canPlaceShipsFromSetSizes(){
        Grid grid = new Grid(8,8);
        int [] shipSizes = {5,4,3,2,2,1};
        AIShips aiShips = new AIShips(grid, shipSizes);
        aiShips.setShips();
        assertEquals(6, aiShips.getShipCount());
    }

    @Test
    public void throwsErrorIfShipSizeIsTooBig(){
        try {
            Grid grid = new Grid(8,8);
            int [] shipSizes = {9,4,3,2,2,1};
            AIShips aiShips = new AIShips(grid, shipSizes);
        } catch (RuntimeException e) {
            assertEquals("INVALID SHIP SIZES GIVEN", e.getMessage());
        }


    }
}
