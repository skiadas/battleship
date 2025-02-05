package ui;

import core.Cell;
/**
 * converts a cell state to a visual representation as a string
 */
public class DefualtConvert implements Convert {
    @Override
    public String convert(final Cell cell) {
        if (cell.cellIsHit()) {
            return "X";
        } else if (cell.hasShip()) {
            return "~";
        } else if (cell.cellIsMiss()) {
            return "*";
        } else if (cell.isEmpty()) {
            return "0";
        }
        return "";
    }
}
