package com.rosekingdom.rosekingdom.Events;

import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class onChatEvent implements Listener {
    @EventHandler
    public void AsyncChatEvent(AsyncChatEvent e){
        e.renderer((source, sourceDisplayName, message, viewer) ->
                Component.text()
                        .append(e.getPlayer().displayName())
                        .append(Component.text(": ",TextColor.fromHexString("#9b9b9b")))
                        .append(message)
                        .build());
    }
}
