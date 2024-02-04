package com.rosekingdom.rosekingdom.Economy.GUIs;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class Store implements InventoryHolder {

    String store;
    Set<ItemStack> items;

    public Store(String store, Set<ItemStack> items){
        this.store = store;
        this.items = items;
    }

    @Override
    public @NotNull Inventory getInventory() {
        Inventory inventory = Bukkit.createInventory(this, 27, Component.text("\u00A7f\uDAFF\uDFF8\uEE01"));
        int[] slots = {1,2,3,19,20,21};
        int i = 0;
        for(ItemStack item : items){
            inventory.setItem(slots[i], item);
            i++;
        }
        return inventory;
    }
}
