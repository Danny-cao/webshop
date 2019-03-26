package persistence;

import model.Order;

public interface OrderDAO {
	
	Order insert();
	Order getById(int id);
}
