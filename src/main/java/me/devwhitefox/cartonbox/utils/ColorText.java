package me.devwhitefox.cartonbox.utils;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ColorText {

    public @NotNull List<String> AndColorToMcColor(@NotNull List<String> texts){
        for (int i = 0; i < texts.size(); i++) {
            texts.set(i, this.AndColorToMcColor( texts.get(i) ));
        }
        return texts;
    }

    public @NotNull String AndColorToMcColor(@NotNull String text){
        return text.replace("&", "§");
    }
}
