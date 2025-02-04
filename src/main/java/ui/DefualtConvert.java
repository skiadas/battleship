package ui;

import core.Cell;
import core.visualState;
/**
 *
 */
public class DefualtConvert implements Convert {
    @Override
    public String convert(visualState currState) {
        if (currState == visualState.HIT) {
            return "X";
        } else if (currState == visualState.SHIP) {
            return "~";
        } else if (currState == visualState.MISS) {
            return "*";
        } else if (currState == visualState.EMPTY) {
            return "0";
        }
        return "";
    }
}
