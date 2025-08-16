package com.rosekingdom.rosekingdom.Core.gui.InventoryTypes;

import com.rosekingdom.rosekingdom.Core.gui.InventoryButton;
import com.rosekingdom.rosekingdom.Core.gui.InventoryHandler;
import com.rosekingdom.rosekingdom.Core.gui.InventoryLocker;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public abstract class InventoryGUI implements InventoryHandler {
    private final Inventory inventory;
    private final Map<Integer, InventoryButton> buttonMap = new HashMap<>();
    private OfflinePlayer targetPlayer;

    public InventoryGUI() {
        this.inventory = this.createInventory();
    }

    protected InventoryGUI(Object... params) {
        this.inventory = this.createInventory(params);
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    public void addButton(int slot, InventoryButton button) {
        this.buttonMap.put(slot, button);
    }

    public void decorate(Player player) {
        this.buttonMap.forEach((slot, button) -> {
            ItemStack icon = button.getIconCreator().apply(player);
            this.inventory.setItem(slot, icon);
        });
    }

    @Override
    public void onClick(InventoryClickEvent event) {
        event.setCancelled(true);
        int slot = event.getRawSlot();
        InventoryButton button = this.buttonMap.get(slot);
        if (button != null) {
            button.getEventConsumer().accept(event);
        }
    }

    @Override
    public void onOpen(InventoryOpenEvent event) {
        InventoryLocker.lock((Player) event.getPlayer());
        this.decorate((Player) event.getPlayer());
    }

    @Override
    public void onClose(InventoryCloseEvent event) {
        InventoryLocker.restore((Player) event.getPlayer());
    }

    protected abstract Inventory createInventory();
    protected Inventory createInventory(Object... params) {
        return this.createInventory();
    }
}
