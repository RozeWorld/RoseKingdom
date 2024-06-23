package com.rosekingdom.rosekingdom.Economy.Items;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class sRemoveItem extends ItemStack {
    public sRemoveItem(){
        super(Material.STICK);
        setAmount(1);
        ItemMeta meta = getItemMeta();
        meta.setCustomModelData(2352);
        meta.displayName(Component.text("Remove Item", TextColor.fromHexString("#f70000")).decoration(TextDecoration.ITALIC, false));
        setItemMeta(meta);
    }
}
