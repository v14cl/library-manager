package com.librarymanager;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.librarymanager.dao.BookDAO;
import com.librarymanager.dao.ClientDAO;
import com.librarymanager.dao.CheckoutDAO;
import com.librarymanager.model.Book;
import com.librarymanager.model.Client;
import com.librarymanager.model.Checkout;
import com.librarymanager.model.Language;

public class LibraryService {
    private BookDAO bookDAO = new BookDAO();
    private ClientDAO clientDAO = new ClientDAO();
    private CheckoutDAO checkoutDAO = new CheckoutDAO();

    public LibraryService(BookDAO bookDAO, ClientDAO clientDAO, CheckoutDAO checkoutDAO) {
        this.bookDAO = bookDAO;
        this.clientDAO = clientDAO;
        this.checkoutDAO = checkoutDAO;
    }

    public List<Book> listAllBooks() throws SQLException {
        return bookDAO.findAll();
    }

    public List<Book> searchBooksByTitle(String pattern) throws SQLException {
        return bookDAO.findByTitle(pattern);
    }

    public Client addClient(String fullName, String phone) throws SQLException {
        return clientDAO.insert(new Client(fullName, phone));
    }

    public Book addBook(String title, Language language, String publisher) throws SQLException {
        return bookDAO.insert(new Book(title, language, publisher));
    }

    public Checkout checkoutBook(int clientId, int bookId) throws SQLException {
        LocalDate taken = LocalDate.now();
        LocalDate deadline = taken.plusDays(30);
        Checkout ch = new Checkout(clientId, bookId, taken, deadline);
        return checkoutDAO.insert(ch);
    }

    public void returnBook(int checkoutId) throws SQLException {
        checkoutDAO.markReturned(checkoutId, LocalDate.now());
    }

    public List<Checkout> getOverdue() throws SQLException {
        return checkoutDAO.findOverdue(LocalDate.now());
    }

    public List<Checkout> activeByClient(int clientId) throws SQLException {
        return checkoutDAO.findActiveByClient(clientId);
    }
}