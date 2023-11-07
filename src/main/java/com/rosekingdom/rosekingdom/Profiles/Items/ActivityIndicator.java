package com.rosekingdom.rosekingdom.Profiles.Items;

import com.rosekingdom.rosekingdom.Core.Utils.MillisToTime;
import com.rosekingdom.rosekingdom.Profiles.Statements.ProfileStatement;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ActivityIndicator extends ItemStack {
    public ActivityIndicator(OfflinePlayer player){
        setAmount(1);
        setType(Material.PAPER);
        ItemMeta meta = getItemMeta();
        meta.setCustomModelData(5002);
        if(player.isOnline()){
            meta.displayName(Component.text("Currently Online", TextColor.fromHexString("#17fc32"))
                    .decoration(TextDecoration.ITALIC, false));
        }else{
            long lastOnline = ProfileStatement.getLast_Online(player);
            int rawTime = (int) (System.currentTimeMillis()-lastOnline)/1000;
            String text = String.format("Last seen %s ago.", MillisToTime.withWord(rawTime));
            meta.displayName(Component.text(text, TextColor.fromHexString("#e80e0e"))
                    .decoration(TextDecoration.ITALIC, false));
        }
        setItemMeta(meta);
    }
}
