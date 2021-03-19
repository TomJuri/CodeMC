package de.tomjuri.codemc.api.sql;

import de.tomjuri.codemc.api.CMCAPI;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class SQLGetter {

    private CMCAPI plugin;

    public SQLGetter(CMCAPI plugin) {
        this.plugin = plugin;

    }

    public void createTable() {
        PreparedStatement ps;
        try {
            ps = plugin.SQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS Coins (uuid VARCHAR(100),coins DOUBLE, PRIMARY KEY (uuid))");
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createPlayer(Player player) {
        try {
            UUID uuid = player.getUniqueId();
            if (!exists(uuid)) {
                PreparedStatement ps2 = plugin.SQL.getConnection().prepareStatement("INSERT IGNORE INTO Coins " + "(uuid) VALUES (?)");
                ps2.setString(1, uuid.toString());
                ps2.executeUpdate();

                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean exists(UUID uuid) {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT * FROM Coins WHERE uuid=?");
            ps.setString(1, uuid.toString());
            ResultSet results = ps.executeQuery();
            if (results.next()) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void setCoins(UUID uuid, double coins) {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE Coins SET coins=? WHERE uuid=?");
            ps.setDouble(1, coins);
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public double getCoins(UUID uuid) {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT coins FROM Coins WHERE uuid=?");
            ps.setString(1, uuid.toString());
            ResultSet results = ps.executeQuery();
            double coins = 0;
            if (results.next()) {
                coins = results.getDouble("coins");
                return coins;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
