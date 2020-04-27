package ua.nure.biletska.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

public class DBManager {

    private static final Logger log = Logger.getLogger(DBManager.class);
    private static DBManager instance;
    
    /* Singleton */
    private DBManager() {
    }

    public static synchronized DBManager getInstance() {
        if (instance == null)
            instance = new DBManager();
        return instance;
    }

    public Connection getConnection() throws SQLException {
        Connection connection = null;
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");

            // st4db - the name of data source
            DataSource ds = (DataSource) envContext.lookup("jdbc/st4db");
            connection = ds.getConnection();
        } catch (NamingException ex) {
            ex.printStackTrace();
            log.error("Cannot obtain a connection from the pool", ex);
        }
        return connection;
    }

    /**
     * Commits and close the given connection.
     *
     * @param connection
     *            Connection to be committed and closed.
     */
    public void commitAndClose(Connection connection) {
        try {
            connection.commit();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Rollback and close the given connection.
     *
     * @param connection
     *            Connection to be rollbacked and closed.
     */
    public void rollbackAndClose(Connection connection) {
        try {
            connection.rollback();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Connection getConnectionWithDriverManager() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/st4dbdatabase?useUnicode=true"
                        + "&useJDBCCompliantTimezoneShift=true" + "&useLegacyDatetimeCode=false&serverTimezone=UTC", "root",
                "root");
        connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        connection.setAutoCommit(false);
        return connection;
    }
}
