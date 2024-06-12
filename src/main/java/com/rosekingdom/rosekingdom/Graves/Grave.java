package com.rosekingdom.rosekingdom.Graves;

import com.rosekingdom.rosekingdom.Core.Database.Main_Statements.UserStatement;
import com.rosekingdom.rosekingdom.Graves.Statements.DeathStatement;
import com.rosekingdom.rosekingdom.Graves.Statements.GraveStatement;
import com.rosekingdom.rosekingdom.RoseKingdom;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Interaction;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.ArrayList;
import java.util.List;

public class Grave {
    Player player;
    int id;
    Location location;
    ItemDisplay display;
    Interaction interaction;
    String graveId;
    int time;
    static int task;

    public Grave(Player player){
        this.player = player;
        this.id = UserStatement.getId(player.getUniqueId());
        GraveHandler.addGrave(this);
    }
    public Grave(int id, String graveId) {
        this.id = id;
        this.graveId = graveId;
        createGrave(DeathStatement.getLocation(id, graveId));
        GraveHandler.addGrave(this);
    }



    public void setupGrave(){
        Location loc = player.getLocation();
        Material block = loc.getBlock().getType();
        if(block != Material.AIR && !Tag.REPLACEABLE.isTagged(block) && !Tag.FLOWERS.isTagged(block)){
            loc.setY(loc.getBlockY()+1);
        }
        location = new Location(player.getWorld(), loc.getBlockX()+0.5, loc.getBlockY(), loc.getBlockZ()+0.5, player.getBodyYaw(), 0);
        createGrave(location);
        showPlayerGrave();
        graveId = DeathStatement.insert(player, location, display.getUniqueId(), interaction.getUniqueId());
        GraveStatement.insertInventory(id, graveId, player);
        timer(3600);

    }
    @SuppressWarnings("Experimental")
    private void createGrave(Location loc){
        //Grave
        display = (ItemDisplay) loc.getWorld().spawnEntity(loc.toCenterLocation(), EntityType.ITEM_DISPLAY);
        ItemStack item = new ItemStack(Material.PAPER);
        ItemMeta meta = item.getItemMeta();
        meta.setCustomModelData(5);
        item.setItemMeta(meta);
        display.setItemStack(item);
        display.setRotation(loc.getYaw(), 0);

        //Interaction
        interaction = (Interaction) loc.getWorld().spawnEntity(loc, EntityType.INTERACTION);
        interaction.setInteractionWidth(0.3f);

        //Change visibility
        display.setVisibleByDefault(false);
        interaction.setVisibleByDefault(false);
    }

    @SuppressWarnings("Experimental")
    public void showPlayerGrave(){
        JavaPlugin plugin = JavaPlugin.getPlugin(RoseKingdom.class);
        player.showEntity(plugin, display);
        player.showEntity(plugin, interaction);
    }

    public void timer(int duration){
        time = duration;
        BukkitScheduler scheduler = Bukkit.getScheduler();
        task = scheduler.scheduleSyncRepeatingTask(JavaPlugin.getPlugin(RoseKingdom.class), () -> {
            time--;
            if(time <= 0){
                GraveHandler.removeGrave(id, graveId);
            }
        }, 0, 20);
    }



    public static void stopTimer(){
        BukkitScheduler scheduler = Bukkit.getScheduler();
        scheduler.cancelTask(task);
    }

    public void save() {
        DeathStatement.saveTime(id, graveId, time);
        stopTimer();
    }
}
