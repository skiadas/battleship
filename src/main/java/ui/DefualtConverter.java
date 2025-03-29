package ui;

import core.CellStatus;

/** converts a cell state to a visual representation as a string */
public class DefualtConverter implements Converter {
    /**
     * @param cellStatus is the status of a location in the grid
     * @return a visulale respentaion of the cell for all computers
     */
    @Override
    public String convert(final CellStatus cellStatus) {
        switch (cellStatus) {
            case SHIP_HIT -> {
                return "X";
            }
            case SHIP_UNREVEALED -> {
                return "~";
            }
            case EMPTY -> {
                return "*";
            }

            case UNKNOWN -> {
                return "0";
            }
        }
        return "";
    }
}
