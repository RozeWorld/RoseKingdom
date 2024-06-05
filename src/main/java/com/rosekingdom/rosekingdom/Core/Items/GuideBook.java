package com.rosekingdom.rosekingdom.Core.Items;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.WritableBookMeta;

public class GuideBook extends ItemStack {
    public GuideBook() {
        super(Material.WRITTEN_BOOK);
        setAmount(1);
        ItemMeta meta = this.getItemMeta();
        meta.displayName(Component.text("Server Guide"));
        WritableBookMeta pages = (WritableBookMeta) meta;

        this.setItemMeta(meta);

    }
}
