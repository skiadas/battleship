package core;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.Optional;

@Entity
public class ShipList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private final List<Ship> ships = new ArrayList<>();

    protected ShipList() {}

    public ShipList(List<Ship> ships) {
        this.ships.addAll(ships);
    }

    public void addShip(Ship ship) {
        ships.add(ship);
    }

    public List<Ship> getShips() {
        return new ArrayList<>(ships);
    }

    public long getId() {
        return id;
    }

    Optional<Ship> getShipAt(Coord coordinate, Grid grid) {
        for (Ship ship : getShips()) {
            if (ship.containsCoord(coordinate)) {
                return Optional.of(ship);
            }
        }
        return Optional.empty();
    }

    /**
     * Checks if all ships in the list satisfy a given predicate.
     *
     * @param predicate the condition to apply to each ship
     * @return true if the predicate returns true for all ships, otherwise false
     */
    public boolean all(Predicate<Ship> predicate) {
        for (Ship ship : ships) {
            if (!predicate.test(ship)) {
                return false;
            }
        }
        return true;
    }
}
