// Raihan and Jonah
package core;

public class Cell {
    private boolean shot;

    public Cell() {
        this.shot = false;
    }

    // the cell has been shot
    public boolean hasBeenShot() {
        return this.shot;
    }

    // the shot on this cell resulted in a hit
    public boolean cellIsHit() {
        return this.hasBeenShot();
    }

    // Mark the cell as shot
    public void setAsShot() {
        this.shot = true;
    }

    // Reset the cell to its initial state
    public void reset() {
        this.shot = false;
    }
}