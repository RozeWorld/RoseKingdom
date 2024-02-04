package com.rosekingdom.rosekingdom.Core.Items;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class Checksymbol extends ItemStack {
    public Checksymbol(String title, List<Component> lore){
        setAmount(1);
        setType(Material.STICK);
        ItemMeta meta = getItemMeta();
        meta.setCustomModelData(1);
        meta.displayName(Component.text(title).decoration(TextDecoration.ITALIC, false));
        meta.lore(lore);
        setItemMeta(meta);
    }
}
