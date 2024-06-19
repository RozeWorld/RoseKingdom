package com.rosekingdom.rosekingdom.Tab.Kingdoms.Commands;

import com.rosekingdom.rosekingdom.Core.CommandManager.subCommandRK;
import com.rosekingdom.rosekingdom.Core.Utils.Message;
import com.rosekingdom.rosekingdom.Tab.Kingdoms.Kingdom;
import com.rosekingdom.rosekingdom.Tab.Kingdoms.KingdomHandler;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class kingdom_join extends subCommandRK {

    public kingdom_join(int arg){
        super(arg);
        setName("join");
    }

    @Override
    public void executeSub(CommandSender sender, String[] args) {
        if(!(sender instanceof Player player)) return;
        if(args.length == 2) {
            if (!KingdomHandler.isInKingdom(player)) {
                for (Kingdom kingdom : KingdomHandler.getKingdoms()) {
                    if (kingdom.getName().equals(args[1]))
                        if (kingdom.isPublic()) {
                            kingdom.joinKingdom(player);
                            return;
                        } else {
                            player.sendMessage(Message.Info("This kingdom is not public."));
                            return;
                        }
                }
                player.sendMessage(Message.Warning("There is not such kingdom!"));
            }else{
                player.sendMessage(Message.Info("You are already in a kingdom!"));
                player.sendMessage(Message.Warning("You need to abandon your kingdom to join a new one."));
            }
        }else{
            player.sendMessage(Message.Warning("You need to specify a kingdom!"));
        }
    }
}
