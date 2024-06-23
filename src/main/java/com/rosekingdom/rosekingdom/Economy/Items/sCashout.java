package com.rosekingdom.rosekingdom.Economy.Items;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class sCashout extends ItemStack {
    public sCashout(int coins){
        super(Material.PAPER);
        setAmount(1);
        ItemMeta meta = getItemMeta();
        meta.setCustomModelData(2357);
        meta.displayName(Component.text("Collect coins!", TextColor.fromHexString("#f5ae07")).decoration(TextDecoration.ITALIC, false));
        List<Component> lore = new ArrayList<>();
        lore.add(Component.empty());
        lore.add(Component.text("Coins: " + coins, TextColor.fromHexString("#A5A5A5")).decoration(TextDecoration.ITALIC, false));
        lore.add(Component.empty());
        meta.lore(lore);
        setItemMeta(meta);
    }
}
