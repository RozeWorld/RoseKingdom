package com.rosekingdom.rosekingdom.Profiles.Items;

import com.rosekingdom.rosekingdom.Profiles.Statements.ProfileStatement;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class StreakScore extends ItemStack {
    public StreakScore(OfflinePlayer player){
        setAmount(1);
        setType(Material.PAPER);
        ItemMeta meta = getItemMeta();
        meta.displayName(Component.text("Daily Streak: " + ProfileStatement.getStreak(player), TextColor.fromHexString("#fa9e14"))
                .decoration(TextDecoration.ITALIC, false));
        meta.lore(List.of(Component.text("Daily Streak Highscore: " + ProfileStatement.getHighscore(player), TextColor.fromHexString("#fa9e14"))
                .decoration(TextDecoration.ITALIC, false)));
        setItemMeta(meta);
    }
}
