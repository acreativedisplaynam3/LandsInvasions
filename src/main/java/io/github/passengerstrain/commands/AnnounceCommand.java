package io.github.passengerstrain.commands;

import io.github.passengerstrain.utils.ConfigUtils;
import io.github.passengerstrain.utils.ColorUtils;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public class AnnounceCommand implements CommandExecutor {

    private final ConfigUtils configUtils;

    public AnnounceCommand(ConfigUtils configUtils) {
        this.configUtils = configUtils;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, String[] args) {
        if (!(commandSender instanceof Player)) {
            String onlyPlayersCanExecuteThisCommand = configUtils.getLanguageConfiguration().getString("messages.only-players-can-execute-this-command");
            commandSender.sendMessage(onlyPlayersCanExecuteThisCommand != null ? onlyPlayersCanExecuteThisCommand : "Only players can execute this command.");
            return true;
        }

        Player player = (Player) commandSender;

        if (args.length == 0) {
            String notEnoughArgs = configUtils.getLanguageConfiguration().getString("messages.not-enough-command-args");
            player.sendMessage(ColorUtils.colorize(notEnoughArgs != null ? notEnoughArgs : "&cYou have provided insufficient args for this command."));
            return true;
        }

        String announceMessage = String.join(" ", args);

        if (player.hasPermission("landsinvasion.command.announce")) {
            if (announceMessage.isEmpty()) {
                String emptyAnnounceMessage = configUtils.getLanguageConfiguration().getString("messages.empty-announcement-message");
                player.sendMessage(ColorUtils.colorize(emptyAnnounceMessage != null ? emptyAnnounceMessage : "&cYou cannot broadcast an empty announcement message."));
            } else {
                for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                    onlinePlayer.sendTitle(ColorUtils.colorize("#18E277MINELANDS"), ColorUtils.colorize(announceMessage));
                    onlinePlayer.playSound(onlinePlayer.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 500f, 1f);

                }
            }
        } else {
            String noPermission = configUtils.getLanguageConfiguration().getString("messages.no-permission");
            player.sendMessage(ColorUtils.colorize(noPermission != null ? noPermission : "&cYou don't have sufficient permissions to execute this command."));
        }
        return true;
    }
}
