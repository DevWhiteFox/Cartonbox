package me.devwhitefox.cartonbox.utils;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class PersistentData {
    private final JavaPlugin plugin;
    private final PersistentDataType dataType;

    private ItemMeta meta;

    public PersistentData(JavaPlugin plugin, PersistentDataType dataType) {
        this.plugin = plugin;
        this.dataType = dataType;
    }

    public PersistentData from(ItemMeta meta){
        this.meta = meta;
        return this;
    }

    private @NotNull NamespacedKey obtainNamespacedKeyWith(@NotNull String key) {
        return new NamespacedKey(plugin, key);
    }

    private @NotNull PersistentDataContainer getContainer() {
        return meta.getPersistentDataContainer();
    }

    public <Z> void set(@NotNull String key, @NotNull Z value){
        getContainer().set(obtainNamespacedKeyWith(key), dataType, value);
    }
    public <Z> Z get(@NotNull String key){
        return (Z) getContainer().get(obtainNamespacedKeyWith(key), dataType);
    }

    public boolean has(@NotNull String key){
        return getContainer().has(obtainNamespacedKeyWith(key), dataType);
    }

    public void remove(@NotNull String key) {
        getContainer().remove(obtainNamespacedKeyWith(key));
    }
}
