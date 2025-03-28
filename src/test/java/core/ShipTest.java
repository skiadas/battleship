package core;

import static core.Ship.Direction.*;
import static org.junit.jupiter.api.Assertions.*;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.Test;

class ShipTest {
    Coord coordinate = new Coord(1, 2);
    Ship ship = new Ship(coordinate, 3, VERTICAL, "BattleShip");
    Grid grid = new Grid(5, 5);
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("test");

    @Test
    public void getStartRowReturnsStartRowFromField() {
        assertEquals(1, ship.getStartRow());
    }

    @Test
    public void getStartColReturnsStartColFromField() {
        assertEquals(2, ship.getStartCol());
    }

    @Test
    public void getSizeReturnsSizeFromField() {
        assertEquals(3, ship.getSize());
    }

    @Test
    public void getDirectionReturnsDirectionFromField() {
        assertEquals(VERTICAL, ship.getDirection());
    }

    @Test
    public void getNameReturnsNameFromField() {
        assertEquals("BattleShip", ship.getName());
    }

    @Test
    public void hitsShipCorrectlyReturnsTrue() {
        Coord coordInShip = new Coord(1, 2);
        assertTrue(ship.containsCoord(coordInShip));
    }

    @Test
    public void hitsShipCorrectlyReturnsFalse() {
        Coord coordNotInShip = new Coord(3, 3);
        assertFalse(ship.containsCoord(coordNotInShip));
    }

    @Test
    public void shipOverlapsReturnsTrueWhenShipsOverlap() {
        Coord c1 = new Coord(1, 2);
        Ship otherShip = new Ship(c1, 3, VERTICAL, "BattleShip");
        assertTrue(ship.isOverlapping(otherShip));
    }

    @Test
    public void shipOverlapsReturnsFalseWhenShipsDoNotOverlap() {
        Coord c1 = new Coord(4, 4);
        Ship otherShip = new Ship(c1, 2, VERTICAL, "BattleShip");
        assertFalse(ship.isOverlapping(otherShip));
    }

    @Test
    public void shipStartCoordStoredInDatabase() {
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(ship);
        entityManager.getTransaction().commit();
        entityManager.close();
        EntityManager entityManager2 = factory.createEntityManager();
        entityManager2.getTransaction().begin();
        Ship ship2 = entityManager2.find(Ship.class, ship.getId());
        assertTrue(ship.getStartcoordinate().isEqual(ship2.getStartcoordinate()));
        assertTrue(ship2.getName().equals(ship.getName()));
        entityManager2.close();
    }
}
