package com.rosekingdom.rosekingdom.Events;

import com.rosekingdom.rosekingdom.Database.Statements.DeathStatement;
import com.rosekingdom.rosekingdom.Database.Statements.GraveStatement;
import com.rosekingdom.rosekingdom.Database.Statements.UserStatement;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.GameEvent;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;


public class onRightClick implements  Listener {

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onRightClickEvent(PlayerInteractEntityEvent e) {
        Player player = e.getPlayer();
        Entity grave = e.getRightClicked();

        if(grave.getType() == EntityType.INTERACTION){
            for(Location l : DeathStatement.getLocation(player)){
                player.openInventory(grave(player));
            }
            e.setCancelled(true);
        }
    }

    private void addItems(Inventory inventory, Player player){
        for(ItemStack i : Objects.requireNonNull(GraveStatement.getItems(player))){
            inventory.addItem(i);
        }
    }

    public Inventory grave(Player player){
        Inventory inventory = Bukkit.createInventory(null, 45,
                player.displayName()
                        .append(Component.text(" Grave ")
                        .append(Component.text(GraveStatement.getGraves(UserStatement.getId(player.getUniqueId()))))));
        addItems(inventory, player);
        return inventory;
    }
}
