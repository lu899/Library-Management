import java.util.*;

public class App {
    static Scanner scanner = new Scanner(System.in);
    static Lib myLibrary = new Lib("Lucy's Library");
    static Random random = new Random();
        
    public static void main(String[] args) throws Exception {

        System.out.println("**********************************");
        System.out.println("Hello, Welcome to Lucy's Library!!");
        System.out.println("**********************************");
        
        System.out.print("Are you a student or an admin: ");
        String identity = scanner.nextLine();

        if (identity.equals("student")) {
            System.out.print("Enter Your name: ");
            String name = scanner.nextLine();
            Student student = new Student(name);

            if(!myLibrary.registereStudents.contains(student)){
                int  myId = random.nextInt(200);
                student.setId(myId);
                myLibrary.registereStudents.add(student);                
            }

            System.out.print("Do You want to borrow or return it:(b/r) ");
            char ans = scanner.next().charAt(0);

            if (ans == 'b') {
                System.out.print("How many books do you want: ");
                int numOfbooks = scanner.nextInt();
                scanner.nextLine();

                borrowBook(student, numOfbooks);
                
            } else if (ans == 'r') {
                System.out.print("How many books are you returning: ");
                int count = scanner.nextInt();
                scanner.nextLine();

                returnBook(student, count);

            } else {
                System.out.println("Enter a valid response");
            }
        } else if (identity.equals("admin")) {
            System.out.print("Enter your name: ");
            String adminName = scanner.nextLine();
            Admin admin = new Admin(adminName);
            
            if(!myLibrary.admins.contains(admin)){
                int adminId = random.nextInt(500);
                admin.setId(adminId);
                myLibrary.admins.add(admin);
            }
            System.out.println("*****************");
            System.out.println("     Welcome     ");
            System.out.println("*****************");
            System.out.println("As an admin you can: ");
            System.out.println("1. Borrow a book");
            System.out.println("2. Return a book");
            System.out.println("3. Add a book");
            System.out.println("4. See Available books");
            System.out.print("Enter your choice(1-4): ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("How many books do you want: ");
                    int num = scanner.nextInt();
                    scanner.nextLine();

                    borrowBook(admin, num);                    
                    break;
                case 2:
                    System.out.print("How many books are you returning: ");
                    int count = scanner.nextInt();
                    scanner.nextLine(); 
                    
                    returnBook(admin, count);
                    break;
                case 3:
                    System.out.print("How many books are you adding: ");
                    int total = scanner.nextInt();
                    scanner.nextLine();

                    addBook(total);
                    break;
                case 4:
                    myLibrary.display();
                    break;
                default:
                    System.out.println("Pick an appropiate choice!!");
                    break;
            }

        }else{
            System.out.println("Enter a valid response");
        }

        scanner.close();
    }

    static void borrowBook(Person identity, int numOfbooks){
        while (numOfbooks > 0) {
            System.out.print("Enter title of book: ");
            String title = scanner.nextLine();
            System.out.print("Who is the author: ");
            String author = scanner.nextLine();
            int bookId = random.nextInt(200);
            Book myBook = new Book(title, author);
            myBook.setId(bookId);
            if (myLibrary.books.contains(myBook)) {
                myBook.borrow();
                identity.addBook(myBook);
                myLibrary.removeBook(myBook);
            } else{
                System.out.println("Book is unavailable!!");
            }
            numOfbooks--;
        }
    }

    static void returnBook(Person identity, int count){
        while (count > 0) {
            System.out.print("Enter title of book: ");
            String title = scanner.nextLine();
            System.out.print("Who is the author: ");
            String author = scanner.nextLine();
            int bookId = random.nextInt(200);
            Book myBook = new Book(title, author);
            myBook.setId(bookId);
            myLibrary.books.add(myBook);
            identity.removeBook(myBook);
            count--;
        }
        System.out.println("Thank You...Come back next time!!");
    }

    static void addBook(int count){
        while (count > 0) {
            System.out.print("Enter title of book: ");
            String title = scanner.nextLine();
            System.out.print("Enter author: ");
            String author = scanner.nextLine();

            Book book = new Book(title, author);
            int bookId = random.nextInt(500);
            book.setId(bookId);
            if (myLibrary.books.contains(book)) {
                System.out.println("Book already in records!!");
            } else {
                myLibrary.addBook(book);
            }
            count--;
        }
        System.out.println("Thank You for increacing our knowkedge base 😊");
    }
}
