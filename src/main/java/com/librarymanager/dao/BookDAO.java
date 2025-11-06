package com.librarymanager.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.librarymanager.*;
import com.librarymanager.model.*;

public class BookDAO implements CrudDAO<Book> {
    
    public Book insert(Book book) throws SQLException {
        Validator.validateNotEmpty(book.getTitle(), "Book title");
        Validator.validateNotEmpty(book.getPublisher(), "Publisher");
        Validator.validateNotNull(book.getLanguage(), "Language");

        String sql = "INSERT INTO book(title, language, publisher) VALUES (?, ?, ?) RETURNING book_id";

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, book.getTitle());
            ps.setString(2, book.getLanguage().name());
            ps.setString(3, book.getPublisher());

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    book.setBookId(rs.getInt(1));
                }
            }
        }

        return book;
    }

    public void update(Book book) throws SQLException {
        Validator.validateId(book.getBookId(), "Book ID");
        Validator.validateNotEmpty(book.getTitle(), "Book title");
        Validator.validateNotEmpty(book.getPublisher(), "Publisher");
        Validator.validateNotNull(book.getLanguage(), "Language");

        String sql = "UPDATE book SET title = ?, language = ?, publisher = ? WHERE book_id = ?";

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, book.getTitle());
            ps.setString(2, book.getLanguage().name());
            ps.setString(3, book.getPublisher());
            ps.setInt(4, book.getBookId());

            ps.executeUpdate();
        }
    }

    public void delete(int bookId) throws SQLException {
        Validator.validateId(bookId, "Book ID");

        String sql = "DELETE FROM book WHERE book_id = ?";

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, bookId);
            ps.executeUpdate();
        }
    }

    public Book findById(int bookId) throws SQLException {
        Validator.validateId(bookId, "Book ID");

        String sql = "SELECT book_id, title, language, publisher FROM book WHERE book_id = ?";

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, bookId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return map(rs);
                }
            }
        }

        return null;
    }

    public List<Book> findAll() throws SQLException {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT book_id, title, language, publisher FROM book";

        try (Connection c = DatabaseConnection.getConnection();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                books.add(map(rs));
            }
        }

        return books;
    }

    public List<Book> findByTitle(String pattern) throws SQLException {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT book_id, title, language, publisher FROM book WHERE title ILIKE ?";

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, "%" + pattern + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    books.add(map(rs));
                }
            }
        }

        return books;
    }

    private Book map(ResultSet rs) throws SQLException {
        return new Book(
                rs.getInt("book_id"),
                rs.getString("title"),
                Language.valueOf(rs.getString("language").toUpperCase()),
                rs.getString("publisher")
        );
    }
}
