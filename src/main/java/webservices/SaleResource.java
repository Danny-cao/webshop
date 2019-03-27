package webservices;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import model.Product;
import model.Sale;

@Path("/sales")
public class SaleResource {
	
	SaleService service = ServiceProvider.getSaleService();
	ProductService prod = ServiceProvider.getProductService();
	
	@GET
	@Produces("application/json")
	public Response allSales() {

		List<Sale> sales = service.getAllSales();
		
		return Response.ok(sales).build();

	}
	
	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Response findByProductId(@PathParam("id") int id) {
		
		Product product = prod.getProductById(id);
		
		Sale sale = service.findByProduct(product);
		
		return Response.ok(sale).build();
	}
	

}
