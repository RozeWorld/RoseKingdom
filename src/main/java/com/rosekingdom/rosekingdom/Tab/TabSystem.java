package com.rosekingdom.rosekingdom.Tab;

import com.rosekingdom.rosekingdom.Core.Database.Main_Statements.UserStatement;
import com.rosekingdom.rosekingdom.RoseKingdom;
import com.rosekingdom.rosekingdom.Tab.Kingdoms.Kingdom;
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
    private static final List<Kingdom> kingdomList = new ArrayList<>();
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


    //TODO:fix the issue when smone creates a team then another person creates and the first person deletes his and makes a new one
    public static void join(Player player){
        Kingdom kingdom = getKingdom(player);
        String rankName = UserStatement.getRank(player.getUniqueId());
        Rank rank = Rank.valueOf(rankName);
        if(kingdom != null){
            Team playerRank = kingdom.getPlayerRank(player);
            playerRank.addPlayer(player);
            player.displayName(playerRank.prefix().append(Component.text(player.getName())));
            RankHandler.setPlayerRank(player, playerRank);

            if(!kingdom.getOnlinePlayers().isEmpty()){
                kingdom.showSeparator();
            }
        }else{
            for(Team ranks : RankHandler.getBaseRanks()){
                if(ranks.getName().contains(rankName)){
                    ranks.addPlayer(player);
                    ranks.prefix(Component.text(rank.prefix));
                    player.displayName(Component.text(rank.prefix + player.getName()));
                    RankHandler.setPlayerRank(player, ranks);
                    break;
                }
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
            if(kingdom.getMembers().contains(player.getUniqueId())){
                return true;
            }
        }
        return false;
    }

    public static Kingdom getKingdom(Player player){
        for(Kingdom kingdom : kingdomList){
            if(kingdom.getMembers().contains(player.getUniqueId())){
                return kingdom;
            }
        }
        return null;
    }

    public static void lastOnline(Player player){
        Kingdom kingdom = getKingdom(player);
        if(kingdom == null) return;
        if(kingdom.getOnlinePlayers().size()<=1){
            kingdom.hideSeparator();
        }
    }

    public static void refreshScoreboard(){
        for(Player online : Bukkit.getOnlinePlayers()){
            online.setScoreboard(board);
        }
    }
}
