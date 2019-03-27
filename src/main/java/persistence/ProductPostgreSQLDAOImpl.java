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
                int id = dbResultSet.getInt("id");
                String name = dbResultSet.getString("naam");
                double price = dbResultSet.getDouble("prijs");
                String description = dbResultSet.getString("beschrijving");
                Product prod = new Product(id, name, price, description);
                products.add(prod);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return products;
    }

    public List<Product> findAll() {
        List<Product> products = new ArrayList<Product>();
        try (Connection con = super.getConnection()) {
            Statement stmt = con.createStatement();
            ResultSet dbResultSet = stmt.executeQuery("select * from product");
            while (dbResultSet.next()) {
                int id = dbResultSet.getInt("id");
                String name = dbResultSet.getString("naam");
                double price = dbResultSet.getDouble("prijs");
                String description = dbResultSet.getString("beschrijving");
                Product prod = new Product(id, name, price, description);
                products.add(prod);
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
            CategoryPostgreSQLDAOImpl cpsd = new CategoryPostgreSQLDAOImpl();

            String queryText = "SELECT naam, prijs, categorie, beschrijving FROM product WHERE id = ?;";
            PreparedStatement stmt = conn.prepareStatement(queryText);
            stmt.setInt(1, id);
            ResultSet result = stmt.executeQuery();
            System.out.println(result.next());

            String naam = result.getString("naam");
            Double prijs = result.getDouble("prijs");
            Category category = cpsd.findByName(result.getString("categorie"));
            String beschrijving = result.getString("beschrijving");

            Product p = new Product(id, naam, prijs, beschrijving);
            p.setCategory(category);

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

            String queryText = "SELECT id, prijs, categorie, beschrijving FROM product WHERE naam = ?;";
            PreparedStatement stmt = conn.prepareStatement(queryText);
            stmt.setString(1, name);
            ResultSet result = stmt.executeQuery();

            result.next();

            int id = result.getInt("id");
            Double prijs = result.getDouble("prijs");
            Category category = cpsd.findByName(result.getString("categorie"));
            String beschrijving = result.getString("beschrijving");

            Product p = new Product(id, name, prijs, beschrijving);
            p.setCategory(category);

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
                int id = result.getInt("id");
                String name = result.getString("naam");
                Double price = result.getDouble("prijs");
                Category category = cpsd.findByName(result.getString("categorie"));
                String description = result.getString("beschrijving");

                Product p = new Product(id, name, price, description);
                p.setCategory(category);

                products.add(p);
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
                int id = dbResultSet.getInt("id");
                String name = dbResultSet.getString("naam");
                double price = dbResultSet.getDouble("prijs");
                String description = dbResultSet.getString("beschrijving");
                Product prod = new Product(id, name, price, description);
                products.add(prod);
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
            ResultSet dbResultSet = stmt.executeQuery("select naam, omschrijving from categorie");
            while (dbResultSet.next()) {
                categories.add(new Category(dbResultSet.getString("naam"), dbResultSet.getString("omschrijving")));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return categories;

    }

    public boolean insertProduct(Product product) {
        try {
            Connection conn = super.getConnection();
            String query = "INSERT INTO product (naam, prijs, categorie, beschrijving)" +
                    "VALUES (?, ?, ?, ?);";

            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, product.getName());
            stmt.setDouble(2, product.getPrice());
            stmt.setString(3, product.getCategory().getName());
            stmt.setString(4, product.getDescription());

            boolean status = stmt.execute();
            conn.close();
            return status;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateProduct(Product product) {
        try {
            Connection conn = super.getConnection();
            String query = "update product " +
                    "set naam = ?, prijs = ?, categorie = ?, beschrijving = ?" +
                    "where id = ?;";

            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, product.getName());
            stmt.setDouble(2, product.getPrice());
            stmt.setString(3, product.getCategory().getName());
            stmt.setString(4, product.getDescription());
            stmt.setInt(5, product.getId());

            boolean status = stmt.execute();
            conn.close();
            return status;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteProduct(Product product) {
        try {
            Connection conn = super.getConnection();

            String query = "delete from product where id = ?;";

            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, product.getId());

            boolean status = stmt.execute();
            conn.close();
            return status;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
