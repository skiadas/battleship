package ui;

import core.Cell;

/** converts a cell state to an emoji representation as a unicode string */
public class EmojiConvert implements Convert {
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
