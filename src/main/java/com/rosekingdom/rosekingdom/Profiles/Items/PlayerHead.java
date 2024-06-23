package com.rosekingdom.rosekingdom.Profiles.Items;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class PlayerHead extends ItemStack {
    public PlayerHead(OfflinePlayer player){
        super(Material.PLAYER_HEAD);
        setAmount(1);
        SkullMeta meta = (SkullMeta) getItemMeta();
        meta.setOwningPlayer(player);
        meta.setCustomModelData(2000);
        meta.displayName(Component.text(player.getName(), TextColor.fromHexString("#9c9c9c")).decoration(TextDecoration.ITALIC, false));
        setItemMeta(meta);
    }
}
