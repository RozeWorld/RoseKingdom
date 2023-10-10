package com.rosekingdom.rosekingdom.Graves;

import com.rosekingdom.rosekingdom.Core.Database.Main_Statements.UserStatement;
import com.rosekingdom.rosekingdom.Graves.Statements.GraveStatement;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class GraveGUI implements InventoryHolder {
    int id;
    String graveId;

    public GraveGUI(Player player, String graveId){
        this.id = UserStatement.getId(player.getUniqueId());
        this.graveId = graveId;
    }

    @Override
    public @NotNull Inventory getInventory() {
        Inventory grave = Bukkit.createInventory(this, 45, Component.text("Grave"));
        getItems(grave);
        return grave;
    }

    private void getItems(Inventory inventory){
        for (ItemStack itemStack : GraveStatement.getItems(id, graveId)) {
            inventory.addItem(itemStack);
        }
    }

    public String getGraveId() {
        return graveId;
    }
}
