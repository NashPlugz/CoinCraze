package me.nashplugz.coinc;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TycoonManager {

    private final CoinCraze plugin;
    private final Map<String, TycoonArea> tycoonAreas;
    private final File tycoonFile;
    private FileConfiguration tycoonConfig;

    public TycoonManager(CoinCraze plugin) {
        this.plugin = plugin;
        this.tycoonAreas = new HashMap<>();
        this.tycoonFile = new File(plugin.getDataFolder(), "tycoons.yml");
        this.tycoonConfig = YamlConfiguration.loadConfiguration(tycoonFile);
    }

    public void createTycoonArea(String name, Location corner1, Location corner2, Location entrance) {
        TycoonArea area = new TycoonArea(name, corner1, corner2, entrance);
        tycoonAreas.put(name, area);
    }

    public TycoonArea getTycoonArea(String name) {
        return tycoonAreas.get(name);
    }

    public void claimTycoonArea(String name, UUID playerUUID) {
        TycoonArea area = tycoonAreas.get(name);
        if (area != null) {
            area.setOwner(playerUUID);
        }
    }

    public void unclaimTycoonArea(String name) {
        TycoonArea area = tycoonAreas.get(name);
        if (area != null) {
            area.setOwner(null);
            area.reset();
        }
    }

    public void loadTycoonAreas() {
        for (String key : tycoonConfig.getKeys(false)) {
            String name = tycoonConfig.getString(key + ".name");
            Location corner1 = (Location) tycoonConfig.get(key + ".corner1");
            Location corner2 = (Location) tycoonConfig.get(key + ".corner2");
            Location entrance = (Location) tycoonConfig.get(key + ".entrance");
            createTycoonArea(name, corner1, corner2, entrance);
        }
    }

    public void saveTycoonAreas() {
        for (Map.Entry<String, TycoonArea> entry : tycoonAreas.entrySet()) {
            String key = entry.getKey();
            TycoonArea area = entry.getValue();
            tycoonConfig.set(key + ".name", area.getName());
            tycoonConfig.set(key + ".corner1", area.getCorner1());
            tycoonConfig.set(key + ".corner2", area.getCorner2());
            tycoonConfig.set(key + ".entrance", area.getEntrance());
        }
        try {
            tycoonConfig.save(tycoonFile);
        } catch (IOException e) {
            plugin.getLogger().severe("Could not save tycoon areas: " + e.getMessage());
        }
    }
}
