package io.github.passengerstrain;

import io.github.passengerstrain.commands.*;
import io.github.passengerstrain.utils.ConfigUtils;
import io.github.passengerstrain.utils.LogUtils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("unused")
public final class LandsInvasion extends JavaPlugin {

    private ConfigUtils configUtils;

    @Override
    public void onEnable() {
        configUtils = new ConfigUtils(this);
        LogUtils.info("Creating configuration files...");
        registerConfigFiles();
        LogUtils.info("Registering commands...");
        registerCommands();
    }

    @Override
    public void onDisable() {
    }

    private void registerCommands() {
        getCommand("discord").setExecutor(new DiscordCommand(configUtils));
        getCommand("announce").setExecutor(new AnnounceCommand(configUtils));
        getCommand("store").setExecutor(new StoreCommand(configUtils));
        getCommand("live").setExecutor(new LiveCommand(configUtils));
    }

    private void registerConfigFiles() {
        configUtils.createLanguageConfiguration();
    }
}
