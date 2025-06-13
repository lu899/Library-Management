import java.util.*;

public class Lib {
    private String name;
   
    ArrayList<Book> books = new ArrayList<>();
    ArrayList<Student> registereStudents = new ArrayList<>();
    ArrayList<Admin> admins = new ArrayList<>();

    Lib(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void removeBook(Book book){
        books.remove(book);
    }

    public void addBook(Book book){
        books.add(book);
    }

    public void display(){
        if (!books.isEmpty()) {
            System.out.println("Books available:");
            for (Book book : books) {
                System.out.println("Title: " + book.getTitle());
                System.out.println("Author: " + book.getAuthor());
                System.out.println("RegId: "  + book.getId());
            }
        } else {
            System.out.println("No books in our library!!");
        }
    }
}
