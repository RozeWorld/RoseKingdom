package com.rosekingdom.rosekingdom.Tab;

import com.rosekingdom.rosekingdom.Core.Database.Main_Statements.UserStatement;
import com.rosekingdom.rosekingdom.RoseKingdom;
import com.rosekingdom.rosekingdom.Tab.Kingdoms.Kingdom;
import com.rosekingdom.rosekingdom.Tab.Kingdoms.KingdomHandler;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

public class Tab {
    static ScoreboardManager manager = Bukkit.getScoreboardManager();
    static Scoreboard board = manager.getNewScoreboard();
    public static Scoreboard getBoard(){
        return board;
    }

    public static void join(Player player){
        String rankName = UserStatement.getRank(player.getUniqueId());
        Rank rank = Rank.valueOf(rankName);

        Kingdom kingdom = KingdomHandler.getKingdom(player);
        if(kingdom != null){
            kingdom.joinKingdom(player);
            if(!kingdom.getOnlinePlayers().isEmpty()){
                kingdom.createSeparator();
            }
        }else{
            for(Team ranks : RankHandler.getBaseRanks()){
                if(ranks.getName().contains(rankName)){
                    ranks.addPlayer(player);
                    ranks.prefix(Component.text(rank.prefix));
                    player.displayName(Component.text(rank.prefix).append(Component.text(player.getName(), TextColor.fromHexString(""))));
                    RankHandler.setPlayerRank(player, ranks);
                    break;
                }
            }
        }

        refreshScoreboard();

        //Permissions
        PermissionAttachment attachment = player.addAttachment(JavaPlugin.getPlugin(RoseKingdom.class));
        attachment.setPermission("rk." + rank, true);
        player.updateCommands();
    }

    public static void display(Player player){
        player.sendPlayerListHeader(Component.text("\u00a7f\uef05\uDAFF\uDFFF\uef06\uDAFF\uDFFF\uef07\uDAFF\uDFFE\uef08\n\n"));
        updatePlayerCount();
    }

    public static void updatePlayerCount(){
        BukkitScheduler scheduler = Bukkit.getScheduler();
        scheduler.scheduleSyncDelayedTask(JavaPlugin.getPlugin(RoseKingdom.class), () -> {
            for(Player on : Bukkit.getServer().getOnlinePlayers()){
                on.sendPlayerListFooter(Component.text("\nOnline Players: ", TextColor.fromHexString("#FFB415"))
                        .append(Component.text(Bukkit.getOnlinePlayers().size(),TextColor.fromHexString("#2eff31"))));
            }
        }, 10);
    }

    public static void refreshScoreboard(){
        for(Player online : Bukkit.getOnlinePlayers()){
            online.setScoreboard(board);
        }
    }
}
