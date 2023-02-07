package conexaoDB;

import java.sql.*;

public class ConexaoDB {
    public static String URL = "jdbc:mysql://localhost:3307/hotel";
    public static String USER = "root";
    public static String PASSWORD = "usbw";
    private Connection dbconn = null;
    public static Connection createConnectionToMySQL() throws Exception {

        Connection dbconn = DriverManager.getConnection(URL, USER, PASSWORD);

        return dbconn;
    }
}
