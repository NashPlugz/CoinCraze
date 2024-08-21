package me.nashplugz.coinc;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {
    private final CoinCraze plugin;

    public PlayerListener(CoinCraze plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        // Load player data when they join
        plugin.getPlayerManager().loadPlayerData(event.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        // Save player data when they quit
        plugin.getPlayerManager().savePlayerData(event.getPlayer().getUniqueId());
    }
}

