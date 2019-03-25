package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleBaseDao {

    private static final String DB_DRIV = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_URL = "jdbc:oracle:thin:@//ondora04.hu.nl:8521/EDUC21";
    private static final String DB_USER = "cursist";
    private static final String DB_PASS = "cursist4491";

    public Connection getConnection() throws SQLException {
        try {
            Class.forName(DB_DRIV);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
    }

    public void closeConnection(Connection conn) throws SQLException{
        conn.close();
    }
}