package ui;

import core.Grid;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class TextPresenterTest {
    @Test
    void whenDisplayGridIsCalled_TheGridIsSentToTheOutputStream(){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(baos,true,StandardCharsets.UTF_8);
        InputStream in = System.in;
        Grid grid = new Grid(3,3);
        TextPresenter presenter = new TextPresenter(out,in);
        presenter.displayGrid(grid);
        String expected = "     1   2   3 \n" +
                "\n" +
                " A   -   -   - \n" +
                "\n" +
                " B   -   -   - \n" +
                "\n" +
                " C   -   -   - \n" + "\n";
        String actual = baos.toString(StandardCharsets.UTF_8);
        assertEquals(expected,actual);
    }
    @Test
    void whenDisplayGridIsCalled_CreatesRectangularGrid(){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(baos,true,StandardCharsets.UTF_8);
        Grid grid = new Grid(2,3);
        TextPresenter presenter = new TextPresenter(out,null);
        presenter.displayGrid(grid);
        String expected = "     1   2   3 \n" +
                "\n" +
                " A   -   -   - \n" +
                "\n" +
                " B   -   -   - \n" +
                "\n";
        String actual = baos.toString(StandardCharsets.UTF_8);
        assertEquals(expected,actual);
    }
}