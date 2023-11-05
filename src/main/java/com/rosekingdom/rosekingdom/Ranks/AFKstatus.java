package com.rosekingdom.rosekingdom.Ranks;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.HashMap;
import java.util.Map;

public class AFKstatus implements Listener {

    static HashMap<Player, Long> lastMoved = new HashMap<>();

    public static void check(JavaPlugin plugin){
        BukkitScheduler scheduler = Bukkit.getScheduler();
        scheduler.runTaskTimerAsynchronously(plugin, () -> {
            for(Map.Entry<Player, Long> moved : lastMoved.entrySet()){
                if(System.currentTimeMillis()-moved.getValue() >= 5 * 60 * 1000){
                    Player player = moved.getKey();
                    RankSystem.setStatusAFK(player);
                    player.sendMessage(Component.text("You are now AFK!"));
                    lastMoved.remove(player);
                }
            }
        }, 0, 60 * 20);
    }
    @EventHandler(priority = EventPriority.LOW)
    public void playerMoved(PlayerMoveEvent e){
        lastMoved.put(e.getPlayer(), System.currentTimeMillis());
        RankSystem.removeStatusAFK(e.getPlayer());
    }
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        lastMoved.remove(e.getPlayer());
    }
}
