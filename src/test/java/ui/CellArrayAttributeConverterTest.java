package ui;

import static org.junit.jupiter.api.Assertions.*;

import core.Cell;
import org.junit.jupiter.api.Test;

class CellArrayAttributeConverterTest {

    @Test
    void convertToDatabaseColumn() {
        Cell[][] cell1 = {{new Cell()}, {new Cell()}};
        for (Cell[] cell : cell1) {
            for (Cell c : cell) {
                c.setAsShot();
            }
        }
        CellArrayAttributeConverter converter = new CellArrayAttributeConverter();
        byte[] bytes = converter.convertToDatabaseColumn(cell1);
        assertEquals(2, bytes[0]);
        assertEquals(1, bytes[1]);
        assertEquals(3, bytes[2]);
    }

    @Test
    void convertToDatabaseColumn2() {
        Cell[][] cell1 = {{new Cell(), new Cell()}, {new Cell(), new Cell()}};
        CellArrayAttributeConverter converter = new CellArrayAttributeConverter();
        byte[] bytes = converter.convertToDatabaseColumn(cell1);
        assertEquals(2, bytes[0]);
        assertEquals(2, bytes[1]);
        assertEquals(0, bytes[2]);
    }

    @Test
    void convertToDatabaseColumn3() {
        Cell[][] cell1 = {{new Cell(), new Cell()}, {new Cell(), new Cell()}};
        for (Cell[] cell : cell1) {
            for (int i = 0; i < cell.length; i += 2) {
                cell[i].setAsShot();
            }
        }
        CellArrayAttributeConverter converter = new CellArrayAttributeConverter();
        byte[] bytes = converter.convertToDatabaseColumn(cell1);
        assertEquals(2, bytes[0]);
        assertEquals(2, bytes[1]);
        assertEquals(5, bytes[2]);
    }

    @Test
    void convertToDatabaseColumn4() {
        Cell[][] cell1 = {
            {new Cell(), new Cell(), new Cell(), new Cell()},
            {new Cell(), new Cell(), new Cell(), new Cell()}
        };
        for (Cell[] cell : cell1) {
            for (int i = 0; i < cell.length; i += 2) {
                cell[i].setAsShot();
            }
        }
        CellArrayAttributeConverter converter = new CellArrayAttributeConverter();
        byte[] bytes = converter.convertToDatabaseColumn(cell1);
        assertEquals(2, bytes[0]);
        assertEquals(4, bytes[1]);
        assertEquals(85, bytes[2]);
    }

    //    @Test
    //    void convertToEntityAttribute() {
    //        byte[] bytes = {2,1,3};
    //
    //        CellArrayAttributeConverter converter = new CellArrayAttributeConverter();
    //
    //        Cell[][] cells = converter.convertToEntityAttribute(bytes);
    //
    //        Cell[][] actualCells = {{new Cell()},{new Cell()}};
    //        for (Cell[] cell : actualCells) {
    //            for (Cell c : cell) {
    //                c.setAsShot();
    //            }
    //        }
    //
    //        assertEquals(actualCells.length,cells.length);
    //        assertEquals(actualCells[0][0].hasBeenShot(), cells[0][0].hasBeenShot());
    //        assertEquals(actualCells[1][0].hasBeenShot(), cells[1][0].hasBeenShot());
    //
    //    }
}
