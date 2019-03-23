package webservices;

import persistence.ProductOracleBaseDAOImpl;
import persistence.Product;
import java.util.List;

public class ProductService {
	ProductOracleBaseDAOImpl pobd = new ProductOracleBaseDAOImpl();
	
	public List<Product> getAllProducts(){
		return pobd.findAll();
	}
}

