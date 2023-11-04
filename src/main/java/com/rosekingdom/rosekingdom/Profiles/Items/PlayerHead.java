package com.rosekingdom.rosekingdom.Profiles.Items;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class PlayerHead extends ItemStack {
    public PlayerHead(OfflinePlayer player){
        setAmount(1);
        setType(Material.PLAYER_HEAD);
        SkullMeta skull = (SkullMeta) getItemMeta();
        skull.setOwningPlayer(player);
        skull.setCustomModelData(5000);
        skull.displayName(Component.text(player.getName()).decoration(TextDecoration.ITALIC, false));
        setItemMeta(skull);
    }
}
