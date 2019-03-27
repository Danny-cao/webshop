package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Order;
import model.OrderRow;

public class OrderRowPostgreSQLDAOImpl extends PostgreSQLBaseDao implements OrderRowDAO {

	@Override
	public OrderRow insert(OrderRow orderRow) {
		
		try (Connection con = getConnection()) {

			String query = "INSERT INTO bestellingsregel (bestelling, product, aantal, prijs) VALUES(?, ?, ?, ?)";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setInt(1, orderRow.getOrder().getId());
			stmt.setInt(2, orderRow.getProduct().getId());
			stmt.setInt(3, orderRow.getCount());
			stmt.setDouble(4, orderRow.getPrice());
			
			return orderRow;
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			return null;
			}
	}

	public OrderRow delete(OrderRow orderRow) {
		try {
			Connection conn = super.getConnection();

			String query = "delete from bestellingsregel where id = ?;";

			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, orderRow.getId());

			conn.close();
			return orderRow;
		} catch (SQLException e) {
			return null;
		}
	}



	@Override
	public ArrayList<OrderRow> getByOrder(Order order) {
		// TODO Auto-generated method stub
		return null;
	}

}
