package org.geppi.main.handlers;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class BadwordsHandler {

    private final File file;
    private final FileConfiguration badwordsConfig;

    public BadwordsHandler(JavaPlugin plugin) {
        if (!plugin.getDataFolder().exists())
            plugin.getDataFolder().mkdir();
        this.file = new File(plugin.getDataFolder(), "badwords.yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        this.badwordsConfig = YamlConfiguration.loadConfiguration(file);
    }

    public List<String> getBadWords() {
        return this.badwordsConfig.getStringList("bad-words");
    }

}
