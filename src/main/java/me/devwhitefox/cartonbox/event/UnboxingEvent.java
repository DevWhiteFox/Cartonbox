package me.devwhitefox.cartonbox.event;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class UnboxingEvent extends org.bukkit.event.Event implements Cancellable {
    //This need for the purpose of a spigot event
    private static final HandlerList HANDLERS = new HandlerList();
    private boolean isCancelled;
    private final Block whatBlock;
    private final Player whoTape;

    //Event's data about event
    public UnboxingEvent(Block whatBlock, Player whoTape) {
        this.whatBlock = whatBlock;
        this.whoTape = whoTape;
        this.isCancelled = false;
    }

    //Getter
    public Block getWhatBlock() {
        return whatBlock;
    }

    public Player getWhoTape() {
        return whoTape;
    }

    //If in future need a support for other plugin, then must update this event es. shortcut for get some data from block and player

    public static HandlerList getHandlersList() {
        return HANDLERS;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    /**
     * Gets the cancellation state of this event. A cancelled event will not
     * be executed in the server, but will still pass to other plugins
     *
     * @return true if this event is cancelled
     */
    @Override
    public boolean isCancelled() {
        return isCancelled;
    }

    /**
     * Sets the cancellation state of this event. A cancelled event will not
     * be executed in the server, but will still pass to other plugins.
     *
     * @param cancel true if you wish to cancel this event
     */
    @Override
    public void setCancelled(boolean cancel) {
        isCancelled = cancel;
    }
}
