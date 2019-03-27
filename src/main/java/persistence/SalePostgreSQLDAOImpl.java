package persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Category;
import model.Product;
import model.Sale;

public class SalePostgreSQLDAOImpl extends PostgreSQLBaseDao implements SaleDAO{
	
	ProductPostgreSQLDAOImpl pobd = new ProductPostgreSQLDAOImpl();

	@Override
	public Sale findByProduct(Product product) {
		
        try (Connection con = super.getConnection()) {

            String query = "select * from aanbieding where product = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, product.getId());
            ResultSet dbResultSet = stmt.executeQuery();
            
            
            if(dbResultSet.next() == false) {
            	return null;
            }

            Date begin = dbResultSet.getDate("vandatum");
            Date end = dbResultSet.getDate("totdatum");
            float price = dbResultSet.getFloat("prijs");
            
            return new Sale(product, begin, end, price);
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
	}

	@Override
	public List<Sale> findAll() {
		
		
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
