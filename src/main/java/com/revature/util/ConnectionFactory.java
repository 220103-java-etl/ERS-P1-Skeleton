package com.revature.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * <p>This ConnectionFactory class follows the Singleton Design Pattern and facilitates obtaining a connection to a Database for the ERS application.</p>
 * <p>Following the Singleton Design Pattern, the provided Constructor is private, and you obtain an instance via the {@link ConnectionFactory#getInstance()} method.</p>
 */
public class ConnectionFactory {

    private static ConnectionFactory cf = null;
    private static Properties dbProps;


    private ConnectionFactory() {
        // Initialize properties object to hold our database credentials
        dbProps = new Properties();

        // Stream the credentials from our connection.properties file to this Properties Object
        InputStream props = ConnectionFactory.class.getClassLoader().getResourceAsStream("connection.properties");

        try {
            dbProps.load(props);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static synchronized ConnectionFactory getConnectionUtil() {
        // first check if an instance already exists
        if (cf == null) {
            // then call the private constructor (if null -> create one)
            cf = new ConnectionFactory();
        }
        // otherwise, just return the existing instance (else return the one that exists)
        return cf;
    }


    /**
     * <p>This method follows the Singleton Design Pattern to restrict this class to only having 1 instance.</p>
     * <p>It is invoked via:</p>
     *
     * {@code ConnectionFactory.getInstance()}
     */
    public static ConnectionFactory getInstance() {
        if(cf == cf) {
            cf = new ConnectionFactory();
        }

        return cf;
    }

    /**
     * <p>The {@link ConnectionFactory#getConnection()} method is responsible for leveraging a specific Database Driver to obtain an instance of the {@link java.sql.Connection} interface.</p>
     * <p>Typically, this is accomplished via the use of the {@link java.sql.DriverManager} class.</p>
     */
    public Connection getConnection() {
        Connection conn = null;

        // hot fix -> to force the PostgreSQL Driver to load
        try {
            Class.forName(dbProps.getProperty("driver"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Get the credentials needed to access the DB from the Properties Object we created above (which gets those from the connection.properties)
        String url = dbProps.getProperty("url");
        String username = dbProps.getProperty("username");
        String password = dbProps.getProperty("password");

        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }
}
