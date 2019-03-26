package persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Product;
import model.Sale;

public class SalePostgreSQLDAOImpl extends PostgreSQLBaseDao implements SaleDAO{

	@Override
	public Sale findByProduct(Product product) {
		return null;
	}

	@Override
	public List<Sale> findAll() {
		
		ProductPostgreSQLDAOImpl pobd = new ProductPostgreSQLDAOImpl();
		
		List<Sale> sales = new ArrayList<>();
		
		try (Connection con = super.getConnection()) {
		      Statement stmt = con.createStatement();
		      
		      String query = "SELECT product, vandatum, totdatum, prijs FROM aanbieding";
		      
		      ResultSet dbResultSet = stmt.executeQuery(query);

		      while (dbResultSet.next()) {
		    	    int productnumber = dbResultSet.getInt("product");
			        Date begin = dbResultSet.getDate("vandatum");        
			        Date end = dbResultSet.getDate("totdatum");
			        float price = dbResultSet.getFloat("prijs");
			       
			        Product product = pobd.findById(productnumber);
			        
			        sales.add(new Sale(product, begin, end, price));
		      }
		      
		      return sales;
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
