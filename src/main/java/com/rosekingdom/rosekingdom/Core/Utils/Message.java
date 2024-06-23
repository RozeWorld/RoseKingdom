package com.rosekingdom.rosekingdom.Core.Utils;

import com.rosekingdom.rosekingdom.RoseKingdom;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class Message {
    protected static Plugin plugin = RoseKingdom.getPlugin(RoseKingdom.class);
    public static void Console(String text){
        plugin.getLogger().info(text);
    }

    public static void Console(Component message) {
        plugin.getComponentLogger().info(message);
    }

    public static void Exception(String error, Exception e){
        plugin.getLogger().warning(error);
        plugin.getLogger().warning(e.getMessage());
    }

    public static void Global(String text){
        Bukkit.broadcast(Component.text(text));
    }
    public static void Global(TextComponent text) {
        Bukkit.broadcast(text);
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
    public static Component Green(String text){
        return Component.text(text)
                .color(TextColor.fromHexString("#e30000"));
    }
    public static Component LightGreen(String text){
        return Component.text(text)
                .color(TextColor.fromHexString("#e30000"));
    }
    public static Component Purple(String text){
        return Component.text(text)
                .color(TextColor.fromHexString("#e30000"));
    }
    public static Component Blue(String text){
        return Component.text(text)
                .color(TextColor.fromHexString("#e30000"));
    }
    public static Component LightBlue(String text){
        return Component.text(text)
                .color(TextColor.fromHexString("#e30000"));
    }


}
