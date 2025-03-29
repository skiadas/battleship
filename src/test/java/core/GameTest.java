package core;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameTest {
    private DefaultGridBuilder gridBuilder;
    private Grid firstGrid;
    private Grid secondGrid;
    private Game game;

    @BeforeEach
    public void setup() {
        firstGrid = gridBuilder.defaultGrid();
        secondGrid = gridBuilder.defaultGrid();
        game = new Game(firstGrid, secondGrid);
    }

    @Test
    public void testInitialPlayerIsFirst() {
        assertEquals(Game.Player.FIRST, game.getCurrent());
    }

    //    @Disabled
    @Test
    public void testNextSwitchesToSecondPlayer() {
        game.next();
        assertEquals(Game.Player.SECOND, game.getCurrent());
    }

    @Test
    public void testNextSwitchesBackToFirstPlayer() {
        game.next(); // First -> Second
        game.next(); // Second -> First
        assertEquals(Game.Player.FIRST, game.getCurrent());
    }

    @Test
    public void testShootDelegatesToEnemyGrid() {
        Coord coord = new Coord("A1");
        game.shoot(coord);
        CellStatus status = secondGrid.getStatus(coord);
        assertEquals(CellStatus.EMPTY, status);
    }

    @Test
    public void testIsOverReturnsFalseInitially() {
        assertFalse(game.isOver());
    }

    @Test
    public void testNextSetsGameOverWhenAllEnemyShipsSunk() {
        List<Ship> shiplist = secondGrid.getShipList();
        for (Ship ship : shiplist) {
            for (Coord cord : ship.getCoordList()) {
                secondGrid.shoot(cord);
            }
        }
        game.next();
        assertTrue(game.isOver());
    }

    @Test
    public void testNextThrowsIfGameIsOver() {
        List<Ship> shiplist = secondGrid.getShipList();
        for (Ship ship : shiplist) {
            for (Coord cord : ship.getCoordList()) {
                secondGrid.shoot(cord);
            }
        }
        game.next(); // Ends the game
        assertThrows(RuntimeException.class, () -> game.next());
    }

    @Test
    public void testIsShipSunkDelegatesToEnemyGrid() {
        List<Ship> shiplist = secondGrid.getShipList();
        Boolean shipSunk = true;
        for (Ship ship : shiplist) {
            for (Coord cord : ship.getCoordList()) {
                game.shoot(cord);
            }
            shipSunk = game.isShipSunk(ship);
            break;
        }
        assertTrue(shipSunk);
    }
}
