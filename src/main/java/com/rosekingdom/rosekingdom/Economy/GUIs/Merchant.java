package com.rosekingdom.rosekingdom.Economy.GUIs;

import com.rosekingdom.rosekingdom.Economy.Items.sAdd;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;

public class Merchant implements InventoryHolder {
    @Override
    public @NotNull Inventory getInventory() {
        Inventory inventory = Bukkit.createInventory(this,45, Component.text("Merchant Menu"));
        inventory.setItem(15, new sAdd());
        return inventory;
    }
}
