package com.rosekingdom.rosekingdom.Tab.Teams.Commands;

import com.rosekingdom.rosekingdom.Core.CommandManager.subCommandRK;
import com.rosekingdom.rosekingdom.Core.Utils.Message;
import com.rosekingdom.rosekingdom.Tab.TabSystem;
import com.rosekingdom.rosekingdom.Tab.Teams.Kingdom;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class removeKingdom extends subCommandRK {

    public removeKingdom(int arg){
        super(arg);
        setName("remove");
        addAlias("delete");
    }

    @Override
    public void executeSub(CommandSender sender, String[] args) {
        if(!(sender instanceof Player player)) return;
        if(args.length == 2){
            for(Kingdom kingdom : TabSystem.getKingdoms()){
                if(kingdom.getName().equalsIgnoreCase(args[1])){
                    kingdom.deleteKingdom();
                    TabSystem.join(player);
                    player.sendMessage(Message.Warning(kingdom.getName() + " has been deleted!"));
                    return;
                }
            }
        }
    }
}
