package com.rosekingdom.rosekingdom.Events;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;


public class onRightClick implements  Listener {

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onRightClickEvent(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        if(event.getRightClicked().getType()== EntityType.INTERACTION){
            event.setCancelled(true);
            player.openInventory(createInventory(player));
        }
    }

    public Inventory createInventory(Player player){
        return Bukkit.createInventory(null, 45, player.displayName().append(Component.text(" Grave")));
    }
}
