package com.rosekingdom.rosekingdom.Economy.Events.GUI;

import com.rosekingdom.rosekingdom.Economy.GUIs.Store;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;

public class eOpenAsPlayer implements Listener {
    @EventHandler
    public void movingItems(InventoryClickEvent e) {
        if (e.getRawSlot() < 45 && e.getView().getTopInventory().getHolder() instanceof Store) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void draggingItems(InventoryDragEvent e) {
        if (e.getInventory().getHolder() instanceof Store) {
            for (int slot : e.getRawSlots()) {
                if (slot < 45) {
                    e.setCancelled(true);
                }
            }
        }
    }
}