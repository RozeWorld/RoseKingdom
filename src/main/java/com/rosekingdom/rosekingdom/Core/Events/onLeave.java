package com.rosekingdom.rosekingdom.Core.Events;

import com.rosekingdom.rosekingdom.Core.Utils.Message;
import com.rosekingdom.rosekingdom.Tab.Kingdoms.Kingdom;
import com.rosekingdom.rosekingdom.Tab.Kingdoms.KingdomHandler;
import com.rosekingdom.rosekingdom.Tab.Tab;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class onLeave implements Listener {

    @EventHandler
    public void onLeaveEvent(PlayerQuitEvent e){
        Player player = e.getPlayer();
        e.quitMessage(Component.text("[", TextColor.fromHexString("#696969"))
                .append(Component.text("-", TextColor.fromHexString("#d90d12")))
                .append(Component.text("] ", TextColor.fromHexString("#696969")))
                .append(Component.text(player.getName(), TextColor.fromHexString("#7d7d7d"))));

        Tab.updatePlayerCount();

        if(KingdomHandler.isInKingdom(player)){
            Kingdom kingdom = KingdomHandler.getKingdom(player);
            if(kingdom == null) {
                Message.Console("Couldn't fetch kingdom!");
                return;
            }
            KingdomHandler.lastOnline(kingdom);
        }
    }
}
