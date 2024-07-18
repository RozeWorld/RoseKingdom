package com.rosekingdom.rosekingdom.Tab.Kingdoms.Commands;

import com.rosekingdom.rosekingdom.Core.CommandManager.subCommandRK;
import com.rosekingdom.rosekingdom.Core.Utils.Message;
import com.rosekingdom.rosekingdom.Tab.Kingdoms.Kingdom;
import com.rosekingdom.rosekingdom.Tab.Kingdoms.KingdomHandler;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class kingdom_rename extends subCommandRK {

    public kingdom_rename(int arg) {
        super(arg);
        setName("rename");
    }

    @Override
    public void executeSub(CommandSender sender, String[] args) {
        if(!(sender instanceof Player player)) return;
        Kingdom kingdom = KingdomHandler.getKingdom(player);
        if(kingdom == null) {
            player.sendMessage(Message.Info("You are not in a kingdom!"));
            return;
        }
        kingdom.setName(args[1]);
    }
}
