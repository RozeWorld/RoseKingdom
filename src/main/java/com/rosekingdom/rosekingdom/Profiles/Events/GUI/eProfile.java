package com.rosekingdom.rosekingdom.Profiles.Events.GUI;

import com.rosekingdom.rosekingdom.Profiles.UserGUI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;

public class eProfile implements Listener {
    @EventHandler
    public void movingItems(InventoryClickEvent e){
        if(e.getRawSlot() <= 26 && e.getView().getTopInventory().getHolder() instanceof UserGUI){
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
        if(e.getRightClicked() instanceof Player player && player.isSneaking()){
            Inventory inventory = new UserGUI(player).getInventory();
            e.getPlayer().openInventory(inventory);
        }
    }
}
