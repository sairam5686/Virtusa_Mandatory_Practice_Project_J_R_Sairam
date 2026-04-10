import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

abstract class LibUser {
    abstract void RegisterUser(String Name, String Email); 
    abstract void RemoveUser(int user_id) ; 
    abstract void viewUsers(); 
}

public class User extends LibUser {
    void RegisterUser(String Name, String Email){  
        try {
            Connection connect = JdbcConnection.getConnection();
            Statement state = connect.createStatement();
            String Query = String.format("insert into users(user_id , name , email) value ( %s  , %s);" ,Name , Email );
            int result =  state.executeUpdate(Query);
            if(result == 1){
                System.out.println("User has been Registered");
            }else{
                System.out.println("User Cannot be registered");
            }

            state.close();
            connect.close();

        } catch (SQLException e) {
            
            e.printStackTrace();
        }

    }

    void RemoveUser(int user_id)  {
        try {
            Connection connect = JdbcConnection.getConnection();
            Statement state = connect.createStatement();
            String Query = String.format("delete from users where user_id  =%d ;"  ,user_id );
            int result =  state.executeUpdate(Query);
            if(result == 1){
                System.out.println("User has been Registered");
            }else{
                System.out.println("User Cannot be registered");
            }

            state.close();
            connect.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
   
    }


    void viewUsers(){
        try {
       
            Connection connect = JdbcConnection.getConnection();
            Statement state = connect.createStatement();
            ResultSet result =  state.executeQuery("select * from users;");
            while (result.next()) {
                System.out.println(result.getInt("user_id") + "|" 
                + result.getString("name") + "|" + result.getString("email"));
            }
            
            result.close();
            state.close();
            connect.close();    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
