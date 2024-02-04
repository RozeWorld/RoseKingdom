package com.rosekingdom.rosekingdom.Economy.Items;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class sPriceItem extends ItemStack {
    public sPriceItem(int amount, int model, boolean plus){
        setAmount(amount);
        setType(Material.PAPER);
        ItemMeta meta = getItemMeta();
        meta.setCustomModelData(model);
        if(plus){
            meta.displayName(Component.text("Add " + amount));
        }else {
            meta.displayName(Component.text("Remove " + amount));
        }
        setItemMeta(meta);
    }
}
