package webservices;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import persistence.Product;

@Path("category")
public class CategoryRecource {
	@GET
	@Produces("application/json")
	public String  getAllCategories() {
		ProductService service = ServiceProvider.getProductService();
		JsonArrayBuilder jab = Json.createArrayBuilder();
		for (String category : service.getAllCategories()) {
			JsonObjectBuilder con = Json.createObjectBuilder();
			con.add("category", category);
			jab.add(con);
		}

		JsonArray array = jab.build();
		return array.toString();
	}
	
	
	@GET
	@Path("{product_category}")
	@Produces("application/json")
	public String getAllProductsByCategory(@PathParam("product_category") String product_category) {
		ProductService service = ServiceProvider.getProductService();
		JsonArrayBuilder jab = Json.createArrayBuilder();
		for (Product p : service.getProductsByCategory(product_category)) {
			JsonObjectBuilder con = Json.createObjectBuilder();
			con.add("name", p.getName());
			con.add("id", p.getId());
			con.add("price", p.getPrice());
			con.add("description", p.getDescription());
			jab.add(con);
		}

		JsonArray array = jab.build();
		return array.toString();
	}
}
