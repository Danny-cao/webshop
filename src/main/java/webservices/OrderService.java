package webservices;

import model.Order;
import persistence.OrderPostgreSQLDAOImpl;

public class OrderService {
	private OrderPostgreSQLDAOImpl odao = new OrderPostgreSQLDAOImpl();
	
	public Order insert(Order order) {
		return odao.insert(order);
	}
	
	public Order getById(int id) {
		return odao.getById(id);
	}
}
