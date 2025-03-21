package ui;

import core.Cell;
import jakarta.persistence.AttributeConverter;
import java.util.BitSet;

public class CellArrayAttributeConverter implements AttributeConverter<Cell[][], byte[]> {

    @Override
    public byte[] convertToDatabaseColumn(Cell[][] cells) {
        int n = cells.length;
        int k = cells[0].length;
        byte[] bytes = new byte[2 + 1 + n * k / 8];
        bytes[0] = (byte) n;
        bytes[1] = (byte) k;
        BitSet bitSet = new BitSet(n * k);
        int index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < k; j++) {
                bitSet.set(index, cells[i][j].hasBeenShot());
                index++;
            }
        }
        byte[] byteArray = bitSet.toByteArray();
        System.arraycopy(byteArray, 0, bytes, 2, byteArray.length);
        return bytes;
    }

    @Override
    public Cell[][] convertToEntityAttribute(byte[] bytes) {
        int n = bytes[0];
        int k = bytes[1];
        Cell[][] result = new Cell[n][k];
        int byteIndex = 2;
        int bitIndex = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < k; j++) {
                boolean shot = (bytes[byteIndex] & (1 << (7 - bitIndex))) != 0;
                result[i][j] = new Cell();
                if (shot) {
                    result[i][j].setAsShot();
                }
                bitIndex++;
                if (bitIndex == 8) {
                    bitIndex = 0;
                    byteIndex++;
                }
            }
        }
        return result;
    }
}
