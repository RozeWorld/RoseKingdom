package com.rosekingdom.rosekingdom.Economy.GUIs;

import com.rosekingdom.rosekingdom.Core.Items.Checksymbol;
import com.rosekingdom.rosekingdom.Core.Items.Xsymbol;
import com.rosekingdom.rosekingdom.Economy.Items.sPriceItem;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class sAmountSelector implements InventoryHolder {

    ItemStack item;

    public sAmountSelector(ItemStack item){
        this.item = item;
    }

    @Override
    public @NotNull Inventory getInventory() {
        Inventory inventory = Bukkit.createInventory(this, 18, Component.text("\u00A7f\uDAFF\uDFF8\uEE04"));
        inventory.setItem(0, new sPriceItem(32, 2300, false));
        inventory.setItem(1, new sPriceItem(16, 2300, false));
        inventory.setItem(2, new sPriceItem(5, 2300, false));
        inventory.setItem(3, new sPriceItem(1, 2300, false));
        inventory.setItem(4, item);
        inventory.setItem(5, new sPriceItem(1, 2301, true));
        inventory.setItem(6, new sPriceItem(5, 2301, true));
        inventory.setItem(7, new sPriceItem(16, 2301, true));
        inventory.setItem(8, new sPriceItem(32, 2301, true));
        inventory.setItem(12, new Xsymbol("Cancel", null));
        inventory.setItem(14, new Checksymbol("Continue", null));
        return inventory;
    }
}
