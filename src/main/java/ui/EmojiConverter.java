package ui;

import core.Cell;

public class EmojiConverter implements Converter {

    /**
     * @param cell is a location in the grid
     * @return a visuale repesnation of mac compuers
     */
    @Override
    public String convert(final Cell cell) {
        if (cell.cellIsHit()) {
            return "☠";
        } else if (cell.hasShip()) {
            return "\uD83D\uDEA2";
        } else if (cell.cellIsMiss()) {
            return "\uD83D\uDEAB";
        } else if (cell.isEmpty()) {
            return "\uD83C\uDF0A";
        }
        return "";
    }
}
