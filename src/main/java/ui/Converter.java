package ui;

import core.CellStatus;

/** interface to decide which visuals to uses */
public interface Converter {
    /**
     * @param cellStatus is the status of a cell on the grid
     * @return the visual representation off that cell
     */
    String convert(CellStatus cellStatus);
}
