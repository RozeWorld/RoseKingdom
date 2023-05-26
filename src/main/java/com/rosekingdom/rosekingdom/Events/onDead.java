package com.rosekingdom.rosekingdom.Events;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class onDead implements Listener {

    @EventHandler
    public void onDeadEvent(PlayerDeathEvent e) {
        Player player = e.getPlayer();
        Location loc = player.getLocation();
//        Block block = player.getWorld().getBlockAt(loc);
//        block.setType(Material.CHEST);
//        Chest chest = (Chest) block.getState();
//        chest.getBlockInventory().addItem(player.getInventory().getItemInMainHand());
//        player.getWorld().setBlockData(loc, chest.getBlockData());
        player.sendMessage(Component.text("You died at ", TextColor.fromHexString("#fcc603")).append(Component.text(loc.getBlockX() + " " + loc.getBlockY() + " " + loc.getBlockZ(), TextColor.fromHexString("#d60606"))));
    }
}
