package com.rosekingdom.rosekingdom.Profiles.Items;

import com.rosekingdom.rosekingdom.Core.Database.Main_Statements.UserStatement;
import com.rosekingdom.rosekingdom.Core.Utils.MillisToTime;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.Statistic;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

public class ProfilePlayTime extends ItemStack {
    public ProfilePlayTime(OfflinePlayer player){
        super(Material.PAPER);
        int rawTime = player.getStatistic(Statistic.PLAY_ONE_MINUTE)/20;
        Timestamp timestamp = UserStatement.getJoinDate(UserStatement.getId(player.getUniqueId()));
        Instant rawDate = timestamp.toInstant();
        String date = rawDate.toString();
        date = date.replace('-', '/');
        String time = String.format("Total time spent on the server: %s.", MillisToTime.withSymbol(rawTime));
        String joinDate = String.format("First joined on %s %s (UTC).", date.substring(0, 10), date.substring(11, 19));
        setAmount(1);
        ItemMeta meta = getItemMeta();
        meta.setCustomModelData(2103);
        meta.displayName(Component.text(time, TextColor.fromHexString("#17fc32"))
                .decoration(TextDecoration.ITALIC, false));
        meta.lore(List.of(Component.text(joinDate, TextColor.fromHexString("#17fc32"))
                .decoration(TextDecoration.ITALIC, false)));
        setItemMeta(meta);
    }
}
