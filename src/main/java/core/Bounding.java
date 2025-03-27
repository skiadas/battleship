package core;
/**
 * Bounding is an  interface, that directs you
 * to the Coords class rather than the Grid class
 * it gives an easier way to acess the needed and proper class.
*/
/**
 * @parma numRows int value of rows in grid
 * @parma numCols int value of Cols in grid
 */
public interface Bounding {
    int numRows();

    int numCols();
}
