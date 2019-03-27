package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import model.Order;
import model.OrderRow;

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

	public Order delete(Order order) {
		try {
			Connection conn = super.getConnection();

			String query = "delete from bestelling where id = ?;";

			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, order.getId());

			conn.close();
			return order;
		} catch (SQLException e) {
			return null;
		}
	}

	@Override
	public Order getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
