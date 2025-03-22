package core;

import java.util.ArrayList;
import java.util.List;

public class ShipList {
    private final List<Ship> ships = new ArrayList<>();

    public ShipList(List<Ship> ships) {
        this.ships.addAll(ships);
    }

    public void addShip(Ship ship) {
        ships.add(ship);
    }

    public List<Ship> getShips() {
        return new ArrayList<>(ships);
    }
}
