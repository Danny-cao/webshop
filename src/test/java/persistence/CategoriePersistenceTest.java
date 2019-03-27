package persistence;

import model.Category;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Unit test for category database persistence
 */
public class CategoriePersistenceTest {
    @Test
    @DisplayName("Should return all categories")
    void testGetCategories() {
        ProductPostgreSQLDAOImpl productDao = new ProductPostgreSQLDAOImpl();
        List<Category> categories = productDao.findAllCategories();
        assertNotNull(categories, "List of categories");
    }
}
