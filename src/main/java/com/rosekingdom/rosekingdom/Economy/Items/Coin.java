package com.rosekingdom.rosekingdom.Economy.Items;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Coin extends ItemStack {
    public Coin(int amount){
        setAmount(amount);
        setType(Material.PAPER);
        ItemMeta meta = getItemMeta();
        meta.setCustomModelData(10);
        meta.displayName(Component.text("Rose Coin").decoration(TextDecoration.ITALIC, false).color(TextColor.fromHexString("#c91444")));
        setItemMeta(meta);
    }
}
