package com.rosekingdom.rosekingdom.Tab.Teams;

import com.rosekingdom.rosekingdom.Core.Database.Main_Statements.UserStatement;
import com.rosekingdom.rosekingdom.Core.NPCs.NPC;
import com.rosekingdom.rosekingdom.Tab.Rank;
import com.rosekingdom.rosekingdom.Tab.TabSystem;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.HashMap;

public class Kingdom extends TabSystem {
    Team team;
    NPC separator;
    int teamRank;
    HashMap<Player, Rank> members = new HashMap<>();

    public Kingdom(String name){
        teamRank = getKingdoms().size();
        team = getBoard().registerNewTeam(teamRank+"0"+name);

        separator = new NPC(name,
                "ewogICJ0aW1lc3RhbXAiIDogMTcxMDg4NDQwNTE2MCwKICAicHJvZmlsZUlkIiA6ICI5ZDE1OGM1YjNiN2U0ZGNlOWU0OTA5MTdjNmJlYmM5MSIsCiAgInByb2ZpbGVOYW1lIiA6ICJTbm9uX1NTIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzYyM2NmYWY0MjZiNTZhZmVjM2VmOWUxYjg4ZGVhZjU5YzlkYWMwMDU4ZDNiOTE4Nzk1MzgwNzFjNjRkODVkMWEiCiAgICB9CiAgfQp9",
                "nKIof7pfbAUmqs+uRtNSvWa0M2/ZuPNqHWEzpk8JKe5vvrnyWtM+Udw2ehEM8Lvpzf/2x+AGc+DIJGXOiMJfIaGEiZUjONHXjhouA6mDcCx+kRNZZTmIT6pLF0s1Uni801v56yAPJSKgQ7vhZOmODRZgkHbLVoXG1oVWMan1vUiv573ISQ2/MF6huOgh/3hbUZU0JhOGv/NMjPaDDnYwLDkAMMqYWPeWX4xULZ+bs9KidMDgXI4WquD2uqaXgSbfkhWPySxSC2VYAxgHrGPiCwGPh5dp6YPnzD1/k1Om4XCNxhvUPPXr25yqKuN354/U4GlApBdMiEJK+9WsruK0agiahr1ARcEGlgiS7LwK39nr7Z7nKQz9NxHUhDEW9K719x5CAXqpt9R4ihmROq+rnU1xWBNViUjzszdNdyEaEyPtpVTAjghd0Erop7qK9mNkl1akiZtWReYPjMFZy9uPuKp2zLaJo9iYzNUKtZgz43VtxrPHjCmfPg4hy1PZ7I+OdcW6nTJMZidle9NA/xaExjl9/w0vTzU1LXJ5Jeo6B09Pq/ebOBK152t4VjSx8/28/l3G8l4NPZiqonk8BM4k2NHcI1Ma8OO9jVUoFhcNN0M2NfSUsg4HlCsglp6FqPqxWOLniEna5yCl4ker+ljEOMsDw+WvRGTJ9vKEQXTHD88=");
        separator.addToTabOnly();
    }
    public void join(Player player){
        Rank rank = Rank.valueOf(UserStatement.getRank(player.getUniqueId()));
        int rankingNum = 1;
        for(Rank ranking : Rank.values()){
            if(rank.equals(ranking)){

            }
        }
    }
}
