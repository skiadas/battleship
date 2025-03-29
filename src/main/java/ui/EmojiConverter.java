package ui;

import core.CellStatus;

/** converts a cell state to an emoji representation as a unicode string */
public class EmojiConverter implements Converter {
    /**
     * @param cellStatus is the status of a location in the grid
     * @return a visuale repesnation of mac compuers
     */
    @Override
    public String convert(final CellStatus cellStatus, final Boolean isPlayer) {
        switch (cellStatus) {
            case SHIP_HIT -> {
                return "☠";
            }
            case SHIP_UNREVEALED -> {
                if (isPlayer) {
                    return "\uD83D\uDEA2";
                } else {
                    return "\uD83C\uDF0A";
                }
            }
            case EMPTY -> {
                return "\uD83D\uDEAB";
            }

            case UNKNOWN -> {
                return "\uD83C\uDF0A";
            }
        }
        return "";
    }
}
