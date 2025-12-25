package io.github.passengerstrain.utils;

import io.github.passengerstrain.LandsInvasion;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

@SuppressWarnings("unused")
public class ConfigUtils {

    private FileConfiguration languageConfiguration;

    private FileConfiguration guiConfiguration;

    private FileConfiguration data;

    private final LandsInvasion plugin;

    public ConfigUtils(LandsInvasion plugin) {
        this.plugin = plugin;
    }

    public FileConfiguration getLanguageConfiguration() {
        return this.languageConfiguration;
    }

    public FileConfiguration getGuiConfiguration() {
        return this.guiConfiguration;
    }

    public FileConfiguration getData() {
        return this.data;
    }

    public void createLanguageConfiguration() {
        File languageConfigFile = new File(plugin.getDataFolder(), "language.yml");
        if (!languageConfigFile.exists()) {
            languageConfigFile.getParentFile().mkdirs();
            plugin.saveResource("language.yml", false);
        }

        languageConfiguration = new YamlConfiguration();
        try {
            languageConfiguration.load(languageConfigFile);
        } catch (IOException | InvalidConfigurationException e) {
            LogUtils.severe("The server was unable to load configuration file, please read through the logs for more information." + e.getMessage());
        }
    }

    public void createGuiConfiguration() {
        File guiConfigFile = new File(plugin.getDataFolder(), "gui.yml");
        if (!guiConfigFile.exists()) {
            guiConfigFile.getParentFile().mkdirs();
            plugin.saveResource("gui.yml", false);
        }

        guiConfiguration = new YamlConfiguration();
        try {
            guiConfiguration.load(guiConfigFile);
        } catch (IOException | InvalidConfigurationException e) {
            LogUtils.severe("The server was unable to load configuration file, please read through the logs for more information." + e.getMessage());
        }
    }

    public void createDataFile() {
        File dataFile = new File(plugin.getDataFolder(), "data.yml");
        if (!dataFile.exists()) {
            dataFile.getParentFile().mkdirs();
            plugin.saveResource("data.yml", false);
        }

        data = new YamlConfiguration();
        try {
            data.load(dataFile);
        } catch (IOException | InvalidConfigurationException e) {
            LogUtils.severe("The server was unable to load configuration file, please read through the logs for more information." + e.getMessage());
        }
    }

    public void set(Player player, int index) {
        String homeLocation = player.getLocation().getX() + "," + player.getLocation().getY() + "," + player.getLocation().getZ();
        save(player.getName(), homeLocation, index);
        LogUtils.info("Successfully saved playerHomeData.");
    }

    private void save(String playerName, String homeLocation, int index) {
        data.set("players." + playerName + ".playerHouseData.home" + (index + 1), homeLocation);
        try {
            getData().save("data.yml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
