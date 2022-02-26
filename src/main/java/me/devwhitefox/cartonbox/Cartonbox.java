package me.devwhitefox.cartonbox;

import me.devwhitefox.cartonbox.console.SplashConsole;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Here is the core of the plugin
 */
public final class Cartonbox extends JavaPlugin {
    /**
     * The constant instance of plugin.
     */
    public static Cartonbox instance;

    /**
     * Instantiates the plugin instance and print in console welcome message.
     */
    public Cartonbox() {
        Cartonbox.instance = this;
        SplashConsole.initialize();
    }

    @Override
    public void onEnable() {
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    /**
     * Shortcut to be able to record listeners of events starting from spigot
     *
     * @param listener the listener
     */
    public void registerEvents(Listener listener){
        getServer().getPluginManager().registerEvents(listener, this);
    }
}
