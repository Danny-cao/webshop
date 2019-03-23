package persistence;

import java.sql.Connection;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class ProductOracleBaseDAOImpl extends OracleBaseDAO implements ProductDAO {
	public List<Product> findAll() {
		List<Product> products = new ArrayList<Product>();
		try (Connection con = super.getConnection()){
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery("select * from product");
			while (dbResultSet.next()) {
				int id = dbResultSet.getInt("id");
				String name = dbResultSet.getString("naam");
				int price = dbResultSet.getInt("prijs");
				Product prod = new Product(id,name,price);
				products.add(prod);
			}
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	return products;
	}

	public Product findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Product findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean insertProduct() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean updateProduct() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean deleteProduct() {
		// TODO Auto-generated method stub
		return false;
	}



}
