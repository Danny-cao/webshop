package persistence;

import model.Categorie;
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
        // TODO Auto-generated method stub
        return null;
    }

    public Product findByName(String name) {
        // TODO Auto-generated method stub
        return null;
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

    public List<Categorie> findAllCategories() {
        List<Categorie> categories = new ArrayList<Categorie>();
        try (Connection con = super.getConnection()) {
            Statement stmt = con.createStatement();
            ResultSet dbResultSet = stmt.executeQuery("select naam, omschrijving from categorie");
            while (dbResultSet.next()) {
                categories.add(new Categorie(dbResultSet.getString("naam"), dbResultSet.getString("omschrijving")));
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
            stmt.setString(3, product.getCategorie().getNaam());
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
            stmt.setString(3, product.getCategorie().getNaam());
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
