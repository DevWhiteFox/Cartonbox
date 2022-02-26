package me.devwhitefox.cartonbox.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;

public interface ISubCommand {
    void doCommand(CommandSender sender, Command command, String label, String[] args);
    String getPermission();
    boolean wantOnlyPlayer();
    List<String> getDataParameter();
}
