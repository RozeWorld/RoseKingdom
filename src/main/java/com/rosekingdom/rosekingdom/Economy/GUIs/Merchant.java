package com.rosekingdom.rosekingdom.Economy.GUIs;

import com.rosekingdom.rosekingdom.Economy.Items.sAdd;
import com.rosekingdom.rosekingdom.Economy.Items.sRemove;
import com.rosekingdom.rosekingdom.Economy.Items.sStock;
import com.rosekingdom.rosekingdom.Economy.Statements.StockStatement;
import com.rosekingdom.rosekingdom.Economy.Statements.StoreStatement;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Merchant implements InventoryHolder {

    Entity storeId;
    String store;
    public Merchant(Entity entity){
        this.storeId = entity;
        this.store = StoreStatement.getStore(storeId);
    }
    @Override
    public @NotNull Inventory getInventory() {
        Inventory inventory = Bukkit.createInventory(this,36, Component.text("Merchant Menu"));
        inventory.setItem(15, new sRemove());
        inventory.setItem(16, new sAdd());
        inventory.setItem(25, new sStock(store));
        List<ItemStack> stock = StockStatement.getItems(store);
        for(ItemStack item : stock){
            List<Component> lore = new ArrayList<>();
            lore.add(Component.text("Stock: ", TextColor.fromHexString("#ffb330"))
                    .append(Component.text(StockStatement.getStock(item, store),TextColor.fromHexString("#d94330")))
                    .decoration(TextDecoration.ITALIC, false));
            lore.add(Component.text("Price -> ", TextColor.fromHexString("#ffb330"))
                    .append(Component.text(StockStatement.getPrice(item, store), TextColor.fromHexString("#d94330")))
                    .decoration(TextDecoration.ITALIC, false));
            item.lore(lore);
        }
        if(!stock.isEmpty()){
            switch (stock.size()){
                case 1 -> inventory.setItem(1, stock.get(0));
                case 2 -> {
                    inventory.setItem(1, stock.get(0));
                    inventory.setItem(2, stock.get(1));
                }
                case 3 -> {
                    inventory.setItem(1, stock.get(0));
                    inventory.setItem(2, stock.get(1));
                    inventory.setItem(3, stock.get(2));
                }
                case 4 -> {
                    inventory.setItem(1, stock.get(0));
                    inventory.setItem(2, stock.get(1));
                    inventory.setItem(3, stock.get(2));
                    inventory.setItem(19, stock.get(3));
                }
                case 5 -> {
                    inventory.setItem(1, stock.get(0));
                    inventory.setItem(2, stock.get(1));
                    inventory.setItem(3, stock.get(2));
                    inventory.setItem(19, stock.get(3));
                    inventory.setItem(20, stock.get(4));
                }
                case 6 -> {
                    inventory.setItem(1, stock.get(0));
                    inventory.setItem(2, stock.get(1));
                    inventory.setItem(3, stock.get(2));
                    inventory.setItem(19, stock.get(3));
                    inventory.setItem(20, stock.get(4));
                    inventory.setItem(21, stock.get(5));
                }
            }
        }
        return inventory;
    }
}
