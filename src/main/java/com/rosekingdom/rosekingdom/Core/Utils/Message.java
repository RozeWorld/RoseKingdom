package com.rosekingdom.rosekingdom.Core.Utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;

public class Message {
    public static void Console(String text){
        Bukkit.getLogger().info(text);
    }

    public static void Exception(String error){
        Bukkit.getLogger().info(error);
    }

    public static void Global(String text){
        Bukkit.broadcast(Component.text(text));
    }

    public static Component Warning(String text){
        return Component.text("\uEe01 ")
                .append(Component.text(text))
                .color(TextColor.fromHexString("#e30000"));
    }
    public static Component Info(String text){
        return Component.text("\uEe02 ")
                .append(Component.text(text))
                .color(TextColor.fromHexString("#ebb22f"));
    }
}
