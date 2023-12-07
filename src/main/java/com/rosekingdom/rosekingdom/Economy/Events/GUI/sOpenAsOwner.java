package com.rosekingdom.rosekingdom.Economy.Events.GUI;

import com.rosekingdom.rosekingdom.Economy.Statements.StoreStatement;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class sOpenAsOwner implements Listener {
    @EventHandler
    public void onMerchantClick(PlayerInteractEntityEvent e){
        Player player = e.getPlayer();
        Entity entity = e.getRightClicked();
        if(StoreStatement.isStore(entity.getUniqueId()) && StoreStatement.owner(player)){
            //TODO: Open Merchant GUI
        }
    }
}
