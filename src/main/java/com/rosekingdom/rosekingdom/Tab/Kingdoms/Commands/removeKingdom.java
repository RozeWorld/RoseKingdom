package com.rosekingdom.rosekingdom.Tab.Kingdoms.Commands;

import com.rosekingdom.rosekingdom.Core.CommandManager.subCommandRK;
import com.rosekingdom.rosekingdom.Core.Utils.Message;
import com.rosekingdom.rosekingdom.Tab.Kingdoms.Kingdom;
import com.rosekingdom.rosekingdom.Tab.Kingdoms.KingdomHandle;
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
            for(Kingdom kingdom : KingdomHandler.getKingdoms()){
                if(kingdom.getName().equalsIgnoreCase(args[1])){
                    if(kingdom.getOwner().equals(player.getUniqueId())) {
                        kingdom.deleteKingdom();
                        player.sendMessage(Message.Warning(kingdom.getName() + " has been deleted!"));
                        return;
                    }else{
                        player.sendMessage(Message.Warning("You don't have a permission to run this action!"));
                    }
                }
            }
        }
    }
}
