package com.rosekingdom.rosekingdom.Items;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

public class GuideBook {
    public static void book(Player player) {
        Inventory pinv = player.getInventory();
        ItemStack book1 = new ItemStack(Material.WRITTEN_BOOK);
        BookMeta bookmeta = (BookMeta) book1.getItemMeta();
        bookmeta.setTitle("Guide book");
        bookmeta.setAuthor("Misho0oka");
        bookmeta.addPages(Component.text("        THE BOOK!!\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "In this book you will find information about all custom things made by our staff. "));
        bookmeta.addPages(Component.text("1.1 WhenYouDie:\n" +
                " This send a message   in the chat, which        shows the cordinates  you died at.\n" +
                "\n" +
                "1.2 Graves:\n" +
                " it spawns at the         location you died at    and store all  your     items until you get      them back."));
        bookmeta.addPages(Component.text("1.3 Rank:\n" +
                " [Owner]\n" +
                " [Admin]\n" +
                " [Mod]\n" +
                " [Default]\n" +
                "\n" +
                "1.4 Locations:\n" +
                " Here you can save your public locations (shops,markets) which other players can see and private locations which you are the only one who can see them"));
        book1.setItemMeta(bookmeta);
        pinv.addItem(book1);
    }
}
