package com.rosekingdom.rosekingdom.Events;

import com.rosekingdom.rosekingdom.Database.Database;
import com.rosekingdom.rosekingdom.Database.Tables.UserTable;
import com.rosekingdom.rosekingdom.RoseKingdom;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.JoinConfiguration;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class onJoin implements Listener {

    private final RoseKingdom plugin;
    public onJoin(RoseKingdom plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoinEvent(PlayerJoinEvent e){
        Player player = e.getPlayer();
        plugin.players++;
        player.sendPlayerListHeader(Component.text("\nRoseKingdom\n", TextColor.fromHexString("#ff0000")));
        for(Player p : Bukkit.getServer().getOnlinePlayers()){
            p.sendPlayerListFooter(Component.text("\nOnline Players: ",TextColor.fromHexString("#ffb415"))
                        .append(Component.text(plugin.players,TextColor.fromHexString("#2eff31"))));
        }
        //Join Message
        e.joinMessage(
                Component.text("[", TextColor.fromHexString("#696969"))
                        .append(Component.text("+", TextColor.fromHexString("#3fd951"))
                        .append(Component.text("] ", TextColor.fromHexString("#696969")))
                        .append(player.displayName().color(TextColor.fromHexString("#7d7d7d")))));

        if(!player.hasPlayedBefore()){
            player.sendMessage(Component.text("=====================================================\n",TextColor.fromHexString("#f5b431"))
                    .append(Component.text("              Welcome to RoseKingdom!\n",TextColor.fromHexString("#f5b431")))
                    .append(Component.text("=====================================================",TextColor.fromHexString("#f5b431"))));
        }

        //Add player to Database
        if(!UserTable.exists(plugin.sql.getConnection(), player.getUniqueId())) {
            UserTable.insert(plugin.sql.getConnection(), player.getName(), player.getUniqueId().toString());
        }
    }
}
