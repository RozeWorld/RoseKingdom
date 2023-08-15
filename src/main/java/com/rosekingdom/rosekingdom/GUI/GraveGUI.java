package com.rosekingdom.rosekingdom.GUI;

import com.rosekingdom.rosekingdom.Database.Statements.UserStatement;
import com.rosekingdom.rosekingdom.Database.Statements.GraveStatement;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class GraveGUI implements InventoryHolder {
    int id;
    int grave_num;

    public GraveGUI(Player player, int grave_num){
        this.id = UserStatement.getId(player.getUniqueId());
        this.grave_num = grave_num;
    }

    @Override
    public @NotNull Inventory getInventory() {
        Inventory grave = Bukkit.createInventory(this, 45, Component.text("Grave"));
        getItems(grave);
        return grave;
    }

    private void getItems(Inventory inventory){
        for (ItemStack itemStack : GraveStatement.getItems(id, grave_num)) {
            inventory.addItem(itemStack);
        }
    }

    public int getGrave_num() {
        return grave_num;
    }
}
