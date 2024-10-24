package core;

import java.util.List;

public class Coord {

    private final static List<String> ALPHABET = List.of(
            " ", "a", "b", "c", "d", "e", "f", "g",
            "h", "i", "j", "k", "l", "m", "n", "o", "p",
            "q", "r", "s", "t", "u", "v", "w", "x", "y", "z");

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

    //public Coord(String rowCol) {

        //this.row =
        //this.col = col;
        //"(\w)(\d+)"
   // }

}
