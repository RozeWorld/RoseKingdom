package com.rosekingdom.rosekingdom.Economy.Events.GUI;

import com.rosekingdom.rosekingdom.Economy.GUIs.Merchant;
import com.rosekingdom.rosekingdom.Economy.GUIs.Store;
import com.rosekingdom.rosekingdom.Economy.GUIs.sAddItem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;

public class eAddItem implements Listener {
    @EventHandler
    public void movingItems(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        if (e.getView().getTopInventory().getHolder() instanceof sAddItem) {
            e.setCancelled(true);
        }
        if (e.getAction().equals(InventoryAction.MOVE_TO_OTHER_INVENTORY) && e.getView().getTopInventory().getHolder() instanceof Store) {
            e.setCancelled(true);
        }

    }
}
