package com.rosekingdom.rosekingdom.Economy.Items;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class sPlus extends ItemStack {
    public sPlus(){
        setAmount(1);
        setType(Material.STICK);
        ItemMeta meta = getItemMeta();
        //todo: change later to match with the other stuff
        meta.setCustomModelData(1000);
        meta.displayName(Component.text("Set quantity and price", TextColor.fromHexString("#10FF33")).decoration(TextDecoration.ITALIC, false));
        setItemMeta(meta);
    }
}
