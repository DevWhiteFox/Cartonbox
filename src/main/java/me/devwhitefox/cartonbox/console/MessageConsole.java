package me.devwhitefox.cartonbox.console;

import me.devwhitefox.cartonbox.Cartonbox;

import java.util.logging.Level;

public class MessageConsole {

    private void sendLog(Level level, String message, Exception e){
        Cartonbox.getInstance().getLogger().log(level, message, e);
    }

    private void sendLog(Level level, String message){
        sendLog(level,message,null);
    }

    public void sendInfo(String message){
        sendLog(Level.INFO, message);
    }

    public void sendWarning(String message, Exception e){
        sendLog(Level.WARNING, message, e);
    }

    public void sendWarning(String message){
        sendWarning(message, null);
    }

    public void sendSevere(String message, Exception e){
        sendLog(Level.SEVERE, message, e);
    }

    public void sendSevere(String message){
        sendSevere(message, null);
    }




}
