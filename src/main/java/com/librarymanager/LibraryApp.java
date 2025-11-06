package com.librarymanager;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.librarymanager.model.Book;
import com.librarymanager.model.Client;
import com.librarymanager.model.Checkout;

public class LibraryApp {
    private static final LibraryService service = new LibraryService();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {
        boolean run = true;
        while (run) {
            printMenu();
            String cmd = scanner.nextLine().trim();
            switch (cmd) {
                case "1" -> listBooks();
                case "2" -> searchBooks();
                case "3" -> addClient();
                case "4" -> checkoutBook();
                case "5" -> returnBook();
                case "6" -> showOverdue();
                case "0" -> run = false;
                default -> System.out.println("Unknown command");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n=== Library ===");
        System.out.println("1. List all books");
        System.out.println("2. Search books by title");
        System.out.println("3. Add client");
        System.out.println("4. Checkout book");
        System.out.println("5. Return book");
        System.out.println("6. Show overdue");
        System.out.println("0. Exit");
        System.out.print("Choose: ");
    }

    private static void listBooks() throws SQLException {
        List<Book> books = service.listAllBooks();
        books.forEach(System.out::println);
    }

    private static void searchBooks() throws SQLException {
        System.out.print("Title pattern: ");
        String p = scanner.nextLine();
        List<Book> books = service.searchBooksByTitle(p);
        books.forEach(System.out::println);
    }

    private static void addClient() throws SQLException {
        System.out.print("Full name: ");
        String name = scanner.nextLine();
        System.out.print("Phone: ");
        String phone = scanner.nextLine();
        Client c = service.addClient(name, phone);
        System.out.println("Added: " + c);
    }

    private static void checkoutBook() throws SQLException {
        System.out.print("Client id: ");
        int clientId = Integer.parseInt(scanner.nextLine());
        System.out.print("Book id: ");
        int bookId = Integer.parseInt(scanner.nextLine());
        Checkout ch = service.checkoutBook(clientId, bookId);
        System.out.println("Checked out: " + ch);
    }

    private static void returnBook() throws SQLException {
        System.out.print("Checkout id: ");
        int id = Integer.parseInt(scanner.nextLine());
        service.returnBook(id);
        System.out.println("Marked returned");
    }

    private static void showOverdue() throws SQLException {
        List<Checkout> list = service.getOverdue();
        list.forEach(System.out::println);
    }
}