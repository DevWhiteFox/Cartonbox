package me.devwhitefox.cartonbox.utils;

import me.devwhitefox.cartonbox.Cartonbox;
import me.devwhitefox.cartonbox.console.MessageConsole;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ConfigManager {
    private static final Cartonbox plugin = Cartonbox.instance;
    private FileConfiguration fileConfiguration = null;
    private File configFile = null;
    private final String nameFile;

    public ConfigManager(String nameFile){
        this.nameFile = nameFile;
    }

    public void reloadConfig(){
        if(this.configFile == null){
            this.configFile = new File(plugin.getDataFolder(), nameFile);
        }

        this.fileConfiguration = YamlConfiguration.loadConfiguration(this.configFile);

        InputStream defaultStream = plugin.getResource(nameFile);
        if(defaultStream != null){
            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
            this.fileConfiguration.setDefaults(defaultConfig);
        }
    }

    public FileConfiguration getConfig(){
        if(fileConfiguration == null){
            reloadConfig();
        }
        return this.fileConfiguration;
    }

    public void saveConfig(){
        if(this.fileConfiguration == null || this.configFile == null)
            return;

        try {
            this.fileConfiguration.save(this.configFile);
        } catch (IOException e) {
            new MessageConsole().sendSevere("Could not save config to " + this.configFile, e);
        }
    }

    public void saveDefaultConfig(){
        if(this.configFile == null){
            this.configFile = new File(plugin.getDataFolder(), nameFile);
        }

        if (!this.configFile.exists()){
            plugin.saveResource(nameFile, false);
        }
    }
}
