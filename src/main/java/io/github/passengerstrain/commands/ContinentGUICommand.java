package io.github.passengerstrain.commands;

import io.github.passengerstrain.gui.ContinentGUI;
import io.github.passengerstrain.utils.ConfigUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public class ContinentGUICommand implements CommandExecutor {

    private final ConfigUtils configUtils;

    private final ContinentGUI continentGUI;

    public ContinentGUICommand(ConfigUtils configUtils, ContinentGUI continentGUI) {
        this.configUtils = configUtils;
        this.continentGUI = continentGUI;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(!((commandSender) instanceof Player)) {
            String onlyPlayersCanExecuteThisCommand = configUtils.getLanguageConfiguration().getString("messages.only-players-can-execute-this-command");
            commandSender.sendMessage(onlyPlayersCanExecuteThisCommand != null ? onlyPlayersCanExecuteThisCommand : "Only player's can execute this command.");
            return true;
        }

        Player player = (Player) commandSender;

        if(player.hasPermission("landsinvasion.command.continentgui")) {
            continentGUI.openInventory(player);
        } else {
            String noPermission = configUtils.getLanguageConfiguration().getString("messages.no-permission");
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', noPermission != null ? noPermission : "&cYou don't have sufficient permissions to execute this command."));
        }
        return true;
    }
}
