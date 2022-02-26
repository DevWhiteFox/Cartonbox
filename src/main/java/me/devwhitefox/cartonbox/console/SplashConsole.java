package me.devwhitefox.cartonbox.console;

import me.devwhitefox.cartonbox.utils.ConfigManager;
import org.bukkit.Bukkit;

import java.util.List;

public class SplashConsole {
    private static ConfigManager splashConfig;

    public static void initialize() {
        splashConfig = new ConfigManager("splashMessage.yml");
        splashConfig.saveDefaultConfig();
        splashConfig.reloadConfig();
    }

    public static void reloadConfig(){
        splashConfig.reloadConfig();
    }

    public static void sendSplashLines(String ymlPath){
        List<String> messageLines = splashConfig.getConfig().getStringList(ymlPath);
        messageLines.forEach(line ->
                {
                    if (line != null) {
                        Bukkit.getConsoleSender().sendMessage(line);
                    }
                }
        );
    }
}
