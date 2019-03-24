package persistence;

import java.util.List;

interface ProductDAO {
	List<Product> findAll();
	List<Product> findByCategory(String category);
	Product findById(int id);
	Product findByName(String name);
	
	boolean insertProduct();
	boolean updateProduct();
	boolean deleteProduct();
}
