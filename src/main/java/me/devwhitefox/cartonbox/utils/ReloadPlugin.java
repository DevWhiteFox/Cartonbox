package me.devwhitefox.cartonbox.utils;

import me.devwhitefox.cartonbox.Cartonbox;
public class ReloadPlugin {
    //Callable by command
    public ReloadPlugin(){
        Cartonbox.getInstance().reloadConfig();
        Cartonbox.getOptions().reloadConfig();
    }
}
