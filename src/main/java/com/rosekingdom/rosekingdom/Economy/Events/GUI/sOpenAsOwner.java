package com.rosekingdom.rosekingdom.Economy.Events.GUI;

import com.rosekingdom.rosekingdom.Economy.GUIs.Merchant;
import com.rosekingdom.rosekingdom.Economy.GUIs.sAddItem;
import com.rosekingdom.rosekingdom.Economy.Statements.StoreStatement;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class sOpenAsOwner implements Listener {
    @EventHandler
    public void movingItems(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        if (e.getRawSlot() < 45 && e.getView().getTopInventory().getHolder() instanceof Merchant) {
            e.setCancelled(true);
        }
        if(e.getRawSlot() == 15 && e.getView().getTopInventory().getHolder() instanceof Merchant){
            player.openInventory(new sAddItem().getInventory());
        }
    }

    @EventHandler
    public void draggingItems(InventoryDragEvent e) {
        if (e.getInventory().getHolder() instanceof Merchant) {
            for (int slot : e.getRawSlots()) {
                if (slot < 45) {
                    e.setCancelled(true);
                }
            }
        }
    }
    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOW)
    public void onMerchantClick(PlayerInteractEntityEvent e){
        Player player = e.getPlayer();
        Entity entity = e.getRightClicked();
        if(StoreStatement.isStore(entity.getUniqueId()) && StoreStatement.owner(player, entity.getUniqueId())){
            player.openInventory(new Merchant().getInventory());
        }
    }
}
