package me.devwhitefox.cartonbox.item;

import de.tr7zw.changeme.nbtapi.NBTTileEntity;
import me.devwhitefox.cartonbox.Cartonbox;
import me.devwhitefox.cartonbox.utils.ColorText;
import me.devwhitefox.cartonbox.utils.HeadManager;
import me.devwhitefox.cartonbox.utils.PersistentData;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CardboardBoxItem {
    private static final Cartonbox plugin = Cartonbox.getInstance();
    ItemStack box = new ItemStack(Material.PLAYER_HEAD);

    //Scotch head data
    String nameSkull;
    String textureValue;

    public CardboardBoxItem(){
        nameSkull = plugin.getConfig().getString("box.skull.name");
        textureValue = plugin.getConfig().getString("box.skull.texture");
    }

    public @Nullable ItemStack generateItem(){
        box = HeadManager.GetCustomHead(nameSkull, textureValue);

        decorateItemCardboard(box);
        return box;
    }

    public void importBlockData(@NotNull Block block){
        NBTTileEntity nbtt = new NBTTileEntity(block.getState());

        ItemMeta iMeta = box.getItemMeta();
        PersistentData blockDataString = new PersistentData( Cartonbox.getInstance(), PersistentDataType.STRING ).from( iMeta );
        blockDataString.set("blockdata", nbtt.toString());
        blockDataString.set("material", block.getType().toString());
        box.setItemMeta( iMeta );
    }


    private void decorateItemCardboard(@NotNull ItemStack itemToDecorate) {
        ItemMeta iMeta = itemToDecorate.getItemMeta();

        String nameItem =  plugin.getConfig().getString("box.name");
        assert nameItem != null;
        nameItem = new ColorText().AndColorToMcColor(nameItem);
        assert iMeta != null;
        iMeta.setDisplayName(nameItem);

        List<String> lore = plugin.getConfig().getStringList("box.lore");
        lore = new ColorText().AndColorToMcColor(lore);
        iMeta.setLore(lore);

        itemToDecorate.setItemMeta(iMeta);
    }

    public void updateNameBox(@NotNull String nameBlock){
        ItemMeta iMeta = box.getItemMeta();
        assert iMeta != null;
        String rawName = iMeta.getDisplayName();
        rawName = rawName.replace("%container_name%", nameBlock);
        iMeta.setDisplayName(rawName);
        box.setItemMeta(iMeta);
    }
}
