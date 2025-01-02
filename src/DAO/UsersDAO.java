package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersDAO {

    public static boolean verifyLoginInformation(String username, String password) throws SQLException {
        String checkingLogin = "SELECT * FROM users WHERE User_Name=? AND Password=?";
        PreparedStatement verifyLogin = JDBC.createConnection().prepareStatement(checkingLogin);
        verifyLogin.setString(1, username);
        verifyLogin.setString(2, password);
        try {
            verifyLogin.execute();
            ResultSet result = verifyLogin.getResultSet();
            return (result.next());
        }
        catch (Exception e) {
            System.out.println("ERROR OCCURRED: " + e.getMessage());
            return false;
        }
    }
}
