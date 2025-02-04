package core;

/** Ship Specifications such as name and size / length for a ship to be created */
public class ShipSpec {
    /** The name of the desired ship */
    public final String name;

    /** The size / length of the desired ship */
    public final int size;

    /**
     * Creates an Instance of ShipSpec Class
     *
     * @param name the name of desired ship
     * @param size the size / length of desired ship
     */
    public ShipSpec(String name, int size) {
        this.name = name;
        this.size = size;
    }
}
