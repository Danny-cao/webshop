package persistence;

import java.sql.Connection;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class ProductOracleDAOImpl extends OracleBaseDao implements ProductDAO {
    public List<Product> findAllOnSale() {
        List<Product> products = new ArrayList<Product>();
        try (Connection con = super.getConnection()) {
            Statement stmt = con.createStatement();
            ResultSet dbResultSet = stmt.executeQuery
                    ("SELECT product.id, product.naam, product.afbeelding, product.categorie, product.beschrijving, aanbieding.prijs\n" +
                            "FROM product\n" +
                            "INNER JOIN aanbieding\n" +
                            "ON product.id = aanbieding.product where sysdate < aanbieding.totdatum and sysdate > aanbieding.vandatum");
            while (dbResultSet.next()) {
                int id = dbResultSet.getInt("id");
                String name = dbResultSet.getString("naam");
                int price = dbResultSet.getInt("prijs");
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
                int price = dbResultSet.getInt("prijs");
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
            Statement stmt = con.createStatement();
            ResultSet dbResultSet = stmt.executeQuery("select * from product where categorie = '" + category + "'");
            while (dbResultSet.next()) {
                int id = dbResultSet.getInt("id");
                String name = dbResultSet.getString("naam");
                int price = dbResultSet.getInt("prijs");
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
        // TODO Auto-generated method stub
        return false;
    }

    public boolean updateProduct(Product product) {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean deleteProduct(Product product) {
        // TODO Auto-generated method stub
        return false;
    }


}
