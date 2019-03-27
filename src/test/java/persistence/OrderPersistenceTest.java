package persistence;

import model.Order;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OrderPersistenceTest {

    @Test
    @DisplayName("Should successfully insert and delete same order")
    void testInsertionDeletion() {


        assertTrue(false);
    }

    @Test
    @DisplayName("Should return a order containing provided ID")
    void testGetByID() {
        OrderPostgreSQLDAOImpl opd = new OrderPostgreSQLDAOImpl();
        Order o = opd.getById(1);

        assertEquals(1, o.getId());
    }

    @Test
    @DisplayName("Asking for a non-exsisting order, it should return null")
    void testGetByWrongID() {
        OrderPostgreSQLDAOImpl opd = new OrderPostgreSQLDAOImpl();
        Order o = opd.getById(0);

        // GetByID not done yet -> test always fails
        assertNotNull(null);
    }
}
