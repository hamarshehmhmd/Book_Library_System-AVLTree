# AVL Tree Library

The AVL Tree Library is a Java implementation of an AVL tree data structure specifically designed to manage books in a library. It provides functionality to add books, remove books, search for books by ISBN, list all books, and check if the library is balanced.

## Features

- Add a book: Allows you to add a book to the library by providing its title, author, ISBN, and year of publication.
- Remove a book: Enables you to remove a book from the library by specifying its ISBN.
- Search for a book by ISBN: Allows you to search for a book in the library by providing its ISBN. Returns the book's details if found, or a message indicating that the book was not found.
- List all books: Displays the details of all books in the library in a sorted order (inorder traversal).
- Check if the library is balanced: Determines whether the AVL tree representing the library is balanced or not.
- Bonus features:
  - Search for books by author: Allows you to search for books written by a specific author and returns a list of matching books.
  - Search for books by title: Allows you to search for books with a specific title and returns a list of matching books.
  - Search for books by year: Allows you to search for books published in a specific year and returns a list of matching books.
  - Search book by ISBN with search count: Provides the ability to search for a book by ISBN, incrementing a search count for each search operation performed on the book.
  - Get top searched books: Retrieves a list of the top 10 most searched books in the library based on their search count.

## Usage

To use the AVL Tree Library, follow these steps:

1. Create an instance of the `Library` class:

```java
Library library = new Library();
 
```
2. Use the available methods to perform various operations on the library:

- **Add a book**: To add a book to the library, use the `addBook()` method. Provide the title, author, ISBN, and year of the book as parameters.
   
   ```java
   library.addBook("Title", "Author", 1234567890, 2022);
    ```

- **Remove a book**: To remove a book from the library, use the remove() method. Pass the ISBN of the book to be removed as a parameter.

    ```java
    library.remove(1234567890);
    ```

 - **Search for a book by ISBN**: To search for a book by its ISBN, use the searchByISBN() method. Provide the ISBN of the book as a parameter. The method will return the book if found, or null if not found.

    ```java
    Book book = library.searchByISBN(1234567890);
        if (book != null) {
        // Book found
        // Perform actions on the book
        } else {
        // Book not found
        }
    ```
- **Search for books by author**: To search for books by a specific author, use the searchByAuthor() method. Provide the author's name as a parameter. The method will return a list of books written by the specified author.

    ```java
        List<Book> booksByAuthor = library.searchByAuthor("Author Name");
        // Iterate over the list and perform actions on each book
    ```

- **search for books by title**: To search for books by a specific title, use the searchByTitle() method. Provide the title as a parameter. The method will return a list of books with matching titles.

    ```java
        List<Book> booksByTitle = library.searchByTitle("Book Title");
    // Iterate over the list and perform actions on each book
    ```

- **Search for books by year**:To search for books published in a specific year, use the searchByYear() method. Provide the year as a parameter. The method will return a list of books published in the specified year

```java
    List<Book> booksByYear = library.searchByYear(2022);
    // Iterate over the list and perform actions on each book

```

- **Get top searched books**: To get the top searched books in the library, use the getTopSearchedBooks() method. The method will return a list of books sorted by their search count, with the most searched books appearing first.

```java
List<Book> topSearchedBooks = library.getTopSearchedBooks();
// Iterate over the list and perform actions on each book

```

- **List all books**: To list all the books in the library, use the listAllBooks() method. This method will print the details of all the books in the library.

```java
library.listAllBooks();

```

## License

This project is licensed under the [MIT License](LICENSE). See the LICENSE file for details.

Feel free to modify and adapt the code to suit your specific needs.

## Acknowledgements

The AVL Tree Library code was developed by Mohammad Hamarsheh & Abdullah Thweib. It is inspired by the AVL tree data structure and designed to handle book management in a library.

If you find this library helpful, consider providing feedback or contributing to its improvement.










