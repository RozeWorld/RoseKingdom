package com.rosekingdom.rosekingdom.Core.Items;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class GuideBook extends ItemStack {
    public GuideBook() {
        super(Material.WRITTEN_BOOK);
        setAmount(1);
        ItemMeta meta = this.getItemMeta();
        meta.displayName(Component.text("Server Guide"));
        BookMeta book = (BookMeta) meta;
        book.setAuthor("RoseKing");
        List<Component> pages = book.pages();
        pages.add(Component.text(
                "          Welcome\n" +
                        "              to\n" +
                        "       RoseKingdom\n" +
                        "\n" +
                        "===================\n" +
                        "\n" +
                        "  That is our server guide, where you can find all the needed information about everything we have made so far. \n" +
                        "\n" +
                        "==================="));
        pages.add(Component.text(
                "        Contents:\n" +
                "1. Rules\n" +
                "2. Features\n" +
                "3. Credits"));
        setItemMeta(book);
    }
}
