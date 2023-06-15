package com.rosekingdom.rosekingdom.GUI;

import com.rosekingdom.rosekingdom.Database.Statements.GraveStatement;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Grave implements InventoryHolder {
    Player player;
    int grave_num;

    public Grave(Player player, int grave_num){
        this.player = player;
        this.grave_num = grave_num;
    }

    @Override
    public @NotNull Inventory getInventory() {
        Inventory inventory = Bukkit.createInventory(this, 45, Component.text("Grave ")
                .append(Component.text(grave_num)));
        addItems(inventory, player, grave_num);
        return inventory;
    }

    private void addItems(Inventory inventory, Player player, int graveNum){
        for(ItemStack i : Objects.requireNonNull(GraveStatement.getItems(player, graveNum))){
            inventory.addItem(i);
        }
    }

    public int getGrave_num(){
        return grave_num;
    }
}
