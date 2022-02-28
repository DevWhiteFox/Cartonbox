package me.devwhitefox.cartonbox.commands;

import me.devwhitefox.cartonbox.commands.utils.ISubCommand;
import me.devwhitefox.cartonbox.item.ScotchItem;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class GiveScotch implements ISubCommand {

    @Override
    public void doCommand(final CommandSender sender, final Command command, final String label, final String @NotNull [] args) {
        Player player = Bukkit.getPlayer(args[1]);
        if(player == null) return;
        if(!player.isOnline()) return;

        player.getInventory().addItem(new ScotchItem().generateItem());
    }

    @Override
    public String getPermission() {
        return "cartonbox.give";
    }

    @Override
    public boolean wantOnlyPlayer() {
        return false;
    }

    @Override
    public List<String> tabList() {
        List<String> playersName = new ArrayList<>();
        for (Player player: Bukkit.getServer().getOnlinePlayers()) {
            playersName.add(player.getName());
        }
        return playersName;
    }
}
