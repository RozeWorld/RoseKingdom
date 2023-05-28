package com.rosekingdom.rosekingdom.Premissions;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;


public class Teams {
    static Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
    static Team team = scoreboard.registerNewTeam("Default");
    public static void setDefaultRank(Player player){
        team.addPlayer(player);
        team.displayName(Component.text("[", TextColor.fromHexString("#696969"))
                .append(Component.text("Default", TextColor.fromHexString("#a57cc2"))
                        .append(Component.text("] ", TextColor.fromHexString("#696969")))));
        team.prefix(Component.text("[", TextColor.fromHexString("#696969"))
                .append(Component.text("Default", TextColor.fromHexString("#a57cc2"))
                        .append(Component.text("] ", TextColor.fromHexString("#696969")))));
        player.displayName(Component.text("[", TextColor.fromHexString("#696969"))
                .append(Component.text("Default", TextColor.fromHexString("#a57cc2"))
                        .append(Component.text("] ", TextColor.fromHexString("#696969")))
                        .append(Component.text(player.getName(), TextColor.fromHexString("#ffffff")))));
        for(Player online : Bukkit.getOnlinePlayers()){
            online.setScoreboard(scoreboard);
        }
    }

//    public static void setRank(Player p, String rank){
//        p.displayName(Component.text("[", TextColor.fromHexString("#696969"))
//                .append(Component.text(rank.toUpperCase(), TextColor.fromHexString("#a57cc2"))
//                        .append(Component.text("] ", TextColor.fromHexString("#696969")))
//                        .append(Component.text(p.getName(), TextColor.fromHexString("#ffffff")))));
//        p.playerListName(Component.text("[", TextColor.fromHexString("#696969"))
//                .append(Component.text(rank.toUpperCase(), TextColor.fromHexString("#a57cc2"))
//                        .append(Component.text("] ", TextColor.fromHexString("#696969")))
//                        .append(Component.text(p.getName(), TextColor.fromHexString("#ffffff")))));
//
//        UserStatement.addRank(p.getUniqueId().toString(), rank.toLowerCase());
//    }
//
//    public static void setDefaultRank(Player player){
//        if(!UserStatement.hasRank(player.getUniqueId().toString(), null)){
//            setRank(player, "Default");
//        }
//    }
}
