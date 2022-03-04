package me.devwhitefox.cartonbox;

import me.devwhitefox.cartonbox.commands.utils.CoreCommand;
import me.devwhitefox.cartonbox.console.SplashConsole;
import me.devwhitefox.cartonbox.item.CardboardBoxItem;
import me.devwhitefox.cartonbox.listener.BoxTheBlock;
import me.devwhitefox.cartonbox.item.ScotchItem;
import me.devwhitefox.cartonbox.listener.UnboxCardboard;
import me.devwhitefox.cartonbox.utils.ConfigManager;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

/**
 * Here is the core of the plugin
 */
public final class Cartonbox extends JavaPlugin {
    /**
     * The constant instance of plugin.
     */
    private static Cartonbox instance;

    /**
    Config options
     */
    private static ConfigManager options;


    /**
     * Instantiates the plugin instance and print in console welcome message.
     */
    public Cartonbox() {
        Cartonbox.instance = this;
        options = new ConfigManager("options.yml");
        options.saveDefaultConfig();
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        super.onEnable();
        saveDefaultConfig();
        SplashConsole.sendSplashLines("plugin.onEnable"); //Find "onEnable" message to print in console

        Objects.requireNonNull(this.getCommand("cartonbox")).setExecutor(new CoreCommand());

        registerEvents(new BoxTheBlock());
        registerEvents(new UnboxCardboard());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        super.onDisable();
        SplashConsole.sendSplashLines("plugin.onDisable"); //Find "onDisable" message to print in console
    }

    /**
     * Shortcut to be able to record listeners of events starting from spigot
     *
     * @param listener the listener
     */
    public void registerEvents(Listener listener){
        getServer().getPluginManager().registerEvents(listener, this);
    }
    
    public static Cartonbox getInstance() {
        return instance;
    }

    public static ConfigManager getOptions() {
        return options;
    }
}
