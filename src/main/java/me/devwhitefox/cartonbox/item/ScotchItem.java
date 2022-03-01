package me.devwhitefox.cartonbox.item;

import me.devwhitefox.cartonbox.Cartonbox;
import me.devwhitefox.cartonbox.utils.ColorText;
import me.devwhitefox.cartonbox.utils.HeadManager;
import me.devwhitefox.cartonbox.utils.PersistentData;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public class ScotchItem {

    private static final Cartonbox plugin = Cartonbox.getInstance();
    private static final PersistentData tapeID = new PersistentData(plugin, PersistentDataType.STRING);

    //Scotch head data
    String nameSkull;
    String textureValue;

    public ScotchItem(){
        nameSkull = plugin.getConfig().getString("scotch.skull.name");
        textureValue = plugin.getConfig().getString("scotch.skull.texture");
    }

    public @Nullable ItemStack generateItem() {
        ItemStack scotch = HeadManager.GetCustomHead(nameSkull, textureValue);

        makeItAScotch(scotch);
        decorateItemScotch(scotch);

        return scotch;
    }

    public static @NotNull ItemStack makeItAScotch(@NotNull ItemStack itemToMakeItATape) {
        ItemMeta iMeta = itemToMakeItATape.getItemMeta();
        tapeID.from(iMeta).set("tapeID", UUID.randomUUID().toString());
        itemToMakeItATape.setItemMeta(iMeta);
        return itemToMakeItATape;
    }

    public static boolean isScotch(@NotNull ItemStack maybeIsTape) {
        return tapeID.from( maybeIsTape.getItemMeta() ).has("tapeID");
    }

    public static @NotNull ItemStack decorateItemScotch(@NotNull ItemStack itemToDecorate) {
        ItemMeta iMeta = itemToDecorate.getItemMeta();

        String nameItem = plugin.getConfig().getString("scotch.name");
        assert nameItem != null;
        nameItem = new ColorText().AndColorToMcColor(nameItem);
        assert iMeta != null;
        iMeta.setDisplayName(nameItem);

        List<String> lore = plugin.getConfig().getStringList("scotch.lore");
        lore = new ColorText().AndColorToMcColor(lore);
        iMeta.setLore(lore);

        itemToDecorate.setItemMeta(iMeta);
        return itemToDecorate;
    }
}
