package com.rosekingdom.rosekingdom.Economy.Events;

import com.rosekingdom.rosekingdom.Economy.GUIs.MerchantGUI;
import com.rosekingdom.rosekingdom.Economy.Statements.EconomyStatement;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;

public class TestHandler implements Listener {
    @EventHandler
    public void movingItems(InventoryClickEvent e) {
        if (e.getAction().equals(InventoryAction.MOVE_TO_OTHER_INVENTORY) && e.getView().getTopInventory().getHolder() instanceof MerchantGUI) {
            EconomyStatement.removeCoins((OfflinePlayer) e.getWhoClicked(), 12);
            e.setCancelled(true);
        }
        if (e.getView().getTopInventory().getHolder() instanceof MerchantGUI) {
            EconomyStatement.removeCoins((OfflinePlayer) e.getWhoClicked(), 12);
            e.setCancelled(true);
        }
        if (e.getAction().equals(InventoryAction.COLLECT_TO_CURSOR) && e.getView().getTopInventory().getHolder() instanceof MerchantGUI) {
            EconomyStatement.removeCoins((OfflinePlayer) e.getWhoClicked(), 12);
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void draggingItems(InventoryDragEvent e) {
        if (e.getInventory().getHolder() instanceof MerchantGUI) {
            for (int slot : e.getRawSlots()) {
                if (slot <= 26) {
                    e.setCancelled(true);
                }
            }
        }
    }
}