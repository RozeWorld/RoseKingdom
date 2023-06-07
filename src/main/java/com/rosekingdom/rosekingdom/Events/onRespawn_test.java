package com.rosekingdom.rosekingdom.Events;


import com.rosekingdom.rosekingdom.Database.Statements.GraveStatement;
import com.rosekingdom.rosekingdom.Database.Statements.UserStatement;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

public class onRespawn_test implements Listener {

    @EventHandler
    public void onRespawn(PlayerRespawnEvent e){
        Player player = e.getPlayer();
        int n = GraveStatement.items(player,1);
        player.sendMessage(Component.text(n));
//        for(int k = 0; k<n;k++){
//            player.getInventory().addItem(ItemStack.deserializeBytes(GraveStatement.getData(UserStatement.getId(player.getUniqueId()))));
//        }
    }
}
