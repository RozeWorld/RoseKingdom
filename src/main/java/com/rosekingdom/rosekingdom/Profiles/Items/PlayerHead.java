package com.rosekingdom.rosekingdom.Profiles.Items;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PlayerHead extends ItemStack {
    public PlayerHead(){
        setAmount(1);
        setType(Material.PLAYER_HEAD);
        ItemMeta meta = getItemMeta();
        meta.displayName(Component.text("Test"));
        setItemMeta(meta);
    }
}
