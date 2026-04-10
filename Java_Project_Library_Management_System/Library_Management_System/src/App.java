import java.sql.*;
import java.util.*;

// Library_management_system -- connection name
// lms -- database name
// users , books , borrowlist - tables name




public class App {
    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://127.0.0.1:3306/lms";
        String user = "root" ; 
        String password = "sairam@5686"; 
        Connection conn = DriverManager.getConnection(url, user, password);
        System.out.println("Connected Successfully!");
       
       
       }
}
