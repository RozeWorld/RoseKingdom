package com.rosekingdom.rosekingdom.Core.Utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;

public class Message {
    public static void Console(String text){
        Bukkit.getLogger().info(text);
    }

    public static void Exception(String error, Exception e){
        Bukkit.getLogger().warning(error);
        Bukkit.getLogger().warning(e.getMessage());
    }

    public static void Global(String text){
        Bukkit.broadcast(Component.text(text));
    }

    public static Component Warning(String text){
        return Component.text("\uEf10")
                .append(Component.text(text))
                .color(TextColor.fromHexString("#e30000"));
    }
    public static Component Info(String text){
        return Component.text("\uEf10")
                .append(Component.text(text))
                .color(TextColor.fromHexString("#ebb22f"));
    }

    public static Component Text(String text, String color){
        return Component.text(text)
                .color(TextColor.fromHexString(color));
    }

    public static Component Gold(String text){
        return Component.text(text)
                .color(TextColor.fromHexString("#ebb22f"));
    }
    public static Component Red(String text){
        return Component.text(text)
                .color(TextColor.fromHexString("#e30000"));
    }
}
