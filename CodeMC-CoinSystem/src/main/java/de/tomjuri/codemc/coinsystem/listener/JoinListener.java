package de.tomjuri.codemc.coinsystem.listener;

import de.tomjuri.codemc.api.CMCAPI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        if (CMCAPI.getInstance.SQL.isConnected()) {
            CMCAPI.getInstance.data.createPlayer(event.getPlayer());

        }
    }
}
