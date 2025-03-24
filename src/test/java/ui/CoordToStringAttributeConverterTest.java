package ui;

import static org.junit.jupiter.api.Assertions.*;

import core.Coord;
import org.junit.jupiter.api.Test;

class CoordToStringAttributeConverterTest {

    @Test
    void convertToDatabaseColumn() {
        CoordToStringAttributeConverter converter = new CoordToStringAttributeConverter();
        Coord coord = new Coord(1, 0);
        String result = converter.convertToDatabaseColumn(coord);
        assertEquals("(A, 0)", result);
    }

    @Test
    void convertToDatabaseColumn2() {
        CoordToStringAttributeConverter converter = new CoordToStringAttributeConverter();
        Coord coord = new Coord(3, 7);
        String result = converter.convertToDatabaseColumn(coord);
        assertEquals("(C, 7)", result);
    }

    @Test
    void convertToEntityAttribute() {
        String coord = "A0";
        CoordToStringAttributeConverter converter = new CoordToStringAttributeConverter();
        Coord result = converter.convertToEntityAttribute(coord);
        Coord expected = new Coord(1, 0);
        boolean truth = expected.isEqual(result);
        assertTrue(truth);
    }

    @Test
    void convertToEntityAttribute2() {
        String coord = "C7";
        CoordToStringAttributeConverter converter = new CoordToStringAttributeConverter();
        Coord result = converter.convertToEntityAttribute(coord);
        Coord expected = new Coord(3, 7);
        boolean truth = expected.isEqual(result);
        assertTrue(truth);
    }

    @Test
    void convertToEntityAttributeTestConvertDoubleDiggest() {
        String coord = "K3";
        CoordToStringAttributeConverter converter = new CoordToStringAttributeConverter();
        Coord result = converter.convertToEntityAttribute(coord);
        System.out.println(result.toString());
        Coord expected = new Coord(11, 3);
        boolean truth = expected.isEqual(result);
        assertTrue(truth);
    }
}
