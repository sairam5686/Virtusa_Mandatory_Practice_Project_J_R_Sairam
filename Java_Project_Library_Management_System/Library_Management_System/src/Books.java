import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

abstract  class LibraryBooks{
    abstract void AddBooks(String BookName , String BookAuthor , int Quantity); 
    abstract void removeBooks(int book_id);
    abstract void ViewAllBooks(); 
    abstract void SearchBookd(String author , String title );
}


public class Books extends LibraryBooks  {
   
 void AddBooks(String BookName , String BookAuthor , int Quantity ){
     try {
         Connection connect = JdbcConnection.getConnection();
         Statement state = connect.createStatement();
         String Query = String.format("INSERT INTO books(title , author , quantity ) VALUES (%s , %s , %d)", BookName,
                 BookAuthor, Quantity);
         int result = state.executeUpdate(Query);
         if (result == 1) {
             System.out.println("Book Has been Registered");
         } else {
             System.out.println("Problem in Book Registered");
         }

         state.close();
         connect.close();
     } catch (Exception e) {
         System.out.println(e);
     }
 }

    void removeBooks(int book_id){
        try {
        Connection connect = JdbcConnection.getConnection();
         Statement state = connect.createStatement();
         String Query = String.format("delete from books where book_id  =%d ;" , book_id );
         int result  = state.executeUpdate(Query);
        if(result != 0 ){
            System.out.println("Successfully Removed the Book ");
        }else{
            System.out.println("Can find the Book with"+book_id +" as the Book id");
        }
            
        } catch (Exception e) {
            System.out.println(e);
        }
        

    };
     void ViewAllBooks(){
        try {
            Connection connect = JdbcConnection.getConnection();
            Statement state = connect.createStatement();
            ResultSet result =  state.executeQuery("select * from books;");
            while (result.next()) {
                System.out.println(result.getInt("book_id") + "|" 
                + result.getString("title") + "|" + result.getString("author") 
                +  "|" + result.getString("quantity"));
            }
            
            result.close();
            state.close();
            connect.close();
        } catch (Exception e) {
          System.out.println(e);
        }
     }; 



     void SearchBookd(String author , String title ){
         try {
            Connection connect = JdbcConnection.getConnection();
            Statement state = connect.createStatement();
            String Query = String.format("SELECT * FROM books WHERE author =%s and title = %s " , author , title); 
            ResultSet result =  state.executeQuery(Query);
            while (result.next()) {
                System.out.println(result.getInt("book_id") + "|" 
                + result.getString("title") + "|" + result.getString("author") 
                +  "|" + result.getString("quantity"));
            }

            result.close();
            state.close();
            connect.close();
        } catch (Exception e) {
          System.out.println(e);
        }

     };


}
