package com.rosekingdom.rosekingdom.Economy.GUIs;

import com.rosekingdom.rosekingdom.Core.Items.Checksymbol;
import com.rosekingdom.rosekingdom.Core.Items.Xsymbol;
import com.rosekingdom.rosekingdom.Economy.Items.Coin;
import com.rosekingdom.rosekingdom.Economy.Items.sPriceItem;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class sPriceSelector implements InventoryHolder {

    ItemStack item;

    public sPriceSelector(ItemStack item){
        this.item = item;
    }

    @Override
    public @NotNull Inventory getInventory() {
        Inventory inventory = Bukkit.createInventory(this, 27, Component.text("Select Price"));
        inventory.setItem(4, item);
        inventory.setItem(10, new sPriceItem(32, 2300, false));
        inventory.setItem(11, new sPriceItem(16, 2300, false));
        inventory.setItem(12, new sPriceItem(1, 2300, false));
        inventory.setItem(13, new Coin(1));
        inventory.setItem(14, new sPriceItem(1, 2301, true));
        inventory.setItem(15, new sPriceItem(16, 2301, true));
        inventory.setItem(16, new sPriceItem(32, 2301, true));
        inventory.setItem(21, new Xsymbol("Cancel", null));
        inventory.setItem(23, new Checksymbol("Accept", null));
        return inventory;
    }
}
