package com.rosekingdom.rosekingdom.Economy.Commands;

import com.rosekingdom.rosekingdom.Core.CommandManager.subCommandRK;
import com.rosekingdom.rosekingdom.Economy.Statements.EconomyStatement;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class remCoins extends subCommandRK {
    public remCoins(int arg){
        super(arg);
        setName("remove");
    }
    @Override
    public void executeSub(CommandSender sender, String[] args) {
        if(!(sender instanceof Player player)){
            return;
        }
        EconomyStatement.remCoins(player, Integer.parseInt(args[1]));
    }
}