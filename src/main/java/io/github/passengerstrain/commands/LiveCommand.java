package io.github.passengerstrain.commands;

import io.github.passengerstrain.utils.ConfigUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public class LiveCommand implements CommandExecutor {

    private final ConfigUtils configUtils;

    public LiveCommand(ConfigUtils configUtils) {
        this.configUtils = configUtils;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, String[] args) {
        if (!((commandSender) instanceof Player)) {
            String onlyPlayersCanExecuteThisCommand = configUtils.getLanguageConfiguration().getString("messages.only-players-can-execute-this-command");
            commandSender.sendMessage(onlyPlayersCanExecuteThisCommand != null ? onlyPlayersCanExecuteThisCommand : "Only player's can execute this command.");
            return true;
        }

        Player player = (Player) commandSender;

        if(args.length == 0) {
            String notEnoughArgs = configUtils.getLanguageConfiguration().getString("messages.not-enough-command-args");
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', notEnoughArgs != null ? notEnoughArgs : "&cYou have provided insufficient args for this command."));
            return true;
        }

        String applicationType = args[0].toLowerCase();

        switch (applicationType) {
            case "youtube":
                handleYoutubeBroadcast(player, args);
                break;
            case "twitch":
                handleTwichBroadcast(player, args);
                break;
            default:
                String invalidApplicationTypeMessage = configUtils.getLanguageConfiguration().getString("messages.invalid-streaming-application-used");
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', invalidApplicationTypeMessage != null ? invalidApplicationTypeMessage : "&cInvalid stream platform mentioned. Please use 'twitch' or 'youtube'."));
                break;
        }
        return true;
    }

    private boolean handleYoutubeBroadcast(Player player, String[] args) {
        String youtubeChannelLink = args[1];

        if(youtubeChannelLink.isEmpty()) {
            String emptyBroadcastStreamLink = configUtils.getLanguageConfiguration().getString("messages.empty-stream-link");
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', emptyBroadcastStreamLink != null ? emptyBroadcastStreamLink : "&cPlease enter a valid stream link."));
        } else {
            for(Player onlinePlayers : Bukkit.getOnlinePlayers()) {
                String broadcastMessage = configUtils.getLanguageConfiguration().getString("messages.youtube-stream-broadcast-message").replace("%youtube_broadcast_player_name%", player.getName());
                onlinePlayers.sendMessage(ChatColor.translateAlternateColorCodes('&', broadcastMessage != null ? broadcastMessage : "&cThe server doesn't have this configured yet."));
            }
        }
        return true;
    }

    private boolean handleTwichBroadcast(Player player, String[] args) {
        String twitchChannelLink = args[1];

        if(twitchChannelLink.isEmpty()) {
            String emptyBroadcastStreamLink = configUtils.getLanguageConfiguration().getString("messages.empty-stream-link");
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', emptyBroadcastStreamLink != null ? emptyBroadcastStreamLink : "&cPlease enter a valid stream link."));
        } else {
            for(Player onlinePlayers : Bukkit.getOnlinePlayers()) {
                String broadcastMessage = configUtils.getLanguageConfiguration().getString("messages.twitch-stream-broadcast-message").replace("%twitch_broadcast_player_name%", player.getName());
                onlinePlayers.sendMessage(ChatColor.translateAlternateColorCodes('&', broadcastMessage != null ? broadcastMessage : "&cThe server doesn't have this configured yet."));
            }
        }
        return true;
    }
}


