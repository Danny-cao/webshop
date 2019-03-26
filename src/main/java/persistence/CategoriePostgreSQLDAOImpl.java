package persistence;

import model.Categorie;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class CategoriePostgreSQLDAOImpl extends PostgreSQLBaseDao implements CategorieDAO {
	
	
	@Override
	public Categorie findByName(String name) {
		
        try (Connection con = super.getConnection()) {
            Statement stmt = con.createStatement();
            ResultSet dbResultSet = stmt.executeQuery("select naam, beschrijving from categorie where naam = '" + name + "'");
            dbResultSet.next();
            
            String naam = dbResultSet.getString("naam");
            String beschrijving = dbResultSet.getString("beschrijving");
            
            return new Categorie(naam, beschrijving);
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
	}
}
