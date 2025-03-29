package core;

import java.util.List;

/** States the location of a {@link Ship} */
public class Coord {
    /** The Y axis of the grid */
    private static final List<String> ALPHABET =
            List.of(
                    " ", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O",
                    "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z");

    /** The {@link Coord} in a given horizontal line */
    public final int row;

    /** The {@link Coord} in a given vertical line */
    public final int col;

    /**
     * constructor using row and col
     *
     * @param row location on the horizontal line
     * @param col location on the vertical line
     */
    public Coord(final int row, final int col) {
        this.row = row;
        this.col = col;
    }

    /** basic constructor */
    public Coord() {
        row = 0;
        col = 0;
    }

    /**
     * corresponding number to the alphabet
     *
     * @param row location on the horizontal line
     * @return an int
     */
    public static int asInt(final String row) {
        return ALPHABET.indexOf(row);
    }

    /**
     * corresponding alphabet to row
     *
     * @param row location on the horizontal line
     * @return a string
     */
    public static String asString(final int row) {
        return ALPHABET.get(row);
    }

    /**
     * @return {@link Coord} in string format
     */
    public String getCoordString() {
        return asString(row) + col;
    }

    /**
     * Constructor using string
     *
     * @param rowCol the combination of row and col
     */
    public Coord(final String rowCol) {
        final String[] coords = rowCol.split("");
        this.row = asInt(coords[0]);
        this.col = Integer.parseInt(coords[1]);
    }

    /**
     * If both {@link Coord} are the same or not
     *
     * @param other additonal coordinate
     * @return true or false
     */
    public boolean isEqual(final Coord other) {
        return this.col == other.col && this.row == other.row;
    }

    /**
     * Shifts the row and col
     *
     * @param verticalDistance number that adds or decreases row
     * @param horizontalDistance number that adds or decreases col
     * @return a new {@link Coord} with shifted row and col
     */
    public Coord shiftBy(final int verticalDistance, final int horizontalDistance) {
        return new Coord(this.row + verticalDistance, this.col + horizontalDistance);
    }

    /**
     * Tells if the {@link Coord} is in the {@link Grid}
     *
     * @param bounding gives {@link Grid} boundary
     * @return If the {@link Coord} is within the {@link Grid}
     */
    public Boolean isWithin(final Bounding bounding) {
        final int numRows = bounding.numRows();
        final int numCols = bounding.numCols();
        return row - 1 >= 0 && row - 1 < numRows && col - 1 >= 0 && col - 1 < numCols;
    }
}
