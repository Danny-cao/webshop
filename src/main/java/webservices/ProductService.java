package webservices;

import model.Categorie;
import persistence.CategoriePostgreSQLDAOImpl;
import model.Product;
import persistence.ProductPostgreSQLDAOImpl;

import java.util.List;

public class ProductService {
	private ProductPostgreSQLDAOImpl pobd = new ProductPostgreSQLDAOImpl();
	private CategoriePostgreSQLDAOImpl cad = new CategoriePostgreSQLDAOImpl();
	
	public List<Product> getAllProducts(){
		return pobd.findAll();
	}
	
	public List<Product> getProductsByCategory(String category){
		return pobd.findByCategory(category);
	}
	
	public List<Categorie> getAllCategories(){
		return pobd.findAllCategories();
	}

	public List<Product> getAllProductsOnSale() {
		return pobd.findAllOnSale();
	}

	public Categorie getCategorie(String name) {
		return cad.findByName(name);
	}
}

