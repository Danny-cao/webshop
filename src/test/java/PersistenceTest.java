import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import model.Categorie;
import model.Product;
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
            // TODO: Moet Double zijn
            int price = p.getPrice();
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
        List<Categorie> categories = productDao.findAllCategories();
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
        Product sameP = productDao.findByName(p.getName());

        assertEquals(p, sameP, "Same product, different way of getting them (findAll / getByName)");
    }

    @Test
    @DisplayName("Should insert new test product in database")
    void testInsertProduct() {
        Product testProduct = new Product(9999, "Test Product", 20, "Test");

        ProductPostgreSQLDAOImpl productDao = new ProductPostgreSQLDAOImpl();
        productDao.insertProduct(testProduct);
        Product sameTestProduct = productDao.findById(9999);

        assertEquals(testProduct, sameTestProduct, "Same 2 products, one created in Java then inserted in Database" +
                ", other found in database using the name of the old product");
    }

    @Test
    @DisplayName("Should update test product in database")
    void testUpdateProduct() {
        ProductPostgreSQLDAOImpl productDao = new ProductPostgreSQLDAOImpl();
        Product testProduct = productDao.findById(9999);

        testProduct.setName("Updated Test Product");
        testProduct.setPrice(30);
        testProduct.setDescription("Updated Test");
        productDao.updateProduct(testProduct);

        Product sameTestProduct = productDao.findById(9999);

        assertEquals(testProduct, sameTestProduct, "Same 2 products, one updated in Java then updated in Database" +
                ", other found in database using the name of the updated product");
    }

    @Test
    @DisplayName("Should delete test product in database")
    void testDeleteProduct() {
        ProductPostgreSQLDAOImpl productDao = new ProductPostgreSQLDAOImpl();
        Product testProduct = productDao.findById(9999);
        productDao.updateProduct(testProduct);

        assertNull(productDao.findById(9999), "Trying to find product that was deleted");
    }


}
