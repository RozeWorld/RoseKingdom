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
import org.bukkit.inventory.meta.SkullMeta;

import java.util.List;

public class ProfilePlayTime extends ItemStack {
    public ProfilePlayTime(OfflinePlayer player){
        int rawTime = player.getStatistic(Statistic.PLAY_ONE_MINUTE)/20;
        String date = UserStatement.getJoinDate(UserStatement.getId(player.getUniqueId()));
        date = date.substring(0, date.indexOf('.')).replace('-', '/');
        String time = String.format("Total time spent on the server: %s.", MillisToTime.withSymbol(rawTime));
        String joinDate = String.format("First joined on %s (UTC+1).", date);
        setAmount(1);
        setType(Material.PLAYER_HEAD);
        SkullMeta skull = (SkullMeta) getItemMeta();
        skull.setOwningPlayer(player);
        skull.setCustomModelData(2000);
        skull.displayName(Component.text(time, TextColor.fromHexString("#17fc32"))
                .decoration(TextDecoration.ITALIC, false));
        skull.lore(List.of(Component.text(joinDate, TextColor.fromHexString("#17fc32"))
                .decoration(TextDecoration.ITALIC, false)));
        setItemMeta(skull);
    }
}
