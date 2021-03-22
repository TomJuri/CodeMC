package de.tomjuri.codemc.api.sql;

import de.tomjuri.codemc.api.CMCAPI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL {

    private Connection connection;

    public boolean isConnected() {
        return (connection != null);

    }

    public void connect() throws ClassNotFoundException, SQLException {
        if (!isConnected()) {
            connection = DriverManager.getConnection("jdbc:mysql://" + CMCAPI.getInstance.host + ":" + CMCAPI.getInstance.port + "/" + CMCAPI.getInstance.database + "?useSSL=true", CMCAPI.getInstance.username, CMCAPI.getInstance.password);

        }

    }

    public void disconnect() {
        if (isConnected()) {
            try {
                connection.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }

    public Connection getConnection() {
        return connection;
    }
}
