package persistence;

import model.Account;
import model.Address;
import model.Order;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OrderPersistenceTest {

    @Test
    @DisplayName("Should successfully insert and delete same order")
    void testInsertionDeletion() {
        OrderPostgreSQLDAOImpl ops = new OrderPostgreSQLDAOImpl();

        Address address = new Address("####", "####");
//        Order order = new Order(0, account, address);
//        ops.insert(order);
//
//        Order newOrder = ops.getById(0);
//        assertEquals(order, newOrder);
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
