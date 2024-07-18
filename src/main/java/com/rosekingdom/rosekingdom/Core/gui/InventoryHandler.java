package com.rosekingdom.rosekingdom.Core.gui;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;

public interface InventoryHandler {
    void onClick(InventoryClickEvent event);
    void onClose(InventoryCloseEvent event);
    void onOpen(InventoryOpenEvent event);
}
