package com.rosekingdom.rosekingdom.Economy.Events.GUI;

import com.rosekingdom.rosekingdom.Economy.GUIs.Merchant;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;

public class eMerchant implements Listener {
    @EventHandler
    public void movingItems(InventoryClickEvent e) {
        if (e.getView().getTopInventory().getHolder() instanceof Merchant) {
            e.setCancelled(true);
        }
        if (e.getAction().equals(InventoryAction.MOVE_TO_OTHER_INVENTORY) && e.getView().getTopInventory().getHolder() instanceof Merchant) {
            e.setCancelled(true);
        }

        if (e.getAction().equals(InventoryAction.COLLECT_TO_CURSOR) && e.getView().getTopInventory().getHolder() instanceof Merchant) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void draggingItems(InventoryDragEvent e) {
        if (e.getInventory().getHolder() instanceof Merchant) {
            for (int slot : e.getRawSlots()) {
                if (slot <= 26) {
                    e.setCancelled(true);
                }
            }
        }
    }
}