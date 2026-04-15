import java.sql.*;
import java.util.*;


// Library_management_system -- connection name
// lms -- database name
// users , books , borrowlist - tables name

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scan  = new Scanner(System.in);
        
        User user = new User();
        Books book = new Books();
        BorrowBooks borrowbooks = new BorrowBooks();


        int options ; 
        do {
        System.out.println("******************************LIBARAY MANAGEMENT SYSTEM **************************");
        System.out.println("------User Options ---------");
        System.out.println("1 . Register User");
        System.out.println("2 . Remove User");
        System.out.println("3 . View User");

        System.out.println("-------Books OPtions ---------");
        System.out.println("4 . Add Book");
            System.out.println("5 . Remove Book");
            System.out.println("6 . View Books");
            System.out.println("7 . Search Books ");

        System.out.println("--------Borrow or Return Books options --------");
        System.out.println("8 . Borrow Book ");
        System.out.println("9 . Return Book ");
        System.out.println("0.View Borrow list");

        options = scan.nextInt();
        scan.nextLine();


        switch (options) {
            case 1:
                System.out.print("Enter the Name :");
                String name = scan.nextLine();
                System.out.print("Enter the Email :");
                String email = scan.nextLine();
                user.RegisterUser(name, email);
                break;

            case 2:
                System.out.print("Enter the User ID :");
                int user_id = scan.nextInt();
                user.RemoveUser(user_id);
                break;


            case 3 :
                user.viewUsers();
                break;

            case 4:
                System.out.print("enter the Book name :");
                String bname = scan.nextLine();
                System.out.print("Enter the Author name :");
                String Aname = scan.nextLine();
                System.out.print("Enter the Quantity of the book available :");
                int quantity  = scan.nextInt();
                book.AddBooks(bname, Aname, quantity);
                break;

            case 5: 
                System.out.print("Enter the book ID : ");
                int book_id = scan.nextInt();
                book.removeBooks(book_id);
                break ; 
            
            case 6:
                book.ViewAllBooks();
                break;
            
            case 7:
                System.out.print("Enter the book author name :");
                String author  = scan.nextLine();
                System.out.print("Enter the Book Name :");
                String bookname = scan.nextLine();
                book.SearchBookd(author, bookname);
                break ; 

            case 8:
                System.out.print("Enter the user id :");
                int userid  = scan.nextInt();
                System.out.print("Enter the book id :");
                int bookid =  scan.nextInt();
                borrowbooks.BorrowBook(userid, bookid);
                break; 
            case 9:
                System.out.print("enter the borrow id :");
                int bid = scan.nextInt();
                scan.nextLine();
                System.out.print("Enter Return Date (YYYY-MM-DD): ");
                String returnDate = scan.nextLine();
                borrowbooks.ReturnBook(bid, returnDate);
                break;
            
            case 0:
                borrowbooks.viewBorrowlist();
                break;


            default:
                System.out.println("Invalid Option please enter an valid option that is given above in the menu ");
        }



        } while (true);
    
       }
}
