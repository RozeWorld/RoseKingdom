package com.rosekingdom.rosekingdom.Premissions;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.HashMap;
import java.util.Map;


public class Teams {
    private static final Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
    private static Map<String, Team> teams = new HashMap<>();
    private static Team team;

    public static void UpdateScoreboard(){
        for(Player online : Bukkit.getOnlinePlayers()){
            online.setScoreboard(scoreboard);
        }
    }

    public static void createTeams(){
        team = scoreboard.registerNewTeam("owner");
        teams.put("owner", team);
        team = scoreboard.registerNewTeam("admin");
        teams.put("admin", team);
        team = scoreboard.registerNewTeam("mod");
        teams.put("mod", team);
        team = scoreboard.registerNewTeam("default");
        teams.put("default", team);
        setupPrefixes();
    }

    public static void setupPrefixes(){
        team = teams.get("owner");
        team.prefix(Component.text("\uEff4 "));
        team = teams.get("admin");
        team.prefix(Component.text("\uEff6 "));
        team = teams.get("mod");
        team.prefix(Component.text("\uEff7 "));
        team = teams.get("default");
        team.prefix(Component.text("\uEff5 "));
    }

    public static void joinTeam(Player player, String team){
        teams.get(team).addPlayer(player);
        player.displayName(teams.get(team).prefix().append(Component.text(player.getName())));
        UpdateScoreboard();
    }
}
