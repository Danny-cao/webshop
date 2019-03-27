package persistence;

import model.Account;
import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountPostgreSQLDAOImpl extends PostgreSQLBaseDao implements AccountDAO {

//    public Account getAccountByEmail(String email) {
//        try {
//            Connection conn = super.getConnection();
//            CategoryPostgreSQLDAOImpl cpsd = new CategoryPostgreSQLDAOImpl();
//
//            String queryText = "SELECT * FROM account WHERE email = ?;";
//            PreparedStatement stmt = conn.prepareStatement(queryText);
//            stmt.setString(1, email);
//            ResultSet result = stmt.executeQuery();
//
//            result.next();
//            Product p = getProductOutResultset(result);
//
//            conn.close();
//            return p;
//        } catch (SQLException e) {
//            System.out.println("Product with name not found / More than 1 product found");
//            return null;
//        }
//    }
//
//    private getAccountFromResultSet(ResultSet dbResultSet) {
//        try {
//            int id = dbResultSet.getInt("id");
//            String name = dbResultSet.getString("naam");
//            double price = dbResultSet.getDouble("prijs");
//            String description = dbResultSet.getString("beschrijving");
//            Category category = cpd.findByName(dbResultSet.getString("categorie"));
//            Product p = new Product(id, name, price, description);
//            p.setCategory(category);
//            return p;
//        } catch (SQLException e) {
//            return null;
//        }
//    }
}
