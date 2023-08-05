package com.rosekingdom.rosekingdom.Events;

import com.rosekingdom.rosekingdom.Database.Database;
import com.rosekingdom.rosekingdom.Database.Statements.UserStatement;
import com.rosekingdom.rosekingdom.RoseKingdom;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class onJoin implements Listener {
    @EventHandler
    public void onJoinEvent(PlayerJoinEvent e){
        Player player = e.getPlayer();
        RoseKingdom.players++;
        player.sendPlayerListHeader(Component.text("\nRoseKingdom\n", TextColor.fromHexString("#ff0000")));
        for(Player p : Bukkit.getServer().getOnlinePlayers()){
            p.sendPlayerListFooter(Component.text("\nOnline Players: ",TextColor.fromHexString("#ffb415"))
                        .append(Component.text(RoseKingdom.players,TextColor.fromHexString("#2eff31"))));
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
        if(!UserStatement.exists(Database.getConnection(), player.getUniqueId())) {
            UserStatement.insert(Database.getConnection(), player.getName(), player.getUniqueId().toString());
        }

        if(UserStatement.getRank(player.getUniqueId().toString())==null){
            UserStatement.addRank(player.getUniqueId().toString(), "default");
        }
        Teams.joinTeam(player, UserStatement.getRank(player.getUniqueId().toString()));
        Teams.UpdateScoreboard();
    }
}
