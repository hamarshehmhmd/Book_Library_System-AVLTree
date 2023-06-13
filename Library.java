package Library;

import javax.swing.*;
import java.util.*;


// Space Complexity of this code in the best case is O(log n) and in worst case is O(n)
// Time Complexity of this code in the worst case is O(n)

public class Library {
    public class Book {
        String title;
        String author;
        int isbn;
        int year;
        int height;
        int searchCount; // New field to track search count
        Book left;
        Book right;

        public Book(String title, String author, int isbn, int year) {
            this.title = title;
            this.author = author;
            this.isbn = isbn;
            this.year = year;
            this.height = 1;
            this.searchCount = 0;
        }
    }

    private Book root;
    private PriorityQueue<Book> popularBooks; // PriorityQueue to maintain top searched books

    public Library() {
        popularBooks = new PriorityQueue<>(Comparator.comparingInt(b -> b.searchCount));
    }

    public void addBook(String title, String author, int isbn, int year) {
        root = addBook(root, title, author, isbn, year);
    }

    private Book addBook(Book element, String title, String author, int isbn, int year) {
        if (element == null)
            return new Book(title, author, isbn, year);

        if (isbn < element.isbn)
            element.left = addBook(element.left, title, author, isbn, year);
        else if (isbn > element.isbn)
            element.right = addBook(element.right, title, author, isbn, year);
        else
            return element;

        return balance(element);
    }

    public void remove(int isbn) {
        root = remove(root, isbn);
    }

    private Book remove(Book element, int isbn) {
        if (element == null)
            return null;

        if (isbn < element.isbn)
            element.left = remove(element.left, isbn);
        else if (isbn > element.isbn)
            element.right = remove(element.right, isbn);
        else {
            if (element.left == null || element.right == null) {
                Book temp = null;
                if (element.left == null)
                    temp = element.right;
                else
                    temp = element.left;

                if (temp == null) {
                    temp = element;
                    element = null;
                } else
                    element = temp;
            } else {
                Book temp = minValueNode(element.right);
                element.isbn = temp.isbn;
                element.title = temp.title;
                element.author = temp.author;
                element.year = temp.year;

                element.right = remove(element.right, temp.isbn);
            }
        }

        if (element == null)
            return null;

        return balance(element);
    }

    private int height(Book element) {
        if (element == null)
            return 0;
        return element.height;
    }

    public boolean isBalanced() {
        return isBalanced(root);
    }

    private boolean isBalanced(Book element) {
        if (element == null)
            return true;

        int balance = getBalance(element);

        if (Math.abs(balance) > 1)
            return false;

        return isBalanced(element.left) && isBalanced(element.right);
    }

    private int getBalance(Book element) {
        if (element == null)
            return 0;
        return height(element.left) - height(element.right);
    }

    private Book balance(Book element) {
        if (element == null)
            return null;

        updateHeight(element);

        int balance = getBalance(element);

        if (balance > 1) {
            if (getBalance(element.left) >= 0)
                return rightRotate(element);
            else {
                element.left = leftRotate(element.left);
                return rightRotate(element);
            }
        }

        if (balance < -1) {
            if (getBalance(element.right) <= 0)
                return leftRotate(element);
            else {
                element.right = rightRotate(element.right);
                return leftRotate(element);
            }
        }

        return element;
    }

    private void updateHeight(Book element) {
        if (element != null)
            element.height = 1 + Math.max(height(element.left), height(element.right));
    }

    private Book leftRotate(Book z) {
        Book y = z.right;
        Book T2 = y.left;

        y.left = z;
        z.right = T2;

        updateHeight(z);
        updateHeight(y);

        return y;
    }

    private Book rightRotate(Book y) {
        Book x = y.left;
        Book T2 = x.right;

        x.right = y;
        y.left = T2;

        updateHeight(y);
        updateHeight(x);

        return x;
    }

    private Book minValueNode(Book element) {
        Book current = element;
        while (current.left != null)
            current = current.left;
        return current;
    }
    // defualt search
    public Book searchByISBN(int isbn) {
        return searchByISBN(root, isbn);
    }
    //defualt search
    private Book searchByISBN(Book element, int isbn) {
        if (element == null || element.isbn == isbn) {
            element.searchCount++;
            return element;
        }
        if (isbn < element.isbn)
            return searchByISBN(element.left, isbn);

        return searchByISBN(element.right, isbn);
    }
    // bonus search
    public List<Book> searchByAuthor(String author) {
        List<Book> result = new ArrayList<>();
        searchByAuthor(root, author, result);
        return result;
    }
    // bonus search
    private void searchByAuthor(Book element, String author, List<Book> result) {
        if (element != null) {
            searchByAuthor(element.left, author, result);
            if (element.author.equals(author))
                element.searchCount++;

            result.add(element);
            searchByAuthor(element.right, author, result);
        }
    }
    // bonus search
    public List<Book> searchByTitle(String title) {
        List<Book> result = new ArrayList<>();
        searchByTitle(root, title, result);
        return result;
    }
    // bonus search
    private void searchByTitle(Book element, String title, List<Book> result) {
        if (element != null) {
            searchByTitle(element.left, title, result);
            if (element.title.equals(title))
                result.add(element);
            element.searchCount++;

            searchByTitle(element.right, title, result);
        }
    }
    // bonus search
    public List<Book> searchByYear(int year) {
        List<Book> result = new ArrayList<>();
        searchByYear(root, year, result);
        return result;
    }
    // bonus search
    private void searchByYear(Book element, int year, List<Book> result) {
        if (element != null) {
            searchByYear(element.left, year, result);
            if (element.year == year)
                result.add(element);
            element.searchCount++;

            searchByYear(element.right, year, result);
        }
    }
    // bonus search
    public void searchBook(int isbn) {
        Book book = searchByISBN(isbn);
        if (book != null) {
            book.searchCount++;
            System.out.println("Book found: " + book.title);
        } else {
            System.out.println("Book not found!");
        }
    }

    // bonus search
    public List<Book> getTopSearchedBooks() {
        List<Book> topBooks = new ArrayList<>();
        getTopSearchedBooks(root, topBooks);
        return topBooks;
    }
    // bonus search
    private void getTopSearchedBooks(Book node, List<Book>  topBooks) {
        if (node != null) {
            getTopSearchedBooks(node.left, topBooks);
            if (topBooks.size()< 10) {
                topBooks.add(node);
            } else {
                int minSearchCount = topBooks.get(0).searchCount;
                int index = 0;
                for (int i = 1; i < topBooks.size(); i++) {
                    if (topBooks.get(i).searchCount < minSearchCount) {
                        minSearchCount = topBooks.get(i).searchCount;
                        index = i;
                    }
                }
                if (node.searchCount > minSearchCount) {
                    topBooks.set(index, node);
                }
            }
            getTopSearchedBooks(node.right, topBooks);
        }
    }
    public void display(List<Book> allBooks){
        for (Book node : allBooks) {
            System.out.println("Title: " + node.title + ", Author: " + node.author + ", ISBN: " + node.isbn + ", Year: " + node.year);
        }
    }


    // bonus search
    public List<Book> getBooksByAuthor(String author) {
        List<Book> result = new ArrayList<>();
        getBooksByAuthor(root, author, result);
        return result;
    }
    // bonus search
    private void getBooksByAuthor(Book element, String author, List<Book> result) {
        if (element != null) {
            getBooksByAuthor(element.left, author, result);
            if (element.author.equals(author))
                result.add(element);
            getBooksByAuthor(element.right, author, result);
        }
    }
    public void listAllBooks() {
        inorderTraversal(root);
    }
    private void inorderTraversal(Book node) {
        if (node != null) {
            inorderTraversal(node.left);
            System.out.println("Title: " + node.title + ", Author: " + node.author + ", ISBN: " + node.isbn + ", Year: " + node.year);
            inorderTraversal(node.right);

        }
    }
}
