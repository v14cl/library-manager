package com.librarymanager.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.librarymanager.DatabaseConnection;
import com.librarymanager.Validator;
import com.librarymanager.model.Client;

public class ClientDAO implements CrudDAO<Client> {

    public Client insert(Client client) throws SQLException {
        Validator.validateNotEmpty(client.getFullName(), "Client name");
        Validator.validatePhone(client.getPhoneNumber());

        String sql = "INSERT INTO client(full_name, phone_number) VALUES (?, ?) RETURNING client_id";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, client.getFullName());
            ps.setString(2, client.getPhoneNumber());

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) client.setClientId(rs.getInt(1));
            }
        }
        return client;
    }

    public void update(Client client) throws SQLException {
        Validator.validateId(client.getClientId(), "Client ID");
        Validator.validateNotEmpty(client.getFullName(), "Client name");
        Validator.validatePhone(client.getPhoneNumber());

        String sql = "UPDATE client SET full_name = ?, phone_number = ? WHERE client_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, client.getFullName());
            ps.setString(2, client.getPhoneNumber());
            ps.setInt(3, client.getClientId());
            ps.executeUpdate();
        }
    }

    public void delete(int clientId) throws SQLException {
        Validator.validateId(clientId, "Client ID");

        String sql = "DELETE FROM client WHERE client_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, clientId);
            ps.executeUpdate();
        }
    }

    public Client findById(int clientId) throws SQLException {
        Validator.validateId(clientId, "Client ID");

        String sql = "SELECT client_id, full_name, phone_number FROM client WHERE client_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, clientId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Client(
                            rs.getInt("client_id"),
                            rs.getString("full_name"),
                            rs.getString("phone_number")
                    );
                }
            }
        }
        return null;
    }

    public List<Client> findAll() throws SQLException {
        List<Client> clients = new ArrayList<>();

        String sql = "SELECT client_id, full_name, phone_number FROM client";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                clients.add(new Client(
                        rs.getInt("client_id"),
                        rs.getString("full_name"),
                        rs.getString("phone_number")
                ));
            }
        }
        return clients;
    }
}
