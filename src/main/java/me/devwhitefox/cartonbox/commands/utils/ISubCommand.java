package me.devwhitefox.cartonbox.commands.utils;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;

public interface ISubCommand {
    void doCommand(final CommandSender sender, final Command command, final String label, final String[] args);
    String getPermission();
    boolean wantOnlyPlayer();
    List<String> tabList();
}
