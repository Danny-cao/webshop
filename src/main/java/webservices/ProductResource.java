package webservices;


import javax.json.*;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import model.Product;

import java.util.List;

@Path("/products")
public class ProductResource {

	@GET
	@Produces("application/json")
	public String allProducts() {
		ProductService service = ServiceProvider.getProductService();
		JsonArrayBuilder jab = Json.createArrayBuilder();
		for (Product p : service.getAllProducts()) {
			jab.add(createJsonObjectBuilder(p));
		}

		JsonArray array = jab.build();
		return array.toString();
	}

    @GET
	@Path("sale")
	@Produces("application/json")
	public String allProductsOnSale() {
		ProductService service = ServiceProvider.getProductService();
		JsonArrayBuilder jab = Json.createArrayBuilder();
		for (Product p : service.getAllProductsOnSale()) {
			jab.add(createJsonObjectBuilder(p));
		}

		JsonArray array = jab.build();
		return array.toString();
	}

    @GET
	@Path("/{id}")
	@Produces("application/json")
	public String getProductById(@PathParam("id") int id) {
		ProductService service = ServiceProvider.getProductService();
		Product p = service.getProductById(id);

		JsonObject jo = createJsonObjectBuilder(p).build();

		return jo.toString();
	}

	@GET
	@Path("/search/{searchparam}")
	@Produces("application/json")
	public String findProducts(@PathParam("searchparam") String searchparam) {
		ProductService service = ServiceProvider.getProductService();
		List<Product> products = service.findProducts(searchparam);
		JsonArrayBuilder jab = Json.createArrayBuilder();

		for (Product p : products) {
			jab.add(createJsonObjectBuilder(p));
		}
		JsonArray ja = jab.build();

		return ja.toString();
	}

	// TODO create product rest service
	// TODO modify product rest service
	// TODO delete product rest service

	private JsonObjectBuilder createJsonObjectBuilder(Product p) {
		JsonObjectBuilder con = Json.createObjectBuilder();

		con.add("name", p.getName());
		con.add("id", p.getId());
		con.add("price", p.getPrice());
		con.add("categorie", p.getCategory().getName());
		con.add("description", p.getDescription());

		return con;
	}
}
