import java.util.*;
import java.io.*;

public class FileCSV {
    public static void saveBooks(List<Book> books, String filename){
        try (FileWriter writer = new FileWriter(filename)) {
            for (Book book : books) {
                writer.write(book.getId() + "," + book.getTitle() + "," + book.getAuthor() + '\n');
            }
        } catch (IOException e) {
            System.out.println("Error: couldn't save books!!");
        }
    }

    public static List<Book> loadBooks(String filename){
        List<Book> books = new ArrayList<>();
        File file = new File(filename);
        if (!file.exists()) {
            return books;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 3) {
                    Book b = new Book(data[1], data[2]);
                    b.setId(Integer.parseInt(data[0]));
                    books.add(b);
                }
            }
        } catch (IOException e) {
            System.out.println("Couldn't load books: " + e.getMessage());
        }
        return books;
    }

    public static void registerStudents(List<Student> students, String filename){
        try (FileWriter writer = new FileWriter(filename)) {
            for (Student student : students) {
                writer.write(student.getId() + "," + student.getName() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Couldn't register student: " + e.getMessage());
        }
    }

    public static List<Student> loadStudents(String filename){
        List<Student> students = new ArrayList<>();
        File file = new File(filename);
        if (!file.exists()) {
            return students;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 2) {
                    Student s = new Student(data[1]);
                    s.setId(Integer.parseInt(data[0]));
                    students.add(s);
                }
            }
        } catch (Exception e) {
            System.out.println("Error leading Students: " + e.getMessage());
        }
        return students;
    }

    public static void registerAdmins(List<Admin> admins, String filename){
        try (FileWriter writer = new FileWriter(filename)) {
            for (Admin admin : admins) {
                writer.write(admin.getId() + "," + admin.getName() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Couldn't register student: " + e.getMessage());
        }
    }

    public static List<Admin> loadAdmins(String filename){
        List<Admin> admins = new ArrayList<>();
        File file = new File(filename);
        if (!file.exists()) {
            return admins;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 2) {
                    Admin a = new Admin(data[1]);
                    a.setId(Integer.parseInt(data[0]));
                    admins.add(a);
                }
            }
        } catch (Exception e) {
            System.out.println("Error leading admins: " + e.getMessage());
        }
        return admins;
    }
}
