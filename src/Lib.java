import java.util.*;
import javax.swing.*;

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
        for (Map.Entry<Person, List<Book>> entry : map.entrySet()) {
            Person p = entry.getKey();
            List<Book> borrowed = entry.getValue();
            p.getBooksborrowed().clear();
            p.getBooksborrowed().addAll(borrowed);
        }
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

    public List<Book> getBooks(){
        return books;
    }

    public void borrowBook(Person identity, String title, String author, String url){
        int bookId = random.nextInt(200);
        Book myBook = new Book(title, author, url);
        myBook.setId(bookId);

        if (books.contains(myBook)) {
            myBook.borrow();
            identity.addBook(myBook);
            map.put(identity, identity.getBooksborrowed());
            FileCSV.saveBorrowedBooks(map, BORRFILENAME);
            removeBook(myBook);
        } else{
            JOptionPane.showMessageDialog(null, "Book is unavailable!!");
        }
    }

    public void returnBook(Person identity, String title, String author){
        Book myBook = null;
        String searchTitle = title.trim().toLowerCase();
        String searchAuthor = author.trim().toLowerCase();
       
        for (Book b : identity.getBooksborrowed()) {           
            if (b.getTitle().trim().toLowerCase().equals(searchTitle) &&
                b.getAuthor().trim().toLowerCase().equals(searchAuthor)) {
                myBook = b;
                break;
            }
        }
        
        if (myBook != null) {
            books.add(myBook);
            FileCSV.saveBooks(books, LIBFILENAME);
            identity.removeBook(myBook);
            FileCSV.saveBorrowedBooks(map, BORRFILENAME);
            JOptionPane.showMessageDialog(null, "Thank You...Come back next time!!\n");
        } else {
            JOptionPane.showMessageDialog(null, "Sorry You didn't borrow that book from us!!\n");
        }
    }

    public void addBook(String title, String author, String imgPath){            
        Book book = new Book(title, author, imgPath);
        int bookId = random.nextInt(500);
        book.setId(bookId);
        books.add(book);
        FileCSV.saveBooks(books, LIBFILENAME);           
    }

    public void registerStudent(Student s){
        if (getStudent(s.getName()) == null) {
            registeredStudents.add(s);
            FileCSV.registerStudents(registeredStudents, STDFILENAME);
        } else {
            JOptionPane.showMessageDialog(null, "Student already registered!");
        }
    }
}
