package ui;

import static org.junit.jupiter.api.Assertions.*;

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
}
