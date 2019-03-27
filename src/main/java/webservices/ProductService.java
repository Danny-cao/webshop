package webservices;

import model.Category;
import model.Product;
import persistence.CategoryPostgreSQLDAOImpl;
import persistence.ProductPostgreSQLDAOImpl;

import java.util.List;

public class ProductService {

	private ProductPostgreSQLDAOImpl pobd = new ProductPostgreSQLDAOImpl();
	private CategoryPostgreSQLDAOImpl cad = new CategoryPostgreSQLDAOImpl();
	
	public List<Product> getAllProducts(){
		return pobd.findAll();
	}
	
	public List<Product> getProductsByCategory(String category){
		return pobd.findByCategory(category);
	}
	
	public List<Category> getAllCategories(){
		return pobd.findAllCategories();
	}

	public List<Product> getAllProductsOnSale() {
		return pobd.findAllOnSale();
	}

	public Category getCategory(String name) {
		return cad.findByName(name);
	}

	public Product getProductById(int id) {
		return pobd.findById(id);
	}

	public List<Product> findProducts(String string) {
		return pobd.findProducts(string);
	}

	public boolean createProduct(int id, String name, double price, String description, String nameCategory) {
		Category category = getCategory(nameCategory);
		try {
			Product p = new Product(id, name, price, description);
			p.setCategory(category);
			pobd.insert(p);
			return true;
		} catch (NullPointerException e) {
			System.out.println("Category not found");
			return false;
		}
	}
}

