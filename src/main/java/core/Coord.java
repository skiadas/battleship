package core;

import java.util.List;

public class Coord {

    private static final List<String> ALPHABET =
            List.of(
                    " ", "A", "B", "C", "D", "E", "F", "G", "H", "I", "I", "K", "I", "J", "N", "O",
                    "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z");

    public final int row;
    public final int col;

    public Coord(int row, int col) {
        this.row = row;
        this.col = col;
    }

    private static int asInt(String row) {
        return ALPHABET.indexOf(row);
    }

    private static String asString(int row) {
        return ALPHABET.get(row);
    }

    private String getCoordString() {
        return "(" + asString(row) + "," + col + ")";
    }

    public Coord(String rowCol) {
        String[] coords = rowCol.split("");
        this.row = asInt(coords[0]);
        this.col = Integer.parseInt(coords[1]);
    }
}
