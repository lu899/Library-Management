import java.util.*;

public class Lib {
    private String name;
    private String LIBFILENAME = "libBooks.csv";
    private String STDFILENAME = "students.csv";
    private String ADMFILENAME = "admins.csv";
   
    private List<Book> books = FileCSV.loadBooks(LIBFILENAME);
    private List<Student> registeredStudents = FileCSV.loadStudents(STDFILENAME);
    private List<Admin> admins = FileCSV.loadAdmins(ADMFILENAME);
    Random random = new Random();

    Lib(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public boolean checkStudent(String name){
        for (Student student : registeredStudents) {
            if (student.getName().equalsIgnoreCase(name.trim())) {
                return true;
            }
        }
        return false;
    }

    public boolean checkAdmin(String adminName){
        for (Admin admin : admins) {
            if (admin.getName().equalsIgnoreCase(adminName.trim())) {
                return true;
            }
        }
        return false;
    }

    public void removeBook(Book book){
        books.remove(book);
        FileCSV.saveBooks(books, LIBFILENAME);
    }

    public void borrowBook(Person identity, int numOfbooks, Scanner scanner){
        while (numOfbooks > 0) {
            System.out.print("\nEnter title of book: ");
            String title = scanner.nextLine().toLowerCase().trim();
            System.out.print("Who is the author: ");
            String author = scanner.nextLine().toLowerCase().trim();
            int bookId = random.nextInt(200);
            Book myBook = new Book(title, author);
            myBook.setId(bookId);
            if (books.contains(myBook)) {
                myBook.borrow();
                identity.addBook(myBook);
                removeBook(myBook);
            } else{
                System.out.println("Book is unavailable!!");
            }
            numOfbooks--;
        }
    }

    public void returnBook(Person identity, Scanner scanner){
            System.out.print("\nEnter title of book: ");
            String title = scanner.nextLine().toLowerCase().trim();
            System.out.println(title);
            System.out.print("Who is the author: ");
            String author = scanner.nextLine().toLowerCase().trim();
            Book myBook = new Book(title, author);
            
            if (identity.checkBorrowed(title)) {
                books.add(myBook);
                FileCSV.saveBooks(books, LIBFILENAME);
                identity.removeBook(myBook);
                System.out.println("Thank You...Come back next time!!\n");
            } else {
                System.out.println("Sorry You didn't borrow that book from us!!\n");
            }
        
    }

    public void addBook(int count, Scanner scanner){
        while (count > 0) {
            System.out.print("\nEnter title of book: ");
            String title = scanner.nextLine().toLowerCase().trim();
            System.out.print("Enter author: ");
            String author = scanner.nextLine().toLowerCase().trim();
            System.out.print("Enter number of book copies: ");
            int num = scanner.nextInt();
            scanner.nextLine();

            for (int i = 0; i < num; i++) {
                Book book = new Book(title, author);
                int bookId = random.nextInt(500);
                book.setId(bookId);
                books.add(book);
                FileCSV.saveBooks(books, LIBFILENAME);
            }
            count--;
        }
        System.out.println("Thank You for increacing our knowledge base 😊");
    }

    public void display(){
        if (!books.isEmpty()) {
            System.out.println("Books available:");
            for (Book book : books) {
                System.out.println("\nTitle: " + book.getTitle());
                System.out.println("Author: " + book.getAuthor());
                System.out.println("RegId: "  + book.getId());
            }
        } else {
            System.out.println("No books in our library!!");
        }
    }

    public void registerStudent(Student s){
        registeredStudents.add(s);
        FileCSV.registerStudents(registeredStudents, STDFILENAME);
    }
}
