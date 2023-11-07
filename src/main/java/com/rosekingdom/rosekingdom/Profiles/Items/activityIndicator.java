package com.rosekingdom.rosekingdom.Profiles.Items;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class activityIndicator extends ItemStack {
    public activityIndicator(OfflinePlayer player){
        setAmount(1);
        setType(Material.PAPER);
        ItemMeta meta = getItemMeta();
        meta.setCustomModelData(5002);
        if(player.isOnline()){
            meta.displayName(Component.text("Currently Online", TextColor.fromHexString("#17fc32"))
                    .decoration(TextDecoration.ITALIC, false));
        }else{
            meta.displayName(Component.text("Currently Offline", TextColor.fromHexString("#e80e0e"))
                    .decoration(TextDecoration.ITALIC, false));
        }
        setItemMeta(meta);
    }
}
