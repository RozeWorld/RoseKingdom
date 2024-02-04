package com.rosekingdom.rosekingdom.Economy.Items;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class sAdd extends ItemStack {
    public sAdd(){
        setAmount(1);
        setType(Material.PAPER);
        ItemMeta meta = getItemMeta();
        meta.setCustomModelData(2351);
        meta.displayName(Component.text("Add Item", TextColor.fromHexString("#0ac200")).decoration(TextDecoration.ITALIC, false));
        setItemMeta(meta);
    }
}
