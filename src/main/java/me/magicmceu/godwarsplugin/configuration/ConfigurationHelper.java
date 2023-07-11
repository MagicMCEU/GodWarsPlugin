package me.magicmceu.godwarsplugin.configuration;

import de.exlll.configlib.YamlConfigurations;
import me.magicmceu.godwarsplugin.GodWars;
import me.magicmceu.godwarsplugin.configuration.configs.MainConfig;

import java.nio.file.Path;

public class ConfigurationHelper {
    public MainConfig main = new MainConfig();
    private final Path mainConfigPath = GodWars.plugin.getDataFolder().toPath().resolve("config.yml");

    public ConfigurationHelper() {
        if (!mainConfigPath.toFile().exists()) {
            save();
        } else {
            reload();
        }
    }

    public void save() {
        YamlConfigurations.save(mainConfigPath, MainConfig.class, main);
    }

    public void reload() {
        main = YamlConfigurations.load(mainConfigPath, MainConfig.class);
    }
}
