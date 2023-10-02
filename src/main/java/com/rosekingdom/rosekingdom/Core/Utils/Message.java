package com.rosekingdom.rosekingdom.Core.Utils;

import net.kyori.adventure.text.Component;
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
}
