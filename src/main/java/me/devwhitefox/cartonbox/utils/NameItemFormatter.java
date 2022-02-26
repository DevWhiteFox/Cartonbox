package me.devwhitefox.cartonbox.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

public class NameItemFormatter {
    public String displayItemType(@NotNull Material material){
        StringBuilder builder = new StringBuilder();
        for (String word : material.name().split("_")){
            builder.append(word.substring(0, 1).toUpperCase())
                    .append(word.substring(1).toLowerCase()).append(" ");
        }
        return builder.toString().trim();
    }

    public void renameItem(@NotNull ItemStack item, String newName){
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return;

        meta.setDisplayName(newName);
        item.setItemMeta(meta);
    }

    public String getNameItem(@NotNull ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        if(meta != null && meta.hasDisplayName()){
            return meta.getDisplayName();
        }
        return displayItemType(item.getType());
    }
}
