package com.rosekingdom.rosekingdom.Economy.Events.GUI;

import com.rosekingdom.rosekingdom.Core.Utils.Message;
import com.rosekingdom.rosekingdom.Economy.GUIs.Store;
import com.rosekingdom.rosekingdom.Economy.GUIs.sBuySelector;
import com.rosekingdom.rosekingdom.Economy.Statements.EconomyStatement;
import com.rosekingdom.rosekingdom.Economy.Statements.PricingStatement;
import com.rosekingdom.rosekingdom.Economy.Statements.StockStatement;
import com.rosekingdom.rosekingdom.Economy.Statements.StoreStatement;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class sPlayerPanel implements Listener {

    Entity storeId;
    String store;
    Map<ItemStack, Integer> stock;
    Set<ItemStack> items;
    ItemStack item;
    @EventHandler
    public void movingItems(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        InventoryHolder holder = e.getView().getTopInventory().getHolder();
        //Storefront
        if(holder instanceof Store){
            if(e.getRawSlot() < 27) e.setCancelled(true);
            if(e.getClick().equals(ClickType.SHIFT_RIGHT) || e.getClick().equals(ClickType.SHIFT_LEFT)) e.setCancelled(true);
            Integer[] rawSlots = {1,2,3,18,19,20};
            List<Integer> slots = new ArrayList<>(Arrays.asList(rawSlots));
            if(slots.contains(e.getRawSlot()) && e.getCurrentItem() != null){
                item = new ItemStack(e.getCurrentItem());
                if (items.contains(item)){
                    player.openInventory(new sBuySelector(item, stock.get(item), store).getInventory());
                }
            }
        }
        if(holder instanceof sBuySelector){
            if(e.getRawSlot() < 27) e.setCancelled(true);
            if(e.getClick().equals(ClickType.SHIFT_RIGHT) || e.getClick().equals(ClickType.SHIFT_LEFT)) e.setCancelled(true);
            if(e.getCurrentItem() != null && e.getRawSlot() < 27){
                ItemStack item = new ItemStack(e.getCurrentItem());
                int available = StockStatement.getStock(PricingStatement.getRawItem(item, store), store);
                int coins = EconomyStatement.getCoins(player);
                int price = PricingStatement.getItemPrice(item, store);
                if(available < item.getAmount()){
                    player.sendMessage(Message.Warning("Out of stock!"));
                    e.setCancelled(true);
                }else if (coins < price){
                    player.sendMessage(Message.Warning("Not enough coins!"));
                    e.setCancelled(true);
                }else{
                    player.getInventory().addItem(PricingStatement.getRawItem(item, store).asQuantity(item.getAmount()));
                    player.sendMessage(Component.text("Purchased " + item.getAmount() + " of ").append(item.displayName()));
                    StockStatement.removeStock(item, store);
                    EconomyStatement.removeCoins(player, price);
                }
            }
        }
    }

    @EventHandler
    public void draggingItems(InventoryDragEvent e) {
        if (e.getInventory().getHolder() instanceof Store) {
            for (int slot : e.getRawSlots()) {
                if (slot < 45) {
                    e.setCancelled(true);
                }
            }
        }
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOW)
    public void onMerchantClick(PlayerInteractEntityEvent e){
        Player player = e.getPlayer();
        Entity entity = e.getRightClicked();
        if(StoreStatement.isStore(entity.getUniqueId()) && !StoreStatement.owner(player, entity.getUniqueId())){
            storeId = entity;
            store = StoreStatement.getStore(storeId);
            stock = StockStatement.getItemsStock(store);
            items = stock.keySet();
            player.openInventory(new Store(store, items).getInventory());
        }
    }
}