import java.sql.Connection;
import java.sql.DriverManager;

public  class JdbcConnection {
    public static Connection getConnection(){
        Connection con = null ; 
        try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://127.0.0.1:3306/lms";
        String user = "root" ; 
        String password = "sairam@5686"; 
        con = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            System.out.println(e);
        }
        return con;
    }
    
}
