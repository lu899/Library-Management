import java.util.*;

public class Person {
    private String name;
    private int regId;
    
    private ArrayList<Book> booksBorrowed = new ArrayList<>();

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
