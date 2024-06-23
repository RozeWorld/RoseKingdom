package com.rosekingdom.rosekingdom.Core.Items;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class InvsibleItem extends ItemStack {
    public InvsibleItem(Component name) {
        super(Material.PAPER);
        ItemMeta meta = getItemMeta();
        meta.setCustomModelData(1);
        meta.displayName(name.decoration(TextDecoration.ITALIC, false));
        setItemMeta(meta);
    }

    public InvsibleItem(Component name, List<Component> lore) {
        super(Material.PAPER);
        ItemMeta meta = getItemMeta();
        meta.setCustomModelData(1);
        meta.displayName(name.decoration(TextDecoration.ITALIC, false));
        meta.lore(lore);
        setItemMeta(meta);
    }
}
