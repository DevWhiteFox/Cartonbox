package me.devwhitefox.cartonbox.listener;

import de.tr7zw.changeme.nbtapi.NBTContainer;
import de.tr7zw.changeme.nbtapi.NBTTileEntity;
import me.devwhitefox.cartonbox.Cartonbox;
import me.devwhitefox.cartonbox.utils.PersistentData;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Directional;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class UnboxCardboard implements Listener {
    @EventHandler
    public void unboxTheBox(@NotNull final BlockPlaceEvent e){
        ItemStack box = e.getItemInHand();
        if(box == null) return;

        PersistentData boxToBlockData = new PersistentData(Cartonbox.getInstance(), PersistentDataType.STRING).from(box.getItemMeta());
        if(boxToBlockData.has("tapeid")){
            e.setCancelled(true);
            return;
        }
        if(!boxToBlockData.has("blockdata")) return;

        NBTContainer cont = new NBTContainer( (String) boxToBlockData.get("blockdata") );

        Block block = e.getBlock();
        block.setType(Objects.requireNonNull(Material.getMaterial(boxToBlockData.get("material"))));
        NBTTileEntity targetBlock = new NBTTileEntity(block.getState());
        targetBlock.mergeCompound( cont );

        Directional directional = (Directional) block.getBlockData();
        directional.setFacing( rotateTowardPlayer( e.getPlayer() ) );
        block.setBlockData( directional );
    }

    private @NotNull BlockFace rotateTowardPlayer(@NotNull Player player){
        return player.getFacing().getOppositeFace();
    }
}
