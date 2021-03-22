package de.tomjuri.codemc.coinsystem.commands.utils;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class CommandHandler implements CommandExecutor {

    private static final HashMap<String, CommandInterface> commands = new HashMap<String, CommandInterface>();

    public void register(String name, CommandInterface cmd) {
        commands.put(name, cmd);

    }

    public boolean exists(String name) {
        return commands.containsKey(name);

    }

    public CommandInterface getExecutor(String name) {
        return commands.get(name);

    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            if (args.length == 0) {
                getExecutor("coins").onCommand(sender, cmd, label, args);

                return true;

            } else if (args.length > 0) {
                if (exists(args[0])) {
                    getExecutor(args[0]).onCommand(sender, cmd, label, args);
                    return true;
                } else {
                    sender.sendMessage("§c§lCodeMC §8» §cError! Bitte benutze §b/coins§c!");
                    return true;
                }
            }
        } else {
            sender.sendMessage("§c§lCodeMC §8» §cError! Du musst ein Spieler sein, um das zu tun!");
            return true;
        }
        return false;
    }

}