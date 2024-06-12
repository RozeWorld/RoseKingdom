package com.rosekingdom.rosekingdom.Economy.Items;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class sAddOffer extends ItemStack {
    public sAddOffer(){
        setAmount(1);
        setType(Material.STICK);
        ItemMeta meta = getItemMeta();
        meta.setCustomModelData(2353);
        meta.displayName(Component.text("Add offer for this item", TextColor.fromHexString("#10FF33")).decoration(TextDecoration.ITALIC, false));
        setItemMeta(meta);
    }
}
