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
        if(e.getAction().equals(InventoryAction.MOVE_TO_OTHER_INVENTORY) && e.getView().getTopInventory().getHolder() instanceof UserGUI){
            e.setCancelled(true);
        }
        if(e.getView().getTopInventory().getHolder() instanceof UserGUI){
            e.setCancelled(true);
        }
        if(e.getAction().equals(InventoryAction.COLLECT_TO_CURSOR) && e.getView().getTopInventory().getHolder() instanceof UserGUI){
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void draggingItems(InventoryDragEvent e){
        if(e.getInventory().getHolder() instanceof UserGUI){
            for(int slot : e.getRawSlots()){
                if(slot <= 26){
                    e.setCancelled(true);
                }
            }
        }

    }

    @EventHandler
    public void openProfile(PlayerInteractEntityEvent e){
        if(e.getRightClicked() instanceof Player player){
            Inventory inventory = new UserGUI(player).getInventory();
            e.getPlayer().openInventory(inventory);
        }
    }
}