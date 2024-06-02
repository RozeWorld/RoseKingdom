package com.rosekingdom.rosekingdom.Core.NPCs.Options;

import com.rosekingdom.rosekingdom.Core.CommandManager.subCommandRK;
import com.rosekingdom.rosekingdom.Core.NPCs.NPCHandler;
import com.rosekingdom.rosekingdom.Core.NPCs.Statements.NPCStatement;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class removeNPC extends subCommandRK {

    public removeNPC(int arg){
        super(arg);
        setName("remove");
        addAlias("rm");
    }

    @Override
    public void executeSub(CommandSender sender, String[] args) {
        if(!(sender instanceof Player player)){
            return;
        }
        if(args.length == 2){
            NPCHandler.removeNPC(Integer.parseInt(args[1]));
            NPCStatement.removeNPC(Integer.parseInt(args[1]));
        }
    }
}
