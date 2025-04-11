package db;

import static org.junit.jupiter.api.Assertions.*;

import core.DefaultGridBuilder;
import core.Grid;
import core.ShipList;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.Test;

public class GridDatabasingTest {

    EntityManagerFactory factory = Persistence.createEntityManagerFactory("test");

    @Test
    public void gridStoredInDatabaseAndRestoredCorrectly() {
        Grid grid = createGridAndPersistToDatabase();
        Grid grid2 = findGridInDataBaseAndStore(grid);
        confirmGridsAreTheSame(grid2, grid);
    }

    private Grid createGridAndPersistToDatabase() {
        EntityManager entityManager = createEntityManagerAndBegin();

        Grid grid = new Grid(5, 5, DefaultGridBuilder.defaultShipsFor5x5());
        ShipList shipList = grid.getShipListObject();

        entityManager.persist(shipList);
        entityManager.persist(grid);

        commitAndCloseEntityManager(entityManager);
        return grid;
    }

    private Grid findGridInDataBaseAndStore(Grid grid) {
        EntityManager entityManager2 = createEntityManagerAndBegin();
        Grid grid2 = entityManager2.find(Grid.class, grid.getId());
        commitAndCloseEntityManager(entityManager2);
        return grid2;
    }

    private EntityManager createEntityManagerAndBegin() {
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();
        return entityManager;
    }

    private static void commitAndCloseEntityManager(EntityManager entityManager2) {
        entityManager2.getTransaction().commit();
        entityManager2.close();
    }

    private static void confirmGridsAreTheSame(Grid grid2, Grid grid) {
        assertNotNull(grid2);
        assertEquals(grid.numRows(), grid2.numRows());
        assertEquals(grid.numCols(), grid2.numCols());
        assertEquals(grid.getShipList().size(), grid2.getShipList().size());
    }
}
