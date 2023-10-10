package com.rosekingdom.rosekingdom.Profiles.Items;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;

public class PlayerHead extends ItemStack {
    public PlayerHead(Player player){
        setAmount(1);
        setType(Material.PLAYER_HEAD);
        SkullMeta skull = (SkullMeta) getItemMeta();
        skull.setOwningPlayer(player);
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text("Some lore we could have"));
        skull.lore(lore);
        skull.displayName(Component.text("Test"));
        setItemMeta(skull);
    }
}
