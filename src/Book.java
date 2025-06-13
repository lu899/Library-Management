import java.util.Objects;

public class Book {
    private String title;
    private String author;
    private int regId;

    Book(String title, String author){
        this.author = author;
        this.title = title;
    }

    public String getTitle(){
        return title;
    }

    public void setId(int id){
        this.regId = id;
    }

    public int getId(){
        return regId;
    }

    public String getAuthor(){
        return author;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        Book book = (Book) obj;
        return title.equalsIgnoreCase(book.title) && author.equalsIgnoreCase(book.author);
    }

    @Override
    public int hashCode(){
        return Objects.hash(title.toLowerCase(), author.toLowerCase());
    }
    
    public void borrow(){
        System.out.println("Book borrowed: " + title);
        System.out.println("Author: " + author);
        System.out.println("Happy Reading");
    }
}
