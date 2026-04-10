import java.sql.*;
import java.util.*;


// Library_management_system -- connection name
// lms -- database name
// users , books , borrowlist - tables name

public class App {
    public static void main(String[] args) throws Exception {
        Connection connect = JdbcConnection.getConnection();
        Statement state = connect.createStatement();
        state.executeUpdate("insert  into books(book_id , title , author  , quantity) values (3 , 'Pride and Prejudice' , 'Jane Austen' , 4) ");
        connect.close();
        state.close();

       }
}
