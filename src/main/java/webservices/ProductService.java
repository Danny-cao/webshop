package webservices;

import persistence.ProductOracleBaseDAOImpl;
import persistence.Product;
import java.util.List;

public class ProductService {
	ProductOracleBaseDAOImpl pobd = new ProductOracleBaseDAOImpl();
	
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

