import java.util.*;

public class Lib {
    private String name;
    private String LIBFILENAME = "libBooks.csv";
    private String STDFILENAME = "students.csv";
    private String ADMFILENAME = "admins.csv";
    private String BORRFILENAME = "borrowed.csv";
   
    private List<Book> books = FileCSV.loadBooks(LIBFILENAME);
    private List<Student> registeredStudents = FileCSV.loadStudents(STDFILENAME);
    private List<Admin> admins = FileCSV.loadAdmins(ADMFILENAME);
    private static HashMap<Person, List<Book>> map;

    Random random = new Random();

    Lib(String name){
        this.name = name;
        map = FileCSV.loadBorrowedBooks(BORRFILENAME, registeredStudents, admins);
    }

    public String getName(){
        return name;
    }

    public static HashMap<Person, List<Book>> getHashMap(){
        return map;
    }

    public Student getStudent(String name){
        for (Student s : registeredStudents) {
            if (s.getName().equalsIgnoreCase(name.trim())) {
                return s;
            }
        }
        return null;
    }

    public Admin getAdmin(String name){
        for (Admin a : admins) {
            if (a.getName().equalsIgnoreCase(name.trim())) {
                return a;
            }
        }
        return null;
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
                map.put(identity, identity.getBooksborrowed());
                FileCSV.saveBorrowedBooks(map, BORRFILENAME);
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

            Book myBook = null;
            for (Book b : identity.getBooksborrowed()) {
                if (b.getTitle().equalsIgnoreCase(title) && b.getAuthor().equalsIgnoreCase(author)) {
                    myBook = b;
                    break;
                }
            }
            
            if (myBook != null) {
                books.add(myBook);
                FileCSV.saveBooks(books, LIBFILENAME);
                identity.removeBook(myBook);
                FileCSV.saveBorrowedBooks(map, BORRFILENAME);
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
        if (getStudent(s.getName()) == null) {
            registeredStudents.add(s);
            FileCSV.registerStudents(registeredStudents, STDFILENAME);
        } else {
            System.out.println("Student already registered!");
        }
    }
}
