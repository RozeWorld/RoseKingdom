package com.rosekingdom.rosekingdom.Profiles;

import com.rosekingdom.rosekingdom.Profiles.Items.PlayerHead;
import com.rosekingdom.rosekingdom.Profiles.Items.ProfilePlayTime;
import com.rosekingdom.rosekingdom.Profiles.Items.activityIndicator;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;

public class UserGUI implements InventoryHolder{

    OfflinePlayer player;

    public UserGUI(OfflinePlayer player){
        this.player = player;
    }

    @Override
    public @NotNull Inventory getInventory() {
        Inventory profile = Bukkit.createInventory(this, InventoryType.CHEST, Component.text(""));
        profile.setItem(10, new PlayerHead(player));
        profile.setItem(21, new ProfilePlayTime(player));
        profile.setItem(8, new activityIndicator(player));
        return profile;
    }
}
