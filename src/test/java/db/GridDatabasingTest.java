package db;

import core.DefaultGridBuilder;
import core.Grid;
import core.ShipList;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GridDatabasingTest {

    EntityManagerFactory factory = Persistence.createEntityManagerFactory("test");

    @Test
    public void gridStoredInDatabaseAndRestoredCorrectly() {
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

        Grid grid = new Grid(5, 5, DefaultGridBuilder.defaultShipsFor5x5());
        ShipList shipList = grid.getShipListObject();
        entityManager.persist(shipList);
        entityManager.persist(grid);

        entityManager.getTransaction().commit();
        entityManager.close();

        EntityManager entityManager2 = factory.createEntityManager();
        entityManager2.getTransaction().begin();

        Grid grid2 = entityManager2.find(Grid.class, grid.getId());

        assertNotNull(grid2);
        assertEquals(grid.numRows(), grid2.numRows());
        assertEquals(grid.numCols(), grid2.numCols());
        assertEquals(grid.getShipList().size(), grid2.getShipList().size());

        entityManager2.getTransaction().commit();
        entityManager2.close();
    }
}
