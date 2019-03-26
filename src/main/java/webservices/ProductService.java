package webservices;

import persistence.ProductPostgreSQLDAOImpl;
import persistence.Product;
import java.util.List;

public class ProductService {
	ProductPostgreSQLDAOImpl pobd = new ProductPostgreSQLDAOImpl();
	
	public List<Product> getAllProducts(){
		return pobd.findAll();
	}
	
	public List<Product> getProductsByCategory(String category){
		return pobd.findByCategory(category);
	}
	
	public List<String> getAllCategories(){
		return pobd.findAllCategories();
	}

	public List<Product> getAllProductsOnSale() {
		return pobd.findAllOnSale();
	}
}

