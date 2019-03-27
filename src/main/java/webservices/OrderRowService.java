package webservices;

import model.OrderRow;
import persistence.OrderRowPostgreSQLDAOImpl;

public class OrderRowService {
	private OrderRowPostgreSQLDAOImpl odao = new OrderRowPostgreSQLDAOImpl();
	
	public OrderRow insert(OrderRow orderRow) {
		return odao.insert(orderRow);
	}
}
