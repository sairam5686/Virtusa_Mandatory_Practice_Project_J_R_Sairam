
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;

abstract class LibraryFunctions {
    abstract void BorrowBook(int user_id , int book_id);
    abstract void ReturnBook(int borrow_id , String return_date);   
}

public class BorrowBooks extends LibraryFunctions {
    void BorrowBook(int user_id , int book_id){
        Connection connect = JdbcConnection.getConnection();
        try {
            Statement stmt = connect.createStatement();
            Statement stmt2 = connect.createStatement();

            String Query = String.format("select * from books where book_id = %d" , book_id);
            String Query2 = String.format("select * from users where user_id = %d" , user_id); 
            ResultSet result  = stmt.executeQuery(Query);
            ResultSet result2 = stmt2.executeQuery(Query2);

            if(result.next() && result2.next()){
                int  BookQuantity = result.getInt("quantity"); 
                if(BookQuantity >  0 ){
                    String UpdateQuery = String.format("update books set quantity =quantity -  1 where  book_id = %d;" , book_id);
                    stmt.executeUpdate(UpdateQuery);
                    String IssueQuery = String.format( "insert into borrowlist( user_id , book_id , issue_date  , return_date   ) values (%d , %d, CURDATE(),DATE_ADD(CURDATE(), INTERVAL 7 DAY));", user_id , book_id);
                    stmt.executeUpdate(IssueQuery) ; 


                    System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++ ");
                    System.out.println("Book Has Been Successfully Issued to the User with" + user_id  );
                    System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++ ");
                    

                }else{
                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++ ");
                System.out.println("All the Book or user are Given out cannot be Issued");
                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++ ");
                }

            }else{
                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++ ");
                System.out.println("Incorrect Book or User id  ! Please enter valid book ID ");
                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++ ");
            
            }


            result2.close();
            result.close();
            stmt.close();
            stmt2.close();
            connect.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void viewBorrowlist(){
        Connection connect = JdbcConnection.getConnection();
        try {
            Statement stmt = connect.createStatement();
            ResultSet result  = stmt.executeQuery("select * from borrowlist");
            while (result.next()) {
                int borrowId = result.getInt("borrow_id");
            int userId = result.getInt("user_id");
            int bookId = result.getInt("book_id");
            String issueDate = result.getString("issue_date");
            String returnDate = result.getString("return_date");
            int isReturned = result.getInt("is_returned");
            System.out.println("Borrow_id | user_id | book_id | issueDate | returnDate | isReturned ");
            System.out.println(
                borrowId + " | " +
                userId + " | " +
                bookId + " | " +
                issueDate + " | " +
                returnDate + " | " +
                isReturned
            );
                
                
            }

            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    void ReturnBook(int borrow_id , String return_date){
        Connection connect = JdbcConnection.getConnection();
        try {
            Statement stmt = connect.createStatement();
            Statement stmt2 = connect.createStatement();

            String Query = String.format("select * from borrowlist where borrow_id = %d" , borrow_id );
            ResultSet result  = stmt.executeQuery(Query);


            if(result.next() && !result.getBoolean("is_returned")){
                String ExpectedReturnDate = result.getString("return_date");

                String UpdateQuery = String.format("update books set quantity =quantity +  1 where  book_id = %d;" , result.getInt("book_id"));
                stmt.executeUpdate(UpdateQuery);
                
                String UpdateBorrowQuery = String.format( "update borrowlist set is_returned = TRUE where borrow_id = %d" , borrow_id );
                stmt2.executeUpdate(UpdateBorrowQuery);

                java.sql.Date dueDate = java.sql.Date.valueOf(ExpectedReturnDate);
                java.sql.Date ActualReturnDate = java.sql.Date.valueOf(return_date);

                long diff = ActualReturnDate.getTime() - dueDate.getTime();
                long daysLate = diff / (1000 * 60 * 60 * 24);

                int fine = 0;
                int finePerDay = 10; // ₹10 per day

                if (daysLate > 0) {
                    fine = (int) daysLate * finePerDay;
                }
                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++ ");
                System.out.println("Fine: ₹" + fine);
                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++ ");
            
            }else{
                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++ ");
                System.out.println("All the Book or user are Given out cannot be Issued");
                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++ ");
            }

                result.close();
             stmt.close();
             stmt2.close();
            connect.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
            
    }

}
