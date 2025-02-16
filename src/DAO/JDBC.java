package DAO;


import java.sql.Connection;
import java.sql.DriverManager;

/**
 * JDBC contains all Connection Methods to communicate with the MySQL database.
 */
public abstract class JDBC {
    private static final String protocol = "jdbc";
    private static final String vendor = ":mysql:";
    private static final String location = "//localhost/";
    private static final String databaseName = "basecompanydatabase";
    private static final String jdbcUrl = protocol + vendor + location + databaseName + "?connectionTimeZone = SERVER"; // LOCAL
    private static final String driver = "com.mysql.cj.jdbc.Driver"; // Driver reference
    private static final String userName = "baseDatabase"; // Username
    private static String password = "e3_y!7kp"; // Password
    public static Connection connection;  // Connection Interface

    /**
     * openConnection() Opens the connection to mySQL database.
     */
    public static void openConnection()
    {
        try {
            Class.forName(driver); // Locate Driver
            connection = DriverManager.getConnection(jdbcUrl, userName, password); // Reference Connection object
            System.out.println("Connection successful!");
        }
        catch(Exception e)
        {
            System.out.println("Error:" + e.getMessage());
        }
    }

    /**
     * closeConnection Closes the connection to mySQL database.
     */
    public static void closeConnection() {
        try {
            connection.close();
            System.out.println("Connection closed!");
        }
        catch(Exception e)
        {
            System.out.println("Error:" + e.getMessage());
        }
    }

    /**
     * createConnection() was created as a shortcut to get connection
     * @return connection
     */
    public static Connection createConnection() {
        return connection;
    }
}
