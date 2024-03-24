package com.rosekingdom.rosekingdom.Core.NPCs.Options;

import com.rosekingdom.rosekingdom.Core.CommandManager.subCommandRK;
import com.rosekingdom.rosekingdom.Core.NPCs.NPC;
import com.rosekingdom.rosekingdom.Tab.TabSystem;
import org.bukkit.command.CommandSender;
import org.bukkit.scoreboard.Team;

public class tabTest extends subCommandRK {

    public tabTest(int arg){
        super(arg);
        setName("test");
    }

    @Override
    public void executeSub(CommandSender sender, String[] args) {
        if(args.length == 2){
            int n = Integer.parseInt(args[1]);
            NPC npc = new NPC(n+"npc");
            npc.addToTabOnly();
            Team team = TabSystem.getBoard().registerNewTeam(n+"npc");
            team.addEntity(npc.getNPC().getBukkitEntity());
        }
        if(args.length == 3){
            int n = Integer.parseInt(args[1]);
            int m = Integer.parseInt(args[2]);
            for(; n <= m; n++){
                NPC npc = new NPC(n+"npc");
                npc.addToTabOnly();
                Team team = TabSystem.getBoard().registerNewTeam(n+"npc");
                team.addEntity(npc.getNPC().getBukkitEntity());
            }
        }

    }
}
