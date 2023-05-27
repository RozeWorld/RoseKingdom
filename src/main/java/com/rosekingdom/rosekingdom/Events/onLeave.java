package com.rosekingdom.rosekingdom.Events;

import com.rosekingdom.rosekingdom.RoseKingdom;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class onLeave implements Listener {

    @EventHandler
    public void onLeaveEvent(PlayerQuitEvent e){
        Player player = e.getPlayer();
        RoseKingdom.players--;
        for(Player p : Bukkit.getServer().getOnlinePlayers()){
            p.sendPlayerListFooter(Component.text("\nOnline Players: ",TextColor.fromHexString("#FFB415"))
                    .append(Component.text(RoseKingdom.players,TextColor.fromHexString("#2eff31"))));
        }
        e.quitMessage(Component.text("[", TextColor.fromHexString("#696969"))
                        .append(Component.text("-", TextColor.fromHexString("#d90d12"))
                        .append(Component.text("] ", TextColor.fromHexString("#696969")))
                        .append(player.displayName().color(TextColor.fromHexString("#7d7d7d")))));
    }

}
