package com.rosekingdom.rosekingdom.Economy.GUIs;

import com.rosekingdom.rosekingdom.Economy.Statements.StockStatement;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class sRemoveSelector implements InventoryHolder {

    String store;
    public sRemoveSelector(String store){
        this.store = store;
    }

    @Override
    public @NotNull Inventory getInventory() {
        Inventory inventory = Bukkit.createInventory(this, 9, Component.text("\u00A7f\uDAFF\uDFF8\uEE06"));
        List<ItemStack> items = StockStatement.getItems(store);
        switch (items.size()) {
            case 1 -> inventory.setItem(4, items.get(0));
            case 2 -> {
                inventory.setItem(3, items.get(0));
                inventory.setItem(5, items.get(1));
            }
            case 3 -> {
                inventory.setItem(3, items.get(0));
                inventory.setItem(4, items.get(1));
                inventory.setItem(5, items.get(2));
            }
            case 4 -> {
                inventory.setItem(2, items.get(0));
                inventory.setItem(3, items.get(1));
                inventory.setItem(5, items.get(2));
                inventory.setItem(6, items.get(3));
            }
            case 5 -> {
                inventory.setItem(2, items.get(0));
                inventory.setItem(3, items.get(1));
                inventory.setItem(4, items.get(2));
                inventory.setItem(5, items.get(3));
                inventory.setItem(6, items.get(4));
            }
            case 6 -> {
                inventory.setItem(1, items.get(0));
                inventory.setItem(2, items.get(0));
                inventory.setItem(3, items.get(1));
                inventory.setItem(5, items.get(2));
                inventory.setItem(6, items.get(3));
                inventory.setItem(7, items.get(4));
            }
        }
        return inventory;
    }
}
