package persistence;

import model.Categorie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class CategoriePostgreSQLDAOImpl extends PostgreSQLBaseDao implements CategorieDAO {
	
	
	@Override
	public Categorie findByName(String name) {
		
        try (Connection con = super.getConnection()) {

            String query = "select naam, omschrijving from categorie where naam = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, name);
            ResultSet dbResultSet = stmt.executeQuery();
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
