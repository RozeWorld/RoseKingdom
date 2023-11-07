package com.rosekingdom.rosekingdom.Core.Events;

import com.rosekingdom.rosekingdom.Core.Database.Main_Statements.UserStatement;
import com.rosekingdom.rosekingdom.Core.Utils.ResourcePackLoader;
import com.rosekingdom.rosekingdom.Ranks.RankSystem;
import com.rosekingdom.rosekingdom.RoseKingdom;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

public class onJoin implements Listener {
    @EventHandler
    public void onJoinEvent(PlayerJoinEvent e){
        Player player = e.getPlayer();
        new ResourcePackLoader().setResourcePack(player);

        //Join Message
        e.joinMessage(
                Component.text("[", TextColor.fromHexString("#696969"))
                        .append(Component.text("+", TextColor.fromHexString("#3fd951"))
                        .append(Component.text("] ", TextColor.fromHexString("#696969")))
                        .append(player.displayName().color(TextColor.fromHexString("#7d7d7d")))));

//        if(!player.hasPlayedBefore()){
//            player.showDemoScreen();
//        }

        player.sendPlayerListHeader(Component.text("\n\nRoseKingdom :3\n\n"));
        BukkitScheduler scheduler = Bukkit.getScheduler();
        scheduler.scheduleSyncDelayedTask(JavaPlugin.getPlugin(RoseKingdom.class), () -> {
            for(Player p : Bukkit.getServer().getOnlinePlayers()){
                p.sendPlayerListFooter(Component.text("\nOnline Players: ",TextColor.fromHexString("#FFB415"))
                        .append(Component.text(Bukkit.getOnlinePlayers().size(),TextColor.fromHexString("#2eff31"))));
            }
        }, 10);

        if(!UserStatement.exists(player.getUniqueId())) {
            UserStatement.insert(player.getName(), player.getUniqueId().toString());
        }
        RankSystem.loadRank(player);
    }
}
