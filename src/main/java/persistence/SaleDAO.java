package persistence;

import java.util.List;

import model.Product;
import model.Sale;

public interface SaleDAO {
	
	Sale findByProduct(Product product);
	List<Sale> findAll();
}
