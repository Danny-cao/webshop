package webservices;

import java.util.List;

import model.Product;
import model.Sale;
import persistence.SalePostgreSQLDAOImpl;

public class SaleService {
	private SalePostgreSQLDAOImpl sad = new SalePostgreSQLDAOImpl();
	
	public List<Sale> getAllSales(){
		return sad.findAll();
	}
	
	public Sale findByProduct(Product product) {
		return sad.findByProduct(product);
	} 
}
