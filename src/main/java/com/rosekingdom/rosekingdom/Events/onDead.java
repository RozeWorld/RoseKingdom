package com.rosekingdom.rosekingdom.Events;

import com.rosekingdom.rosekingdom.Database.Statements.GraveStatement;
import com.rosekingdom.rosekingdom.Database.Statements.UserStatement;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import java.sql.SQLException;
import java.util.Arrays;

public class onDead implements Listener {

    @EventHandler
    public void onDeadEvent(PlayerDeathEvent e) {
        Player player = e.getPlayer();
        Location loc = player.getLocation();

        ItemStack item = new ItemStack(Material.COBBLESTONE);
        ItemMeta meta = item.getItemMeta();
        meta.setCustomModelData(1000);
        item.setItemMeta(meta);
        ItemDisplay display = (ItemDisplay) player.getWorld().spawnEntity(loc.toCenterLocation(), EntityType.ITEM_DISPLAY);
        display.setItemStack(item);
        for(ItemStack i : player.getInventory()){
            if(i!=null) {
                GraveStatement.insert(UserStatement.getId(player.getUniqueId()), i.serializeAsBytes());
            }else continue;
        }
        player.sendMessage(Component.text("You died at ", TextColor.fromHexString("#fcc603")).append(Component.text(loc.getBlockX() + " " + loc.getBlockY() + " " + loc.getBlockZ(), TextColor.fromHexString("#d60606"))));

    }
}
