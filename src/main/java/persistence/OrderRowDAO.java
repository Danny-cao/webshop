package persistence;

import java.util.ArrayList;

import model.Order;
import model.OrderRow;

public interface OrderRowDAO {
	
	OrderRow insert(OrderRow orderRow);
	ArrayList<OrderRow> getByOrder(Order order); 

}
