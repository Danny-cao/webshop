package webservices;


import javax.json.*;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import model.Product;
import persistence.CategoryPostgreSQLDAOImpl;
import persistence.ProductPostgreSQLDAOImpl;

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

	@POST
	@Path("/create")
	@Consumes("application/json")
	public Response createProduct(@FormParam("name") String name,
								  @FormParam("price") double price,
								  @FormParam("picture") String picture,
								  @FormParam("category") String categoryString,
								  @FormParam("description") String description) {
		try {
			ProductPostgreSQLDAOImpl ppd = new ProductPostgreSQLDAOImpl();
			CategoryPostgreSQLDAOImpl cpd = new CategoryPostgreSQLDAOImpl();

			Product product = new Product(name, price, picture, description);
			product.setCategory(cpd.findByName(categoryString));

			if (!ppd.insert(product)) {
				throw new Exception("Insertion of product is not succesful");
			}

			return Response.ok().build();
		} catch (Exception e) {
			return Response.status(400).build();
		}
	}
	
	@PUT
	@Path("/update")
	@Consumes("application/json")
	public Response updateProduct(@FormParam("name") String name,
								  @FormParam("id") int id,
								  @FormParam("price") double price,
								  @FormParam("picture") String picture,
								  @FormParam("category") String categoryString,
								  @FormParam("description") String description) {
		try {
			ProductPostgreSQLDAOImpl ppd = new ProductPostgreSQLDAOImpl();
			CategoryPostgreSQLDAOImpl cpd = new CategoryPostgreSQLDAOImpl();

			Product product = new Product(id, name, price, picture);
			product.setCategory(cpd.findByName(categoryString));

			if (!ppd.update(product)) {
				throw new Exception("Update of product is not succesful");
			}

			return Response.ok().build();
		} catch (Exception e) {
			return Response.status(400).build();
		}
	}

	@DELETE
	@Path("/delete")
	@Consumes("application/json")
	public Response deleteProduct(@FormParam("name") String name,
								  @FormParam("id") int id,
								  @FormParam("price") double price,
								  @FormParam("picture") String picture,
								  @FormParam("category") String categoryString,
								  @FormParam("description") String description) {
		try {
			ProductPostgreSQLDAOImpl ppd = new ProductPostgreSQLDAOImpl();
			CategoryPostgreSQLDAOImpl cpd = new CategoryPostgreSQLDAOImpl();

			Product product = new Product(id, name, price, picture);
			product.setCategory(cpd.findByName(categoryString));

			if (!ppd.delete(product)) {
				throw new Exception("Deletion of product is not succesful");
			}

			return Response.ok().build();
		} catch (Exception e) {
			return Response.status(400).build();
		}
	}

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
