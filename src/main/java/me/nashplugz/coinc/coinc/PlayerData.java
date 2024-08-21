package me.nashplugz.coinc;

import java.util.UUID;

public class PlayerData {

    private final UUID playerUUID;
    private int leaderboardPosition;

    public PlayerData(UUID playerUUID) {
        this(playerUUID, 0);
    }

    public PlayerData(UUID playerUUID, int leaderboardPosition) {
        this.playerUUID = playerUUID;
        this.leaderboardPosition = leaderboardPosition;
    }

    public UUID getPlayerUUID() {
        return playerUUID;
    }

    public int getLeaderboardPosition() {
        return leaderboardPosition;
    }

    public void setLeaderboardPosition(int leaderboardPosition) {
        this.leaderboardPosition = leaderboardPosition;
    }
}
