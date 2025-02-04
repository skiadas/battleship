package core;

public class ShipSpec {
    /** The name of the desired ship */
    public final String name;

    /** The size / length of the desired ship */
    public final int size;
    public ShipSpec(String name, int size) {
        this.name = name;
        this.size = size;
    }
}
