package com.rosekingdom.rosekingdom.Events;

import com.rosekingdom.rosekingdom.Database.Statements.DeathStatement;
import com.rosekingdom.rosekingdom.Database.Statements.GraveStatement;
import com.rosekingdom.rosekingdom.GUI.Grave;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import java.util.Objects;


public class onGraveClick implements  Listener {

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onInteract(PlayerInteractEntityEvent e) {
        Player player = e.getPlayer();
        Entity clicked = e.getRightClicked();
        Location gr = clicked.getLocation();
        if(clicked.getType() == EntityType.INTERACTION){
            if(DeathStatement.exists(player) && DeathStatement.hasDiedOnLocation(player, gr)){
                Grave grave = new Grave(player, DeathStatement.getGraveNum(player, gr));
                player.openInventory(grave.getInventory());
            }
            e.setCancelled(true);
        }
    }


    //TODO:get inventory on close and save it if there is any change
    @EventHandler
    public void graveClose(InventoryCloseEvent e){
        Player player = (Player) e.getPlayer();
        if(e.getInventory().getHolder() instanceof Grave grave){
            GraveStatement.UpdateInventory(player, e.getInventory(), grave.getGrave_num());
            if(e.getInventory().isEmpty()){
                Objects.requireNonNull(player.getWorld().getEntity(Objects.requireNonNull(DeathStatement.getInteraction(player, grave.getGrave_num())))).remove();
                Objects.requireNonNull(player.getWorld().getEntity(Objects.requireNonNull(DeathStatement.getDisplayGrave(player, grave.getGrave_num())))).remove();
                DeathStatement.deleteDeath(player, grave.getGrave_num());
            }
        }
    }
}
