package ui;

import core.Cell;
import core.visualState;
/**
 * converts a cell state to an emoji representation as a unicode string
 *
 */
public class EmojiConvert implements Convert {
    @Override
    public String convert(visualState cellState) {
        if (cellState == visualState.HIT) {
            return "☠";
        } else if (cellState == visualState.SHIP) {
            return "\uD83D\uDEA2";
        } else if (cellState == visualState.MISS) {
            return "\uD83D\uDEAB";
        } else if (cellState == visualState.EMPTY) {
            return "\uD83C\uDF0A";
        }
        return "";
    }
}
