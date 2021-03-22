package de.tomjuri.codemc.api;

import de.tomjuri.codemc.api.sql.MySQL;
import de.tomjuri.codemc.api.sql.SQLGetter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public final class CMCAPI extends JavaPlugin {

    public String host = "localhost";
    public String port = "3306";
    public String database = "codemc";
    public String username = "root";
    public String password = "";

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
