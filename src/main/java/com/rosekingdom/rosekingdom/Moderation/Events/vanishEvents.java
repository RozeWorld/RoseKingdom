package com.rosekingdom.rosekingdom.Moderation.Events;

import com.rosekingdom.rosekingdom.Moderation.Utils.vanish;
import com.rosekingdom.rosekingdom.RoseKingdom;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class vanishEvents implements Listener {

    @EventHandler
    private void playerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        for(Player p : vanish.getVanished()) {
            player.hidePlayer(RoseKingdom.getPlugin(RoseKingdom.class), p);
        }
    }
}
