package com.rosekingdom.rosekingdom.Profiles.Items;

import com.rosekingdom.rosekingdom.Core.Database.Main_Statements.UserStatement;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.Statistic;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ProfilePlayTime extends ItemStack {
    public ProfilePlayTime(OfflinePlayer player){
        int rawTime = player.getStatistic(Statistic.PLAY_ONE_MINUTE)/20;
        int days = rawTime/86400;
        int hours = (rawTime%86400) / 3600;
        int minutes = ((rawTime%86400) % 3600) / 60;
        int seconds = rawTime % 60;
        String date = UserStatement.getJoinDate(UserStatement.getId(player.getUniqueId()));
        date = date.substring(0, date.indexOf('.')).replace('-', '/');
        String time = String.format("Total time spent on the server: d%d h%d m%d s%d.", days, hours, minutes, seconds);
        String joinDate = String.format("First joined on %s (UTC+1).", date);
        setAmount(1);
        setType(Material.PAPER);
        ItemMeta meta = getItemMeta();
        meta.setCustomModelData(5001);
        meta.displayName(Component.text(time, TextColor.fromHexString("#17fc32"))
                .decoration(TextDecoration.ITALIC, false));
        meta.lore(List.of(Component.text(joinDate, TextColor.fromHexString("#17fc32"))
                .decoration(TextDecoration.ITALIC, false)));
        setItemMeta(meta);
    }
}
