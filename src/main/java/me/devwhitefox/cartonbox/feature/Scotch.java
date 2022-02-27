package me.devwhitefox.cartonbox.feature;

import me.devwhitefox.cartonbox.Cartonbox;
import me.devwhitefox.cartonbox.utils.ConfigManager;
import me.devwhitefox.cartonbox.utils.PersistentData;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.UUID;

public class Scotch {
    private static ConfigManager ScotchConfig;
    private static final PersistentData tapeID = new PersistentData(Cartonbox.instance, PersistentDataType.STRING);

    public static void initialize() {
        ScotchConfig = new ConfigManager("scotchDecoration.yml");
        //splashConfig.saveDefaultConfig();
        ScotchConfig.reloadConfig();
    }

    public static void reloadConfig(){
        ScotchConfig.reloadConfig();
    }

    public static void makeItAScotch(@NotNull ItemStack itemToMakeItATape) {
        ItemMeta iMeta = itemToMakeItATape.getItemMeta();
        tapeID.from(iMeta).set("tapeID", UUID.randomUUID().toString());
        itemToMakeItATape.setItemMeta(iMeta);
    }

    public static boolean isScotch(@NotNull ItemStack maybeIsTape) {
        return tapeID.from( maybeIsTape.getItemMeta() ).has("tapeID");
    }

    public static void decorateItemScotch(@NotNull ItemStack itemToDecorate) {
        ItemMeta iMeta = itemToDecorate.getItemMeta();
        String nameItem =  ScotchConfig.getConfig().getString("name");
        List<String> lore = ScotchConfig.getConfig().getStringList("lore");
        iMeta.setLore(lore);
    }
}
