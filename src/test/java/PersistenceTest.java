import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import model.Category;
import model.Product;
import persistence.CategoryPostgreSQLDAOImpl;
import persistence.ProductPostgreSQLDAOImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for the database persistence
 */
public class PersistenceTest {

    @BeforeAll
    @DisplayName("setup")
    static void setup() {
    }

    @Test
    @DisplayName("Should return all products on sale")
    void testGetItemsOnSale() {
        ProductPostgreSQLDAOImpl productDao = new ProductPostgreSQLDAOImpl();
        List<Product> productsOnSale = productDao.findAllOnSale();

        // TODO: Check for on sale?
//        for (Product p : productsOnSale) {
//
//        }

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
        }
    }

    @Test
    @DisplayName("Should return all categories")
    void testGetCategories() {
        ProductPostgreSQLDAOImpl productDao = new ProductPostgreSQLDAOImpl();
        List<Category> categories = productDao.findAllCategories();
        assertNotNull(categories, "List of categories");
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
    @DisplayName("Should return a product containing provided name")
    void testGetByName() {
        ProductPostgreSQLDAOImpl productDao = new ProductPostgreSQLDAOImpl();
        List<Product> products = productDao.findAll();

        Product p = products.get(0);
        Product sameP = productDao.findSingleProductByName(p.getName());

        assertEquals(p, sameP, "Same product, different way of getting them (findAll / getByName)");
    }

    @Test
    @DisplayName("Should insert/update/delete new test product in database")
    void testInsertUpdateDeleteProduct() {
        CategoryPostgreSQLDAOImpl cpd = new CategoryPostgreSQLDAOImpl();

        Product insertTestProduct = new Product("Test Product", 20, "Test");
        insertTestProduct.setCategory(cpd.findByName("shirt"));

        ProductPostgreSQLDAOImpl productDao = new ProductPostgreSQLDAOImpl();
        productDao.insertProduct(insertTestProduct);
        Product sameInsertTestProduct = productDao.findSingleProductByName("Test Product");
        insertTestProduct.setId(sameInsertTestProduct.getId());

        assertEquals(insertTestProduct, sameInsertTestProduct, "Same 2 products, one created in Java then inserted in Database" +
                ", other found in database using the name of the old product");

        Product updateTestProduct = productDao.findSingleProductByName("Test Product");

        updateTestProduct.setName("Updated Test Product");
        updateTestProduct.setPrice(30);
        updateTestProduct.setDescription("Updated Test");
        productDao.updateProduct(updateTestProduct);

        Product sameUpdateTestProduct = productDao.findSingleProductByName("Updated Test Product");

        assertEquals(updateTestProduct, sameUpdateTestProduct, "Same 2 products, one updated in Java then updated in Database" +
                ", other found in database using the name of the updated product");

        Product deleteTestProduct = productDao.findSingleProductByName("Updated Test Product");
        productDao.deleteProduct(deleteTestProduct);

        assertNull(productDao.findSingleProductByName("Updated Test Product"), "Trying to find product that was deleted");
    }
}
