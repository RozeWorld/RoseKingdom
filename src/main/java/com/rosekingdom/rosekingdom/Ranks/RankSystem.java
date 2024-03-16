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
import java.util.UUID;


public class RankSystem {
    private static final Map<Player, Team> teamRank = new HashMap<>();
    private static final Map<Player, Team> isAFK = new HashMap<>();
    static ScoreboardManager manager = Bukkit.getScoreboardManager();
    static Scoreboard board = manager.getNewScoreboard();
    public static void loadRank(Player player){
        String userRank = UserStatement.getRank(player.getUniqueId());
        Rank rank = Rank.valueOf(userRank);
        player.displayName(Component.text(rank.prefix + player.getName()));
        String numberedRank = "";
        switch (rank.name()){
            case "OWNER" -> numberedRank = "01"+Rank.OWNER.name();
            case "ADMIN" -> numberedRank = "02"+Rank.ADMIN.name();
            case "MOD" -> numberedRank = "03"+Rank.MOD.name();
            case "ARTIST" -> numberedRank = "04"+Rank.ARTIST.name();
            case "DEFAULT" -> numberedRank = "05"+Rank.DEFAULT.name();
        }
        Team team = board.getTeam(numberedRank);
        team.addPlayer(player);
        team.prefix(Component.text(rank.prefix));
        refreshScoreboard();
        teamRank.put(player, team);

        PermissionAttachment attachment = player.addAttachment(JavaPlugin.getPlugin(RoseKingdom.class));
        attachment.setPermission("rk."+rank, true);
        player.updateCommands();
    }

    public static void setStatusAFK(Player player) {
        Team team = board.registerNewTeam(String.valueOf(UUID.randomUUID()));
        team.prefix(teamRank.get(player).prefix());
        team.suffix(Component.text("\uDB00\uDC03\uEa06"));
        team.addPlayer(player);
        isAFK.put(player, team);
        refreshScoreboard();
    }
    public static void removeStatusAFK(Player player) {
        if(isAFK.containsKey(player)){
            isAFK.get(player).unregister();
            isAFK.remove(player);
        }
        teamRank.get(player).addPlayer(player);
        //#48 issue suspect
        refreshScoreboard();
    }

    private static void refreshScoreboard(){
        for(Player online : Bukkit.getOnlinePlayers()){
            online.setScoreboard(board);
        }
    }

    public static void registerAllRanks() {
        board.registerNewTeam("01"+Rank.OWNER.name());
        board.registerNewTeam("02"+Rank.ADMIN.name());
        board.registerNewTeam("03"+Rank.MOD.name());
        board.registerNewTeam("04"+Rank.ARTIST.name());
        board.registerNewTeam("05"+Rank.DEFAULT.name());
    }
}
