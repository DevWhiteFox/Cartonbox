package me.devwhitefox.cartonbox.commands.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SubCommandCollector {
    HashMap<String, ISubCommand> subCommands = new HashMap<>();

    public void registerSubCommand(String key, ISubCommand command){
        subCommands.put(key, command);
    }

    public ISubCommand getSubCommand(String key){
        return subCommands.get(key);
    }

    public List<String> getAllSubCommand(){
        return new ArrayList<>(subCommands.keySet());
    }
}
