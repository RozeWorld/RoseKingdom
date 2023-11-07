package com.rosekingdom.rosekingdom.Profiles.Events;

import com.rosekingdom.rosekingdom.Profiles.Statements.ProfileStatement;
import com.rosekingdom.rosekingdom.RoseKingdom;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

public class DailyStreakChecker implements Listener {
    @EventHandler
    public void checkIfPlayerPlayedToday(PlayerJoinEvent e){
        Player p = e.getPlayer();
        if(!ProfileStatement.exists(p)){
            ProfileStatement.createProfile(p, System.currentTimeMillis());
        }

        long lastOnline = ProfileStatement.getLast_Online(p);
        Instant instant = Instant.ofEpochMilli(lastOnline);
        LocalDate lastActiveOn = instant.atZone(ZoneId.systemDefault()).toLocalDate();

        if(!LocalDate.now().equals(lastActiveOn) && LocalDate.now().minusDays(1).equals(lastActiveOn)){
            isActive(p);
        }else{
            ProfileStatement.updateStreak(p, 1);
        }
    }

    @EventHandler
    public void updateLastOnline(PlayerQuitEvent e){
        Player p = e.getPlayer();
        ProfileStatement.setLastOnline(p, System.currentTimeMillis());
    }

    private void isActive(Player player){
        BukkitScheduler scheduler = Bukkit.getScheduler();
        scheduler.runTaskLater(JavaPlugin.getPlugin(RoseKingdom.class), () -> {
            if(player.isOnline()){
                int score = ProfileStatement.getStreak(player);
                int highscore = ProfileStatement.getHighscore(player);
                score++;
                ProfileStatement.updateStreak(player, score);
                if(highscore < score){
                    ProfileStatement.setHighscore(player, score);
                }
            }
        }, 10 * 60 * 20);
    }
}
