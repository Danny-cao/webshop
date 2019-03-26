package persistence;

import java.util.ArrayList;

import model.Order;
import model.OrderRow;

public interface OrderRowDAO {
	
	OrderRow insert();
	ArrayList<OrderRow> getByOrder(Order order); 

}
