package com.rosekingdom.rosekingdom.Economy.GUIs;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;

public class sAddItem implements InventoryHolder {
    @Override
    public @NotNull Inventory getInventory() {
        return Bukkit.createInventory(this, 9, Component.text("ui image"));
    }

}
