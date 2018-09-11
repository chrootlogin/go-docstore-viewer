package ch.rootlogin.godocstore.viewer.database;

import ch.rootlogin.godocstore.viewer.Helper;
import ch.rootlogin.godocstore.viewer.database.models.Configuration;

import java.sql.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {
    private final static Logger logger = Logger.getLogger(Database.class.getName());

    // Singleton
    public final static Database INSTANCE = new Database();

    private Connection conn;

    private Database() {
        connect();
        init();
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

    private void init() {
        if(!tableExists("config")) {
            logger.log(Level.INFO, "Database not prepared, preparing...");
            runSQLFile("/sql/init.sql");
        }

        logger.log(Level.INFO, String.format("Running db version: %s", Configuration().get("db.version")));
    }

    private boolean tableExists(String tableName) {
        var sql = "SELECT COUNT(*) FROM sqlite_master WHERE type = 'table' AND name = ?";

        var count = 0;
        try{
            var stmt = conn.prepareStatement(sql);
            stmt.setString(1, tableName);

            var rs = stmt.executeQuery();
            count = rs.getInt(1);
        } catch(SQLException ex) {
            logger.log(Level.SEVERE, "Error checking if table exists: ", ex);
        }

        return count == 1;
    }

    private void runSQLFile(String fileName) {
        var sqlFile = Helper.getInputStream(fileName);
        if(sqlFile == null) {
            logger.warning("Couldn't read sql file: " + fileName);
            return;
        }

        var s = new Scanner(sqlFile);
        s.useDelimiter("(;(\r)?\n)|(--\n)");

        Statement st = null;
        try
        {
            st = conn.createStatement();
            while (s.hasNext())
            {
                var line = s.next();
                if (line.startsWith("/*!") && line.endsWith("*/"))
                {
                    var i = line.indexOf(' ');
                    line = line.substring(i + 1, line.length() - " */".length());
                }

                if (line.trim().length() > 0)
                {
                    st.execute(line);
                }
            }
        } catch(SQLException e) {
            logger.warning("Error executing SQL Statement: " + e.getMessage());
        } finally {
            try {
                if(st != null) {
                    st.close();
                }
            } catch(SQLException e) {
                logger.warning("Couldn't close SQL Statement: " + e.getMessage());
            }
        }
    }
}
