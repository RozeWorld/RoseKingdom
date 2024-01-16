package com.rosekingdom.rosekingdom.Economy.GUIs;

import com.rosekingdom.rosekingdom.Core.Items.Xsymbol;
import com.rosekingdom.rosekingdom.Economy.Items.sAdd;
import com.rosekingdom.rosekingdom.Economy.Items.sOptions;
import com.rosekingdom.rosekingdom.Economy.Items.sRemove;
import com.rosekingdom.rosekingdom.Economy.Items.sStock;
import com.rosekingdom.rosekingdom.Economy.Statements.PricingStatement;
import com.rosekingdom.rosekingdom.Economy.Statements.StockStatement;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Merchant implements InventoryHolder {
    String store;
    public Merchant(String store){
        this.store = store;
    }
    @Override
    public @NotNull Inventory getInventory() {
        Inventory inventory = Bukkit.createInventory(this,36, Component.text("\u00A7f\uDAFF\uDFF8\uEE02"));
        inventory.setItem(15, new sRemove());
        inventory.setItem(16, new sAdd());
        inventory.setItem(25, new sStock(store));
        List<ItemStack> stock = StockStatement.getItems(store);
        int[] itemSlots = {1,2,3,19,20,21};
        int[] optionSlots = {10,11,12,28,29,30};
        int i = 0;
        for(ItemStack item : stock){
            List<Component> lore = item.lore() != null ? item.lore() : new ArrayList<>();
            if(PricingStatement.hasOptions(item, store)){
                inventory.setItem(optionSlots[i], new sOptions(item.displayName()));
            }else{
                inventory.setItem(optionSlots[i], new Xsymbol("Doesn't have options", null));
            }


            //Item description (added in reverse)
            Map<Integer, Integer> prices = PricingStatement.getItemPrices(item, store);
            lore.add(0, Component.empty());
            for(int amount : prices.keySet()){
                lore.add(0, Component.text(amount + " for " + prices.get(amount) + " coins", TextColor.fromHexString("#A5A5A5"))
                        .decoration(TextDecoration.ITALIC, false));
            }
            lore.add(0, Component.text("Prices:", TextColor.fromHexString("#ffb330"))
                    .decoration(TextDecoration.ITALIC, false));
            lore.add(0, Component.empty());
            lore.add(0, Component.text("Stock: ", TextColor.fromHexString("#ffb330"))
                    .append(Component.text(StockStatement.getStock(item, store),TextColor.fromHexString("#d94330")))
                    .decoration(TextDecoration.ITALIC, false));
            lore.add(0, Component.empty());

            item.lore(lore);
            //Add
            inventory.setItem(itemSlots[i], item);
            i++;
        }
        return inventory;
    }
}
