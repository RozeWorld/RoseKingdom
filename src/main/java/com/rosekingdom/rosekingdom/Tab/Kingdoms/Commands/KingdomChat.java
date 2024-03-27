package com.rosekingdom.rosekingdom.Tab.Kingdoms.Commands;

import com.rosekingdom.rosekingdom.Core.CommandManager.subCommandRK;
import org.bukkit.command.CommandSender;

public class KingdomChat extends subCommandRK {

    public KingdomChat(int arg){
        super(arg);
        setName("chat");
        addAlias("c");
    }

    @Override
    public void executeSub(CommandSender sender, String[] args) {

    }
}
