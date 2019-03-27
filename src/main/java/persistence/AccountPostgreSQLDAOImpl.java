package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Account;
import model.Category;
import model.Product;


public class AccountPostgreSQLDAOImpl extends PostgreSQLBaseDao implements AccountDAO{

	@Override
	public boolean validateLogin(String email, String password) {
        try (Connection con = super.getConnection()) {
      
            PreparedStatement pst = con.prepareStatement("SELECT * FROM account where email = '"+email+"' AND password = '"+password+"'");
            ResultSet rs = pst.executeQuery(); 

        if (rs.next()) {
        	return true;        	
        }        
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
	}

	@Override
	public Account getAccountDetails(String email) {
        try {
            Connection conn = super.getConnection();

            String queryText = "SELECT * FROM account WHERE email = ?;";
            PreparedStatement stmt = conn.prepareStatement(queryText);
            stmt.setString(1, email);
            ResultSet result = stmt.executeQuery();

            result.next();
            int idNew = result.getInt("id");
            String emailNew = result.getString("email");
            String adressNew = result.getString("factuuradres");
            Account a = new Account(idNew, emailNew, adressNew);

            conn.close();
            return a;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
	}

}
