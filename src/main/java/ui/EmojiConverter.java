package ui;

import core.CellStatus;

/** converts a cell state to an emoji representation as a unicode string */
public class EmojiConverter implements Converter {
    /**
     * @param cellStatus is the status of a location in the grid
     * @return a visuale repesnation of mac compuers
     */
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
