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
        return Component.text("\uEf10\uDB00\uDC01")
                .append(Component.text(text))
                .color(TextColor.fromHexString("#cd0000"));
    }
    public static Component Info(String text){
        return Component.text("\uEf10\uDB00\uDC01")
                .append(Component.text(text))
                .color(TextColor.fromHexString("#ebb22f"));
    }

    public static Component Text(String text, String color){
        return Component.text(text)
                .color(TextColor.fromHexString(color));
    }


    // TEXT COLORS

    public static Component Gold(String text){
        return Component.text(text)
                .color(TextColor.fromHexString("#FFA500"));
    }
    public static Component Red(String text){
        return Component.text(text)
                .color(TextColor.fromHexString("#cd0000"));
    }
    public static Component Green(String text){
        return Component.text(text)
                .color(TextColor.fromHexString("#008d00"));
    }
    public static Component Lime(String text){
        return Component.text(text)
                .color(TextColor.fromHexString("#32CD32"));
    }
    public static Component Cyan(String text){
        return Component.text(text)
                .color(TextColor.fromHexString("#00FFFF"));
    }
    public static Component Purple(String text){
        return Component.text(text)
                .color(TextColor.fromHexString("#A020F0"));
    }
    public static Component Blue(String text){
        return Component.text(text)
                .color(TextColor.fromHexString("#1750AC"));
    }
    public static Component Orange(String text){
        return Component.text(text)
                .color(TextColor.fromHexString("#ff7d00"));
    }
    public static Component Magenta(String text){
        return Component.text(text)
                .color(TextColor.fromHexString("#FF00FF"));
    }
    public static Component Pink(String text){
        return Component.text(text)
                .color(TextColor.fromHexString("#ffb0cf"));
    }
    public static Component Bronze(String text){
        return Component.text(text)
                .color(TextColor.fromHexString("#CE8946"));
    }
    public static Component Black(String text){
        return Component.text(text)
                .color(TextColor.fromHexString("#000"));
    }
    public static Component Gray(String text){
        return Component.text(text)
                .color(TextColor.fromHexString("#808080"));
    }
    public static Component LightGray(String text){
        return Component.text(text)
                .color(TextColor.fromHexString("#cdcdcd"));
    }
    public static Component White(String text){
        return Component.text(text)
                .color(TextColor.fromHexString("#fff"));
    }


    // INT COLORS

    public static Component Gold(int text){
        return Component.text(text)
                .color(TextColor.fromHexString("#FFA500"));
    }
    public static Component Red(int text){
        return Component.text(text)
                .color(TextColor.fromHexString("#cd0000"));
    }
    public static Component Green(int text){
        return Component.text(text)
                .color(TextColor.fromHexString("#008d00"));
    }
    public static Component Lime(int text){
        return Component.text(text)
                .color(TextColor.fromHexString("#32CD32"));
    }
    public static Component Cyan(int text){
        return Component.text(text)
                .color(TextColor.fromHexString("#00FFFF"));
    }
    public static Component Purple(int text){
        return Component.text(text)
                .color(TextColor.fromHexString("#A020F0"));
    }
    public static Component Blue(int text){
        return Component.text(text)
                .color(TextColor.fromHexString("#1750AC"));
    }
    public static Component Orange(int text){
        return Component.text(text)
                .color(TextColor.fromHexString("#ff7d00"));
    }
    public static Component Magenta(int text){
        return Component.text(text)
                .color(TextColor.fromHexString("#FF00FF"));
    }
    public static Component Pink(int text){
        return Component.text(text)
                .color(TextColor.fromHexString("#ffb0cf"));
    }
    public static Component Bronze(int text){
        return Component.text(text)
                .color(TextColor.fromHexString("#CE8946"));
    }
    public static Component Black(int text){
        return Component.text(text)
                .color(TextColor.fromHexString("#000"));
    }
    public static Component Gray(int text){
        return Component.text(text)
                .color(TextColor.fromHexString("#808080"));
    }
    public static Component LightGray(int text){
        return Component.text(text)
                .color(TextColor.fromHexString("#cdcdcd"));
    }
    public static Component White(int text){
        return Component.text(text)
                .color(TextColor.fromHexString("#fff"));
    }

    // FLOAT COLORS

    public static Component Gold(float text){
        return Component.text(text)
                .color(TextColor.fromHexString("#FFA500"));
    }
    public static Component Red(float text){
        return Component.text(text)
                .color(TextColor.fromHexString("#cd0000"));
    }
    public static Component Green(float text){
        return Component.text(text)
                .color(TextColor.fromHexString("#008d00"));
    }
    public static Component Lime(float text){
        return Component.text(text)
                .color(TextColor.fromHexString("#32CD32"));
    }
    public static Component Cyan(float text){
        return Component.text(text)
                .color(TextColor.fromHexString("#00FFFF"));
    }
    public static Component Purple(float text){
        return Component.text(text)
                .color(TextColor.fromHexString("#A020F0"));
    }
    public static Component Blue(float text){
        return Component.text(text)
                .color(TextColor.fromHexString("#1750AC"));
    }
    public static Component Orange(float text){
        return Component.text(text)
                .color(TextColor.fromHexString("#ff7d00"));
    }
    public static Component Magenta(float text){
        return Component.text(text)
                .color(TextColor.fromHexString("#FF00FF"));
    }
    public static Component Pink(float text){
        return Component.text(text)
                .color(TextColor.fromHexString("#ffb0cf"));
    }
    public static Component Bronze(float text){
        return Component.text(text)
                .color(TextColor.fromHexString("#CE8946"));
    }
    public static Component Black(float text){
        return Component.text(text)
                .color(TextColor.fromHexString("#000"));
    }
    public static Component Gray(float text){
        return Component.text(text)
                .color(TextColor.fromHexString("#808080"));
    }
    public static Component LightGray(float text){
        return Component.text(text)
                .color(TextColor.fromHexString("#cdcdcd"));
    }
    public static Component White(float text){
        return Component.text(text)
                .color(TextColor.fromHexString("#fff"));
    }

    // DOUBLE COLORS

    public static Component Gold(double text){
        return Component.text(text)
                .color(TextColor.fromHexString("#FFA500"));
    }
    public static Component Red(double text){
        return Component.text(text)
                .color(TextColor.fromHexString("#cd0000"));
    }
    public static Component Green(double text){
        return Component.text(text)
                .color(TextColor.fromHexString("#008d00"));
    }
    public static Component Lime(double text){
        return Component.text(text)
                .color(TextColor.fromHexString("#32CD32"));
    }
    public static Component Cyan(double text){
        return Component.text(text)
                .color(TextColor.fromHexString("#00FFFF"));
    }
    public static Component Purple(double text){
        return Component.text(text)
                .color(TextColor.fromHexString("#A020F0"));
    }
    public static Component Blue(double text){
        return Component.text(text)
                .color(TextColor.fromHexString("#1750AC"));
    }
    public static Component Orange(double text){
        return Component.text(text)
                .color(TextColor.fromHexString("#ff7d00"));
    }
    public static Component Magenta(double text){
        return Component.text(text)
                .color(TextColor.fromHexString("#FF00FF"));
    }
    public static Component Pink(double text){
        return Component.text(text)
                .color(TextColor.fromHexString("#ffb0cf"));
    }
    public static Component Bronze(double text){
        return Component.text(text)
                .color(TextColor.fromHexString("#CE8946"));
    }
    public static Component Black(double text){
        return Component.text(text)
                .color(TextColor.fromHexString("#000"));
    }
    public static Component Gray(double text){
        return Component.text(text)
                .color(TextColor.fromHexString("#808080"));
    }
    public static Component LightGray(double text){
        return Component.text(text)
                .color(TextColor.fromHexString("#cdcdcd"));
    }
    public static Component White(double text){
        return Component.text(text)
                .color(TextColor.fromHexString("#fff"));
    }
}
