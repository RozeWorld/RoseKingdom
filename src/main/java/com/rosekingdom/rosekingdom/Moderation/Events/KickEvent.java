package com.rosekingdom.rosekingdom.Moderation.Events;

import net.kyori.adventure.text.Component;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;

public class KickEvent implements Listener {

    @EventHandler
    public void onKick(PlayerKickEvent e) {
        if(e.getCause().equals(PlayerKickEvent.Cause.KICK_COMMAND)){
            e.leaveMessage(Component.text(e.getPlayer().getName() + " was kicked! Reason: " + e.reason()));
        }
    }
}
