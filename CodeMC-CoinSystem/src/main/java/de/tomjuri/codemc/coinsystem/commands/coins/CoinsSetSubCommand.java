package de.tomjuri.codemc.coinsystem.commands.coins;

import de.tomjuri.codemc.api.CMCAPI;
import de.tomjuri.codemc.coinsystem.commands.utils.CommandInterface;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CoinsSetSubCommand implements CommandInterface {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;
        if (args.length > 3) return false;
        if (player.hasPermission("codemc.coinsystem.set")) {
            if (args.length == 3) {
                Player target = Bukkit.getPlayer(args[1]);
                try {
                    if (CMCAPI.getInstance.data.exists(target.getUniqueId()))
                        CMCAPI.getInstance.data.setCoins(target.getUniqueId(), Double.parseDouble(args[2]));
                    player.sendMessage("§c§lCodeMC §8» §eDer neue Kontostand von §b" + target.getDisplayName() + " §ebeträgt nun §b" + CMCAPI.getInstance.data.getCoins(target.getUniqueId()) + "§e.");
                    target.sendMessage("§c§lCodeMC §8» §eDein neuer Kontostand beträgt nun §b" + CMCAPI.getInstance.data.getCoins(target.getUniqueId()) + "§e.");
                } catch (Exception exception) {
                    player.sendMessage("§c§lCodeMC §8» §cError! Der Spieler §b" + args[1] + " §cist nicht Online, oder er hat keinen Account!");
                }
            } else
                player.sendMessage("§c§lCodeMC §8» §cError! Bitte benutze §b/coins§c!");
        } else
            player.sendMessage("§c§lCodeMC §8» §cError! Dazu benötigst du folgende Berechtigung: §bcodemc.coinsystem.set§c!");
        return false;
    }
}