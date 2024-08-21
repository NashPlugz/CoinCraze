package me.nashplugz.coinc;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class TycoonListener implements Listener {
    private final CoinCraze plugin;

    public TycoonListener(CoinCraze plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        // Implement logic for placing dropper blocks
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        // Implement logic for entering/exiting tycoon areas
    }
}
