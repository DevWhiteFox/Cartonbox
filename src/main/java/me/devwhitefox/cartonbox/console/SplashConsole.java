package me.devwhitefox.cartonbox.console;

import me.devwhitefox.cartonbox.Cartonbox;
import org.bukkit.Bukkit;

import java.util.List;

public class SplashConsole {
    private static final Cartonbox plugin = Cartonbox.getInstance();

    public static void sendSplashLines(String ymlPath){
        List<String> messageLines = plugin.getConfig().getStringList(ymlPath);
        messageLines.forEach(line ->
                {
                    if (line != null) {
                        Bukkit.getConsoleSender().sendMessage(line);
                    }
                }
        );
    }
}
