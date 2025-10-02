import java.util.*;
import java.io.*;
import javax.swing.*;

public class FileCSV {
    public static void saveBooks(List<Book> books, String filename){
        try (FileWriter writer = new FileWriter(filename)) {
            for (Book book : books) {
                writer.write(book.getId() + "," + book.getTitle() + "," + book.getAuthor() + "," + book.getUrl() + '\n');
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error: couldn't save books!!");
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
                if (data.length == 4) {
                    Book b = new Book(data[1], data[2], data[3]);
                    b.setId(Integer.parseInt(data[0]));
                    books.add(b);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Couldn't load books: " + e.getMessage());
        }
        return books;
    }

    public static void registerStudents(List<Student> students, String filename){
        try (FileWriter writer = new FileWriter(filename)) {
            for (Student student : students) {
                writer.write(student.getId() + "," + student.getName() + "\n");
            }
            JOptionPane.showMessageDialog(null, "Student registered successfully!!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Couldn't register student: " + e.getMessage());
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
                    Student s = new Student(Integer.parseInt(data[0]), data[1]);
                    students.add(s);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error leading Students: " + e.getMessage());
        }
        return students;
    }

    public static void registerAdmins(List<Admin> admins, String filename){
        try (FileWriter writer = new FileWriter(filename)) {
            for (Admin admin : admins) {
                writer.write(admin.getId() + "," + admin.getName() + "\n");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Couldn't register student: " + e.getMessage());
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
                    Admin a = new Admin(Integer.parseInt(data[0]), data[1]);
                    admins.add(a);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error leading admins: " + e.getMessage());
        }
        return admins;
    }

    public static void saveBorrowedBooks(HashMap<Person, List<Book>> books, String filename){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("PersonId,PersonName,BookId,BookTitle,BookAuthor,ImagePath");
            writer.newLine();

            for (Map.Entry<Person, List<Book>> entry : books.entrySet()) {
                Person p = entry.getKey();
                List<Book> borrowed = entry.getValue();
                
                for (Book book : borrowed) {
                    writer.write(p.getId() + "," + p.getName() + "," + book.getId() + "," + book.getTitle() + "," + book.getAuthor() + "," + book.getUrl() + "\n");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error saving borrowed books: " + e.getMessage());
        }
    }

    public static HashMap<Person, List<Book>> loadBorrowedBooks(String filename, List<Student> students, List<Admin> admins){
        HashMap<Person, List<Book>> map = new HashMap<>();
        File file = new File(filename);
        if (!file.exists()) {
            return map;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",", 6);
                int id = Integer.parseInt(data[0]);
                String name = data[1];
                Person p = null;

                for (Student student : students) {
                   if (student.getId() == id && student.getName().equalsIgnoreCase(name)) {
                        p=student;
                        break;
                   }
                }

                if (p == null) {
                    for (Admin admin : admins) {
                        if (admin.getId() == id && admin.getName().equalsIgnoreCase(name)) {
                            p=admin;
                            break;
                        }
                    }
                }
                if (p==null) {
                    p = new Person(id, name);
                }

                Book b = new Book(data[3], data[4], data[5]);
                b.setId(Integer.parseInt(data[2]));                
                map.computeIfAbsent(p, k-> new ArrayList<>()).add(b);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error loading map: " + e.getMessage());
        }

        return map;
    }
}
