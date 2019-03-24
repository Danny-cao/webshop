package webservices;


import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import persistence.Product;

@Path("/products")
public class ProductResource {
	@GET
	@Produces("application/json")
	public String allProducts() {
		// comment voor de commit
		ProductService service = ServiceProvider.getProductService();
		JsonArrayBuilder jab = Json.createArrayBuilder();
		for (Product p : service.getAllProducts()) {
			JsonObjectBuilder con = Json.createObjectBuilder();
			con.add("name", p.getName());
			con.add("id", p.getId());
			con.add("price", p.getPrice());
			jab.add(con);
		}

		JsonArray array = jab.build();
		return array.toString();
	}

}
