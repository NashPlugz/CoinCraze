package me.nashplugz.coinc;

import org.bukkit.Location;

import java.util.UUID;

public class TycoonArea {

    private final String name;
    private final Location corner1;
    private final Location corner2;
    private final Location entrance;
    private UUID owner;

    public TycoonArea(String name, Location corner1, Location corner2, Location entrance) {
        this.name = name;
        this.corner1 = corner1;
        this.corner2 = corner2;
        this.entrance = entrance;
    }

    public String getName() {
        return name;
    }

    public Location getCorner1() {
        return corner1;
    }

    public Location getCorner2() {
        return corner2;
    }

    public Location getEntrance() {
        return entrance;
    }

    public UUID getOwner() {
        return owner;
    }

    public void setOwner(UUID owner) {
        this.owner = owner;
    }

    public void reset() {
        // Implement logic to reset the tycoon area to its original state
    }
}
