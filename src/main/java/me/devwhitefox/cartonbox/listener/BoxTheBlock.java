package me.devwhitefox.cartonbox.listener;

import me.devwhitefox.cartonbox.event.TapeBlockEvent;
import me.devwhitefox.cartonbox.item.CardboardBoxItem;
import me.devwhitefox.cartonbox.item.ScotchItem;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Container;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class BoxTheBlock implements org.bukkit.event.Listener {
    @EventHandler
    public void ClickOnBlockWithInventory(@NotNull final PlayerInteractEvent event) {
        Block block = event.getClickedBlock();

        //If block with inventory
        if(event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        if(!(block.getState() instanceof Container)) return;

        //If clicked by scotch
        ItemStack item = event.getItem();
        if(item == null) return;
        if(!ScotchItem.isScotch(item)) return;

        //Fire event for other plugin
        TapeBlockEvent tapeBlockEvent = new TapeBlockEvent(event.getClickedBlock(), event.getPlayer());
        Bukkit.getPluginManager().callEvent(tapeBlockEvent);

        if (!tapeBlockEvent.isCancelled()){
            blockToBoxItem(event);
        }
    }

    private void blockToBoxItem(@NotNull PlayerInteractEvent event) {
        event.setCancelled(true);

        CardboardBoxItem cardboardBoxItem = new CardboardBoxItem();
        ItemStack box = cardboardBoxItem.generateItem();
        cardboardBoxItem.importBlockData(event.getClickedBlock());

        event.getClickedBlock().setType(Material.AIR);

        World world = event.getClickedBlock().getWorld();
        Location location = event.getClickedBlock().getLocation();

        world.dropItemNaturally(location, box);
    }
}
