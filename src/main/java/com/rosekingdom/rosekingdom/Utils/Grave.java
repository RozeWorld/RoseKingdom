package com.rosekingdom.rosekingdom.Utils;

import com.rosekingdom.rosekingdom.Database.Statements.GraveStatement;
import com.rosekingdom.rosekingdom.Database.Statements.UserStatement;
import com.rosekingdom.rosekingdom.RoseKingdom;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
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
    int time;
    int task;
    String graveId;
    public static List<Grave> graveList = new ArrayList<>();
    public Grave(Player player){
        this.player = player;
        this.id = UserStatement.getId(player.getUniqueId());
    }
    public Grave(int id, String graveId) {
        this.id = id;
        this.graveId = graveId;
    }

    public static void addGrave(Grave grave){
        graveList.add(grave);
    }




    public void CreateGrave(){
        Location loc = player.getLocation();
        loc = new Location(player.getWorld(), loc.getBlockX()+0.5, loc.getBlockY(), loc.getBlockZ()+0.5);


        ItemStack item = new ItemStack(Material.PAPER);
        ItemMeta meta = item.getItemMeta();
        meta.setCustomModelData(5);
        item.setItemMeta(meta);
        ItemDisplay display = (ItemDisplay) player.getWorld().spawnEntity(loc.toCenterLocation(), EntityType.ITEM_DISPLAY);
        display.setItemStack(item);
        display.setRotation(player.getBodyYaw(), 0);
        Interaction interaction = (Interaction) player.getWorld().spawnEntity(loc, EntityType.INTERACTION);

        graveId = GraveStatement.insert(player, loc, display.getUniqueId(), interaction.getUniqueId());
        GraveStatement.insertInventory(id, graveId, player);
        timer(3600);
    }

    public void timer(int duration){
        time = duration;
        BukkitScheduler scheduler = Bukkit.getScheduler();
        task = scheduler.scheduleSyncRepeatingTask(JavaPlugin.getPlugin(RoseKingdom.class), () -> {
            time--;
            if(time <= 0){
                removeGrave(id, graveId);
                scheduler.cancelTask(task);
            }
        }, 0, 20);
    }

    private void removeGrave(int id, String graveId) {
        Location loc = GraveStatement.getLocation(id, graveId);
        for(ItemStack items : GraveStatement.getItems(id, graveId)){
            if (loc != null) {
                loc.getWorld().dropItemNaturally(loc, items);
            }
        }
        GraveStatement.deleteGrave(id, graveId);
        GraveStatement.purge(id, graveId);
    }

    public void save() {
        GraveStatement.saveTime(id, graveId, time);
    }
}
