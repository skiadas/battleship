import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.Test;

public class EntityManagerTest {
    private EntityManagerFactory factory = Persistence.createEntityManagerFactory("test");

    @Test
        public void test() {
        EntityManager em = factory.createEntityManager();
    }
}