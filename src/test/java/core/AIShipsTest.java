package core;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AIShipsTest {

    private Grid grid = new Grid(8,8);
    private List<Integer> shipSizes = new ArrayList<Integer>(Arrays.asList(5,4,3,2,2,1));
    private AIShips aiShips = new AIShips(grid, shipSizes);

    @Test
    public void canPlaceShipsFromSetSizes(){
        aiShips.setShips();
        assertEquals(6, aiShips.getShipCount());
    }
}
