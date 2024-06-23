package com.rosekingdom.rosekingdom.Economy.Items;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class sAutoPricing extends ItemStack {
    public sAutoPricing(){
        super(Material.PAPER);
        setAmount(1);
        ItemMeta meta = getItemMeta();
        meta.setCustomModelData(2355);
        meta.displayName(Component.text("Automatic allocation (Coming soon) ", TextColor.fromHexString("#F0BB22")).decoration(TextDecoration.ITALIC, false));
        setItemMeta(meta);
    }
}
