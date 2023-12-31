package com.rosekingdom.rosekingdom.Economy.Items;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class sOptions extends ItemStack {
    public sOptions(Component itemName){
        setAmount(1);
        setType(Material.PAPER);
        ItemMeta meta = getItemMeta();
        //TODO: Change to the proper value
        meta.setCustomModelData(1000);
        meta.displayName(itemName.append(Component.text("'s options")).decoration(TextDecoration.ITALIC, false));
        setItemMeta(meta);
    }
}
