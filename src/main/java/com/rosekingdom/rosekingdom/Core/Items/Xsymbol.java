package com.rosekingdom.rosekingdom.Core.Items;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class Xsymbol extends ItemStack {
    public Xsymbol(String title, List<Component> lore){
        setAmount(1);
        setType(Material.STICK);
        ItemMeta meta = getItemMeta();
        meta.setCustomModelData(2);
        meta.displayName(Component.text(title));
        meta.lore(lore);
        setItemMeta(meta);
    }
}
