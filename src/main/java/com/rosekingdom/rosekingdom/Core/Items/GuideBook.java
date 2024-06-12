package com.rosekingdom.rosekingdom.Core.Items;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class GuideBook extends ItemStack {
    public GuideBook() {
        super(Material.WRITTEN_BOOK);
        setAmount(1);
        ItemMeta meta = this.getItemMeta();
        meta.displayName(Component.text("Server Guide"));
        BookMeta book = (BookMeta) meta;
        book.setAuthor("RoseKing");
        List<Component> page = new ArrayList<>();
        page.add(Component.text("          Welcome\n")
                .append(Component.text("              to\n"))
                .append(Component.text("       RoseKingdom\n\n"))
                .append(Component.text("===================\n\n"))
                .append(Component.text("  That is our server guide, where you can find all the needed information about everything we have made so far. \n\n"))
                .append(Component.text("===================")));
        page.add(Component.text("        Contents:\n")
                .append(Component.text("1. Rules\n"))
                .append(Component.text("2. Features\n"))
                .append(Component.text("3. Staff")));
        page.add(Component.text("           Rules\n")
                .append(Component.text("===================\n"))
                .append(Component.text("1. Griefing and stealing is not allowed.\n"))
                .append(Component.text("2. Be respectful and kind to everyone. \n"))
                .append(Component.text("3. Don't kill other players without a proper reason\n"))
                .append(Component.text("4. Destructive pranks are not allowed unless both parties are okay with it.\n"))
                .append(Component.text("5. Using duplication")));
        page.add(Component.text("exploits is forbidden.\n")
                .append(Component.text("6. Using cheats and hack clients is forbidden.\n"))
                .append(Component.text("7. It's preferable to fill out your creeper holes especially if they are on spawn."))
                .append(Component.text("\n===================\n\n"))
                .append(Component.text("Have a nice and enjoyable stay!")));
        page.add(Component.text("        Features\n")
                .append(Component.text("===================\n"))
                .append(Component.text("\u2022 Graves - When you die, a grave with all your items will spawn. The lifespan of the grave is one hour and can you can have up to three at once. If  you die while having three graves you will die normally. Graves drop all the items in")));
        page.add(Component.text("the end of their lifespan.\n")
                .append(Component.text("\u2022 Kingdoms - This is like your normal teams option. You can create a kingdom and invite your friends to it. Every kingdom has a separator in the tab that separates all kindom members from the rest of the players. (This feature is still WIP)")));
        page.add(Component.text("\u2022 Locations - Allows you to save and share locations easily in-game.\n")
                .append(Component.text("\u2022 Coordinates/XYZ - Makes sharing your coordinates easier with a command and a name.\n"))
                .append(Component.text("\u2022 Profiles - Gives you little information about you or a player.\n"))
                .append(Component.text("\u2022 Stores - Option to create a secure and unique way for selling\n")));
        page.add(Component.text("or buying items.\n")
                .append(Component.text("\u2022 ---- (Coming soon)")));
        page.add(Component.text("           Staff\n")
                .append(Component.text("===================\n"))
                .append(Component.text("Owner and Developer of the server:\n"))
                .append(Component.text("RozeKing (TMG-8047KG)\n\n"))
                .append(Component.text("Admins:\n"))
                .append(Component.text("Misho0oka\n\n"))
                .append(Component.text("Mod and Artist:\n"))
                .append(Component.text("for0ut (lookingforout)")));
        book.pages(page);
        setItemMeta(book);

//                .append(Component.text())
//                .append(Component.text())
//                .append(Component.text())
//                .append(Component.text())
//                .append(Component.text())
//                .append(Component.text()));
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
