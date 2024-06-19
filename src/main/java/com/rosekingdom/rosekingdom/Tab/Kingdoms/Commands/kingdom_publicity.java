package com.rosekingdom.rosekingdom.Tab.Kingdoms.Commands;

import com.rosekingdom.rosekingdom.Core.CommandManager.subCommandRK;
import com.rosekingdom.rosekingdom.Core.Utils.Message;
import com.rosekingdom.rosekingdom.Tab.Kingdoms.Kingdom;
import com.rosekingdom.rosekingdom.Tab.Kingdoms.KingdomHandler;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class kingdom_publicity extends subCommandRK {

    public kingdom_publicity(int arg) {
        super(arg);
        setName("public");
        addAlias("p");
    }

    @Override
    public void executeSub(CommandSender sender, String[] args) {
        if(!(sender instanceof Player player)) {
            return;
        }
        Kingdom kingdom = KingdomHandler.getKingdom(player);
        if(kingdom == null) {
            player.sendMessage(Message.Info("You are not in a kingdom!"));
            return;
        }
        if(kingdom.getOwner().equals(player.getUniqueId())){
            if(args.length == 2) {
                kingdom.setPublic(Boolean.parseBoolean(args[1]));
            }else{
                if(kingdom.isPublic()){
                    player.sendMessage(Message.Info(kingdom.getName() + " is now public!"));
                }else {
                    player.sendMessage(Message.Info(kingdom.getName() + " is now private!"));
                }
            }
        }else{
            player.sendMessage(Message.Warning("You don't have permissions to change the kingdom!"));
        }
    }
}
