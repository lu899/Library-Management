# Library Management System (Java GUI)

A modern Java-based Library Management System with a graphical user interface (GUI) built using Swing. This project allows students to borrow and return books, and admins to manage the library's collection and users. All data is persisted using CSV files.

## Features

- **Student Functions:**
  - Log in via a GUI form
  - View all available books with images
  - Borrow books with a single click
  - Return borrowed books through a dedicated GUI panel

- **Admin Functions:**
  - Log in via a GUI form
  - Add new books to the library (with image picker)
  - View all books in a grid layout
  - Register new students using a GUI form
  - Borrow and return books on behalf of users

- **Data Persistence:**
  - All books, students, admins, and borrowed books are stored in CSV files (`libBooks.csv`, `students.csv`, `admins.csv`, `borrowed.csv`)
  - Data is loaded at startup and saved after any changes

- **User Experience:**
  - Responsive GUI with panels for navigation, book display, and user actions
  - Image previews for books
  - Error messages and confirmations via dialog boxes

## Folder Structure

- `src/` - Java source files
- `lib/` - Dependencies (if any)
- `bin/` - Compiled output files
- CSV files (`libBooks.csv`, `students.csv`, `admins.csv`, `borrowed.csv`) are stored in the project root
- `Images/` - Book cover images

## Main Classes

- `App.java` - Entry point and main GUI logic
- `Lib.java` - Library management logic
- `Book.java` - Book model
- `Person.java`, `Student.java`, `Admin.java` - User models
- `FileCSV.java` - Handles reading/writing CSV files

## How to Run

1. **Compile the project:**
   ```
   javac -d bin src/*.java
   ```

2. **Run the application:**
   ```
   java -cp bin App
   ```

3. **Use the GUI** to log in as a student or admin and manage the library.

## Requirements

- Java 8 or higher
- Visual Studio Code (recommended for development)
- Swing (included in standard Java)

## Notes

- Ensure the CSV files and images are present in the project root for full functionality.
- The system matches users and books by name and ID to ensure correct operations.
- All changes (borrowing, returning, adding books, registering students) are saved automatically.
- The GUI is designed for ease of use and quick access to library features.

---

> This project was developed as a learning exercise for Java Swing GUI, file I/O, object-oriented programming, and basic data management.