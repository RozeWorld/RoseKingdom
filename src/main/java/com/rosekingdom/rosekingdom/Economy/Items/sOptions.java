package com.rosekingdom.rosekingdom.Economy.Items;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class sOptions extends ItemStack {
    public sOptions(Component itemName){
        super(Material.PAPER);
        setAmount(1);
        ItemMeta meta = getItemMeta();
        meta.setCustomModelData(2356);
        meta.displayName(itemName.append(Component.text("'s options")).decoration(TextDecoration.ITALIC, false));
        setItemMeta(meta);
    }
}
