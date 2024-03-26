package com.rosekingdom.rosekingdom.Tab.Kingdoms.Commands;

import com.rosekingdom.rosekingdom.Core.CommandManager.subCommandRK;
import com.rosekingdom.rosekingdom.Core.Utils.Message;
import com.rosekingdom.rosekingdom.Tab.Kingdoms.Kingdom;
import com.rosekingdom.rosekingdom.Tab.TabSystem;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class joinKingdom extends subCommandRK {

    public joinKingdom(int arg){
        super(arg);
        setName("join");
    }

    @Override
    public void executeSub(CommandSender sender, String[] args) {
        if(!(sender instanceof Player player)) return;
        if(args.length == 2){
            if(!TabSystem.isInKingdom(player)){
                for(Kingdom kingdom : TabSystem.getKingdoms()) {
                    if (kingdom.getName().equals(args[1])) {
                        kingdom.joinKingdom(player);
                        return;
                    } else {
                        player.sendMessage(Message.Warning(args[1] + " doesn't exist!"));
                    }
                }
            }else{
                player.sendMessage(Message.Info("You are already in a kingdom!"));
                player.sendMessage(Message.Warning("You need to abandon your kingdom to join a new one."));
            }
        }
    }
}
