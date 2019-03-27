package persistence;

import model.Product;

import java.util.List;

interface ProductDAO {
	List<Product> findAll();
	List<Product> findByCategory(String category);
	Product findById(int id);

    Product findSingleProductByName(String name);

    boolean insert(Product product);

    boolean update(Product product);

    boolean delete(Product product);
}
