package ch.rootlogin.godocstore.viewer.database;

import ch.rootlogin.godocstore.viewer.GUI;
import ch.rootlogin.godocstore.viewer.models.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {
    private final static Logger logger = Logger.getLogger(Database.class.getName());

    // Singleton
    public final static Database INSTANCE = new Database();

    private Connection conn;

    private Database() {
        connect();
    }

    public Connection Connection() {
        return conn;
    }

    public Configuration Configuration() {
        return new Configuration(conn);
    }

    public void closeConnection() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            logger.log(Level.WARNING, "Couldn't close SQLite connection:", ex);
        }
    }

    private void connect() {
        try {
            // db parameters
            String url = "jdbc:sqlite:data.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            logger.log(Level.INFO, "Connection to SQLite has been established.");
        } catch (SQLException ex) {
            logger.log(Level.WARNING, "Couldn't connect to SQLite DB:", ex);
            closeConnection();

            System.exit(255);
        }
    }
}
