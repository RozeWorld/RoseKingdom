package com.rosekingdom.rosekingdom.Profiles.Items;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ProfilePlayTime extends ItemStack {
    public ProfilePlayTime(Player player){
        int rawTime = player.getStatistic(Statistic.PLAY_ONE_MINUTE)/20;
        int hours = rawTime/3600;
        int minutes = (rawTime%3600) / 60;
        int seconds = rawTime % 60;
        String time = String.format("H: %d M: %d S: %d", hours, minutes, seconds);
        setAmount(1);
        setType(Material.PAPER);
        ItemMeta meta = getItemMeta();
        meta.setCustomModelData(5001);
        meta.displayName(Component.text("Total Playtime", TextColor.fromHexString("#17fc32")).decoration(TextDecoration.ITALIC, false));
        meta.lore(List.of(Component.text(time, TextColor.fromHexString("#17fc32")).decoration(TextDecoration.ITALIC, false)));
        setItemMeta(meta);
    }
}
