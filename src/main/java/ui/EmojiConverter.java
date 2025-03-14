package ui;

import core.Cell;
import core.CellStatus;

/** converts a cell state to an emoji representation as a unicode string */
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

    @Override
    public String convert(final CellStatus cellStatus) {
        switch (cellStatus) {
            case ShipHit -> {
                return "☠";
            }
            case ShipUnrevealed -> {
                return "\uD83D\uDEA2";
            }
            case Empty -> {
                return "\uD83D\uDEAB";
            }

            case Unknown -> {
                return "\uD83C\uDF0A";
            }
        }
        return "";
    }
}
