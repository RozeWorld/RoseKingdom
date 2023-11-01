package com.rosekingdom.rosekingdom.Profiles;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;

public class GUIhandler implements Listener {
    @EventHandler
    public void movingItems(InventoryClickEvent e){
        if(e.getAction().equals(InventoryAction.MOVE_TO_OTHER_INVENTORY)){
            e.setCancelled(true);
        }
        if(e.getClickedInventory().getHolder() instanceof UserGUI){
            e.setCancelled(true);
        }
        if(e.getAction().equals(InventoryAction.COLLECT_TO_CURSOR)){
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void draggingItems(InventoryDragEvent e){
        for(int slot : e.getRawSlots()){
            if(slot <= 26){
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void openProfile(PlayerInteractEntityEvent e){
        Player player = (Player) e.getRightClicked();
        Inventory inventory = new UserGUI(player).getInventory();
        e.getPlayer().openInventory(inventory);
    }
}
