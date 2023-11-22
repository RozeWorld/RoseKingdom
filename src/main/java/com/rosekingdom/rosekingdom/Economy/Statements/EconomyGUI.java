package com.rosekingdom.rosekingdom.Economy.Statements;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class EconomyGUI implements InventoryHolder {
    @Override
    public @NotNull Inventory getInventory() {
        Inventory shop = Bukkit.createInventory(this ,18,"Shop");
        shop.addItem(new ItemStack(Material.DIAMOND));
        return shop;
    }
}
