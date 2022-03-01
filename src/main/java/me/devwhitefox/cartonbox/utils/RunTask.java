package me.devwhitefox.cartonbox.utils;

import me.devwhitefox.cartonbox.Cartonbox;
import org.bukkit.scheduler.BukkitScheduler;
import org.jetbrains.annotations.NotNull;

public class RunTask <Z>{
    private static final Cartonbox plugin = Cartonbox.getInstance(); //Change with other plugin class (if case of migration)

    public @NotNull BukkitScheduler getScheduler(){
        return plugin.getServer().getScheduler();
    }

    public void runTaskLater(Runnable runnable, long delay){
        getScheduler().runTaskLater(plugin, runnable, delay);
    }

    public void runAsync(Runnable runnable){
        getScheduler().runTaskAsynchronously(plugin, runnable);
    }
}
