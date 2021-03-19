package de.tomjuri.codemc.api;

import de.tomjuri.codemc.api.sql.MySQL;
import de.tomjuri.codemc.api.sql.SQLGetter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public final class CMCAPI extends JavaPlugin {

    public String host = "host";
    public String port = "port";
    public String database = "database";
    public String username = "username";
    public String password = "password";

    public static CMCAPI getInstance;
    public MySQL SQL;
    public SQLGetter data;

    @Override
    public void onEnable() {
        getInstance = this;
        SQL = new MySQL();
        data = new SQLGetter(this);
        connectToDatabase();

    }

    @Override
    public void onDisable() {

    }

    public void connectToDatabase() {
        try {
            SQL.connect();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            Bukkit.getServer().getConsoleSender().sendMessage("§cDatenbank nicht gefunden!");
        }
        if (SQL.isConnected()) {
            Bukkit.getServer().getConsoleSender().sendMessage("§aDatenbank gefunden und verbunden!");
            data.createTable();

        }
    }
}
