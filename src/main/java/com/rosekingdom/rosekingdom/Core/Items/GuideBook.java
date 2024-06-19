package com.rosekingdom.rosekingdom.Core.Items;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
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
        page.add(Component.text("          Welcome\n", TextColor.fromHexString("#e63212"))
                .append(Component.text("              to\n", TextColor.fromHexString("#e63212")))
                .append(Component.text("       RoseKingdom\n\n", TextColor.fromHexString("#e63212")))
                .append(Component.text("===================\n\n", TextColor.fromHexString("#000000")))
                .append(Component.text("  That is our server guide, where you can find all the needed information about everything we have made so far. \n\n", TextColor.fromHexString("#737373")))
                .append(Component.text("===================", TextColor.fromHexString("#000000"))));
        page.add(Component.text("        Contents:\n")
                .append(Component.text("===================", TextColor.fromHexString("#000000")))
                .append(Component.text("1. Rules\n", TextColor.fromHexString("#737373")))
                .append(Component.text("2. Features\n", TextColor.fromHexString("#737373")))
                .append(Component.text("3. Staff", TextColor.fromHexString("#737373"))));
        page.add(Component.text("           Rules\n")
                .append(Component.text("===================\n", TextColor.fromHexString("#000000")))
                .append(Component.text("1. Griefing and stealing is not allowed.\n"))
                .append(Component.text("2. Be respectful and kind to everyone. \n"))
                .append(Component.text("3. Don't kill other players without a proper reason\n"))
                .append(Component.text("4. Destructive pranks are not allowed unless both parties are okay with it.\n"))
                .append(Component.text("5. Using duplication")));
        page.add(Component.text("exploits is forbidden.\n")
                .append(Component.text("6. Using cheats and hack clients is forbidden.\n"))
                .append(Component.text("7. It's preferable to fill out your creeper holes especially if they are on spawn."))
                .append(Component.text("\n===================\n\n", TextColor.fromHexString("#000000")))
                .append(Component.text("Have a nice and enjoyable stay!", TextColor.fromHexString("#ffc70f"))));
        page.add(Component.text("        Features\n")
                .append(Component.text("===================\n", TextColor.fromHexString("#000000")))
                .append(Component.text("\u2022 Graves", TextColor.fromHexString("#ffb30f")))
                        .append(Component.text(" - When you die, a grave with all your items will spawn. The lifespan of the grave is one hour and can you can have up to three at once. If  you die while having three graves you will die normally. Graves drop all the items in")));
        page.add(Component.text("the end of their lifespan.\n")
                .append(Component.text("\u2022 Kingdoms", TextColor.fromHexString("#ffb30f"))).append(Component.text(" - This is like your normal teams option. You can create a kingdom and invite your friends to it. Every kingdom has a separator in the tab that separates all kindom members from the rest of the players. (This feature is still WIP)")));
        page.add(Component.text("\u2022 Locations", TextColor.fromHexString("#ffb30f"))
                .append(Component.text(" - Allows you to save and share locations easily in-game.\n", TextColor.fromHexString("#000000"))
                .append(Component.text("\u2022 Coordinates/XYZ", TextColor.fromHexString("#ffb30f")))
                .append(Component.text(" - Makes sharing your coordinates easier with a command and a name.\n", TextColor.fromHexString("#000000")))
                .append(Component.text("\u2022 Profiles", TextColor.fromHexString("#ffb30f")))
                .append(Component.text(" - Gives you little information about you or a player.\n", TextColor.fromHexString("#000000")))
                .append(Component.text("\u2022 Stores", TextColor.fromHexString("#ffb30f")))
                .append(Component.text(" - Option to create a secure and unique way for selling\n", TextColor.fromHexString("#000000")))));
        page.add(Component.text("or buying items.\n")
                .append(Component.text("\u2022 ---- (Coming soon)", TextColor.fromHexString("#c42d12"))));
        page.add(Component.text("           Staff\n")
                .append(Component.text("===================\n", TextColor.fromHexString("#000000")))
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
        setItemMeta(book);
    }
}
