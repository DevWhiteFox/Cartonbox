package me.devwhitefox.cartonbox.commands;

import me.devwhitefox.cartonbox.utils.ReloadPlugin;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ReloadCommand implements ISubCommand {

    @Override
    public void doCommand(@NotNull CommandSender sender,@NotNull Command command,@NotNull String label,@NotNull String[] args) {
        new ReloadPlugin();
        sender.sendMessage(ChatColor.DARK_GREEN + "Reloaded");
    }

    @Override
    public String getPermission() {
        return "cb.reload";
    }

    @Override
    public boolean wantOnlyPlayer() {
        return false;
    }

    @Override
    public List<String> getDataParameter() {
        return new ArrayList<>();
    }
}
