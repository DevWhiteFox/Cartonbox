package me.devwhitefox.cartonbox;

import me.devwhitefox.cartonbox.commands.utils.CoreCommand;
import me.devwhitefox.cartonbox.console.SplashConsole;
import me.devwhitefox.cartonbox.listener.BoxTheBlock;
import me.devwhitefox.cartonbox.item.ScotchItem;
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
    public static Cartonbox instance;

    /**
     * Instantiates the plugin instance and print in console welcome message.
     */
    public Cartonbox() {
        Cartonbox.instance = this;
        SplashConsole.initialize();
        ScotchItem.initialize();
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        super.onEnable();
        saveDefaultConfig();
        getConfig().options().copyDefaults(true);
        SplashConsole.sendSplashLines("onEnable"); //Find "onEnable" message to print in console

        Objects.requireNonNull(this.getCommand("cartonbox")).setExecutor(new CoreCommand());

        registerEvents(new BoxTheBlock());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        super.onDisable();
        SplashConsole.sendSplashLines("onDisable"); //Find "onDisable" message to print in console
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
