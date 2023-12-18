package com.rosekingdom.rosekingdom.Economy.Events.GUI;

import com.rosekingdom.rosekingdom.Core.Utils.Message;
import com.rosekingdom.rosekingdom.Economy.GUIs.*;
import com.rosekingdom.rosekingdom.Economy.Statements.StockStatement;
import com.rosekingdom.rosekingdom.Economy.Statements.StoreStatement;
import net.kyori.adventure.text.Component;
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

import java.util.HashMap;
import java.util.List;

public class sOwnerPanel implements Listener {
    Entity storeId;
    String store;
    @EventHandler(ignoreCancelled = true)
    public void movingItems(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        InventoryHolder holder = e.getView().getTopInventory().getHolder();
        List<ItemStack> items = StockStatement.getItems(store);
        //Main GUI
        if (holder instanceof Merchant) {
            if (e.getRawSlot() < 45) e.setCancelled(true);
            if (e.getClick().equals(ClickType.SHIFT_RIGHT) || e.getClick().equals(ClickType.SHIFT_LEFT)) e.setCancelled(true);
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
            if(e.getRawSlot() == 25){
                player.openInventory(new sStocking().getInventory());
            }
        }

        //Item selecting
        if(holder instanceof sItemSelector) {
            if (e.getRawSlot() < 9 && e.getRawSlot() != 4) e.setCancelled(true);
            if (e.getClick().equals(ClickType.SHIFT_RIGHT) || e.getClick().equals(ClickType.SHIFT_LEFT)) e.setCancelled(true);
            if (e.getAction().equals(InventoryAction.PLACE_ALL) && e.getRawSlot() == 4) {
                ItemStack item = e.getCursor();
                if(!items.contains(item)){
                    player.openInventory(new sPriceSelector(item).getInventory());
                }else{
                    player.closeInventory();
                    player.sendMessage(Message.Info("You are already selling this item!"));
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
                    player.closeInventory();
                }
                e.setCancelled(true);
            }
            if (e.getClick().equals(ClickType.SHIFT_RIGHT) || e.getClick().equals(ClickType.SHIFT_LEFT)) e.setCancelled(true);
        }


        //Price selecting
        if(holder instanceof sPriceSelector){
            ItemStack item = e.getInventory().getItem(4);
            ItemStack price = e.getInventory().getItem(13);
            if(e.getRawSlot() < 27) e.setCancelled(true);
            if(e.getClick().equals(ClickType.SHIFT_RIGHT) || e.getClick().equals(ClickType.SHIFT_LEFT)) e.setCancelled(true);
            int value = price.getAmount() == 1 ? 0 : price.getAmount();
            switch (e.getRawSlot()){
                case 10 -> value -= 32;
                case 11 -> value -= 16;
                case 12 -> value -= 1;
                case 14 -> value += 1;
                case 15 -> value += 16;
                case 16 -> value += 32;
            }
            if(value < 1) value = 1;
            if(value > 64) value = 64;
            price.setAmount(value);
            if(e.getRawSlot() == 21) {
                player.sendMessage("Cancelled adding item to store");
                player.closeInventory();
            }
            if(e.getRawSlot() == 23){
                player.sendMessage("Successfully added item to store");
                StockStatement.addItemToStore(item, value, store);
                player.closeInventory();
            }
        }

        //Stocking
        if(holder instanceof sStocking){
            ItemStack item = e.getCursor();
            if(e.getRawSlot() < 9) e.setCancelled(true);
            if(e.getClick().equals(ClickType.SHIFT_RIGHT) || e.getClick().equals(ClickType.SHIFT_LEFT)) e.setCancelled(true);
            if(e.getRawSlot() == 4){
                for(ItemStack i : items){
                    if(i.isSimilar(item)){
                        player.sendMessage(Component.text("Added " + item.getAmount() + " stock to ")
                            .append(item.displayName()));
                        StockStatement.addStock(item, store);
                        item.setAmount(0);
                    }
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

        if (holder instanceof sItemSelector || holder instanceof sStocking || holder instanceof sRemoveSelector) {
            for (int slot : e.getRawSlots()) {
                if (slot < 9) {
                    e.setCancelled(true);
                }
            }
        }

        if (holder instanceof sPriceSelector) {
            for (int slot : e.getRawSlots()) {
                if (slot < 27) {
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
            player.openInventory(new Merchant(entity).getInventory());
        }
    }
}
