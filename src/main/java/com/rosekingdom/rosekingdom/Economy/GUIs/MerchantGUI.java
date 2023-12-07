package com.rosekingdom.rosekingdom.Economy.GUIs;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;

public class MerchantGUI implements InventoryHolder {
    @Override
    public @NotNull Inventory getInventory() {
        Inventory shop = Bukkit.createInventory(this ,45, Component.text("Merchant Menu"));
        return shop;
    }
}
