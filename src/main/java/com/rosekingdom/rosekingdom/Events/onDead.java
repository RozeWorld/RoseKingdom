package com.rosekingdom.rosekingdom.Events;

import com.rosekingdom.rosekingdom.Database.Statements.DeathStatement;
import com.rosekingdom.rosekingdom.Database.Statements.GraveStatement;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Interaction;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.time.LocalTime;
import java.util.Timer;

public class onDead implements Listener {

    @EventHandler
    public void onDeadEvent(PlayerDeathEvent e) {
        Player player = e.getPlayer();
        Location loc = player.getLocation();
        loc = new Location(player.getWorld(), loc.getBlockX()+0.5, loc.getBlockY(), loc.getBlockZ()+0.5);

        player.sendMessage(Component.text()
                .append(Component.text("You have died on ", TextColor.fromHexString("#fcc603")))
                .append(Component.text(loc.getBlockX() + " " + loc.getBlockY() + " " + loc.getBlockZ(), TextColor.fromHexString("#d60606")))
                .append(Component.text(" in " + loc.getWorld().getName(), TextColor.fromHexString("#fcc603"))));

        if(player.getInventory().isEmpty()){
            return;
        }

        ItemStack item = new ItemStack(Material.COBBLESTONE);
        ItemMeta meta = item.getItemMeta();
        meta.setCustomModelData(1000);
        item.setItemMeta(meta);
        ItemDisplay display = (ItemDisplay) player.getWorld().spawnEntity(loc.toCenterLocation(), EntityType.ITEM_DISPLAY);
        display.setItemStack(item);
        Interaction interaction = (Interaction) e.getPlayer().getWorld().spawnEntity(loc, EntityType.INTERACTION);

        DeathStatement.insert(player, loc, LocalTime.now(),interaction.getUniqueId(), display.getUniqueId());
        GraveStatement.insertInventory(player);
    }
}
