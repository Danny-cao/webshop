package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


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

}
