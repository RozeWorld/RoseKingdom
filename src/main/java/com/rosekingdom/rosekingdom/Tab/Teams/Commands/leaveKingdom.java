package com.rosekingdom.rosekingdom.Tab.Teams.Commands;

import com.rosekingdom.rosekingdom.Core.CommandManager.subCommandRK;
import com.rosekingdom.rosekingdom.Core.Utils.Message;
import com.rosekingdom.rosekingdom.Tab.TabSystem;
import com.rosekingdom.rosekingdom.Tab.Teams.Kingdom;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class leaveKingdom extends subCommandRK {

    public leaveKingdom(int arg){
        super(arg);
        setName("leave");
        addAlias("l");
        addAlias("lv");
    }

    @Override
    public void executeSub(CommandSender sender, String[] args) {
        if(!(sender instanceof Player player)) return;
        Kingdom kingdom = TabSystem.getKingdom(player);
        if(kingdom == null) {
            player.sendMessage(Message.Info("You are not in a kingdom!"));
            return;
        }
        kingdom.leaveKingdom(player);
    }
}
