package core.state;

import core.Coord;
import java.util.Objects;

public class SelectCoord extends Action {
    public final Coord coord;

    public SelectCoord(Coord coord) {
        this.coord = coord;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        SelectCoord that = (SelectCoord) obj;
        return coord.isEqual(that.coord);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coord.row, coord.col);
    }
}
