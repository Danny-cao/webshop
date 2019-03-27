package persistence;

import model.Order;

public interface OrderDAO {
	
	Order insert(Order order);
	Order getById(int id);
}
