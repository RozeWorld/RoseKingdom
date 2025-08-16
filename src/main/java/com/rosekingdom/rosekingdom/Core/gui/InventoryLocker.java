package com.rosekingdom.rosekingdom.Core.gui;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class InventoryLocker {
    private static final Map<UUID, ItemStack[]> storageContents = new HashMap<>();
    private static final Map<UUID, ItemStack[]> armorContents = new HashMap<>();
    private static final Map<UUID, ItemStack> offhandItem = new HashMap<>();

    public static boolean isLocked(Player player) {
        return storageContents.containsKey(player.getUniqueId());
    }

    public static void lock(Player player) {
        UUID id = player.getUniqueId();
        if (isLocked(player)) return;

        PlayerInventory inv = player.getInventory();

        storageContents.put(id, cloneArray(inv.getStorageContents()));
        armorContents.put(id, cloneArray(inv.getArmorContents()));
        offhandItem.put(id, cloneItem(inv.getItemInOffHand()));

        inv.clear();
        inv.setArmorContents(new ItemStack[4]);
        inv.setItemInOffHand(null);

        player.updateInventory();
    }

    public static void restore(Player player) {
        UUID id = player.getUniqueId();
        PlayerInventory inv = player.getInventory();

        ItemStack[] storage = storageContents.remove(id);
        ItemStack[] armor = armorContents.remove(id);
        ItemStack offhand = offhandItem.remove(id);

        if (storage != null) inv.setStorageContents(storage);
        if (armor != null) inv.setArmorContents(armor);
        inv.setItemInOffHand(offhand);

        player.updateInventory();
    }

    //Just in case and it might get used in the Grave system
    public static void forceDropAndForget(Player player) {
        UUID id = player.getUniqueId();
        ItemStack[] storage = storageContents.remove(id);
        ItemStack[] armor   = armorContents.remove(id);
        ItemStack   offhand = offhandItem.remove(id);

        if (storage != null) for (ItemStack it : storage) if (it != null) player.getWorld().dropItemNaturally(player.getLocation(), it);
        if (armor != null) for (ItemStack it : armor) if (it != null) player.getWorld().dropItemNaturally(player.getLocation(), it);
        if (offhand != null) player.getWorld().dropItemNaturally(player.getLocation(), offhand);
    }

    private static ItemStack[] cloneArray(ItemStack[] items) {
        if (items == null) return null;
        ItemStack[] out = new ItemStack[items.length];
        for (int i = 0; i < items.length; i++) {
            out[i] = cloneItem(items[i]);
        }
        return out;
    }

    private static ItemStack cloneItem(ItemStack item) {
        return item == null ? null : item.clone();
    }
}
