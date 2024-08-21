package me.nashplugz.coinc;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class CoinCraze extends JavaPlugin {

    private TycoonManager tycoonManager;
    private PlayerManager playerManager;
    private Economy economy;

    @Override
    public void onEnable() {
        if (!setupEconomy()) {
            getLogger().severe("Vault not found! Disabling plugin.");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        // Initialize managers
        tycoonManager = new TycoonManager(this);
        playerManager = new PlayerManager(this);

        // Register commands
        getCommand("coincraze").setExecutor(new CoinCrazeCommand(this));

        // Register event listeners
        getServer().getPluginManager().registerEvents(new TycoonListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);

        // Load tycoon areas and player data
        tycoonManager.loadTycoonAreas();
        playerManager.loadPlayerData();

        getLogger().info("CoinCraze has been enabled!");
    }

    @Override
    public void onDisable() {
        // Save tycoon areas and player data
        tycoonManager.saveTycoonAreas();
        playerManager.savePlayerData();

        getLogger().info("CoinCraze has been disabled!");
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        economy = rsp.getProvider();
        return economy != null;
    }

    public TycoonManager getTycoonManager() {
        return tycoonManager;
    }

    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    public Economy getEconomy() {
        return economy;
    }
}
