package persistence;

import model.Account;
import model.Address;
import model.Order;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for order database persistence
 */
public class OrderPersistenceTest {

    @Test
    @DisplayName("Should successfully insert and delete same order")
    void testInsertionDeletion() {
        OrderPostgreSQLDAOImpl ops = new OrderPostgreSQLDAOImpl();

        // Succesful creation testing not possible with current functions
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

        assertNull(o);
    }
}
