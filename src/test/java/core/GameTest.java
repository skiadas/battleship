package core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    private Grid firstGrid;
    private Grid secondGrid;
    private Game game;

    @BeforeEach
    public void setup() {
        firstGrid = mock(Grid.class);
        secondGrid = mock(Grid.class);
        game = new Game(firstGrid, secondGrid);
    }

    @Test
    public void testInitialPlayerIsFirst() {
        assertEquals(Game.Player.First, game.getCurrent());
    }

    @Test
    public void testNextSwitchesToSecondPlayer() {
        game.next();
        assertEquals(Game.Player.Second, game.getCurrent());
    }

    @Test
    public void testNextSwitchesBackToFirstPlayer() {
        game.next(); // First -> Second
        game.next(); // Second -> First
        assertEquals(Game.Player.First, game.getCurrent());
    }

    @Test
    public void testShootDelegatesToEnemyGrid() {
        Coord coord = new Coord("A1");
        game.shoot(coord);
        verify(secondGrid).shoot(coord); // secondGrid is enemy for Player.First
    }

    @Test
    public void testIsOverReturnsFalseInitially() {
        assertFalse(game.isOver());
    }

    @Test
    public void testNextSetsGameOverWhenAllEnemyShipsSunk() {
        Ship ship = mock(Ship.class);
        when(secondGrid.getShipList()).thenReturn(Collections.singletonList(ship));
        when(secondGrid.isShipSunk(ship)).thenReturn(true);

        game.next();
        assertTrue(game.isOver());
    }

    @Test
    public void testNextThrowsIfGameIsOver() {
        Ship ship = mock(Ship.class);
        when(secondGrid.getShipList()).thenReturn(Collections.singletonList(ship));
        when(secondGrid.isShipSunk(ship)).thenReturn(true);

        game.next(); // Ends the game
        assertThrows(RuntimeException.class, () -> game.next());
    }

    @Test
    public void testIsShipSunkDelegatesToEnemyGrid() {
        Ship ship = mock(Ship.class);
        game.isShipSunk(ship);
        verify(secondGrid).isShipSunk(ship);
    }
}