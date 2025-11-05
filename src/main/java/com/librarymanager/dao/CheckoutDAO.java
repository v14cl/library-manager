package com.librarymanager.dao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.librarymanager.DatabaseConnection;
import com.librarymanager.Validator;
import com.librarymanager.model.Checkout;

public class CheckoutDAO {

    public Checkout insert(Checkout ch) throws SQLException {
        validateCheckout(ch);
        prepareDefaults(ch);

        String sql = "INSERT INTO checkout(client_id, book_id, date_taken, deadline, date_returned) " +
                "VALUES (?, ?, ?, ?, ?) RETURNING checkout_id";

        try (Connection c = DatabaseConnection.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)) {

            fillStatement(ps, ch);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ch.setCheckoutId(rs.getInt(1));
                }
            }
        }

        return ch;
    }

    private void validateCheckout(Checkout ch) throws SQLException {
        Validator.validateId(ch.getClientId(), "Client ID");
        Validator.validateId(ch.getBookId(), "Book ID");

        if (!isBookAvailable(ch.getBookId())) {
            throw new IllegalStateException("The book is already checked out to another client!");
        }

        if (countActiveCheckouts(ch.getClientId()) >= 5) {
            throw new IllegalStateException("The client can't have more than 5 books simultaneously.");
        }
    }

    private void prepareDefaults(Checkout ch) {
        if (ch.getDateTaken() == null) {
            ch.setDateTaken(LocalDate.now());
        }

        if (ch.getDeadline() == null) {
            ch.setDeadline(ch.getDateTaken().plusDays(30));
        }
    }

    private void fillStatement(PreparedStatement ps, Checkout ch) throws SQLException {
        ps.setInt(1, ch.getClientId());
        ps.setInt(2, ch.getBookId());
        ps.setDate(3, Date.valueOf(ch.getDateTaken()));
        ps.setDate(4, Date.valueOf(ch.getDeadline()));

        if (ch.getDateReturned() != null) {
            ps.setDate(5, Date.valueOf(ch.getDateReturned()));
        } else {
            ps.setNull(5, Types.DATE);
        }
    }

    public void markReturned(int checkoutId, LocalDate returnedDate) throws SQLException {
        Validator.validateId(checkoutId, "Checkout ID");
        if (returnedDate == null)
            returnedDate = LocalDate.now();

        String sql = "UPDATE checkout SET date_returned = ? WHERE checkout_id = ?";

        try (Connection c = DatabaseConnection.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setDate(1, Date.valueOf(returnedDate));
            ps.setInt(2, checkoutId);
            ps.executeUpdate();
        }
    }

    public boolean isBookAvailable(int bookId) throws SQLException {
        Validator.validateId(bookId, "Book ID");

        String sql = "SELECT COUNT(*) FROM checkout WHERE book_id = ? AND date_returned IS NULL";

        try (Connection c = DatabaseConnection.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, bookId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) == 0;
                }
            }
        }
        return false;
    }

    public int countActiveCheckouts(int clientId) throws SQLException {
        Validator.validateId(clientId, "Client ID");

        String sql = "SELECT COUNT(*) FROM checkout WHERE client_id = ? AND date_returned IS NULL";

        try (Connection c = DatabaseConnection.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, clientId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next())
                    return rs.getInt(1);
            }
        }
        return 0;
    }

    public List<Checkout> findActiveByClient(int clientId) throws SQLException {
        Validator.validateId(clientId, "Client ID");

        List<Checkout> list = new ArrayList<>();

        String sql = "SELECT * FROM checkout WHERE client_id = ? AND date_returned IS NULL";

        try (Connection c = DatabaseConnection.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, clientId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(map(rs));
                }
            }
        }

        return list;
    }

    public List<Checkout> findOverdue(LocalDate today) throws SQLException {
        if (today == null)
            today = LocalDate.now();

        List<Checkout> list = new ArrayList<>();

        String sql = "SELECT * FROM checkout WHERE date_returned IS NULL AND deadline < ?";

        try (Connection c = DatabaseConnection.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setDate(1, Date.valueOf(today));
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(map(rs));
                }
            }
        }

        return list;
    }

    private Checkout map(ResultSet rs) throws SQLException {
        return new Checkout(
                rs.getInt("checkout_id"),
                rs.getInt("client_id"),
                rs.getInt("book_id"),
                rs.getDate("date_taken").toLocalDate(),
                rs.getDate("deadline").toLocalDate(),
                rs.getDate("date_returned") != null ? rs.getDate("date_returned").toLocalDate() : null);
    }
}
