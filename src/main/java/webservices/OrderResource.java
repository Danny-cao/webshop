package webservices;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import model.Account;
import model.Address;
import model.Order;

@Path("/order")
public class OrderResource {
	
	
	@POST
	@Produces("application/json")
	public Response confirmOrder(@FormParam("order") String orderList, 
								 @FormParam("account") int accountId, 
								 @FormParam("address") int addressId) {
		
		OrderService service = ServiceProvider.getOrderService();
		
		//testdata
		Order order = new Order(1, new Account(1), new Address("street", "number"));
		accountId = 1;
		addressId = 1;
		
		orderList = "121212345";
		
		String [] strArr = orderList.split("");
		
		
		
		
		service.insert(order);
		
		return Response.ok().build();
	}
}
