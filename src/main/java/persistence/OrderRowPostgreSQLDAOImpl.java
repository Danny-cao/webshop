package persistence;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Order;
import model.OrderRow;

public class OrderRowPostgreSQLDAOImpl extends PostgreSQLBaseDao implements OrderRowDAO {

	@Override
	public OrderRow insert(OrderRow orderRow) {
		
		try (Connection con = getConnection()) {
			
			Statement stmt = con.createStatement();

			String query = "INSERT INTO bestellingsregel (bestelling, product, aantal, prijs) VALUES(1, 1, 2, 30)";
			
			stmt.executeUpdate(query);
			
			return orderRow;
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			return null;
			}
	}

	@Override
	public ArrayList<OrderRow> getByOrder(Order order) {
		// TODO Auto-generated method stub
		return null;
	}

}
