package persistence;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import model.Order;

public class OrderPostgreSQLDAOImpl extends PostgreSQLBaseDao implements OrderDAO{

	@Override
	public Order insert(Order order) {
		
		// Momenteel Account en afleveradres nog statisch
		
		try (Connection con = getConnection()) {
			
			Statement stmt = con.createStatement();

			String query = "INSERT INTO bestelling (afleveradres, account) VALUES(1, 1)";
			
			stmt.executeUpdate(query);
			return order;
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			return null;
			}
	}

	@Override
	public Order getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
