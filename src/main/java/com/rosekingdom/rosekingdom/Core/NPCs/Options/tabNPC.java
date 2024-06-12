package com.rosekingdom.rosekingdom.Core.NPCs.Options;

import com.rosekingdom.rosekingdom.Core.CommandManager.subCommandRK;
import com.rosekingdom.rosekingdom.Core.NPCs.NPC;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class tabNPC extends subCommandRK {

    public tabNPC(int arg){
        super(arg);
        setName("tab");
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
            npc = new NPC(args[1], player.getLocation(), args[2]);
        }else{
            return;
        }
        npc.addToTabOnly();
    }
}
