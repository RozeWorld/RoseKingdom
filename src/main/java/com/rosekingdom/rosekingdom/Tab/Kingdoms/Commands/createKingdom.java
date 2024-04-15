package com.rosekingdom.rosekingdom.Tab.Kingdoms.Commands;

import com.rosekingdom.rosekingdom.Core.CommandManager.subCommandRK;
import com.rosekingdom.rosekingdom.Core.Utils.Message;
import com.rosekingdom.rosekingdom.Tab.Kingdoms.Kingdom;
import com.rosekingdom.rosekingdom.Tab.Kingdoms.KingdomHandle;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class createKingdom extends subCommandRK {

    public createKingdom(int arg){
        super(arg);
        setName("create");
    }

    @Override
    public void executeSub(CommandSender sender, String[] args) {
        if(!(sender instanceof Player player)) return;
        if(args.length == 2){
            if(KingdomHandler.getKingdoms().size()+1 > 10){
                player.sendMessage(Message.Warning("Unable to create a kingdom cause the exceeding number of kingdoms!"));
            }
            if(!KingdomHandler.isInKingdom(player)){
                Kingdom kingdom = new Kingdom(args[1], player);
                kingdom.joinKingdom(player);
            }else{
                player.sendMessage(Message.Info("You are already in a kingdom!"));
                player.sendMessage(Message.Warning("You need to abandon your kingdom to create a new one."));
            }
        }
    }
}
