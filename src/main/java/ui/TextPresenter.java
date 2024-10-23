package ui;

import core.Grid;
import core.Presenter;
import java.io.IOException;
import org.jline.terminal.Terminal;

public class TextPresenter implements Presenter {
    Terminal terminal;

    public TextPresenter() throws IOException {
        //        terminal = TerminalBuilder.builder().providers("ffm").build();
    }

    public void displayMessage(String s) {
        //        terminal.writer().println(s);
        System.out.println(s);
    }

    public void displayGrid(Grid g) {
        // These gets us the dimensions of the grid
        int numOfRows = g.numRows();
        int numOfCols = g.numCols();
        String[] letter = {
            " ", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P",
            "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
        };
        // now we need to display the grid using its rows and colunms
        // while also displaying the numbers of the x-axis and letters on the y-axis
        for (int num = 0; num < numOfRows; num++) {
            if (num == 0) {
                System.out.print("   ");
            }
            if (num < 10) {
                System.out.print("  " + (num + 1) + " ");
            } else {
                System.out.print(" " + (num + 1) + " ");
            }
        }
        System.out.println("\n");
        for (int row = 1; row < numOfRows + 1; row++) {
            System.out.print(" " + (letter[row]) + " ");
            for (int col = 1; col < numOfCols + 1; col++) {
                System.out.print("  " + g.get(row, col) + " ");
            }
            System.out.println("\n");
        }
    }
}
