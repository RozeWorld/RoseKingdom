package com.rosekingdom.rosekingdom.Core.Utils;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

public class Item extends ItemStack{
    private String itemName;
    private final ItemMeta itemMeta;
    private List<Component> lore;

    public Item(Material material, int amount){
        super(material, amount);
        itemMeta = getItemMeta();
        lore = itemMeta.lore();
    }

    public Item setName(String name){
        itemMeta.displayName(Component.text(name));
        return this;
    }

    public Item setCustomModelData(int modelData){
        itemMeta.setCustomModelData(modelData);
        return this;
    }

    public Item setLore(Component... lore){
        this.lore.addAll(Arrays.asList(lore));
        return this;
    }
}
