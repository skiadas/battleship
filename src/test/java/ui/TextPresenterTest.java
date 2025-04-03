package ui;

import static org.junit.jupiter.api.Assertions.*;

import core.Coord;
import core.Game;
import core.Grid;
import core.Ship;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import org.junit.jupiter.api.Test;

class TextPresenterTest {

    @Test
    void whenUserChoosesOption_thenTheCorrectFunctionIsCalled() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(baos, true, StandardCharsets.UTF_8);
        ByteArrayInputStream in = new ByteArrayInputStream("Start\n".getBytes());

        Grid grid = new Grid(3, 3);
        var ref =
                new Object() {
                    boolean startCalled = false;
                    boolean stopCalled = false;
                };
        TextPresenter presenter = new TextPresenter(out, in);
        presenter.displayOptions(
                "prompt",
                Map.of(
                        "Start",
                                () -> {
                                    ref.startCalled = true;
                                },
                        "Stop",
                                () -> {
                                    ref.stopCalled = true;
                                }));
        assertTrue(ref.startCalled);
        assertFalse(ref.stopCalled);
    }

    @Test
    void whenDisplayGridIsCalled_TheGridIsSentToTheOutputStream() {
        TestIOProvider ioProvider = TestIOProvider.withInput("");
        Grid grid = new Grid(3, 3);
        TextPresenter presenter = new TextPresenter(ioProvider);
        presenter.displayGrid(grid);
        String expected =
                """
                             1   2   3\s

                         A   0   0   0\s

                         B   0   0   0\s

                         C   0   0   0\s

                        """;
        assertEquals(expected, ioProvider.getOutput());
    }

    @Test
    void whenUserChoosesStopOption_thenStopFunctionIsCalled() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(baos, true, StandardCharsets.UTF_8);
        ByteArrayInputStream input =
                new ByteArrayInputStream("Stop\n".getBytes(StandardCharsets.UTF_8));

        var ref =
                new Object() {
                    boolean startCalled = false;
                    boolean stopCalled = false;
                };

        TextPresenter presenter = new TextPresenter(out, input);
        presenter.displayOptions(
                "prompt",
                Map.of(
                        "Start",
                                () -> {
                                    ref.startCalled = true;
                                },
                        "Stop",
                                () -> {
                                    ref.stopCalled = true;
                                }));

        assertFalse(ref.startCalled);
        assertTrue(ref.stopCalled);
    }

    @Test
    void whenDisplayGridIsCalled_CreatesRectangularGrid() {
        TestIOProvider ioProvider = TestIOProvider.withInput("");
        Grid grid = new Grid(2, 3);
        TextPresenter presenter = new TextPresenter(ioProvider);
        presenter.displayGrid(grid);

        String expected =
                """
                             1   2   3\s

                         A   0   0   0\s

                         B   0   0   0\s

                        """;
        assertEquals(expected, ioProvider.getOutput());
    }

    @Test
    void whenAskForCoordinateItReturnsIT() {
        Grid grid = new Grid(2, 3);
        TestIOProvider ioProvider = TestIOProvider.withInput("B3\n");
        TextPresenter presenter = new TextPresenter(ioProvider);
        Coord expected = new Coord(2, 3);
        Coord actual = presenter.askForCoordinate(grid);
        boolean Result = expected.isEqual(actual);
        assertTrue(Result);
        assertEquals(expected.row, actual.row);
        assertEquals(expected.col, actual.col);
    }

    @Test
    void whenAskForCoordinateIsFalse() {
        Grid grid = new Grid(2, 3);
        TestIOProvider ioProvider = TestIOProvider.withInput("C5\nB3\n");
        TextPresenter presenter = new TextPresenter(ioProvider);
        Coord expected = new Coord(2, 3);
        Coord actual = presenter.askForCoordinate(grid);
        boolean Result = expected.isEqual(actual);
        String expected_m = "Not within the Grid!";
        assertEquals(expected_m, ioProvider.getOutput());
        assertTrue(Result);
    }

    @Test
    void whenDisplayGridIsCalled_CreatesRectangularGridThatHas1hit() {
        TestIOProvider ioProvider = TestIOProvider.withInput("");
        Grid grid = new Grid(2, 3);
        TextPresenter presenter = new TextPresenter(ioProvider);
        Coord a = new Coord(1, 1);
        Ship bShip = new Ship(a, 1, Ship.Direction.HORIZONTAL, "a");
        grid.addShip(bShip);
        grid.shoot(a);
        presenter.displayGrid(grid);
        String expected =
                """
                             1   2   3\s

                         A   X   0   0\s

                         B   0   0   0\s

                        """;
        assertEquals(expected, ioProvider.getOutput());
    }

    @Test
    void whenDisplayGridIsCalled_CreatesRectangularGridThatHas2hitsAMissAndAShip() {
        TestIOProvider ioProvider = TestIOProvider.withInput("");
        Grid g = new Grid(5, 7);
        TextPresenter presenter = new TextPresenter(ioProvider);
        Coord a = new Coord(4, 5);
        Ship aShip = new Ship(a, 1, Ship.Direction.HORIZONTAL, "a");
        g.addShip(aShip);
        g.shoot(a);
        Coord b = new Coord(1, 1);
        Ship bShip = new Ship(b, 1, Ship.Direction.HORIZONTAL, "b");
        g.addShip(bShip);
        g.shoot(b);
        Coord c = new Coord(5, 7);
        Ship cShip = new Ship(c, 1, Ship.Direction.HORIZONTAL, "c");
        g.addShip(cShip);
        g.shoot(c);
        Coord d = new Coord(2, 6);
        g.shoot(d);
        Coord e = new Coord(3, 2);
        Ship eShip = new Ship(e, 1, Ship.Direction.HORIZONTAL, "e");
        g.addShip(eShip);

        presenter.displayGrid(g);
        String expected =
                """
                             1   2   3   4   5   6   7\s

                         A   X   0   0   0   0   0   0\s

                         B   0   0   0   0   0   *   0\s

                         C   0   ~   0   0   0   0   0\s

                         D   0   0   0   0   X   0   0\s

                         E   0   0   0   0   0   0   X\s

                        """;
        assertEquals(expected, ioProvider.getOutput());
    }

    @Test
    void displayGameOn3x3Grids() {
        Grid grid = new Grid(3, 3);
        TestIOProvider ioProvider = TestIOProvider.withInput("B3\n");
        TextPresenter presenter = new TextPresenter(ioProvider);
        Coord a = new Coord(1, 1);
        Ship bShip = new Ship(a, 2, Ship.Direction.HORIZONTAL, "a");
        grid.addShip(bShip);
        grid.shoot(a);
        grid.shoot(new Coord(2, 1));
        presenter.displayGame(new Game(grid, grid));
        System.out.println(ioProvider.getOutput());
    }

    @Test
    void displayGameOn5x5Grids() {
        Grid grid = new Grid(5, 5);
        Coord a = new Coord(1, 1);
        Ship ship = new Ship(a, 2, Ship.Direction.HORIZONTAL, "a");
        grid.addShip(ship);
        grid.shoot(a);
        Coord b = new Coord(3, 2);
        grid.shoot(b);
        TestIOProvider ioProvider = TestIOProvider.withInput("B5\n");
        TextPresenter presenter = new TextPresenter(ioProvider);
        presenter.displayGame(new Game(grid, grid));
        System.out.println(ioProvider.getOutput());
    }
}
