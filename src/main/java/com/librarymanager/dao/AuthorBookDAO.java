package com.librarymanager.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.librarymanager.*;
import com.librarymanager.model.*;

public class AuthorBookDAO {

    public void insert(AuthorBook ab) throws SQLException {
        Validator.validateId(ab.getBookId(), "Book ID");
        Validator.validateId(ab.getAuthorId(), "Author ID");

        String sql = "INSERT INTO author_book(book_id, author_id) VALUES (?, ?)";

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, ab.getBookId());
            ps.setInt(2, ab.getAuthorId());
            ps.executeUpdate();
        }
    }

    public void delete(AuthorBook ab) throws SQLException {
        Validator.validateId(ab.getBookId(), "Book ID");
        Validator.validateId(ab.getAuthorId(), "Author ID");

        String sql = "DELETE FROM author_book WHERE book_id = ? AND author_id = ?";

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, ab.getBookId());
            ps.setInt(2, ab.getAuthorId());
            ps.executeUpdate();
        }
    }

    public List<Author> findAuthorsByBookId(int bookId) throws SQLException {
        Validator.validateId(bookId, "Book ID");

        List<Author> authors = new ArrayList<>();

        String sql = "SELECT a.author_id, a.full_name " +
                     "FROM author a " +
                     "JOIN author_book ab ON a.author_id = ab.author_id " +
                     "WHERE ab.book_id = ?";

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, bookId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    authors.add(new Author(rs.getInt("author_id"), rs.getString("full_name")));
                }
            }
        }
        return authors;
    }

    public List<Book> findBooksByAuthorId(int authorId) throws SQLException {
        Validator.validateId(authorId, "Author ID");

        List<Book> books = new ArrayList<>();

        String sql = "SELECT b.book_id, b.title, b.language, b.publisher " +
                     "FROM book b " +
                     "JOIN author_book ab ON b.book_id = ab.book_id " +
                     "WHERE ab.author_id = ?";
                     
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, authorId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    books.add(new Book(
                            rs.getInt("book_id"),
                            rs.getString("title"),
                            Language.valueOf(rs.getString("language").toUpperCase()),
                            rs.getString("publisher")));
                }
            }
        }
        return books;
    }
}
