package com.rosekingdom.rosekingdom.Core.Events;

import com.rosekingdom.rosekingdom.Core.Database.Main_Statements.UserStatement;
import com.rosekingdom.rosekingdom.Core.Utils.ResourcePackLoader;
import com.rosekingdom.rosekingdom.Economy.Statements.EconomyStatement;
import com.rosekingdom.rosekingdom.Graves.Grave;
import com.rosekingdom.rosekingdom.Graves.Statements.DeathStatement;
import com.rosekingdom.rosekingdom.Profiles.Statements.ProfileStatement;
import com.rosekingdom.rosekingdom.Ranks.RankSystem;
import com.rosekingdom.rosekingdom.RoseKingdom;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

public class onJoin implements Listener {
    @EventHandler(priority = EventPriority.HIGH)
    public void onJoinEvent(PlayerJoinEvent e){
        Player player = e.getPlayer();
        new ResourcePackLoader().setResourcePack(player);

        //Join Message
        e.joinMessage(
                Component.text("[", TextColor.fromHexString("#696969"))
                        .append(Component.text("+", TextColor.fromHexString("#3fd951"))
                        .append(Component.text("] ", TextColor.fromHexString("#696969")))
                        .append(player.displayName().color(TextColor.fromHexString("#7d7d7d")))));
        player.sendPlayerListHeader(Component.text("\uEF02\n\n\n\n"));
        BukkitScheduler scheduler = Bukkit.getScheduler();
        scheduler.scheduleSyncDelayedTask(JavaPlugin.getPlugin(RoseKingdom.class), () -> {
            for(Player on : Bukkit.getServer().getOnlinePlayers()){
                on.sendPlayerListFooter(Component.text("\nOnline Players: ", TextColor.fromHexString("#FFB415"))
                        .append(Component.text(Bukkit.getOnlinePlayers().size(),TextColor.fromHexString("#2eff31"))));
            }
        }, 10);

        //Database things
        if(!UserStatement.exists(player.getUniqueId())) {
            UserStatement.insert(player.getName(), player.getUniqueId().toString());
            EconomyStatement.insert(player);
            ProfileStatement.createProfile(player);
        }
        //Rank
        RankSystem.loadRank(player);

        //Activity Streak Checker
        long lastOnline = player.getLastSeen();
        Instant instant = Instant.ofEpochMilli(lastOnline);
        LocalDate lastActiveOn = instant.atZone(ZoneId.systemDefault()).toLocalDate();

        if(!LocalDate.now().equals(lastActiveOn) && LocalDate.now().minusDays(1).equals(lastActiveOn)){
            isActive(player);
        }else{
            ProfileStatement.updateStreak(player, 1);
        }

        //Load Graves (if any)
        int id = UserStatement.getId(player);
        if(DeathStatement.hasGraves(id)){
            for(Grave grave : Grave.getGraves(id)){
                grave.showPlayerGrave(player);
            }
        }
    }


    //TODO: Change the way of counting and checking if a player was active that day for a sertan time
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
