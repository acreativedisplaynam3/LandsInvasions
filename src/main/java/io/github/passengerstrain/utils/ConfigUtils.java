package io.github.passengerstrain.utils;

import io.github.passengerstrain.LandsInvasion;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

@SuppressWarnings("unused")
public class ConfigUtils {

    private FileConfiguration languageConfiguration;

    private final LandsInvasion plugin;

    public ConfigUtils(LandsInvasion plugin) {
        this.plugin = plugin;
    }

    public FileConfiguration getLanguageConfiguration() {
        return this.languageConfiguration;
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
}
