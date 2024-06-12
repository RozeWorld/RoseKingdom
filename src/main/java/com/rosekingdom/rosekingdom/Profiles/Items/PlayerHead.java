package com.rosekingdom.rosekingdom.Profiles.Items;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class PlayerHead extends ItemStack {
    public PlayerHead(OfflinePlayer player, boolean head){
        setAmount(1);
        if(!head) {
            setType(Material.PAPER);
            ItemMeta meta = getItemMeta();
            meta.setCustomModelData(1);
            meta.displayName(Component.text(player.getName(), TextColor.fromHexString("#9c9c9c")).decoration(TextDecoration.ITALIC, false));
            setItemMeta(meta);
        }else{
            setType(Material.PLAYER_HEAD);
            SkullMeta meta = (SkullMeta) getItemMeta();
            meta.setOwningPlayer(player);
            meta.setCustomModelData(2000);
            meta.displayName(Component.text(player.getName(), TextColor.fromHexString("#9c9c9c")).decoration(TextDecoration.ITALIC, false));
            setItemMeta(meta);
        }
    }
}
