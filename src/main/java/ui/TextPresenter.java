package ui;

import core.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Map;
import java.util.Scanner;

/** Displays the grid */
public class TextPresenter implements Presenter {
    /** Sets the default converter */
    private Converter converter = new DefualtConverter();

    /** Outputs user input */
    private PrintStream output;

    /** takes user input */
    private InputStream input;

    /** reads input data */
    private Scanner scanner;

    // Terminal terminal;

    /**
     * Converts to something visible on the grid
     *
     * @param converter
     */
    public void setConvert(Converter converter) {
        this.converter = converter;
    }

    /**
     * Displays text
     *
     * @throws IOException
     */
    public TextPresenter() throws IOException {
        //        terminal = TerminalBuilder.builder().providers("ffm").build();
        this(System.out, System.in);
    }

    /**
     * Uses IOProvider interface
     *
     * @param provider
     */
    public TextPresenter(IOProvider provider) {
        this(provider.out(), provider.in());
    }

    /**
     * Takes user input and displays it
     *
     * @param output
     * @param input
     */
    public TextPresenter(PrintStream output, InputStream input) {
        this.output = output;
        this.input = input;
        this.scanner = new Scanner(input);
    }

    /**
     * Shows message to user
     *
     * @param s
     */
    @Override
    public void displayMessage(String s) {
        //        terminal.writer().println(s);
        output.println(s);
    }

    /**
     * Displays grid with labels for the cols and rows along with symbols
     *
     * @param g
     */
    @Override
    public void displayGrid(Grid g) {
        // These gets us the dimensions of the grid
        int numOfRows = g.numRows();
        int numOfCols = g.numCols();
        // create a if statement that throws a error if numRows()
        // return a number higher then 26

        String[] letter = {
            " ", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P",
            "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
        };
        // now we need to display the grid using its rows and colunms
        // while also displaying the numbers of the x-axis and letters on the y-axis
        for (int num = 0; num <= numOfCols; num++) {
            if (num == 0) {
                output.print("   ");
            } else {
                output.printf(" %2d ", num);
            }
        }
        output.print("\n\n");
        for (int row = 1; row <= numOfRows; row++) {
            output.print(" " + letter[row] + " ");
            for (int col = 1; col <= numOfCols; col++) {
                CellStatus cellStatus = g.getStatus(new Coord(row, col));
                String symbol = converter.convert(cellStatus);
                output.print("  " + symbol + " ");
            }
            output.print("\n\n");
        }
    }

    /**
     * Prompts user for Coordinate selection and checks if it is within the Grid
     *
     * @param g
     * @return
     */
    @Override
    public Coord askForCoordinate(Grid g) {
        Scanner scanner = new Scanner(input);
        while (true) {
            String userInput = scanner.next();
            Coord coordinate = new Coord(userInput);
            boolean answer = coordinate.isWithin(g);
            if (answer) {
                return coordinate;
            } else {
                output.print("Not within the Grid!");
            }
        }
    }

    /**
     * Asks user their grid spot to shoot and makes sure its on the grid
     *
     * @param prompt
     * @param choices
     */
    @Override
    public void displayOptions(String prompt, Map<String, Runnable> choices) {
        output.println(prompt);
        printOptions(choices);

        while (true) {
            output.print("Enter your choice: ");
            String userInput = scanner.nextLine();
            if (choices.containsKey(userInput)) {
                choices.get(userInput).run();
                return;
            } else {
                output.println("Invalid option. Please try again.");
            }
        }
    }

    /**
     * Prints a list of choices for the user
     *
     * @param choices
     */
    private void printOptions(Map<String, Runnable> choices) {
        choices.keySet().forEach(option -> output.println("- " + option));
    }
}
