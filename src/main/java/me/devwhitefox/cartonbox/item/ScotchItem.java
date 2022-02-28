package me.devwhitefox.cartonbox.item;

import me.devwhitefox.cartonbox.Cartonbox;
import me.devwhitefox.cartonbox.utils.ColorText;
import me.devwhitefox.cartonbox.utils.ConfigManager;
import me.devwhitefox.cartonbox.utils.HeadManager;
import me.devwhitefox.cartonbox.utils.PersistentData;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public class ScotchItem {
    private static ConfigManager ScotchConfig;
    private static final PersistentData tapeID = new PersistentData(Cartonbox.instance, PersistentDataType.STRING);

    public @Nullable ItemStack generateItem() {
        ItemStack scotch = new ItemStack(Material.PLAYER_HEAD);
        HeadManager.itemWithBase64(scotch, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzIxOTc2MmY5M2I3NzJlMzU4ZGE5OTJkYTIyYmM1YTUxYmU1YWJjN2JiOGFmZTEzNDI0MjhiMjQ5NTM2YzgifX19");

        makeItAScotch(scotch);
        decorateItemScotch(scotch);

        return scotch;
    }

    public static void initialize() {
        ScotchConfig = new ConfigManager("scotchDecoration.yml");
        //splashConfig.saveDefaultConfig();
        ScotchConfig.reloadConfig();
    }

    public static void reloadConfig(){
        ScotchConfig.reloadConfig();
    }

    public static @NotNull ItemStack makeItAScotch(@NotNull ItemStack itemToMakeItATape) {
        ItemMeta iMeta = itemToMakeItATape.getItemMeta();
        tapeID.from(iMeta).set("tapeID", UUID.randomUUID().toString());
        itemToMakeItATape.setItemMeta(iMeta);
        return  itemToMakeItATape;
    }

    public static boolean isScotch(@NotNull ItemStack maybeIsTape) {
        return tapeID.from( maybeIsTape.getItemMeta() ).has("tapeID");
    }

    public static @NotNull ItemStack decorateItemScotch(@NotNull ItemStack itemToDecorate) {
        ItemMeta iMeta = itemToDecorate.getItemMeta();

        String nameItem =  ScotchConfig.getConfig().getString("name");
        assert nameItem != null;
        nameItem = new ColorText().AndColorToMcColor(nameItem);
        assert iMeta != null;
        iMeta.setDisplayName(nameItem);

        List<String> lore = ScotchConfig.getConfig().getStringList("lore");
        lore = new ColorText().AndColorToMcColor(lore);
        iMeta.setLore(lore);

        itemToDecorate.setItemMeta(iMeta);
        return itemToDecorate;
    }
}
