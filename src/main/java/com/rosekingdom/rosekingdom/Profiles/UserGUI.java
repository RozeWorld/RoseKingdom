package com.rosekingdom.rosekingdom.Profiles;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;

public class UserGUI implements InventoryHolder {

    Player player;

    public UserGUI(Player player){
        this.player = player;
    }

    @Override
    public @NotNull Inventory getInventory() {
        return Bukkit.createInventory(this, 27, player.displayName());
    }
}
