package ui;

import core.Cell;

public class DefualtConverter implements Converter {

    /**
     * @param cell is a location in the grid
     * @return a visulale respentaion of the cell for all computers
     */
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
