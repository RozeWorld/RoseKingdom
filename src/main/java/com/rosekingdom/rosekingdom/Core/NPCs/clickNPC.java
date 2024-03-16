package com.rosekingdom.rosekingdom.Core.NPCs;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class clickNPC implements Listener {
    @EventHandler
    public void onClickedNPC(PlayerInteractEntityEvent e){
        Player player = e.getPlayer();
        player.sendMessage("Clicked Entity!");
    }
}
