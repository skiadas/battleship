package ui;

import core.Coord;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class CoordToStringAttributeConverter implements AttributeConverter<Coord, String> {
    @Override
    public String convertToDatabaseColumn(Coord coord) {
        return coord.getCoordString();
    }

    @Override
    public Coord convertToEntityAttribute(String s) {
        Coord coord = new Coord(s);
        return coord;
    }
}
