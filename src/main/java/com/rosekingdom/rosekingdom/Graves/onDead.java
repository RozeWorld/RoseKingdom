package com.rosekingdom.rosekingdom.Graves;

import com.rosekingdom.rosekingdom.Core.Database.Main_Statements.UserStatement;
import com.rosekingdom.rosekingdom.Graves.Statements.DeathStatement;
import com.rosekingdom.rosekingdom.Graves.Statements.GraveStatement;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class onDead implements Listener {

    @EventHandler
    public void onDeadEvent(PlayerDeathEvent e) {
        Player player = e.getPlayer();
        Location rawLocation = player.getLocation();
        String location = rawLocation.getBlockX() + " " + rawLocation.getBlockY() + " " + rawLocation.getBlockZ();
        String world = "Unknown";

        switch (rawLocation.getWorld().getEnvironment()){
            case NORMAL -> world = "Overworld";
            case NETHER -> world = "Nether";
            case THE_END -> world = "End";
        }
        
        player.sendMessage(Component.text()
                .append(Component.text("You have died on ", TextColor.fromHexString("#fcc603")))
                .append(Component.text(location, TextColor.fromHexString("#d60606")).clickEvent(ClickEvent.copyToClipboard(location)))
                .append(Component.text(" in " + world, TextColor.fromHexString("#fcc603"))));

        if(player.getInventory().isEmpty()){
            return;
        }

        if(DeathStatement.total_graves(player) != 3){
            e.getDrops().clear();

            Grave grave = new Grave(player);
            grave.CreateGrave();
            Grave.addGrave(grave);
        }
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onInteract(PlayerInteractEntityEvent e) {
        Player player = e.getPlayer();
        Entity clicked = e.getRightClicked();
        if(clicked.getType() == EntityType.INTERACTION && DeathStatement.isGrave(clicked.getLocation())){
            GraveGUI gui = new GraveGUI(player, DeathStatement.getGrave(clicked));
            player.openInventory(gui.getInventory());
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void graveClose(InventoryCloseEvent e){
        Player player = (Player) e.getPlayer();
        int id = UserStatement.getId(player.getUniqueId());
        if(e.getInventory().getHolder() instanceof GraveGUI grave){
            GraveStatement.UpdateInventory(id, e.getInventory(), grave.getGraveId());
            if(e.getInventory().isEmpty()){
                DeathStatement.purge(id, grave.getGraveId());
            }
        }
    }
}
