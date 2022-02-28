package me.devwhitefox.cartonbox.commands.utils;

import me.devwhitefox.cartonbox.commands.GiveScotch;
import me.devwhitefox.cartonbox.console.MessageConsole;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CoreCommand implements CommandExecutor, TabExecutor {

    SubCommandCollector collector;

    public CoreCommand() {
        collector = new SubCommandCollector();
        collector.registerSubCommand("give", new GiveScotch());
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String @NotNull [] args) {

        if(args.length == 0) {
            sender.sendMessage(ChatColor.GOLD + "No command executed");
            return true;
        }

        ISubCommand sub = collector.getSubCommand(args[0]);

        if(sub != null) {
            if(sender instanceof Player || !sub.wantOnlyPlayer()) {
                if (sender.hasPermission(sub.getPermission())) {
                    sub.doCommand(sender, command, label, args);
                    return true;
                }
            }
            new MessageConsole().sendWarning("Not usable by console");
            return true;
        }

        sender.sendMessage(ChatColor.GOLD + "Wrong sub command executed");
        return true;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, String @NotNull [] args) {
        if(args.length == 1) {
            return collector.getAllSubCommand();
        }
        if(args.length > 1){
            return collector.getSubCommand(args[0]).tabList();
        }

        return new ArrayList<>();
    }
}
