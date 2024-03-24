package com.rosekingdom.rosekingdom.Tab;

import com.rosekingdom.rosekingdom.Core.Database.Main_Statements.UserStatement;
import com.rosekingdom.rosekingdom.RoseKingdom;
import com.rosekingdom.rosekingdom.Tab.Teams.Kingdom;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.List;


public class TabSystem {
    static ScoreboardManager manager = Bukkit.getScoreboardManager();
    static Scoreboard board = manager.getNewScoreboard();
    private static List<Kingdom> kingdomList = new ArrayList<>();
    public static Scoreboard getBoard(){
        return board;
    }
    public static List<Kingdom> getKingdoms(){
        return kingdomList;
    }
    public static void addKingdom(Kingdom team){
        kingdomList.add(team);
    }
    public static void removeKingdom(Kingdom team) {
        kingdomList.remove(team);
    }

    public static void join(Player player){
        Kingdom kingdom = getKingdom(player);
        if(kingdom != null){
            Team rank = kingdom.getPlayerRank(player);
            rank.addPlayer(player);
            player.displayName(rank.prefix().append(Component.text(player.getName())));
            RankHandler.setPlayerRank(player, rank);

            int onlineMembers = 0;
            for(Player member : kingdom.getMembers()){
                if(member.isOnline()){
                    onlineMembers++;
                }
            }
            if(onlineMembers >= 1){
                kingdom.showSeparator();
            }
        }
        String rankName = UserStatement.getRank(player.getUniqueId());
        Rank rank = Rank.valueOf(rankName);
        for(Team ranks : RankHandler.getBaseRanks()){
            if(ranks.getName().contains(rankName)){
                ranks.addPlayer(player);
                ranks.prefix(Component.text(rank.prefix));
                player.displayName(Component.text(rank.prefix + player.getName()));
                RankHandler.setPlayerRank(player, ranks);
                break;
            }
        }

        refreshScoreboard();

        //Permissions
        PermissionAttachment attachment = player.addAttachment(JavaPlugin.getPlugin(RoseKingdom.class));
        attachment.setPermission("rk."+rank, true);
        player.updateCommands();
    }

    public static boolean isInKingdom(Player player){
        for(Kingdom kingdom : kingdomList){
            if(kingdom.getMembers().contains(player)){
                return true;
            }
        }
        return false;
    }

    public static Kingdom getKingdom(Player player){
        for(Kingdom kingdom : kingdomList){
            if(kingdom.getMembers().contains(player)){
                return kingdom;
            }
        }
        return null;
    }

    public static void lastOnline(Player player){
        Kingdom kingdom = getKingdom(player);
        if(kingdom == null) return;
        boolean lastOnline = true;
        for(Player online : Bukkit.getOnlinePlayers()){
            for(Player member : kingdom.getMembers()){
                if (member.equals(online)) {
                    lastOnline = false;
                    break;
                }
            }
            if(!lastOnline) break;
        }
        if(lastOnline){
            kingdom.hideSeparator();
        }
    }

    public static void refreshScoreboard(){
        for(Player online : Bukkit.getOnlinePlayers()){
            online.setScoreboard(board);
        }
    }
}
