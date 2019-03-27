package webservices;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import model.Account;
import model.Address;
import model.Order;
import model.OrderRow;
import model.Product;

@Path("/order")
public class OrderResource {
	
	
	@POST
	@Produces("application/json")
	public Response confirmOrder(@FormParam("order") String orderList, 
								 @FormParam("account") int accountId, 
								 @FormParam("address") int addressId) {
		
		OrderService service = ServiceProvider.getOrderService();
		ProductService pro = ServiceProvider.getProductService();
		OrderRowService oserv = ServiceProvider.getOrderRowService();
		
		
		//testdata
		accountId = 1;
		addressId = 1;
		Order order = new Order(1, new Account(accountId), new Address(addressId, "street", "number"));
		
		Order newOrder = service.insert(order);
		
		System.out.println("orderid: " + newOrder.getId());
		
		//test order
		orderList = "121212345";
		
		// split into list
		String [] strArr = orderList.split("");
		
		// turn into int list
		List<Integer> numbers = new ArrayList<>(strArr.length);
		
		// add orders from split list
		for(int i = 0;i < strArr.length;i++)
		{
		   numbers.add(Integer.parseInt(strArr[i]));
		}
		
		// remove duplicates
		List<Integer> listWithoutDuplicates = numbers.stream().distinct().collect(Collectors.toList());
		
		// find Products by Id and add to list
		
		List<Product> products = new ArrayList<>();
		
		for (Integer number : listWithoutDuplicates) {
			
			Product product = pro.getProductById(number);
			products.add(product);
		}
		
		for (Product p: products ) {
			
			// get bestelling id
			int id = newOrder.getId();
			
			// get product id count from original list
			int count = 0;
			
			for(int n: numbers) {
				System.out.println(n + " " + p.getId());
				if(p.getId() == n) {
					count++;
				}
			}
			
			System.out.println(p.getId() + " has count: " + count);
			
			// count * product.price
			float price = (float) (p.getPrice() * count);
			
			// insert OrderRow to db
			oserv.insert(new OrderRow(newOrder, p, count, price));
			
		}
		
		return Response.ok().build();
	}
}
