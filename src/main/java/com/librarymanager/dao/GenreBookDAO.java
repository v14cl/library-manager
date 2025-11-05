package com.librarymanager.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.librarymanager.DatabaseConnection;
import com.librarymanager.Validator;
import com.librarymanager.model.GenreBook;
import com.librarymanager.model.Genre;
import com.librarymanager.model.Book;
import com.librarymanager.model.Language;

public class GenreBookDAO {

    public void insert(GenreBook gb) throws SQLException {
        Validator.validateId(gb.getBookId(), "Book ID");
        Validator.validateId(gb.getGenreId(), "Genre ID");

        String sql = "INSERT INTO genre_book(book_id, genre_id) VALUES (?, ?)";
        try (Connection c = DatabaseConnection.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, gb.getBookId());
            ps.setInt(2, gb.getGenreId());
            ps.executeUpdate();
        }
    }

    public void delete(GenreBook gb) throws SQLException {
        Validator.validateId(gb.getBookId(), "Book ID");
        Validator.validateId(gb.getGenreId(), "Genre ID");

        String sql = "DELETE FROM genre_book WHERE book_id = ? AND genre_id = ?";
        try (Connection c = DatabaseConnection.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, gb.getBookId());
            ps.setInt(2, gb.getGenreId());
            ps.executeUpdate();
        }
    }

    public List<Genre> findGenresByBookId(int bookId) throws SQLException {
        Validator.validateId(bookId, "Book ID");

        List<Genre> genres = new ArrayList<>();

        String sql = "SELECT g.genre_id, g.name " +
                     "FROM genre g " +
                     "JOIN genre_book gb ON g.genre_id = gb.genre_id " +
                     "WHERE gb.book_id = ?";

        try (Connection c = DatabaseConnection.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, bookId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    genres.add(new Genre(rs.getInt("genre_id"), rs.getString("name")));
                }
            }
        }
        return genres;
    }

    public List<Book> findBooksByGenreId(int genreId) throws SQLException {
        Validator.validateId(genreId, "Genre ID");

        List<Book> books = new ArrayList<>();

        String sql = "SELECT b.book_id, b.title, b.language, b.publisher " +
                "FROM book b " +
                "JOIN genre_book gb ON b.book_id = gb.book_id " +
                "WHERE gb.genre_id = ?";
                
        try (Connection c = DatabaseConnection.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, genreId);
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
