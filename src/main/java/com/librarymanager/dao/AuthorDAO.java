package com.librarymanager.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.librarymanager.DatabaseConnection;
import com.librarymanager.Validator;
import com.librarymanager.model.Author;

public class AuthorDAO {

    public Author insert(Author author) throws SQLException {
        Validator.validateNotEmpty(author.getFullName(), "Author name");

        String sql = "INSERT INTO author(full_name) VALUES (?) RETURNING author_id";

        try (Connection c = DatabaseConnection.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, author.getFullName());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    author.setAuthorId(rs.getInt(1));
                }
            }
        }
        return author;
    }

    public void update(Author author) throws SQLException {
        Validator.validateId(author.getAuthorId(), "Author ID");
        Validator.validateNotEmpty(author.getFullName(), "Author name");

        String sql = "UPDATE author SET full_name = ? WHERE author_id = ?";

        try (Connection c = DatabaseConnection.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, author.getFullName());
            ps.setInt(2, author.getAuthorId());
            ps.executeUpdate();
        }
    }

    public void delete(int authorId) throws SQLException {
        Validator.validateId(authorId, "Author ID");

        String sql = "DELETE FROM author WHERE author_id = ?";

        try (Connection c = DatabaseConnection.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, authorId);
            ps.executeUpdate();
        }
    }

    public Author findById(int authorId) throws SQLException {
        Validator.validateId(authorId, "Author ID");

        String sql = "SELECT author_id, full_name FROM author WHERE author_id = ?";

        try (Connection c = DatabaseConnection.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, authorId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Author(rs.getInt("author_id"), rs.getString("full_name"));
                }
            }
        }
        return null;
    }

    public List<Author> findAll() throws SQLException {
        List<Author> authors = new ArrayList<>();
        
        String sql = "SELECT author_id, full_name FROM author";

        try (Connection c = DatabaseConnection.getConnection();
                Statement st = c.createStatement();
                ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                authors.add(new Author(rs.getInt("author_id"), rs.getString("full_name")));
            }
        }
        return authors;
    }
}
