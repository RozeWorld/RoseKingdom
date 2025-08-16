package com.rosekingdom.rosekingdom.Core.Events;


import com.rosekingdom.rosekingdom.RoseKingdom;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.block.Chest;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.DoubleChestInventory;

public class onChestPlace implements Listener {

    @EventHandler
    public void onChestPlacement(BlockPlaceEvent e) {
        if(!(e.getBlockPlaced().getState() instanceof Chest placed)) return;

        Component chestName = placed.customName();

        Bukkit.getServer().getScheduler().runTask(RoseKingdom.getPlugin(RoseKingdom.class), () -> {
            if(!(e.getBlockPlaced().getState() instanceof Chest chest)) return;
            if(chestName != null){
                Component name = Component.text("\u00A7f\uDAFF\uDFF8\uEFB2\u00A7f\uDAFf\uDF57").append(chestName);
                chest.customName(name);
                chest.update(true);

                Component sideName = Component.text("\u00A7f\uDAFF\uDFF8\uEFB1\u00A7f\uDAFf\uDF57").append(chestName);
                if(chest.getInventory() instanceof DoubleChestInventory inv) {
                    if(inv.getLeftSide().getHolder() instanceof Chest chestL) {
                        chestL.customName(sideName);
                        chestL.update(true);
                    }
                    if(inv.getRightSide().getHolder() instanceof Chest chestR) {
                        chestR.customName(sideName);
                        chestR.update(true);
                    }
                }
            }
        });
    }
}
