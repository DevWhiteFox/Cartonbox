package me.devwhitefox.cartonbox.listener;

import me.devwhitefox.cartonbox.Cartonbox;
import me.devwhitefox.cartonbox.event.TapeBlockEvent;
import me.devwhitefox.cartonbox.item.CardboardBoxItem;
import me.devwhitefox.cartonbox.item.ScotchItem;
import me.devwhitefox.cartonbox.utils.NameItemFormatter;
import me.devwhitefox.cartonbox.utils.PersistentData;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Nameable;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Container;
import org.bukkit.block.ShulkerBox;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

public class BoxTheBlock implements org.bukkit.event.Listener {

    @EventHandler
    public void ClickOnBlockWithInventory(@NotNull final PlayerInteractEvent event) {
        Block block = event.getClickedBlock();
        if(block == null) return;
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
        Block block = event.getClickedBlock();
        if(block == null) return;

        BlockState state = block.getState();

        if(!Cartonbox.getOptions().getConfig().getBoolean("rules.matryoshka") && thereIsBoxInsideABlock(state)){
            event.setCancelled(true);
            return;
        }

        String finalName;
        if (state instanceof Nameable) {
            Nameable stateName = (Nameable) state;
            finalName = stateName.getCustomName();
            if (finalName == null) {
                finalName = getMaterialName(block);
            }
        }else{
            finalName = getMaterialName(block);
        }

        CardboardBoxItem cardboardBoxItem = new CardboardBoxItem();
        ItemStack box = cardboardBoxItem.generateItem();
        cardboardBoxItem.importBlockData(block);

        cardboardBoxItem.updateNameBox(finalName);

        event.getClickedBlock().setType(Material.AIR);

        World world = event.getClickedBlock().getWorld();
        Location location = event.getClickedBlock().getLocation();

        assert box != null;
        world.dropItemNaturally(location, box);
    }

    private String getMaterialName(@NotNull Block block) {
        String finalName;
        NameItemFormatter nameItemFormatter = new NameItemFormatter();
        finalName = nameItemFormatter.displayItemType(block.getType());
        return finalName;
    }

    private boolean thereIsBoxInsideABlock(BlockState state){
        org.bukkit.block.Container container = (org.bukkit.block.Container) state;
        PersistentData itemData = new PersistentData(Cartonbox.getInstance(), PersistentDataType.STRING);

        for(ItemStack item : container.getInventory().getStorageContents()) {
            if(item == null) continue;

            //If inside a shulker
            if(checkBoxInShulker(item)){
                return true;
            }

            //If inside block container
            itemData.from(item.getItemMeta());
            if(itemData.has("blockdata")){
                return true;
            }
        }

        return false;
    }

    private boolean checkBoxInShulker(@NotNull final ItemStack item){
        if(item.getItemMeta() instanceof BlockStateMeta){
            BlockStateMeta im = (BlockStateMeta)item.getItemMeta();
            if(im.getBlockState() instanceof ShulkerBox){
                return thereIsBoxInsideAShulker((ShulkerBox) im.getBlockState());
            }
        }
        return false;
    }

    private boolean thereIsBoxInsideAShulker(@NotNull final ShulkerBox shulker){
        PersistentData itemData = new PersistentData(Cartonbox.getInstance(), PersistentDataType.STRING);

        for(ItemStack item : shulker.getInventory().getStorageContents()) {
            if(item == null) continue;

            itemData.from(item.getItemMeta());
            if(itemData.has("blockdata")){
                return true;
            }
        }

        return false;
    }
}
