package persistence;

import model.Order;
import model.OrderRow;
import model.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for order row database persistence
 */
public class OrderRowPersistenceTest {

    @Test
    @DisplayName("Testing the insertion of order rows")
    void insertTest() {
        ProductPostgreSQLDAOImpl ppd = new ProductPostgreSQLDAOImpl();
        OrderPostgreSQLDAOImpl opd = new OrderPostgreSQLDAOImpl();

        // Succesful creation testing not possible with current functions
    }

    @Test
    @DisplayName("Testing getting orderrules by order")
    void getByOrderTest() {
        ProductPostgreSQLDAOImpl ppd = new ProductPostgreSQLDAOImpl();
        OrderPostgreSQLDAOImpl opd = new OrderPostgreSQLDAOImpl();
        OrderRowPostgreSQLDAOImpl oprd = new OrderRowPostgreSQLDAOImpl();

        Order order = opd.getById(1);
        List<OrderRow> rows = oprd.getByOrder(order);
        assertNotNull(rows, "Should be not null, finding a list with orderrules");
        for (OrderRow ow : rows) {
            assertNotEquals(0, ow.getCount());
            assertNotEquals(0, ow.getId());
            assertNotNull(ow.getOrder());
            assertNotEquals(0, ow.getPrice());
            assertTrue(0 < ow.getPrice(), "This has to be above the zero");
            assertNotNull(ow.getProduct());
        }
    }
}
