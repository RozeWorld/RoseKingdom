package com.rosekingdom.rosekingdom.Core.Events;

import com.rosekingdom.rosekingdom.Core.Utils.Message;
import com.rosekingdom.rosekingdom.Tab.Kingdoms.Kingdom;
import com.rosekingdom.rosekingdom.Tab.Kingdoms.KingdomHandler;
import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;


public class onChatEvent implements Listener {
    @EventHandler
    public void AsyncChatEvent(AsyncChatEvent e){
        Player player = e.getPlayer();
        if(KingdomHandler.getKingdomChatters().contains(player.getUniqueId())){
            e.setCancelled(true);
            Kingdom kingdom = KingdomHandler.getChatterKingdom(player);
            for(Player members : kingdom.getOnlinePlayers()){
                Component message = Component.text()
                        .append(Component.text("["+kingdom.getName()+"] ", TextColor.fromHexString("#5ae630"))
                                .clickEvent(ClickEvent.clickEvent(ClickEvent.Action.RUN_COMMAND, "/kingdom chat")))
                        .append(player.name().color(TextColor.fromHexString("#c7c7c7")).clickEvent(ClickEvent.clickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/msg " + player.getName() + " ")))
                        .append(Component.text(": ",TextColor.fromHexString("#9b9b9b")))
                        .append(e.message())
                        .build();
                members.sendMessage(message);
                Message.Console(message);
            }
        }
        e.renderer((source, sourceDisplayName, message, viewer) ->
                Component.text()
                        .append(Component.text("\uDAFF\uDFFD"))
                        .append(player.displayName().clickEvent(ClickEvent.clickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/msg " + player.getName() + " ")))
                        .append(Component.text(": ",TextColor.fromHexString("#9b9b9b")))
                        .append(message)
                        .build());
    }
}
