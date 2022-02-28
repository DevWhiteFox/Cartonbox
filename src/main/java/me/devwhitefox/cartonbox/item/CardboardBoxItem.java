package me.devwhitefox.cartonbox.item;

import me.devwhitefox.cartonbox.console.MessageConsole;
import me.devwhitefox.cartonbox.utils.HeadManager;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.data.BlockData;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CardboardBoxItem {
    public @Nullable ItemStack generateItem(){
        ItemStack box = new ItemStack(Material.PLAYER_HEAD);
        HeadManager.itemWithBase64(box, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDk2NDk2ODVjM2FkZmJkN2U2NWY5OTA1ZjcwNWZjNTY3NGJlNGM4ZWE1YTVkNmY1ZjcyZThlYmFkMTkyOSJ9fX0=");
        return box;
    }

    public @Nullable ItemStack importBlockData(@NotNull Block block){
        BlockState state = block.getState();
        String data = state.getBlockData().getAsString();

        new MessageConsole().sendInfo(data);
        return new ItemStack(Material.AIR);
    }
}
