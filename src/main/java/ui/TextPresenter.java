package ui;

import core.*;
import core.state.Action;
import core.state.SelectCoord;
import core.state.Start;
import core.state.Stop;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Map;
import java.util.Scanner;

/** Displays the grid */
public class TextPresenter extends Presenter {
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
    public void setConvert(final Converter converter) {
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
    public TextPresenter(final IOProvider provider) {
        this(provider.out(), provider.in());
    }

    /**
     * Takes user input and displays it
     *
     * @param output
     * @param input
     */
    public TextPresenter(final PrintStream output, final InputStream input) {
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
    public void displayMessage(final String s) {
        //        terminal.writer().println(s);
        output.println(s);
    }

    /**
     * Displays grid with labels for the cols and rows along with symbols
     *
     * @param g
     */
    @Override
    public void displayGrid(final Grid g) {
        final int numOfRows = g.numRows();
        final int numOfCols = g.numCols();

        final String[] letter = {
                " ", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P",
                "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
        };

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
                final CellStatus cellStatus = g.getStatus(new Coord(row, col));
                final String symbol = converter.convert(cellStatus, true);
                output.print("  " + symbol + " ");
            }
            output.print("\n\n");
        }
    }

    public void displayGame(final Game game) {
        final Grid player = game.getPlayerGrid();
        final Grid enemy = game.getEnemyGrid();

        final int numOfRows = player.numRows();
        final int numOfCols = player.numCols();

        final String[] letter = {
                " ", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P",
                "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
        };

        String titles = "   Your Grid:";
        for (int cols = 1; cols <= numOfCols; cols++) {
            titles += "  ";
        }
        titles += "Enemy Grid:\n\n";
        output.print(titles);

        for (int i = 0; i <= 1; i++) {
            for (int num = 0; num <= numOfCols; num++) {
                if (num == 0) {
                    output.print("   ");
                    if (i == 1) {
                        output.print("     ");
                    }
                } else {
                    output.printf(" %2d ", num);
                }
            }
        }
        output.print("\n\n");

        for (int row = 1; row <= numOfRows; row++) {
            output.print(" " + letter[row] + " ");
            for (int col = 1; col <= numOfCols; col++) {
                final CellStatus cellStatus = player.getStatus(new Coord(row, col));
                final String symbol = converter.convert(cellStatus, true);
                output.print("  " + symbol + " ");
            }
            output.print("  |  ");
            output.print(" " + letter[row] + " ");
            for (int col = 1; col <= numOfCols; col++) {
                final CellStatus cellStatus = enemy.getStatus(new Coord(row, col));
                final String symbol = converter.convert(cellStatus, false);
                output.print("  " + symbol + " ");
            }
            output.print("\n\n");
        }
    }

    @Override
    public Coord askForCoordinate(final Grid g) {
        final Scanner scanner = new Scanner(input);
        while (true) {
            final String userInput = scanner.next();
            final Coord coordinate = new Coord(userInput);
            final boolean answer = coordinate.isWithin(g);
            if (answer) {
                return coordinate;
            } else {
                output.print("Not within the Grid!");
            }
        }
    }

    @Override
    public void displayOptions(final String prompt, final Map<String, Runnable> choices) {
        output.println(prompt);
        printOptions(choices);

        while (true) {
            output.print("Enter your choice: ");
            final String userInput = scanner.nextLine();
            if (choices.containsKey(userInput)) {
                choices.get(userInput).run();
                return;
            } else {
                output.println("Invalid option. Please try again.");
            }
        }
    }

    private void printOptions(final Map<String, Runnable> choices) {
        choices.keySet().forEach(option -> output.println("- " + option));
    }

    /**
     * Prompts user for a game action (start, stop, or coordinate)
     *
     * @param g the grid to validate coordinate input against
     * @return an Action representing user intent
     */
    @Override
    public Action askForGameAction(final Grid g) {
        final Scanner scanner = new Scanner(input);
        while (true) {
            final String userInput = scanner.next().toLowerCase();
            switch (userInput) {
                case "start":
                    return new Start();
                case "stop":
                    return new Stop();
                default:
                    try {
                        Coord coord = new Coord(userInput);
                        if (coord.isWithin(g)) {
                            return new SelectCoord(coord);
                        } else {
                            output.println("Not within the Grid!");
                        }
                    } catch (Exception e) {
                        output.println("Invalid input! Type a valid coordinate, 'start', or 'stop'.");
                    }
            }
        }
    }
}
