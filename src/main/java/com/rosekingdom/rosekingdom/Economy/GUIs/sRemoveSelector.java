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
        Inventory inventory = Bukkit.createInventory(this, 9, Component.text(""));
        List<ItemStack> items = StockStatement.getItems(store);
        int[] slots = {1,2,3,5,6,7};
        int i = 0;
        for(ItemStack item : items){
            inventory.setItem(slots[i], item);
            i++;
        }
        return inventory;
    }
}
