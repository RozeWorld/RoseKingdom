package com.rosekingdom.rosekingdom.Economy.GUIs;

import com.rosekingdom.rosekingdom.Core.Items.Checksymbol;
import com.rosekingdom.rosekingdom.Core.Items.Xsymbol;
import com.rosekingdom.rosekingdom.Economy.Items.sAutoPricing;
import com.rosekingdom.rosekingdom.Economy.Items.sPlus;
import com.rosekingdom.rosekingdom.Economy.Statements.PricingStatement;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class sItemStoreOptions implements InventoryHolder {

    final String store;
    final ItemStack item;
    boolean options = false;

    public sItemStoreOptions(ItemStack item, String store){
        this.store = store;
        this.item = item;
    }

    public sItemStoreOptions(ItemStack item, String store, boolean options){
        this.store = store;
        this.item = item;
        this.options = options;
    }

    @Override
    public @NotNull Inventory getInventory() {
        Inventory inventory = Bukkit.createInventory(this, 27, Component.text("store"));
        int[] slots = {2,3,4,5,6};
        for(int k : slots){
            inventory.setItem(k, new sPlus());
        }
        int i = 2;
        for(ItemStack item : PricingStatement.getItems(item, store)){
            List<Component> lore = new ArrayList<>();
            if(item.lore() != null){
                lore = new ArrayList<>(item.lore());
            }
            lore.add(4, Component.text("Right click to remove", TextColor.fromHexString("#ffac0a")));
            item.lore(lore);
            inventory.setItem(i, item);
            i++;
        }
        if(options){
            inventory.setItem(18, new Xsymbol("Back", null));
        }else {
            inventory.setItem(18, new Xsymbol("Cancel", null));
            inventory.setItem(22, new sAutoPricing());
            inventory.setItem(26, new Checksymbol("Save", null));
        }
        return inventory;
    }
}
