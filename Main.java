package Library;

import javax.swing.*;
import java.util.*;


package Library;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        // Add some initial books to the library
        library.addBook("Book 1", "Author 1", 12345, 2000);
        library.addBook("Book 2", "Author 2", 67890, 2005);
        library.addBook("Book 3", "Author 1", 11111, 2010);

        boolean exit = false;
        while (!exit) {
            System.out.println("Select an option:");
            System.out.println("1. Add a book");
            System.out.println("2. Remove a book");
            System.out.println("3. Search for a book by ISBN");
            System.out.println("4. Search for books by author");
            System.out.println("5. Search for books by title");
            System.out.println("6. Search for books by year");
            System.out.println("7. Display all  book ");
            System.out.println("8. Display popular books");
            System.out.println("9. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.println("Enter the title:");
                    String title = scanner.nextLine();
                    System.out.println("Enter the author:");
                    String author = scanner.nextLine();
                    System.out.println("Enter the ISBN:");
                    int isbn = scanner.nextInt();
                    System.out.println("Enter the year:");
                    int year = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    library.addBook(title, author, isbn, year);
                    System.out.println("Book added successfully.");
                    break;

                case 2:
                    System.out.println("Enter the ISBN of the book to remove:");
                    int isbnToRemove = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    library.remove(isbnToRemove);
                    System.out.println("Book removed successfully.");
                    break;

                case 3:
                    System.out.println("Enter the ISBN of the book to search:");
                    int isbnToSearch = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    Library.Book book = library.searchByISBN(isbnToSearch);
                    if (book != null) {
                        System.out.println("Book found: " + book.title);
                    } else {
                        System.out.println("Book not found!");
                    }
                    break;

                case 4:
                    System.out.println("Enter the author:");
                    String authorToSearch = scanner.nextLine();
                    List<Library.Book> booksByAuthor = library.searchByAuthor(authorToSearch);
                    if (!booksByAuthor.isEmpty()) {
                        System.out.println("Books by " + authorToSearch + ":");
                        for (Library.Book b : booksByAuthor) {
                            System.out.println(b.title);
                        }
                    } else {
                        System.out.println("No books found by " + authorToSearch);
                    }
                    break;

                case 5:
                    System.out.println("Enter the title:");
                    String titleToSearch = scanner.nextLine();
                    List<Library.Book> booksByTitle = library.searchByTitle(titleToSearch);
                    if (!booksByTitle.isEmpty()) {
                        System.out.println("Books with title " + titleToSearch + ":");
                        for (Library.Book b : booksByTitle) {
                            System.out.println(b.title + " by " + b.author);
                        }
                    } else {
                        System.out.println("No books found with title " + titleToSearch);
                    }
                    break;

                case 6:
                    System.out.println("Enter the year:");
                    int yearToSearch = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    List<Library.Book> booksByYear = library.searchByYear(yearToSearch);
                    if (!booksByYear.isEmpty()) {
                        System.out.println("Books published in " + yearToSearch + ":");
                        for (Library.Book b : booksByYear) {
                            System.out.println(b.title + " by " + b.author);
                        }
                    } else {
                        System.out.println("No books found published in " + yearToSearch);
                    }
                    break;
                case 7:
                    library.listAllBooks();
                    break;
                case 8:
                    List<Library.Book> books=library.getTopSearchedBooks();
                    library.display(books);
                    break;

                case 9:
                    exit = true;
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }

        scanner.close();
    }
}
