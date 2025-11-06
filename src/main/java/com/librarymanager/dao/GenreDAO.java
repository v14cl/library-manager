package com.librarymanager.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.librarymanager.DatabaseConnection;
import com.librarymanager.Validator;
import com.librarymanager.model.Genre;

public class GenreDAO implements CrudDAO<Genre> {

    public Genre insert(Genre genre) throws SQLException {
        Validator.validateNotEmpty(genre.getName(), "Genre name");

        String sql = "INSERT INTO genre(name) VALUES (?) RETURNING genre_id";

        try (Connection c = DatabaseConnection.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, genre.getName());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next())
                    genre.setGenreId(rs.getInt(1));
            }
        }
        return genre;
    }

    public void update(Genre genre) throws SQLException {
        Validator.validateId(genre.getGenreId(), "Genre ID");
        Validator.validateNotEmpty(genre.getName(), "Genre name");

        String sql = "UPDATE genre SET name = ? WHERE genre_id = ?";

        try (Connection c = DatabaseConnection.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, genre.getName());
            ps.setInt(2, genre.getGenreId());
            ps.executeUpdate();
        }
    }

    public void delete(int genreId) throws SQLException {
        Validator.validateId(genreId, "Genre ID");

        String sql = "DELETE FROM genre WHERE genre_id = ?";

        try (Connection c = DatabaseConnection.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, genreId);
            ps.executeUpdate();
        }
    }

    public Genre findById(int genreId) throws SQLException {
        Validator.validateId(genreId, "Genre ID");

        String sql = "SELECT genre_id, name FROM genre WHERE genre_id = ?";

        try (Connection c = DatabaseConnection.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, genreId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Genre(rs.getInt("genre_id"), rs.getString("name"));
                }
            }
        }
        return null;
    }

    public List<Genre> findAll() throws SQLException {
        List<Genre> list = new ArrayList<>();
        
        String sql = "SELECT genre_id, name FROM genre";

        try (Connection c = DatabaseConnection.getConnection();
                Statement st = c.createStatement();
                ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Genre(rs.getInt("genre_id"), rs.getString("name")));
            }
        }
        return list;
    }
}
