package com.rosekingdom.rosekingdom.Graves;

import com.rosekingdom.rosekingdom.Core.Database.Main_Statements.UserStatement;
import com.rosekingdom.rosekingdom.Core.NPCs.NPC;
import com.rosekingdom.rosekingdom.Core.NPCs.NPCHandler;
import com.rosekingdom.rosekingdom.Graves.Statements.DeathStatement;
import com.rosekingdom.rosekingdom.Graves.Statements.GraveStatement;
import io.papermc.paper.event.packet.PlayerChunkLoadEvent;
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

public class GraveEvents implements Listener {

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
            grave.setupGrave();
        }
    }

    @EventHandler
    public void onChunkUpdate(PlayerChunkLoadEvent e){
        for(Grave grave : GraveHandler.getGraveList()){
            if(grave.location.getChunk().equals(e.getChunk())){
                grave.showPlayerGrave();
            }
        }
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onInteract(PlayerInteractEntityEvent e) {
        Player player = e.getPlayer();
        Entity clicked = e.getRightClicked();
        if(clicked.getType() == EntityType.INTERACTION && DeathStatement.isGrave(clicked.getLocation())){
            GraveGUI gui = new GraveGUI(player, DeathStatement.getGraveId(clicked));
            player.openInventory(gui.getInventory());
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void graveClose(InventoryCloseEvent e){
        if(e.getInventory().getHolder() instanceof GraveGUI grave){
            String graveID = grave.getGraveId();
            int id = grave.id;
            GraveStatement.UpdateInventory(id, e.getInventory(), graveID);
            if(e.getInventory().isEmpty()){
                GraveHandler.removeGrave(id, graveID);
            }
        }
    }
}
