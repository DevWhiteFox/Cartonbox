package me.devwhitefox.cartonbox.utils;

import me.devwhitefox.cartonbox.Cartonbox;
import me.devwhitefox.cartonbox.console.SplashConsole;

public class ReloadPlugin {
    //Callable by command
    public ReloadPlugin(){
        Cartonbox.instance.reloadConfig();
        SplashConsole.reloadConfig();
        //List of class that require reload because they have a config file
    }
}
