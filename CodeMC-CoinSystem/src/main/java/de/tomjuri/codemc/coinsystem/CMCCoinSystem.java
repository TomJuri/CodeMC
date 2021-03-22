package de.tomjuri.codemc.coinsystem;

import de.tomjuri.codemc.coinsystem.commands.coins.*;
import de.tomjuri.codemc.coinsystem.commands.utils.CommandHandler;
import de.tomjuri.codemc.coinsystem.listener.JoinListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class CMCCoinSystem extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new JoinListener(), this);
        registerCommands();

    }

    @Override
    public void onDisable() {

    }

    public void registerCommands() {
        CommandHandler handler = new CommandHandler();
        handler.register("coins", new CoinsBaseCommand());
        handler.register("get", new CoinsGetSubCommand());
        handler.register("set", new CoinsSetSubCommand());
        handler.register("add", new CoinsAddSubCommand());
        handler.register("remove", new CoinsRemoveSubCommand());
        getCommand("coins").setExecutor(handler);

    }
}
