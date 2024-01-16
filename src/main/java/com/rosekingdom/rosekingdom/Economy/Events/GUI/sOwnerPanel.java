package com.rosekingdom.rosekingdom.Economy.Events.GUI;

import com.rosekingdom.rosekingdom.Core.Utils.Message;
import com.rosekingdom.rosekingdom.Economy.GUIs.*;
import com.rosekingdom.rosekingdom.Economy.Statements.PricingStatement;
import com.rosekingdom.rosekingdom.Economy.Statements.StockStatement;
import com.rosekingdom.rosekingdom.Economy.Statements.StoreStatement;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class sOwnerPanel implements Listener {
    Entity storeId;
    String store;
    ItemStack rawItem;
    int price = 1;
    @EventHandler(ignoreCancelled = true)
    public void movingItems(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        InventoryHolder holder = e.getView().getTopInventory().getHolder();
        List<ItemStack> items = StockStatement.getItems(store);
        //Main GUI
        if (holder instanceof Merchant) {
            if (e.getRawSlot() < 45) e.setCancelled(true);
            if (e.getClick().equals(ClickType.SHIFT_RIGHT) || e.getClick().equals(ClickType.SHIFT_LEFT)) e.setCancelled(true);
            List<Integer> slots = new ArrayList<>(Arrays.asList(10,11,12,28,29,30));
            if(slots.contains(e.getRawSlot())){
                rawItem = new ItemStack(items.get(slots.indexOf(e.getRawSlot())));
                if(PricingStatement.hasOptions(rawItem, store)){
                    player.openInventory(new sItemStoreOptions(rawItem, store, true).getInventory());
                }
            }
            if (e.getRawSlot() == 15 && !items.isEmpty()){
                player.openInventory(new sRemoveSelector(store).getInventory());
            }
            if (e.getRawSlot() == 16) {
                if(items.size() < 6){
                    player.openInventory(new sItemSelector().getInventory());
                }else {
                    player.closeInventory();
                    player.sendMessage(Message.Info("Can't add more items"));
                }
            }
            if(e.getRawSlot() == 24){
                //Cashout
            }
            if(e.getRawSlot() == 25){
                player.openInventory(new sStocking().getInventory());
            }
        }

        //Item selecting
        if(holder instanceof sItemSelector) {
            if (e.getRawSlot() < 9 && e.getRawSlot() != 4) e.setCancelled(true);
            if (e.getClick().equals(ClickType.SHIFT_RIGHT) || e.getClick().equals(ClickType.SHIFT_LEFT)) e.setCancelled(true);
            if (e.getAction().equals(InventoryAction.PLACE_ALL) && e.getRawSlot() == 4) {
                ItemStack item = e.getCursor().asOne();
                rawItem = new ItemStack(item);
                if(!items.contains(rawItem)){
                    if(item.getType().getMaxStackSize() > 1){
                        player.openInventory(new sItemStoreOptions(rawItem, store).getInventory());
                    }else{
                        price = 1;
                        player.openInventory(new sPriceSelector(rawItem).getInventory());
                    }
                }else{
                    e.setCancelled(true);
                    player.sendMessage(Message.Info("You are already selling this item!"));
                }
            }
        }

        if(holder instanceof sItemStoreOptions) {
            if (e.getRawSlot() < 27) e.setCancelled(true);
            if (e.getClick().equals(ClickType.SHIFT_RIGHT) || e.getClick().equals(ClickType.SHIFT_LEFT)) e.setCancelled(true);
            List<Integer> slots = new ArrayList<>(Arrays.asList(2, 3, 4, 5, 6));
            if (slots.contains(e.getRawSlot())){
                ItemStack item = e.getCurrentItem();
                if(item.lore() != null){
                    List<Component> lore = item.lore();
                    lore.remove(4);
                    item.lore(lore);
                }
                boolean exists = PricingStatement.exists(item, store);
                if(exists && e.getClick().isRightClick()){
                    if(e.getInventory().getItem(26) == null && PricingStatement.getOffers(rawItem, store) == 1){
                        player.sendMessage(Message.Warning("You need to have at least one offer!"));
                        player.openInventory(new sItemStoreOptions(rawItem, store, e.getInventory().getItem(26) == null).getInventory());
                    }else {
                        PricingStatement.removeItem(item, store);
                        player.openInventory(new sItemStoreOptions(rawItem, store, e.getInventory().getItem(26) == null).getInventory());
                    }
                }else{
                    player.openInventory(new sAmountSelector(rawItem.asOne()).getInventory());
                }
            }
            if(e.getRawSlot() == 18){
                if(!StockStatement.exists(rawItem, store)){
                    PricingStatement.clearAll(rawItem, store);
                    player.openInventory(new Merchant(store).getInventory());
                    player.sendMessage(Message.Info("Canceling adding item to the store!"));
                }else{
                    player.openInventory(new Merchant(store).getInventory());
                }
            }
            if(e.getRawSlot() == 26){
                if(PricingStatement.hasOffers(rawItem, store)){
                StockStatement.addItemToStore(rawItem, store);
                player.openInventory(new Merchant(store).getInventory());
                }else {
                    player.sendMessage(Message.Warning("You don't have offers!"));
                }
            }
            //TODO:Finish after the main system
//            if (e.getRawSlot() == 22) {
//                player.openInventory(new sPriceSelector(rawItem).getInventory());
//            }
        }

        if(holder instanceof sAmountSelector){
            ItemStack size = e.getInventory().getItem(4);
            if(e.getRawSlot() < 18) e.setCancelled(true);
            if(e.getClick().equals(ClickType.SHIFT_RIGHT) || e.getClick().equals(ClickType.SHIFT_LEFT)) e.setCancelled(true);
            int value = size.getAmount();
            switch (e.getRawSlot()){
                case 0 -> value -= 32;
                case 1 -> value -= 16;
                case 2 -> value -= 5;
                case 3 -> value -= 1;
                case 5 -> value += 1;
                case 6 -> value += 5;
                case 7 -> value += 16;
                case 8 -> value += 32;
            }
            if(value < 1) value = 1;
            if(value > size.getType().getMaxStackSize()) value = size.getType().getMaxStackSize();
            size.setAmount(value);
            if(e.getRawSlot() == 12) {
                player.sendMessage(Message.Info("Canceled offer creation!"));
                player.closeInventory();
            }
            if(e.getRawSlot() == 14){
                if(PricingStatement.itemSizeExists(rawItem, value, store)){
                    player.sendMessage(Message.Warning("You already have this amount!"));
                }else{
                    price = 1;
                    player.openInventory(new sPriceSelector(size).getInventory());
                }
            }
        }


        //Price selecting
        if(holder instanceof sPriceSelector){
            ItemStack item = e.getInventory().getItem(4);
            if(e.getRawSlot() < 18) e.setCancelled(true);
            if(e.getClick().equals(ClickType.SHIFT_RIGHT) || e.getClick().equals(ClickType.SHIFT_LEFT)) e.setCancelled(true);
            switch (e.getRawSlot()){
                case 0 -> price -= 32;
                case 1 -> price -= 16;
                case 2 -> price -= 5;
                case 3 -> price -= 1;
                case 5 -> price += 1;
                case 6 -> price += 5;
                case 7 -> price += 16;
                case 8 -> price += 32;
            }
            if(price < 1) {
                price = 1;
                player.sendMessage(Component.text("You can't use negative values!"));
            }
            if(item != null && item.lore() != null){
                List<Component> lore = item.lore();
                lore.set(2, Component.text(price + " Coins", TextColor.fromHexString("#FFC624")));
                item.lore(lore);
            }
            if(e.getRawSlot() == 12) {
                player.sendMessage(Message.Info("Canceled offer creation!"));
                player.closeInventory();
            }
            if(e.getRawSlot() == 14){
                player.sendMessage(Message.Info("Successfully added offer"));
                if (rawItem.getType().getMaxStackSize() > 1) {
                    PricingStatement.addItem(item, price, store, rawItem, true);
                    player.openInventory(new sItemStoreOptions(rawItem, store, StockStatement.exists(rawItem, store)).getInventory());
                } else {
                    PricingStatement.addItem(item, price, store, rawItem, false);
                    StockStatement.addItemToStore(rawItem, store);
                    player.openInventory(new Merchant(store).getInventory());
                }
            }
        }

        //Item removal
        if(holder instanceof sRemoveSelector) {
            if (e.getRawSlot() < 9){
                if(e.getCurrentItem() != null){
                    ItemStack item = e.getCurrentItem();
                    item.setAmount(StockStatement.getStock(item, store));
                    final HashMap<Integer, ItemStack> dropItems = player.getInventory().addItem(item);
                    for(ItemStack drop : dropItems.values()){
                        player.getWorld().dropItemNaturally(player.getLocation(), drop);
                    }
                    player.sendMessage(item.displayName()
                            .append(Component.text(" was removed from the store!")));
                    item.setAmount(1);
                    StockStatement.removeItemFromStore(e.getCurrentItem(), store);
                    PricingStatement.clearAll(e.getCurrentItem(), store);
                    player.closeInventory();
                }
                e.setCancelled(true);
            }
            if (e.getClick().equals(ClickType.SHIFT_RIGHT) || e.getClick().equals(ClickType.SHIFT_LEFT)) e.setCancelled(true);
        }


        //Stocking
        if(holder instanceof sStocking){
            ItemStack item = e.getCursor();
            if(e.getRawSlot() < 9) e.setCancelled(true);
            if(e.getClick().equals(ClickType.SHIFT_RIGHT) || e.getClick().equals(ClickType.SHIFT_LEFT)) e.setCancelled(true);
            if(e.getRawSlot() == 4){
                if(items.contains(item)){
                    player.sendMessage(Component.text("Added " + item.getAmount() + " stock to ")
                        .append(item.displayName()));
                    StockStatement.addStock(item, store);
                    item.setAmount(0);
                }else {
                    player.sendMessage(Message.Info("You're not selling this item!"));
                }
            }
        }
    }

    @EventHandler
    public void draggingItems(InventoryDragEvent e) {
        InventoryHolder holder = e.getInventory().getHolder();
        if (holder instanceof Merchant) {
            for (int slot : e.getRawSlots()) {
                if (slot < 45) {
                    e.setCancelled(true);
                }
            }
        }

        if(holder instanceof sItemStoreOptions){
            for (int slot : e.getRawSlots()) {
                if (slot < 27) {
                    e.setCancelled(true);
                }
            }
        }

        if (holder instanceof sPriceSelector || holder instanceof sAmountSelector) {
            for (int slot : e.getRawSlots()) {
                if (slot < 18) {
                    e.setCancelled(true);
                }
            }
        }

        if (holder instanceof sItemSelector || holder instanceof sStocking || holder instanceof sRemoveSelector) {
            for (int slot : e.getRawSlots()) {
                if (slot < 9) {
                    e.setCancelled(true);
                }
            }
        }
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOW)
    public void onMerchantClick(PlayerInteractEntityEvent e){
        Player player = e.getPlayer();
        Entity entity = e.getRightClicked();
        if(StoreStatement.isStore(entity.getUniqueId()) && StoreStatement.owner(player, entity.getUniqueId())){
            storeId = entity;
            store = StoreStatement.getStore(storeId);
            player.openInventory(new Merchant(store).getInventory());
        }
    }
}
