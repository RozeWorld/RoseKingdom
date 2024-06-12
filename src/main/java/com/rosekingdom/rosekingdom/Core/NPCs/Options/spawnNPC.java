package com.rosekingdom.rosekingdom.Core.NPCs.Options;

import com.rosekingdom.rosekingdom.Core.CommandManager.subCommandRK;
import com.rosekingdom.rosekingdom.Core.NPCs.NPC;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class spawnNPC extends subCommandRK {

    public spawnNPC(int arg){
        super(arg);
        setName("spawn");
        addAlias("add");
        addAlias("s");
    }
    @Override
    public void executeSub(CommandSender sender, String[] args) {
        if(!(sender instanceof Player player)){
            return;
        }
        NPC npc;
        if(args.length == 2){
            npc = new NPC(args[1], player.getLocation());
        }else if(args.length == 3){
            npc = new NPC(args[1], player.getLocation());
            npc.setOnTabList(Boolean.parseBoolean(args[2]));
        }else if(args.length == 4){
            npc = new NPC(args[1], player.getLocation(), args[3]);
            npc.setOnTabList(Boolean.parseBoolean(args[2]));
        }else{
            return;
        }
        npc.spawn();
    }
}
