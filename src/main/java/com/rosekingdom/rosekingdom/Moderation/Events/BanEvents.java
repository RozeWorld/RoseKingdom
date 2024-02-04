package com.rosekingdom.rosekingdom.Moderation.Events;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.BanEntry;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerLoginEvent;

public class BanEvents implements Listener {
    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent e){
        if(e.getResult().equals(PlayerLoginEvent.Result.KICK_BANNED)){
            boolean permBan;
            for(BanEntry<?> entry: Bukkit.getBanList(BanList.Type.PROFILE).getEntries()){
                if(e.getPlayer().getPlayerProfile().equals(entry.getBanTarget())){
                    permBan = entry.getExpiration() != null;
                    e.kickMessage(Component.text("============================================\n\n", TextColor.fromHexString("#E36414"))
                            .append(Component.text("Banned until: ", TextColor.fromHexString("#FB8B24")))
                            .append(Component.text((permBan ? entry.getExpiration().toString() : "Permanently") + "\n", TextColor.fromHexString("#9A031E")))
                            .append(Component.text("Reason: ", TextColor.fromHexString("#FB8B24")))
                            .append(Component.text(entry.getReason() + "\n", TextColor.fromHexString("#9A031E")))
                            .append(Component.text("By: ", TextColor.fromHexString("#FB8B24")))
                            .append(Component.text(entry.getSource() + "\n\n", TextColor.fromHexString("#9A031E")))
                            .append(Component.text("============================================\n\n", TextColor.fromHexString("#E36414"))));
                }
            }
        }
    }
    @EventHandler
    public void onKick(PlayerKickEvent e){
        e.getPlayer().kick(Component.text("============================================\n\n", TextColor.fromHexString("#E36414"))
                .append(Component.text("You were struck by the ban hammer!\n\n", TextColor.fromHexString("#FB8B24")))
                .append(Component.text("============================================", TextColor.fromHexString("#E36414"))), PlayerKickEvent.Cause.BANNED);
    }
}
