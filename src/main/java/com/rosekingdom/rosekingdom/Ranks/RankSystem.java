package com.rosekingdom.rosekingdom.Ranks;

import com.rosekingdom.rosekingdom.Core.Database.Main_Statements.UserStatement;
import com.rosekingdom.rosekingdom.RoseKingdom;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import java.util.HashMap;
import java.util.Map;


public class RankSystem {
    private static final Map<Player, Team> teamRank = new HashMap<>();
    static ScoreboardManager manager = Bukkit.getScoreboardManager();
    static Scoreboard board = manager.getNewScoreboard();
    public static void loadRank(Player player){
        String userRank = UserStatement.getRank(player.getUniqueId());
        Rank rank = Rank.valueOf(userRank);
        player.displayName(Component.text(rank.prefix + player.getName()));

        Team team = board.getTeam(rank.name());
        team.addPlayer(player);
        team.prefix(Component.text(rank.prefix));
        refreshScoreboard();
        teamRank.put(player, team);

        PermissionAttachment attachment = player.addAttachment(JavaPlugin.getPlugin(RoseKingdom.class));
        attachment.setPermission("rk."+rank, true);
        player.updateCommands();
    }

    public static void setStatusAFK(Player player) {
        teamRank.get(player).suffix(Component.text("\uDB00\uDC03\uEa06"));
        refreshScoreboard();
    }
    public static void removeStatusAFK(Player player) {
        teamRank.get(player).suffix(Component.empty());
        refreshScoreboard();
    }

    private static void refreshScoreboard(){
        for(Player online : Bukkit.getOnlinePlayers()){
            online.setScoreboard(board);
        }
    }

    public static void registerAllRanks() {
        board.registerNewTeam(Rank.OWNER.name());
        board.registerNewTeam(Rank.ADMIN.name());
        board.registerNewTeam(Rank.MOD.name());
        board.registerNewTeam(Rank.ARTIST.name());
        board.registerNewTeam(Rank.DEFAULT.name());
    }
}
