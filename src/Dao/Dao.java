package Dao;

import java.sql.Connection;
import java.sql.DriverManager;

public abstract class Dao {


    public static Connection connector() {
        java.sql.Connection connection = null;

        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost/jdbc";
        String user = "teste";
        String password = "Heitor123@";

        try {
            Class.forName (driver);
            connection = DriverManager.getConnection(url, user, password);
            return connection;
        }

        catch (Exception exception) {
            System.out.println(exception);
        }

        return null;
    }


}
