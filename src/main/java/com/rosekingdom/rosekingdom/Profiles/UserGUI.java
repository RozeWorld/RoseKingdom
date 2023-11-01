package com.rosekingdom.rosekingdom.Profiles;

import com.rosekingdom.rosekingdom.Profiles.Items.PlayerHead;
import com.rosekingdom.rosekingdom.Profiles.Items.ProfilePlayTime;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;

public class UserGUI implements InventoryHolder{

    Player player;

    public UserGUI(Player player){
        this.player = player;
    }

    @Override
    public @NotNull Inventory getInventory() {
        Inventory profile = Bukkit.createInventory(this, 27, player.displayName());
        profile.setItem(10, new PlayerHead(player));
        profile.setItem(21, new ProfilePlayTime(player));
        return profile;
    }
}
