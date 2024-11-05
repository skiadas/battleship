package ui;

import core.Cell;
import core.Coord;
import core.Grid;
import core.Presenter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

public class TextPresenter implements Presenter {
    private PrintStream output;
    private InputStream input;

    // Terminal terminal;

    public TextPresenter() throws IOException {
        //        terminal = TerminalBuilder.builder().providers("ffm").build();
        output = System.out;
        input = System.in;
    }

    public TextPresenter(IOProvider provider) {
        this(provider.out(), provider.in());
    }

    public TextPresenter(PrintStream output, InputStream input) {
        this.output = output;
        this.input = input;
    }

    public void displayMessage(String s) {
        //        terminal.writer().println(s);
        output.println(s);
    }

    public void displayGrid(Grid g) {
        // These gets us the dimensions of the grid
        int numOfRows = g.numRows();
        int numOfCols = g.numCols();
        // create a if statement that throws a error if numRows()
        // return a number higher then 26

        String[] letter = {
            " ","A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q",
            "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
        };
        // now we need to display the grid using its rows and colunms
        // while also displaying the numbers of the x-axis and letters on the y-axis
        for (int num = 0; num <= numOfCols; num++) {
            if (num == 0) {
                output.print("   ");
            } else {
                output.printf(" %2d ", (num));
            }
        }
        output.print("\n\n");
        for (int row = 1; row <= numOfRows; row++) {
            output.print(" " + (letter[row]) + " ");
            for (int col = 1; col <= numOfCols; col++) {
                Cell cell = g.get(new Coord(row, col));
                setCellAs(cell);
            }
            output.print("\n\n");
        }
    }

    private void setCellAs(Cell cell) {
        if (cell.hasBeenShot()) {output.print("  " + "X" + " ");}
        else if (cell.hasShip()) {output.print("  " + "~" + " ");}
        else if (cell.cellIsMiss()) {output.print("  " + "*" + " ");}
        else if (cell.isEmpty()){output.print("  " + "0" + " ");}
    }
}
