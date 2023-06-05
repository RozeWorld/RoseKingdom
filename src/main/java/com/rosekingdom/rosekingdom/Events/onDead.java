package com.rosekingdom.rosekingdom.Events;

import com.rosekingdom.rosekingdom.Database.Statements.GraveStatement;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class onDead implements Listener {

    @EventHandler
    public void onDeadEvent(PlayerDeathEvent e) {
        Player player = e.getPlayer();
        Location loc = player.getLocation();
        player.getWorld().spawnEntity(loc, EntityType.ZOMBIE);
        for(ItemStack i : player.getInventory()){
            if(i!=null) {
                GraveStatement.insert(1, i.serializeAsBytes(), i.getAmount());
            }else continue;
        }
            player.getInventory().clear();
            player.sendMessage(Component.text("You died at ", TextColor.fromHexString("#fcc603")).append(Component.text(loc.getBlockX() + " " + loc.getBlockY() + " " + loc.getBlockZ(), TextColor.fromHexString("#d60606"))));
        }
    }
