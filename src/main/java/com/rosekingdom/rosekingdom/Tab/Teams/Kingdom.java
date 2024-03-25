package com.rosekingdom.rosekingdom.Tab.Teams;

import com.rosekingdom.rosekingdom.Core.Database.Main_Statements.UserStatement;
import com.rosekingdom.rosekingdom.Core.NPCs.NPC;
import com.rosekingdom.rosekingdom.Core.NPCs.NPCHandler;
import com.rosekingdom.rosekingdom.Core.Utils.Message;
import com.rosekingdom.rosekingdom.Tab.Rank;
import com.rosekingdom.rosekingdom.Tab.RankHandler;
import com.rosekingdom.rosekingdom.Tab.TabSystem;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Kingdom extends TabSystem {
    String name;
    Team team;
    NPC separator;
    int teamRank;
    Map<Player, Team> members = new HashMap<>();
    Player owner;

    public Kingdom(String name, Player player){
        teamRank = getKingdoms().size()+1;
        team = getBoard().registerNewTeam("1"+teamRank+"00"+name);
        team.prefix(Component.text("\uDB00\uDC02"));
        team.color(NamedTextColor.GRAY);
        separator = new NPC(name,
                "ewogICJ0aW1lc3RhbXAiIDogMTcxMDg4NDQwNTE2MCwKICAicHJvZmlsZUlkIiA6ICI5ZDE1OGM1YjNiN2U0ZGNlOWU0OTA5MTdjNmJlYmM5MSIsCiAgInByb2ZpbGVOYW1lIiA6ICJTbm9uX1NTIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzYyM2NmYWY0MjZiNTZhZmVjM2VmOWUxYjg4ZGVhZjU5YzlkYWMwMDU4ZDNiOTE4Nzk1MzgwNzFjNjRkODVkMWEiCiAgICB9CiAgfQp9",
                "nKIof7pfbAUmqs+uRtNSvWa0M2/ZuPNqHWEzpk8JKe5vvrnyWtM+Udw2ehEM8Lvpzf/2x+AGc+DIJGXOiMJfIaGEiZUjONHXjhouA6mDcCx+kRNZZTmIT6pLF0s1Uni801v56yAPJSKgQ7vhZOmODRZgkHbLVoXG1oVWMan1vUiv573ISQ2/MF6huOgh/3hbUZU0JhOGv/NMjPaDDnYwLDkAMMqYWPeWX4xULZ+bs9KidMDgXI4WquD2uqaXgSbfkhWPySxSC2VYAxgHrGPiCwGPh5dp6YPnzD1/k1Om4XCNxhvUPPXr25yqKuN354/U4GlApBdMiEJK+9WsruK0agiahr1ARcEGlgiS7LwK39nr7Z7nKQz9NxHUhDEW9K719x5CAXqpt9R4ihmROq+rnU1xWBNViUjzszdNdyEaEyPtpVTAjghd0Erop7qK9mNkl1akiZtWReYPjMFZy9uPuKp2zLaJo9iYzNUKtZgz43VtxrPHjCmfPg4hy1PZ7I+OdcW6nTJMZidle9NA/xaExjl9/w0vTzU1LXJ5Jeo6B09Pq/ebOBK152t4VjSx8/28/l3G8l4NPZiqonk8BM4k2NHcI1Ma8OO9jVUoFhcNN0M2NfSUsg4HlCsglp6FqPqxWOLniEna5yCl4ker+ljEOMsDw+WvRGTJ9vKEQXTHD88=");
        separator.addToTabOnly();
        team.addEntity(separator.getNPC().getBukkitEntity());
        this.name = name;
        this.owner = player;
        addKingdom(this);
    }

    public String getName(){
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Set<Player> getMembers(){
        return members.keySet();
    }
    public void removeMember(Player player){
        int playersWithThatRank = 0;
        for(Team rank : members.values()){
            if(rank.equals(members.get(player))) playersWithThatRank++;
        }
        if(playersWithThatRank <= 1){
            members.get(player).unregister();
        }
        members.remove(player);
    }
    public Team getPlayerRank(Player player){
        return members.get(player);
    }

    public Player getOwner(){
        return owner;
    }


    public void joinKingdom(Player player){
        String playerRankName = UserStatement.getRank(player.getUniqueId());
        Rank playerRank = Rank.valueOf(playerRankName);
        String rankName = "1"+teamRank+"0"+RankHandler.getRankNumber(playerRankName)+playerRankName;
        for(Team ranking : members.values()){
            if(ranking.getName().equals(rankName)){
                ranking.addPlayer(player);
                RankHandler.setPlayerRank(player, ranking);
                members.put(player, ranking);
                return;
            }
        }
        Team rank = getBoard().registerNewTeam(rankName);
        rank.prefix(Component.text(playerRank.prefix));
        rank.addPlayer(player);
        RankHandler.setPlayerRank(player, rank);
        members.put(player, rank);
        player.sendMessage(Message.Info("You joined " + name + "!"));
    }

    public void leaveKingdom(Player player) {
        String rankName = UserStatement.getRank(player.getUniqueId());
        for(Team ranks : RankHandler.getBaseRanks()){
            if(ranks.getName().contains(rankName)) {
                removeMember(player);
                ranks.addPlayer(player);
                RankHandler.setPlayerRank(player, ranks);
                if(getMembers().isEmpty()){
                    deleteKingdom();
                }
            }
        }
    }

    public void deleteKingdom(){
        NPCHandler.removeNPC(separator.getId());
        team.unregister();
        for(Team team : members.values()){
            team.unregister();
        }
        refreshScoreboard();
        TabSystem.removeKingdom(this);

    }

    public void hideSeparator() {
        separator.despawn();
    }

    public void showSeparator() {
        separator.addToTabOnly();
    }
}
