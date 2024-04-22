package Facade_Patt;

import java.util.ArrayList;
import java.util.List;

class Book {
    private String title;
    private String author;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }
}

class BookInventorySystem {
    private List<Book> booksInInventory;

    public BookInventorySystem() {
        booksInInventory = new ArrayList<>();
        booksInInventory.add(new Book("Harry Potter", "J.K. Rowling"));
        booksInInventory.add(new Book("The Great Gatsby", "F. Scott Fitzgerald"));
        booksInInventory.add(new Book("To Kill a Mockingbird", "Harper Lee"));
    }

    public boolean isBookAvailable(String title) {
        for (Book book : booksInInventory) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return true;
            }
        }
        return false;
    }

}

// Subsystem for managing users
class UserManagementSystem {
}

class LibraryFacade {
    private BookInventorySystem bookInventory;
    private UserManagementSystem userManagement;

    public LibraryFacade() {
        this.bookInventory = new BookInventorySystem();
        this.userManagement = new UserManagementSystem();
    }

    public boolean borrowBook(String title, String username) {
        if (bookInventory.isBookAvailable(title)) {
            System.out.println("Book '" + title + "' borrowed by " + username);
            return true;
        } else {
            System.out.println("Book '" + title + "' is not available for borrowing.");
            return false;
        }
    }

    public void returnBook(String title, String username) {
        System.out.println("Book '" + title + "' returned by " + username);
    }

}

public class Main {
    public static void main(String[] args) {
        LibraryFacade libraryFacade = new LibraryFacade();

        String bookTitle = "Harry Potter";
        String borrower = "John";
        boolean isBorrowed = libraryFacade.borrowBook(bookTitle, borrower);
        if (isBorrowed) {
            libraryFacade.returnBook(bookTitle, borrower);
        }
    }
}
