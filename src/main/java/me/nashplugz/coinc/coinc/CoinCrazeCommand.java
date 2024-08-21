package me.nashplugz.coinc;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CoinCrazeCommand implements CommandExecutor {
    private final CoinCraze plugin;

    public CoinCrazeCommand(CoinCraze plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be used by players.");
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            player.sendMessage("Usage: /coincraze [create|claim|unclaim]");
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "create":
                // Implement tycoon creation logic
                break;
            case "claim":
                // Implement tycoon claiming logic
                break;
            case "unclaim":
                // Implement tycoon unclaiming logic
                break;
            default:
                player.sendMessage("Unknown subcommand. Use /coincraze for help.");
        }

        return true;
    }
}
