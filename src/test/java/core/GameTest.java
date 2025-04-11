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

    private List<Ship> firstShipList;
    private List<Ship> secondShipList;

    @BeforeEach
    public void setup() {
        firstShipList = List.of(
                new Ship(new Coord("A1"), 3, Ship.Direction.HORIZONTAL, "Battleship"),  // 3-cell ship at A1, A2, A3
                new Ship(new Coord("B1"), 2, Ship.Direction.VERTICAL, "Destroyer")    // 2-cell ship at B1, B2
        );

        secondShipList = List.of(
                new Ship(new Coord("D1"), 4, Ship.Direction.HORIZONTAL, "Cruiser"),  // 4-cell ship at D1, D2, D3, D4
                new Ship(new Coord("E1"), 3, Ship.Direction.VERTICAL, "Submarine")   // 3-cell ship at E1, F1, G1
        );

        firstGrid = new Grid(10, 10, firstShipList);
        secondGrid = new Grid(10, 10, secondShipList);
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
        for (Ship ship : secondShipList) {
            for (Coord cord : ship.getCoordList()) {
                secondGrid.shoot(cord);
            }
        }
        game.next();
        assertTrue(game.isOver());
    }

    @Test
    public void testNextThrowsIfGameIsOver() {
        for (Ship ship : secondShipList) {
            for (Coord cord : ship.getCoordList()) {
                secondGrid.shoot(cord);
            }
        }
        game.next(); // Ends the game
        assertThrows(RuntimeException.class, () -> game.next());
    }

    @Test
    public void testIsShipSunkDelegatesToEnemyGrid() {
        Boolean shipSunk = true;
        for (Ship ship : secondShipList) {
            for (Coord cord : ship.getCoordList()) {
                game.shoot(cord);
            }
            shipSunk = game.isShipSunk(ship);
            break;
        }
        assertTrue(shipSunk);
    }
}