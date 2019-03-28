package persistence;

import model.Product;
import model.Sale;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Unit test for sale database persistence
 */
public class SalePersistenceTest {

    @Test
    @DisplayName("Test finding sales by product")
    void testFindByProduct() {
        SalePostgreSQLDAOImpl spd = new SalePostgreSQLDAOImpl();
        ProductPostgreSQLDAOImpl ppd = new ProductPostgreSQLDAOImpl();

        Product testProduct = ppd.findById(2);
        Sale sale = spd.findByProduct(testProduct);
        assertNotNull(sale, "Should be not null, finding a sale");

        Product testProduct2 = ppd.findById(1);
        Sale sale2 = spd.findByProduct(testProduct2);
        assertNull(sale2, "Should be null, not finding a sale");
    }

    @Test
    @DisplayName("Test finding all sales")
    void testFindAll() {
        SalePostgreSQLDAOImpl spd = new SalePostgreSQLDAOImpl();
        ProductPostgreSQLDAOImpl ppd = new ProductPostgreSQLDAOImpl();

        List<Sale> sales = spd.findAll();
        assertNotNull(sales, "Should be not null, finding a list containing Sales");
        for (Sale s : sales) {
            assertNotNull(s.getBegin());
            assertNotNull(s.getEnd());
            assertNotEquals(0, s.getPrice());
            assertTrue(0 < s.getPrice(), "This has to be above the zero");
            assertNotNull(s.getProduct());
        }
    }
}
