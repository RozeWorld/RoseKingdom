package com.rosekingdom.rosekingdom.Tab;

import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

import java.util.*;

public class RankHandler extends TabSystem {
    private static final List<Team> baseRanks = new ArrayList<>();
    private static final Map<Player, Team> playerRanks = new HashMap<>();
    private static final Map<Player, Team> isAFK = new HashMap<>();
    private static final Map<String, Integer> rankNumbers = new HashMap<>();
    public static int getRankNumber(String rank){
        return rankNumbers.get(rank);
    }
    public static List<Team> getBaseRanks(){
        return baseRanks;
    }
    public static void setPlayerRank(Player player, Team team){
        playerRanks.put(player, team);
    }
    public static Team getPlayerRank(Player player){
        return playerRanks.get(player);
    }


    public static void registerBaseRanks(){
        baseRanks.add(board.registerNewTeam("1001"+Rank.OWNER.name()));
        baseRanks.add(board.registerNewTeam("1002"+Rank.ADMIN.name()));
        baseRanks.add(board.registerNewTeam("1003"+Rank.MOD.name()));
        baseRanks.add(board.registerNewTeam("1004"+Rank.ARTIST.name()));
        baseRanks.add(board.registerNewTeam("1005"+Rank.DEFAULT.name()));
        int number = 1;
        for(Rank rank : Rank.values()){
            rankNumbers.put(rank.name(), number);
            number++;
        }
    }

    public static void setStatusAFK(Player player) {
        Team base = playerRanks.get(player);
        Team team = board.registerNewTeam(base.getName().substring(1,4) + UUID.randomUUID());
        team.prefix(base.prefix());
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
        TabSystem.join(player);
        refreshScoreboard();
    }
}
