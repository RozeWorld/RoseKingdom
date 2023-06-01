package com.rosekingdom.rosekingdom.Events;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;


public class onRightClick implements  Listener {
    public static Inventory customInventory = Bukkit.createInventory(null, 45, "Custom Inv");

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onRightClickEvent(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        if(event.getRightClicked().getType()== EntityType.ZOMBIE){
            event.setCancelled(true);
            player.openInventory(customInventory);
        }
    }
}
