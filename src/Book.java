import java.util.*;
import javax.swing.*;

public class Book {
    private String title;
    private String author;
    private int regId;
    private String url;

    Book(String title, String author, String url){
        this.author = author;
        this.title = title;
        this.url = url;
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

    public String getUrl(){
        return url;
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
        JOptionPane.showMessageDialog(null, "Happy Reading!!\n");
    }
}
