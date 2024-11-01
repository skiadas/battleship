package ui;

import static org.junit.jupiter.api.Assertions.*;

import core.Coord;
import core.Grid;
import org.junit.jupiter.api.Test;

class TextPresenterTest {
    @Test
    void whenDisplayGridIsCalled_TheGridIsSentToTheOutputStream() {
        TestIOProvider ioProvider = TestIOProvider.withInput("");
        Grid grid = new Grid(3, 3);
        TextPresenter presenter = new TextPresenter(ioProvider);
        presenter.displayGrid(grid);
        String expected =
                "     1   2   3 \n"
                        + "\n"
                        + " A   -   -   - \n"
                        + "\n"
                        + " B   -   -   - \n"
                        + "\n"
                        + " C   -   -   - \n"
                        + "\n";
        assertEquals(expected, ioProvider.getOutput());
    }

    @Test
    void whenDisplayGridIsCalled_CreatesRectangularGrid() {
        TestIOProvider ioProvider = TestIOProvider.withInput("");
        Grid grid = new Grid(2, 3);
        TextPresenter presenter = new TextPresenter(ioProvider);
        presenter.displayGrid(grid);
        String expected =
                "     1   2   3 \n"
                        + "\n"
                        + " A   -   -   - \n"
                        + "\n"
                        + " B   -   -   - \n"
                        + "\n";
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
        assertEquals(true, Result);
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
        assertEquals(true, Result);
    }
}
