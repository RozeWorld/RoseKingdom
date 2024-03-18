package com.rosekingdom.rosekingdom.Core.CommandManager.FeatureTesting;

import com.rosekingdom.rosekingdom.Core.CommandManager.CommandRK;
import com.rosekingdom.rosekingdom.Core.NPCs.NPC;
import com.rosekingdom.rosekingdom.Core.NPCs.NPCHandler;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ProtocolTest extends CommandRK {

    public ProtocolTest(){
        setName("pt");
        setArgumentRequirement(true);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(!(sender instanceof Player player)){
            return;
        }

        if(args.length == 1){
            Location location = player.getLocation();
            NPC npc = new NPC(args[0], location);
            npc.spawn();
            npc.setRotation(true);
        }
        if(args.length == 2){

        }
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        if(args.length == 2){
            List<String> tabs = new ArrayList<>();
            for(int ids : NPCHandler.getIds()){
                tabs.add(Integer.toString(ids));
            }
        }
        return null;
    }
}
