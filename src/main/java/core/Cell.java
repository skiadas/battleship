package core;

import java.io.Serializable;

/** Cell is the individual spots of the grid. Used in the {@link Grid} class. */
public class Cell implements Serializable {
    /** Keeps track of if the cell was shot. */
    private boolean shot;

    /** Sets the default cell to having a false boolean shot. */
    public Cell() {
        this.shot = false;
    }

    /**
     * @return the indication that the cell has been shot
     */
    public boolean hasBeenShot() {
        return this.shot;
    }

    /**
     * @return the status of the cell
     */
    public boolean cellIsHit() {
        return this.hasBeenShot();
    }

    /** marks the cell as shot */
    public void setAsShot() {
        this.shot = true;
    }

    /** marks the cell as hit if a ship resided inside it */
    public void setAsHit() {
        this.shot = true;
    }

    /** marks the cell as missed */
    public void setAsMiss() {
        this.shot = true;
    }

    /** resets the cell to its initial state */
    public void reset() {
        this.shot = false;
    }
}
