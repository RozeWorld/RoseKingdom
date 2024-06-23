package com.rosekingdom.rosekingdom.Tab;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AFKstatus implements Listener {

    static HashMap<Player, Long> lastMoved = new HashMap<>();

    public static void check(JavaPlugin plugin){
        List<Player> forRemoval = new ArrayList<>();
        BukkitScheduler scheduler = Bukkit.getScheduler();
        scheduler.runTaskTimerAsynchronously(plugin, () -> {
            for(Map.Entry<Player, Long> moved : lastMoved.entrySet()){
                if((System.currentTimeMillis()-moved.getValue()) >= 3 * 60 * 1000){
                    Player player = moved.getKey();
                    RankHandler.setStatusAFK(player);
                    forRemoval.add(player);
                }
            }
            for(Player player : forRemoval){
                lastMoved.remove(player);
            }
            forRemoval.clear();
        }, 0, 30 * 20);
    }

    public static void setAFK(Player player) {
        lastMoved.remove(player);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void playerMoved(PlayerMoveEvent e){
        if(!lastMoved.containsKey(e.getPlayer())){
            RankHandler.removeStatusAFK(e.getPlayer());
        }
        lastMoved.put(e.getPlayer(), System.currentTimeMillis());
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerQuit(PlayerQuitEvent e) {
        lastMoved.remove(e.getPlayer());
    }
}
