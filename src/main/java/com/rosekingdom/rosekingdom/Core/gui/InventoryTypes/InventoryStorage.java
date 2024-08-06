package com.rosekingdom.rosekingdom.Core.gui.InventoryTypes;

import com.rosekingdom.rosekingdom.Core.gui.InventoryHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public abstract class InventoryStorage implements InventoryHandler {
    private final Inventory inventory;
    private final Map<Integer, ItemStack> itemMap = new HashMap<>();

    protected InventoryStorage() {
        this.inventory = this.createInventory();
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    public void decorate(Player player) {
        this.itemMap.forEach(this.inventory::setItem);
    }

    public void addItem(int slot, ItemStack item) {
        this.itemMap.put(slot, item);
    }

    @Override
    public void onClick(InventoryClickEvent event) {
    }

    @Override
    public void onClose(InventoryCloseEvent event) {

    }

    @Override
    public void onOpen(InventoryOpenEvent event) {
        this.decorate((Player) event.getPlayer());
    }
    protected abstract Inventory createInventory();
}
