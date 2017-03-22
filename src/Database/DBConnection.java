package Database;

/**
 * Created by matrixcode on 10/28/16.
 */
import java.sql.*;
public class DBConnection {
    Connection conn = null;

    public  static Connection ConnectDB() throws ClassNotFoundException
    {
        try{
            Class.forName("org.sqlite.JDBC");
            //Connection conn = DriverManager.getConnection("jdbc:sqlite:"+ System.getProperty("user.dir") + "/folder/newshop.sqlite");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:shop.sqlite");

            return conn;
        } catch(Exception e ) {
            e.printStackTrace();
        }
        return null;

    }
}
