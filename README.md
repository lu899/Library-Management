# Library Management System

A simple Java-based Library Management System for managing books, students, and admins. This project allows students to borrow and return books, and admins to manage the library's collection and users. Data is persisted using CSV files.

## Features

- **Student Functions:**
  - View all available books
  - Borrow books
  - Return borrowed books

- **Admin Functions:**
  - Add new books to the library
  - View all books
  - Register new students
  - Borrow and return books on behalf of users

- **Data Persistence:**
  - All books, students, admins, and borrowed books are stored in CSV files (`libBooks.csv`, `students.csv`, `admins.csv`, `borrowed.csv`)
  - Data is loaded at startup and saved after any changes

## Folder Structure

- `src/` - Java source files
- `lib/` - Dependencies (if any)
- `bin/` - Compiled output files
- CSV files (`libBooks.csv`, `students.csv`, `admins.csv`, `borrowed.csv`) are stored in the project root

## Main Classes

- `App.java` - Entry point and main menu logic
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

3. **Follow the prompts** to log in as a student or admin and use the system.

## Requirements

- Java 8 or higher
- Visual Studio Code (recommended for development)

## Notes

- Make sure the CSV files are present in the project root for data persistence.
- The system matches users and books by name and ID to ensure correct operations.
- All changes (borrowing, returning, adding books, registering students) are saved automatically.

---

> This project was developed as a learning exercise for Java file I/O, object-oriented programming, and basic data management.