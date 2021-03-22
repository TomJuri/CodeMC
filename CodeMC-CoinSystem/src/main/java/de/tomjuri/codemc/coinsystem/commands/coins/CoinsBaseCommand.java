package de.tomjuri.codemc.coinsystem.commands.coins;

import de.tomjuri.codemc.coinsystem.commands.utils.CommandInterface;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CoinsBaseCommand implements CommandInterface {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;
        if (player.hasPermission("codemc.coinsystem.use")) {
            player.sendMessage("§c§lCodeMC §8» &eDu kannst folgende Commands benutzen: &b/coins <add/get/set/remove> <spieler> <menge>&e.");

        } else
            player.sendMessage("§c§lCodeMC §8» §cError! Dazu benötigst du folgende Berechtigung: §bcodemc.coinsystem.use§c!");
        return false;
    }
}
