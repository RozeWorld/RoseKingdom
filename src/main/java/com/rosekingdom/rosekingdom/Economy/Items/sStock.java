package com.rosekingdom.rosekingdom.Economy.Items;

import com.rosekingdom.rosekingdom.Economy.Statements.StockStatement;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class sStock extends ItemStack {
    public sStock(String store){
        setAmount(1);
        setType(Material.CHEST);
        ItemMeta meta = getItemMeta();
        meta.setCustomModelData(2353);
        meta.displayName(Component.text("Stock"));
        List<ItemStack> items = StockStatement.getItems(store);
        List<Component> lore = new ArrayList<>();
        for(ItemStack item : items){
            lore.add(item.displayName()
                    .append(Component.text(" -> ", TextColor.fromHexString("#e6e6e6")))
                    .append(Component.text(StockStatement.getStock(item, store)))
                    .decoration(TextDecoration.ITALIC, false)
                    .color(TextColor.fromHexString("#ffb330")));
        }
        meta.lore(lore);
        setItemMeta(meta);
    }
}
