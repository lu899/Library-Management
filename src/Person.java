import java.util.*;

public class Person {
    private String name;
    private int regId;
    
    private ArrayList<Book> booksBorrowed = new ArrayList<>();

    Person(int id, String name){
        this.name = name;
        this.regId = id;
    }

    public String getName(){
        return name;
    }

    public int getId(){
        return regId;
    }

    public List<Book> getBooksborrowed(){
        return booksBorrowed;
    }

    public void addBook(Book book){
        booksBorrowed.add(book);
    }

    public void removeBook(Book book){
        booksBorrowed.remove(book);
    }

    public boolean checkBorrowed(String name){
        for (Book book : booksBorrowed) {
            if (book.getTitle().equalsIgnoreCase(name.trim())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        Person person = (Person) obj;
        return regId == person.regId && name.equalsIgnoreCase(person.name);
    }

    @Override
    public int hashCode(){
        return Objects.hash(name.toLowerCase(), regId);
    }
}
