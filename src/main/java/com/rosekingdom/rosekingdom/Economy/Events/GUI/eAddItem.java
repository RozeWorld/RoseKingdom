package com.rosekingdom.rosekingdom.Economy.Events.GUI;

import com.rosekingdom.rosekingdom.Economy.GUIs.sAddItem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;

public class eAddItem implements Listener {
    @EventHandler
    public void movingItems(InventoryClickEvent e) {
        //TODO: somehow fix picking the items from the players inv
        Player player = (Player) e.getWhoClicked();
        if (e.getRawSlot() != 4 && e.getView().getTopInventory().getHolder() instanceof sAddItem) {
            e.setCancelled(true);
        }
        if (e.getAction().equals(InventoryAction.PLACE_ALL) && e.getRawSlot() == 4 && e.getView().getTopInventory().getHolder() instanceof sAddItem){
            player.closeInventory();
        }
    }
    @EventHandler
    public void draggingItems(InventoryDragEvent e) {
        if (e.getInventory().getHolder() instanceof sAddItem) {
            for (int slot : e.getRawSlots()) {
                if (slot < 9) {
                    e.setCancelled(true);
                }
            }
        }
    }
}
