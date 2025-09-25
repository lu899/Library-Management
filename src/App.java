import java.util.*;

public class App {
    static Scanner scanner = new Scanner(System.in);
    static Lib myLibrary = new Lib("Lucy's Library");
    static Random random = new Random();
        
    public static void main(String[] args) throws Exception {

        System.out.println("**********************************");
        System.out.println("Hello, Welcome to Lucy's Library!!");
        System.out.println("**********************************");
        
        boolean isrunning = true;
        System.out.print("Are you a student or an admin: ");
        String identity = scanner.nextLine();

        if (identity.equals("student")) {
            System.out.print("Enter Your name: ");
            String name = scanner.nextLine();
            Student student = myLibrary.getStudent(name);

            if(student != null){
                   while (isrunning) {
                    System.out.println("\n*****************");
                    System.out.println("     Welcome     ");
                    System.out.println("*****************");
                    
                    System.out.println("\n1. View All Books");
                    System.out.println("2. Borrow a book");
                    System.out.println("3. Return a book");
                    System.out.println("4. Exit");
                    System.out.print("Enter your choice(1-4): ");
                    int ans = scanner.nextInt();
                    scanner.nextLine();

                    switch (ans) {
                        case 1:
                            myLibrary.display();
                            break;
                        case 2:
                            System.out.print("How many books do you want: ");
                            int numOfbooks = scanner.nextInt();
                            scanner.nextLine();
                            myLibrary.borrowBook(student, numOfbooks, scanner);
                            break;
                        case 3:
                            myLibrary.returnBook(student, scanner);
                            break;
                        case 4:
                            isrunning = false;
                            break;
                        default:
                            System.out.println("Enter a valid response");
                            break;
                    }
                }        
            } else {
                System.out.println("Sorry you aren't a registered student!!\n");
            }
        } else if (identity.equals("admin")) {
            System.out.print("Enter your name: ");
            String adminName = scanner.nextLine();
            Admin admin = myLibrary.getAdmin(adminName);
            
            if(admin != null){            
                while (isrunning) {
                    System.out.println("\n*****************");
                    System.out.println("     Welcome     ");
                    System.out.println("*****************");
                    System.out.println("\nAs an admin you can: ");
                    System.out.println("1. Borrow a book");
                    System.out.println("2. Return a book");
                    System.out.println("3. Add a book");
                    System.out.println("4. View all books");
                    System.out.println("5. Register a student");
                    System.out.println("6. Exit");
                    System.out.print("Enter your choice: ");
                    int choice = scanner.nextInt();
                    scanner.nextLine();

                    switch (choice) {
                        case 1:
                            System.out.print("How many books do you want: ");
                            int num = scanner.nextInt();
                            scanner.nextLine();

                            myLibrary.borrowBook(admin, num, scanner);                    
                            break;
                        case 2:                                                                                   
                            myLibrary.returnBook(admin, scanner);
                            break;
                        case 3:
                            System.out.print("How many books are you adding: ");
                            int total = scanner.nextInt();
                            scanner.nextLine();

                            myLibrary.addBook(total, scanner);
                            break;
                        case 4:
                            myLibrary.display();
                            break;
                        case 5:
                            System.out.print("Enter student name: ");
                            String name = scanner.nextLine().trim();
                            Student s = new Student(name);
                            s.setId(random.nextInt(200));
                            myLibrary.registerStudent(s);
                            break;
                        case 6:
                            isrunning = false;
                            break;
                        default:
                            System.out.println("Pick an appropiate choice!!");
                            break;
                    }
                }
            } else {
                System.out.println("Sorry you aren't a registered admin!!\n");
            }
        }else{
            System.out.println("Enter a valid response");
        }

        scanner.close();
    }
}
