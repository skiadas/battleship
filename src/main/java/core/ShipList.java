package core;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
}
