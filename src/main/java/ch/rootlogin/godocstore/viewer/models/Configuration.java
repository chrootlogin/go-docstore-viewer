package ch.rootlogin.godocstore.viewer.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Configuration {
    private Connection conn;

    public Configuration(Connection connection) {
        conn = connection;
    }

    public void addServerConnection(ServerConnection serverConnection) {
        String sql = "INSERT INTO server_connections (url,username,password) VALUES (?,?,?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, serverConnection.getUrl());
            pstmt.setString(2, serverConnection.getUsername());
            pstmt.setString(2, serverConnection.getPassword());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
