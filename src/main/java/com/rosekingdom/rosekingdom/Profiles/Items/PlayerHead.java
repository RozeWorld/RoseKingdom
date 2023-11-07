package com.rosekingdom.rosekingdom.Profiles.Items;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PlayerHead extends ItemStack {
    public PlayerHead(Player player){
        setAmount(1);

        setType(Material.PAPER);
        ItemMeta meta = getItemMeta();
        //TODO: get the rank symbol
        meta.displayName(Component.text(player.getName(), TextColor.fromHexString("#9c9c9c")).decoration(TextDecoration.ITALIC, false));
        setItemMeta(meta);
    }
}
