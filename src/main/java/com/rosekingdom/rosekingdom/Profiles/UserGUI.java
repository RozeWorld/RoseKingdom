package com.rosekingdom.rosekingdom.Profiles;

import com.rosekingdom.rosekingdom.Core.Items.InvsibleItem;
import com.rosekingdom.rosekingdom.Core.gui.InventoryButton;
import com.rosekingdom.rosekingdom.Core.gui.InventoryTypes.InventoryGUI;
import com.rosekingdom.rosekingdom.Profiles.Items.ActivityIndicator;
import com.rosekingdom.rosekingdom.Profiles.Items.ProfilePlayTime;
import com.rosekingdom.rosekingdom.Profiles.Items.StreakScore;
import com.rosekingdom.rosekingdom.RoseKingdom;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class UserGUI extends InventoryGUI {

    OfflinePlayer target;

    public UserGUI(OfflinePlayer player){
        this.target = player;
    }

    @Override
    protected Inventory createInventory() {
        return Bukkit.createInventory(null, 3*9, Component.text("\u00A7f\uDAFF\uDFF8\uEFB0"));
    }

    @Override
    public void decorate(Player player) {
        int[] playerHead = {0,1,2,9,10,11,18,19,20};
        for(int i : playerHead){
            this.addButton(i, createButton(new InvsibleItem(Component.text(target.getName(), TextColor.fromHexString("#9c9c9c")))));
        }


        var profile = Bukkit.createProfile(target.getUniqueId());
        profile.update().thenAcceptAsync(
                updatedProfile -> {
                    var head = ItemStack.of(Material.PLAYER_HEAD);
                    var meta = (SkullMeta) head.getItemMeta();
                    meta.displayName(Component.text(player.getName(), TextColor.fromHexString("#9c9c9c")).decoration(TextDecoration.ITALIC, false));
                    meta.setCustomModelData(2000);
                    meta.setPlayerProfile(updatedProfile);
                    head.setItemMeta(meta);
                    this.addButton(10, createButton(head));
                    super.decorate(player);
                },
                runnable -> Bukkit. getScheduler().runTask(RoseKingdom.getPlugin(RoseKingdom.class), runnable));
        this.addButton(21, createButton(new ProfilePlayTime(target)));
        this.addButton(8, createButton(new ActivityIndicator(target)));
        this.addButton(22, createButton(new StreakScore(target)));
        super.decorate(player);
    }

    private InventoryButton createButton(ItemStack item) {
        return new InventoryButton()
                .creator(player -> item)
                .consumer(event -> {});
    }
}
