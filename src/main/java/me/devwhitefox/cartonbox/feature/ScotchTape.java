package me.devwhitefox.cartonbox.feature;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;

public class ScotchTape implements org.bukkit.event.Listener {
    @EventHandler
    public void ClickOnBlockWithInventory(PlayerInteractEvent event) {
        if(event.getAction() != org.bukkit.event.block.Action.RIGHT_CLICK_BLOCK) return;

    }
}
