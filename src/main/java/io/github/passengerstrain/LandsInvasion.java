package io.github.passengerstrain;

import io.github.passengerstrain.utils.ConfigUtils;
import io.github.passengerstrain.utils.LogUtils;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("unused")
public final class LandsInvasion extends JavaPlugin {

    private ConfigUtils configUtils;

    @Override
    public void onEnable() {
        LogUtils.info("Enabling LandsInvasion.");
        LogUtils.info("Creating configuration files...");
        registerConfigFiles();
        LogUtils.info("Registering listeners...");
        registerListeners();
        LogUtils.info("Registering commands...");
        registerCommands();
        LogUtils.info("Successfully enabled the plugin.");
    }

    @Override
    public void onDisable() {
        LogUtils.info("Disabling LandsInvasion.");
    }

    private void registerListeners() {

    }

    private void registerCommands() {

    }

    private void registerConfigFiles() {
        configUtils = new ConfigUtils(this);
        configUtils.createLanguageConfiguration();
    }
}
