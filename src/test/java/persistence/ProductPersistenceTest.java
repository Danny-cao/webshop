package persistence;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import model.Product;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for product database persistence
 */
public class ProductPersistenceTest {

    @BeforeAll
    @DisplayName("setup")
    static void setup() {
    }

    @Test
    @DisplayName("Should return all products")
    void testGetItems() {
        ProductPostgreSQLDAOImpl productDao = new ProductPostgreSQLDAOImpl();
        List<Product> products = productDao.findAll();

        for (Product p : products) {
            int id = p.getId();
            String name = p.getName();
            double price = p.getPrice();
            String description = p.getDescription();

            System.out.println(p.getName());
            assertNotEquals(0, id, "ID of the product");
            assertNotNull(name, "Name of the product");
            assertNotEquals(0, price, "Price of the product");
            assertTrue(0 < price, "This has to be above the zero");
        }
    }

    @Test
    @DisplayName("Should return a product containing provided ID")
    void testGetByID() {
        ProductPostgreSQLDAOImpl productDao = new ProductPostgreSQLDAOImpl();
        List<Product> products = productDao.findAll();

        Product p = products.get(0);
        Product sameP = productDao.findById(p.getId());

        assertEquals(p, sameP, "Same product, different way of getting them (findAll / getById)");
    }

    @Test
    @DisplayName("Asking for a non-exsisting product, it should return null")
    void testGetByWrongID() {
        ProductPostgreSQLDAOImpl productDao = new ProductPostgreSQLDAOImpl();
        Product p = productDao.findById(0);

        assertNull(p);
    }

    @Test
    @DisplayName("Should return a product containing provided name")
    void testGetByName() {
        ProductPostgreSQLDAOImpl productDao = new ProductPostgreSQLDAOImpl();
        List<Product> products = productDao.findAll();

        Product p = products.get(0);
        Product sameP = productDao.findSingleProductByName(p.getName());

        assertEquals(p, sameP, "Same product, different way of getting them (findAll / getByName)");
    }

    @Test
    @DisplayName("Asking for a non-exsisting product, it should return null")
    void testGetByWrongName() {
        ProductPostgreSQLDAOImpl productDao = new ProductPostgreSQLDAOImpl();
        Product p = productDao.findSingleProductByName("@&#%^#&*@%^#");

        assertNull(p);
    }

    @Test
    @DisplayName("Should insert/update/delete new test product in database")
    void testInsertUpdateDeleteProduct() {
        CategoryPostgreSQLDAOImpl cpd = new CategoryPostgreSQLDAOImpl();
        ProductPostgreSQLDAOImpl productDao = new ProductPostgreSQLDAOImpl();

        Product insertTestProduct = new Product("Test Product", 20, "test", "Test");
        insertTestProduct.setCategory(cpd.findByName("shoes"));


        productDao.insert(insertTestProduct);
        Product sameInsertTestProduct = productDao.findSingleProductByName("Test Product");
        insertTestProduct.setId(sameInsertTestProduct.getId());

        assertEquals(insertTestProduct, sameInsertTestProduct, "Same 2 products, one created in Java then inserted in Database" +
                ", other found in database using the name of the old product");

        Product updateTestProduct = productDao.findSingleProductByName("Test Product");

        updateTestProduct.setName("Updated Test Product");
        updateTestProduct.setPrice(30);
        updateTestProduct.setDescription("Updated Test");
        productDao.update(updateTestProduct);

        Product sameUpdateTestProduct = productDao.findSingleProductByName("Updated Test Product");

        assertEquals(updateTestProduct, sameUpdateTestProduct, "Same 2 products, one updated in Java then updated in Database" +
                ", other found in database using the name of the updated product");

        Product deleteTestProduct = productDao.findSingleProductByName("Updated Test Product");
        productDao.delete(deleteTestProduct);

        assertNull(productDao.findSingleProductByName("Updated Test Product"), "Trying to find product that was deleted");
    }

    @Test
    @DisplayName("Should return false while trying to update a non-existing product, should return false")
    void testUpdateFalseProduct() {
        ProductPostgreSQLDAOImpl productDao = new ProductPostgreSQLDAOImpl();
        CategoryPostgreSQLDAOImpl cpd = new CategoryPostgreSQLDAOImpl();

        Product p = new Product(0, "a", 1, "b", "c");
        p.setCategory(cpd.findByName("shoes"));
        assertFalse(productDao.update(p));
    }

    @Test
    @DisplayName("Should return false while trying create a product with negative and zero as price")
    void testZeroNegativePriceProduct() {
        ProductPostgreSQLDAOImpl productDao = new ProductPostgreSQLDAOImpl();
        CategoryPostgreSQLDAOImpl cpd = new CategoryPostgreSQLDAOImpl();

        Product pZero = new Product("a", 0, "b", "c");
        pZero.setCategory(cpd.findByName("shoes"));
        assertFalse(productDao.insert(pZero), "Inserting product with price zero");

        Product pNegative = new Product("a", -1, "b", "c");
        pNegative.setCategory(cpd.findByName("shoes"));
        assertFalse(productDao.insert(pNegative), "Inserting product with negative price");
    }
}
