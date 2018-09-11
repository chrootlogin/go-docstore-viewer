package ch.rootlogin.godocstore.viewer.database.models;

import ch.rootlogin.godocstore.viewer.models.ServerConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Configuration {
    private Connection conn;

    public Configuration(Connection connection) {
        conn = connection;
    }

    public void addServerConnection(ServerConnection serverConnection) {
        var sql = "INSERT INTO server_connections (url,username,password) VALUES (?,?,?)";

        try (var stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, serverConnection.getUrl());
            stmt.setString(2, serverConnection.getUsername());
            stmt.setString(3, serverConnection.getPassword());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public String get(String key) {
        var sql = "SELECT cvalue FROM config WHERE ckey = ?";

        try (var stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, key);

            var rs = stmt.executeQuery();
            if(rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }
}
