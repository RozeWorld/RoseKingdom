package com.rosekingdom.rosekingdom.Tab;

import com.rosekingdom.rosekingdom.Core.Database.Main_Statements.UserStatement;
import com.rosekingdom.rosekingdom.RoseKingdom;
import com.rosekingdom.rosekingdom.Tab.Kingdoms.Kingdom;
import com.rosekingdom.rosekingdom.Tab.Kingdoms.KingdomHandler;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.java.JavaPlugin;
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
        attachment.setPermission("rk." + rank, true);
        player.updateCommands();
    }

    public static void refreshScoreboard(){
        for(Player online : Bukkit.getOnlinePlayers()){
            online.setScoreboard(board);
        }
    }
}
