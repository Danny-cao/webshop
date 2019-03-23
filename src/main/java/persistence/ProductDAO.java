package persistence;

import java.util.List;

interface ProductDAO {
	List<Product> findAll();
	Product findById(int id);
	Product findByName(String name);
	
	boolean insertProduct();
	boolean updateProduct();
	boolean deleteProduct();
}
