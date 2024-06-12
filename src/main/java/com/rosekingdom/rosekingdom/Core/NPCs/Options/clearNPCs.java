package com.rosekingdom.rosekingdom.Core.NPCs.Options;

import com.rosekingdom.rosekingdom.Core.CommandManager.subCommandRK;
import com.rosekingdom.rosekingdom.Core.NPCs.NPC;
import com.rosekingdom.rosekingdom.Core.NPCs.NPCHandler;
import com.rosekingdom.rosekingdom.Tab.Tab;
import org.bukkit.command.CommandSender;
import org.bukkit.scoreboard.Team;

public class clearNPCs extends subCommandRK {

    public clearNPCs(int arg){
        super(arg);
        setName("clear");
    }

    @Override
    public void executeSub(CommandSender sender, String[] args) {
        for(NPC npc : NPCHandler.getNPCs()){
            for(Team team : Tab.getBoard().getTeams()){
                if(team.getName().equals(npc.getName())){
                    team.unregister();
                }
            }
            npc.despawn();
        }
        Tab.refreshScoreboard();
    }
}
