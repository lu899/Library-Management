import java.util.ArrayList;

public class Person {
    public String name;
    public int regId;
    
    public ArrayList<Book> booksBorrowed = new ArrayList<>();

    Person(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setId(int regId){
        this.regId = regId;
    }

    public int getId(){
        return regId;
    }

    public void addBook(Book book){
        booksBorrowed.add(book);
    }

    public void removeBook(Book book){
        booksBorrowed.remove(book);
    }

}
