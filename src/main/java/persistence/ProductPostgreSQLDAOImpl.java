package persistence;

import model.Category;
import model.Product;

import java.sql.Connection;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class ProductPostgreSQLDAOImpl extends PostgreSQLBaseDao implements ProductDAO {
    public List<Product> findAllOnSale() {
        List<Product> products = new ArrayList<Product>();
        try (Connection con = super.getConnection()) {
            Statement stmt = con.createStatement();
            ResultSet dbResultSet = stmt.executeQuery
                    ("SELECT product.id, product.naam, product.afbeelding, product.categorie, product.beschrijving, aanbieding.prijs\n" +
                            "FROM product\n" +
                            "INNER JOIN aanbieding\n" +
                            "ON product.id = aanbieding.product where current_timestamp < aanbieding.totdatum and current_timestamp > aanbieding.vandatum");
            while (dbResultSet.next()) {
                products.add(getProductOutResultset(dbResultSet));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return products;
    }

    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        try (Connection con = super.getConnection()) {
            Statement stmt = con.createStatement();
            ResultSet dbResultSet = stmt.executeQuery("select * from product");
            while (dbResultSet.next()) {
                products.add(getProductOutResultset(dbResultSet));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return products;
    }

    public Product findById(int id) {
        try {
            Connection conn = super.getConnection();

            String queryText = "SELECT * FROM product WHERE id = ?;";
            PreparedStatement stmt = conn.prepareStatement(queryText);
            stmt.setInt(1, id);
            ResultSet result = stmt.executeQuery();
            System.out.println(result.next());

            Product p = getProductOutResultset(result);

            conn.close();
            return p;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Product findSingleProductByName(String name) {
        try {
            Connection conn = super.getConnection();
            CategoryPostgreSQLDAOImpl cpsd = new CategoryPostgreSQLDAOImpl();

            String queryText = "SELECT * FROM product WHERE naam = ?;";
            PreparedStatement stmt = conn.prepareStatement(queryText);
            stmt.setString(1, name);
            ResultSet result = stmt.executeQuery();

            result.next();
            Product p = getProductOutResultset(result);

            conn.close();
            return p;
        } catch (SQLException e) {
            System.out.println("Product with name not found / More than 1 product found");
            return null;
        }
    }

    public List<Product> findProducts(String string) {
        try {
            Connection conn = super.getConnection();
            CategoryPostgreSQLDAOImpl cpsd = new CategoryPostgreSQLDAOImpl();

            String queryText = "SELECT * " +
                    "FROM product " +
                    "WHERE (length(?) = 0 OR lower(naam) LIKE lower(?)) and (? = 0 OR categorie = ?)";
            PreparedStatement stmt = conn.prepareStatement(queryText);
            stmt.setString(1, string);
            stmt.setString(2, string);
            stmt.setString(3, string);
            stmt.setString(4, string);
            ResultSet result = stmt.executeQuery();

            List<Product> products = new ArrayList<>();

            while (result.next()) {
                products.add(getProductOutResultset(result));
            }

            conn.close();
            return products;
        } catch (SQLException e) {
            System.out.println("Product with name not found");
            return null;
        }
    }

    public List<Product> findByCategory(String category) {
        List<Product> products = new ArrayList<Product>();
        try (Connection con = super.getConnection()) {
            String query = "select * from product where categorie = ?;";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, category);
            ResultSet dbResultSet = stmt.executeQuery();
            while (dbResultSet.next()) {
                products.add(getProductOutResultset(dbResultSet));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return products;
    }

    public List<Category> findAllCategories() {
        List<Category> categories = new ArrayList<Category>();
        try (Connection con = super.getConnection()) {
            Statement stmt = con.createStatement();
            ResultSet dbResultSet = stmt.executeQuery("select naam, omschrijving, afbeelding from categorie");
            while (dbResultSet.next()) {
                categories.add(new Category(dbResultSet.getString("naam")
                        ,dbResultSet.getString("omschrijving")
                        ,dbResultSet.getString("afbeelding")));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return categories;

    }

    public boolean insert(Product product) {
        try {
            Connection conn = super.getConnection();
            String query = "INSERT INTO product (naam, prijs, categorie, afbeelding, beschrijving)" +
                    "VALUES (?, ?, ?, ?, ?);";

            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, product.getName());
            stmt.setDouble(2, product.getPrice());
            stmt.setString(3, product.getCategory().getName());
            stmt.setString(4, product.getPicture());
            stmt.setString(5, product.getDescription());

            boolean status = stmt.execute();
            conn.close();
            return status;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean update(Product product) {
        try {
            Connection conn = super.getConnection();
            String query = "update product " +
                    "set naam = ?, prijs = ?, categorie = ?, afbeelding = ?, beschrijving = ?" +
                    "where id = ?;";

            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, product.getName());
            stmt.setDouble(2, product.getPrice());
            stmt.setString(3, product.getCategory().getName());
            stmt.setString(4, product.getPicture());
            stmt.setString(5, product.getDescription());
            stmt.setInt(6, product.getId());

            boolean status = stmt.execute();
            conn.close();
            return status;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean delete(Product product) {
        try {
            Connection conn = super.getConnection();

            String query = "delete from product where id = ?;";

            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, product.getId());

            boolean status = stmt.execute();
            conn.close();
            return status;
        } catch (SQLException e) {
            return false;
        }
    }

    private Product getProductOutResultset(ResultSet dbResultSet) {
        try {
            CategoryPostgreSQLDAOImpl cpd = new CategoryPostgreSQLDAOImpl();

            int id = dbResultSet.getInt("id");
            String name = dbResultSet.getString("naam");
            double price = dbResultSet.getDouble("prijs");
            String picture = dbResultSet.getString("afbeelding");
            String description = dbResultSet.getString("beschrijving");
            Category category = cpd.findByName(dbResultSet.getString("categorie"));
            Product p = new Product(id, name, price, picture, description);
            p.setCategory(category);
            return p;
        } catch (SQLException e) {
            return null;
        }
    }

	@Override
	public List<Product> searchByString(String text) {
		
        List<Product> products = new ArrayList<>();
        try (Connection con = super.getConnection()) {
        	
        	String query = "SELECT * FROM product WHERE (length(?) = 0 OR lower(naam) LIKE lower(?))";
            PreparedStatement stmt = con.prepareStatement(query);

            stmt.setString(1, text);
            stmt.setString(2, "%" + text + "%");
            
            ResultSet dbResultSet = stmt.executeQuery();
            
            while (dbResultSet.next()) {
                products.add(getProductOutResultset(dbResultSet));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return products;
	}
}
