package me.nashplugz.coinc;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerManager {

    private final CoinCraze plugin;
    private final Economy economy;
    private final Map<UUID, PlayerData> playerData;
    private final File playerDataFile;
    private FileConfiguration playerDataConfig;

    public PlayerManager(CoinCraze plugin) {
        this.plugin = plugin;
        this.economy = plugin.getEconomy();
        this.playerData = new HashMap<>();
        this.playerDataFile = new File(plugin.getDataFolder(), "playerdata.yml");
        this.playerDataConfig = YamlConfiguration.loadConfiguration(playerDataFile);
    }

    public PlayerData getPlayerData(UUID playerUUID) {
        return playerData.computeIfAbsent(playerUUID, uuid -> new PlayerData(uuid));
    }

    public double getBalance(UUID playerUUID) {
        OfflinePlayer player = Bukkit.getOfflinePlayer(playerUUID);
        return economy.getBalance(player);
    }

    public void addMoney(UUID playerUUID, double amount) {
        OfflinePlayer player = Bukkit.getOfflinePlayer(playerUUID);
        economy.depositPlayer(player, amount);
    }

    public void removeMoney(UUID playerUUID, double amount) {
        OfflinePlayer player = Bukkit.getOfflinePlayer(playerUUID);
        economy.withdrawPlayer(player, amount);
    }

    public void loadPlayerData(UUID playerUUID) {
        String key = playerUUID.toString();
        if (playerDataConfig.contains(key)) {
            int leaderboardPosition = playerDataConfig.getInt(key + ".leaderboardPosition");
            PlayerData data = new PlayerData(playerUUID, leaderboardPosition);
            playerData.put(playerUUID, data);
        } else {
            playerData.put(playerUUID, new PlayerData(playerUUID));
        }
    }

    public void savePlayerData(UUID playerUUID) {
        String key = playerUUID.toString();
        PlayerData data = playerData.get(playerUUID);
        if (data != null) {
            playerDataConfig.set(key + ".leaderboardPosition", data.getLeaderboardPosition());
            try {
                playerDataConfig.save(playerDataFile);
            } catch (IOException e) {
                plugin.getLogger().severe("Could not save player data for " + playerUUID + ": " + e.getMessage());
            }
        }
    }

    public void loadPlayerData() {
        for (String key : playerDataConfig.getKeys(false)) {
            UUID playerUUID = UUID.fromString(key);
            loadPlayerData(playerUUID);
        }
    }

    public void savePlayerData() {
        for (Map.Entry<UUID, PlayerData> entry : playerData.entrySet()) {
            savePlayerData(entry.getKey());
        }
    }
}
