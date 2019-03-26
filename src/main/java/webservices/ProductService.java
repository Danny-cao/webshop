package webservices;

import persistence.ProductOracleDAOImpl;
import persistence.Categorie;
import persistence.CategorieOracleDAOImpl;
import persistence.Product;
import java.util.List;

public class ProductService {
	ProductOracleDAOImpl pobd = new ProductOracleDAOImpl();
	CategorieOracleDAOImpl cad = new CategorieOracleDAOImpl();
	
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

